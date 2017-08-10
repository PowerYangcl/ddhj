package cn.com.ddhj.service.apporderpay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srnpr.zapdata.dbdo.DbUp;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.helper.PropHelper;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TOrderMapper;
import cn.com.ddhj.model.TOrder;
import cn.com.ddhj.model.map.MDataMap;
import cn.com.ddhj.service.apporderpay.result.AlipayPaymentResult;
import cn.com.ddhj.service.apporderpay.result.ApiPayInfoInput;
import cn.com.ddhj.service.apporderpay.result.ApiPayInfoResult;
import cn.com.ddhj.service.apporderpay.result.WechatPaymentResult;
import cn.com.ddhj.util.Constant;

/**
 * 支付
 * 
 * @author wz
 * 
 */
@Service
public class ApiPayInfo {
	@Autowired
	private TOrderMapper mapper;
	
	public ApiPayInfoResult Process(ApiPayInfoInput inputParam, MDataMap mRequestMap) {

		ApiPayInfoResult apiPayInfoResult = new ApiPayInfoResult();
		AlipayPaymentResult alipayPaymentResult = new AlipayPaymentResult();
		WechatPaymentResult wechatPaymentResult = new WechatPaymentResult();
		
		CreateOrderPay createOrderPay = new CreateOrderPay();
		
		String orderCodeNewBig = "";
		String alipaySign = "";
		String sellerCode = "SI2003";

		String statePay = "";
		String orderCodeAll = "";
		
		
		// 拼接订单号
		for (String orderCode : inputParam.getOrderCodes()) {
			orderCodeAll = orderCodeAll + "'" + orderCode + "',";
		}
		if (!"".equals(orderCodeAll)) {
			orderCodeAll = orderCodeAll.substring(0, orderCodeAll.length() - 1);
		}
		// 查询支付单号详情表, 获取支付状态
		TOrder order = mapper.selectByCode(orderCodeAll);
		//status 订单状态 0 未支付 1 已支付未下载 2 已支付已下载 3 已取消 4订单作废
		if(order == null) {
			apiPayInfoResult.setResultCode(Constant.RESULT_ERROR);
			apiPayInfoResult.setResultMessage("查询不到指定订单,无法支付!");
			return apiPayInfoResult;
		}
		
		if(order.getStatus()!=0) {
			apiPayInfoResult.setResultCode(Constant.RESULT_ERROR);
			apiPayInfoResult.setResultMessage("订单状态不是未支付,无法支付!");
			return apiPayInfoResult;
		}
//		List<Map<String, Object>> payDetailList = DbUp.upTable("oc_paydetail").dataSqlList(
//						"select state from oc_paydetail where order_code in ("
//								+ orderCodeAll + ") "
//								+ "or big_order_code in (" + orderCodeAll + ")",
//						new MDataMap());
		
		//判断state属于那种支付状态, 如果不是待支付状态(0), 直接返回错误信息退出
//		for (Map payDetailMap : payDetailList) {
//			if ("1".equals(payDetailMap.get("state"))) {
//				apiPayInfoResult.setResultMessage("此订单正在支付中,无法进行支付!");
//				return apiPayInfoResult;
//			} else if ("2".equals(payDetailMap.get("state"))) {
//				apiPayInfoResult.setResultMessage("此订单已经支付过,无法再次支付!");
//				return apiPayInfoResult;
//			}
//		}
		
		
		/*
		 * 以下代码是待支付状态下运行的
		 */
		
//		// 将所有订单号拼接成DD150105100199,DD150105100190形式
//		String orderCodeNewSmall = "";
//		for (String orderCode : inputParam.getOrderCodes()) {
//			if ("DD".equals(orderCode.substring(0, 2))) {
//				orderCodeNewSmall = orderCodeNewSmall + "'" + orderCode + "',";
//			} else if ("OS".equals(orderCode.substring(0, 2))) {
//				orderCodeNewBig = orderCodeNewBig + "'" + orderCode + "',";
//			}
//
//		}
//		if (!"".equals(orderCodeNewSmall)) {
//			orderCodeNewSmall = orderCodeNewSmall.substring(0,orderCodeNewSmall.length() - 1);
//		}
//		if (!"".equals(orderCodeNewBig)) {
//			orderCodeNewBig = orderCodeNewBig.substring(0,orderCodeNewBig.length() - 1);
//		}
		// 自动生成支付单号
		String payCode = WebHelper.getInstance().getUniqueCode("PP");
		// 获取锁定唯一约束值
		String sLockUuid = WebHelper.getInstance().addLock(10, payCode);

		// 创建支付单号信息
		String orderPayCode = createOrderPay.createOrderPayInfo(payCode, sLockUuid, inputParam.getType(), orderCodeNewSmall,orderCodeNewBig, sellerCode);
		
		if (orderPayCode != null) {
			if ("449746280003".equals(inputParam.getType())) { // 支付宝支付
				// 获取支付宝移动支付参数
				ApiAlipayMoveProcessService alipayMoveProcessService = new ApiAlipayMoveProcessService();
				alipaySign = alipayMoveProcessService.alipayMoveParameterNew(orderPayCode);
				alipayPaymentResult.setAlipaySign(alipaySign);
				alipayPaymentResult.setAlipayUrl(PropHelper.getValue("ali_url_http"));

				apiPayInfoResult.setAlipayPayment(alipayPaymentResult);

				if (!"".equals(alipaySign) && alipaySign != null) {
					// 去支付时更新状态 为支付中 state： 0 待支付 1 支付中 2 已支付
					DbUp.upTable("oc_pay_info").dataUpdate(new MDataMap("pay_code", orderPayCode,"state", "1"), "state", "pay_code");

//					DbUp.upTable("oc_paydetail").dataUpdate(new MDataMap("pay_code", orderPayCode,"state", "1"), "state", "pay_code");

				}

			} else if ("449746280005".equals(inputParam.getType())) { // 微信支付
				BaseResult rootResult = new BaseResult();
				// 获取微信支付信息
				WechatProcessRequest wechatProcessRequest = new WechatProcessRequest();
				MDataMap mDataMap = wechatProcessRequest.wechatMoveNew(orderPayCode, inputParam.getIp(), rootResult);
				
				if (mDataMap != null) {
					wechatPaymentResult.setAppid(mDataMap.get("appid"));
					wechatPaymentResult.setNonceStr(mDataMap.get("noncestr"));
					wechatPaymentResult.setPackageValue(mDataMap.get("package"));
					wechatPaymentResult.setPartnerid(mDataMap.get("partnerid"));
					wechatPaymentResult.setPrepayid(mDataMap.get("prepayid"));
					wechatPaymentResult.setSign(mDataMap.get("sign"));
					wechatPaymentResult.setTimeStamp(mDataMap.get("timestamp"));

					// 去支付时更新状态 为支付中 state： 0 待支付 1 支付中 2 已支付
					DbUp.upTable("oc_pay_info").dataUpdate(new MDataMap("pay_code", orderPayCode,"state", "1"), "state", "pay_code");

					DbUp.upTable("oc_paydetail").dataUpdate(new MDataMap("pay_code", orderPayCode,"state", "1"), "state", "pay_code");
				} else {
					apiPayInfoResult.setResultCode(rootResult.getResultCode());
					apiPayInfoResult.setResultMessage(rootResult.getResultMessage());
				}
				apiPayInfoResult.setWechatResult(wechatPaymentResult);

			} else if ("".equals(inputParam.getType())) { // 银联支付
				
				
				// 去支付时更新状态 为支付中 state： 0 待支付 1 支付中 2 已支付
				DbUp.upTable("oc_pay_info").dataUpdate(new MDataMap("pay_code", orderPayCode, "state", "1"),"state", "pay_code");

				DbUp.upTable("oc_paydetail").dataUpdate(new MDataMap("pay_code", orderPayCode, "state", "1"),"state", "pay_code");
			}
			WebHelper.getInstance().unLock(sLockUuid);
		}

		return apiPayInfoResult;
	}

}
