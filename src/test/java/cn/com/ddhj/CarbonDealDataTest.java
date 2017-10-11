package cn.com.ddhj;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.dto.trade.TTradeDealDto;
import cn.com.ddhj.dto.trade.TTradeOrderDto;
import cn.com.ddhj.model.TOrderRecharge;
import cn.com.ddhj.model.trade.TTradeOrder;
import cn.com.ddhj.result.carbon.CarbonRechargeResult;
import cn.com.ddhj.result.trade.TradeBalanceResult;
import cn.com.ddhj.result.trade.TradeCityResult;
import cn.com.ddhj.result.trade.TradeDealResult;
import cn.com.ddhj.result.trade.TradeOrderResult;
import cn.com.ddhj.result.trade.TradePriceAvaiAmountResult;
import cn.com.ddhj.service.ITradeService;
import cn.com.ddhj.service.user.ITUserCarbonOperationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class CarbonDealDataTest extends BaseTest {
	@Autowired
	private ITradeService service;
	@Autowired
	private ITUserCarbonOperationService userCarbonService;
	
	@Test
	public void grab() {
		service.grabDealData("http://k.tanjiaoyi.com:8080/KDataController/getHouseDatasInAverage.do?jsoncallback=abc&lcnK=7b56aba5e6e2b29d01587bf76dd3aa95&brand=TAN&_=1489330729677");
	}
	
	@Test
	public void getAllTradeCity() {
		TradeCityResult result = service.queryAllTradeCity();
		System.out.println(JSON.toJSON(result));
	}
	
	@Test
	public void queryDealsByCityId() {
		TTradeDealDto dto = new TTradeDealDto();
		dto.setCityId("aae0171545b0ad680145b0ad68ee0000");
		dto.setPageIndex(0);
		dto.setPageSize(100);
		TradeDealResult result = service.queryDealsByCityId(dto);
		System.out.println(JSON.toJSON(result));
	}
	
	@Test
	public void sendTradeOrder() {
		TTradeOrder order = new TTradeOrder();
		order.setBuySell("B");
		order.setAmount(1000);
		order.setPrice(new BigDecimal(10.5));
		order.setObjectCode("aae0171545b0ad680145b0ad68ee0000");
		BaseResult result = service.sendTradeOrder(order, "2aa3530a3361472b91e236caae8a6d10");
		System.out.println(JSON.toJSON(result));
	}
	
	@Test
	public void sendTradeOrderSell() {
		TTradeOrder orderSell = new TTradeOrder();
		orderSell.setBuySell("C");
		orderSell.setAmount(500);
		orderSell.setPrice(new BigDecimal(10.5));
		orderSell.setObjectCode("aae0171545b0ad680145b0ad68ee0000");
		BaseResult resultSell = service.sendTradeOrder(orderSell, "2aa3530a3361472b91e236caae8a6d10");
		System.out.println(JSON.toJSON(resultSell));
	}
	@Test
	public void getCurrentPriceAndAvailableAmount() {
		String objectCode = "aae0171545b0ad680145b0ad68ee0000";
		TTradeDealDto dto = new TTradeDealDto();
		dto.setCityId(objectCode);
		TradePriceAvaiAmountResult result = service.getCurrentPriceAndAvailableAmount(dto, "2aa3530a3361472b91e236caae8a6d10");
		System.out.println(JSON.toJSON(result));
	}
	
	@Test
	public void getUserBalance() {
		TradeBalanceResult result = service.getUserBalance("2aa3530a3361472b91e236caae8a6d10");
		System.out.println(JSON.toJSON(result));
	}
	
	@Test
	public void getUserTradeOrder() {
		TTradeOrderDto dto = new TTradeOrderDto();
//		dto.setBeginDate("2017-01-01 00:00:00");
//		dto.setEndDate("2017-03-15 23:59:59");
		TradeOrderResult result = service.getUserTradeOrder(dto, "2aa3530a3361472b91e236caae8a6d10");
		System.out.println("userTradeOrder:" + JSON.toJSON(result));
	}
	
	@Test
	public void createRechargeOrder() {
		TTradeOrderDto dto = new TTradeOrderDto();
//		dto.setBeginDate("2017-01-01 00:00:00");
//		dto.setEndDate("2017-03-15 23:59:59");
		TOrderRecharge recharge = new TOrderRecharge();
		recharge.setCarbonMoney(new BigDecimal(3.4));
		recharge.setPayPrice(new BigDecimal(5.6));
		CarbonRechargeResult result = userCarbonService.carbonRecharge("2aa3530a3361472b91e236caae8a6d10", recharge);
		System.out.println("userRecharge:" + JSON.toJSON(result));
	}
}
