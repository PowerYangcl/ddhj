package cn.com.ddhj.service;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.trade.TTradeDealDto;
import cn.com.ddhj.model.trade.TTradeOrder;
import cn.com.ddhj.result.trade.TradeCityResult;
import cn.com.ddhj.result.trade.TradeDealResult;
import cn.com.ddhj.result.trade.TradePriceAvaiAmountResult;

/**
 * 
 * 类: ITTradeService <br>
 * 描述: 碳交易接口<br>
 * 作者: zht<br>
 * 时间: 2017年3月12日
 */
public interface ITradeService {
	
	/**
	 * 
	 * 方法: grabDealData<br>
	 * 描述: 定时从中国碳交易网抓取行情数据<br>
	 * 作者: zht<br>
	 * 时间: 2017年3月12日 下午10:49:01
	 * 
	 * @param url 数据抓取URL 
	 * @return 1.成功  0.失败
	 */
	public int grabDealData(String url);
	
	/**
	 * 
	 * 方法: queryAllTradeCity<br>
	 * 描述: 查询所有的交易城市<br>
	 * 作者: zht<br>
	 * 时间: 2017年3月14日 上午10:53:51
	 * 
	 * @param 
	 * @return
	 */
	public TradeCityResult queryAllTradeCity();
	
	/**
	 * 
	 * 方法: queryDealsByCityId<br>
	 * 描述: 按城市Id分页查询该城市所有碳交易记录,按交易日期倒序,无城市ID时查询所有城市<br>
	 * 作者: zht<br>
	 * 时间: 2017年3月15日 下午9:35:05
	 * 
	 * @param 
	 * @return
	 */
	public TradeDealResult queryDealsByCityId(TTradeDealDto dto);
	
	/**
	 * 
	 * 方法: sendTradeOrder<br>
	 * 描述: 接受用户的委买和委卖单<br>
	 * 作者: zht<br>
	 * 时间: 2017年3月16日 上午12:34:57
	 * 
	 * @param 
	 * @return
	 */
	public BaseResult sendTradeOrder(TTradeOrder order, String userToken);
	
	
	/**
	 * 
	 * 方法: getCurrentPriceAndAvailableAmount<br>
	 * 描述: 获取当前交易标的价格和可买/可卖数量<br>
	 * 作者: 海涛<br>
	 * 时间: 2017年3月16日 下午2:41:00
	 * 
	 * @param 
	 * @return
	 */
	public TradePriceAvaiAmountResult getCurrentPriceAndAvailableAmount(TTradeDealDto dto, String userToken);
	
}