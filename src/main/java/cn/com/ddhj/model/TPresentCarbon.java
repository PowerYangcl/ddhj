package cn.com.ddhj.model;

import java.math.BigDecimal;


public class TPresentCarbon extends BaseModel {
	private String userCode;
	private Double carbonMoney;
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Double getCarbonMoney() {
		return carbonMoney;
	}
	public void setCarbonMoney(Double carbonMoney) {
		this.carbonMoney = carbonMoney;
	}
}
