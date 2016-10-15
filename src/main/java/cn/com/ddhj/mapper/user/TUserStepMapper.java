package cn.com.ddhj.mapper.user;

import java.util.List;

import cn.com.ddhj.dto.user.TUserStepDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.user.TUserStep;

/**
 * 
 * 类: TUserStepMapper <br>
 * 描述: 用户计步表数据库访问接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月14日 下午4:23:12
 */
public interface TUserStepMapper extends BaseMapper<TUserStep, TUserStepDto> {

	/**
	 * 
	 * 方法: batchInstart <br>
	 * 描述: 批量同步数据到用户计步表 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月14日 下午4:25:07
	 * 
	 * @param list
	 * @return
	 */
	int batchInstart(List<TUserStep> list);

	/**
	 * 
	 * 方法: updateByEquipmentCode <br>
	 * 描述: 根据设备编码修改用户编码和绑定状态 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月14日 下午5:08:05
	 * 
	 * @param entity
	 * @return
	 */
	int updateByEquipmentCode(TUserStep entity);

	/**
	 * 
	 * 方法: findUserStepData <br>
	 * 描述: 根据日期和设备码查询计步信息 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月14日 下午4:33:44
	 * 
	 * @param dto
	 * @return
	 */
	List<TUserStep> findUserStepData(TUserStepDto dto);

}