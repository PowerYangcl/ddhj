package cn.com.ddhj.mapper.trade;

import java.util.List;

import cn.com.ddhj.dto.trade.TTradeOrderDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.trade.TTradeOrder;

/**
 * 
 * 类: TTradeOrderMapper 
 * 描述: 交易订单接口
 * 作者: zht
 * 时间: 2017年3月8日 下午10:03:48 
 *
 */
public interface TTradeOrderMapper extends BaseMapper<TTradeOrder, TTradeOrderDto> { 
	/**
	 * 
	 * 方法: selectByUserCode 
	 * 参数: userCode:用户编号
	 * 描述: 查询用户所有的委托单,按委托时间倒序查询 
	 * 作者: zht
	 * 时间: 2017年3月8日 下午11:42:06 
	 * 
	 * @param userCode
	 * @return
	 */
	List<TTradeOrder> selectByUserCode(String userCode);
}
