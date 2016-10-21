package cn.com.ddhj.system;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.system.SysRoleMapper;
import cn.com.ddhj.model.system.SysRole;
import cn.com.ddhj.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class SysRoleTest extends BaseTest {

	@Autowired
	private SysRoleMapper mapper;

	@Test
	public void insert() {
		SysRole entity = new SysRole();
		entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
		entity.setCode(WebHelper.getInstance().getUniqueCode("SR"));
		entity.setName("系统管理员");
		entity.setCreateUser("system");
		entity.setCreateTime(DateUtil.getSysDateTime());
		entity.setUpdateUser("system");
		entity.setUpdateTime(DateUtil.getSysDateTime());
		mapper.insertSelective(entity);
	}
}
