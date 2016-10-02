package cn.com.ddhj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.mapper.TReportTemplateMapper;
import cn.com.ddhj.model.TReportTemplate;
import cn.com.ddhj.service.ITReportTemplateService;

/**
 * 
 * 类: TReportTemplateServiceImpl <br>
 * 描述: 报告模板表业务逻辑处理接口实现类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 下午11:41:00
 */
public class TReportTemplateServiceImpl extends BaseServiceImpl<TReportTemplate, TReportTemplateMapper, BaseDto>
		implements ITReportTemplateService {

	@Autowired
	private TReportTemplateMapper mapper;

	/**
	 * 
	 * 方法: findReportTemplateByType <br>
	 * 描述: TODO
	 * 
	 * @param type
	 * @return
	 * @see cn.com.ddhj.service.ITReportTemplateService#findReportTemplateByType(java.lang.String)
	 */
	@Override
	public TReportTemplate findReportTemplateByType(String type) {
		return mapper.findReportTemplateByType(type);
	}

}
