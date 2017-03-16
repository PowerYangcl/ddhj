package cn.com.ddhj.dto.trade;

import cn.com.ddhj.dto.BaseDto;

public class TTradeOrderDto extends BaseDto {
	private String beginDate;
	
	private String endDate;
	
	/**
	 * 客户编号
	 */
	private String userCode;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
