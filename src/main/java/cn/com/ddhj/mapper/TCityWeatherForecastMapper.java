package cn.com.ddhj.mapper;

import java.util.Map;

import cn.com.ddhj.model.TCityWeatherForecast;

public interface TCityWeatherForecastMapper {
	
	public Integer insertSelective(TCityWeatherForecast entity);
	
	public Integer updateSelective(TCityWeatherForecast entity);
	
	/**
	 * @descriptions 根据City 和Area来定位一条记录
	 *
	 * @param map
	 * @date 2016年12月7日 下午10:16:34
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public TCityWeatherForecast selectByCityArea(Map<String ,String> map);
}
