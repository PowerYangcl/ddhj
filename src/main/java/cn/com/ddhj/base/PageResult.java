package cn.com.ddhj.base;

import java.util.List;

/**
 * 
 * 类: PageResult <br>
 * 描述: 分页结果 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月4日 下午8:35:40
 * 
 * @param <T>
 */
public class PageResult<T> extends BaseResult {

	private List<T> list;
	private Integer total;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
