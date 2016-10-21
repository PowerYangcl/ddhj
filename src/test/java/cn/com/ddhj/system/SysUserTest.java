package cn.com.ddhj.system;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.dto.system.SysUserDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.system.SysUserMapper;
import cn.com.ddhj.model.system.SysUser;
import cn.com.ddhj.util.DateUtil;
import cn.com.ddhj.util.MD5Util;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class SysUserTest extends BaseTest {

	@Autowired
	private SysUserMapper mapper;

	public void insert() {
		SysUser entity = new SysUser();
		entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
		entity.setCode(WebHelper.getInstance().getUniqueCode("SU"));
		entity.setUserName("admin");
		entity.setPassword(MD5Util.md5Hex("admin"));
		entity.setRoleCode("");
		entity.setCreateUser("system");
		entity.setCreateTime(DateUtil.getSysDateTime());
		entity.setUpdateUser("system");
		entity.setUpdateTime(DateUtil.getSysDateTime());
		mapper.insertSelective(entity);
	}

	public void login() {
		SysUserDto dto = new SysUserDto();
		dto.setUserName("admin");
		dto.setPassword(MD5Util.md5Hex("admin"));
		mapper.findSysUserForLogin(dto);
	}

	@Test
	public void update() {
		SysUser entity = new SysUser();
		entity.setCode("SU161021100002");
		entity.setPassword(MD5Util.md5Hex("admin"));
		mapper.updateByCode(entity);
	}
}
