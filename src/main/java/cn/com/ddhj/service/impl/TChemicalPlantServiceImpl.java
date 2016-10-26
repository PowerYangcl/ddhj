package cn.com.ddhj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.dto.TChemicalPlantDto;
import cn.com.ddhj.mapper.TChemicalPlantMapper;
import cn.com.ddhj.model.TChemicalPlant;
import cn.com.ddhj.service.ITChemicalPlantService;
import cn.com.ddhj.util.CommonUtil;

/**
 * 
 * 类: TChemicalPlantServiceImpl <br>
 * 描述: 化工厂业务逻辑处理接口实现类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月26日 下午3:15:13
 */
@Service
public class TChemicalPlantServiceImpl extends BaseServiceImpl<TChemicalPlant, TChemicalPlantMapper, TChemicalPlantDto>
		implements ITChemicalPlantService {

	@Autowired
	private TChemicalPlantMapper mapper;

	@Override
	public int chemicalLevel(String city, String lat, String lng) {
		int level = 1;
		TChemicalPlantDto dto = new TChemicalPlantDto();
		dto.setCity(city);
		dto.setLat(lat);
		dto.setLng(lng);
		TChemicalPlant model = this.getTChemicalPlant(dto);
		Double ll = CommonUtil.getDistanceFromLL(Double.valueOf(dto.getLat()), Double.valueOf(dto.getLng()),
				Double.valueOf(model.getLat()), Double.valueOf(model.getLng()));
		if (ll > 1000) {
			level = 2;
		}
		return level;
	}

	private TChemicalPlant getTChemicalPlant(TChemicalPlantDto dto) {
		TChemicalPlant max = mapper.getMaxTChemicalPlant(dto);
		TChemicalPlant min = mapper.getMinTChemicalPlant(dto);
		Double maxLL = Double.valueOf(dto.getLat()) - Double.valueOf(max.getLat());
		Double minLL = Double.valueOf(min.getLat()) - Double.valueOf(dto.getLat());
		if (maxLL < minLL) {
			return max;
		} else {
			return min;
		}
	}

}
