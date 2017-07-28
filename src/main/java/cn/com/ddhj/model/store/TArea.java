package cn.com.ddhj.model.store;

import cn.com.ddhj.model.BaseModel;

/**
 * 城市维护表
 * 
 * @author user
 *
 */
public class TArea extends BaseModel {

	private String code;

	private String parentCode;

	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}