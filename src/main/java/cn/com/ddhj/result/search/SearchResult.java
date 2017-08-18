package cn.com.ddhj.result.search;

import java.util.LinkedList;
import java.util.List;

import cn.com.ddhj.base.BaseResult;

public class SearchResult<T> extends BaseResult {
	private List<T> list = new LinkedList<T>();

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
