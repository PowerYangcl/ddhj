package cn.com.ddhj.store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.ddhj.model.TUserAddress;
import cn.com.ddhj.service.store.ITUserAddressService;
import cn.com.ddhj.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TUserAddressTest {

	@Autowired
	private ITUserAddressService service;

	public void add() {
		TUserAddress entity = new TUserAddress();
		entity.setCode("111222");
		entity.setUserCode("12312312");
		entity.setName("测试");
		entity.setPhone("13329994211");
		entity.setAreaCode("122");
		entity.setProvinces("测试");
		entity.setStreet("错误是是是");
		entity.setCreateUser("add");
		entity.setCreateTime(DateUtil.getSysDateTime());
		service.insertSelective(entity);
	}

	@Test
	public void edit() {
		TUserAddress entity = new TUserAddress();
		entity.setCode("111222");
		entity.setUserCode("ssssss");
		entity.setName("测试-改");
		entity.setPhone("1测试szdfsadfsdf");
		entity.setAreaCode("123tde31");
		entity.setProvinces("13tdsasdsa");
		entity.setStreet("似懂非懂是的方式发生的发");
		entity.setUpdateTime(DateUtil.getSysDateTime());
		service.updateByCode(entity, "6a0a01f3378a459580b20ac89eada0fd");
	}
}
