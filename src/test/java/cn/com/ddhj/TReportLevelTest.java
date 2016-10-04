package cn.com.ddhj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.model.report.TReportLevel;
import cn.com.ddhj.service.report.ITReportLevelService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TReportLevelTest extends BaseTest {

	@Autowired
	private ITReportLevelService service;

	@Test
	public void insert() {
		TReportLevel model = new TReportLevel();
		model.setCode(WebHelper.getInstance().getUniqueCode("RL"));
		model.setName("普通");
		model.setCreateUser("system");
		service.insertSelective(model);
		TReportLevel model2 = new TReportLevel();
		model2.setCode(WebHelper.getInstance().getUniqueCode("RL"));
		model2.setName("高级");
		model2.setCreateUser("system");
		service.insertSelective(model2);
		TReportLevel model3 = new TReportLevel();
		model3.setCode(WebHelper.getInstance().getUniqueCode("RL"));
		model3.setName("专业");
		model3.setCreateUser("system");
		service.insertSelective(model3);
	}
}
