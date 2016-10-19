package cn.com.ddhj.result.estateInfo;

import java.util.List;

public class EData {
	private String title;   // 地产名称
	private String addressFull;  // 地址
	private String total;	// 总住户
	private String city;	// 城市 苏州
	private Integer score;
	private String completion;	// 竣工时间 2011-12
	private String propertyType;		// 物业类型 别墅
	private String propertyCompany;		// 物业公司 上都物业
	private String price;			// 价格 24761元/平米
	private String volumeRate;	// 容积率  0.46
	private String propertyCosts;		// 物业费 4.64元/平方米/月
	private String parking;		// 停车位 1398个 (1：2)
	private String greeningRate;  	// 绿化率 50%
	private String gfa;			// 总建筑面积  339479平方米
	private String metro;		// 地铁信息
	private String bus;			// 公交信息
	private String lat;			// 纬度
	private String lng;		// 经度
	private List<String> images ;		// 图片
	private String overview;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddressFull() {
		return addressFull;
	}
	public void setAddressFull(String addressFull) {
		this.addressFull = addressFull;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCompletion() {
		return completion;
	}
	public void setCompletion(String completion) {
		this.completion = completion;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public String getPropertyCompany() {
		return propertyCompany;
	}
	public void setPropertyCompany(String propertyCompany) {
		this.propertyCompany = propertyCompany;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getVolumeRate() {
		return volumeRate;
	}
	public void setVolumeRate(String volumeRate) {
		this.volumeRate = volumeRate;
	}
	public String getPropertyCosts() {
		return propertyCosts;
	}
	public void setPropertyCosts(String propertyCosts) {
		this.propertyCosts = propertyCosts;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getGreeningRate() {
		return greeningRate;
	}
	public void setGreeningRate(String greeningRate) {
		this.greeningRate = greeningRate;
	}
	public String getGfa() {
		return gfa;
	}
	public void setGfa(String gfa) {
		this.gfa = gfa;
	}
	public String getMetro() {
		return metro;
	}
	public void setMetro(String metro) {
		this.metro = metro;
	}
	public String getBus() {
		return bus;
	}
	public void setBus(String bus) {
		this.bus = bus;
	}
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
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	
}
