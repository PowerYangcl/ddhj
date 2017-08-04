package cn.com.ddhj.dto.user;

import cn.com.ddhj.dto.BaseDto;

public class TUserStepDto extends BaseDto {

	/**
	 * 设备标识码
	 */
	private String equipmentCode;
	/**
	 * 开始查询日期
	 */
	private String startDate;
	/**
	 * 结束查询日期
	 */
	private String endDate;
	/**
	 * 查询类型 0 七天 1 一个月 2 一年
	 */
	private Integer type;

	private String userCode;

	private String date;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
