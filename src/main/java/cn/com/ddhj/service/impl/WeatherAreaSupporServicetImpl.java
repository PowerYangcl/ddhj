package cn.com.ddhj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.WeatherAreaSupportDto;
import cn.com.ddhj.mapper.IWeatherAreaSupportMapper;
import cn.com.ddhj.model.WeatherAreaSupport;
import cn.com.ddhj.result.weatherArea.WeatherAreaSupportResult;
import cn.com.ddhj.service.IWeatherAreaSupportService;
import cn.com.ddhj.util.PureNetUtil;

@Service
public class WeatherAreaSupporServicetImpl 
				extends BaseServiceImpl<WeatherAreaSupport , IWeatherAreaSupportMapper , WeatherAreaSupportDto>
				implements IWeatherAreaSupportService{
	
	@Autowired
	private IWeatherAreaSupportMapper mapper;

	/**
	 * @descriptions 7*24小时城市天气空气质量预报|地区信息列表|请求此接口数据并入库
	 *
	 * @date 2016年10月3日 下午9:41:09
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public void findWeatherAreaSupport() {
		String key = "d0949e3362e8a98f426273b7763fec1e";
		String url = "http://v.juhe.cn/xiangji_weather/areaList.php";
		Map<String, String> param = new HashMap<String, String>(); 		 
		param.put("key", key);	
		String responseJson = PureNetUtil.get(url , param);
		if (responseJson != null && !"".equals(responseJson)){
			WeatherAreaSupportResult w = JSON.parseObject(responseJson, WeatherAreaSupportResult.class); 
			if(w.getReason().equals("success")){
				List<WeatherAreaSupport> list = w.getResult();
				for(WeatherAreaSupport e : list){
					mapper.insertSelective(e);
				}
			}
		} 
	}

	/**
	 * @descriptions 查库找到支持地区列表
	 *
	 * @return
	 * @date 2016年10月3日 下午9:53:12
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject findSupportAreas() {
		JSONObject result = new JSONObject();
		List<WeatherAreaSupport> list = mapper.findSupportAreas();
		if(list != null && list.size() > 0){
			result.put("code" , "1");
			result.put("list" , list);
		}else{
			result.put("code", "0");
			result.put("msg", "数据查询返回空");
		}
		return result;
	}
	
	
	
	
}















