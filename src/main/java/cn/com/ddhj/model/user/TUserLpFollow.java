package cn.com.ddhj.model.user;

import cn.com.ddhj.model.BaseModel;

/**
 * 
 * 类: TUserLpFollow <br>
 * 描述: 用户楼盘关注表 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月11日 下午2:01:37
 */
public class TUserLpFollow extends BaseModel {

	private String lpCode;

	private String userCode;

	public String getLpCode() {
		return lpCode;
	}

	public void setLpCode(String lpCode) {
		this.lpCode = lpCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}