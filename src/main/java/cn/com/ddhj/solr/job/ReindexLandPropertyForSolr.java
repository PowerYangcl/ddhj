package cn.com.ddhj.solr.job;

import cn.com.ddhj.annotation.Inject;
import cn.com.ddhj.service.solr.SolrDataService;

public class ReindexLandPropertyForSolr {
	@Inject
	private SolrDataService service;
	public void reindex() {
		//每天凌晨5分运行此定时批量更新solr索引
		service.getListSolrData(null);
	}
}
