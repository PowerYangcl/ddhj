package cn.com.ddhj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.user.TUserLpFollowDto;
import cn.com.ddhj.result.tuser.FollowResult;
import cn.com.ddhj.service.user.ITUserLpFollowService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TUserLpFollowTest {

	@Autowired
	private ITUserLpFollowService service;

	public void insert() {
		String lpCode = "LP161004101471,LP161004101472,LP161004101473,LP161004101474,LP161004101475,LP161004101476,LP161004101477,LP161004101478,LP161004101479,LP161004101480";
		for (int i = 0; i < lpCode.split(",").length; i++) {
			String userToken = "4255756c4b2b4730a5f0600c6f003b13";
			service.insert(lpCode.split(",")[i], userToken);
		}
	}

	public void del() {
		TUserLpFollowDto dto = new TUserLpFollowDto();
		service.delFollow(dto, "4255756c4b2b4730a5f0600c6f003b13");
	}

	@Test
	public void data() {
		TUserLpFollowDto dto = new TUserLpFollowDto();
		dto.setLat("40.0830383300781");
		dto.setLng("116.41576385498");
		dto.setPageIndex(0);
		dto.setPageSize(10);
		FollowResult result = service.findFollowLpData(dto, "4255756c4b2b4730a5f0600c6f003b13");
		System.out.println(JSONObject.toJSON(result));
	}
}
