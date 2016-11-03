package cn.com.ddhj.result.tuser;

import com.github.pagehelper.PageInfo;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.user.TUser;

public class UserDataResult extends BaseResult {

	private TUser user;

	private PageInfo<TUser> page;

	public PageInfo<TUser> getPage() {
		return page;
	}

	public void setPage(PageInfo<TUser> page) {
		this.page = page;
	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

}
