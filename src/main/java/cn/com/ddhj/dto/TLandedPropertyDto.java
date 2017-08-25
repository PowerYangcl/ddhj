package cn.com.ddhj.dto;

import java.util.List;

public class TLandedPropertyDto extends BaseDto {

	private String title;

	private String city;

	private List<String> codes;

	private Double minLat;

	private Double minLng;

	private Double maxLat;

	private Double maxLng;

	private Double score;
	
	private String userCode;
	
	

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Double getMinLat() {
		return minLat;
	}

	public void setMinLat(Double minLat) {
		this.minLat = minLat;
	}

	public Double getMinLng() {
		return minLng;
	}

	public void setMinLng(Double minLng) {
		this.minLng = minLng;
	}

	public Double getMaxLat() {
		return maxLat;
	}

	public void setMaxLat(Double maxLat) {
		this.maxLat = maxLat;
	}

	public Double getMaxLng() {
		return maxLng;
	}

	public void setMaxLng(Double maxLng) {
		this.maxLng = maxLng;
	}

	public List<String> getCodes() {
		return codes;
	}

	public void setCodes(List<String> codes) {
		this.codes = codes;
	}

}
