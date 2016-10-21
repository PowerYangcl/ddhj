package cn.com.ddhj.model.system;

import cn.com.ddhj.model.BaseModel;

/**
 * 
 * 类: SysUser <br>
 * 描述: 系统用户表 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月21日 下午2:52:32
 */
public class SysUser extends BaseModel {

	private String code;
	
	private String userName;

	private String password;

	private String roleCode;

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

}