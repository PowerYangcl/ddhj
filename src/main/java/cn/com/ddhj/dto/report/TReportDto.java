package cn.com.ddhj.dto.report;

import cn.com.ddhj.dto.BaseDto;

/**
 * 
 * 类: TReportDto <br>
 * 描述: 报告请求参数 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月4日 下午5:51:37
 */
public class TReportDto extends BaseDto {

	private String code;

	private String title;

	private String lpTitle;

	private String city;

	/**
	 * 楼盘编码
	 */
	private String lpCode;
	/**
	 * 报告等级编码
	 */
	private String levelCode;
	/**
	 * 范围，默认为公里数
	 */
	private Integer raidus;
	private String minLat;
	private String minLng;
	private String maxLat;
	private String maxLng;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLpTitle() {
		return lpTitle;
	}

	public void setLpTitle(String lpTitle) {
		this.lpTitle = lpTitle;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLpCode() {
		return lpCode;
	}

	public void setLpCode(String lpCode) {
		this.lpCode = lpCode;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public Integer getRaidus() {
		return raidus;
	}

	public void setRaidus(Integer raidus) {
		this.raidus = raidus;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
