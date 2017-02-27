package cn.com.ddhj.service;

import java.util.Map;

import cn.com.ddhj.dto.TChemicalPlantDto;
import cn.com.ddhj.model.TChemicalPlant;

/**
 * 
 * 类: ITChemicalPlantService <br>
 * 描述: 化工厂业务逻辑处理接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月26日 下午3:15:30
 */
public interface ITChemicalPlantService extends IBaseService<TChemicalPlant, TChemicalPlantDto> {

	/**
	 * 
	 * 方法: getChemical <br>
	 * 描述: 获取化工厂等级和距离 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年2月26日 上午12:04:35
	 * @param city
	 * @param lat
	 * @param lng
	 * @return
	 */
	Map<String, String> getChemical(String city, String lat, String lng);
}
