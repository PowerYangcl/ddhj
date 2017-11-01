package cn.com.ddhj;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.dto.report.TReportDto;
import cn.com.ddhj.helper.PropHelper;
import cn.com.ddhj.helper.ReportHelper;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.mapper.report.TReportMapper;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.model.lp.TLpEnvironmentIndex;
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

	public void createPDF() {
		TReportDto dto = new TReportDto();
		dto.setCode("R161009164878");
		dto.setLpCode("LP161009105939");
		BaseResult result = service.createReport(dto, "E://", null);
		System.out.println(JSON.toJSON(result));
	}

	public void batch() {
		TReport entity = new TReport();
		entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
		entity.setCode(WebHelper.getInstance().getUniqueCode("L"));
		entity.setHousesCode("LP161009105939");
		entity.setTitle("测试" + "-环境报告-普通");
		entity.setLevelCode("RL161006100001");
		entity.setPic("");
		entity.setImage("");
		entity.setRang(10);
		entity.setPrice(BigDecimal.valueOf(0.01));
		entity.setPath("");
		entity.setDetail("测试" + "-环境报告说明-普通");
		entity.setCreateUser("system");
		entity.setCreateTime(DateUtil.getSysDateTime());
		entity.setUpdateUser("system");
		entity.setUpdateTime(DateUtil.getSysDateTime());
		List<TReport> list = new ArrayList<TReport>();
		list.add(entity);
		mapper.insertReportData(list);
	}

	public void batchCreateReport() {
		// service.batchCreateReport();
		String code = "R161009100013";
		String lpCode = "LP161004101472";
		service.createPPT(code, lpCode, null, null);
	}

	public void updateReportByLpAndLevel() {
		try {
			List<TLandedProperty> lps = lpMapper.findTLandedPropertyAll();
			for (TLandedProperty lp : lps) {
				System.out.println(lp.getCode());
				lp.setWeatherDistribution(ReportHelper.getWeatherDistribution(Float.valueOf(lp.getLat())));
				List<TLpEnvironmentIndex> list = ReportHelper.getInstance().getLpEnvironmentIndexs(lp);
				lp.setEnvironmentIndexs(list);
				lp.setEnvironmentIndexs1(list.subList(0, 3));
				lp.setEnvironmentIndexs2(list.subList(3, 6));
				lp.setEnvironmentIndexs3(list.subList(6, 9));
				lp.setEnvironmentIndexs4(list.subList(9, list.size()));
				String fullPath = PropHelper.getValue("report_url") + lp.getCode() + "/full.html";
				TReport full = new TReport();
				full.setHousesCode(lp.getCode());
				full.setLevelCode("RL161006100002");
				full.setPath(fullPath);
				full.setUpdateUser("test");
				full.setUpdateTime(DateUtil.getSysDateTime());
				mapper.updateReportByLpAndLevel(full);
				String simplificationPath = PropHelper.getValue("report_url") + lp.getCode() + "/simplification.html";
				TReport simplification = new TReport();
				simplification.setHousesCode(lp.getCode());
				simplification.setLevelCode("RL161006100001");
				simplification.setPath(simplificationPath);
				simplification.setUpdateUser("test");
				simplification.setUpdateTime(DateUtil.getSysDateTime());
				mapper.updateReportByLpAndLevel(simplification);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createTReport() {
		/**
		 * 查询所有已生成的报告添加到t_report表
		 */
		List<TReport> reports = new ArrayList<TReport>();
		List<Map<String, Object>> list = mapper.fineTreportTmp();
		String time = DateUtil.getSysDateTime();
		String code = WebHelper.getInstance().getUniqueCode("");
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			TReport entity = new TReport();
			entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
			entity.setCode("R" + (Long.valueOf(code) + i));
			entity.setHousesCode(map.get("lp_code").toString());
			entity.setTitle(map.get("report_title").toString());
			entity.setLevelCode(map.get("report_level").toString());
			entity.setPath(map.get("path").toString());
			entity.setPic("");
			entity.setImage("");
			entity.setRang(10);
			entity.setPrice(BigDecimal.TEN);
			entity.setDetail(map.get("report_title").toString());
			entity.setCreateUser("system");
			entity.setCreateTime(time);
			entity.setUpdateUser("system");
			entity.setUpdateTime(time);
			reports.add(entity);
		}
		System.out.println(reports.size());
		int size = reports.size() / 5000;
		System.out.println(size);
		int current = 5000;
		try {
			for (int i = 0; i < size; i++) {
				if (i == 0) {
					System.out.println(current * i + "|" + current * (i + 1));
					List<TReport> subList = reports.subList(current * i, current * (i + 1));
					mapper.insertReportData(subList);
				} else {
					System.out.println((current * i) + "|" + current * (i + 1));
					List<TReport> subList = reports.subList(current * i, current * (i + 1));
					mapper.insertReportData(subList);
				}
			}
			System.out.println(current * size + 1 + "|" + reports.size());
			List<TReport> subList = reports.subList(current * size, reports.size());
			mapper.insertReportData(subList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void createH5Report() {
		service.batchCreateH5Report();
	}
}
