package cn.com.ddhj.result.product;

import java.util.List;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.BaseModel;

public class TPageProductListResult extends BaseResult {
	private List<? extends BaseModel> productList;
	private Integer recCount;
	public List<? extends BaseModel> getProductList() {
		return productList;
	}
	public void setProductList(List<? extends BaseModel> productList) {
		this.productList = productList;
	}
	public Integer getRecCount() {
		return recCount;
	}
	public void setRecCount(Integer recCount) {
		this.recCount = recCount;
	}
}
