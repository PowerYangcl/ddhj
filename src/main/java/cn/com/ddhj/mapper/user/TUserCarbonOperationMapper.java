package cn.com.ddhj.mapper.user;

import java.util.List;

import cn.com.ddhj.dto.user.TUserCarbonOperationDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.user.TUserCarbonOperation;

public interface TUserCarbonOperationMapper extends BaseMapper<TUserCarbonOperation, TUserCarbonOperationDto> {

	/**
	 * 
	 * 方法: findCarbonOperationByDate <br>
	 * 描述: 查询某一时间段的碳币收支信息 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年2月13日 下午4:20:10
	 * 
	 * @param dto
	 * @return
	 */
	List<TUserCarbonOperation> findCarbonOperationByDate(TUserCarbonOperationDto dto);

	/**
	 * 
	 * 方法: findCarbonOperationByTime <br>
	 * 描述: 查询一个月内的碳币收支明细 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年2月10日 上午10:01:55
	 * 
	 * @param dto
	 * @return
	 */
	List<TUserCarbonOperation> findCarbonOperationByTime(TUserCarbonOperationDto dto);

	/**
	 * 
	 * 方法: getCarbonOperationSum <br>
	 * 描述: 根据收支类型汇总交易记录 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年2月10日 上午10:35:05
	 * 
	 * @param dto
	 * @return
	 */
	List<TUserCarbonOperation> getCarbonOperationSum(TUserCarbonOperationDto dto);
	
	
	/**
	 * 
	 * 方法: batchInsert <br>
	 * 描述: 批量添加碳币操作日志 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年2月14日 上午9:01:25
	 * @param list
	 * @return
	 */
	int batchInsert(List<TUserCarbonOperation> list);
	
	
	/**
	 * 根据用户编号查询用户昨日碳币支出与收入情况
	 * @param userCode
	 * @return
	 */
	List<TUserCarbonOperation> getUserCarbonOperationYesterday(String userCode);
	
	
}