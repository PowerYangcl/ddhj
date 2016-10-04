package cn.com.ddhj.model.report;

import cn.com.ddhj.model.BaseModel;

/**
 * 
 * 类: TReportEnvironmentLevel <br>
 * 描述: 环境报告环境等级描述 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 下午11:12:54
 */
public class TReportEnvironmentLevel extends BaseModel {

	private String code;

	private String type;

	private Integer level;

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}