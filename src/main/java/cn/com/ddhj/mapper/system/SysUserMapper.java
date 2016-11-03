package cn.com.ddhj.mapper.system;

import cn.com.ddhj.dto.system.SysUserDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.system.SysUser;

/**
 * 
 * 类: SysUserMapper <br>
 * 描述: 系统用户表数据库访问接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月21日 下午2:54:18
 */
public interface SysUserMapper extends BaseMapper<SysUser, SysUserDto> {

	/**
	 * 
	 * 方法: findSysUserForLogin <br>
	 * 描述: 根据用户名和密码查询用户 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月21日 下午3:16:06
	 * 
	 * @param entity
	 * @return
	 */
	SysUser findSysUserForLogin(SysUserDto dto);
}