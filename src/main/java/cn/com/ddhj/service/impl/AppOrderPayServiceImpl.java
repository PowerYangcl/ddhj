package cn.com.ddhj.service.impl;

import java.util.List;
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
import cn.com.ddhj.mapper.apppay.TPayInfoMapper;
import cn.com.ddhj.mapper.user.TUserLoginMapper;
import cn.com.ddhj.mapper.user.TUserMapper;
import cn.com.ddhj.model.TOrder;
import cn.com.ddhj.model.apppay.TPayInfo;
import cn.com.ddhj.model.map.MDataMap;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.model.user.TUserCarbonOperation;
import cn.com.ddhj.model.user.TUserLogin;
import cn.com.ddhj.result.tuser.UserDataResult;
import cn.com.ddhj.service.IAppOrderPay;
import cn.com.ddhj.service.apporderpay.alipay.ApiAlipayMoveProcessService;
import cn.com.ddhj.service.apporderpay.result.AlipayPaymentResult;
import cn.com.ddhj.service.apporderpay.result.ApiPayInfoResult;
import cn.com.ddhj.service.apporderpay.result.WechatPaymentResult;
import cn.com.ddhj.service.apporderpay.wechat.WechatProcessRequest;
import cn.com.ddhj.util.Constant;
import cn.com.ddhj.util.DateUtil;

@Service
public class AppOrderPayServiceImpl implements IAppOrderPay {
	@Autowired
	private TOrderMapper mapper;
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

	@Override
	public BaseResult doPay(TPayInfoDto dto, String userToken, String remoteIp) {
		ApiPayInfoResult apiPayInfoResult = new ApiPayInfoResult();
		AlipayPaymentResult alipayPaymentResult = new AlipayPaymentResult();
		WechatPaymentResult wechatPaymentResult = new WechatPaymentResult();
		
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
