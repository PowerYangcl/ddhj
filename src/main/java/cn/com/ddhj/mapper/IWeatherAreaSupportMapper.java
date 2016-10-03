package cn.com.ddhj.mapper;

import java.util.List;

import cn.com.ddhj.dto.WeatherAreaSupportDto;
import cn.com.ddhj.model.WeatherAreaSupport;

public interface IWeatherAreaSupportMapper  extends BaseMapper<WeatherAreaSupport , WeatherAreaSupportDto>{

	/**
	 * @descriptions 查库找到支持地区列表
	 *
	 * @return
	 * @date 2016年10月3日 下午9:53:12
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public List<WeatherAreaSupport> findSupportAreas();
}
