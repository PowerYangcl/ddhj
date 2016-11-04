package cn.com.ddhj.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * @description:教授评分接口 
 * 
 * @author Yangcl
 * @date 2016年11月4日 上午11:50:51 
 * @version 1.0.0
 */
public class DoctorScoreUtil {
	
	/**
	 * @descriptions 获取教授数学模型综合评分|噪音和水质暂时默认为2
	 * 	机场5km以内 4类  距离候车站地点2km以内的，4类
	 *
	 * @param a hourAQI
	 * @param b dayAQI   @教授的接口文档有问题，暂时放一个参数
	 * @param c  l  生态状况:绿化率指数 0.5或1  【地产检索接口->"greeningRate":"50%"】
	 * @param d  j 生态状况:容积率指数  0~9之间 【地产检索接口->"volumeRate":"0.46"】
	 * @param s 水环境:水质分类       否       1~5之间,默认为2
	 * @param z1 噪声分级号:昼间       否       0~5之间,必须和夜间噪声分级号一起传入方才有效, 默认为2
	 * @param z2 噪声分级号:夜间       否       0~5之间,必须和昼间噪声分级号一起传入方才有效, 默认为2
	 * 
	 * @return
	 * @date 2016年10月4日 下午10:18:29
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public static String getDoctorScore(String a ,String b ,String c , String d , String s ,String z1 , String z2){
		String url = "http://api.sys.ecomapit.com/environment/servlet/environmentZHInterface";
		JSONObject obj = null;
		Map<String, String> param = new HashMap<String, String>();
		param.put("hourAQI", a);		 
		param.put("dayAQI", b);	 
		param.put("s", s);				 		 
		param.put("z1", z1);							 
		param.put("z2", z2);							 
		param.put("l", c);	
		param.put("j", d);	
		param.put("t", "2");  // 土壤指数 
		
		String result = PureNetUtil.get(url, param);
		if (result != null && !"".equals(result)) {
			obj = JSONObject.parseObject(result); 
			if(obj.getString("flag").equals("true")){
				return obj.getString("message");
			}
		}
		return "0";
	}
	
}























