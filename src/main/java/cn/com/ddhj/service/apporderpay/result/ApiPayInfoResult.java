package cn.com.ddhj.service.apporderpay.result;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.service.apporderpay.result.ali.AlipayPaymentResult;
import cn.com.ddhj.service.apporderpay.result.wechat.WCPaymentResult;

public class ApiPayInfoResult extends BaseResult {
	private AlipayPaymentResult  alipayPayment = new AlipayPaymentResult();
	
	private WCPaymentResult wechatResult = new WCPaymentResult();

	public AlipayPaymentResult getAlipayPayment() {
		return alipayPayment;
	}

	public void setAlipayPayment(AlipayPaymentResult alipayPayment) {
		this.alipayPayment = alipayPayment;
	}

	public WCPaymentResult getWechatResult() {
		return wechatResult;
	}

	public void setWechatResult(WCPaymentResult wechatResult) {
		this.wechatResult = wechatResult;
	}
}
