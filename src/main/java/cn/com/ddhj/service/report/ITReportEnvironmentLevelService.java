package cn.com.ddhj.service.report;

import cn.com.ddhj.dto.TReportEnvironmentLevelDto;
import cn.com.ddhj.model.report.TReportEnvironmentLevel;
import cn.com.ddhj.service.IBaseService;

/**
 * 
 * 类: ITReportEnvironmentLevelService <br>
 * 描述: 环境报告环境等级描述业务逻辑处理接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 下午11:37:56
 */
public interface ITReportEnvironmentLevelService
		extends IBaseService<TReportEnvironmentLevel, TReportEnvironmentLevelDto> {

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
