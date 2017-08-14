package cn.com.ddhj.dto.apppay;

import cn.com.ddhj.dto.BaseDto;

public class TPayInfoDto extends BaseDto  {
	private String type;
	private String orderCodes;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrderCodes() {
		return orderCodes;
	}
	public void setOrderCodes(String orderCodes) {
		this.orderCodes = orderCodes;
	}
}
