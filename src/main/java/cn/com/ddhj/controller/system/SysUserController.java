package cn.com.ddhj.controller.system;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.ddhj.dto.system.SysUserDto;
import cn.com.ddhj.result.system.SysUserLoginResult;
import cn.com.ddhj.service.system.ISysUserService;
import cn.com.ddhj.util.Constant;

/**
 * 
 * 类: SysUserController <br>
 * 描述: 系统用户前端交互 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月21日 下午3:43:44
 */
@Controller
@RequestMapping("sys/user")
public class SysUserController {

	@Autowired
	private ISysUserService service;

	/**
	 * 
	 * 方法: login <br>
	 * 描述: 系统用户登录 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月21日 下午3:43:33
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public SysUserLoginResult login(SysUserDto dto, HttpSession session) {
		SysUserLoginResult result = service.login(dto);
		if (result.getResultCode() == Constant.RESULT_SUCCESS) {
			session.setAttribute("user", result.getUser());
		}
		return result;
	}
}
