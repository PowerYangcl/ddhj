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
	 * 
	 * @param dto
	 * @return
	 */
	List<Map<String, String>> findOrderAll(TOrderDto dto);

	/**
	 * 根据楼盘报告编号和用户编号查询楼盘报告订单信息
	 * 
	 * @author zht
	 * @param reportCode
	 * @param userCode
	 * @return
	 */
	List<TOrder> findOrderByReportCodeAndUserCode(TOrderDto dto);

	public Integer deleteOne(Integer id);

	public Integer updateOrderStatus(TOrder e);

	/**
	 * 
	 * 方法: findOrderLPAndCreateUser <br>
	 * 描述: 查询已支付订单的楼盘编码和创建人 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年8月19日 上午11:00:35
	 * 
	 * @return
	 */
	public List<TOrder> findOrderLPAndCreateUser();

	/**
	 * 
	 * 方法: findOrderLPAndCreateUserAll <br>
	 * 描述: 查询所有已支付订单的楼盘编码和创建人 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年10月18日 下午11:16:17
	 * 
	 * @return
	 */
	List<TOrder> findOrderLPAndCreateUserAll();
}
