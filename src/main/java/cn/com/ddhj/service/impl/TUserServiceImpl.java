package cn.com.ddhj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.dto.TUserDto;
import cn.com.ddhj.mapper.TUserMapper;
import cn.com.ddhj.model.TUser;
import cn.com.ddhj.result.tuser.LoginResult;
import cn.com.ddhj.service.ITUserService;
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
	 * @param dto
	 * @return 
	 * @see cn.com.ddhj.service.ITUserService#login(cn.com.ddhj.dto.TUserDto)
	 */
	@Override
	public LoginResult login(TUserDto dto) {
		LoginResult result = new LoginResult();
		TUser user = mapper.findTUser(dto);
		if(user != null){
			result.setResultCode(0);
			result.setResultMessage("");
			result.setUserToken(user.getUuid());
		}else{
			result.setResultCode(-1);
			result.setResultMessage("用户不存在");			
		}
		return result;
	}

}
