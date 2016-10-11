package cn.com.ddhj.service.user;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.user.TUserLpFollowDto;
import cn.com.ddhj.model.user.TUserLpFollow;
import cn.com.ddhj.result.tuser.FollowResult;
import cn.com.ddhj.service.IBaseService;

/**
 * 
 * 类: ITUserLpFollowService <br>
 * 描述: 用户楼盘关注表业务逻辑处理接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月11日 下午2:54:49
 */
public interface ITUserLpFollowService extends IBaseService<TUserLpFollow, TUserLpFollowDto> {

	/**
	 * 
	 * 方法: delFollow <br>
	 * 描述: 删除关注 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月11日 下午2:55:41
	 * 
	 * @param dto
	 * @return
	 */
	BaseResult delFollow(TUserLpFollowDto dto, String userToken);

	/**
	 * 
	 * 方法: insert <br>
	 * 描述: 添加关注 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月11日 下午3:36:30
	 * 
	 * @param lpCode
	 * @param userToken
	 * @return
	 */
	BaseResult insert(String lpCode, String userToken);

	/**
	 * 
	 * 方法: findFollowLpData <br>
	 * 描述: 获取关注的楼盘列表 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月11日 下午3:37:13
	 * 
	 * @param list
	 * @return
	 */
	FollowResult findFollowLpData(TUserLpFollowDto dto,String userToken);

}
