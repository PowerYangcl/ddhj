package cn.com.ddhj.model;

import java.util.List;

public class TProductOrder extends BaseModel {
	private String code;
	private Integer payMoney;
	private String buyerCode;
	private String buyerPhone;
	private Integer dispatching;
	private String addressCode;

	private List<TProductOrderDetail> details;

	private TUserAddress address;

	public List<TProductOrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<TProductOrderDetail> details) {
		this.details = details;
	}

	public TUserAddress getAddress() {
		return address;
	}

	public void setAddress(TUserAddress address) {
		this.address = address;
	}

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

	public Integer getDispatching() {
		return dispatching;
	}

	public void setDispatching(Integer dispatching) {
		this.dispatching = dispatching;
	}

	public String getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}
}