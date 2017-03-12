package cn.com.ddhj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.service.ITradeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class CarbonDealData extends BaseTest {
	@Autowired
	private ITradeService service;
	
	@Test
	public void grab() {
		service.grabDealData("http://k.tanjiaoyi.com:8080/KDataController/getHouseDatasInAverage.do?jsoncallback=abc&lcnK=7b56aba5e6e2b29d01587bf76dd3aa95&brand=TAN&_=1489330729677");
	}
}
