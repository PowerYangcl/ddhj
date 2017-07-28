package cn.com.ddhj.result.product;

import java.util.List;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.BaseModel;

public class TPageProductListResult extends BaseResult {
	private List<? extends BaseModel> productList;
	private Long recCount = new Long(0);
	public List<? extends BaseModel> getProductList() {
		return productList;
	}
	public void setProductList(List<? extends BaseModel> productList) {
		this.productList = productList;
	}
	public Long getRecCount() {
		return recCount;
	}
	public void setRecCount(Long recCount) {
		this.recCount = recCount;
	}
}
