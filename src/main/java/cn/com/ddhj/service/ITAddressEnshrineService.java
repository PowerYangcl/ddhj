package cn.com.ddhj.service;

import com.alibaba.fastjson.JSONObject;


public interface ITAddressEnshrineService {

	public JSONObject rsyncAddressEnshrine(JSONObject input, String userToken);  
}
