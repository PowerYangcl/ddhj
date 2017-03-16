package cn.com.ddhj.result.trade;

import java.util.List;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.trade.TTradeBalance;

public class TradeBalanceResult extends BaseResult {
	private List<TTradeBalance> objects;

	public List<TTradeBalance> getObjects() {
		return objects;
	}

	public void setObjects(List<TTradeBalance> objects) {
		this.objects = objects;
	}
}
