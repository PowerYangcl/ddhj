package cn.com.ddhj.model;

import java.math.BigDecimal;


public class TPresentCarbon extends BaseModel {
	private String userCode;
	private BigDecimal carbonMoney;
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public BigDecimal getCarbonMoney() {
		return carbonMoney;
	}
	public void setCarbonMoney(BigDecimal carbonMoney) {
		this.carbonMoney = carbonMoney;
	}
}
