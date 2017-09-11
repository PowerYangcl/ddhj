package cn.com.ddhj.service.user;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.user.TUserStepDto;
import cn.com.ddhj.model.user.TUserStep;
import cn.com.ddhj.result.EntityResult;
import cn.com.ddhj.result.tuser.UserStepResult;
import cn.com.ddhj.service.IBaseService;

public interface ITUserStepService extends IBaseService<TUserStep, TUserStepDto> {

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
	BaseResult batchInsert(String data, String userToken);

	/**
	 * 
	 * 方法: findUserStepData <br>
	 * 描述: 计步数据信息 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月14日 下午6:35:25
	 * 
	 * @param equipmentCode
	 * @param time
	 * @return
	 */
	UserStepResult findUserStepData(String equipmentCode,String userToken);

	/**
	 * 
	 * 方法: syncStepDataToCarbon <br>
	 * 描述: 同步用户步数兑换炭币更新用户碳币信息及操作日志 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年2月14日 上午10:31:32
	 * 
	 * @return
	 */
	BaseResult syncStepDataToCarbon();
	
	/**
	 * 查询用户步数，按天显示，如果不传默认显示当天数据
	 */
	EntityResult findStepByDate(TUserStepDto dto,String userToken);
	
	/**
	 * 每日凌晨统计根据用户当日步行数赠送碳币
	 * @Title: dailyCountStepPresent 
	 * @Description: TODO 
	 * @param     
	 * @return void 
	 * @throws
	 */
	void dailyCountStepPresent(String createDate);
}
