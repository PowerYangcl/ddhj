package cn.com.ddhj.mapper;

import cn.com.ddhj.dto.TUserDto;
import cn.com.ddhj.model.TUser;

/**
 * 
 * 类: TUserMapper <br>
 * 描述: 注册用户数据库访问接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 上午11:48:24
 */
public interface TUserMapper extends BaseMapper<TUser, TUserDto> {

	/**
	 * 
	 * 方法: findTUser <br>
	 * 描述: 根据手机号和密码查询用户 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月2日 上午11:48:08
	 * 
	 * @param dto
	 * @return
	 */
	TUser findTUser(TUserDto dto);

	/**
	 * 
	 * 方法: userLoginAndLogOut <br>
	 * 描述: 用户登录和登出公共接口 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月2日 下午12:18:45
	 * 
	 * @param entity
	 * @return
	 */
	int userLoginAndLogOut(TUser entity);

	/**
	 * 
	 * 方法: findTUserByUuid <br>
	 * 描述: 根据uuid查询注册用户信息 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月7日 下午3:23:51
	 * 
	 * @param uuid
	 * @return
	 */
	TUser findTUserByUuid(String uuid);
}