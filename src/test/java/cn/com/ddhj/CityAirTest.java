package cn.com.ddhj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.dto.landedProperty.LandLatLngDto;
import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.result.estateInfo.EData;
import cn.com.ddhj.result.estateInfo.EstateResult;
import cn.com.ddhj.service.ICityAirService;
import cn.com.ddhj.util.PureNetUtil;

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
	
	
	@Test
	public void baiduEstateList(){
//		http://api.map.baidu.com/place/v2/search?q=饭店&region=北京&output=json&ak={您的密钥}
		String ak= "mwtIbCVKxq5l7TMLCpNrCnoOxGnWlotK";
		String url = "http://api.map.baidu.com/place/v2/search";
		JSONObject result = new JSONObject();
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("ak", ak);					 
		param.put("q", "楼盘");					 
		param.put("region", "北京");				 
//		param.put("page", String.valueOf(page)); 				 
		param.put("output", "json");		 
		 
		String responseJson = PureNetUtil.get(url , param);
		if (responseJson != null && !"".equals(responseJson)) {
//			EstateResult cr = JSON.parseObject(responseJson, EstateResult.class); 
//			if(cr.getResultcode().equals("200")){
//				result.put("code" , "1");
//				result.put("msg" , "SUCCESS");
//				result.put("list" , cr.getResult()); 
//			}else{
//				result.put("code" , "0");
//				result.put("msg" , cr.getReason() + "|请联系后台开发人员调试");
//			}
			
		}else{
			result.put("code" , "0");
			result.put("msg" , "聚合接口响应数据为空");
		}
		
		System.out.println(responseJson); 
	}
	
	
	
}


























