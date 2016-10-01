package cn.com.ddhj.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.service.ICityAirService;
import cn.com.ddhj.util.PureNetUtil;

@Service
public class CityAirServiceImpl implements ICityAirService {

	@Override
	public JSONObject getCityAir(String cityName) {
		JSONObject obj = null;
		Map<String, String> param = new HashMap<String, String>();
		param.put("key", KEY);
		param.put("city", cityName);
		String result = PureNetUtil.post(URL, param);
		if (result != null && !"".equals(result)) {
			obj = JSONObject.parseObject(result);
		} else {
			obj = new JSONObject();
			obj.put("code", "-1");
			obj.put("message", "查询数据为空");
		}
		return obj;
	}

}
