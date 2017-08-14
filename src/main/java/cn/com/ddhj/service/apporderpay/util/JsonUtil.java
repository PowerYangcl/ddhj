package cn.com.ddhj.service.apporderpay.util;

import com.alibaba.fastjson.JSONObject;



public class JsonUtil {
	
	/**
	 * 获取JSON某一个key的值
	 * @param rescontent
	 * @param key
	 * @return
	 */
	public static String getJsonValue(String rescontent, String key) {
		JSONObject jsonObject;
		String v = null;
		try {
			jsonObject = JSONObject.parseObject(rescontent);
			v = jsonObject.getString(key);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return v;
	}
	
	/**
	 * 获取所有JSON数据
	 * @param rescontent
	 * @return
	 */
	public static JSONObject getJsonValues(String rescontent){
		JSONObject jsonObject;
		try {
			jsonObject = JSONObject.parseObject(rescontent);
			return jsonObject;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}
}
