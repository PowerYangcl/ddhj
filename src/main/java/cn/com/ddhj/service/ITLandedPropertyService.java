package cn.com.ddhj.service;

import java.util.List;

import cn.com.ddhj.dto.TLandedPropertyDto;
import cn.com.ddhj.dto.search.SearchLandPropertyDto;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.result.lp.TLandedPropertyDataResult;
import cn.com.ddhj.solr.data.SolrData;

/**
 * 
 * 类: ITLandedPropertyService <br>
 * 描述: 地产楼盘列表业务逻辑处理接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月3日 下午5:30:25
 */
public interface ITLandedPropertyService extends IBaseService<TLandedProperty, TLandedPropertyDto> {

	/**
	 * 
	 * 方法: insertDataFromAPI <br>
	 * 描述: 从聚合数据根据城市名称导入地产楼盘信息 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月3日 下午6:13:27
	 */
	void insertDataFromAPI(String cityName);

	/**
	 * 
	 * 方法: getLpData <br>
	 * 描述: 获取楼盘列表数据 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月21日 下午4:17:36
	 * 
	 * @param dto
	 * @return
	 */
	TLandedPropertyDataResult getLpData(TLandedPropertyDto dto);
}
