package cn.com.ddhj.service;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.EstateInfoDto;
import cn.com.ddhj.model.EstateInfo;

public interface IEstateInfoService extends IBaseService<EstateInfo, EstateInfoDto>{
	
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
} 


































