package cn.com.ddhj.service.system;

import cn.com.ddhj.dto.system.SysUserDto;
import cn.com.ddhj.model.system.SysUser;
import cn.com.ddhj.result.system.SysUserLoginResult;
import cn.com.ddhj.service.IBaseService;

/**
 * 
 * 类: ISysUserService <br>
 * 描述: 系统用户表业务处理接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月21日 下午3:35:32
 */
public interface ISysUserService extends IBaseService<SysUser, SysUserDto> {

	/**
	 * 
	 * 方法: login <br>
	 * 描述: 用户登录 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月21日 下午3:37:01
	 * 
	 * @param dto
	 * @return
	 */
	SysUserLoginResult login(SysUserDto dto);
}
