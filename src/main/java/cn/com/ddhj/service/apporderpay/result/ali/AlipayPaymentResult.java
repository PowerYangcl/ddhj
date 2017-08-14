package cn.com.ddhj.service.apporderpay.result.ali;

public class AlipayPaymentResult {


	private String alipayUrl = "";

	private String alipaySign = "";
	
	
	public String getAlipayUrl() {
		return alipayUrl;
	}
	public void setAlipayUrl(String alipayUrl) {
		this.alipayUrl = alipayUrl;
	}
	public String getAlipaySign() {
		return alipaySign;
	}
	public void setAlipaySign(String alipaySign) {
		this.alipaySign = alipaySign;
	}
}
