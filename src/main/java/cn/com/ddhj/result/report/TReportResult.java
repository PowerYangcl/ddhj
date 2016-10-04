package cn.com.ddhj.result.report;

import java.util.List;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.report.TReport;

/**
 * 
 * 类: TReportResult <br>
 * 描述: 获取报告列表结果 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月4日 下午5:56:52
 */
public class TReportResult extends BaseResult {

	/**
	 * 报告总数
	 */
	private Integer repCount;
	/**
	 * 报告集合
	 */
	private List<TReport> repList;

	public Integer getRepCount() {
		return repCount;
	}

	public void setRepCount(Integer repCount) {
		this.repCount = repCount;
	}

	public List<TReport> getRepList() {
		return repList;
	}

	public void setRepList(List<TReport> repList) {
		this.repList = repList;
	}

}
