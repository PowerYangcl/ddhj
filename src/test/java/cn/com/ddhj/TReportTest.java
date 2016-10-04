package cn.com.ddhj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.dto.TReportDto;
import cn.com.ddhj.model.report.TReport;
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
		dto.setPageIndex(1);
		dto.setPageSize(10);
		System.out.println(JSONObject.toJSON(service.findEntityToPage(dto)));
	}
}