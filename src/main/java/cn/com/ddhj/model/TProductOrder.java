package cn.com.ddhj.model;

public class TProductOrder extends BaseModel{
    private String code;
    private Integer payMoney;
    private String buyerCode;
    private String buyerPhone;
    private String dispatching;
    private String addressCode;
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Integer payMoney) {
		this.payMoney = payMoney;
	}
	public String getBuyerCode() {
		return buyerCode;
	}
	public void setBuyerCode(String buyerCode) {
		this.buyerCode = buyerCode;
	}
	public String getBuyerPhone() {
		return buyerPhone;
	}
	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}
	public String getDispatching() {
		return dispatching;
	}
	public void setDispatching(String dispatching) {
		this.dispatching = dispatching;
	}
	public String getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}
}