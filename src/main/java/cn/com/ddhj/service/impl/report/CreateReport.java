package cn.com.ddhj.service.impl.report;

import java.util.List;

import cn.com.ddhj.helper.ReportHelper;
import cn.com.ddhj.model.TLandedProperty;
import freemarker.template.Configuration;

public class CreateReport implements Runnable {

	private Configuration config;

	private List<TLandedProperty> list;

	public CreateReport(List<TLandedProperty> list, Configuration config) {
		this.list = list;
		this.config = config;
	}

	@Override
	public void run() {
		try {
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					TLandedProperty lp = list.get(i);
					/*
					 * 精简版
					 */
					ReportHelper.getInstance().createHtml(lp, "simplification");
					/*
					 * 完整版
					 */
					ReportHelper.getInstance().createHtml(lp, "full");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<TLandedProperty> getList() {
		return list;
	}

	public void setList(List<TLandedProperty> list) {
		this.list = list;
	}

	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}

}
