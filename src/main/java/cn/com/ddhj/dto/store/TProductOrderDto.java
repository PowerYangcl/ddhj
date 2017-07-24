package cn.com.ddhj.dto.store;

import java.util.List;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.model.TProductInfo;

public class TProductOrderDto extends BaseDto {

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
