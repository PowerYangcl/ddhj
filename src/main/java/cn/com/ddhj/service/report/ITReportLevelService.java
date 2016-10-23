package cn.com.ddhj.service.report;

import java.util.List;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.model.report.TReportLevel;
import cn.com.ddhj.service.IBaseService;

/**
 * 
 * 类: ITReportLevelService <br>
 * 描述: 环境报告等级业务处理接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月4日 下午6:52:08
 */
public interface ITReportLevelService extends IBaseService<TReportLevel, BaseDto> {

	/**
	 * 
	 * 方法: findLevelAll <br>
	 * 描述: 获取所有报告等级 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月23日 下午7:42:38
	 * @return
	 */
	List<TReportLevel> findLevelAll();
}
