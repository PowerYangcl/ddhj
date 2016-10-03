package cn.com.ddhj.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 类: IWaterQualityService <br>
 * 描述: 聚合数据水质量相关接口业务处理接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月3日 下午8:51:49
 */
public interface IWaterQualityService {

	/**
	 * 
	 * 方法: getWaterFormState <br>
	 * 描述: 根据监测站点查询水质量 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月3日 下午8:52:43
	 * 
	 * @param state
	 * @return
	 */
	JSONObject getWaterFormState(String state);

	/**
	 * 
	 * 方法: getWaterLevel <br>
	 * 描述: 获取水质量等级 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月3日 下午9:18:12
	 * 
	 * @return
	 */
	Integer getWaterLevel(String state);
}
