package cn.com.ddhj.job;

import cn.com.ddhj.annotation.Inject;
import cn.com.ddhj.base.BaseClass;
import cn.com.ddhj.service.ITradeService;

/**
 * 类: CarbonDealData <br>
 * 描述: 从中国碳币交易网定时抓取数据<br>
 * 作者: zht<br>
 * 时间: 2017年3月12日
 */
public class CarbonDealData extends BaseClass {
	private final static String url = "http://k.tanjiaoyi.com:8080/KDataController/getHouseDatasInAverage.do?jsoncallback=abc&lcnK=7b56aba5e6e2b29d01587bf76dd3aa95&brand=TAN&_=1489330729677";
	@Inject
	private ITradeService tradeService;
	public void grab() {
		tradeService.grabDealData(url);
	}
}
