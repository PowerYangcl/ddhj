package cn.com.ddhj.service.impl.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.user.TUserDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.user.TUserLoginMapper;
import cn.com.ddhj.mapper.user.TUserMapper;
import cn.com.ddhj.mapper.user.TUserStepMapper;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.model.user.TUserLogin;
import cn.com.ddhj.model.user.TUserStep;
import cn.com.ddhj.result.tuser.LoginResult;
import cn.com.ddhj.result.tuser.RegisterResult;
import cn.com.ddhj.result.tuser.UserDataResult;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.user.ITUserService;
import cn.com.ddhj.util.DateUtil;
import cn.com.ddhj.util.MD5Util;

/**
 * 
 * 类: TUserServiceImpl <br>
 * 描述: 注册用户业务处理接口实现类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 上午11:35:46
 */
@Service
public class TUserServiceImpl extends BaseServiceImpl<TUser, TUserMapper, TUserDto> implements ITUserService {

	@Autowired
	private TUserMapper mapper;
	@Autowired
	private TUserLoginMapper loginMapper;
	@Autowired
	private TUserStepMapper stepMapper;

	/**
	 * 
	 * 方法: login <br>
	 * 
	 * @param dto
	 * @return
	 * @see cn.com.ddhj.service.user.ITUserService#login(cn.com.ddhj.dto.user.TUserDto)
	 */
	@Override
	public LoginResult login(TUserDto dto) {
		LoginResult result = new LoginResult();
		TUser user = mapper.findUserByPhone(dto.getPhone());
		if (user != null) {
			String password = MD5Util.md5Hex(dto.getPassword());
			if (StringUtils.equals(password, user.getPassword())) {
				TUser entity = new TUser();
				entity.setUuid(user.getUuid());
				entity.setIsLogin(0);
				int flag = mapper.userLoginAndLogOut(entity);
				if (flag >= 0) {
					// 添加登录信息到用户登录表
					TUserLogin login = new TUserLogin();
					login.setUuid(UUID.randomUUID().toString().replace("-", ""));
					login.setUserToken(entity.getUuid());
					login.setCreateUser(user.getUserCode());
					login.setCreateTime(DateUtil.getSysDateTime());
					loginMapper.insertSelective(login);
					// 根据设备号同步计步数据
					if (StringUtils.isNoneBlank(dto.getEquipmentCode())) {
						TUserStep step = new TUserStep();
						step.setEquipmentCode(dto.getEquipmentCode());
						step.setUserCode(user.getUserCode());
						step.setIsBinding(1);
						step.setUpdateTime(DateUtil.getSysDateTime());
						stepMapper.updateByEquipmentCode(step);
					}
					result.setResultCode(0);
					user.setPassword("");
					result.setUser(user);
					result.setResultMessage("登录成功");
					result.setUserToken(login.getUuid());
				} else {
					result.setResultCode(-1);
					result.setResultMessage("用户登录失败");
				}
			} else {
				result.setResultCode(-1);
				result.setResultMessage("用户密码错误");
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
			TUser user = mapper.findUserByPhone(entity.getPhone());
			if (user == null) {
				String userCode = WebHelper.getInstance().getUniqueCode("U");
				entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
				entity.setPassword(MD5Util.md5Hex(entity.getPassword()));
				entity.setUserCode(userCode);
				entity.setCreateTime(DateUtil.getSysDateTime());
				entity.setCreateUser(userCode);
				entity.setUpdateUser(userCode);
				entity.setUpdateTime(DateUtil.getSysDateTime());
				int flag = mapper.insertSelective(entity);
				if (flag > 0) {
					// 添加登录信息到用户登录表
					TUserLogin login = new TUserLogin();
					login.setUuid(UUID.randomUUID().toString().replace("-", ""));
					login.setUserToken(entity.getUuid());
					login.setCreateUser(entity.getUserCode());
					login.setCreateTime(DateUtil.getSysDateTime());
					loginMapper.insertSelective(login);
					// end
					result.setResultCode(0);
					entity.setPassword("");
					result.setUser(entity);
					result.setResultMessage("注册成功");
					result.setUserToken(login.getUuid());
				} else {
					result.setResultCode(-1);
					result.setResultMessage("注册失败");
				}
			} else {
				result.setResultCode(-1);
				result.setResultMessage("用户已存在");
			}
		}
		return result;
	}

	/**
	 * 
	 * 方法: logOut <br>
	 * 
	 * @param uid
	 * @return
	 * @see cn.com.ddhj.service.user.ITUserService#logOut(java.lang.String)
	 */
	@Override
	public BaseResult logOut(String uid) {
		BaseResult result = new BaseResult();
		TUserLogin login = loginMapper.findLoginByUuid(uid);
		if (login != null) {
			TUser entity = new TUser();
			entity.setUuid(login.getUserToken());
			entity.setIsLogin(0);
			int flag = mapper.userLoginAndLogOut(entity);
			if (flag >= 0) {
				loginMapper.deletByUuid(uid);
				result.setResultCode(0);
				result.setResultMessage("用户登出成功");
			} else {
				result.setResultCode(-1);
				result.setResultMessage("用户登出失败");
			}
		} else {
			result.setResultCode(-1);
			result.setResultMessage("用户登出失败");
		}

		return result;
	}

	/**
	 * 
	 * 方法: updateByCode <br>
	 * 
	 * @param entity
	 * @return
	 * @see cn.com.ddhj.service.impl.BaseServiceImpl#updateByCode(cn.com.ddhj.model.BaseModel)
	 */
	@Override
	public BaseResult updateByCode(TUser entity, String target, String userToken) {
		BaseResult result = new BaseResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login == null) {
			result.setResultCode(-1);
			result.setResultMessage("用户未登录");
		} else {
			TUser user = mapper.findTUserByUuid(login.getUserToken());
			if (user != null) {
				entity.setUserCode(user.getUserCode());
				entity.setUpdateUser(user.getUserCode());
				entity.setUpdateTime(DateUtil.getSysDateTime());
				if (StringUtils.equals(target, "user_edit_pass")) {
					/**
					 * 修改密码
					 */
					if (StringUtils.isNotBlank(entity.getPassword())) {
						entity.setPassword(MD5Util.md5Hex(entity.getPassword()));
						int flag = mapper.updateByCode(entity);
						if (flag >= 0) {
							result.setResultCode(0);
							result.setResultMessage("修改密码成功");
						} else {
							result.setResultCode(-1);
							result.setResultMessage("修改密码失败");
						}
					} else {
						result.setResultCode(-1);
						result.setResultMessage("修改密码，密码不能为空");
					}
				} else if (StringUtils.equals(target, "user_edit_pic")) {
					/**
					 * 修改头像
					 */
					if (StringUtils.isNotBlank(entity.getHeadPic())) {
						int flag = mapper.updateByCode(entity);
						if (flag >= 0) {
							result.setResultCode(0);
							result.setResultMessage("修改用户头像成功");
						} else {
							result.setResultCode(-1);
							result.setResultMessage("修改用户头像失败");
						}
					} else {
						result.setResultCode(-1);
						result.setResultMessage("头像地址为空");
					}
				} else if (StringUtils.equals(target, "user_edit_email")) {
					/**
					 * 修改邮箱
					 */
					if (StringUtils.isNotBlank(entity.geteMail())) {
						Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
						Matcher matcher = emailPattern.matcher(entity.geteMail());
						if (matcher.find()) {
							int flag = mapper.updateByCode(entity);
							if (flag >= 0) {
								result.setResultCode(0);
								result.setResultMessage("修改电子邮箱成功");
							} else {
								result.setResultCode(-1);
								result.setResultMessage("修改电子邮箱失败");
							}
						} else {
							result.setResultCode(-1);
							result.setResultMessage("请填写正确邮箱格式");
						}
					} else {
						result.setResultCode(-1);
						result.setResultMessage("电子邮箱不能为空");
					}
				} else if (StringUtils.equals(target, "user_edit_nickname")) {
					/**
					 * 修改昵称
					 */
					if (StringUtils.isNotBlank(entity.getNickName())) {
						int flag = mapper.updateByCode(entity);
						if (flag >= 0) {
							result.setResultCode(0);
							result.setResultMessage("修改用户昵称成功");
						} else {
							result.setResultCode(-1);
							result.setResultMessage("修改用户昵称失败");
						}
					} else {
						result.setResultCode(-1);
						result.setResultMessage("昵称不能为空");
					}
				}
			} else {
				result.setResultCode(-1);
				result.setResultMessage("用户尚未注册");
			}
		}
		return result;
	}

