package cn.com.ddhj.model.user;

import java.math.BigDecimal;

import cn.com.ddhj.model.BaseModel;

public class TUserCarbonOperation extends BaseModel {

	private String code;

	private String userCode;

	private String operationType;

	private String operationTypeName;
	
	private String operationTypeChild;

	private String operationTypeChildName;

	private BigDecimal carbonSum;

	
	public String getOperationTypeName() {
		return operationTypeName;
	}

	public void setOperationTypeName(String operationTypeName) {
		this.operationTypeName = operationTypeName;
	}

	public String getOperationTypeChildName() {
		return operationTypeChildName;
	}

	public void setOperationTypeChildName(String operationTypeChildName) {
		this.operationTypeChildName = operationTypeChildName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public BigDecimal getCarbonSum() {
		return carbonSum;
	}

	public void setCarbonSum(BigDecimal carbonSum) {
		this.carbonSum = carbonSum;
	}

}