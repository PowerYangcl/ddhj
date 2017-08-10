package cn.com.ddhj.service.apporderpay.result;

public class ApiPayInfoResult {
	private AlipayPaymentResult  alipayPayment = new AlipayPaymentResult();
	
	private WechatPaymentResult wechatResult = new WechatPaymentResult();

	public AlipayPaymentResult getAlipayPayment() {
		return alipayPayment;
	}

	public void setAlipayPayment(AlipayPaymentResult alipayPayment) {
		this.alipayPayment = alipayPayment;
	}

	public WechatPaymentResult getWechatResult() {
		return wechatResult;
	}

	public void setWechatResult(WechatPaymentResult wechatResult) {
		this.wechatResult = wechatResult;
	}
	
	
	
}
