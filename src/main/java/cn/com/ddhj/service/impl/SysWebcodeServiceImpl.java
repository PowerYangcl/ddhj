package cn.com.ddhj.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.mapper.SysWebcodeMapper;
import cn.com.ddhj.model.SysWebcode;
import cn.com.ddhj.service.ISysWebcodeService;

/**
 * 
 * 类: SysWebcodeServiceImpl <br>
 * 描述: 系统编码表业务逻辑处理接口实现类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 上午10:38:12
 */
@Service
public class SysWebcodeServiceImpl extends BaseServiceImpl<SysWebcode, SysWebcodeMapper, BaseDto>
		implements ISysWebcodeService {

	@Autowired
	private SysWebcodeMapper mapper;

	/**
	 * 
	 * 方法: callUniqueCode <br>
	 * 描述: TODO
	 * 
	 * @param param
	 * @return
	 * @see cn.com.ddhj.service.ISysWebcodeService#callUniqueCode(java.util.Map)
	 */
	@Override
	public String callUniqueCode(Map<String, Object> param) {
		return mapper.callUniqueCode(param);
	}
}
