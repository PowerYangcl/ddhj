package cn.com.ddhj.model;

public class TProductOrderDetail extends BaseModel {
	private String orderCode;
	private String productCode;
	private Integer currentPrice;
	private Integer buyNum;
	private TProductInfo product;

	public TProductInfo getProduct() {
		return product;
	}

	public void setProduct(TProductInfo product) {
		this.product = product;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Integer currentPrice) {
		this.currentPrice = currentPrice;
	}

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}
}