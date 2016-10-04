package cn.com.ddhj.service;

import cn.com.ddhj.dto.TRubbishRecyclingDto;
import cn.com.ddhj.model.TRubbishRecycling;

/**
 * 
 * 类: ITRubbishRecyclingService <br>
 * 描述: 垃圾回收站业务逻辑处理接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月4日 下午12:07:09
 */
public interface ITRubbishRecyclingService extends IBaseService<TRubbishRecycling, TRubbishRecyclingDto> {

	/**
	 * 
	 * 方法: getTRubbishRecycling <br>
	 * 描述: 获取最近的垃圾回收站 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月4日 下午12:09:02
	 * 
	 * @param dto
	 * @return
	 */
	TRubbishRecycling getTRubbishRecycling(TRubbishRecyclingDto dto);

	/**
	 * 
	 * 方法: getDistance <br>
	 * 描述: 获取离垃圾场的距离 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月4日 下午12:24:38
	 * 
	 * @param dto
	 * @return
	 */
	Double getDistance(TRubbishRecyclingDto dto);
}
