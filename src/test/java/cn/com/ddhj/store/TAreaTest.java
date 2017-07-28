package cn.com.ddhj.store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import cn.com.ddhj.result.DataResult;
import cn.com.ddhj.service.store.ITAreaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TAreaTest {

	@Autowired
	private ITAreaService service;

	@Test
	public void findDataByParent() {
		DataResult result = service.findDataByParent("0");
		System.out.println(JSON.toJSON(result));
	}
}
