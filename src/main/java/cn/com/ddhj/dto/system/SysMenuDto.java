package cn.com.ddhj.dto.system;

import cn.com.ddhj.dto.BaseDto;

public class SysMenuDto extends BaseDto{

	private String groupCode;
	
	private String parentCode;

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
	
}
