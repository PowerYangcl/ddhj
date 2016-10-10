package cn.com.ddhj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.dto.TLpCommentDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TUserLoginMapper;
import cn.com.ddhj.model.TLpComment;
import cn.com.ddhj.model.TUserLogin;
import cn.com.ddhj.result.lp.TLpCommentData;
import cn.com.ddhj.result.lp.TLpCommentTopData;
import cn.com.ddhj.service.ITLpCommentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TLpCommentTest extends BaseTest {

	@Autowired
	private ITLpCommentService service;
	@Autowired
	private TUserLoginMapper loginMapper;

	@Test
	public void insert() {
		// for (int i = 0; i < 99; i++) {
		// TLpComment entity = new TLpComment();
		// entity.setCode(WebHelper.getInstance().getUniqueCode("LPC"));
		// entity.setLpCode("LP161004101471");
		// entity.setCreateUser("U161009100001");
		// entity.setContent("测试楼盘评价");
		// if (i % 5 == 0) {
		// entity.setLevel(1);
		// } else if (i % 7 == 0) {
		// entity.setLevel(2);
		// }
		// service.insertSelective(entity);
		// }
		TLpComment entity = new TLpComment();
		entity.setCode(WebHelper.getInstance().getUniqueCode("LPC"));
		entity.setLpCode("LP161004101471");
		entity.setCreateUser("U161009100001");
		entity.setContent("测试楼盘评价");
		BaseResult result = service.insertSelective(entity, "6a397b4cd42f4d62b3c5c43143d94714");
		System.out.println(JSONObject.toJSON(result));
	}

	public void top5() {
		TLpCommentDto dto = new TLpCommentDto();
		dto.setLpCode("LP161004101471");
		TLpCommentTopData result = service.findDataTop5(dto);
		System.out.println(JSONObject.toJSON(result));
	}

	public void data() {
		TLpCommentDto dto = new TLpCommentDto();
		dto.setPageIndex(0);
		dto.setPageSize(10);
		dto.setLpCode("LP161004101471");
		TLpCommentData result = service.findData(dto);
		System.out.println(JSONObject.toJSON(result));
	}

}
