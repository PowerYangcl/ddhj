package cn.com.ddhj.model.system;

import java.util.List;

import cn.com.ddhj.model.BaseModel;

public class SysMenu extends BaseModel {

	private String code;

	private String parentCode;

	private String groupCode;

	private String name;

	private String url;

	private Integer isUsable;

	private List<SysMenu> subMenus;

	public List<SysMenu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<SysMenu> subMenus) {
		this.subMenus = subMenus;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getIsUsable() {
		return isUsable;
	}

	public void setIsUsable(Integer isUsable) {
		this.isUsable = isUsable;
	}

}