	/**
	 * 
	 * 方法: loginBySecurityCode <br>
	 * 
	 * @param entity
	 * @return
	 * @see cn.com.ddhj.service.user.ITUserService#loginBySecurityCode(cn.com.ddhj.model.user.TUser)
	 */
	@Override
	public RegisterResult loginBySecurityCode(TUser entity) {
		RegisterResult result = new RegisterResult();
		// 根据手机号查询用户是否存在
		if (StringUtils.isNoneBlank(entity.getPhone())) {
			TUser user = mapper.findUserByPhone(entity.getPhone());
			if (user != null) {
				TUser loginU = new TUser();
				loginU.setUuid(user.getUuid());
				loginU.setIsLogin(0);
				int flag = mapper.userLoginAndLogOut(loginU);
				if (flag >= 0) {
					// 添加登录信息到用户登录表
					TUserLogin login = new TUserLogin();
					login.setUuid(UUID.randomUUID().toString().replace("-", ""));
					login.setUserToken(user.getUuid());
					login.setCreateUser(user.getUserCode());
					login.setCreateTime(DateUtil.getSysDateTime());
					loginMapper.insertSelective(login);
					result.setResultCode(0);
					user.setPassword("");
					result.setUser(user);
					result.setResultMessage("登录成功");
					result.setUserToken(login.getUuid());
				} else {
					result.setResultCode(-1);
					result.setResultMessage("用户登录失败");
				}
			} else {
				String userCode = WebHelper.getInstance().getUniqueCode("U");
				entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
				entity.setPassword(MD5Util.md5Hex(entity.getPassword()));
				entity.setUserCode(userCode);
				entity.setCreateTime(DateUtil.getSysDateTime());
				entity.setCreateUser(userCode);
				entity.setUpdateUser(userCode);
				entity.setUpdateTime(DateUtil.getSysDateTime());
				int flag = mapper.insertSelective(entity);
				if (flag > 0) {
					// 添加登录信息到用户登录表
					TUserLogin login = new TUserLogin();
					login.setUuid(UUID.randomUUID().toString().replace("-", ""));
					login.setUserToken(entity.getUuid());
					login.setCreateUser(entity.getUserCode());
					login.setCreateTime(DateUtil.getSysDateTime());
					loginMapper.insertSelective(login);
					// end
					result.setResultCode(0);
					entity.setPassword("");
					result.setUser(entity);
					result.setResultMessage("登录成功");
					result.setUserToken(login.getUuid());
				} else {
					result.setResultCode(-1);
					result.setResultMessage("登录失败");
				}
			}
		} else {
			result.setResultCode(-1);
			result.setResultMessage("手机号不能为空");
		}
		return result;
	}

