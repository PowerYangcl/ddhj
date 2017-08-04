package cn.com.ddhj.result.trade;

import java.util.LinkedList;
import java.util.List;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.trade.TTradeDeal;

public class TradeDealChartResult extends BaseResult {
	List<CityTradeDeals> list = new LinkedList<CityTradeDeals>();
	
	public List<CityTradeDeals> getList() {
		return list;
	}
	public void setList(List<CityTradeDeals> list) {
		this.list = list;
	}

	public class CityTradeDeals {
		private String cityName;
		private List<TTradeDeal> deals = new LinkedList<TTradeDeal>();
		public String getCityName() {
			return cityName;
		}
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		public List<TTradeDeal> getDeals() {
			return deals;
		}
		public void setDeals(List<TTradeDeal> deals) {
			this.deals = deals;
		}
		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			if(!(obj instanceof CityTradeDeals)) {
				return false;
			}
			return cityName.equals(((CityTradeDeals)obj).getCityName());
		}
	}
}
