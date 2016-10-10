package cn.com.ddhj.service.report;

import java.util.List;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.dto.report.TReportDto;
import cn.com.ddhj.model.report.TReport;
import cn.com.ddhj.result.report.TReportLResult;
import cn.com.ddhj.result.report.TReportSelResult;
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
	 * 方法: getReportData <br>
	 * 描述: 获取报告列表 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月4日 下午5:58:28
	 * 
	 * @param dto
	 * @return
	 */
	TReportLResult getReportData(TReportDto dto);

	/**
	 * 
	 * 方法: insert <br>
	 * 描述: 添加新的环境报告 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月4日 下午9:04:56
	 * 
	 * @param entity
	 * @return
	 */
	BaseResult insert(TReport entity, String path);

	/**
	 * 
	 * 方法: updateByCode <br>
	 * 描述: 编辑现有的环境报告 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月4日 下午9:22:14
	 * 
	 * @param entity
	 * @param path
	 * @return
	 */
	BaseResult updateByCode(TReport entity, String path);

	/**
	 * 
	 * 方法: getTReport <br>
	 * 描述: 根据编码查询报告详情 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月4日 下午9:59:40
	 * 
	 * @param code
	 * @return
	 */
	TReportSelResult getTReport(String code);

	/**
	 * 
	 * 方法: insertReportData <br>
	 * 描述: 批量生成环境报告 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月9日 下午6:48:59
	 * 
	 * @param list
	 * @return
	 */
	BaseResult insertReportData(List<TReport> list);
}
