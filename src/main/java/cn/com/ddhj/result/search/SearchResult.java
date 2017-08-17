package cn.com.ddhj.result.search;

import java.util.LinkedList;
import java.util.List;

import cn.com.ddhj.base.BaseResult;

public class SearchResult extends BaseResult {
	private List<SearchEntity> list = new LinkedList<SearchEntity>();

	public List<SearchEntity> getList() {
		return list;
	}

	public void setList(List<SearchEntity> list) {
		this.list = list;
	}
}
