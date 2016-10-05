package cn.com.ddhj.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseAPI;
import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.TUserDto;
import cn.com.ddhj.dto.report.TReportDto;
import cn.com.ddhj.model.TUser;
import cn.com.ddhj.result.report.TReportLResult;
import cn.com.ddhj.result.report.TReportSelResult;
import cn.com.ddhj.result.tuser.LoginResult;
import cn.com.ddhj.result.tuser.RegisterResult;
import cn.com.ddhj.service.ITUserService;
import cn.com.ddhj.service.report.ITReportService;

@Controller
public class ApiController {
	@Autowired
	private ITReportService reportService;
	@Autowired
	private ITUserService userService;

	@RequestMapping("api")
	@ResponseBody
	public BaseResult api(BaseAPI api, HttpSession session) {
		JSONObject obj = JSONObject.parseObject(api.getApiInput());
		if ("user_register".equals(api.getApiTarget())) {
			// 用户注册
			TUser entity = obj.toJavaObject(TUser.class);
			RegisterResult result = userService.register(entity);
			if (result.getResultCode() == 0) {
				session.setAttribute(result.getUserToken(), entity);
			}
			return result;
		} else if ("user_login".equals(api.getApiTarget())) {
			TUserDto dto = obj.toJavaObject(TUserDto.class);
			LoginResult result = userService.login(dto);
			if (result.getResultCode() == 0) {
				session.setAttribute(result.getUserToken(), result.getUser());
			}
			return result;
		} else if ("user_logout".equals(api.getApiTarget())) {
			BaseResult result = userService.logOut(obj.getString("userToken"));
			if (result.getResultCode() == 0) {
				session.removeAttribute(obj.getString("userToken"));
			}
			return result;
		} else if ("report_data".equals(api.getApiTarget())) {
			TReportDto dto = obj.toJavaObject(TReportDto.class);
			TReportLResult result = reportService.getReportData(dto);
			return result;
		} else if ("report_sel".equals(api.getApiTarget())) {
			String code = obj.getString("code");
			TReportSelResult result = reportService.getTReport(code);
			return result;
		} else {
			BaseResult result = new BaseResult();
			result.setResultCode(-1);
			result.setResultMessage("调用接口失败");
			return result;
		}
	}
}
