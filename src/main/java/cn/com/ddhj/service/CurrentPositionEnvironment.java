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
	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
	
	
	// 配置您申请的KEY
	public static final String KEY1 = "e06872cce776a7fd22b66251127512e7";
	
	public static void main(String[] args) {
		 getRequestJWD();
	}
	
	
	 
	public static JSONArray getRequestJWD() {
		String url = "http://123.56.169.49:8338/environment/servlet/environmentZHInterface";
		JSONObject obj = null;
		JSONArray array = new JSONArray();
		Map<String, String> param = new HashMap<String, String>();
		param.put("hourAQI", "150");			// 经度 (如：119.9772857)
		param.put("dayAQI", "150");				// 纬度 (如：27.327578)
		param.put("s", "2");							// 申请的APPKEY
		param.put("z1", "2");								// 传递的坐标类型,1:GPS 2:百度经纬度 3：谷歌经纬度
		param.put("z2", "2");								// 返回数据格式：json或xml,默认json
		param.put("l", "0.5");	
		param.put("j", "5");	
		param.put("t", "2");	 
		
		 
		String result =  get(url, param );
 
		if (result != null && !"".equals(result)) {
			obj = JSONObject.parseObject(result);
			// 如果状态=200时，获取城市列表
			if (obj.getInteger("resultCode") == 200) {
				array = JSONArray.parseArray(obj.getString("result"));
			}
		}
		return array;
	}

	
	
	
	 public static String get(String strUrl, Map params) {
		 	String method = "GET";
		 	HttpURLConnection conn = null;
	        BufferedReader reader = null;
	        String rs = null;
	        try {
	            StringBuffer sb = new StringBuffer();
	            if(method==null || method.equals("GET")){
	                strUrl = strUrl+"?"+urlencode(params);
	            }
	            URL url = new URL(strUrl);
	            conn = (HttpURLConnection) url.openConnection();
	            if(method==null || method.equals("GET")){
	                conn.setRequestMethod("GET");
	            }else{
	                conn.setRequestMethod("POST");
	                conn.setDoOutput(true);
	            }
	            conn.setRequestProperty("User-agent", userAgent);
	            conn.setUseCaches(false);
	            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
	            conn.setReadTimeout(DEF_READ_TIMEOUT);
	            conn.setInstanceFollowRedirects(false);
	            conn.connect();
	            if (params!= null && method.equals("POST")) {
	                try {
	                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
	                        out.writeBytes(urlencode(params));
	                } catch (Exception e) {
	                    // TODO: handle exception
	                }
	            }
	            InputStream is = conn.getInputStream();
	            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
	            String strRead = null;
	            while ((strRead = reader.readLine()) != null) {
	                sb.append(strRead);
	            }
	            rs = sb.toString();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
	            if (conn != null) {
	                conn.disconnect();
	            }
	        }
	        return rs;
	    }
	 
	    //将map型转为请求参数型
	    public static String urlencode(Map<String,Object>data) {
	        StringBuilder sb = new StringBuilder();
	        for (Map.Entry i : data.entrySet()) {
	            try {
	                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
	            } catch (UnsupportedEncodingException e) {
	                e.printStackTrace();
	            }
	        }
	        return sb.toString();
	    }
	
	
 


}
