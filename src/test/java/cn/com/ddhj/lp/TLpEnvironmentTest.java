package cn.com.ddhj.lp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.service.lp.ITLpEnvironmentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TLpEnvironmentTest extends BaseTest{

	@Autowired
	private ITLpEnvironmentService service;

	@Test
	public void batchInsert() {
		BaseResult result = service.batchInsert();
		System.out.println(JSON.toJSON(result));
	}
}
