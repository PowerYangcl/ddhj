package cn.com.ddhj.model;

import java.math.BigDecimal;
import java.util.List;

import cn.com.ddhj.model.lp.TLpEnvironment;
import cn.com.ddhj.model.lp.TLpEnvironmentIndex;

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

	private Double score;

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

	/**
	 * 环境相关参数
	 */
	private TLpEnvironment environment;
	/**
	 * 气候类型
	 */
	private String weatherDistribution;

	private List<TLpEnvironmentIndex> environmentIndexs;

	private List<TLpEnvironmentIndex> environmentIndexs1;

	private List<TLpEnvironmentIndex> environmentIndexs2;

	private List<TLpEnvironmentIndex> environmentIndexs3;

	private List<TLpEnvironmentIndex> environmentIndexs4;

	public List<TLpEnvironmentIndex> getEnvironmentIndexs1() {
		return environmentIndexs1;
	}

	public void setEnvironmentIndexs1(List<TLpEnvironmentIndex> environmentIndexs1) {
		this.environmentIndexs1 = environmentIndexs1;
	}

	public List<TLpEnvironmentIndex> getEnvironmentIndexs4() {
		return environmentIndexs4;
	}

	public void setEnvironmentIndexs4(List<TLpEnvironmentIndex> environmentIndexs4) {
		this.environmentIndexs4 = environmentIndexs4;
	}

	public List<TLpEnvironmentIndex> getEnvironmentIndexs2() {
		return environmentIndexs2;
	}

	public void setEnvironmentIndexs2(List<TLpEnvironmentIndex> environmentIndexs2) {
		this.environmentIndexs2 = environmentIndexs2;
	}

	public List<TLpEnvironmentIndex> getEnvironmentIndexs3() {
		return environmentIndexs3;
	}

	public void setEnvironmentIndexs3(List<TLpEnvironmentIndex> environmentIndexs3) {
		this.environmentIndexs3 = environmentIndexs3;
	}

	public List<TLpEnvironmentIndex> getEnvironmentIndexs() {
		return environmentIndexs;
	}

	public void setEnvironmentIndexs(List<TLpEnvironmentIndex> environmentIndexs) {
		this.environmentIndexs = environmentIndexs;
	}

	public TLpEnvironment getEnvironment() {
		return environment;
	}

	public void setEnvironment(TLpEnvironment environment) {
		this.environment = environment;
	}

	public String getWeatherDistribution() {
		return weatherDistribution;
	}

	public void setWeatherDistribution(String weatherDistribution) {
		this.weatherDistribution = weatherDistribution;
	}

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

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

}