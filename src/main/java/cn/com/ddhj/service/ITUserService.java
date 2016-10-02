package cn.com.ddhj.service;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.TUserDto;
import cn.com.ddhj.model.TUser;
import cn.com.ddhj.result.tuser.LoginResult;
import cn.com.ddhj.result.tuser.RegisterResult;

/**
 * 
 * 类: ITUserService <br>
 * 描述: 注册用户业务处理接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 上午11:17:31
 */
public interface ITUserService extends IBaseService<TUser, TUserDto> {

	/**
	 * 
	 * 方法: login <br>
	 * 描述: 登录验证 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月2日 上午11:57:08
	 * 
	 * @param dto
	 * @return
	 */
	LoginResult login(TUserDto dto);

	/**
	 * 
	 * 方法: register <br>
	 * 描述: 用户注册 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月2日 下午12:03:25
	 * 
	 * @param dto
	 * @return
	 */
	RegisterResult register(TUser entity);

	/**
	 * 
	 * 方法: logOut <br>
	 * 描述: 用户登出 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月2日 下午12:14:27
	 * 
	 * @param uid
	 * @return
	 */
	BaseResult logOut(String uid);
}
