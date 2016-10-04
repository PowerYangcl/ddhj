package cn.com.ddhj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.dto.TRubbishRecyclingDto;
import cn.com.ddhj.mapper.TRubbishRecyclingMapper;
import cn.com.ddhj.model.TRubbishRecycling;
import cn.com.ddhj.service.ITRubbishRecyclingService;
import cn.com.ddhj.util.CommonUtil;

/**
 * 
 * 类: TRubbishRecyclingServiceImpl <br>
 * 描述: 垃圾回收站业务逻辑处理接口实现类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月4日 下午12:17:00
 */
@Service
public class TRubbishRecyclingServiceImpl
		extends BaseServiceImpl<TRubbishRecycling, TRubbishRecyclingMapper, TRubbishRecyclingDto>
		implements ITRubbishRecyclingService {

	@Autowired
	private TRubbishRecyclingMapper mapper;

	/**
	 * 
	 * 方法: getTRubbishRecycling <br>
	 * 描述: TODO
	 * 
	 * @param dto
	 * @return
	 * @see cn.com.ddhj.service.ITRubbishRecyclingService#getTRubbishRecycling(cn.com.ddhj.dto.TRubbishRecyclingDto)
	 */
	@Override
	public TRubbishRecycling getTRubbishRecycling(TRubbishRecyclingDto dto) {
		// 小于dto经纬度的最大距离垃圾回收站
		TRubbishRecycling toMax = mapper.getMaxTRubbishRecycling(dto);
		// 大于dto经纬度的最小距离垃圾回收站
		TRubbishRecycling toMin = mapper.getMinTRubbishRecycling(dto);
		Double maxLL = Double.valueOf(dto.getLat()) - Double.valueOf(toMax.getLat());
		Double minLL = Double.valueOf(toMin.getLat()) - Double.valueOf(dto.getLat());
		if (maxLL < minLL) {
			return toMax;
		} else {
			return toMin;
		}
	}

	/**
	 * 
	 * 方法: getDistance <br>
	 * 描述: TODO
	 * 
	 * @param dto
	 * @return
	 * @see cn.com.ddhj.service.ITRubbishRecyclingService#getDistance(cn.com.ddhj.dto.TRubbishRecyclingDto)
	 */
	@Override
	public Double getDistance(TRubbishRecyclingDto dto) {
		TRubbishRecycling model = this.getTRubbishRecycling(dto);
		Double ll = CommonUtil.getDistanceFromLL(Double.valueOf(dto.getLat()), Double.valueOf(dto.getLng()),
				Double.valueOf(model.getLat()), Double.valueOf(model.getLng()));
		return ll;
	}

	/**
	 * 
	 * 方法: getRubbishLevel <br>
	 * 描述: TODO
	 * 
	 * @param distance
	 * @return
	 * @see cn.com.ddhj.service.ITRubbishRecyclingService#getRubbishLevel(java.lang.Double)
	 */
	@Override
	public Integer getRubbishLevel(String city, String lat, String lng) {
		Integer level = 1;
		TRubbishRecyclingDto dto = new TRubbishRecyclingDto();
		dto.setCity(city);
		dto.setLat(lat);
		dto.setLng(lng);
		Double distance = this.getDistance(dto);
		if (distance > 300 && distance <= 1000) {
			level = 2;
		} else if (distance <= 300) {
			level = 3;
		}
		return level;
	}

}
