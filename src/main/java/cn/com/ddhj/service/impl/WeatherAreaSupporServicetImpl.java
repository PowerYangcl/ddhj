package cn.com.ddhj.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.WeatherAreaSupportDto;
import cn.com.ddhj.mapper.IWeatherAreaSupportMapper;
import cn.com.ddhj.model.WeatherAreaSupport;
import cn.com.ddhj.result.weatherArea.HourAqiResult;
import cn.com.ddhj.result.weatherArea.WeatherAreaSupportResult;
import cn.com.ddhj.service.IWeatherAreaSupportService;
import cn.com.ddhj.util.PureNetUtil;

/**
 * @descriptions 7*24小时城市天气空气质量预报|根据输入经纬度逐小时查询AQI，根据经纬度查询每日AQI 均值
 *
 * @date 2016年10月3日 下午11:20:20
 * @author Yangcl 
 * @version 1.0.1
 */
@Service
public class WeatherAreaSupporServicetImpl 
				extends BaseServiceImpl<WeatherAreaSupport , IWeatherAreaSupportMapper , WeatherAreaSupportDto>
				implements IWeatherAreaSupportService{
	
	@Autowired
	private IWeatherAreaSupportMapper mapper;

	/**
	 * @descriptions 7*24小时城市天气空气质量预报|地区信息列表|请求此接口数据并入库
	 *
	 * @date 2016年10月3日 下午9:41:09
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public void findWeatherAreaSupport() {
		String key = "d0949e3362e8a98f426273b7763fec1e";
		String url = "http://v.juhe.cn/xiangji_weather/areaList.php";
		Map<String, String> param = new HashMap<String, String>(); 		 
		param.put("key", key);	
		String responseJson = PureNetUtil.get(url , param);
		if (responseJson != null && !"".equals(responseJson)){
			WeatherAreaSupportResult w = JSON.parseObject(responseJson, WeatherAreaSupportResult.class); 
			if(w.getReason().equals("success")){
				List<WeatherAreaSupport> list = w.getResult();
				for(WeatherAreaSupport e : list){
					mapper.insertSelective(e);
				}
			}
		} 
	}

	/**
	 * @descriptions 查库找到支持地区列表
	 *
	 * @return
	 * @date 2016年10月3日 下午9:53:12
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject findSupportAreas() {
		JSONObject result = new JSONObject();
		List<WeatherAreaSupport> list = mapper.findSupportAreas();
		if(list != null && list.size() > 0){
			result.put("code" , "1");
			result.put("list" , list);
		}else{
			result.put("code", "0");
			result.put("msg", "数据查询返回空");
		}
		return result;
	}

	
	/**
	 * @descriptions 7*24小时城市天气空气质量预报|根据输入经纬度逐小时查询AQI
	 *
	 * @param lon 经度(小数位数自行确定，一般不少于四位)如:121.4042
	 * @param lat	纬度(小数位数自行确定，一般不少于四位)如:121.4042
	 * @param startTime 预报开始时间(yyyyMMddHH)如: 2016100312
	 * @param endTime 预报结束时间(yyyyMMddHH)如: 2016100313
	 * @return aqi
	 * @date 2016年10月3日 下午11:25:56
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public String findHourAqi(String lon, String lat, String startTime, String endTime) {
		String key = "d0949e3362e8a98f426273b7763fec1e";
		String url = "http://v.juhe.cn/xiangji_weather/aqi_byCoo.php";
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("lon", lon);					 
		param.put("lat", lat);					 
		param.put("key", key);				 
		param.put("startTime", startTime);				 
		param.put("endTime", endTime);		 
		 
		String responseJson = PureNetUtil.post(url , param);
		if (responseJson != null && !"".equals(responseJson)) {
			HourAqiResult har = JSON.parseObject(responseJson, HourAqiResult.class);
			if(har.getReason().equals("success")){
				return har.getResult().getSeries().get(0).toString();
			}
		}
		return "-1";
	}

	/**
	 * @descriptions 7*24小时城市天气空气质量预报|根据经纬度查询每日AQI 均值
	 *
	 * @param lon 经度(小数位数自行确定，一般不少于四位)如:121.4042
	 * @param lat	纬度(小数位数自行确定，一般不少于四位)如:121.4042
	 * @param startTime 预报开始时间(yyyyMMdd)如: 20161003
	 * @param endTime 预报结束时间(yyyyMMdd)如: 20161004
	 * @return aqi
	 * @date 2016年10月3日 下午11:25:56
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public String findDayAqi(String lon, String lat, String startTime, String endTime) {
		String key = "d0949e3362e8a98f426273b7763fec1e";
		String url = "http://v.juhe.cn/xiangji_weather/aqi_average_byCoor.php";
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("longitude", lon);					 
		param.put("latitude", lat);					 
		param.put("key", key);				 
		param.put("startTime", startTime);				 
		param.put("endTime", endTime);		 
		 
		String responseJson = PureNetUtil.post(url , param);
		if (responseJson != null && !"".equals(responseJson)) {
			HourAqiResult har = JSON.parseObject(responseJson, HourAqiResult.class);
			if(har.getReason().equals("success")){
				return har.getResult().getSeries().get(0).toString();
			}
		}
		return "-1";
	}

	/**
	 * @descriptions 全国天气预报|根据GPS坐标查询天气
	 *
	 * @param lon 经度，如：116.39277
	 * @param lat 纬度，如：39.933748
	 * @return
	 * @date 2016年10月7日 下午8:48:52
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject getWeatherWithPosition(String lon, String lat) {
		String key = "d915236d734c8a417e48116ee70a4257";
		String url = "http://v.juhe.cn/weather/geo";
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("lon", lon);					 
		param.put("lat", lat);					 
		param.put("key", key);		
//		param.put("format", "1");	
		param.put("dtype", "json");	
		
		JSONObject obj = new JSONObject();
		String responseJson = PureNetUtil.post(url , param);
		if (responseJson != null && !"".equals(responseJson)) {
			obj = JSONObject.parseObject(responseJson);
		}
		
		return obj;
	}
	
	
	
	
}









































