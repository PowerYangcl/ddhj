package cn.com.ddhj.service;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.util.PureNetUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @descriptions 当前位置实时环境查询（调聚合，调环境模型）|接口验证类
 *
 * @date 2016年10月2日 下午3:01:11
 * @author Yangcl
 * @version 1.0.1
 */
public class CurrentPositionEnvironment {

	// 配置您申请的KEY
	public static final String KEY = "e06872cce776a7fd22b66251127512e7";
	
	public static void main(String[] args) {
		
	}
	
	
	/**
	 * @descriptions 经纬度地址解析调用示例代码 － 聚合数据
	*
	* @date 2016年10月2日 下午6:00:43
	* @author Yangcl 
	* @version 1.0.0.1
	 */
	public static JSONArray getRequestJWD() {
		String url = "http://apis.juhe.cn/geo/";
		JSONObject obj = null;
		JSONArray array = new JSONArray();
		Map<String, String> param = new HashMap<String, String>();
		param.put("lng", "119.9772857");			// 经度 (如：119.9772857)
		param.put("lat", "27.327578");				// 纬度 (如：27.327578)
		param.put("key", KEY);							// 申请的APPKEY
		param.put("type", "2");								// 传递的坐标类型,1:GPS 2:百度经纬度 3：谷歌经纬度
		param.put("dtype", "json");								// 返回数据格式：json或xml,默认json

		 
		String result = PureNetUtil.post(url, param);
		if (result != null && !"".equals(result)) {
			obj = JSONObject.parseObject(result);
			// 如果状态=200时，获取城市列表
			if (obj.getInteger("resultCode") == 200) {
				array = JSONArray.parseArray(obj.getString("result"));
			}
		}
		return array;
	}

	
	
	
	
	
	
 


}
