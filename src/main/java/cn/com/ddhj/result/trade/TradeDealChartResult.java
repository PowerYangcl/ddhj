package cn.com.ddhj.result.trade;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.trade.TTradeDeal;

public class TradeDealChartResult extends BaseResult {
	Map<String, List<TTradeDeal>> deals = new ConcurrentHashMap<String, List<TTradeDeal>>();

	public Map<String, List<TTradeDeal>> getDeals() {
		return deals;
	}

	public void setDeals(Map<String, List<TTradeDeal>> deals) {
		this.deals = deals;
	}
}
