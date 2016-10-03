package cn.com.ddhj.service;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.model.TReport;

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
	String createPDF(String code);
}
