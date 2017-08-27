package cn.com.ddhj.mapper.adver;

import java.util.List;

import cn.com.ddhj.dto.adver.TAdvertisingDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.adver.TAdvertising;

public interface TAdvertisingMapper extends BaseMapper<TAdvertising, TAdvertisingDto> {

	/**
	 * 
	 * 方法: findUserAdver <br>
	 * 描述: 查询指定用户的广告 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年8月27日 上午12:18:43
	 * 
	 * @param dto
	 * @return
	 */
	List<TAdvertising> findUserAdver(TAdvertisingDto dto);
}