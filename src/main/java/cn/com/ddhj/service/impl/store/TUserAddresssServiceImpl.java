package cn.com.ddhj.service.impl.store;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.helper.UserHelper;
import cn.com.ddhj.mapper.TUserAddresssMapper;
import cn.com.ddhj.model.TUserAddresss;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.store.ITUserAddresssService;

public class TUserAddresssServiceImpl extends BaseServiceImpl<TUserAddresss, TUserAddresssMapper, BaseDto>
		implements ITUserAddresssService {

	/**
	 * 
	 * 方法: updateByCode <br>
	 * 
	 * @param entity
	 * @param userToken
	 * @return
	 * @see cn.com.ddhj.service.store.ITUserAddresssService#updateByCode(cn.com.ddhj.model.TUserAddresss,
	 *      java.lang.String)
	 */
	@Override
	public BaseResult updateByCode(TUserAddresss entity, String userToken) {
		BaseResult result = new BaseResult();
		TUser user = UserHelper.getInstance().getUser(userToken);
		if (user != null) {
			entity.setUpdateUser(user.getUserCode());
			result = super.updateByCode(entity);
		} else {
			result.setResultCode(-1);
			result.setResultMessage("用户不存在");
		}

		return result;
	}

}
