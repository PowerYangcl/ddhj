package cn.com.ddhj.service.impl;

import java.math.BigDecimal;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.base.PageResult;
import cn.com.ddhj.dto.apppay.TPayInfoDto;
import cn.com.ddhj.helper.PropHelper;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TOrderMapper;
import cn.com.ddhj.mapper.TOrderRechargeMapper;
import cn.com.ddhj.mapper.apppay.TPayInfoMapper;
import cn.com.ddhj.mapper.user.TUserLoginMapper;
import cn.com.ddhj.mapper.user.TUserMapper;
import cn.com.ddhj.model.TOrder;
import cn.com.ddhj.model.TOrderRecharge;
import cn.com.ddhj.model.apppay.TPayInfo;
import cn.com.ddhj.model.map.MDataMap;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.model.user.TUserLogin;
import cn.com.ddhj.result.tuser.UserDataResult;
import cn.com.ddhj.service.IAppOrderPay;
import cn.com.ddhj.service.apporderpay.alipay.ApiAlipayMoveProcessService;
import cn.com.ddhj.service.apporderpay.result.ApiPayInfoResult;
import cn.com.ddhj.service.apporderpay.result.ApiPaymentAllResult;
import cn.com.ddhj.service.apporderpay.result.ApplePayResponse;
import cn.com.ddhj.service.apporderpay.result.ali.AlipayPaymentResult;
import cn.com.ddhj.service.apporderpay.result.wechat.WCPaymentResult;
import cn.com.ddhj.service.apporderpay.result.wechat.WeChatpaymentResult;
import cn.com.ddhj.service.apporderpay.wechat.WechatProcessRequest;
import cn.com.ddhj.service.impl.orderpay.OrderPayProcess;
import cn.com.ddhj.service.impl.orderpay.prepare.AlipayPreparePayProcess;
import cn.com.ddhj.service.impl.orderpay.prepare.ApplePayPreparePayProcess;
import cn.com.ddhj.service.impl.orderpay.prepare.WechatPreparePayProcess;
import cn.com.ddhj.util.Constant;
import cn.com.ddhj.util.DateUtil;

@Service
public class AppOrderPayServiceImpl implements IAppOrderPay {
	@Autowired
	private TOrderMapper mapper;
	@Autowired
	private TOrderRechargeMapper rechargeMapper;
	@Autowired
	private TPayInfoMapper payInfoMapper;
	@Autowired
	private TUserMapper userMapper;
	@Autowired
	private TUserLoginMapper loginMapper;
	
