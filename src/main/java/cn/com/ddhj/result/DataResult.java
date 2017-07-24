package cn.com.ddhj.result;

import java.util.List;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.BaseModel;

public class DataResult extends BaseResult{

	private List<? extends BaseModel> data;

	public List<? extends BaseModel> getData() {
		return data;
	}

	public void setData(List<? extends BaseModel> data) {
		this.data = data;
	}
	
}
