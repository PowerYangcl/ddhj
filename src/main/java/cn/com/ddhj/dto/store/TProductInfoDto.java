package cn.com.ddhj.dto.store;

import cn.com.ddhj.dto.BaseDto;

public class TProductInfoDto extends BaseDto {

	private String productCode;
	private String productName;
	private Integer flagSellable;

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

	public Integer getFlagSellable() {
		return flagSellable;
	}

	public void setFlagSellable(Integer flagSellable) {
		this.flagSellable = flagSellable;
	}

}
