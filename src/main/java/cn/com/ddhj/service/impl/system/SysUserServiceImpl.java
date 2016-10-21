package cn.com.ddhj.service.impl.system;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.dto.system.SysUserDto;
import cn.com.ddhj.mapper.system.SysUserMapper;
import cn.com.ddhj.model.system.SysUser;
import cn.com.ddhj.result.system.SysUserLoginResult;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.system.ISysUserService;

/**
 * 
 * 类: ISysUserService <br>
 * 描述: 系统用户表业务处理接口实现类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月21日 下午3:35:32
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, SysUserMapper, SysUserDto> implements ISysUserService {

	@Autowired
	private SysUserMapper mapper;

	/**
	 * 
	 * 方法: login <br>
	 * 描述: TODO
	 * 
	 * @param dto
	 * @return
	 * @see cn.com.ddhj.service.system.ISysUserService#login(cn.com.ddhj.dto.system.SysUserDto)
	 */
	@Override
	public SysUserLoginResult login(SysUserDto dto) {
		SysUserLoginResult result = new SysUserLoginResult();
		if (StringUtils.isNotBlank(dto.getUserName()) && StringUtils.isNotBlank(dto.getPassword())) {
			SysUser user = mapper.findSysUserForLogin(dto);
			if (user != null) {
				result.setResultCode(0);
				result.setResultMessage("登录成功");
				result.setUser(user);
			} else {
				result.setResultCode(-1);
				result.setResultMessage("用户不存在");
			}
		} else {
			result.setResultCode(-1);
			result.setResultMessage("用户名或密码为空");
		}
		return result;
	}

}
