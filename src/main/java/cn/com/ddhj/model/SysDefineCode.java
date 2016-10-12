package cn.com.ddhj.model;

/**
 * 
 * 类: SysDefineCode <br>
 * 描述: 系统参数编码设置表 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月12日 上午10:44:01
 */
public class SysDefineCode extends BaseModel {

	private String code;

	private String name;

	private String parentCode;

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

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

}