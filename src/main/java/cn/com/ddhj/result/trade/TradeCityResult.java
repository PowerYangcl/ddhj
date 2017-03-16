package cn.com.ddhj.result.trade;

import java.util.List;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.trade.TTradeCity;

/**
 * 
 * 类: TradeCityResult <br>
 * 描述: 分页查询所有支持碳交易的城市<br>
 * 作者: 海涛<br>
 * 时间: 2017年3月16日
 */
public class TradeCityResult extends BaseResult {
	private List<TTradeCity> citys;

	public List<TTradeCity> getCitys() {
		return citys;
	}

	public void setCitys(List<TTradeCity> citys) {
		this.citys = citys;
	}
}
