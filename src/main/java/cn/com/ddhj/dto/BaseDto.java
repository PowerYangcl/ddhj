package cn.com.ddhj.dto;

/**
 * 
 * 类: BaseInfo <br>
 * 描述: 页面传入参数基类 <br>
 * 作者: zhy<br>
 * 时间: 2016年8月31日 上午9:58:47
 */
public class BaseDto {

	/**
	 * 当前页
	 */
	private Integer pageIndex;
	/**
	 * 页面显示数据最大数
	 */
	private Integer pageSize;
	/**
	 * 查询开始值
	 */
	private Integer start;
	/**
	 * 数据总数
	 */
	private Integer total;

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
