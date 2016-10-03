package cn.com.ddhj.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.WeatherAreaSupportDto;
import cn.com.ddhj.model.WeatherAreaSupport;

public interface IWeatherAreaSupportService extends IBaseService<WeatherAreaSupport , WeatherAreaSupportDto>{
	
	/**
	 * @descriptions 7*24小时城市天气空气质量预报|地区信息列表|请求此接口数据并入库
	 *
	 * @date 2016年10月3日 下午9:41:09
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public void findWeatherAreaSupport();
	
	
	/**
	 * @descriptions 查库找到支持地区列表
	 *
	 * @return
	 * @date 2016年10月3日 下午9:53:12
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject findSupportAreas();
	
	
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
	public String findHourAqi(String lon , String lat , String startTime , String endTime);
	
	
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
	public String findDayAqi(String lon , String lat , String startTime , String endTime);
}































