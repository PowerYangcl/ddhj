package cn.com.ddhj.dto.user;

import cn.com.ddhj.dto.BaseDto;

public class TUserCarbonOperationDto extends BaseDto {

	private String userCode;
	private String operationType;
	private String operationTypeChild;
	private Integer day;

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getOperationTypeChild() {
		return operationTypeChild;
	}

	public void setOperationTypeChild(String operationTypeChild) {
		this.operationTypeChild = operationTypeChild;
	}
}
