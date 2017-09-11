package cn.com.ddhj;

import java.util.Date;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.ddhj.service.user.ITUserStepService;
import cn.com.ddhj.util.SpringCtxUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class DailyUserStepCountPresent {
	@Autowired
	private ApplicationContext ctx;
	@Autowired
	private ITUserStepService stepService;
	@Test
	public void test() {
		SpringCtxUtil.setApplicationContext(ctx);
		String yesterday = DateUtil.formatDate(DateUtils.addDays(new Date(), -1), "yyyy-MM-dd");
		stepService.dailyCountStepPresent(yesterday);
	}
}
