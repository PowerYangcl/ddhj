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
}
