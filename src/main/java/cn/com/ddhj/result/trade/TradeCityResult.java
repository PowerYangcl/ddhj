package cn.com.ddhj.result.trade;

import java.util.List;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.trade.TTradeCity;

public class TradeCityResult extends BaseResult {
	private List<TTradeCity> citys;

	public List<TTradeCity> getCitys() {
		return citys;
	}

	public void setCitys(List<TTradeCity> citys) {
		this.citys = citys;
	}
}
