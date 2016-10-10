package cn.com.ddhj.service.impl;

import java.util.concurrent.Callable;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.service.IWeatherAreaSupportService;

/**
 * @description: 但启动一个线程去调用天气信息 
 * 
 * @author Yangcl
 * @home https://github.com/PowerYangcl
 * @date 2016年10月10日 下午3:11:52 
 * @version 1.0.0
 */
public class Task1025Weather implements Callable<JSONObject>{

	private IWeatherAreaSupportService wasService;
	private String lat = "";
	private String lng = "";
	
	public JSONObject call() throws Exception {
		JSONObject result = new JSONObject();
		result = this.getWasService().getWeatherWithPosition(this.getLng(), this.getLat());
		return result;
	}

	public IWeatherAreaSupportService getWasService() {
		return wasService;
	}
	public void setWasService(IWeatherAreaSupportService wasService) {
		this.wasService = wasService;
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
