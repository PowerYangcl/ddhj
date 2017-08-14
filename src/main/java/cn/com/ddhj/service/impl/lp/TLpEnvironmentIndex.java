package cn.com.ddhj.service.impl.lp;

import java.math.BigDecimal;

public class TLpEnvironmentIndex {

	/**
	 * 数值
	 */
	private BigDecimal value;
	/**
	 * 等级
	 */
	private Integer level;
	/**
	 * 排行
	 */
	private Integer sort;
	/**
	 * 评价
	 */
	private String evaluate;

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}

}
