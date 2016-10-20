package cn.com.ddhj.service.impl;

import java.util.List;
import java.util.concurrent.Callable;

import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.model.TLandedProperty;

public class Task2048LandedPropertyUpdate implements Callable<Integer> {

	private List<TLandedProperty> list;
	
	private TLandedPropertyMapper lrMapper;


	public Task2048LandedPropertyUpdate(List<TLandedProperty> list, TLandedPropertyMapper lrMapper) {
		this.list = list;
		this.lrMapper = lrMapper;
	}


	public Integer call() throws Exception {
		Integer flag = 0;
		try {
			flag = this.getLrMapper().batchUpdateScore(this.getList());
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return flag; 
	}
	
	
	
	
	public List<TLandedProperty> getList() {
		return list;
	}
	public void setList(List<TLandedProperty> list) {
		this.list = list;
	}

	public TLandedPropertyMapper getLrMapper() {
		return lrMapper;
	}
	public void setLrMapper(TLandedPropertyMapper lrMapper) {
		this.lrMapper = lrMapper;
	}
}












