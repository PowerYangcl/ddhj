package cn.com.ddhj.result;

/**
 * @description: 商城 订单列表返回数据实体
 * 
 * @author Yangcl
 * @date 2017年7月27日 上午11:03:53 
 * @version 1.0.0
 */
public class ProductOrderResult {
	private String orderCode;
	private String orderStatus; // order_status 订单状态 OS8866001下单成功 OS8866002下单未付款 OS8866003订单作废
	private Integer payMoney;
	private String mobile;
	private String productNames;
	private String mpurl;
	
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
