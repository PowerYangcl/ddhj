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
	int batchInsert(List<TUserStep> list);
	

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
	 * 通过设备号用户编号生成日期更新记录
	 * @param entity
	 * @return
	 */
	int updateByEquipCodeAndUserCodeAndCreateDate(TUserStep entity);

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
	
	/**
	 * 根据userCode, equipmentCode, createDate查询某一天该用户一个设备的同步步数
	 * @author zht
	 * @param dto
	 * @return
	 */
	List<TUserStep> findUserStepOne(TUserStepDto dto);
	
	/**
	 * 
	 * @Title: dailyCountStepPresent 
	 * @Description: TODO 
	 * @param @param createDate
	 * @param @return    
	 * @return List<TUserStep> 
	 * @throws
	 */
	List<TUserStep> dailyCountStepPresent(String createDate);

	/**
	 * 
	 * 方法: deletByEquipmentCode <br>
	 * 描述: 根据设备号和时间段批量删除计步数据 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月20日 上午10:48:09
	 * 
	 * @param dto
	 * @return
	 */
	int deletByEquipmentCode(TUserStepDto dto);

	/**
	 * 
	 * 方法: findStepDataIsNotSync <br>
	 * 描述: 查询所有未同步兑换碳币数据信息 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年2月14日 上午10:43:22
	 * 
	 * @return
	 */
	List<TUserStep> findStepDataIsNotSync();

	/**
	 * 
	 * 方法: updateSyncByUserCode <br>
	 * 描述: TODO <br>
	 * 作者: zhy<br>
	 * 时间: 2017年2月14日 上午10:55:41
	 * 
	 * @return
	 */
	int updateSyncByUserCode(TUserStep entity);

	/**
	 * 查询用户步数，按天显示，如果不传默认显示当天数据
	 * 
	 * @param dto
	 * @return
	 */
	TUserStep findStepByDate(TUserStepDto dto);
}