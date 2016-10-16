package cn.com.ddhj.result.tuser;

import java.util.List;
import java.util.Map;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.user.TUserStep;

public class UserStepResult extends BaseResult {

	/**
	 * 设备识别码
	 */
	private String equipmentCode;
	/**
	 * 七天统计
	 */
	private List<TUserStep> week;
	/**
	 * 月统计
	 */
	private List<TUserStep> month;
	/**
	 * 年统计
	 */
	private List<Map<String, Integer>> year;

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public List<TUserStep> getWeek() {
		return week;
	}

	public void setWeek(List<TUserStep> week) {
		this.week = week;
	}

	public List<TUserStep> getMonth() {
		return month;
	}

	public void setMonth(List<TUserStep> month) {
		this.month = month;
	}

	public List<Map<String, Integer>> getYear() {
		return year;
	}

	public void setYear(List<Map<String, Integer>> year) {
		this.year = year;
	}

}
