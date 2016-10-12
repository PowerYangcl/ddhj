package cn.com.ddhj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.dto.TCityDto;
import cn.com.ddhj.mapper.TCityMapper;
import cn.com.ddhj.model.TCity;
import cn.com.ddhj.result.CityResult;
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
	 * 
	 * @param city
	 * @return
	 * @see cn.com.ddhj.service.ITCityService#updateByName(cn.com.ddhj.model.TCity)
	 */
	@Override
	public int updateByName(TCity city) {
		return mapper.updateByName(city);
	}

	/**
	 * 
	 * 方法: findHotCity <br>
	 * 
	 * @return
	 * @see cn.com.ddhj.service.ITCityService#findHotCity()
	 */
	@Override
	public CityResult findHotCity() {
		CityResult result = new CityResult();
		List<TCity> list = mapper.findHotCity();
		if (list != null && list.size() > 0) {
			result.setList(list);
			result.setResultCode(0);
			result.setResultMessage("查询热门城市成功");
		} else {
			result.setResultCode(-1);
			result.setResultMessage("查询热门城市失败");
		}
		return result;
	}

}
