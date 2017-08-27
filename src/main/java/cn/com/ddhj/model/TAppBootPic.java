package cn.com.ddhj.model;

import java.util.Date;

public class TAppBootPic extends BaseModel{ 
    private String appVersion;
    private String picUrl;
    
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}