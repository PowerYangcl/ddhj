package cn.com.ddhj.result.order;

import java.util.Map;

import com.github.pagehelper.PageInfo;

import cn.com.ddhj.base.BaseResult;

public class SysOrderDataResult extends BaseResult {

	private PageInfo<Map<String, String>> page;

	public PageInfo<Map<String, String>> getPage() {
		return page;
	}

	public void setPage(PageInfo<Map<String, String>> page) {
		this.page = page;
	}

}
