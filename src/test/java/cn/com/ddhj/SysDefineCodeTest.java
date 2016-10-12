package cn.com.ddhj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.model.SysDefineCode;
import cn.com.ddhj.service.ISysDefineCodeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class SysDefineCodeTest extends BaseTest {

	@Autowired
	private ISysDefineCodeService service;

	@Test
	public void insert() {
		String code = WebHelper.getInstance().getUniqueCode("DC");
		SysDefineCode entity = new SysDefineCode();
		entity.setCode(code);
		entity.setName("消息类型");
		entity.setCreateUser("system");
		service.insertSelective(entity);
		SysDefineCode entity2 = new SysDefineCode();
		entity2.setCode(WebHelper.getInstance().getUniqueCode("DC"));
		entity2.setName("系统消息");
		entity2.setCreateUser("system");
		entity2.setParentCode(code);
		service.insertSelective(entity2);
		SysDefineCode entity3 = new SysDefineCode();
		entity3.setCode(WebHelper.getInstance().getUniqueCode("DC"));
		entity3.setName("活动消息");
		entity3.setCreateUser("system");
		entity3.setParentCode(code);
		service.insertSelective(entity3);
	}
}
