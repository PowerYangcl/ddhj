package cn.com.ddhj.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.service.ICityAirService;
import cn.com.ddhj.util.PureNetUtil;

/**
 * @description: 调用万年历
 * 	【聚合接口】->【万年历】->【获取当天的详细信息】
 * 
 * @author Yangcl
 * @date 2016年12月27日 上午11:20:50 
 * @version 1.0.0
 */
public class Task2051Calendar implements Callable<JSONObject>{

	private static Logger logger = Logger.getLogger(Task2051Calendar.class);
	
	public Task2051Calendar(String today) {
		this.today = today;
	}

	private String today;
	
	public JSONObject call() throws Exception {
		JSONObject result = new JSONObject();
		result.put("flag", false);
		
		String url = "http://japi.juhe.cn/calendar/day";
		String key = "6b773a4dceca6afd793098e116f647de";
		Map<String, String> param = new HashMap<String, String>(); 		 
		param.put("key", key);
		String date = today.split("-")[0] + "-" + Integer.valueOf(today.split("-")[1]) + "-" + today.split("-")[2]; // 2016-2-27  
		param.put("date", date);
		String responseJson = ""; 
		try {
			responseJson = PureNetUtil.post(url , param);
			if (responseJson != null && !"".equals(responseJson)) {
				JSONObject obj = JSONObject.parseObject(responseJson);
				if(obj.getString("reason").equals("Success")){
					obj = JSONObject.parseObject(obj.getString("result"));
					obj = JSONObject.parseObject(obj.getString("data"));
					result.put("avoid", obj.getString("data"));  // 开市.纳采.订盟.作灶.造庙.造船.经络
					result.put("animalsYear", obj.getString("animalsYear"));  // 猴
					result.put("weekday", obj.getString("weekday"));  // 星期六
					result.put("suit", obj.getString("suit"));  //  嫁娶.冠笄.祭祀.祈福.求嗣.雕刻.开光.安香.出行.入学.修造.动土.竖柱.上梁.造屋.起基.安门.出火.移徙.入宅.掘井.造畜椆栖.安葬.破土.除服.成服
					result.put("lunarYear", obj.getString("lunarYear"));  // 丙申年
					result.put("lunar", obj.getString("lunar"));  // 十一月廿六
					result.put("date", obj.getString("date"));  // 2016-12-24
					result.put("flag", true);
				}else{
					logger.info("【聚合接口】->【万年历】->【获取当天的详细信息】|聚合接口获取当天的详细信息失败！错误码：" + obj.getInteger("error_code"));
				}
			}else{
				logger.info("【聚合接口】->【万年历】->【获取当天的详细信息】点点环境服务器发起请求失败！服务器网络不稳定！");
			}
		} catch (Exception e) { 
			e.printStackTrace();   
		}
		
		return result;
	}

}
