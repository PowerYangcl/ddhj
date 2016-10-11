package cn.com.ddhj.dto.user;

import cn.com.ddhj.dto.BaseDto;

/**
 * 
 * 类: TUserLpFollowDto <br>
 * 描述: 用户楼盘关注表参数 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月11日 下午2:42:55
 */
public class TUserLpFollowDto extends BaseDto {

	private String userCode;

	private String idList;

	private String lat;

	private String lng;

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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
