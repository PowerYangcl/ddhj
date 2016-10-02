package cn.com.ddhj.service;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.LongitudeLatitudeDto;
import cn.com.ddhj.model.LongitudeLatitude;

public interface ILongitudeLatitudeService  extends IBaseService<LongitudeLatitude, LongitudeLatitudeDto>{
	
	/**
	 * @descriptions 获取当前地理位置信息
	 *
	 * @param lng 经度 (如：119.9772857)
	 * @param lat 纬度 (如：27.327578)
	 * @param type 传递的坐标类型,1:GPS 2:百度经纬度 3：谷歌经纬度
	 * @return
	 * @date 2016年10月2日 下午8:08:10
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject getCurrentPositionInfo(String lng , String lat , String type);
	
	
	/**
	 * @descriptions 地图坐标服务|经纬度转换
	 *
	 * @param lng 经度 (如：119.9772857)
	 * @param lat 纬度 (如：27.327578)
	 * @param type 转换类型| 1：GPS->百度， 2：百度->GPS ，3：GPS->谷歌， 
	 * 											  4：谷歌->GPS， 5：百度->谷歌，6：谷歌->百度  
	 * @return
	 * @date 2016年10月2日 下午8:48:33
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject longitudeLatitudeConversion(String lng , String lat , String type);
}
