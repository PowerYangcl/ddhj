package cn.com.ddhj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.ddhj.base.BaseClass;
import cn.com.ddhj.service.ITradeService;

@Controller
@RequestMapping("trade/")
public class TradeController extends BaseClass {
//	//交易相关
//	else if("trade_place_order".equals(api.getApiTarget())) {
//		//委托下单接口
//		return null;
//	} else if("trade_query_order".equals(api.getApiTarget())) {
//		//查询委托接口
//		return null;
//	} else if("trade_cancel_order".equals(api.getApiTarget())) {
//		//查询委托接口
//		return null;
//	} else if("trade_query_deal".equals(api.getApiTarget())) {
//		//查询成交接口
//		return null;
//	} else if("trade_query_position".equals(api.getApiTarget())) {
//		//查询用户持仓接口
//		return null;
//	} else if("trade_query_tobject".equals(api.getApiTarget())) {
//		//查询交易标的接口
//		return null;
//	}
	@Autowired
	private ITradeService tradeService;
	
	@RequestMapping("grab")
	public void grab() {
		tradeService.grabDealData("http://k.tanjiaoyi.com:8080/KDataController/getHouseDatasInAverage.do?jsoncallback=abc&lcnK=7b56aba5e6e2b29d01587bf76dd3aa95&brand=TAN&_=1489330729677");
	}
}
