package cn.com.ddhj.service.impl.orderpay;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.srnpr.xmaspay.common.Constants;

import cn.com.ddhj.service.impl.orderpay.prepare.AlipayPreparePayProcess;
import cn.com.ddhj.service.impl.orderpay.prepare.ApplePayPreparePayProcess;
import cn.com.ddhj.service.impl.orderpay.prepare.WechatPreparePayProcess;

public class OrderPayProcess {

	/**
	 * APP支付宝付款（网关渠道）
	 * @param channel 支付渠道
	 * @param bigOrderCode 待支付大订单号
	 * @return
	 */
	public AlipayPreparePayProcess.PaymentResult aliPayAppPrepare(String bigOrderCode){
		return PayServiceSupport.aliPayPrepare(PaymentChannel.APP, bigOrderCode);
	}
	
	/**
	 * APP支付宝H5付款（网关渠道）
	 * @param channel 支付渠道
	 * @param bigOrderCode 待支付大订单号
	 * @return
	 */
	public AlipayPreparePayProcess.PaymentResult aliPayH5Prepare(String bigOrderCode, String returnUrl){
		AlipayPreparePayProcess.PaymentInput input = new AlipayPreparePayProcess.PaymentInput();
		input.bigOrderCode = bigOrderCode;
		input.payChannel = PaymentChannel.WAP;
		input.reurl = returnUrl;
		return PayServiceFactory.getInstance().getAlipayPreparePayProcess().process(input);
	}
	
	/**
	 * 微信付款（网关渠道）
	 * @param channel 支付渠道
	 * @param bigOrderCode 待支付大订单号
	 * @return
	 */
	public WechatPreparePayProcess.PaymentResult wechatPrepare(String bigOrderCode){
		return PayServiceSupport.wechatPrepare(PaymentChannel.APP, bigOrderCode);
	}
	
	/**
	 * 微信JSAPI付款（网关渠道）
	 * @param channel 支付渠道
	 * @param bigOrderCode 待支付大订单号
	 * @return
	 */
	public WechatPreparePayProcess.PaymentResult wechatJSAPIPrepare(String bigOrderCode, String openID, String returnUrl){
		WechatPreparePayProcess.PaymentInput input = new WechatPreparePayProcess.PaymentInput();
		input.bigOrderCode = bigOrderCode;
		input.openid = openID;
		input.payChannel = PaymentChannel.JSAPI;
		input.reurl = returnUrl;
		return PayServiceFactory.getInstance().getWechatPreparePayProcess().process(input);
	}
	
	/**
	 * 苹果支付
	 * @param bigOrderCode 待支付大订单号
	 * @return
	 */
	public ApplePayPreparePayProcess.PaymentResult applePayPrepare(String bigOrderCode){
		return PayServiceSupport.applePayPrepare(bigOrderCode);
	}
	
	/**
	 * 网关支付通知
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String handlePayGateNotify(){
		HttpServletRequest req = WebSessionHelper.create().upHttpRequest();
		Enumeration<String> names = req.getParameterNames();
		Map<String,String> notifyParam = new HashMap<String, String>();
		String name;
		while(names.hasMoreElements()){
			name = names.nextElement();
			notifyParam.put(name, StringUtils.trimToEmpty(req.getParameter(name)));
		}
		
		PayGateNotifyPayProcess.PaymentResult result = PayServiceSupport.payGateNotify(notifyParam);
		
		if(result.getResultCode() == PaymentResult.SUCCESS){
			updateOrderStatusPayed(result.notify.bigOrderCode);
		}
		
		StringBuilder build = new StringBuilder();
		build.append("<result>1</result><reURL>"+result.reURL+"</reURL>");
		if(result.getResultCode() != PaymentResult.SUCCESS){
			build.append("<msg>").append(result.getResultMessage()).append("</msg>");
		}
		return build.toString();
	}
	
	/**
	 * 苹果支付结果通知
	 * @param notifyParam 通知参数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String handleApplePayNotify(){
		Map<String,String> param = new HashMap<String, String>();
		ServletInputStream in = null;
		try {
			in = WebSessionHelper.create().upHttpRequest().getInputStream();
			String jsonText = IOUtils.toString(in, "UTF-8");
			param = JSONObject.parseObject(jsonText, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(in);
		}
		
		ApplePayNotifyPayProcess.PaymentResult result = PayServiceSupport.applePayNotify(param);
		if(result.getResultCode() == PaymentResult.SUCCESS){
			updateOrderStatusPayed(result.notify.bigOrderCode);
		}
		
		return "{\"ret_code\": \"0000\",\"ret_msg\": \"success\"}";
	}
	
	/**
	 * 更改订单为已支付状态
	 * @param bigOrderCode
	 */
	private void updateOrderStatusPayed(String bigOrderCode){
		List<MDataMap> orderInfoList = DbUp.upTable("oc_orderinfo").queryAll("zid,uid,order_code,order_status,buyer_code,seller_code,small_seller_code", "", "", new MDataMap("big_order_code",bigOrderCode));
		String memberCode = null;
		String sellerCode = null;
		String smallSellerCode = null;
		for(MDataMap map : orderInfoList) {
			if(FamilyConfig.ORDER_STATUS_UNPAY.equals(map.get("order_status"))){
				FlowBussinessService fs = new FlowBussinessService();
				String flowBussinessUid = map.get("uid");
				String fromStatus = map.get("order_status");
				String toStatus = FamilyConfig.ORDER_STATUS_PAYED;//下单成功-未发货
				String flowType = "449715390008";
				String userCode = "system";
				String remark = "auto by system";
				MDataMap md = new MDataMap();
				md.put("order_code", map.get("order_code"));
				fs.ChangeFlow(flowBussinessUid, flowType, fromStatus, toStatus, userCode, remark, md);
				
				memberCode = map.get("buyer_code");
				sellerCode = map.get("seller_code");
				smallSellerCode = map.get("small_seller_code");
				if(FamilyConfig.hjy.equals(smallSellerCode) || FamilyConfig.jyh.equals(smallSellerCode)){
					JobExecHelper.createExecInfo(Constants.ZA_EXEC_TYPE_SYNC_PAYINFO_LD, map.get("order_code"), "");
				}
				
				if("SF03KJT".equals(smallSellerCode)){
//					JobExecHelper.createExecInfo(Constants.ZA_EXEC_TYPE_SYNC_KJT, map.get("order_code"), "");
					JobExecHelper.createExecInfoForWebcore(Constants.ZA_EXEC_TYPE_SYNC_KJT, map.get("order_code") , "" , "OrderPayProcess line 122");
				}
			}
		}
		
		if(memberCode != null && sellerCode != null){
			String mobileNO = new MemberLoginSupport().getMoblie(memberCode);
			if(StringUtils.isNotEmpty(mobileNO)) {
				JmsNoticeSupport.INSTANCE.sendQueue(
						JmsNameEnumer.OnDistributeCoupon,
						CouponConst.pay_coupon, new MDataMap(
								"mobile", mobileNO, "manage_code", sellerCode, 
								"big_order_code", bigOrderCode, "member_code", memberCode));
				
			}
		}
	}
}
