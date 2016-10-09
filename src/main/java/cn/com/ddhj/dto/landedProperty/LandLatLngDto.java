package cn.com.ddhj.dto.landedProperty;

public class LandLatLngDto {
	/**
	 * 范围，默认为公里数
	 */
	private Integer raidus;
	private String minLat;
	private String minLng;
	private String maxLat;
	private String maxLng;
	private Integer page;
	
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRaidus() {
		return raidus;
	}
	public void setRaidus(Integer raidus) {
		this.raidus = raidus;
	}
	public String getMinLat() {
		return minLat;
	}
	public void setMinLat(String minLat) {
		this.minLat = minLat;
	}
	public String getMinLng() {
		return minLng;
	}
	public void setMinLng(String minLng) {
		this.minLng = minLng;
	}
	public String getMaxLat() {
		return maxLat;
	}
	public void setMaxLat(String maxLat) {
		this.maxLat = maxLat;
	}
	public String getMaxLng() {
		return maxLng;
	}
	public void setMaxLng(String maxLng) {
		this.maxLng = maxLng;
	}
	
	
}
