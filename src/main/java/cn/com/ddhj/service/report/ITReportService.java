package cn.com.ddhj.service.report;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.dto.TReportDto;
import cn.com.ddhj.model.report.TReport;
import cn.com.ddhj.result.report.PDFReportResult;
import cn.com.ddhj.result.report.TReportResult;
import cn.com.ddhj.service.IBaseService;

/**
 * 
 * 类: ITReportService <br>
 * 描述: 环境报告业务逻辑处理接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 下午11:38:09
 */
public interface ITReportService extends IBaseService<TReport, BaseDto> {

	
	/**
	 * 
	 * 方法: createPDF <br>
	 * 描述: 创建pdf报告 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月3日 下午1:56:13
	 * 
	 * @param array
	 * @return
	 */
	PDFReportResult createPDF(String code, String path);

	/**
	 * 
	 * 方法: getReportData <br>
	 * 描述: 获取报告列表 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月4日 下午5:58:28
	 * 
	 * @param dto
	 * @return
	 */
	TReportResult getReportData(TReportDto dto);
}
