package cn.com.ddhj;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.dto.report.TReportDto;
import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.mapper.report.TReportMapper;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.model.report.TReport;
import cn.com.ddhj.result.report.TReportSelResult;
import cn.com.ddhj.service.report.ITReportService;
import cn.com.ddhj.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TReportTest extends BaseTest {

	@Autowired
	private ITReportService service;
	@Autowired
	private TReportMapper mapper;

	@Autowired
	private TLandedPropertyMapper lpMapper;

	@Test
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
		BaseResult result = service.insert(entity, "E:/");
		System.out.println(JSONObject.toJSON(result));
		TReport entity2 = new TReport();
		entity2.setTitle("测试环境报告");
		entity2.setPrice(BigDecimal.TEN);
		entity2.setHousesCode("LP161004101471");
		entity2.setRang(10);
		entity2.setDetail("测试环境报告-高级，报告说明");
		entity2.setLevelCode("RL161006100002");
		BaseResult result2 = service.insert(entity2, "E:/");
		System.out.println(JSONObject.toJSON(result2));
		TReport entity3 = new TReport();
		entity3.setTitle("测试环境报告");
		entity3.setPrice(BigDecimal.TEN);
		entity3.setHousesCode("LP161004101471");
		entity3.setRang(10);
		entity3.setDetail("测试环境报告-专业，报告说明");
		entity3.setLevelCode("RL161006100003");
		BaseResult result3 = service.insert(entity3, "E:/");
		System.out.println(JSONObject.toJSON(result3));
	}

	public void batchInsert() {
		System.out.println("start:" + DateUtil.getSysDateTime());
		List<TLandedProperty> list = lpMapper.findTLandedPropertyAll();
		System.out.println("查询楼盘:" + DateUtil.getSysDateTime() + "，总数:" + list.size());
		List<TReport> reports = new ArrayList<TReport>();
		Integer code = 100010;
		for (TLandedProperty lp : list) {
			TReport entity = new TReport();
			entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
			entity.setCode("R161009" + code);
			entity.setHousesCode(lp.getCode());
			entity.setTitle(lp.getTitle() + "-环境报告-普通");
			entity.setLevelCode("RL161006100001");
			entity.setPic("");
			entity.setImage("");
			entity.setRang(10);
			entity.setPrice(BigDecimal.TEN);
			entity.setDetail(lp.getTitle() + "-环境报告说明-普通");
			entity.setCreateUser("system");
			entity.setCreateTime(DateUtil.getSysDateTime());
			entity.setUpdateUser("system");
			entity.setUpdateTime(DateUtil.getSysDateTime());
			reports.add(entity);
			code++;
			TReport entity2 = new TReport();
			entity2.setUuid(UUID.randomUUID().toString().replace("-", ""));
			entity2.setCode("R161009" + code);
			entity2.setHousesCode(lp.getCode());
			entity2.setTitle(lp.getTitle() + "-环境报告-高级");
			entity2.setLevelCode("RL161006100002");
			entity2.setPic("");
			entity2.setImage("");
			entity2.setRang(10);
			entity2.setPrice(BigDecimal.TEN);
			entity2.setDetail(lp.getTitle() + "-环境报告说明-高级");
			entity2.setCreateUser("system");
			entity2.setCreateTime(DateUtil.getSysDateTime());
			entity2.setUpdateUser("system");
			entity2.setUpdateTime(DateUtil.getSysDateTime());
			reports.add(entity2);
			code++;
			TReport entity3 = new TReport();
			entity3.setUuid(UUID.randomUUID().toString().replace("-", ""));
			entity3.setCode("R161009" + code);
			entity3.setHousesCode(lp.getCode());
			entity3.setTitle(lp.getTitle() + "-环境报告-专业");
			entity3.setLevelCode("RL161006100003");
			entity3.setPic("");
			entity3.setImage("");
			entity3.setRang(10);
			entity3.setPrice(BigDecimal.TEN);
			entity3.setDetail(lp.getTitle() + "-环境报告说明-专业");
			entity3.setCreateUser("system");
			entity3.setCreateTime(DateUtil.getSysDateTime());
			entity3.setUpdateUser("system");
			entity3.setUpdateTime(DateUtil.getSysDateTime());
			reports.add(entity3);
			code++;
		}
		service.insertReportData(reports);
		System.out.println(reports.size());
	}

	public void getTReport() {
		String code = "R161009100040";
		TReportSelResult result = service.getTReport(code);
		System.out.println(JSONObject.toJSON(result));

	}

	public void findRreportByChart() {
		List<String> list = new ArrayList<String>();
		list.add("R161006100001");
		System.out.println(mapper.findRreportByChart(list));
	}
}
