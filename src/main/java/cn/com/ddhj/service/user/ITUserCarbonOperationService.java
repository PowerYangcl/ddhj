package cn.com.ddhj.service.user;

import cn.com.ddhj.dto.user.TUserCarbonOperationDto;
import cn.com.ddhj.model.user.TUserCarbonOperation;
import cn.com.ddhj.result.carbon.CarbonDetailResult;
import cn.com.ddhj.result.carbon.CarbonTypeDetailResult;
import cn.com.ddhj.service.IBaseService;

/**
 * 
 * 类: ITUserCarbonOperationService <br>
 * 描述: 碳币收支情况相关业务逻辑处理接口 <br>
 * 作者: zhy<br>
 * 时间: 2017年2月10日 上午10:04:38
 */
public interface ITUserCarbonOperationService extends IBaseService<TUserCarbonOperation, TUserCarbonOperationDto> {

	/**
	 * 
	 * 方法: getCarbonOperationDetail <br>
	 * 描述: 获取碳币收支信息 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年2月10日 上午10:20:32
	 * 
	 * @return
	 */
	CarbonDetailResult getCarbonOperationDetail(String userToken);

	/**
	 * 
	 * 方法: getCarbonOperationByType <br>
	 * 描述: 根据类型查询碳币明细 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年2月13日 下午3:51:42
	 * 
	 * @param userToken
	 * @param type
	 * @return
	 */
	CarbonTypeDetailResult getCarbonOperationByType(String userToken, String type);

}
