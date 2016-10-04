package cn.com.ddhj.model;

/**
 * 
 * 类: TVilla <br>
 * 描述: 别墅区 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月4日 上午10:51:03
 */
public class TVilla extends BaseModel {

	private String city;

	private String name;

	private String lat;

	private String lng;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}