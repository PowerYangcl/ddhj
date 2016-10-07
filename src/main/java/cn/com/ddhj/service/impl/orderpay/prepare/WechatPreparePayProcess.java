package cn.com.ddhj.service.impl.orderpay.prepare;


import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.srnpr.xmaspay.PaymentChannel;
import com.srnpr.xmaspay.config.PayGateConfig;

/**
 * 微信支付
 * @author zhaojunling
 */
public class WechatPreparePayProcess extends PayGatePreparePayProcess<WechatPreparePayProcess.PaymentInput, WechatPreparePayProcess.PaymentResult>{

	@Override
	protected Map<String, String> createPayGateParam(PaymentInput input) {
		Map<String, String> map = new HashMap<String, String>();
		if(PaymentChannel.APP == input.payChannel){
			map.put("c_paygate", PayGateConfig.getPayGateVal(PayGateConfig.Type.WECHAT_APP)+"");
		}else if(PaymentChannel.JSAPI == input.payChannel){
			map.put("c_paygate", PayGateConfig.getPayGateVal(PayGateConfig.Type.WECHAT_JSAPI)+"");
			map.put("c_openid", input.openid);
		}else if(PaymentChannel.WAP == input.payChannel){
			map.put("c_paygate", PayGateConfig.getPayGateVal(PayGateConfig.Type.WECHAT_WAP)+"");
		}else if(PaymentChannel.BARCODE == input.payChannel){
			map.put("c_paygate", PayGateConfig.getPayGateVal(PayGateConfig.Type.WECHAT_BARCODE)+"");
		}
		
		return map;
	}

	//{"resultcode":0,"resultmsg":"","APPParam":"_input_charset=\"utf-8\"&body=\"购买商品\"&notify_url=\"http:\/\/test.pay.caiyb.cn\/Notify\/alipay\/653\/1000447\"&out_trade_no=\"ALIPA00001081\"&payment_type=\"1\"&seller_id=\"ucar_app@ichsy.com\"&service=\"mobile.securitypay.pay\"&sign=\"g1SxmCOWhZoC7VWUTMfKIyou2K%2fBte87GAtbMbwVlK%2bjG931EXvxCIp%2bJyR8zQdPBR9dVQh77TFZZAW23kM9CfzzSK%2fhuLyRylrhiR4C3NIQwPfAglocJbXuUTbvhXG6BpI02HnlvRF9waibibvU8%2bE47lpC3Xo9MQ%2bqHRKAtCU%3d\"&sign_type=\"RSA\"&subject=\"购买商品\"&total_fee=\"0.01\""}
	@Override
	protected void prepareResult(PaymentChannel channel, PaymentResult result, String responseText) {
		JSONObject resp = null;
		try {
			resp = JSON.parseObject(responseText);
			
			if(!"0".equals(resp.getString("resultcode"))){
				result.setResultCode(0);
				result.setResultMessage(resp.getString("resultmsg"));
				return;
			}
			
			if(channel == PaymentChannel.JSAPI){
				result.jsapiparam = resp.getString("jsapiparam");
			}else{
				JSONObject appparam = resp.getJSONObject("appparam");
				if(appparam != null){
					result.appid = appparam.getString("appid");
					result.noncestr = appparam.getString("noncestr");
					result.packAge = appparam.getString("package");
					result.partnerid = appparam.getString("partnerid");
					result.prepayid = appparam.getString("prepayid");
					result.sign = appparam.getString("sign");
					result.timestamp = appparam.getString("timestamp");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(0);
			result.setResultMessage("解析网关结果异常");
		}
	}

	/**
	 * 获取支付请求输入对象
	 */
	public static class PaymentInput extends PayGatePreparePayProcess.PaymentInput {}
	
	/**
	 * 获取支付参数的输出对象
	 */
	public static class PaymentResult extends PayGatePreparePayProcess.PaymentResult {
		// JSAPI
		public String jsapiparam;
		
		// APP支付
		public String appid;
		public String noncestr;
		public String packAge;
		public String partnerid;
		public String prepayid;
		public String sign;
		public String timestamp;
	}
	
	@Override
	public PaymentResult getResult() {
		return new PaymentResult();
	}
}
