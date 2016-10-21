package cn.com.ddhj.result.lp;

import com.github.pagehelper.PageInfo;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.TLandedProperty;

/**
 * 
 * 类: TLandedPropertyDataResult <br>
 * 描述: 楼盘列表 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月21日 下午4:13:04
 */
public class TLandedPropertyDataResult extends BaseResult {

	private PageInfo<TLandedProperty> page;

	public PageInfo<TLandedProperty> getPage() {
		return page;
	}

	public void setPage(PageInfo<TLandedProperty> page) {
		this.page = page;
	}

}
