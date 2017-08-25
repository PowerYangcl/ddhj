package cn.com.ddhj.result.order;

/**
 * @description: 商城 订单列表返回数据实体
 * 
 * @author Yangcl
 * @date 2017年7月27日 上午11:03:53 
 * @version 1.0.0
 */
public class ProductOrderListResult {
	private String orderCode;
	private String orderStatus; // order_status 订单状态 8866001下单成功 8866002下单未付款 8866003订单作废
	private Integer payMoney;
	private String mobile;
	private String productNames;
	private String mpurl;
	private String productPrices;
	private String productCode;
	private String buyNums;
	
	public String getBuyNums() {
		return buyNums;
	}
	public void setBuyNums(String buyNums) {
		this.buyNums = buyNums;
	}
	public String getProductPrices() {
		return productPrices;
	}
	public void setProductPrices(String productPrices) {
		this.productPrices = productPrices;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Integer payMoney) {
		this.payMoney = payMoney;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getProductNames() {
		return productNames;
	}
	public void setProductNames(String productNames) {
		this.productNames = productNames;
	}
	public String getMpurl() {
		return mpurl;
	}
	public void setMpurl(String mpurl) {
		this.mpurl = mpurl;
	}
}
