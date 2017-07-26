package cn.com.ddhj.base;

import java.util.List;

import cn.com.ddhj.model.BaseModel;

/**
 * 
 * 类: PageResult <br>
 * 描述: 分页结果 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月4日 下午8:35:40
 * 
 * @param <T>
 */
public class PageResult extends BaseResult {

	private List<? extends BaseModel> repList;
	private Integer repCount;

	public List<? extends BaseModel> getRepList() {
		return repList;
	}

	public void setRepList(List<? extends BaseModel> repList) {
		this.repList = repList;
	}

	public Integer getRepCount() {
		return repCount;
	}

	public void setRepCount(Integer repCount) {
		this.repCount = repCount;
	}

}
