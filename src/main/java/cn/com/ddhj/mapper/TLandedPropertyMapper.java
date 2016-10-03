package cn.com.ddhj.mapper;

import java.util.List;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.model.TLandedProperty;

/**
 * 
 * 类: TLandedPropertyMapper <br>
 * 描述: 地产楼盘列表数据库访问接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月3日 下午5:20:21
 */
public interface TLandedPropertyMapper extends BaseMapper<TLandedProperty, BaseDto> {

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
}