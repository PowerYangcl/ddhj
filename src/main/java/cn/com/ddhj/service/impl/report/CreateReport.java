package cn.com.ddhj.service.impl.report;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.ddhj.helper.ReportHelper;
import cn.com.ddhj.mapper.report.TReportMapper;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.model.lp.TLpEnvironmentIndex;
import cn.com.ddhj.model.report.TReport;
import cn.com.ddhj.util.DateUtil;

public class CreateReport implements Runnable {

	@Autowired
	private TReportMapper mapper;

	private List<TLandedProperty> list;

	public CreateReport(List<TLandedProperty> list) {
		this.list = list;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		try {
			List<TReport> reports = new ArrayList<TReport>();
			for (TLandedProperty lp : list) {
				if (StringUtils.isNotBlank(lp.getLat())) {
					lp.setWeatherDistribution(ReportHelper.getWeatherDistribution(Float.valueOf(lp.getLat())));
				} else {
					lp.setWeatherDistribution("无法定位小区");
				}
				List<TLpEnvironmentIndex> list = ReportHelper.getInstance().getLpEnvironmentIndexs(lp);
				lp.setEnvironmentIndexs(list);
				lp.setEnvironmentIndexs1(list.subList(0, 3));
				lp.setEnvironmentIndexs2(list.subList(3, 6));
				lp.setEnvironmentIndexs3(list.subList(6, 9));
				lp.setEnvironmentIndexs4(list.subList(9, list.size()));
				/*
				 * 精简版
				 */
				TReport simplification = new TReport();
				simplification.setHousesCode(lp.getCode());
				simplification.setLevelCode("RL161006100001");
				String path = ReportHelper.getInstance().createHtml(lp, "simplification");
				simplification.setPath(path);
				String reportDate = DateUtil.getSysDateTime();
				simplification.setCreateTime(reportDate);
				/*
				 * 完整版
				 */
				TReport full = new TReport();
				full.setHousesCode(lp.getCode());
				full.setLevelCode("RL161006100002");
				String fullPath = ReportHelper.getInstance().createHtml(lp, "full");
				full.setPath(fullPath);
				String fullReportDate = DateUtil.getSysDateTime();
				full.setCreateTime(fullReportDate);
			}
			mapper.batchInsertH5ReportToTmp(reports);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("生成环境报告用时:"+(end-start));
	}

	public List<TLandedProperty> getList() {
		return list;
	}

	public void setList(List<TLandedProperty> list) {
		this.list = list;
	}

}
