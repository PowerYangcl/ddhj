package cn.com.ddhj.result.lp;

import java.util.List;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.TLpComment;

/**
 * 
 * 类: TLpCommentData <br>
 * 描述: 评论列表返回结果 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月10日 下午12:07:42
 */
public class TLpCommentData extends BaseResult {

	private Integer goodTotal;

	private Integer mediumTotal;

	private Integer badTotal;

	private List<TLpComment> list;

	private Integer total;

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

	public Integer getGoodTotal() {
		return goodTotal;
	}

	public void setGoodTotal(Integer goodTotal) {
		this.goodTotal = goodTotal;
	}

	public Integer getMediumTotal() {
		return mediumTotal;
	}

	public void setMediumTotal(Integer mediumTotal) {
		this.mediumTotal = mediumTotal;
	}

	public Integer getBadTotal() {
		return badTotal;
	}

	public void setBadTotal(Integer badTotal) {
		this.badTotal = badTotal;
	}

}
