package cn.com.ddhj.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseAPI;
import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.user.TUserDto;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.result.tuser.LoginResult;
import cn.com.ddhj.result.tuser.RegisterResult;
import cn.com.ddhj.service.user.ITUserService;

@Controller
@RequestMapping("user/")
public class TUserController {

	@Autowired
	private ITUserService service;

	@RequestMapping("api")
	@ResponseBody
	private BaseResult api(BaseAPI api, HttpSession session) {
		JSONObject obj = JSONObject.parseObject(api.getApiInput());
		if ("register".equals(api.getApiTarget())) {
			// 用户注册
			TUser entity = obj.toJavaObject(TUser.class);
			RegisterResult result = service.register(entity);
			if (result.getResultCode() == 0) {
				session.setAttribute(result.getUserToken(), entity);
			}
			return result;
		} else if ("login".equals(api.getApiTarget())) {
			TUserDto dto = obj.toJavaObject(TUserDto.class);
			LoginResult result = service.login(dto);
			if (result.getResultCode() == 0) {
				session.setAttribute(result.getUserToken(), result.getUser());
			}
			return result;
		} else if ("logout".equals(api.getApiTarget())) {
			BaseResult result = service.logOut(obj.getString("userToken"));
			if (result.getResultCode() == 0) {
				session.removeAttribute(obj.getString("userToken"));
			}
			return result;
		} else {
			BaseResult result = new BaseResult();
			result.setResultCode(-1);
			result.setResultMessage("接口调用失败");
			return result;
		}
	}
}
