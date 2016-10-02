package cn.com.ddhj.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.EstateInfoDto;
import cn.com.ddhj.mapper.IEstateInfoMapper;
import cn.com.ddhj.model.EstateInfo;
import cn.com.ddhj.result.estateInfo.CityResult;
import cn.com.ddhj.result.estateInfo.EstateResult;
import cn.com.ddhj.service.IEstateInfoService;
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
public class EstateInfoServiceImpl 
						  extends BaseServiceImpl<EstateInfo, IEstateInfoMapper, EstateInfoDto>
						  implements IEstateInfoService{

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
	
	
	
	
	
}


































