package cn.com.ddhj;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.dto.TLandedPropertyDto;
import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.service.ITLandedPropertyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TLandedPropertyTest extends BaseTest {

	@Autowired
	private ITLandedPropertyService service;
	@Autowired
	private TLandedPropertyMapper mapper;

	public void insert() {
		service.insertDataFromAPI("天津");
	}

	public void select() {
		TLandedProperty model = service.selectByCode("LP161004101471");
		System.out.println(JSONObject.toJSON(model));
	}

	public void ii() {
		Double afforest = Double.valueOf("33.12%".substring(0, "33.12%".indexOf("%")));
		System.out.println(afforest);
	}

	@Test
	public void getDataForUser() {
		List<String> list = new ArrayList<String>();
		list.add("LP161004101471");
		TLandedPropertyDto dto = new TLandedPropertyDto();
		dto.setCodes(list);
		dto.setPageSize(10);
		dto.setStart(0);
		List<TLandedProperty> lp = mapper.findLpForUser(dto);
		System.out.println(lp.size());
		System.out.println(mapper.findLpForUserCount(dto));
	}
}
