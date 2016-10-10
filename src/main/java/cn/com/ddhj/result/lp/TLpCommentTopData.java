package cn.com.ddhj.result.lp;

import java.util.List;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.TLpComment;

public class TLpCommentTopData extends BaseResult {

	/**
	 * 好评百分比
	 */
	private Integer goodPercent;
	private Integer total;
	private List<TLpComment> list;

	public Integer getGoodPercent() {
		return goodPercent;
	}

	public void setGoodPercent(Integer goodPercent) {
		this.goodPercent = goodPercent;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<TLpComment> getList() {
		return list;
	}

	public void setList(List<TLpComment> list) {
		this.list = list;
	}

}
