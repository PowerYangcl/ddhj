package cn.com.ddhj.mapper;

import java.util.List;

import cn.com.ddhj.dto.store.TProductOrderDto;
import cn.com.ddhj.model.TProductOrder;
import cn.com.ddhj.result.ProductOrderResult;

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

	/**
	 * @description: 返回指定用户的订单列表信息 
	 * 
	 * @param buyerCode
	 * @return
	 * @author Yangcl 
	 * @date 2017年7月27日 上午11:05:19 
	 * @version 1.0.0.1
	 */
	public List<ProductOrderResult> findProductOrderList(String buyerCode); 
}