	@Override
	public BaseResult insertSelective(TPayInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult updateByCode(TPayInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult deleteByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TPayInfo selectByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TPayInfo selectByEntity(TPayInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult findEntityToPage(TPayInfoDto dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unused")
	public BaseResult doPay(TPayInfoDto dto, String userToken) {
		ApiPaymentAllResult apiPaymentAllResult = new ApiPaymentAllResult();
		
		UserDataResult userResult = new UserDataResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login == null) {
			userResult.setResultCode(Constant.RESULT_ERROR);
			userResult.setResultMessage("用户未登录!");
			return userResult;
		}
		
		TUser user = userMapper.findTUserByUuid(login.getUserToken());
		if(user == null) {
			userResult.setResultCode(Constant.RESULT_ERROR);
			userResult.setResultMessage("用户不存在!");
			return userResult;
		}

		// 查询支付单号详情表, 获取支付状态
		if(StringUtils.isBlank(dto.getOrderCodes()))  {
			apiPaymentAllResult.setResultCode(Constant.RESULT_ERROR);
			apiPaymentAllResult.setResultMessage("支付订单编号为空!");
			return apiPaymentAllResult;
		}
		
		Object order = null;
		Integer orderStatus = null;
		String orderCode = "", payType = "";
		BigDecimal payPrice = BigDecimal.ZERO;
		if(dto.getOrderCodes().startsWith("D")) {
			//楼盘报告订单状态 订单状态 0 未支付 1 已支付未下载 2 已支付已下载 3 已取消 4订单作废
			order = mapper.selectByCode(dto.getOrderCodes());
			TOrder to = (TOrder) order; 
			orderStatus = to.getStatus();
			orderCode = to.getCode();
			payPrice = to.getPayPrice();
			//楼盘报告支付订单
			payType = "DC170811100001001";
		} else if(dto.getOrderCodes().startsWith("RO")) {
			//碳币充值订单状态 0 未支付 1 已支付 2 已取消 3.已充值
			order = rechargeMapper.selectByOrderCode(dto.getOrderCodes());
			orderStatus = ((TOrderRecharge ) order).getStatus();
			TOrderRecharge to = (TOrderRecharge) order;
			orderCode = to.getCode();
			payPrice = to.getPayPrice();
			//碳币充值支付订单
			payType = "DC170811100001002";
		} else {
			apiPaymentAllResult.setResultCode(Constant.RESULT_ERROR);
			apiPaymentAllResult.setResultMessage("支付订单编号格式非法!");
			return apiPaymentAllResult;
		}
		
		if(order == null) {
			apiPaymentAllResult.setResultCode(Constant.RESULT_ERROR);
			apiPaymentAllResult.setResultMessage("查询不到指定订单,无法支付!");
			return apiPaymentAllResult;
		}
				
		if(orderStatus == null || orderStatus != 0) {
			apiPaymentAllResult.setResultCode(Constant.RESULT_ERROR);
			apiPaymentAllResult.setResultMessage("订单状态不是未支付,无法支付!");
			return apiPaymentAllResult;
		}
				
		
		// 获取锁定唯一约束值,开始锁定交易的流水号和交易类型5秒
		String sLockUuid = WebHelper.getInstance().addLock(5, user.getUserCode()+"_PAY");
		if (StringUtils.isBlank(sLockUuid)) {
			apiPaymentAllResult.setResultCode(Constant.RESULT_ERROR);
			apiPaymentAllResult.setResultMessage("支付锁定失败,可能是10秒内多次支付,请稍候重试!");
			return apiPaymentAllResult;
		}
		
		// 创建支付单号信息
		TPayInfo payInfo = new TPayInfo();
		String payCode = WebHelper.getInstance().getUniqueCode("PP");
		payInfo.setUuid(UUID.randomUUID().toString().replace("-", ""));
		payInfo.setPayCode(payCode);
		payInfo.setTradeNo(orderCode);
		payInfo.setCreateUser(user.getUserCode());
		payInfo.setCreateTime(DateUtil.getSysDateTime());
		payInfo.setPayMoney(payPrice);
		payInfo.setPayType(payType);
		payInfo.setSellerCode(Constant.SELLER_CODE);
		//支付状态:   0 待支付    1 支付中  2 已支付
		payInfo.setStatus(0);
		payInfoMapper.insertSelective(payInfo);
		
		if("449746280003".equals(dto.getType())){  
			//支付宝支付
			AlipayPreparePayProcess.PaymentResult result = new OrderPayProcess().aliPayAppPrepare(dto.getOrderCodes());
			AlipayPaymentResult alipayPaymentResult = new AlipayPaymentResult();
			if(result.upFlagTrue()){
				alipayPaymentResult.setAlipaySign(result.payInfo);
				alipayPaymentResult.setAlipayUrl(PropHelper.getValue("ali_url_http"));	
				apiPaymentAllResult.setAlipayPaymentResult(alipayPaymentResult);
			}else{
				apiPaymentAllResult.setResultCode(result.getResultCode());
				apiPaymentAllResult.setResultMessage(result.getResultMessage());
			}
		}else if("449746280005".equals(dto.getType())){   
			//微信支付(惠家有)
			WechatPreparePayProcess.PaymentResult result = new OrderPayProcess().wechatPrepare(dto.getOrderCodes());
			WeChatpaymentResult paymentResult = new WeChatpaymentResult();
			if(result.upFlagTrue()){
				paymentResult.setAppid(result.appid);	
				paymentResult.setMch_id(result.partnerid);
				paymentResult.setNonce_str(result.noncestr);
				paymentResult.setPrepay_id(result.prepayid);
				paymentResult.setSign(result.sign);
				paymentResult.setTrade_type("APP");
				paymentResult.setTimestamp(result.timestamp);
				apiPaymentAllResult.setWeChatpaymentResult(paymentResult);
			}else{
				apiPaymentAllResult.setResultCode(result.getResultCode());
				apiPaymentAllResult.setResultMessage(result.getResultMessage());
			}
		} else if("449746280013".equals(dto.getType())){
			/*
			ApplePayResponse applePayResponse = PayServiceFactory.getInstance().getApplePayProcess().process(inputParam.getOrder_code());
			
			apiPaymentAllResult.setApplePayResult(applePayResponse);
			*/
			ApplePayPreparePayProcess.PaymentResult result = new OrderPayProcess().applePayPrepare(dto.getOrderCodes());
			ApplePayResponse paymentResult = new ApplePayResponse();
			if(result.upFlagTrue()){
				paymentResult.setAp_merchant_id(result.ap_merchant_id);
				paymentResult.setBusi_partner(result.busi_partner);
				paymentResult.setDt_order(result.dt_order);
				paymentResult.setMoney_order(result.money_order);
				paymentResult.setNo_order(result.no_order);
				paymentResult.setNotify_url(result.notify_url);
				paymentResult.setOid_partner(result.oid_partner);
				paymentResult.setRisk_item(result.risk_item);
				paymentResult.setSign(result.sign);
				paymentResult.setSign_type(result.sign_type);
				paymentResult.setUser_id(result.user_id);
				paymentResult.setValid_order(result.valid_order);
				apiPaymentAllResult.setApplePayResult(paymentResult);
			}else{
				apiPaymentAllResult.setResultCode(result.getResultCode());
				apiPaymentAllResult.setResultMessage(result.getResultMessage());
			}
		}
		WebHelper.getInstance().unLock(sLockUuid);
		return apiPaymentAllResult;
	}

	@Override
	@Deprecated
	public BaseResult doPay(TPayInfoDto dto, String userToken, String remoteIp) {
		ApiPayInfoResult apiPayInfoResult = new ApiPayInfoResult();
		AlipayPaymentResult alipayPaymentResult = new AlipayPaymentResult();
		WCPaymentResult wechatPaymentResult = new WCPaymentResult();
		
		UserDataResult result = new UserDataResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login == null) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户未登录");
			return result;
		}
		
		TUser user = userMapper.findTUserByUuid(login.getUserToken());
		if(user == null) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户不存在");
			return result;
		}

		// 查询支付单号详情表, 获取支付状态
		TOrder order = mapper.selectByCode(dto.getOrderCodes());
		//status 订单状态 0 未支付 1 已支付未下载 2 已支付已下载 3 已取消 4订单作废
		if(order == null) {
			apiPayInfoResult.setResultCode(Constant.RESULT_ERROR);
			apiPayInfoResult.setResultMessage("查询不到指定订单,无法支付!");
			return apiPayInfoResult;
		}
				
		if(order.getStatus() != 0) {
			apiPayInfoResult.setResultCode(Constant.RESULT_ERROR);
			apiPayInfoResult.setResultMessage("订单状态不是未支付,无法支付!");
			return apiPayInfoResult;
		}
				
		
		// 获取锁定唯一约束值
		String sLockUuid = WebHelper.getInstance().addLock(5, user.getUserCode()+"_PAY");

		// 创建支付单号信息
		// 开始锁定交易的流水号和交易类型5秒
		TPayInfo payInfo = new TPayInfo();
		if (StringUtils.isBlank(sLockUuid)) {
			apiPayInfoResult.setResultCode(Constant.RESULT_ERROR);
			apiPayInfoResult.setResultMessage("支付锁定失败,可能是10秒内多次支付,请稍候重试!");
			return apiPayInfoResult;
		}
		
		// 自动生成支付单号
		String payCode = WebHelper.getInstance().getUniqueCode("PP");
		payInfo.setUuid(UUID.randomUUID().toString().replace("-", ""));
		payInfo.setPayCode(payCode);
		payInfo.setTradeNo(order.getCode());
		payInfo.setCreateUser(user.getUserCode());
		payInfo.setCreateTime(DateUtil.getSysDateTime());
		payInfo.setPayMoney(order.getPayPrice());
		payInfo.setPayType("DC170811100001001");
		payInfo.setSellerCode(Constant.SELLER_CODE);
		//支付状态:   0 待支付    1 支付中  2 已支付
		payInfo.setStatus(0);
		payInfo.setTradeNo("");
		payInfoMapper.insertSelective(payInfo);
				
		if ("449746280003".equals(dto.getType())) { // 支付宝支付
			// 获取支付宝移动支付参数
			ApiAlipayMoveProcessService alipayMoveProcessService = new ApiAlipayMoveProcessService();
			String alipaySign = alipayMoveProcessService.alipayMoveParameterNew(payCode, payInfo);
			alipayPaymentResult.setAlipaySign(alipaySign);
			alipayPaymentResult.setAlipayUrl(PropHelper.getValue("ali_url_http"));
			apiPayInfoResult.setAlipayPayment(alipayPaymentResult);

			if (!"".equals(alipaySign) && alipaySign != null) {
				// 去支付时更新状态 为支付中 state： 0 待支付 1 支付中 2 已支付
				TPayInfo info = new TPayInfo();
				info.setPayCode(payCode);
				info.setStatus(1);
				payInfoMapper.updateByCode(info);
			}

		} else if ("449746280005".equals(dto.getType())) { // 微信支付
			BaseResult rootResult = new BaseResult();
			// 获取微信支付信息
			WechatProcessRequest wechatProcessRequest = new WechatProcessRequest();
			MDataMap mDataMap = wechatProcessRequest.wechatMoveNew(payCode, payInfo, remoteIp, rootResult);
			
			if (mDataMap != null) {
				wechatPaymentResult.setAppid(mDataMap.get("appid"));
				wechatPaymentResult.setNonceStr(mDataMap.get("noncestr"));
				wechatPaymentResult.setPackageValue(mDataMap.get("package"));
				wechatPaymentResult.setPartnerid(mDataMap.get("partnerid"));
				wechatPaymentResult.setPrepayid(mDataMap.get("prepayid"));
				wechatPaymentResult.setSign(mDataMap.get("sign"));
				wechatPaymentResult.setTimeStamp(mDataMap.get("timestamp"));

				// 去支付时更新状态 为支付中 state： 0 待支付 1 支付中 2 已支付
				TPayInfo info = new TPayInfo();
				info.setPayCode(payCode);
				info.setStatus(1);
				payInfoMapper.updateByCode(info);
			} else {
				apiPayInfoResult.setResultCode(rootResult.getResultCode());
				apiPayInfoResult.setResultMessage(rootResult.getResultMessage());
			}
			apiPayInfoResult.setWechatResult(wechatPaymentResult);

		}
		WebHelper.getInstance().unLock(sLockUuid);
		return apiPayInfoResult;
	}
}
