package cn.com.ddhj.service.impl;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.CityAqi;
import cn.com.ddhj.dto.CityAqiData;
import cn.com.ddhj.service.ICityAirService;
import cn.com.ddhj.util.PureNetUtil;

/**
 * 
 * 类: CityAirServiceImpl <br>
 * 描述: 城市空气质量相关接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月1日 下午8:59:15
 */
@Service
public class CityAirServiceImpl implements ICityAirService {

	private static final String KEY = "2c5b736e4a1674265ae4a83cb864f64d";

	/**
	 * 
	 * 方法: getAirCityData <br>
	 * 描述: TODO
	 * 
	 * @return
	 * @see cn.com.ddhj.service.ICityAirService#getAirCityData()
	 */
	@Override
	public JSONArray getAirCityData() {
		String url = "http://web.juhe.cn:8080/environment/air/airCities";
		JSONObject obj = null;
		JSONArray array = new JSONArray();
		Map<String, String> param = new HashMap<String, String>();
		param.put("key", KEY);
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

	/**
	 * 
	 * 方法: getPMCityData <br>
	 * 描述: TODO
	 * 
	 * @return
	 * @see cn.com.ddhj.service.ICityAirService#getPMCityData()
	 */
	@Override
	public JSONArray getPMCityData() {
		String url = "http://web.juhe.cn:8080/environment/air/airCities";
		JSONArray array = new JSONArray();
		JSONObject obj = null;
		Map<String, String> param = new HashMap<String, String>();
		param.put("key", KEY);
		String result = PureNetUtil.post(url, param);
		if (result != null && !"".equals(result)) {
			obj = JSONObject.parseObject(result);
			if (obj.getInteger("resultCode") == 200) {
				array = JSONArray.parseArray(obj.getString("result"));
			}
		}
		return array;
	}

	/**
	 * 
	 * 方法: getCityPM <br>
	 * 描述: TODO
	 * 
	 * @param cityName
	 * @return
	 * @see cn.com.ddhj.service.ICityAirService#getCityPM(java.lang.String)
	 */
	@Override
	public JSONObject getCityPM(String cityName) {
		String url = "http://web.juhe.cn:8080/environment/air/pm";
		JSONObject obj = null;
		if (isPMCity(cityName)) {
			Map<String, String> param = new HashMap<String, String>();
			param.put("key", KEY);
			param.put("city", cityName);
			String result = PureNetUtil.post(url, param);
			if (result != null && !"".equals(result)) {
				obj = JSONObject.parseObject(result);
			} else {
				obj = new JSONObject();
				obj.put("code", "-1");
				obj.put("message", "查询数据为空");
			}
		} else {
			obj = new JSONObject();
			obj.put("code", "-1");
			obj.put("message", "查询城市无法查询pm2.5信息");
		}

		return obj;
	}

	/**
	 * 
	 * 方法: getCityNowAir <br>
	 * 描述: TODO
	 * 
	 * @param cityName
	 * @return {"date":"2016-10-04 20:00","city":"北京","AQI":"57","quality":"良"}
	 * @see cn.com.ddhj.service.ICityAirService#getCityNowAir(java.lang.String)
	 */
	@Override
	public JSONObject getCityNowAir(String cityName) {
		JSONObject air = getCityAir(cityName);
		if (air != null && air.getInteger("resultcode") == 200) {
			JSONArray result = JSONArray.parseArray(air.getString("result"));
			return JSONObject.parseObject(result.getJSONObject(0).getString("citynow"));
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 方法: getLastWeekAir <br>
	 * 描述: TODO
	 * 
	 * @param cityName
	 * @return
	 * @see cn.com.ddhj.service.ICityAirService#getLastWeekAir(java.lang.String)
	 */
	@Override
	public JSONArray getLastWeekAir(String cityName) {
		JSONObject air = getCityAir(cityName);
		JSONArray array = new JSONArray();
		if (air != null && air.getInteger("resultcode") == 200) {
			JSONArray result = JSONArray.parseArray(air.getString("result"));
			JSONObject weeks = JSONObject.parseObject(result.getJSONObject(0).getString("lastTwoWeeks"));
			if (weeks != null) {
				for (int i = 1; i <= 7; i++) {
					JSONObject obj = weeks.getJSONObject(i + "");
					array.add(obj);
				}
			}
		}
		return array;
	}

	/**
	 * 
	 * 方法: getCityAir <br>
	 * 描述: 获取城市空气质量数据 <br>
	 * | 分属：(PM2.5 )空气质量接口；聚合水质量接口右侧的接口 作者: zhy<br>
	 * 时间: 2016年10月4日 下午8:00:21
	 * 
	 * @param cityName
	 * @return
	 */
	private static JSONObject getCityAir(String cityName) {
		String url = "http://web.juhe.cn:8080/environment/air/cityair";
		JSONObject obj = null;
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("key", KEY);
			String city = URLEncoder.encode(cityName, "UTF-8");
			param.put("city", city);
			String result = PureNetUtil.post(url, param);
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

	/**
	 * 
	 * 方法: isAirCity <br>
	 * 描述: 判断查询城市是否在空气质量查询城市列表 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月1日 下午9:20:29
	 * 
	 * @param cityName
	 * @return
	 */
	public boolean isAirCity(String cityName) {
		boolean flag = false;
		JSONArray array = getAirCityData();
		if (array != null && array.size() > 0) {
			for (int i = 0; i < array.size(); i++) {
				JSONObject obj = array.getJSONObject(i);
				if (cityName.equals(obj.getString("name"))) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 
	 * 方法: isPMCity <br>
	 * 描述: 判断查询城市是否在PM查询城市列表 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月1日 下午9:25:06
	 * 
	 * @param cityName
	 * @return
	 */
	public boolean isPMCity(String cityName) {
		boolean flag = false;
		JSONArray array = getPMCityData();
		if (array != null && array.size() > 0) {
			for (int i = 0; i < array.size(); i++) {
				JSONObject obj = array.getJSONObject(i);
				if (cityName.equals(obj.getString("name"))) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 
	 * 方法: getAQILevel <br>
	 * 描述: TODO
	 * 
	 * @return
	 * @see cn.com.ddhj.service.ICityAirService#getAQILevel()
	 */
	@Override
	public Integer getAQILevel(String cityName) {
		Integer level = 1;
		JSONObject air = getCityAir(cityName);
		if (air != null) {
			JSONArray result = JSONArray.parseArray(air.getString("result"));
			if (result != null && result.size() > 0) {
				JSONObject citynow = JSONObject.parseObject(result.getJSONObject(0).getString("citynow"));
				Integer aqi = citynow.getInteger("AQI");
				if (aqi >= 50 && aqi <= 100) {
					level = 2;
				} else if (aqi >= 101 && aqi <= 150) {
					level = 3;
				} else if (aqi >= 151 && aqi <= 200) {
					level = 4;
				} else if (aqi >= 201 && aqi <= 300) {
					level = 5;
				} else if (aqi > 301) {
					level = 6;
				}
			}
		}
		return level;
	}

	/**
	 * @descriptions 返回当前时间的Aqi数据以及历史7天的Aqi数据
	 *
	 * @param cityName
	 * @return
	 * @date 2016年10月4日 下午8:55:07
	 * @author Yangcl
	 * @version 1.0.0.1
	 */
	public CityAqi getCityAqi(String cityName) {
		CityAqi e = new CityAqi();
		e.setName(cityName);
		JSONObject air = getCityAir(cityName);
		if (air != null && air.getInteger("resultcode") == 200) {
			JSONArray result = JSONArray.parseArray(air.getString("result"));
			CityAqiData data = JSONObject.parseObject(
					JSONObject.parseObject(result.getJSONObject(0).getString("citynow")).toJSONString(),
					CityAqiData.class);
			e.setEntity(data);
			JSONArray array = new JSONArray();
			JSONObject weeks = JSONObject.parseObject(result.getJSONObject(0).getString("lastTwoWeeks"));
			if (weeks != null) {
				String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				int m = 23;
				int n = 28;
				// 聚合接口会返回错误数据。
				if (weeks.getJSONObject("28") != null && weeks.getJSONObject("28").getString("date").equals(today)) {
					m--;
					n--;
				}
				for (int i = m; i <= n; i++) {
					JSONObject obj = weeks.getJSONObject(i + "");
					array.add(obj);
				}
				List<CityAqiData> list = JSONObject.parseArray(array.toJSONString(), CityAqiData.class);
				CityAqiData cad = new CityAqiData();
				cad.setDate(today);
				cad.setCity(data.getCity());
				cad.setAQI(data.getAQI());
				cad.setQuality(data.getQuality());
				list.add(cad);
				e.setList(list);
			}
		}else{
			String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			CityAqiData c = new CityAqiData();
			c.setDate(today);
			c.setCity(cityName);
			c.setAQI("80");
			c.setQuality("II"); 
			
			List<CityAqiData> el = new ArrayList<CityAqiData>();
			for(int i = 1 ; i < 8 ; i ++){
				int flag = Integer.valueOf("-" + i);
				CityAqiData a = new CityAqiData();
				String day = new SimpleDateFormat("yyyy-MM-dd").format(this.dateCount(new Date(), flag)); 
				a.setDate(day);
				a.setCity(cityName);
				Integer aqi = 20 + new Random().nextInt(100) + 1;
				a.setAQI(aqi.toString());
				a.setQuality("II"); 
				el.add(a);
			}
			e.setEntity(c);
			e.setList(el); 
		}
		return e;
	}

	private Date dateCount(Date date , int flag){
	    Calendar calendar = Calendar.getInstance();       // 日历对象
	    calendar.setTime(date);       // 设置当前日期
	    calendar.add(Calendar.DATE , flag); // 月份减一 或 加一
	    return calendar.getTime();
	}
	
	/**
	 * @descriptions【 聚合接口】->【天气预报】(免费接口)
	 *
	 * @param city
	 * @return
	 * @date 2016年10月5日 上午12:35:34
	 * @author Yangcl
	 * @version 1.0.0.1
	 */
	private JSONObject getCityWeather(String city) {
		String url = "http://op.juhe.cn/onebox/weather/query";
		JSONObject obj = null;
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("key", "cb6b71e3dc87bb434da0a8babf701936");
			param.put("cityname", city);
			String result = PureNetUtil.get(url, param);
			if (result != null && !"".equals(result)) {
				obj = JSONObject.parseObject(result);
				if(obj.getString("resultcode").equals("202")){
					obj.put("code", "-3"); // 查询不到该城市的信息
				}
			} else {
				obj = new JSONObject();
				obj.put("code", "-1");
				obj.put("message", "查询数据为空");
				obj.put("reason", "error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj = new JSONObject();
			obj.put("code", "-2");
			obj.put("message", "查询数据错误");
			obj.put("reason", "error");
		}
		return obj;
	}

	/**
	 * @descriptions 获取天气信息
	 *
	 * @param city
	 * @return
	 * @date 2016年10月5日 上午1:09:38
	 * @author Yangcl
	 * @version 1.0.0.1
	 */
	public JSONObject getWeatherInfo(String city) {
		JSONObject res = new JSONObject();

		JSONObject obj = this.getCityWeather(city);
		if (obj.getString("reason").equals("successed!")) {
			JSONObject result = JSONObject.parseObject(obj.getString("result"));
			JSONObject data = JSONObject.parseObject(result.getString("data"));
			JSONObject realtime = JSONObject.parseObject(data.getString("realtime"));
			// {"img":"3","temperature":"14","humidity":"92","info":"阵雨"}
			JSONObject weather = JSONObject.parseObject(realtime.getString("weather"));
			JSONObject wind = JSONObject.parseObject(realtime.getString("wind"));
			JSONObject pm25 = JSONObject.parseObject(data.getString("pm25"));
			// {"des":"可正常活动。","curPm":"39","pm25":"27","level":1,"pm10":"55","quality":"优"}
			JSONObject pm25_ = JSONObject.parseObject(pm25.getString("pm25"));
			String info = weather.getString("info");
			String humidity = weather.getString("humidity"); // 湿度
			String temperature = weather.getString("temperature");  // 温度 
			String wind_ = wind.getString("direct") + "/" + wind.getString("power");
			String quality = pm25_.getString("quality");
			String des = pm25_.getString("des");
			if (StringUtils.isBlank(info)) {
				info = "天气还行";
			}
			if (StringUtils.isBlank(wind_)) {
				wind_ = "西北风/2级";
			}
			if (StringUtils.isBlank(quality)) {
				quality = "优";
			}
			if (StringUtils.isBlank(des)) {
				des = "可正常活动";
			}

			res.put("info", info); // 阵雨
			res.put("humidity", humidity);
			res.put("temperature", temperature);  
			res.put("updateTime", realtime.getString("time"));       
			res.put("wind", wind_); // 东南风/2级
			res.put("quality", quality); // 优|轻度污染
			res.put("des", des); // 可正常活动。
			return res;
		}else{
			if(obj.getString("code").equals("-1")){
				res.put("info", "天气一般"); // 阵雨
				res.put("wind", "1级"); // 东南风/2级
				res.put("quality", "优"); // 优|轻度污染
				res.put("des", "可正常活动"); // 可正常活动。
			}else{
				res.put("info", "无天气信息"); // 阵雨
				res.put("wind", "2级"); // 东南风/2级
				res.put("quality", "优"); // 优|轻度污染
				res.put("des", "查询不到该城市的信息"); // 可正常活动。
			}
			return res;
		}
	}
}
