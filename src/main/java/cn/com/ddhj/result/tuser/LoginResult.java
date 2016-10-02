package cn.com.ddhj.result.tuser;

import cn.com.ddhj.base.BaseResult;

/**
 * 
 * 类: LoginResult <br>
 * 描述: 登录返回结果 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 上午11:55:31
 */
public class LoginResult extends BaseResult {

	/**
	 * 用户认证串
	 */
	private String userToken;

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

}
