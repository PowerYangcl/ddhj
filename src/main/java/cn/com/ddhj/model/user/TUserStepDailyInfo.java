package cn.com.ddhj.model.user;

import java.math.BigDecimal;

import cn.com.ddhj.model.BaseModel;

public class TUserStepDailyInfo  extends BaseModel {
	private BigDecimal carbonMoney;
	private String kilo;
	private String co2;
	private Integer step;
	private String createTime;
	public BigDecimal getCarbonMoney() {
		return carbonMoney;
	}
	public void setCarbonMoney(BigDecimal carbonMoney) {
		this.carbonMoney = carbonMoney;
	}
	public String getKilo() {
		return kilo;
	}
	public void setKilo(String kilo) {
		this.kilo = kilo;
	}
	public String getCo2() {
		return co2;
	}
	public void setCo2(String co2) {
		this.co2 = co2;
	}
	public Integer getStep() {
		return step;
	}
	public void setStep(Integer step) {
		this.step = step;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
