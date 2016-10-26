package cn.com.ddhj.mapper.report;

import java.util.List;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.report.TReportLog;

/**
 * 
 * 类: TReportLogMapper <br>
 * 描述: 报告日志表 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月25日 下午2:39:16
 */
public interface TReportLogMapper extends BaseMapper<TReportLog, BaseDto> {

	/**
	 * 
	 * 方法: batchInsertLog <br>
	 * 描述: 批量添加日志 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月25日 下午3:01:35
	 * 
	 * @param list
	 * @return
	 */
	int batchInsertLog(List<TReportLog> list);
}