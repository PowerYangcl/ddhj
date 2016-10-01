package cn.com.ddhj.mapper;

import java.util.List;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.model.BaseModel;

/**
 * 
 * 类: BaseMapper <br>
 * 描述: 数据库访问接口基类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月1日 上午11:01:56
 * 
 * @param <T>
 * @param <DTO>
 */
public interface BaseMapper<T extends BaseModel, DTO extends BaseDto> {

	/**
	 * 
	 * 方法: insertSelective <br>
	 * 描述: 添加新的对象 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月1日 上午11:02:08
	 * 
	 * @param entity
	 * @return
	 */
	int insertSelective(T entity);

	/**
	 * 
	 * 方法: updateByCode <br>
	 * 描述: 编辑现有对象 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月1日 上午11:02:21
	 * 
	 * @param entity
	 * @return
	 */
	int updateByCode(T entity);

	/**
	 * 
	 * 方法: deleteByCode <br>
	 * 描述: 根据编码删除现有对象 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月1日 上午11:03:00
	 * 
	 * @param code
	 * @return
	 */
	int deleteByCode(String code);

	/**
	 * 
	 * 方法: selectByCode <br>
	 * 描述: 根据编码查询现有对象 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月1日 上午11:03:12
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
	 * 时间: 2016年10月1日 上午11:02:40
	 * 
	 * @param entity
	 * @return
	 */
	T selectByEntity(T entity);

	/**
	 * 
	 * 方法: findEntityAll <br>
	 * 描述: 根据对象属性查询所有对象 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年8月1日 下午1:38:35
	 * 
	 * @return
	 */
	List<T> findEntityAll(DTO dto);

	/**
	 * 
	 * 方法: findEntityAllCount <br>
	 * 描述: 根据对象属性查询所有对象总数 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月1日 上午11:03:28
	 * 
	 * @param dto
	 * @return
	 */
	int findEntityAllCount(DTO dto);
}
