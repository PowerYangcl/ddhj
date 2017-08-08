package cn.com.ddhj.dto.trade;

import cn.com.ddhj.dto.BaseDto;

public class TTradeDealDto extends BaseDto {
	private String cityId;
	private Integer month;
	private String objectCode;
	private String dealDate;

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getObjectCode() {
		return objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getDealDate() {
		return dealDate;
	}

	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}
}
