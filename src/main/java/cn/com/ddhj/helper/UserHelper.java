package cn.com.ddhj.helper;

import cn.com.ddhj.annotation.Inject;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.result.tuser.UserDataResult;
import cn.com.ddhj.service.user.ITUserService;

public class UserHelper {

	@Inject
	private ITUserService service;

	private static UserHelper self;

	public static UserHelper getInstance() {
		if (self == null) {
			synchronized (UserHelper.class) {
				if (self == null)
					self = new UserHelper();
			}
		}
		return self;
	}

	public TUser getUser(String userToken) {
		UserDataResult result = service.getUser(userToken);
		if (result.getResultCode() == 0) {
			return result.getUser();
		} else {
			return null;
		}
	}
}
