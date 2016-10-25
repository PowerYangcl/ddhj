package cn.com.ddhj.model.report;

import cn.com.ddhj.model.BaseModel;

/**
 * 
 * 类: TReportLog <br>
 * 描述: 报告操作日志表 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月25日 上午10:33:12
 */
public class TReportLog extends BaseModel {

	private String lpCode;

	private String reportCode;

	private String detail;

	public String getLpCode() {
		return lpCode;
	}

	public void setLpCode(String lpCode) {
		this.lpCode = lpCode;
	}

	public String getReportCode() {
		return reportCode;
	}

	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}