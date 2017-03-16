package cn.com.ddhj.result.trade;

import java.util.List;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.trade.TTradeOrder;

/**
 * 
 * 类: TradeOrderResult <br>
 * 描述: 按userCode,成交（委托)开始或结束时间查询交易成交(委托)<br>
 * 作者: 海涛<br>
 * 时间: 2017年3月16日
 */
public class TradeOrderResult extends BaseResult {
	private List<TTradeOrder> orders;

	public List<TTradeOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<TTradeOrder> orders) {
		this.orders = orders;
	}
}
