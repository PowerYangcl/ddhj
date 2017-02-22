package cn.com.ddhj.mapper;

import java.util.List;

import cn.com.ddhj.model.THighVoltage;

public interface THighVoltageMapper {
 
	/**
	 * @description: 根据城市列表找到污染源集合
	 * 
	 * @param city
	 * @return
	 * @author Yangcl 
	 * @date 2016年10月17日 下午6:04:33 
	 * @version 1.0.0.1
	 */
	public List<THighVoltage> findListByCity(String city);
}