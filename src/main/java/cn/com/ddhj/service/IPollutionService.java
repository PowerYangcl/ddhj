package cn.com.ddhj.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @descriptions 城市污染查询|支持城市列表，污染源排放口列表，城市污染源列表，获取排口排放情况
 *
 * @date 2016年10月3日 下午4:23:59
 * @author Yangcl 
 * @version 1.0.1
 */
public interface IPollutionService {
	
	
	/**
	 * @descriptions 城市污染查询|支持城市列表 - 在进行城市污染查询时，首先找到该接口支持的城市
	 *
	 * @return
	 * @date 2016年10月3日 下午4:53:01
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject supportCityInfo();
	
	
}
