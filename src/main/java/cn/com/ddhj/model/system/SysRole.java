package cn.com.ddhj.model.system;

import cn.com.ddhj.model.BaseModel;

/**
 * 
 * 类: SysRole <br>
 * 描述: 系统角色表 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月21日 下午2:51:59
 */
public class SysRole extends BaseModel {

	private String code;

	private String name;

	private String permission;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

}