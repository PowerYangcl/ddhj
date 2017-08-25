package cn.com.ddhj.mapper;

import java.util.List;

import cn.com.ddhj.dto.TLandedPropertyDto;
import cn.com.ddhj.dto.landedProperty.LandLatLngDto;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.result.estateInfo.EData;

/**
 * 
 * 类: TLandedPropertyMapper <br>
 * 描述: 地产楼盘列表数据库访问接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月3日 下午5:20:21
 */
public interface TLandedPropertyMapper extends BaseMapper<TLandedProperty, TLandedPropertyDto> {

	/**
	 * 
	 * 方法: batchInsertTLandedProperty <br>
	 * 描述: 从聚合数据导入楼盘信息 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月3日 下午6:23:35
	 * 
	 * @param list
	 * @return
	 */
	int batchInsertTLandedProperty(List<TLandedProperty> list);

	/**
	 * @descriptions 根据楼盘名称寻找楼盘在数据库里的编号
	 *
	 * @param name
	 * @return
	 * @date 2016年10月6日 下午4:44:17
	 * @author Yangcl
	 * @version 1.0.0.1
	 */
	public List<TLandedProperty> findCodeByTitle(List<String> titles);

	public List<EData> findLandedPropertyAll(LandLatLngDto dto);

	/**
	 * 
	 * 方法: findTLandedPropertyAll <br>
	 * 描述: 获取所有楼盘数据 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月9日 下午6:54:57
	 * 
	 * @return
	 */
	List<TLandedProperty> findTLandedPropertyAll();

	/**
	 * 
	 * 方法: findTLandedPropertyCity <br>
	 * 描述: 获取楼盘城市列表 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月10日 上午10:02:30
	 * 
	 * @return
	 */
	List<String> findTLandedPropertyCity();

	/**
	 * 
	 * 方法: findLpAllByCoord <br>
	 * 描述: 根据坐标查询指定范围内的所有楼盘数据 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月12日 下午2:43:15
	 * 
	 * @return
	 */
	List<TLandedProperty> findLpAllByCoord(TLandedPropertyDto dto);

	/**
	 * @description: 取出全部地产信息
	 * 
	 * @return
	 * @author Yangcl
	 * @date 2016年10月18日 下午5:15:13
	 * @version 1.0.0.1
	 */
	List<TLandedProperty> selectAllEstateInfo();

	Integer batchUpdateScore(List<TLandedProperty> list);

	/**
	 * 
	 * 方法: findScoreLessThanLpCount <br>
	 * 描述: 获取一定范围内环境综合评分小于当前楼盘的总数 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年2月28日 下午6:14:42
	 * 
	 * @param dto
	 * @return
	 */
	Integer findScoreLessThanLpCount(TLandedPropertyDto dto);
}
