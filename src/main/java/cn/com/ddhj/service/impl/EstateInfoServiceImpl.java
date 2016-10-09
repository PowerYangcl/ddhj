package cn.com.ddhj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.landedProperty.LandLatLngDto;
import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.result.estateInfo.CityResult;
import cn.com.ddhj.result.estateInfo.EData;
import cn.com.ddhj.result.estateInfo.EstateResult;
import cn.com.ddhj.service.IEstateInfoService;
import cn.com.ddhj.util.CommonUtil;
import cn.com.ddhj.util.PureNetUtil;


/**
 * @descriptions 地产数据|地产检索接口，周边地产接口，支持城市列表接口
 * 
 * TODO 地产周边接口有问题，无法取得有效信息
 *
 * @date 2016年10月2日 下午10:11:49
 * @author Yangcl 
 * @version 1.0.1
 */
@Service
public class EstateInfoServiceImpl implements IEstateInfoService{

	@Resource
	private TLandedPropertyMapper lpMapper;
	
	/**
	 * @descriptions 支持查询地产信息的城市列表
	 *
	 * @return
	 * @date 2016年10月2日 下午10:17:21
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject supportEstateInfoCity() {
		String key = "f74ac8fbf0d992b02420a03387ed8341";
		String url = "http://v.juhe.cn/estate/citys";
		JSONObject result = new JSONObject();
		
		Map<String, String> param = new HashMap<String, String>(); 		 
		param.put("key", key);	
		String responseJson = PureNetUtil.post(url , param);
		if (responseJson != null && !"".equals(responseJson)){
			// 如果状态=200时
			CityResult cr = JSON.parseObject(responseJson, CityResult.class); 
			if(cr.getResultcode().equals("200")){
				result.put("code" , "1");
				result.put("msg" , "SUCCESS");
				result.put("list" , cr.getResult()); 
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
	public JSONObject estateInfoList(String city, String keyword, int page) {
		String key = "f74ac8fbf0d992b02420a03387ed8341";
		String url = "http://v.juhe.cn/estate/query";
		JSONObject result = new JSONObject();
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("city", city);					 
		param.put("q", keyword);					 
		param.put("key", key);				 
		param.put("page", String.valueOf(page)); 				 
		param.put("dtype", "json");		 
		 
		String responseJson = PureNetUtil.post(url , param);
		if (responseJson != null && !"".equals(responseJson)) {
			// 如果状态=200时
			EstateResult cr = JSON.parseObject(responseJson, EstateResult.class); 
			if(cr.getResultcode().equals("200")){
				result.put("code" , "1");
				result.put("msg" , "SUCCESS");
				result.put("list" , cr.getResult()); 
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

	
	public JSONObject estateInfoList(String lng, String lat  , String page , String radius) {
		double[] around = CommonUtil.getAround(Double.valueOf(lat), Double.valueOf(lng), Integer.valueOf(radius)); 
		LandLatLngDto dto = new LandLatLngDto(); 
		dto.setPage(Integer.valueOf(page));
		dto.setMinLat(String.valueOf(around[0]));
		dto.setMinLng(String.valueOf(around[1]));
		dto.setMaxLat(String.valueOf(around[2]));
		dto.setMaxLng(String.valueOf(around[3]));
		
		List<EData> list = lpMapper.findLandedPropertyAll(dto);
		JSONObject result = new JSONObject();
		if (list != null && list.size() > 0) {
			result.put("code" , "1");
			result.put("msg" , "SUCCESS");
			result.put("list" , JSONObject.toJSON(list));  
			
		}else{
			result.put("code" , "0");
			result.put("msg" , "聚合接口响应数据为空");
		}
		
		return result;
	}
	
	
	/**
	 * @descriptions 地产检索|周边地产，根据经纬度确定周边的地产列表，范围10Km 
	 *
	 * @param lng 经度
	 * @param lat 纬度
	 * @return
	 * @date 2016年10月4日 下午6:14:15
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject estateInfoList2(String lng, String lat  , String page , String radius) {
		String key = "f74ac8fbf0d992b02420a03387ed8341";
		String url = "http://v.juhe.cn/estate/local";
		
		JSONObject result = new JSONObject();
		Map<String, String> param = new HashMap<String, String>();
		param.put("lng", lng);					// 经度 (如：119.9772857)
		param.put("lat", lat);					// 纬度 (如：27.327578)
		param.put("radius", radius);		// 检索半径，默认5000 米
		param.put("page", page);				// 页数，默认1,每页返回20条 
		param.put("key", key);			 
		 
		String responseJson = PureNetUtil.get(url , param);
		if (responseJson != null && !"".equals(responseJson)) {
			// 如果状态=200时
			EstateResult cr = JSON.parseObject(responseJson, EstateResult.class); 
			if(cr.getResultcode().equals("200")){
				result.put("code" , "1");
				result.put("msg" , "SUCCESS");
				result.put("list" , JSONObject.toJSON(cr.getResult())); 
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


































