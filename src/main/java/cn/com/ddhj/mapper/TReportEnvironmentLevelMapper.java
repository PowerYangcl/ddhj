package cn.com.ddhj.mapper;

import cn.com.ddhj.dto.TReportEnvironmentLevelDto;
import cn.com.ddhj.model.TReportEnvironmentLevel;

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
	public TReportEnvironmentLevel findTReportEnvironmentLevelByType(TReportEnvironmentLevelDto dto);
}