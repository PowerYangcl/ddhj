package cn.com.ddhj.result.tuser;

import java.util.List;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.TLandedProperty;

/**
 * 
 * 类: VisitResult <br>
 * 描述: 用户楼盘浏览记录 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月11日 下午3:01:43
 */
public class VisitResult extends BaseResult {

	private Integer total;

	private List<TLandedProperty> list;

	public List<TLandedProperty> getList() {
		return list;
	}

	public void setList(List<TLandedProperty> list) {
		this.list = list;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
