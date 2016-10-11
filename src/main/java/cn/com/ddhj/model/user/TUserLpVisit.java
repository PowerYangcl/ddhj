package cn.com.ddhj.model.user;

import cn.com.ddhj.model.BaseModel;

/**
 * 
 * 类: TUserLpVisit <br>
 * 描述: 用户浏览记录表 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月11日 下午2:01:05
 */
public class TUserLpVisit extends BaseModel {

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