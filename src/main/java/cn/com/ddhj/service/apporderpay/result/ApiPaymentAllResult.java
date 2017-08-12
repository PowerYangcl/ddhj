package cn.com.ddhj.service.apporderpay.result;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.service.apporderpay.result.ali.AlipayPaymentResult;
import cn.com.ddhj.service.apporderpay.result.wechat.WeChatpaymentResult;

public class ApiPaymentAllResult extends BaseResult {
	private AlipayPaymentResult alipayPaymentResult = new AlipayPaymentResult();
	
	private WeChatpaymentResult weChatpaymentResult = new WeChatpaymentResult();
	
	private ApplePayResponse applePayResult = new ApplePayResponse();
	
	private String fnCode ="";
	
	private String jsapiparam;
	
	public AlipayPaymentResult getAlipayPaymentResult() {
		return alipayPaymentResult;
	}
	public void setAlipayPaymentResult(AlipayPaymentResult alipayPaymentResult) {
		this.alipayPaymentResult = alipayPaymentResult;
	}
	public WeChatpaymentResult getWeChatpaymentResult() {
		return weChatpaymentResult;
	}
	public void setWeChatpaymentResult(WeChatpaymentResult weChatpaymentResult) {
		this.weChatpaymentResult = weChatpaymentResult;
	}
	public ApplePayResponse getApplePayResult() {
		return applePayResult;
	}
	public void setApplePayResult(ApplePayResponse applePayResult) {
		this.applePayResult = applePayResult;
	}
	public String getFnCode() {
		return fnCode;
	}
	public void setFnCode(String fnCode) {
		this.fnCode = fnCode;
	}
	public String getJsapiparam() {
		return jsapiparam;
	}
	public void setJsapiparam(String jsapiparam) {
		this.jsapiparam = jsapiparam;
	}
	
	
}
