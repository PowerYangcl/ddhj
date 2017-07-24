package cn.com.ddhj.mapper;

import cn.com.ddhj.dto.store.TProductOrderDto;
import cn.com.ddhj.model.TProductOrder;

public interface TProductOrderMapper extends BaseMapper<TProductOrder, TProductOrderDto> {

	/**
	 * 
	 * 方法: findOrderDetailByCode <br>
	 * 描述: 根据订单编码获取订单详情 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年7月24日 上午11:04:52
	 * @param orderCode
	 * @return
	 */
	TProductOrder findOrderDetailByCode(String orderCode);
}