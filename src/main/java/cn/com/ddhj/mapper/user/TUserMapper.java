package cn.com.ddhj.mapper.user;

import java.util.List;


import cn.com.ddhj.dto.user.TUserDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.user.TUser;

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

	/**
	 * 
	 * 方法: findUserByPhone <br>
	 * 描述: 根据手机号查询用户 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月11日 下午2:14:24
	 * 
	 * @param phone
	 * @return
	 */
	TUser findUserByPhone(String phone);

	/**
	 * 
	 * 方法: findUserAll <br>
	 * 描述: 查询所有注册用户 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月21日 下午10:35:24
	 * 
	 * @param dto
	 * @return
	 */
	List<TUser> findUserAll(TUserDto dto);
	
	
	public TUser getUserInfo(String uuid); 
	
	public Integer updateUserInfo(TUserDto dto);

	public Integer  deleteOne(Integer id); 
}














