package cn.com.ddhj.result;

import java.util.List;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.TCity;

public class CityResult extends BaseResult {

	private List<TCity> list;

	public List<TCity> getList() {
		return list;
	}

	public void setList(List<TCity> list) {
		this.list = list;
	}

}
