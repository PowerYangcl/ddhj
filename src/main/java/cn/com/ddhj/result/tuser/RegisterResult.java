package cn.com.ddhj.result.tuser;

import cn.com.ddhj.base.BaseResult;

/**
 * 
 * 类: RegisterResult <br>
 * 描述: 注册返回结果 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 下午12:02:19
 */
public class RegisterResult extends BaseResult{
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
