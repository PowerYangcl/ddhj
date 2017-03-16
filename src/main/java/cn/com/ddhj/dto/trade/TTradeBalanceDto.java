package cn.com.ddhj.dto.trade;

import cn.com.ddhj.dto.BaseDto;

public class TTradeBalanceDto extends BaseDto {
	private String userCode;
	private String objectCode;
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getObjectCode() {
		return objectCode;
	}
	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}
}
