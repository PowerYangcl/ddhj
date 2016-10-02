package cn.com.ddhj.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.LongitudeLatitudeDto;
import cn.com.ddhj.mapper.ILongitudeLatitudeMapper;
import cn.com.ddhj.model.LongitudeLatitude;
import cn.com.ddhj.result.llConvertion.ConversionResult;
import cn.com.ddhj.result.longitudeLatitude.LongitudeLatitudeResult;
import cn.com.ddhj.service.ILongitudeLatitudeService;
import cn.com.ddhj.util.PureNetUtil;

/**
 * @descriptions 经纬度地址解析 |地图坐标服务
 *  
 * @date 2016年10月2日 下午7:47:45
 * @author Yangcl 
 * @version 1.0.1
 */
public class LongitudeLatitudeServiceImpl  
						 extends BaseServiceImpl<LongitudeLatitude,ILongitudeLatitudeMapper, LongitudeLatitudeDto> 
						 implements ILongitudeLatitudeService {

	/**
	 * @descriptions 经纬度地址解析|获取当前地理位置信息
	 *
	 * @param lng 经度 (如：119.9772857)
	 * @param lat 纬度 (如：27.327578)
	 * @param type 传递的坐标类型,1:GPS 2:百度经纬度 3：谷歌经纬度
	 * @return
	 * @date 2016年10月2日 下午8:08:10
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject getCurrentPositionInfo(String lng , String lat , String type) {
		String key = "e06872cce776a7fd22b66251127512e7";
		String url = "http://apis.juhe.cn/geo/";
		
		JSONObject result = new JSONObject();
		Map<String, String> param = new HashMap<String, String>();
		param.put("lng", lng);					// 经度 (如：119.9772857)
		param.put("lat", lat);					// 纬度 (如：27.327578)
		param.put("key", key);				// 申请的APPKEY
		param.put("type", type);				// 传递的坐标类型,1:GPS 2:百度经纬度 3：谷歌经纬度
		param.put("dtype", "json");		// 返回数据格式：json或xml,默认json
		 
		String responseJson = PureNetUtil.post(url , param);
		if (responseJson != null && !"".equals(responseJson)) {
			// 如果状态=200时
			LongitudeLatitudeResult llr = JSON.parseObject(responseJson, LongitudeLatitudeResult.class); 
			if(llr.getResultcode().equals("200")){
				result.put("code" , "1");
				result.put("msg" , "SUCCESS");
				result.put("lng" , llr.getResult().getLng());
				result.put("lat" , llr.getResult().getLat());
				result.put("address" , llr.getResult().getAddress());
				result.put("business" , llr.getResult().getBusiness());
				result.put("citycode" , llr.getResult().getCitycode());

				result.put("country" , llr.getResult().getExt().getCountry());
				result.put("province" , llr.getResult().getExt().getProvince());
				result.put("city" , llr.getResult().getExt().getCity());
				result.put("district" , llr.getResult().getExt().getDistrict());
				result.put("adcode" , llr.getResult().getExt().getAdcode());
				result.put("street" , llr.getResult().getExt().getStreet());
				result.put("street_number" , llr.getResult().getExt().getStreet_number()); 
			}else{
				result.put("code" , "0");
				result.put("msg" , llr.getReason() + "|请联系后台开发人员调试");
			}
		}else{
			result.put("code" , "0");
			result.put("msg" , "聚合接口响应数据为空");
		}
		
		return result;
	}

	
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
	public JSONObject longitudeLatitudeConversion(String lng, String lat, String type) {
		String key = "8f24d556f66932f5efc2f57128a1e7fd";
		String url = "http://v.juhe.cn/offset/index";
		JSONObject result = new JSONObject();
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("lng", lng);					 
		param.put("lat", lat);					 
		param.put("key", key);				 
		param.put("type", type);				 
		param.put("dtype", "json");		 
		 
		String responseJson = PureNetUtil.post(url , param);
		if (responseJson != null && !"".equals(responseJson)) {
			// 如果状态=200时
			ConversionResult cr = JSON.parseObject(responseJson, ConversionResult.class); 
			if(cr.getResultcode().equals("200")){
				result.put("code" , "1");
				result.put("msg" , "SUCCESS");
				result.put("lng" , cr.getResult().getLng());
				result.put("lat" , cr.getResult().getLat());
				result.put("off_lng" , cr.getResult().getOff_lng());
				result.put("off_lat" , cr.getResult().getOff_lat());
				result.put("type", cr.getResult().getType());
			}else{
				result.put("code" , "0");
				result.put("msg" , cr.getReason() + "|请联系后台开发人员调试");
			}
		}else{
			result.put("code" , "0");
			result.put("msg" , "聚合接口响应数据为空");
		}
		
		return result;
	}
	
	
	

}












































