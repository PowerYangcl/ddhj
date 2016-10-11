package cn.com.ddhj.mapper.user;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.user.TUserLogin;

/**
 * 
 * 类: TUserLoginMapper <br>
 * 描述: 用户登录表数据库访问接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月5日 下午10:16:52
 */
public interface TUserLoginMapper extends BaseMapper<TUserLogin, BaseDto> {

	/**
	 * 
	 * 方法: findLoginByUuid <br>
	 * 描述: 根据uuid查询登录信息 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月5日 下午10:20:13
	 * 
	 * @param uuid
	 * @return
	 */
	TUserLogin findLoginByUuid(String uuid);

	/**
	 * 
	 * 方法: deletByUuid <br>
	 * 描述: 登出操作 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月5日 下午10:20:39
	 * 
	 * @param uuid
	 * @return
	 */
	int deletByUuid(String uuid);
}