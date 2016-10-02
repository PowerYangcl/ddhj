package cn.com.ddhj.mapper;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.model.TReportTemplate;

/**
 * 
 * 类: TReportTemplateMapper <br>
 * 描述: 报告模板表数据库访问接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 下午11:34:14
 */
public interface TReportTemplateMapper extends BaseMapper<TReportTemplate, BaseDto> {

	/**
	 * 
	 * 方法: findReportTemplateByType <br>
	 * 描述: 根据类型访问报告模板内容 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月2日 下午11:33:55
	 * 
	 * @param type
	 * @return
	 */
	TReportTemplate findReportTemplateByType(String type);
}