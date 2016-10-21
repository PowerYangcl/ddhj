package cn.com.ddhj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.ddhj.dto.user.TUserDto;
import cn.com.ddhj.result.tuser.UserDataResult;
import cn.com.ddhj.service.user.ITUserService;

@Controller
@RequestMapping("user/")
public class TUserController {

	@Autowired
	private ITUserService service;

	@RequestMapping("index")
	public String index() {
		return "jsp/user/index";
	}

	@RequestMapping("data")
	@ResponseBody
	public UserDataResult data(TUserDto dto) {
		return service.getUserData(dto);
	}
}
