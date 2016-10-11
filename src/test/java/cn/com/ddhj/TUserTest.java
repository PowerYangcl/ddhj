package cn.com.ddhj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.dto.user.TUserDto;
import cn.com.ddhj.mapper.user.TUserMapper;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.result.tuser.LoginResult;
import cn.com.ddhj.result.tuser.RegisterResult;
import cn.com.ddhj.service.user.ITUserService;
import cn.com.ddhj.util.MD5Util;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TUserTest extends BaseTest {

	@Autowired
	private ITUserService service;
	@Autowired
	private TUserMapper mapper;

	public void register() {
		TUser entity = new TUser();
		entity.setPhone("13655422211");
		entity.setPassword(MD5Util.md5Hex("123456"));
		RegisterResult result = service.register(entity);
		System.out.println(JSONObject.toJSON(result));
	}

	public void login() {
		TUserDto dto = new TUserDto();
		dto.setPhone("13655422211");
		dto.setPassword("123456");
		LoginResult result = service.login(dto);
		System.out.println(JSONObject.toJSON(result));
	}
	
	public void logOut(){
		String uuid = "68423c774bd24815b577633dc494b56e";
		BaseResult result = service.logOut(uuid);
		System.out.println(JSONObject.toJSON(result));
	}
	
	@Test
	public void findTUserByUuid(){
		String uuid = "8c91581b84fd4e69bbf28363aa38b11a";
		System.out.println(mapper.findTUserByUuid(uuid));
	}
}
