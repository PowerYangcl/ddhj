package cn.com.ddhj.dto.store;

import java.util.List;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.model.TProductInfo;

public class TProductOrderDto extends BaseDto {

	private String code;

	private String buyerPhone;

	private String nickName;

	private String orderStatus;

	/**
	 * 支付碳币
	 */
	private Integer payMoney;
	/**
	 * 收货地址编号
	 */
	private String phone;
	/**
	 * 配送方式
	 */
	private Integer dispatching;

	/**
	 * 收货地址编号
	 */
	private String addressCode;

	/**
	 * 商品列表
	 */
	private List<TProductInfo> productList;

	public String getBuyerPhone() {
		return buyerPhone;
	}

	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}

	public Integer getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Integer payMoney) {
		this.payMoney = payMoney;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getDispatching() {
		return dispatching;
	}

	public void setDispatching(Integer dispatching) {
		this.dispatching = dispatching;
	}

	public List<TProductInfo> getProductList() {
		return productList;
	}

	public void setProductList(List<TProductInfo> productList) {
		this.productList = productList;
	}

}
