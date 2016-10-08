package cn.com.ddhj;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.dto.report.TReportDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.report.TReportMapper;
import cn.com.ddhj.model.report.TReport;
import cn.com.ddhj.result.report.TReportSelResult;
import cn.com.ddhj.service.report.ITReportService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TReportTest extends BaseTest {

	@Autowired
	private ITReportService service;
	@Autowired
	private TReportMapper mapper;

	public void createPDF() {
		service.createPDF(WebHelper.getInstance().getUniqueCode("R"), "LP161003100031", "d:/");
	}

	public void page() {
		TReportDto dto = new TReportDto();
		// String position = "39.9091529846191,116.244064331055";
		dto.setCode("LP161004101471");
		dto.setPageIndex(0);
		dto.setPageSize(10);
		System.out.println(JSONObject.toJSON(service.getReportData(dto)));
	}

	public void insert() {
		TReport entity = new TReport();
		entity.setTitle("测试环境报告");
		entity.setPrice(BigDecimal.TEN);
		entity.setHousesCode("LP161004101471");
		entity.setRang(10);
		entity.setDetail("测试环境报告-普通，报告说明");
		entity.setLevelCode("RL161006100001");
		BaseResult result = service.insert(entity, "d:/");
		System.out.println(JSONObject.toJSON(result));
		TReport entity2 = new TReport();
		entity2.setTitle("测试环境报告");
		entity2.setPrice(BigDecimal.TEN);
		entity2.setHousesCode("LP161004101471");
		entity2.setRang(10);
		entity2.setDetail("测试环境报告-高级，报告说明");
		entity2.setLevelCode("RL161006100002");
		BaseResult result2 = service.insert(entity2, "d:/");
		System.out.println(JSONObject.toJSON(result2));
		TReport entity3 = new TReport();
		entity3.setTitle("测试环境报告");
		entity3.setPrice(BigDecimal.TEN);
		entity3.setHousesCode("LP161004101471");
		entity3.setRang(10);
		entity3.setDetail("测试环境报告-专业，报告说明");
		entity3.setLevelCode("RL161006100003");
		BaseResult result3 = service.insert(entity3, "d:/");
		System.out.println(JSONObject.toJSON(result3));
	}

	@Test
	public void getTReport() {
		String code = "R161008100006";
		TReportSelResult result = service.getTReport(code);
		System.out.println(JSONObject.toJSON(result));
	}

	public void findRreportByChart() {
		List<String> list = new ArrayList<String>();
		list.add("R161006100001");
		System.out.println(mapper.findRreportByChart(list));
	}
}