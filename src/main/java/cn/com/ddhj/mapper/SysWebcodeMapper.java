package cn.com.ddhj.mapper;

import java.util.Map;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.model.SysWebcode;

/**
 * 
 * 类: SysWebcodeMapper <br>
 * 描述: 系统编码表数据库访问接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 上午9:55:40
 */
public interface SysWebcodeMapper extends BaseMapper<SysWebcode, BaseDto> {

	/**
	 * 
	 * 方法: callUniqueCode <br>
	 * 描述: 获取唯一编码 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月2日 上午9:55:25
	 * 
	 * @param param
	 * @return
	 */
	String callUniqueCode(Map<String, Object> param);
}