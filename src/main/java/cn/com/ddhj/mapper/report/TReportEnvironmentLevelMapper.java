package cn.com.ddhj.mapper.report;

import java.util.List;

import cn.com.ddhj.dto.report.TReportEnvironmentLevelDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.report.TReportEnvironmentLevel;

/**
 * 
 * 类: TReportEnvironmentLevelMapper <br>
 * 描述: 环境报告环境等级描述数据库访问接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 下午11:42:50
 */
public interface TReportEnvironmentLevelMapper extends BaseMapper<TReportEnvironmentLevel, TReportEnvironmentLevelDto> {

	/**
	 * 
	 * 方法: findTReportEnvironmentLevelByType <br>
	 * 描述: 根据环境等级和类型查询等级描述信息 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月2日 下午11:17:12
	 * 
	 * @param type
	 * @return
	 */
	TReportEnvironmentLevel findTReportEnvironmentLevelByType(TReportEnvironmentLevelDto dto);

	/**
	 * 
	 * 方法: findTReportEnvironmentLevelAll <br>
	 * 描述: 查询等级描述信息列表 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月3日 下午2:06:57
	 * 
	 * @return
	 */
	List<TReportEnvironmentLevel> findTReportEnvironmentLevelAll();
}