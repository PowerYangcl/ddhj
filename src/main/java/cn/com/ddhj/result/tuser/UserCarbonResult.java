package cn.com.ddhj.result.tuser;

import java.math.BigDecimal;

import cn.com.ddhj.base.BaseResult;

/**
 * 用户碳币相关数据
 * @author zht
 *
 */
public class UserCarbonResult extends BaseResult {
	/**
	 * 炭币
	 */
	private BigDecimal carbonMoney = BigDecimal.ZERO;
	
	/**
	 * 昨日支出碳币
	 */
	private BigDecimal expense = BigDecimal.ZERO;
	
	/**
	 * 昨日收入碳币
	 */
	private BigDecimal income = BigDecimal.ZERO;

	public BigDecimal getCarbonMoney() {
		return carbonMoney;
	}

	public void setCarbonMoney(BigDecimal carbonMoney) {
		this.carbonMoney = carbonMoney;
	}

	public BigDecimal getExpense() {
		return expense;
	}

	public void setExpense(BigDecimal expense) {
		this.expense = expense;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}
}
