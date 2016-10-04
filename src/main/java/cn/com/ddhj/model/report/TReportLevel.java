package cn.com.ddhj.model.report;

import cn.com.ddhj.model.BaseModel;

/**
 * 
 * 类: TReportLevel <br>
 * 描述: 环境报告等级 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月4日 下午6:45:07
 */
public class TReportLevel extends BaseModel {

    private String code;

    private String name;

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