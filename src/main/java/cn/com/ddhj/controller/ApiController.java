package cn.com.ddhj.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseAPI;
import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.TOrderDto;
import cn.com.ddhj.dto.TUserDto;
import cn.com.ddhj.dto.report.TReportDto;
import cn.com.ddhj.model.TOrder;
import cn.com.ddhj.model.TUser;
import cn.com.ddhj.result.order.OrderAddResult;
import cn.com.ddhj.result.order.OrderAffirmResult;
import cn.com.ddhj.result.order.OrderPayResult;
import cn.com.ddhj.result.order.TOrderResult;
import cn.com.ddhj.result.report.TReportLResult;
import cn.com.ddhj.result.report.TReportSelResult;
import cn.com.ddhj.result.tuser.LoginResult;
import cn.com.ddhj.result.tuser.RegisterResult;
import cn.com.ddhj.service.IEstateEnvironmentService;
import cn.com.ddhj.service.ITOrderService;
import cn.com.ddhj.service.ITUserService;
import cn.com.ddhj.service.impl.orderpay.PayServiceSupport;
import cn.com.ddhj.service.impl.orderpay.notify.NotifyPayProcess.PaymentResult;
import cn.com.ddhj.service.impl.orderpay.notify.PayGateNotifyPayProcess;
import cn.com.ddhj.service.report.ITReportService;
import cn.com.ddhj.util.DateUtil;

@Controller
public class ApiController {
	@Autowired
	private ITReportService reportService;
	@Autowired
	private ITUserService userService;

	@Autowired
	private IEstateEnvironmentService estateEnvService;
	@Autowired
	private ITOrderService orderService;

	@RequestMapping("api")
	@ResponseBody
	public JSONObject api(BaseAPI api, HttpSession session, HttpServletRequest request) {
		JSONObject obj = JSONObject.parseObject(api.getApiInput());
		if ("user_register".equals(api.getApiTarget())) {
			// 用户注册
			TUser entity = obj.toJavaObject(TUser.class);
			RegisterResult result = userService.register(entity);
			if (result.getResultCode() == 0) {
				session.setAttribute(result.getUserToken(), entity);
			}
			return JSONObject.parseObject(JSONObject.toJSONString(result)); // 修正所有的返回值为JSONObject
		} else if ("user_login".equals(api.getApiTarget())) {
			TUserDto dto = obj.toJavaObject(TUserDto.class);
			LoginResult result = userService.login(dto);
			if (result.getResultCode() == 0) {
				session.setAttribute(result.getUserToken(), result.getUser());
			}
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		} else if ("user_logout".equals(api.getApiTarget())) {
			BaseResult result = userService.logOut(obj.getString("userToken"));
			if (result.getResultCode() == 0) {
				session.removeAttribute(obj.getString("userToken"));
			}
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 环境报告
		else if ("report_data".equals(api.getApiTarget())) {
			TReportDto dto = obj.toJavaObject(TReportDto.class);
			TReportLResult result = reportService.getReportData(dto);
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		} else if ("report_sel".equals(api.getApiTarget())) {
			String code = obj.getString("code");
			TReportSelResult result = reportService.getTReport(code);
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		} else if ("1032".equals(api.getApiTarget())) { // 环境综合评分接口
			long start = System.currentTimeMillis();
			String position = obj.getString("position");
			String city = obj.getString("city");
			JSONObject result_ = estateEnvService.apiEnvScore(position, city);
			long end = System.currentTimeMillis();
			System.out.println("接口耗时：" +  (end -start) + " 毫秒"); 
			return result_;
		} else if ("1033".equals(api.getApiTarget())) { // 楼盘列表|检索该经纬度附近10Km内的楼盘信息
			long start = System.currentTimeMillis();
			String position = obj.getString("position");
			String city = obj.getString("city");
			String page = obj.getString("page");
			JSONObject result_= estateEnvService.apiEstateList(position, city, page);
			long end = System.currentTimeMillis();
			
			System.out.println("接口耗时：" +  (end -start)/1000 + " 秒"); 
			return result_;
		}else if ("1025".equals(api.getApiTarget())) { // 地区环境接口 
			String position = obj.getString("position");
			String city = obj.getString("city");
			return estateEnvService.apiAreaEnv(position, city); 
		}
		// 订单相关
		else if ("order_add".equals(api.getApiTarget())) {
			TOrder entity = obj.toJavaObject(TOrder.class);
			OrderAddResult result = orderService.insertSelective(entity, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		} else if ("order_data".equals(api.getApiTarget())) {
			TOrderDto dto = obj.toJavaObject(TOrderDto.class);
			TOrderResult result = orderService.findEntityToPage(dto, request);
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		} else if ("order_edit".equals(api.getApiTarget())) {
			TOrder entity = obj.toJavaObject(TOrder.class);
			BaseResult result = orderService.updateByCode(entity, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		} else if ("order_affirm".equals(api.getApiTarget())) {
			OrderAffirmResult result = orderService.orderAffirm(obj.getString("codes"));
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		} else {
			BaseResult result = new BaseResult();
			result.setResultCode(-1);
			result.setResultMessage("调用接口失败");
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
	}
	
	@RequestMapping("webPay/{orderCode}/{payType}")
	public String webPay(@PathVariable("orderCode") String orderCode, @PathVariable("payType") String payType,
			HttpServletRequest request, HttpServletResponse response) {
		String openID = request.getParameter("openID");
		String returnUrl = request.getParameter("returnUrl");
		OrderPayResult result = orderService.orderPay(openID, orderCode, payType, returnUrl);
		if(StringUtils.isEmpty(result.getErrorMsg())) {
			return result.getRedirectUrl();
		} else {
			return JSONObject.toJSONString(result);
		}
	}
	
	@RequestMapping("payNotify")
	@ResponseBody
	public String payNotify(HttpServletRequest request, HttpServletResponse response) {
		Enumeration<String> names = request.getParameterNames();
		Map<String,String> notifyParam = new HashMap<String, String>();
		String name;
		while(names.hasMoreElements()){
			name = names.nextElement();
			notifyParam.put(name, StringUtils.trimToEmpty(request.getParameter(name)));
		}
		
		PayGateNotifyPayProcess.PaymentResult result = PayServiceSupport.payGateNotify(notifyParam);
		
		if(result.getResultCode() == PaymentResult.SUCCESS) {
			TOrder order = new TOrder();
			order.setCode(result.notify.bigOrderCode);
			order.setStatus(1);
			order.setUpdateUser("paygate");
			order.setUpdateTime(DateUtil.getSysDateTime());
			orderService.updateByCode(order);
		}
		
		StringBuilder build = new StringBuilder();
		build.append("<result>1</result><reURL>"+result.reURL+"</reURL>");
		if(result.getResultCode() != PaymentResult.SUCCESS){
			build.append("<msg>").append(result.getResultMessage()).append("</msg>");
		}
		return build.toString();
	}
	
}
