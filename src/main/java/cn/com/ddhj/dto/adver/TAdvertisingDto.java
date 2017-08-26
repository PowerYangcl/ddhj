package cn.com.ddhj.dto.adver;

import cn.com.ddhj.dto.BaseDto;

public class TAdvertisingDto extends BaseDto {

	private String userCode;
	private String adCode;

	public String getAdCode() {
		return adCode;
	}

	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
