package cn.com.ddhj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.service.ICityAirService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class CityAirTest {

	@Autowired
	private ICityAirService service;

	public void getCityAir() {
		JSONObject obj = service.getCityAir("北京");
		System.out.println(obj.toJSONString());
	}

	@Test
	public void getCityPM() {
		JSONObject obj = service.getCityPM("北京");
		System.out.println(obj.toJSONString());
	}
}