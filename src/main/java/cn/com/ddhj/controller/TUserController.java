package cn.com.ddhj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.TUserDto;
import cn.com.ddhj.model.TUser;
import cn.com.ddhj.result.tuser.LoginResult;
import cn.com.ddhj.result.tuser.RegisterResult;
import cn.com.ddhj.service.ITUserService;

@Controller
@RequestMapping("user/")
public class TUserController {

	@Autowired
	private ITUserService service;

	/**
	 * 
	 * 方法: register <br>
	 * 描述: 注册用户 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月2日 下午7:40:52
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping("register")
	public RegisterResult register(TUser entity) {
		return service.register(entity);
	}

	/**
	 * 
	 * 方法: login <br>
	 * 描述: 用户登录 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月2日 下午7:41:29
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping("login")
	public LoginResult login(TUserDto dto) {
		return service.login(dto);
	}

	/**
	 * 
	 * 方法: logOut <br>
	 * 描述: 用户登出 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月2日 下午7:42:02
	 * 
	 * @param uid
	 * @return
	 */
	@RequestMapping("logout")
	public BaseResult logOut(String uid) {
		return service.logOut(uid);
	}
}
