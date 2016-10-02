package cn.com.ddhj.result.longitudeLatitude;

public class Result {
	private String lat;
	private String lng;
	private String type;
	private String address;
	private String business;
	private int citycode;
	private Ext ext;
	
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public int getCitycode() {
		return citycode;
	}
	public void setCitycode(int citycode) {
		this.citycode = citycode;
	}
	public Ext getExt() {
		return ext;
	}
	public void setExt(Ext ext) {
		this.ext = ext;
	}
}
