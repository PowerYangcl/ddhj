package cn.com.ddhj.service;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.base.PageResult;
import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.model.BaseModel;

/**
 * 
 * 类: IBaseService <br>
 * 描述: 业务逻辑处理接口基类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月1日 上午11:05:29
 * 
 * @param <T>
 * @param <DTO>
 */
public interface IBaseService<T extends BaseModel, DTO extends BaseDto> {

	/**
	 * 
	 * 方法: insertSelective <br>
	 * 描述: 添加新的对象 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月1日 上午11:05:38
	 * 
	 * @param entity
	 * @return
	 */
	BaseResult insertSelective(T entity);

	/**
	 * 
	 * 方法: updateByCode <br>
	 * 描述: 编辑现有对象 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月1日 上午11:05:48
	 * 
	 * @param entity
	 * @return
	 */
	BaseResult updateByCode(T entity);

	/**
	 * 
	 * 方法: deleteByCode <br>
	 * 描述: 根据编码删除现有对象 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月1日 上午11:05:59
	 * 
	 * @param code
	 * @return
	 */
	BaseResult deleteByCode(String code);

	/**
	 * 
	 * 方法: selectByCode <br>
	 * 描述: 根据主键查询现有对象 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月1日 上午11:06:11
	 * 
	 * @param code
	 * @return
	 */
	T selectByCode(String code);

	/**
	 * 
	 * 方法: selectByEntity <br>
	 * 描述: 根据对象属性查询对象信息 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月1日 上午11:06:23
	 * 
	 * @param entity
	 * @return
	 */
	T selectByEntity(T entity);

	/**
	 * 
	 * 方法: findEntityToPage <br>
	 * 描述: 数据分页 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月1日 上午11:06:34
	 * 
	 * @param dto
	 * @return
	 */
	PageResult<T> findEntityToPage(DTO dto);
}
