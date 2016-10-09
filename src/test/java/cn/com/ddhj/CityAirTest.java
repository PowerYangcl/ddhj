package cn.com.ddhj;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.dto.landedProperty.LandLatLngDto;
import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.result.estateInfo.EData;
import cn.com.ddhj.service.ICityAirService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class CityAirTest extends BaseTest {

	@Autowired
	private ICityAirService service;

	@Autowired
	private TLandedPropertyMapper mapper;
	@Test
	public void getCityAir() {
		JSONObject obj = service.getCityNowAir("北京");
		System.out.println(obj.toJSONString());
	}

	public void getCityWeeksAir() {
		JSONArray array = service.getLastWeekAir("北京");
		System.out.println(array.toJSONString());
	}

	public void getCityPM() {
		JSONObject obj = service.getCityPM("北京");
		System.out.println(obj.toJSONString());
	}
	
	
	@Test
	public void getCityPM2() {
		LandLatLngDto dto = new LandLatLngDto(); 
		dto.setPage(Integer.valueOf(1));
		dto.setMinLat(String.valueOf("39.81030360277923"));
		dto.setMinLng(String.valueOf(116.43357410728034));
		dto.setMaxLat(String.valueOf("39.99000839722078"));
		dto.setMaxLng(String.valueOf(116.66781989271966));
		
		List<EData> list = mapper.findLandedPropertyAll(dto);
		
		
		System.out.println(mapper.findLandedPropertyAll(dto));
	}
}


























