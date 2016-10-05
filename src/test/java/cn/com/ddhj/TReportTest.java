package cn.com.ddhj;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.dto.report.TReportDto;
import cn.com.ddhj.model.report.TReport;
import cn.com.ddhj.result.report.TReportSelResult;
import cn.com.ddhj.service.report.ITReportService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TReportTest extends BaseTest {

	@Autowired
	private ITReportService service;

	public void createPDF() {
		service.createPDF("LP161003100031", "d:/");
	}

	@Test
	public void page() {
		TReportDto dto = new TReportDto();
		String position = "39.9091529846191,116.244064331055";
		dto.setPosition(position);
		dto.setPageIndex(0);
		dto.setPageSize(10);
		System.out.println(JSONObject.toJSON(service.getReportData(dto)));
	}

	public void insert() {
		TReport entity = new TReport();
		entity.setTitle("测试环境报告添加");
		entity.setPrice(BigDecimal.ONE);
		entity.setHousesCode("LP161004101471");
		entity.setRang(10);
		entity.setDetail("");
		BaseResult result = service.insert(entity, "d:/");
		System.out.println(JSONObject.toJSON(result));
	}

	public void getTReport() {
		String code = "R161004100011";
		TReportSelResult result = service.getTReport(code);
		System.out.println(JSONObject.toJSON(result));
	}
}