package cn.com.ddhj.job;

import cn.com.ddhj.annotation.Inject;
import cn.com.ddhj.base.BaseClass;
import cn.com.ddhj.service.search.ISearchService;

/**
 * 定时统计执搜关键词
 * @author zht
 *
 */
public class StatSearchHotWord extends BaseClass {
	@Inject
	private ISearchService searchService;
	public void stat() {
		searchService.statSearchHotWord();
	}
}
