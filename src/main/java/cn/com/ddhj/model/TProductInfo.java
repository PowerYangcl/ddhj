package cn.com.ddhj.model;

public class TProductInfo extends BaseModel {
	private String productCode;
	private String productName;
	private String mainPicUrl;
	private Integer currentPrice;
	private Integer stockNum;
	private String productTip;
	private Integer flagSellable;
	/**
	 * 上传图片数组
	 */
	private String images;

	/**
	 * 接口json转换使用在t_product_info中无对应字段
	 */
	private Integer buyNum;

	/**
	 * 商品图片集合
	 */
	private String pics;

	private String initialPreview;

	private String initialPreviewConfig;

	public String getInitialPreview() {
		return initialPreview;
	}

	public void setInitialPreview(String initialPreview) {
		this.initialPreview = initialPreview;
	}

	public String getInitialPreviewConfig() {
		return initialPreviewConfig;
	}

	public void setInitialPreviewConfig(String initialPreviewConfig) {
		this.initialPreviewConfig = initialPreviewConfig;
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Integer getFlagSellable() {
		return flagSellable;
	}

	public void setFlagSellable(Integer flagSellable) {
		this.flagSellable = flagSellable;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMainPicUrl() {
		return mainPicUrl;
	}

	public void setMainPicUrl(String mainPicUrl) {
		this.mainPicUrl = mainPicUrl;
	}

	public Integer getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Integer currentPrice) {
		this.currentPrice = currentPrice;
	}

	public Integer getStockNum() {
		return stockNum;
	}

	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}

	public String getProductTip() {
		return productTip;
	}

	public void setProductTip(String productTip) {
		this.productTip = productTip;
	}

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}
}