package cn.com.ddhj.service;

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

	int chemicalLevel(String city, String lat, String lng);
}
