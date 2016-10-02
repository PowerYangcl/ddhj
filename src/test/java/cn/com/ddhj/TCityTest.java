package cn.com.ddhj;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.model.TCity;
import cn.com.ddhj.service.ICityAirService;
import cn.com.ddhj.service.ITCityService;
import cn.com.ddhj.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TCityTest extends BaseTest {

	@Autowired
	private ITCityService tcityService;
	@Autowired
	private ICityAirService cityAirService;

	@Test
	public void insert() {
		JSONArray array = cityAirService.getAirCityData();
		List<TCity> list = new ArrayList<>();
		if (array != null && array.size() > 0) {
			for (int i = 0; i < array.size(); i++) {
				JSONObject obj = array.getJSONObject(i);
				TCity model = JSONObject.toJavaObject(obj, TCity.class);
				model.setUuid(UUID.randomUUID().toString().replace("-", ""));
				model.setCityCode(WebHelper.getInstance().getUniqueCode("CT"));
				model.setAir(0);
				model.setPm(1);
				model.setCreateUser("system");
				model.setCreateTime(DateUtil.getSysDateTime());
				model.setUpdateUser("system");
				model.setUpdateTime(DateUtil.getSysDateTime());
				list.add(model);
			}
		}
		tcityService.batchInsertCity(list);
	}
}
