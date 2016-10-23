package cn.com.ddhj.mapper;

import java.util.List;
import java.util.Map;

import cn.com.ddhj.dto.TOrderDto;
import cn.com.ddhj.model.TOrder;

/**
 * 
 * 类: TOrderMapper <br>
 * 描述: 订单表数据库访问接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月7日 下午12:57:46
 */
public interface TOrderMapper extends BaseMapper<TOrder, TOrderDto> {

	/**
	 * 
	 * 方法: findOrderByComment <br>
	 * 描述: 查询用户最新购买记录，用于评价使用 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月10日 下午2:31:23
	 * 
	 * @param order
	 * @return
	 */
	TOrder findOrderByComment(TOrder order);
	
	/**
	 * 
	 * 方法: findOrderAll <br>
	 * 描述: 查询所有订单 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月21日 下午9:57:17
	 * @param dto
	 * @return
	 */
	List<Map<String, String>> findOrderAll(TOrderDto dto);

	public Integer deleteOne(Integer id); 
}