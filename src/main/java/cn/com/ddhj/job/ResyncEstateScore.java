package cn.com.ddhj.job;

import cn.com.ddhj.annotation.Inject;
import cn.com.ddhj.base.BaseClass;
import cn.com.ddhj.service.IEstateEnvironmentService;

public class ResyncEstateScore extends BaseClass {
	@Inject
	private IEstateEnvironmentService service;
	
	public void resyncEstateScore() {
		service.resyncEstateScore();
	}
}
