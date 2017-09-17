package cn.com.ddhj;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.httpclient.util.DateUtil;
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
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		stepService.dailyCountStepPresent(f.format(c.getTime()));
	}
}
