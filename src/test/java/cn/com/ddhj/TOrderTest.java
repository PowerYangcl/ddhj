package cn.com.ddhj;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.base.PageResult;
import cn.com.ddhj.dto.TOrderDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.model.TOrder;
import cn.com.ddhj.result.order.OrderAffirmResult;
import cn.com.ddhj.service.ITOrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TOrderTest extends BaseTest {

	@Autowired
	private ITOrderService service;

	public void insert() {
		TOrder entity = new TOrder();
		entity.setCode(WebHelper.getInstance().getUniqueCode("D"));
		entity.setReportCode("R161006100001");
		entity.setCreateUser("system");
		entity.setPayCode("PT161007100002");
		entity.setInvoiceTitle("个人");
		entity.setPayPrice(BigDecimal.TEN);
		entity.setUpdateUser("system");
		service.insertSelective(entity);
	}

	public void data() {
		TOrderDto dto = new TOrderDto();
		dto.setPageIndex(0);
		dto.setPageSize(10);
		PageResult<TOrder> data = service.findEntityToPage(dto);
		System.out.println(JSONObject.toJSON(data));

	}

	public void edit() {
		TOrder entity = new TOrder();
		entity.setCode("D161007100001");
		entity.setStatus(1);
		entity.setUpdateUser("system");
		service.updateByCode(entity);
	}

	public void orderAffirm() {
		String codes = "R161006100001";
		OrderAffirmResult result = service.orderAffirm(codes);
		System.out.println(JSONObject.toJSON(result));
	}

	@Test
	public void payTest() {
		double goodPercent = BigDecimal.valueOf(12).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
				.doubleValue();
		System.out.println(goodPercent);
	}
}
