package cn.com.ddhj.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.ddhj.base.BaseClass;
import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.base.PageResult;
import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.BaseModel;
import cn.com.ddhj.service.IBaseService;
import cn.com.ddhj.util.DateUtil;

/**
 * 
 * 类: BaseServiceImpl <br>
 * 描述: 业务处理接口基类实现类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月1日 上午11:06:57
 * 
 * @param <T>
 * @param <M>
 * @param <DTO>
 */
public class BaseServiceImpl<T extends BaseModel, M extends BaseMapper<T, DTO>, DTO extends BaseDto> extends BaseClass
		implements IBaseService<T, DTO> {

	@Autowired
	private M mapper;

	/**
	 * 
	 * 方法: insertSelective <br>
	 * 描述: TODO
	 * 
	 * @param entity
	 * @return
	 * @see cn.com.ath.service.IBaseService#insertSelective(cn.com.ath.model.BaseModel)
	 */
	@Override
	public BaseResult insertSelective(T entity) {
		BaseResult result = new BaseResult();
		if (entity.getUuid() == null || "".equals(entity.getUuid())) {
			entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
		}
		if (entity.getCreateTime() == null || "".equals(entity.getCreateTime())) {
			entity.setCreateTime(DateUtil.getSysDateTime());
		}
		entity.setUpdateUser(entity.getCreateUser());
		entity.setUpdateTime(entity.getCreateTime());
		try {
			int flag = mapper.insertSelective(entity);
			if (flag > 0) {
				result.setResultCode(0);
				result.setResultMessage("添加成功");
			} else {
				result.setResultCode(-1);
				result.setResultMessage("添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(-1);
			result.setResultMessage("添加错误，请联系管理员");
		}
		return result;
	}

	/**
	 * 
	 * 方法: updateByCode <br>
	 * 描述: TODO
	 * 
	 * @param entity
	 * @return
	 * @see cn.com.ath.service.IBaseService#updateByCode(cn.com.ath.model.BaseModel)
	 */
	@Override
	public BaseResult updateByCode(T entity) {
		BaseResult result = new BaseResult();
		if (entity.getUpdateTime() == null || "".equals(entity.getUpdateTime())) {
			entity.setUpdateTime(DateUtil.getSysDateTime());
		}
		try {
			int flag = mapper.updateByCode(entity);
			if (flag >= 0) {
				result.setResultCode(0);
				result.setResultMessage("编辑成功");
			} else {
				result.setResultCode(-1);
				result.setResultMessage("编辑失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(-1);
			result.setResultMessage("编辑错误，请联系管理员");
		}
		return result;
	}

	/**
	 * 
	 * 方法: selectByEntity <br>
	 * 描述: TODO
	 * 
	 * @param entity
	 * @return
	 * @see cn.com.ath.service.IBaseService#selectByEntity(cn.com.ath.model.BaseModel)
	 */
	@Override
	public T selectByEntity(T entity) {
		return mapper.selectByEntity(entity);
	}

	/**
	 * 
	 * 方法: deleteByCode <br>
	 * 描述: TODO
	 * 
	 * @param code
	 * @return
	 * @see cn.com.ath.service.IBaseService#deleteByCode(java.lang.String)
	 */
	@Override
	public BaseResult deleteByCode(String code) {
		BaseResult result = new BaseResult();
		try {
			int flag = mapper.deleteByCode(code);
			if (flag >= 0) {
				result.setResultCode(0);
				result.setResultMessage("删除成功");
			} else {
				result.setResultCode(0);
				result.setResultMessage("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(0);
			result.setResultMessage("删除错误，请联系管理员");
		}
		return result;
	}

	/**
	 * 
	 * 方法: selectByCode <br>
	 * 描述: TODO
	 * 
	 * @param code
	 * @return
	 * @see cn.com.ath.service.IBaseService#selectByCode(java.lang.String)
	 */
	@Override
	public T selectByCode(String code) {
		return mapper.selectByCode(code);
	}

	/**
	 * 
	 * 方法: findEntityToPage <br>
	 * 描述: TODO
	 * 
	 * @param dto
	 * @return
	 * @see cn.com.ath.service.IBaseService#findEntityToPage(cn.com.ath.dto.BaseDto)
	 */
	@Override
	public PageResult findEntityToPage(DTO dto) {
		PageResult result = new PageResult();
		dto.setStart(dto.getPageIndex() * dto.getPageSize());
		List<T> list = mapper.findEntityAll(dto);
		int total = mapper.findEntityAllCount(dto);
		if (list != null && list.size() > 0) {
			result.setResultCode(0);
		} else {
			result.setResultCode(-1);
			result.setResultMessage("获取数据为空");
			list = new ArrayList<T>();
		}

		result.setRepList(list);
		result.setRepCount(total);
		return result;
	}
}