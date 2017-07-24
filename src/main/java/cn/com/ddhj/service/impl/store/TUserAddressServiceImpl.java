package cn.com.ddhj.service.impl.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.mapper.TUserAddressMapper;
import cn.com.ddhj.model.TUserAddress;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.result.tuser.UserDataResult;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.store.ITUserAddressService;
import cn.com.ddhj.service.user.ITUserService;

@Service
public class TUserAddressServiceImpl extends BaseServiceImpl<TUserAddress, TUserAddressMapper, BaseDto>
		implements ITUserAddressService {

	@Autowired
	private ITUserService userService;

	/**
	 * 
	 * 方法: updateByCode <br>
	 * 
	 * @param entity
	 * @param userToken
	 * @return
	 * @see cn.com.ddhj.service.store.ITUserAddressService#updateByCode(cn.com.ddhj.model.TUserAddress,
	 *      java.lang.String)
	 */
	@Override
	public BaseResult updateByCode(TUserAddress entity, String userToken) {
		BaseResult result = new BaseResult();
		UserDataResult userResult = userService.getUser(userToken);
		if (userResult.getResultCode() == 0) {
			TUser user = userResult.getUser();
			entity.setUpdateUser(user.getUserCode());
			result = super.updateByCode(entity);
		} else {
			result.setResultCode(userResult.getResultCode());
			result.setResultMessage(userResult.getResultMessage());
		}

		return result;
	}

}
