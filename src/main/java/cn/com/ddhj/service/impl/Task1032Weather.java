package cn.com.ddhj.service.impl;

import java.util.concurrent.Callable;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.service.ICityAirService;

/**
 * @description: 但启动一个线程去调用天气信息 
 * 
 * @author Yangcl
 * @date 2016年10月10日 下午3:11:52 
 * @version 1.0.0
 */
public class Task1032Weather implements Callable<JSONObject>{

	private ICityAirService cityAirService;
	private String city;
	
	
	public JSONObject call() throws Exception {
		JSONObject result = new JSONObject();
		result = this.getCityAirService().getWeatherInfo(this.getCity()); 
		return result;
	}



	public ICityAirService getCityAirService() {
		return cityAirService;
	}
	public void setCityAirService(ICityAirService cityAirService) {
		this.cityAirService = cityAirService;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
