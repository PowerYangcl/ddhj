package cn.com.ddhj.mapper;

import java.util.List;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.model.TProductOrderDetail;

public interface TProductOrderDetailMapper extends BaseMapper<TProductOrderDetail, BaseDto> {

	/**
	 * 
	 * 方法: batchInsert <br>
	 * 描述: 批量添加 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年7月24日 上午10:36:24
	 * 
	 * @param list
	 * @return
	 */
	int batchInsert(List<TProductOrderDetail> list);

	/**
	 * 
	 * 方法: findOrderProductDetail <br>
	 * 描述: 根据订单编码查询订单商品列表 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年7月24日 上午11:08:46
	 * 
	 * @param orderCode
	 * @return
	 */
	List<TProductOrderDetail> findOrderProductDetail(String orderCode);
}