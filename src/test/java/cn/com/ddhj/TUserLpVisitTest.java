package cn.com.ddhj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.user.TUserLpVisitDto;
import cn.com.ddhj.result.tuser.VisitResult;
import cn.com.ddhj.service.user.ITUserLpVisitService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TUserLpVisitTest {

	@Autowired
	private ITUserLpVisitService service;

	public void insert() {
		String lpCode = "LP161004101471,LP161004101472,LP161004101473,LP161004101474,LP161004101475,LP161004101476,LP161004101477,LP161004101478,LP161004101479,LP161004101480";
		for (int i = 0; i < lpCode.split(",").length; i++) {
			String userToken = "4255756c4b2b4730a5f0600c6f003b13";
			service.insert(lpCode.split(",")[i], userToken);
		}
	}

	public void del() {
		TUserLpVisitDto dto = new TUserLpVisitDto();
		service.delVisit(dto, "4255756c4b2b4730a5f0600c6f003b13");
	}

	@Test
	public void data() {
		TUserLpVisitDto dto = new TUserLpVisitDto();
		dto.setLat("40.0830383300781");
		dto.setLng("116.41576385498");
		dto.setPageIndex(0);
		dto.setPageSize(10);
		VisitResult result = service.findVisitLpData(dto, "4255756c4b2b4730a5f0600c6f003b13");
		System.out.println(JSONObject.toJSON(result));
	}
}
