package cn.com.ddhj.service.impl;

import java.util.HashMap;
import java.util.Map;

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

	/**
	 * 
	 * 方法: getChemical (一共两级,1级最好)<br>
	 * @param city
	 * @param lat
	 * @param lng
	 * @return 
	 * @see cn.com.ddhj.service.ITChemicalPlantService#getChemical(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, String> getChemical(String city, String lat, String lng) {
		Map<String, String> map = new HashMap<String, String>();
		int level = 2;
		TChemicalPlantDto dto = new TChemicalPlantDto();
		dto.setCity(city);
		dto.setLat(lat);
		dto.setLng(lng);
		TChemicalPlant model = this.getTChemicalPlant(dto);
		if (model != null) {
			Double ll = CommonUtil.getDistanceFromLL(Double.valueOf(dto.getLat()), Double.valueOf(dto.getLng()),
					Double.valueOf(model.getLat()), Double.valueOf(model.getLng()));
			map.put("distance", String.valueOf(ll));
			if (ll > 1000) {
				level = 1;
			}
		}
		map.put("level", String.valueOf(level));
		return map;
	}

	private TChemicalPlant getTChemicalPlant(TChemicalPlantDto dto) {
		TChemicalPlant model = null;
		TChemicalPlant max = mapper.getMaxTChemicalPlant(dto);
		TChemicalPlant min = mapper.getMinTChemicalPlant(dto);
		if (max != null && min == null) {
			model = max;
		} else if (max == null && min != null) {
			model = min;
		} else if (max != null && min != null) {
			Double maxLL = Double.valueOf(dto.getLat()) - Double.valueOf(max.getLat());
			Double minLL = Double.valueOf(min.getLat()) - Double.valueOf(dto.getLat());
			if (maxLL < minLL) {
				model = max;
			} else {
				model = min;
			}
		}
		return model;
	}

}
