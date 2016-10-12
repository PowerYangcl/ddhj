package cn.com.ddhj.service.impl;

import java.util.concurrent.Callable;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.service.ILongitudeLatitudeService;

/**
 * @description: 但启动一个线程去调用位置信息 
 * 
 * @author Yangcl
 * @date 2016年10月10日 下午3:11:52 
 * @version 1.0.0
 */
public class Task1025Position implements Callable<JSONObject>{

	private ILongitudeLatitudeService llService;
	private String lat = "";
	private String lng = "";
	
	
	public JSONObject call() throws Exception {
		JSONObject result = new JSONObject();
		result = this.getLlService().getCurrentPositionInfo(this.getLng(), this.getLat(), "2");
		return result;
	}

	
	public ILongitudeLatitudeService getLlService() {
		return llService;
	}
	public void setLlService(ILongitudeLatitudeService llService) {
		this.llService = llService;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}

}
