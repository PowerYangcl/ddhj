package cn.com.ddhj.service.impl;

import java.util.concurrent.Callable;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.CityAqi;
import cn.com.ddhj.service.ICityAirService;

/**
 * @description: 但启动一个线程去调用AQI信息 
 * 
 * @author Yangcl
 * @home https://github.com/PowerYangcl
 * @date 2016年10月10日 下午3:11:52 
 * @version 1.0.0
 */
public class Task1032Aqi implements Callable<CityAqi>{

	private ICityAirService cityAirService;
	private String city;
	
	
	public CityAqi call() throws Exception {
		CityAqi result = new CityAqi();
		result = this.getCityAirService().getCityAqi(this.getCity()); 
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
