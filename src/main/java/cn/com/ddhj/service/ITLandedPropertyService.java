package cn.com.ddhj.service;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.model.TLandedProperty;

/**
 * 
 * 类: ITLandedPropertyService <br>
 * 描述: 地产楼盘列表业务逻辑处理接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月3日 下午5:30:25
 */
public interface ITLandedPropertyService extends IBaseService<TLandedProperty, BaseDto>{

	/**
	 * 
	 * 方法: insertDataFromAPI <br>
	 * 描述: 从聚合数据导入地产楼盘信息 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月3日 下午6:13:27
	 */
	void insertDataFromAPI();
}
