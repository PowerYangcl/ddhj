package cn.com.ddhj.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.result.pollution.PollutionDistributionResult;
import cn.com.ddhj.result.pollution.PollutionSupportCity;
import cn.com.ddhj.service.IPollutionService;
import cn.com.ddhj.util.PureNetUtil;

/**
 * @descriptions 城市污染查询|支持城市列表，污染源排放口列表，城市污染源列表，获取排口排放情况
 *
 * @date 2016年10月3日 下午4:23:59
 * @author Yangcl 
 * @version 1.0.1
 */
@Service
public class PollutionServiceImpl implements IPollutionService {

	/**
	 * @descriptions 城市污染查询|支持城市列表 - 在进行城市污染查询时，首先找到该接口支持的城市
	 *
	 * @return
	 * @date 2016年10月3日 下午4:53:01
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject supportCityInfo() {
		String key = "2172ed8e0b84a9dfa024c684b57b512d";
		String url = "http://pollution.api.juhe.cn/jhapi/pollution/cityList";
		JSONObject result = new JSONObject();
		
		Map<String, String> param = new HashMap<String, String>(); 		 
		param.put("key", key);	
		String responseJson = PureNetUtil.post(url , param);
		if (responseJson != null && !"".equals(responseJson)){
			// 如果状态=200时
			PollutionSupportCity cr = JSON.parseObject(responseJson , PollutionSupportCity.class);
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
	 * @descriptions 城市污染查询|城市污染源列表
	 *
	 		"EntCode":2410,  @污染源编码
			"EntName":"中国石油化工股份有限公司济南分公司",
			"GasStnums":2,		@废气排口数量
			"Lat":36.703014460635,
			"Lon":117.16555233045,
			"WaterStnums":1 @废水排口数量
	 *
	 * @param city 参数依赖 @supportCityInfo()接口中的code
	 * @return
	 * @date 2016年10月3日 下午7:37:07
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject findPollutionDistributionList(String city) {
		String key = "2172ed8e0b84a9dfa024c684b57b512d";
		String url = "http://pollution.api.juhe.cn/jhapi/pollution/distribution";
		JSONObject result = new JSONObject();
		
		Map<String, String> param = new HashMap<String, String>(); 		 
		param.put("key", key);	
		param.put("city", city);
		String responseJson = PureNetUtil.post(url , param);
		if (responseJson != null && !"".equals(responseJson)){
			// 如果状态=200时
			PollutionDistributionResult cr = JSON.parseObject(responseJson , PollutionDistributionResult.class);
			if(cr.getReason().equals("success")){
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


















