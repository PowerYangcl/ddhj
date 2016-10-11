package cn.com.ddhj.service;

import com.alibaba.fastjson.JSONObject;


public interface IEstateInfoService {
	
	/**
	 * @descriptions 支持查询地产信息的城市列表
	 *
	 * @return
	 * @date 2016年10月2日 下午10:17:21
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject supportEstateInfoCity();
	
	/**
	 * @descriptions 地产检索|查询支持城市的地产信息
	 *
	 * @param city			城市名称，需要用supportEstateInfoCity()接口中支持的城市，必传字段
	 * @param keyword   地产名关键字，非必传字段
	 * @param page			显示第几页，默认为显示第一页(每页返回10条)的数据
	 * @return
	 * @date 2016年10月2日 下午11:10:08
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject estateInfoList(String city , String keyword , int page);
	
	
	/**
	 * @descriptions 地产检索|周边地产，根据经纬度确定周边的地产列表，范围10Km 
	 *
	 * @param lng 经度
	 * @param lat 纬度
	 * @param page 当前第几页
	 * @param count 当前页面显示条数
	 * @return
	 * @date 2016年10月4日 下午6:14:15
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject estateInfoList(String lng , String lat , String page , String count , String radius);
	
} 


































