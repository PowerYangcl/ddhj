package cn.com.ddhj.result.trade;

import java.math.BigDecimal;

import cn.com.ddhj.base.BaseResult;

public class TradePriceAvaiAmountResult extends BaseResult {
	private String objectCode;
	private BigDecimal currentPrice;
	private Integer buyAmount;
	private Integer sellAmount;
	private String userCode;
	public String getObjectCode() {
		return objectCode;
	}
	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}
	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}
	public Integer getBuyAmount() {
		return buyAmount;
	}
	public void setBuyAmount(Integer buyAmount) {
		this.buyAmount = buyAmount;
	}
	public Integer getSellAmount() {
		return sellAmount;
	}
	public void setSellAmount(Integer sellAmount) {
		this.sellAmount = sellAmount;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
