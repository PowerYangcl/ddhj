package cn.com.ddhj;

import java.util.ArrayList;
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
	
	public static void main(String[] args) {
		baiduEstateList();
	}
	
	public static void baiduEstateList(){
		String ak= "mwtIbCVKxq5l7TMLCpNrCnoOxGnWlotK";
		String url = "http://api.map.baidu.com/place/v2/search";
		
		List<Estate> elist = new ArrayList<Estate>();
		for(int i = 0 ; i < 30 ; i ++){
			Map<String, String> param = new HashMap<String, String>();
			param.put("ak", ak);					 
			param.put("q", "楼盘");					 
			param.put("region", "北京");				  		 
			param.put("output", "json");		 
			param.put("page_size", "20");
			param.put("page_num", String.valueOf(i)); 
			param.put("tag", "房地产,住宅区");
			String responseJson = PureNetUtil.get(url , param);  // ,写字楼,住宅区,宿舍
			JSONObject response = JSONObject.parseObject(responseJson);
			
			if(!response.getString("total").equals("400")){
				break;
			}
			
			List<Estate> arr = JSONArray.parseArray(response.getString("results"), Estate.class);
			elist.addAll(arr);
			
			System.out.println("i = " + i +"    " +responseJson); 
		}
		
		System.out.println(elist.size()); 
		System.out.println(elist.size()); 
		
	}
	
	
	
}


class Estate{
    private String name;
    private Location location;
    private String address;
    private String streetId;
    private String telephone;
    private int detail;
    private String uid;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStreetId() {
		return streetId;
	}
	public void setStreetId(String streetId) {
		this.streetId = streetId;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getDetail() {
		return detail;
	}
	public void setDetail(int detail) {
		this.detail = detail;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
}

class Location {
    private double lat;
    private double lng;
    
    public void setLat(double lat) {
         this.lat = lat;
     }
     public double getLat() {
         return lat;
     }
    public void setLng(double lng) {
         this.lng = lng;
     }
     public double getLng() {
         return lng;
     }
}






















