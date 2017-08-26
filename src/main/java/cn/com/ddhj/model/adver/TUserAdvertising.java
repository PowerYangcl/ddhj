package cn.com.ddhj.model.adver;

import cn.com.ddhj.model.BaseModel;

public class TUserAdvertising extends BaseModel {

	private String userCode;

	private String adCode;

	private String nextAdCode;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getAdCode() {
		return adCode;
	}

	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}

	public String getNextAdCode() {
		return nextAdCode;
	}

	public void setNextAdCode(String nextAdCode) {
		this.nextAdCode = nextAdCode;
	}
}