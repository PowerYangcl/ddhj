package cn.com.ddhj.result;

import com.github.pagehelper.PageInfo;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.BaseModel;

public class PageResult extends BaseResult {
	private PageInfo<? extends BaseModel> page;

	public PageInfo<? extends BaseModel> getPage() {
		return page;
	}

	public void setPage(PageInfo<? extends BaseModel> page) {
		this.page = page;
	}

}
