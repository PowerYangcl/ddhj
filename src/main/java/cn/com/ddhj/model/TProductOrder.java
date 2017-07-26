package cn.com.ddhj.model;

import java.util.List;

public class TProductOrder extends BaseModel {
	private String code;
	private String orderStatus; // order_status 订单状态 OS8866001下单成功 OS8866002下单未付款 OS8866003订单作废
	private Integer payMoney;
	private String buyerCode;
	private String buyerPhone;
	private Integer dispatching;
	private String addressCode;

	private List<TProductOrderDetail> details;

	private TUserAddress address;

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

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