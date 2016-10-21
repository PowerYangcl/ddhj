package cn.com.ddhj.dto.system;

import cn.com.ddhj.dto.BaseDto;

public class SysUserDto extends BaseDto{

	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
