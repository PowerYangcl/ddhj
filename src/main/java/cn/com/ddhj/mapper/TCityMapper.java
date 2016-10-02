package cn.com.ddhj.mapper;

import java.util.List;

import cn.com.ddhj.dto.TCityDto;
import cn.com.ddhj.model.TCity;

/**
 * 
 * 类: TCityMapper <br>
 * 描述: 城市列表数据库访问接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月1日 下午10:58:14
 */
public interface TCityMapper extends BaseMapper<TCity, TCityDto> {

	/**
	 * 
	 * 方法: batchInsertCity <br>
	 * 描述: 批量添加城市数据 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月1日 下午11:25:29
	 * 
	 * @param list
	 * @return
	 */
	int batchInsertCity(List<TCity> list);

	/**
	 * 
	 * 方法: findCityByName <br>
	 * 描述: 根据城市名称和类型查询城市是否可查询 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月1日 下午10:51:13
	 * 
	 * @param city
	 * @return
	 */
	int findCityByName(TCity city);

	/**
	 * 
	 * 方法: updateByName <br>
	 * 描述: 根据城市名称修改城市的空气质量和pm2.5查询 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月1日 下午11:05:01
	 * 
	 * @param city
	 * @return
	 */
	int updateByName(TCity city);

}