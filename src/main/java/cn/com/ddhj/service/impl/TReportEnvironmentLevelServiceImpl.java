package cn.com.ddhj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.dto.TReportEnvironmentLevelDto;
import cn.com.ddhj.mapper.TReportEnvironmentLevelMapper;
import cn.com.ddhj.model.TReportEnvironmentLevel;
import cn.com.ddhj.service.ITReportEnvironmentLevelService;

/**
 * 
 * 类: TReportEnvironmentLevelServiceImpl <br>
 * 描述: 环境报告环境等级描述业务逻辑处理接口实现类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 下午11:44:46
 */
@Service
public class TReportEnvironmentLevelServiceImpl
		extends BaseServiceImpl<TReportEnvironmentLevel, TReportEnvironmentLevelMapper, TReportEnvironmentLevelDto>
		implements ITReportEnvironmentLevelService {

	@Autowired
	private TReportEnvironmentLevelMapper mapper;

	/**
	 * 
	 * 方法: findTReportEnvironmentLevelByType <br>
	 * 描述: TODO
	 * 
	 * @param dto
	 * @return
	 * @see cn.com.ddhj.service.ITReportEnvironmentLevelService#findTReportEnvironmentLevelByType(cn.com.ddhj.dto.TReportEnvironmentLevelDto)
	 */
	@Override
	public TReportEnvironmentLevel findTReportEnvironmentLevelByType(TReportEnvironmentLevelDto dto) {
		return mapper.findTReportEnvironmentLevelByType(dto);
	}

}
