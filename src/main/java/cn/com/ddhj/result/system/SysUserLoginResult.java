package cn.com.ddhj.result.system;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.system.SysUser;

public class SysUserLoginResult extends BaseResult {

	private SysUser user;

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

}
