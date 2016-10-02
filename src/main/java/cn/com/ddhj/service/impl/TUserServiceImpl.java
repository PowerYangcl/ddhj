package cn.com.ddhj.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.TUserDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TUserMapper;
import cn.com.ddhj.model.TUser;
import cn.com.ddhj.result.tuser.LoginResult;
import cn.com.ddhj.result.tuser.RegisterResult;
import cn.com.ddhj.service.ITUserService;
import cn.com.ddhj.util.DateUtil;
import cn.com.ddhj.util.MD5Util;

/**
 * 
 * 类: TUserServiceImpl <br>
 * 描述: TODO <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 上午11:35:46
 */
@Service
public class TUserServiceImpl extends BaseServiceImpl<TUser, TUserMapper, TUserDto> implements ITUserService {

	@Autowired
	private TUserMapper mapper;

	/**
	 * 
	 * 方法: login <br>
	 * 描述: TODO
	 * 
	 * @param dto
	 * @return
	 * @see cn.com.ddhj.service.ITUserService#login(cn.com.ddhj.dto.TUserDto)
	 */
	@Override
	public LoginResult login(TUserDto dto) {
		LoginResult result = new LoginResult();
		dto.setPassword(MD5Util.md5Hex(dto.getPassword()));
		TUser user = mapper.findTUser(dto);
		if (user != null) {
			TUser entity = new TUser();
			entity.setUuid(user.getUuid());
			entity.setIsLogin(0);
			int flag = mapper.userLoginAndLogOut(entity);
			if (flag >= 0) {
				result.setResultCode(0);
				result.setResultMessage("");
				result.setUserToken(user.getUuid());
			} else {
				result.setResultCode(-1);
				result.setResultMessage("用户登录失败");
			}
		} else {
			result.setResultCode(-1);
			result.setResultMessage("用户不存在");
		}
		return result;
	}

	@Override
	public RegisterResult register(TUser entity) {
		RegisterResult result = new RegisterResult();
		if (entity.getPhone() == null || "".equals(entity.getPhone())) {
			result.setResultCode(-1);
			result.setResultMessage("用户手机号不能为空");
		} else if (entity.getPassword() == null || "".equals(entity.getPhone())) {
			result.setResultCode(-1);
			result.setResultMessage("用户密码不能为空");
		} else {
			String userCode = WebHelper.getInstance().getUniqueCode("U");
			entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
			entity.setUserCode(userCode);
			entity.setCreateTime(DateUtil.getSysDateTime());
			entity.setCreateUser(userCode);
			entity.setUpdateUser(userCode);
			entity.setUpdateTime(DateUtil.getSysDateTime());
			int flag = mapper.insertSelective(entity);
			if (flag > 0) {
				result.setResultCode(0);
				result.setResultMessage("注册成功");
				result.setUserToken(userCode);
			} else {
				result.setResultCode(-1);
				result.setResultMessage("注册失败");
			}
		}
		return result;
	}

	/**
	 * 
	 * 方法: logOut <br>
	 * 描述: TODO
	 * 
	 * @param uid
	 * @return
	 * @see cn.com.ddhj.service.ITUserService#logOut(java.lang.String)
	 */
	@Override
	public BaseResult logOut(String uid) {
		BaseResult result = new BaseResult();
		TUser entity = new TUser();
		entity.setUuid(uid);
		entity.setIsLogin(0);
		int flag = mapper.userLoginAndLogOut(entity);
		if (flag >= 0) {
			result.setResultCode(0);
			result.setResultMessage("用户登出成功");
		} else {
			result.setResultCode(0);
			result.setResultMessage("用户登出失败");
		}
		return result;
	}

}
