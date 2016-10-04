package cn.com.ddhj.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 类: ICityAirService <br>
 * 描述: 城市空气质量相关接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月1日 下午8:58:53
 */
public interface ICityAirService {

	/**
	 * 
	 * 方法: getAirCityData <br>
	 * 描述: 获取可以查询空气质量的城市列表 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月1日 下午9:05:04
	 * 
	 * @return
	 */
	JSONArray getAirCityData();

	/**
	 * 
	 * 方法: getCityNowAir <br>
	 * 描述: 城市空气质量 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月1日 下午8:57:11
	 * 
	 * @param cityName
	 * @return
	 */
	JSONObject getCityNowAir(String cityName);

	/**
	 * 
	 * 方法: getLastWeekAir <br>
	 * 描述: 获取城市历史七天的空气质量 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月4日 下午7:57:33
	 * 
	 * @param cityName
	 * @return
	 */
	JSONArray getLastWeekAir(String cityName);

	/**
	 * 
	 * 方法: getPMCityData <br>
	 * 描述: 获取可以查询pm的城市列表 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月1日 下午9:21:08
	 * 
	 * @return
	 */
	JSONArray getPMCityData();

	/**
	 * 
	 * 方法: getCityPM <br>
	 * 描述: 城市空气PM2.5 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月1日 下午8:59:45
	 * 
	 * @param cityName
	 * @return
	 */
	JSONObject getCityPM(String cityName);

	/**
	 * 
	 * 方法: getAQILevel <br>
	 * 描述: 获取空气质量等级 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月3日 下午9:17:54
	 * 
	 * @return
	 */
	Integer getAQILevel(String cityName);
}
