package cn.com.ddhj.model.adver;

import cn.com.ddhj.model.BaseModel;

public class TAdvertising extends BaseModel {

	private String adCode;

	private String title;

	private String pic;

	private String startTime;

	private String endTime;

	private Integer status;

	private String content;

	private String nextAdCode;

	public String getNextAdCode() {
		return nextAdCode;
	}

	public void setNextAdCode(String nextAdCode) {
		this.nextAdCode = nextAdCode;
	}

	public String getAdCode() {
		return adCode;
	}

	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}