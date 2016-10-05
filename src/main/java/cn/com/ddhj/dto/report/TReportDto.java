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

	private String position;
	/**
	 * 范围，默认为公里数
	 */
	private Integer raidus;
	private String minLat;
	private String minLng;
	private String maxLat;
	private String maxLng;

	public Integer getRaidus() {
		return raidus;
	}

	public void setRaidus(Integer raidus) {
		this.raidus = raidus;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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
