package cn.com.ddhj.model;


public class TAppConfig extends BaseModel{
    private String wechatAppId;
    private String wechatAppSecret;
    private String umKeyAndroid;
    private String umKeyIos;
    private String qqAppKey;
    private String qqAppId;
    private String appVersion;
    
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getWechatAppId() {
		return wechatAppId;
	}
	public void setWechatAppId(String wechatAppId) {
		this.wechatAppId = wechatAppId;
	}
	public String getWechatAppSecret() {
		return wechatAppSecret;
	}
	public void setWechatAppSecret(String wechatAppSecret) {
		this.wechatAppSecret = wechatAppSecret;
	}
	public String getUmKeyAndroid() {
		return umKeyAndroid;
	}
	public void setUmKeyAndroid(String umKeyAndroid) {
		this.umKeyAndroid = umKeyAndroid;
	}
	public String getUmKeyIos() {
		return umKeyIos;
	}
	public void setUmKeyIos(String umKeyIos) {
		this.umKeyIos = umKeyIos;
	}
	public String getQqAppKey() {
		return qqAppKey;
	}
	public void setQqAppKey(String qqAppKey) {
		this.qqAppKey = qqAppKey;
	}
	public String getQqAppId() {
		return qqAppId;
	}
	public void setQqAppId(String qqAppId) {
		this.qqAppId = qqAppId;
	}
}