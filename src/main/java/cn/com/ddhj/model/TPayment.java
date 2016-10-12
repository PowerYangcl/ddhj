package cn.com.ddhj.model;

import java.math.BigDecimal;

public class TPayment extends BaseModel {
	private String orderCode;
	private String mid;  // 支付网关分配的商户编号
	private BigDecimal amount;   // 订单总金额
	private String ymd;  // 订单产生时间
	private String moneyType; // 支付币种
	private String dealtime;  // 支付时间
	private String succmark;  // 交易成功标识
	private String cause;  // 失败原因
	private String memo1;  // 商户参数一
	private String memo2;  // 商户参数二
	private String signstr;  // 签名
	private String paygate;  // 支付网关

	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getYmd() {
		return ymd;
	}
	public void setYmd(String ymd) {
		this.ymd = ymd;
	}
	public String getMoneyType() {
		return moneyType;
	}
	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}
	public String getDealtime() {
		return dealtime;
	}
	public void setDealtime(String dealtime) {
		this.dealtime = dealtime;
	}
	public String getSuccmark() {
		return succmark;
	}
	public void setSuccmark(String succmark) {
		this.succmark = succmark;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getMemo1() {
		return memo1;
	}
	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}
	public String getMemo2() {
		return memo2;
	}
	public void setMemo2(String memo2) {
		this.memo2 = memo2;
	}
	public String getSignstr() {
		return signstr;
	}
	public void setSignstr(String signstr) {
		this.signstr = signstr;
	}
	public String getPaygate() {
		return paygate;
	}
	public void setPaygate(String paygate) {
		this.paygate = paygate;
	}
}
