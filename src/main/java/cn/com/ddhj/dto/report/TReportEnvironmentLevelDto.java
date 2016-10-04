package cn.com.ddhj.dto.report;

import cn.com.ddhj.dto.BaseDto;

public class TReportEnvironmentLevelDto extends BaseDto{

	private String type;

	private Integer level;
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
