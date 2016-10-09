package cn.com.ddhj.result.order;

import cn.com.ddhj.base.BaseResult;

public class OrderPayResult extends BaseResult {
	private String errorMsg;
	private String redirectUrl;
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
}
