package cn.com.ddhj.service;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.util.PureNetUtil;

/**
 * @descriptions 当前位置实时环境查询（调聚合，调环境模型）|接口验证类
 *
 * @date 2016年10月2日 下午3:01:11
 * @author Yangcl
 * @version 1.0.1
 */
public class CurrentPositionEnvironment {
	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
	
	
	// 配置您申请的KEY
	public static final String KEY1 = "e06872cce776a7fd22b66251127512e7";
	
	public static void main(String[] args) {
		
//		getRequest1();
		
		JSONObject obj = getCityWeather("北京");
		
		JSONObject result = JSONObject.parseObject(obj.getString("result"));
		JSONObject data = JSONObject.parseObject(result.getString("data"));
		
		JSONObject realtime = JSONObject.parseObject(data.getString("realtime"));
		// {"img":"3","temperature":"14","humidity":"92","info":"阵雨"}
		JSONObject weather = JSONObject.parseObject(realtime.getString("weather"));
		
		JSONObject pm25 = JSONObject.parseObject(data.getString("pm25"));
		// {"des":"可正常活动。","curPm":"39","pm25":"27","level":1,"pm10":"55","quality":"优"}
		JSONObject pm25_ = JSONObject.parseObject(pm25.getString("pm25"));
		
		
		String info = weather.getString("info");
		String quality = pm25_.getString("quality");
		String des = pm25_.getString("des");
		
		
		int a = 0 ; 
		System.out.println(a); 
	}
	
	private static JSONObject getCityWeather(String city) {
		String url = "http://op.juhe.cn/onebox/weather/query";
		JSONObject obj = null;
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("key", "cb6b71e3dc87bb434da0a8babf701936");
			param.put("cityname", city); 
			String result = PureNetUtil.get(url, param);
			if (result != null && !"".equals(result)) {
				obj = JSONObject.parseObject(result);
			} else {
				obj = new JSONObject();
				obj.put("code", "-1");
				obj.put("message", "查询数据为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj = new JSONObject();
			obj.put("code", "-1");
			obj.put("message", "查询数据错误");
		}
		return obj;
	}
	
	
	
 


}
