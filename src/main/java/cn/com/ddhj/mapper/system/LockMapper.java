package cn.com.ddhj.mapper.system;

import java.util.Map;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.BaseModel;

/**
 * 
 * 类: LockMapper <br>
 * 描述: 锁相关 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月26日 下午9:47:08
 */
public interface LockMapper extends BaseMapper<BaseModel, BaseDto> {

	/**
	 * 
	 * 方法: lock <br>
	 * 描述: 添加锁或解锁 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月26日 下午9:47:51
	 * 
	 * @param param
	 * @return
	 */
	String lock(Map<String, String> param);
}
