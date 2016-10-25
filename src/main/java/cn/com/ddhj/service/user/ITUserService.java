package cn.com.ddhj.service.user;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.user.TUserDto;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.result.tuser.LoginResult;
import cn.com.ddhj.result.tuser.RegisterResult;
import cn.com.ddhj.result.tuser.UserDataResult;
import cn.com.ddhj.service.IBaseService;

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

	/**
	 * 
	 * 方法: updateByCode <br>
	 * 描述: 编辑现有对象 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月1日 上午11:05:48
	 * 
	 * @param entity
	 * @return
	 */
	BaseResult updateByCode(TUser entity, String target, String userToken);

	/**
	 * 
	 * 方法: loginBySecurityCode <br>
	 * 描述: 用户手机验证码登录 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月11日 下午2:18:57
	 * 
	 * @param entity
	 * @return
	 */
	RegisterResult loginBySecurityCode(TUser entity);

	/**
	 * 
	 * 方法: getUserData <br>
	 * 描述: 获取所有注册用户信息 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月21日 下午10:36:45
	 * 
	 * @param dto
	 * @return
	 */
	UserDataResult getUserData(TUserDto dto);

	public JSONObject getUserInfo(String uuid);

	public JSONObject updateUserInfo(TUserDto dto);

	public JSONObject deleteOne(Integer id);   
}






