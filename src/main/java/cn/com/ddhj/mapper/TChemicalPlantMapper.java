package cn.com.ddhj.mapper;

import cn.com.ddhj.dto.TChemicalPlantDto;
import cn.com.ddhj.model.TChemicalPlant;

/**
 * 
 * 类: TChemicalPlantMapper <br>
 * 描述: 化工厂数据库访问接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月4日 上午10:52:00
 */
public interface TChemicalPlantMapper extends BaseMapper<TChemicalPlant, TChemicalPlantDto> {

	/**
	 * 
	 * 方法: getMaxTChemicalPlant <br>
	 * 描述: 获取小于dto经纬度的化工厂中最大的数据 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月4日 上午11:46:55
	 * 
	 * @param dto
	 * @return
	 */
	TChemicalPlant getMaxTChemicalPlant(TChemicalPlantDto dto);

	/**
	 * 
	 * 方法: getMaxTChemicalPlant <br>
	 * 描述: 获取大于dto经纬度的化工厂中最小的数据 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月4日 上午11:46:55
	 * 
	 * @param dto
	 * @return
	 */
	TChemicalPlant getMinTChemicalPlant(TChemicalPlantDto dto);
}