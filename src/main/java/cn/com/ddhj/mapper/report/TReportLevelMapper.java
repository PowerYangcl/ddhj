package cn.com.ddhj.mapper.report;

import java.util.List;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.report.TReportLevel;

/**
 * 
 * 类: TReportLevelMapper <br>
 * 描述: 环境报告等级数据库访问接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月4日 下午6:49:09
 */
public interface TReportLevelMapper extends BaseMapper<TReportLevel, BaseDto> {

	/**
	 * 
	 * 方法: findEntityAll <br>
	 * 描述: 获取所有等级列表 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月4日 下午9:52:30
	 * 
	 * @return
	 */
	List<TReportLevel> findEntityAll();
}