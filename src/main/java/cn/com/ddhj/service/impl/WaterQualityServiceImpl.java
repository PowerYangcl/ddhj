package cn.com.ddhj.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.service.IWaterQualityService;
import cn.com.ddhj.util.PureNetUtil;

/**
 * 
 * 类: WaterQualityServiceImpl <br>
 * 描述: 聚合数据水质量相关接口业务处理接口实现类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月3日 下午8:53:54
 */
public class WaterQualityServiceImpl implements IWaterQualityService {

	private static final String KEY = "935ddb80b6c973938852bd9e38d1777b";

	/**
	 * 
	 * 方法: getWaterFormState <br>
	 * 描述: TODO
	 * 
	 * @param state
	 * @return
	 * @see cn.com.ddhj.service.IWaterQualityService#getWaterFormState(java.lang.String)
	 */
	@Override
	public JSONObject getWaterFormState(String state) {
		JSONObject obj = null;
		String url = "http://v.juhe.cn/estate/query";
		Map<String, String> param = new HashMap<String, String>();
		param.put("state", state);
		param.put("key", KEY);
		String responseJson = PureNetUtil.post(url, param);
		if (responseJson != null && !"".equals(responseJson)) {
			obj = JSONObject.parseObject(responseJson);
			if (obj.getInteger("resultcode") == 200) {
				obj = JSONObject.parseObject(obj.getString("result"));
			}
		}
		return obj;
	}

	/**
	 * 
	 * 方法: getWaterLevel <br>
	 * 描述: TODO
	 * 
	 * @return
	 * @see cn.com.ddhj.service.IWaterQualityService#getWaterLevel()
	 */
	@Override
	public Integer getWaterLevel(String state) {
		Integer level = 1;
		JSONObject water = this.getWaterFormState(state);
		if (water != null) {
			String levelStr = water.getString("oxygenquality");
			if ("III".equals(levelStr)) {
				level = 2;
			} else if ("IV".equals(levelStr) || "V".equals(levelStr)) {
				level = 3;
			}
		}
		return level;
	}

}
