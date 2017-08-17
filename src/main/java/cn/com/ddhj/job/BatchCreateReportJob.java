package cn.com.ddhj.job;

import cn.com.ddhj.annotation.Inject;
import cn.com.ddhj.base.BaseClass;
import cn.com.ddhj.service.report.ITReportService;

/**
 * 
 * 类: BatchCreateReportJob <br>
 * 描述: 批量生成报告job <br>
 * 作者: zhy<br>
 * 时间: 2016年10月26日 下午10:31:48
 */
public class BatchCreateReportJob extends BaseClass {

	@Inject
	private ITReportService service;

	/**
	 * 
	 * 方法: batchCreateReport <br>
	 * 描述: 批量生成报告 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月26日 下午10:34:18
	 */
	public void batchCreateReport() {
//		service.batchCreateReport();
		service.batchCreateH5Report();
	}

}
