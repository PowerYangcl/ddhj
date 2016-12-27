package cn.com.ddhj.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.service.ICityAirService;
import cn.com.ddhj.util.PureNetUtil;

/**
 * @description: 调用万年历中的日期
 * 	【聚合接口】->【万年历】->【获取近期假期】
 * 
 * @author Yangcl
 * @date 2016年12月27日 上午11:21:10 
 * @version 1.0.0
 */
public class Task2051Holiday implements Callable<JSONObject>{

	private static Logger logger = Logger.getLogger(Task2051Holiday.class);
	
	public Task2051Holiday(String today) {
		this.today = today;
	}
	
	private String today;
	
	public JSONObject call() throws Exception {
		JSONObject result = new JSONObject();
		result.put("flag", false);
		
		String url = "http://japi.juhe.cn/calendar/month";
		String key = "6b773a4dceca6afd793098e116f647de";
		Map<String, String> param = new HashMap<String, String>(); 		 
		param.put("key", key);
		String date = today.split("-")[0] + "-" + Integer.valueOf(today.split("-")[1]); // 2016-2  
		param.put("year-month", date);
		String responseJson = ""; 
		try {
			responseJson = PureNetUtil.post(url , param);
			if (responseJson != null && !"".equals(responseJson)) {
				JSONObject obj = JSONObject.parseObject(responseJson);
				if(obj.getString("reason").equals("Success")){
					obj = JSONObject.parseObject(obj.getString("result"));
					obj = JSONObject.parseObject(obj.getString("data"));
					JSONArray arr = JSONArray.parseArray(obj.getString("holiday"));
					if(arr != null && arr.size() != 0){
						result.put("holidayName", arr.getJSONObject(0).getString("name"));  //  元旦
						result.put("holidayFestival", arr.getJSONObject(0).getString("festival"));  // 2017-1-1
						result.put("holidayDesc", arr.getJSONObject(0).getString("desc"));  // 1月1日放假，1月2日（星期一）补休，共3天。
						result.put("flag", true);
					}else{
						logger.info("聚合接口】->【万年历】->【获取近期假期】聚合接口返回节假日信息为空");
					}
				}else{
					logger.info("聚合接口】->【万年历】->【获取近期假期】|聚合接口返回节假日信息失败！错误码：" + obj.getInteger("error_code"));
				}
			}else{
				logger.info("【聚合接口】->【万年历】->【获取近期假期】点点环境服务器发起请求失败！服务器网络不稳定！");
			}
		} catch (Exception e) { 
			e.printStackTrace();   
		}
		
		return result;
	}

}
