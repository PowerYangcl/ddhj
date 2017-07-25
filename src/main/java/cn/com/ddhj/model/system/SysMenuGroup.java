package cn.com.ddhj.model.system;

import java.util.List;

import cn.com.ddhj.model.BaseModel;

public class SysMenuGroup extends BaseModel {

	private String code;

	private String name;

	private List<SysMenu> menus;

	public List<SysMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<SysMenu> menus) {
		this.menus = menus;
	}

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

}