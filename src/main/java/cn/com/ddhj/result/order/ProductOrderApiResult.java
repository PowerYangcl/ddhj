package cn.com.ddhj.result.order;

import java.util.List;


public class ProductOrderApiResult {
	private String orderCode;
	private Double orderMoney;
	private String orderStatus; // order_status 订单状态 8866001下单成功 8866002下单未付款 8866003订单作废
	private List<ProductResult> productList;
	
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public Double getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(Double orderMoney) {
		this.orderMoney = orderMoney;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public List<ProductResult> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductResult> productList) {
		this.productList = productList;
	} 
}
