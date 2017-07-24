package cn.com.ddhj.service.store;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.store.TProductOrderDto;
import cn.com.ddhj.model.TProductOrder;
import cn.com.ddhj.result.EntityResult;
import cn.com.ddhj.service.IBaseService;

public interface ITProductOrderService extends IBaseService<TProductOrder, TProductOrderDto> {

	/**
	 * 
	 * 方法: createOrder <br>
	 * 描述: 创建订单 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年7月24日 上午11:13:13
	 * 
	 * @param dto
	 * @param userToken
	 * @return
	 */
	BaseResult createOrder(TProductOrderDto dto, String userToken);

	/**
	 * 
	 * 方法: findOrderDetailByCode <br>
	 * 描述: 根据订单编码获取订单详情 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年7月24日 上午11:13:29
	 * 
	 * @param orderCode
	 * @return
	 */
	EntityResult findOrderDetailByCode(String orderCode);
}
