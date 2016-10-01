package cn.com.ddhj.service;

import com.alibaba.fastjson.JSONObject;

public interface ICityAirService {

	public static final String URL = "http://web.juhe.cn:8080/environment/air/cityair";
	public static final String KEY="931fc0d7a07f0b09aa219a4008782f87";
	
	JSONObject getCityAir(String cityName);
}
