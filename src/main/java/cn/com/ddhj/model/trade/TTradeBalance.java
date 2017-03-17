package cn.com.ddhj.model.trade;

import java.math.BigDecimal;

import cn.com.ddhj.model.BaseModel;

public class TTradeBalance extends BaseModel {

	//持仓标的编码
    private String objectCode;

    //用户编码
    private String userCode;

    //持仓标的的均价
    private BigDecimal price;

    //B:多头 S:空头
    private String buySell;

    //持仓数量
    private Integer amount;
    
    //持仓标的的最新价
    private BigDecimal lastPrice;
    
    //持仓标的的盈亏
    private BigDecimal profitLoss;

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode == null ? null : objectCode.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBuySell() {
        return buySell;
    }

    public void setBuySell(String buySell) {
        this.buySell = buySell == null ? null : buySell.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

	public BigDecimal getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(BigDecimal lastPrice) {
		this.lastPrice = lastPrice;
	}

	public BigDecimal getProfitLoss() {
		return profitLoss;
	}

	public void setProfitLoss(BigDecimal profitLoss) {
		this.profitLoss = profitLoss;
	}
    
}