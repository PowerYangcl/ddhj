package cn.com.ddhj.dto.user;

import java.util.List;

import cn.com.ddhj.dto.BaseDto;

/**
 * 
 * 类: TUserLpVisitDto <br>
 * 描述: 用户浏览记录表 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月11日 下午2:43:38
 */
public class TUserLpVisitDto extends BaseDto {

	private String userCode;

	private List<String> codes;

	private String idList;

	private String lat;

	private String lng;

	public List<String> getCodes() {
		return codes;
	}

	public void setCodes(List<String> codes) {
		this.codes = codes;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getIdList() {
		return idList;
	}

	public void setIdList(String idList) {
		this.idList = idList;
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
