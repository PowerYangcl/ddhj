package cn.com.ddhj.controller;

import javax.servlet.http.HttpSession;

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
	public RegisterResult register(TUser entity, HttpSession session) {
		RegisterResult result = service.register(entity);
		if (result.getResultCode() == 0) {
			session.setAttribute(entity.getUuid(), entity);
		}
		return result;
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
	public LoginResult login(TUserDto dto, HttpSession session) {
		LoginResult result = service.login(dto);
		if (result.getResultCode() == 0) {
			session.setAttribute(result.getUser().getUuid(), result.getUser());
		}
		return result;
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
	public BaseResult logOut(String userToken, HttpSession session) {
		BaseResult result = service.logOut(userToken);
		if (result.getResultCode() == 0) {
			session.removeAttribute(userToken);
		}
		return result;
	}
}
