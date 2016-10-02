package cn.com.ddhj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.dto.TCityDto;
import cn.com.ddhj.mapper.TCityMapper;
import cn.com.ddhj.model.TCity;
import cn.com.ddhj.service.ITCityService;

/**
 * 
 * 类: TCityServiceImpl <br>
 * 描述: 城市列表业务处理接口实现类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月1日 下午11:29:41
 */
@Service
public class TCityServiceImpl extends BaseServiceImpl<TCity, TCityMapper, TCityDto> implements ITCityService {

	@Autowired
	private TCityMapper mapper;

	/**
	 * 
	 * 方法: batchInsertCity <br>
	 * 描述: TODO
	 * 
	 * @param list
	 * @return
	 * @see cn.com.ddhj.service.ITCityService#batchInsertCity(java.util.List)
	 */
	@Override
	public int batchInsertCity(List<TCity> list) {
		return mapper.batchInsertCity(list);
	}

	/**
	 * 
	 * 方法: findCityByName <br>
	 * 描述: TODO
	 * 
	 * @param city
	 * @return
	 * @see cn.com.ddhj.service.ITCityService#findCityByName(cn.com.ddhj.model.TCity)
	 */
	@Override
	public int findCityByName(TCity city) {
		return mapper.findCityByName(city);
	}

	/**
	 * 
	 * 方法: updateByName <br>
	 * 描述: TODO
	 * 
	 * @param city
	 * @return
	 * @see cn.com.ddhj.service.ITCityService#updateByName(cn.com.ddhj.model.TCity)
	 */
	@Override
	public int updateByName(TCity city) {
		return mapper.updateByName(city);
	}

}
