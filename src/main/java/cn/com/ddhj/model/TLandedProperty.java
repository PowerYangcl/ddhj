package cn.com.ddhj.model;

import java.math.BigDecimal;

/**
 * 
 * 类: TLandedProperty <br>
 * 描述: 地产楼盘列表 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月3日 下午5:20:41
 */
public class TLandedProperty extends BaseModel {

	private String code;

	private String title;

	private String total;

	private String city;

	private String completion;

	private String propertyType;

	private String propertyCompany;

	private String price;

	private String volumeRate;

	private String propertyCosts;

	private String parking;

	private String greeningRate;

	private String gfa;

	private String metro;

	private String lat;

	private String lng;

	private String addressFull;

	private String bus;

	private String images;

	private String overview;

	/**
	 * 报告最小价格
	 */
	private BigDecimal rPrice;

	/**
	 * 与用户当前位置的距离
	 */
	private Integer distance;

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public BigDecimal getrPrice() {
		return rPrice;
	}

	public void setrPrice(BigDecimal rPrice) {
		this.rPrice = rPrice;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getParking() {
		return parking;
	}

	public void setParking(String parking) {
		this.parking = parking;
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

	public String getBus() {
		return bus;
	}

	public void setBus(String bus) {
		this.bus = bus;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
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

	public String getGreeningRate() {
		return greeningRate;
	}

	public void setGreeningRate(String greeningRate) {
		this.greeningRate = greeningRate;
	}

	public String getAddressFull() {
		return addressFull;
	}

	public void setAddressFull(String addressFull) {
		this.addressFull = addressFull;
	}

}