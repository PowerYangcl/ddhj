package cn.com.ddhj.adver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.dto.adver.TAdvertisingDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.model.adver.TAdvertising;
import cn.com.ddhj.result.EntityResult;
import cn.com.ddhj.service.adver.ITAdvertisingService;
import cn.com.ddhj.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TAdvertisingTest extends BaseTest {

	@Autowired
	private ITAdvertisingService service;

	public void insert() {
		for (int i = 0; i < 10; i++) {
			TAdvertising entity = new TAdvertising();
			entity.setUuid(WebHelper.getInstance().genUuid());
			entity.setAdCode(WebHelper.getInstance().getUniqueCode("AD"));
			entity.setPic("test");
			entity.setTitle("测试广告");
			entity.setCreateUser("insert");
			entity.setCreateTime(DateUtil.getSysDateTime());
			entity.setStartTime(DateUtil.getSysDateTime());
			entity.setEndTime(DateUtil.getSysDateTime());
			entity.setContent("测试数据");
			service.insertSelective(entity);
		}
	}

	public void edit() {
		TAdvertising entity = new TAdvertising();
		entity.setUuid(WebHelper.getInstance().genUuid());
		entity.setAdCode("AD170826100007");
		entity.setPic("test");
		entity.setTitle("测试广告");
		entity.setUpdateUser("edit");
		entity.setUpdateTime(DateUtil.getSysDateTime());
		entity.setStartTime(DateUtil.getSysDateTime());
		entity.setEndTime(DateUtil.getSysDateTime());
		entity.setContent("测试数据");
		service.updateByCode(entity);
	}

	public void sel() {
		TAdvertising entity = service.selectByCode("AD170826100007");
		System.out.println(entity.getAdCode());
	}

	public void del() {
		BaseResult result = service.deleteByCode("AD170826100007");
		System.out.println(JSON.toJSON(result));
	}

	@Test
	public void findUserAdver() {
		TAdvertisingDto dto = new TAdvertisingDto();
		dto.setUserCode("U161009100001");
		dto.setAdCode("AD170826100009");
		EntityResult result = service.findUserAdver(dto);
		System.out.println(JSON.toJSON(result));
	}
}
