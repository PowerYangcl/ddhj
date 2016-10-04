package cn.com.ddhj.model.report;

import cn.com.ddhj.model.BaseModel;

/**
 * 
 * 类: TReportTemplate <br>
 * 描述: 报告模板表 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 下午11:14:23
 */
public class TReportTemplate extends BaseModel {

	private String code;

	private String name;

	private String type;

	private String content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}