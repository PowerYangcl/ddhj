package cn.com.ddhj.model;

/**
 * 
 * 类: TUserLogin <br>
 * 描述: 用户登录表 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月5日 下午10:15:35
 */
public class TUserLogin extends BaseModel {

	private String userToken;

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

}