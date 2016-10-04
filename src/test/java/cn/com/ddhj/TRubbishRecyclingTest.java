package cn.com.ddhj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.TRubbishRecyclingDto;
import cn.com.ddhj.mapper.TRubbishRecyclingMapper;
import cn.com.ddhj.model.TRubbishRecycling;
import cn.com.ddhj.service.ITRubbishRecyclingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TRubbishRecyclingTest {

	@Autowired
	private TRubbishRecyclingMapper mapper;

	@Autowired
	private ITRubbishRecyclingService service;

	public void getMaxTRubbishRecycling() {
		TRubbishRecyclingDto dto = new TRubbishRecyclingDto();
		dto.setCity("北京");
		dto.setLat("39.9091529846191");
		dto.setLng("116.244064331055");
		TRubbishRecycling model = mapper.getMaxTRubbishRecycling(dto);
		System.out.println(JSONObject.toJSON(model));
		// {"id":56,"address":"北京市房山区大石窝镇半壁店村","name":"房山半壁店垃圾卫生填埋场","lng":"115.83231","lat":"39.552854","city":"北京"}
	}

	public void getMinTRubbishRecycling() {
		TRubbishRecyclingDto dto = new TRubbishRecyclingDto();
		dto.setCity("北京");
		dto.setLat("39.9091529846191");
		dto.setLng("116.244064331055");
		TRubbishRecycling model = mapper.getMinTRubbishRecycling(dto);
		System.out.println(JSONObject.toJSON(model));
		// {"id":66,"address":"北京市朝阳区安立路６８","name":"朝阳高安屯焚烧厂","lng":"116.408935","lat":"39.99802","city":"北京"}
	}

	public void getTRubbishRecycling() {
		TRubbishRecyclingDto dto = new TRubbishRecyclingDto();
		dto.setCity("北京");
		dto.setLat("39.9091529846191");
		dto.setLng("116.244064331055");
		TRubbishRecycling model = service.getTRubbishRecycling(dto);
		System.out.println(JSONObject.toJSON(model));
	}

	@Test
	public void getLL() {
		TRubbishRecyclingDto dto = new TRubbishRecyclingDto();
		dto.setCity("北京");
		dto.setLat("39.9091529846191");
		dto.setLng("116.244064331055");
		Double ll = service.getDistance(dto);
		System.out.println(ll);
	}
}
