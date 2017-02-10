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
//		String code = WebHelper.getInstance().getUniqueCode("DC");
//		SysDefineCode entity = new SysDefineCode();
//		entity.setCode(code);
//		entity.setName("消息类型");
//		entity.setCreateUser("system");
//		service.insertSelective(entity);
//		SysDefineCode entity2 = new SysDefineCode();
//		entity2.setCode(WebHelper.getInstance().getUniqueCode("DC"));
//		entity2.setName("系统消息");
//		entity2.setCreateUser("system");
//		entity2.setParentCode(code);
//		service.insertSelective(entity2);
//		SysDefineCode entity3 = new SysDefineCode();
//		entity3.setCode(WebHelper.getInstance().getUniqueCode("DC"));
//		entity3.setName("活动消息");
//		entity3.setCreateUser("system");
//		entity3.setParentCode(code);
//		service.insertSelective(entity3);
		
		String code = WebHelper.getInstance().getUniqueCode("DC");
		SysDefineCode entity = new SysDefineCode();
		entity.setCode(code);
		entity.setName("炭币相关");
		entity.setCreateUser("system");
		service.insertSelective(entity);
		SysDefineCode entity2 = new SysDefineCode();
		entity2.setCode(WebHelper.getInstance().getUniqueCode("DC"));
		entity2.setParentCode(entity.getCode());
		entity2.setName("炭币收入类型");
		entity2.setCreateUser("system");
		service.insertSelective(entity2);
		SysDefineCode entity3 = new SysDefineCode();
		entity3.setCode(WebHelper.getInstance().getUniqueCode("DC"));
		entity3.setParentCode(entity.getCode());
		entity3.setName("炭币支出类型");
		entity3.setCreateUser("system");
		service.insertSelective(entity3);
		
		/**
		 * 收入类型
		 */
		SysDefineCode entity4 = new SysDefineCode();
		entity4.setCode(WebHelper.getInstance().getUniqueCode("DC"));
		entity4.setParentCode(entity2.getCode());
		entity4.setName("步行炭币");
		entity4.setCreateUser("system");
		service.insertSelective(entity4);
		SysDefineCode entity5 = new SysDefineCode();
		entity5.setCode(WebHelper.getInstance().getUniqueCode("DC"));
		entity5.setParentCode(entity2.getCode());
		entity5.setName("新能源里程炭币");
		entity5.setCreateUser("system");
		service.insertSelective(entity5);
		SysDefineCode entity6 = new SysDefineCode();
		entity6.setCode(WebHelper.getInstance().getUniqueCode("DC"));
		entity6.setParentCode(entity2.getCode());
		entity6.setName("购买炭币");
		entity6.setCreateUser("system");
		service.insertSelective(entity6);
		/**
		 * 支出类型
		 */
		SysDefineCode entity7 = new SysDefineCode();
		entity7.setCode(WebHelper.getInstance().getUniqueCode("DC"));
		entity7.setParentCode(entity3.getCode());
		entity7.setName("兑换环境质量报告");
		entity7.setCreateUser("system");
		service.insertSelective(entity7);
		SysDefineCode entity8 = new SysDefineCode();
		entity8.setCode(WebHelper.getInstance().getUniqueCode("DC"));
		entity8.setParentCode(entity3.getCode());
		entity8.setName("兑换奖品");
		entity8.setCreateUser("system");
		service.insertSelective(entity8);
		SysDefineCode entity9 = new SysDefineCode();
		entity9.setCode(WebHelper.getInstance().getUniqueCode("DC"));
		entity9.setParentCode(entity3.getCode());
		entity9.setName("开户炒碳");
		entity9.setCreateUser("system");
		service.insertSelective(entity9);
	}
}
