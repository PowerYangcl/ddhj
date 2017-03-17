package cn.com.ddhj.model;

import java.math.BigDecimal;

public class TOrderRecharge extends BaseModel {

    private String code;

    private String payCode;

    private BigDecimal payPrice;

    private String buyerCode;

    private BigDecimal carbonMoney;

    private Integer status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode == null ? null : payCode.trim();
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode == null ? null : buyerCode.trim();
    }

    public BigDecimal getCarbonMoney() {
        return carbonMoney;
    }

    public void setCarbonMoney(BigDecimal carbonMoney) {
        this.carbonMoney = carbonMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}