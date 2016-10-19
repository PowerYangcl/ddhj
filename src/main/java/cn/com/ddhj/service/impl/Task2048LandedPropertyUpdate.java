package cn.com.ddhj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.util.PureNetUtil;

public class Task2048LandedPropertyUpdate implements Callable<Integer> {

	private List<TLandedProperty> list;
	
	private TLandedPropertyMapper lrMapper;


	public Task2048LandedPropertyUpdate(List<TLandedProperty> list, TLandedPropertyMapper lrMapper) {
		this.list = list;
		this.lrMapper = lrMapper;
	}




	public Integer call() throws Exception {
		Integer flag = this.getLrMapper().batchUpdateScore(this.getList());
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












