package cn.com.ddhj.result.tuser;

import cn.com.ddhj.base.BaseResult;

public class MessageSelResult extends BaseResult {

	private String type;
	private String title;
	private String memo;
	private String time;
	private String outerCode;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOuterCode() {
		return outerCode;
	}

	public void setOuterCode(String outerCode) {
		this.outerCode = outerCode;
	}

}
