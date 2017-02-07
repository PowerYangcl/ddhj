package cn.com.ddhj;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.dto.TOrderDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.model.TOrder;
import cn.com.ddhj.result.order.OrderAffirmResult;
import cn.com.ddhj.result.order.TOrderResult;
import cn.com.ddhj.service.ITOrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TOrderTest extends BaseTest {

	@Autowired
	private ITOrderService service;

	@Test
	public void insert() {
		TOrder entity = new TOrder();
		entity.setCode(WebHelper.getInstance().getUniqueCode("D"));
		entity.setReportCode("R161008100007");
		entity.setCreateUser("system");
		entity.setPayCode("PT161007100002");
		entity.setInvoiceTitle("个人");
		entity.setPayPrice(BigDecimal.TEN);
		entity.setBuyerCode("12332");
		entity.setBuyerMobile("13452221133");
		entity.setCheckPayMoney(entity.getPayPrice());
		entity.setCarbonMoney(BigDecimal.ZERO);
		entity.setCouponCodes("13232,12312312");
		entity.setInvoiceType("增值税");
		entity.setInvoiceContent("惠家有");
		entity.setUpdateUser("system");
		System.out.println(JSON.toJSON(entity));
		// service.insertSelective(entity);
	}

	public void data() {
		TOrderDto dto = new TOrderDto();
		dto.setPageIndex(0);
		dto.setPageSize(10);
		TOrderResult data = service.findEntityToPage(dto, "6a397b4cd42f4d62b3c5c43143d94714", null);
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
		OrderAffirmResult result = service.orderAffirm(codes,"6a397b4cd42f4d62b3c5c43143d94714");
		System.out.println(JSONObject.toJSON(result));
	}

	public void payTest() {
		double goodPercent = BigDecimal.valueOf(12).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
				.doubleValue();
		System.out.println(goodPercent);
	}
}
