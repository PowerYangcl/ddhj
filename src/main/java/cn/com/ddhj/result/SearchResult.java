package cn.com.ddhj.result;

import java.util.List;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.solr.data.SolrData;

public class SearchResult extends BaseResult {
	private List<SolrData> list;

	public List<SolrData> getList() {
		return list;
	}

	public void setList(List<SolrData> list) {
		this.list = list;
	}
}
