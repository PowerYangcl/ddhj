package cn.com.ddhj.service.apporderpay.result;

public class ApiPayInfoInput {

	private String[] orderCodes = new String[]{};

	private String type = "";

	private String ip = "";
	
	public String[] getOrderCodes() {
		return orderCodes;
	}
	public void setOrderCodes(String[] orderCodes) {
		this.orderCodes = orderCodes;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
}
