package cn.com.ddhj.result.order;

import java.math.BigDecimal;
import java.util.List;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.report.TReport;

/**
 * 
 * 类: OrderAffirmResult <br>
 * 描述: 订单确认结果 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月7日 下午4:29:53
 */
public class OrderAffirmResult extends BaseResult {

	private List<TReport> reportList;
	private BigDecimal pageMoney;

	public List<TReport> getReportList() {
		return reportList;
	}

	public void setReportList(List<TReport> reportList) {
		this.reportList = reportList;
	}

	public BigDecimal getPageMoney() {
		return pageMoney;
	}

	public void setPageMoney(BigDecimal pageMoney) {
		this.pageMoney = pageMoney;
	}

}
