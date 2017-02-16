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
	/**
	 * 支付金额
	 */
	private BigDecimal payMoney;
	/**
	 * 碳币使用数量
	 */
	private BigDecimal carbonMoney;
	/**
	 * 碳币兑换人民币比例
	 */
	private BigDecimal carbonToMoneyRatio;

	public BigDecimal getCarbonToMoneyRatio() {
		return carbonToMoneyRatio;
	}

	public void setCarbonToMoneyRatio(BigDecimal carbonToMoneyRatio) {
		this.carbonToMoneyRatio = carbonToMoneyRatio;
	}

	public BigDecimal getCarbonMoney() {
		return carbonMoney;
	}

	public void setCarbonMoney(BigDecimal carbonMoney) {
		this.carbonMoney = carbonMoney;
	}

	public List<TReport> getReportList() {
		return reportList;
	}

	public void setReportList(List<TReport> reportList) {
		this.reportList = reportList;
	}

	public BigDecimal getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}

}
