package cn.com.ddhj.service.report;

import java.util.List;

import com.alibaba.fastjson.JSONArray;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.dto.report.TReportDto;
import cn.com.ddhj.model.report.TReport;
import cn.com.ddhj.model.report.TReportEnvironmentLevel;
import cn.com.ddhj.model.system.SysUser;
import cn.com.ddhj.result.report.CreateReportResult;
import cn.com.ddhj.result.report.TReportDataResult;
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

	/**
	 * 
	 * 方法: getTReportByLp <br>
	 * 描述: 根据楼盘编码查询环境报告 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月12日 下午5:32:44
	 * 
	 * @param lpCode
	 * @return
	 */
	TReportSelResult getTReportByLp(String lpCode, String userTocken);

	/**
	 * 
	 * 方法: getPageData <br>
	 * 描述: 获取所有报告列表 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月21日 下午11:52:57
	 * 
	 * @param dto
	 * @return
	 */
	TReportDataResult getPageData(TReportDto dto);

	/**
	 * 
	 * 方法: createReport <br>
	 * 描述: 根据楼盘编码生成环境报告 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月22日 下午8:16:51
	 * 
	 * @param lpCode
	 * @return
	 */
	BaseResult createReport(TReportDto dto, String path, SysUser user);

	/**
	 * 
	 * 方法: insertSelective <br>
	 * 描述: 添加新的报告 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月23日 下午7:57:35
	 * 
	 * @param entity
	 * @param path
	 * @return
	 */
	BaseResult insertSelective(TReport entity, String path);

	/**
	 * 
	 * 方法: batchCreateReport <br>
	 * 描述: 批量生成报告，用于定时任务 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月26日 下午10:33:04
	 */
	int batchCreateReport();
	
	/**
	 * 批量生成H5报告，用于定时任务 
	 * @return
	 */
	int batchCreateH5Report();

	/**
	 * 
	 * 方法: createPPT <br>
	 * 描述: 生成ppt格式环境报告，并转换为pdf报告 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年3月1日 下午1:41:15
	 * 
	 * @param code
	 * @param lpCode
	 * @param cityAir
	 * @param list
	 * @return
	 */
	CreateReportResult createPPT(String code, String lpCode, JSONArray cityAir, List<TReportEnvironmentLevel> list);

	/**
	 * 批量创建H5环境报告
	 * 
	 * @return
	 */
	BaseResult createHtmlAll();

	/**
	 * 根据楼盘和类型创建报告
	 * 
	 * @param lpCode
	 * @param type
	 *            full 完整版 simplification精简版
	 * @return
	 */
	BaseResult createHtmlByLpCode(String lpCode, String type);
}