	@Override
	public UserDataResult getUserData(TUserDto dto) {
		UserDataResult result = new UserDataResult();
		PageHelper.startPage(dto.getPageIndex(), dto.getPageSize());
		List<TUser> list = mapper.findUserAll(dto);
		if (list != null && list.size() > 0) {
			result.setResultCode(0);
		} else {
			list = new ArrayList<TUser>();
			result.setResultCode(-1);
			result.setResultMessage("查询注册用户列表为空");
		}
		PageInfo<TUser> page = new PageInfo<TUser>(list);
		result.setPage(page);
		return result;
	}

	public JSONObject getUserInfo(String uuid) {
		JSONObject result = new JSONObject();

		TUser u = mapper.findTUserByUuid(uuid);
		if (u != null) {
			result.put("code", "1");
			result.put("msg", "SUCCESS");
			result.put("data", u);
		} else {
			result.put("code", 0);
			result.put("msg", "数据异常");
		}

		return result;
	}

	public JSONObject updateUserInfo(TUserDto dto) {
		JSONObject result = new JSONObject();
		if (StringUtils.isNotBlank(dto.getPassword())) {
			String md5 = MD5Util.md5Sign(dto.getPassword());
			dto.setPassword(md5);
		}
		Integer flag = mapper.updateUserInfo(dto);
		if (flag == 1) {
			result.put("code", "1");
			result.put("msg", "SUCCESS");
		} else {
			result.put("code", 0);
			result.put("msg", "更新异常");
		}

		return result;
	}

	public JSONObject deleteOne(Integer id) {
		JSONObject result = new JSONObject();
		Integer flag = mapper.deleteOne(id);
		if (flag == 1) {
			result.put("code", "1");
			result.put("msg", "删除成功");
		} else {
			result.put("code", 0);
			result.put("msg", "删除失败");
		}

		return result;
	}

	/**
	 * 
	 * 方法: getUser <br>
	 * 
	 * @param dto
	 * @return
	 * @see cn.com.ddhj.service.user.ITUserService#getUser(cn.com.ddhj.dto.user.TUserDto)
	 */
	@Override
	public UserDataResult getUser(String userTocken) {
		UserDataResult result = new UserDataResult();
		TUserLogin login = loginMapper.findLoginByUuid(userTocken);
		if (login == null) {
			result.setResultCode(-1);
			result.setResultMessage("用户未登录");
		} else {
			TUser user = mapper.findTUserByUuid(login.getUserToken());
			if (user != null) {
				result.setResultCode(0);
				result.setResultMessage("获取用户信息成功");
				user.setPassword("");
				result.setUser(user);
			} else {
				result.setResultCode(0);
				result.setResultMessage("用户不存在");
			}
		}
		return result;
	}

}
