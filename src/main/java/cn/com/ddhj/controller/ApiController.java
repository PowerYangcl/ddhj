package cn.com.ddhj.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.annotation.Inject;
import cn.com.ddhj.base.BaseAPI;
import cn.com.ddhj.base.BaseClass;
import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.TLpCommentDto;
import cn.com.ddhj.dto.TOrderDto;
import cn.com.ddhj.dto.report.TReportDto;
import cn.com.ddhj.dto.user.TMessageDto;
import cn.com.ddhj.dto.user.TUserDto;
import cn.com.ddhj.dto.user.TUserLpFollowDto;
import cn.com.ddhj.dto.user.TUserLpVisitDto;
import cn.com.ddhj.mapper.user.TUserLoginMapper;
import cn.com.ddhj.model.TLpComment;
import cn.com.ddhj.model.TOrder;
import cn.com.ddhj.model.TPayment;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.model.user.TUserLogin;
import cn.com.ddhj.result.CityResult;
import cn.com.ddhj.result.carbon.CarbonDetailResult;
import cn.com.ddhj.result.carbon.CarbonTypeDetailResult;
import cn.com.ddhj.result.lp.TLpCommentData;
import cn.com.ddhj.result.lp.TLpCommentTopData;
import cn.com.ddhj.result.order.OrderAddResult;
import cn.com.ddhj.result.order.OrderAffirmResult;
import cn.com.ddhj.result.order.OrderPayResult;
import cn.com.ddhj.result.order.OrderTotal;
import cn.com.ddhj.result.order.TOrderResult;
import cn.com.ddhj.result.report.TReportLResult;
import cn.com.ddhj.result.report.TReportSelResult;
import cn.com.ddhj.result.tuser.FollowResult;
import cn.com.ddhj.result.tuser.LoginResult;
import cn.com.ddhj.result.tuser.MessageData;
import cn.com.ddhj.result.tuser.MessageSelResult;
import cn.com.ddhj.result.tuser.MessageTotal;
import cn.com.ddhj.result.tuser.RegisterResult;
import cn.com.ddhj.result.tuser.UserDataResult;
import cn.com.ddhj.result.tuser.UserStepResult;
import cn.com.ddhj.result.tuser.VisitResult;
import cn.com.ddhj.service.IEstateEnvironmentService;
import cn.com.ddhj.service.ITCityService;
import cn.com.ddhj.service.ITLpCommentService;
import cn.com.ddhj.service.ITOrderService;
import cn.com.ddhj.service.impl.TPaymentServiceImpl;
import cn.com.ddhj.service.user.ITMessageService;
import cn.com.ddhj.service.user.ITUserCarbonOperationService;
import cn.com.ddhj.service.user.ITUserLpFollowService;
import cn.com.ddhj.service.user.ITUserLpVisitService;
import cn.com.ddhj.service.user.ITUserService;
import cn.com.ddhj.service.user.ITUserStepService;
import cn.com.ddhj.service.impl.orderpay.PayServiceSupport;
import cn.com.ddhj.service.impl.orderpay.config.XmasPayConfig;
import cn.com.ddhj.service.impl.orderpay.notify.NotifyPayProcess.PaymentResult;
import cn.com.ddhj.service.impl.orderpay.notify.PayGateNotifyPayProcess;
import cn.com.ddhj.service.report.ITReportService;
import cn.com.ddhj.util.DateUtil;

@Controller
public class ApiController extends BaseClass {
	@Autowired
	private ITReportService reportService;
	@Autowired
	private ITUserService userService;

	@Autowired
	private IEstateEnvironmentService estateEnvService;
	@Autowired
	private ITOrderService orderService;
	@Autowired
	private ITLpCommentService lpcService;
	@Autowired
	private ITUserLpFollowService ufService;
	@Autowired
	private ITUserLpVisitService uvService;
	@Autowired
	private ITMessageService messageService;
	@Autowired
	private ITCityService cityService;
	@Autowired
	private TPaymentServiceImpl paymentService;
	@Inject
	private TUserLoginMapper userLoginMapper;
	@Autowired
	private ITUserStepService stepService;
	@Autowired
	private ITUserCarbonOperationService userCarbonOperationserivce;
	private WebApplicationContext webApplicationContext;
	private ServletContext application;

	@RequestMapping("api")
	@ResponseBody
	public JSONObject api(BaseAPI api, HttpSession session, HttpServletRequest request) {
		if (webApplicationContext == null) { // 创建全局缓存 - Yangcl
			webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
			application = webApplicationContext.getServletContext();
		}

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
		// 根据userTocken查询用户信息
		else if ("user_data".equals(api.getApiTarget())) {
			UserDataResult result = userService.getUser(api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 修改密码
		else if ("user_edit_pass".equals(api.getApiTarget())) {
			String password = obj.getString("new_password");
			TUser entity = new TUser();
			entity.setPassword(password);
			BaseResult result = userService.updateByCode(entity, api.getApiTarget(), api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 修改头像
		else if ("user_edit_pic".equals(api.getApiTarget())) {
			String headPic = obj.getString("headPic");
			TUser entity = new TUser();
			entity.setHeadPic(headPic);
			BaseResult result = userService.updateByCode(entity, api.getApiTarget(), api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 修改邮箱
		else if ("user_edit_email".equals(api.getApiTarget())) {
			String eMail = obj.getString("email");
			TUser entity = new TUser();
			entity.seteMail(eMail);
			BaseResult result = userService.updateByCode(entity, api.getApiTarget(), api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 修改昵称
		else if ("user_edit_nickname".equals(api.getApiTarget())) {
			String nickName = obj.getString("nick_name");
			TUser entity = new TUser();
			entity.setNickName(nickName);
			BaseResult result = userService.updateByCode(entity, api.getApiTarget(), api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 手机验证码登录
		else if ("user_login_security".equals(api.getApiTarget())) {
			TUser entity = obj.toJavaObject(TUser.class);
			RegisterResult result = userService.loginBySecurityCode(entity);
			if (result.getResultCode() == 0) {
				session.setAttribute(result.getUserToken(), entity);
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
		}
		// 根据楼盘编码查询楼盘环境报告
		else if ("report_lp".equals(api.getApiTarget())) {
			String lpCode = obj.getString("lpCode");
			TReportSelResult result = reportService.getTReportByLp(lpCode, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		} else if ("1032".equals(api.getApiTarget())) { // 环境综合评分接口
			long start = System.currentTimeMillis();
			String position = obj.getString("position");
			String city = obj.getString("city");
			String radius = obj.getString("radius");
			JSONObject result_ = estateEnvService.apiEnvScore(position, city, radius);
			long end = System.currentTimeMillis();
			System.out.println("1032号接口总共耗时：" + (end - start) + " 毫秒");
			return result_;
		} else if ("1033".equals(api.getApiTarget())) { // 楼盘列表|检索该经纬度附近10Km内的楼盘信息
			long start = System.currentTimeMillis();
			String position = obj.getString("position");
			String city = obj.getString("city");
			String page = obj.getString("page");
			String radius = obj.getString("radius");
			String count = obj.getString("count");
			JSONObject result_ = estateEnvService.apiEstateList(position, city, page, count, radius);
			long end = System.currentTimeMillis();
			System.out.println("1033号接口总共耗时：" + +(end - start) + " 毫秒");
			return result_;
		} else if ("1025".equals(api.getApiTarget())) { // 地区环境接口
			long start = System.currentTimeMillis();
			String position = obj.getString("position");
			String city = obj.getString("city");
			JSONObject result_ = estateEnvService.apiAreaEnv(position, city);
			long end = System.currentTimeMillis();
			System.out.println("1025号接口总共耗时：" + +(end - start) + " 毫秒");
			return result_;
		} else if ("2048".equals(api.getApiTarget())) { // 地区环境接口
			long start = System.currentTimeMillis();
			estateEnvService.resyncEstateScore();
			long end = System.currentTimeMillis();
			System.out.println("2048号接口总共耗时：" + +(end - start) + " 毫秒");
			return null;
		} else if ("2049".equals(api.getApiTarget())) { // 地区环境接口
			long start = System.currentTimeMillis();
			estateEnvService.resyncWaterEnviroment();
			long end = System.currentTimeMillis();
			System.out.println("2049号接口总共耗时：" + +(end - start) + " 毫秒");
			return null;
		} else if ("2050".equals(api.getApiTarget())) { //
			long start = System.currentTimeMillis();
			String city = obj.getString("city"); // "北京市"
			String area = obj.getString("area"); // "通州区"
			String type = obj.getString("type"); // "A" |"B"
			JSONObject result_ = estateEnvService.getFutureSevenAqi(city, area, type);
			long end = System.currentTimeMillis();
			System.out.println("2050号接口总共耗时：" + +(end - start) + " 毫秒");
			return result_;
		} else if ("2051".equals(api.getApiTarget())) { // 万年历接口
			JSONObject result_ = estateEnvService.perpetualCalendar(application);
			return result_;
		}

		// 订单相关
		else if ("order_add".equals(api.getApiTarget())) {
			TOrder entity = obj.toJavaObject(TOrder.class);
			OrderAddResult result = orderService.insertSelective(entity, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		} else if ("order_data".equals(api.getApiTarget())) {
			TOrderDto dto = obj.toJavaObject(TOrderDto.class);
			TOrderResult result = orderService.findEntityToPage(dto, api.getUserToken(), request);
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		} else if ("order_edit".equals(api.getApiTarget())) {
			TOrder entity = obj.toJavaObject(TOrder.class);
			BaseResult result = orderService.updateByCode(entity, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		} else if ("order_affirm".equals(api.getApiTarget())) {
			OrderAffirmResult result = orderService.orderAffirm(obj.getString("codes"), api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 订单数量
		else if ("order_total".equals(api.getApiTarget())) {
			Integer status = obj.getInteger("status");
			OrderTotal result = orderService.getOrderTotal(status, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 楼盘评价
		else if ("lpc_add".equals(api.getApiTarget())) {
			TLpComment lpc = obj.toJavaObject(TLpComment.class);
			BaseResult result = lpcService.insertSelective(lpc, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 楼盘最新5条评价
		else if ("lpc_top".equals(api.getApiTarget())) {
			TLpCommentDto dto = obj.toJavaObject(TLpCommentDto.class);
			TLpCommentTopData result = lpcService.findDataTop5(dto);
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 楼盘评价列表
		else if ("lpc_data".equals(api.getApiTarget())) {
			TLpCommentDto dto = obj.toJavaObject(TLpCommentDto.class);
			TLpCommentData result = lpcService.findData(dto);
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 添加关注楼盘
		else if ("lp_follow_add".equals(api.getApiTarget())) {
			String code = obj.getString("lpCode");
			BaseResult result = ufService.insert(code, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 删除关注楼盘
		else if ("lp_follow_del".equals(api.getApiTarget())) {
			TUserLpFollowDto dto = obj.toJavaObject(TUserLpFollowDto.class);
			BaseResult result = ufService.delFollow(dto, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 获取关注楼盘列表
		else if ("lp_follow_data".equals(api.getApiTarget())) {
			TUserLpFollowDto dto = obj.toJavaObject(TUserLpFollowDto.class);
			FollowResult result = ufService.findFollowLpData(dto, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 添加楼盘浏览记录
		else if ("lp_visit_add".equals(api.getApiTarget())) {
			String code = obj.getString("lpCode");
			BaseResult result = uvService.insert(code, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 删除楼盘浏览记录
		else if ("lp_visit_del".equals(api.getApiTarget())) {
			TUserLpVisitDto dto = obj.toJavaObject(TUserLpVisitDto.class);
			BaseResult result = uvService.delVisit(dto, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 获取楼盘浏览记录
		else if ("lp_visit_data".equals(api.getApiTarget())) {
			TUserLpVisitDto dto = obj.toJavaObject(TUserLpVisitDto.class);
			VisitResult result = uvService.findVisitLpData(dto, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 消息数量
		else if ("message_total".equals(api.getApiTarget())) {
			Integer isRead = obj.getInteger("is_read");
			MessageTotal result = messageService.findEntityTotal(isRead, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 消息列表
		else if ("message_data".equals(api.getApiTarget())) {
			TMessageDto dto = obj.toJavaObject(TMessageDto.class);
			MessageData result = messageService.findEntityToPage(dto, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 消息详情
		else if ("message_sel".equals(api.getApiTarget())) {
			String code = obj.getString("code");
			MessageSelResult result = messageService.selectByCode(code, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 热门城市列表
		else if ("hot_city".equals(api.getApiTarget())) {
			CityResult result = cityService.findHotCity();
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		} // 获取计步数据
		else if ("step_data".equals(api.getApiTarget())) {
			UserStepResult result = stepService.findUserStepData(obj.getString("equipmentCode"));
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 批量导入计步数据
		else if ("step_sync".equals(api.getApiTarget())) {
			BaseResult result = stepService.batchInsert(api.getApiInput());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		/**
		 * ================= 碳币相关 start ======================
		 */
		// 获取用户碳币交易详情
		else if ("user_carbon_detail".equals(api.getApiTarget())) {
			CarbonDetailResult result = userCarbonOperationserivce.getCarbonOperationDetail(api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 根据碳币类型查询交易明细
		else if ("user_carbon_type_detail".equals(api.getApiTarget())) {
			JSONObject param = JSONObject.parseObject(api.getApiInput());
			CarbonTypeDetailResult result = userCarbonOperationserivce.getCarbonOperationByType(api.getUserToken(),
					param.getString("type"));
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		/**
		 * ================= 碳币相关 end ======================
		 */
		else {
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
		if (StringUtils.isEmpty(result.getErrorMsg())) {
			return result.getRedirectUrl();
		} else {
			String errMsg = "";
			try {
				errMsg = URLEncoder.encode(result.getErrorMsg(), "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:" + XmasPayConfig.getPayGateDefaultReURL() + "?errorMsg=" + errMsg;

		}
	}

	@RequestMapping("payNotify")
	@ResponseBody
	public String payNotify(HttpServletRequest request, HttpServletResponse response) {
		Enumeration<String> names = request.getParameterNames();
		Map<String, String> notifyParam = new HashMap<String, String>();
		String name;
		while (names.hasMoreElements()) {
			name = names.nextElement();
			notifyParam.put(name, StringUtils.trimToEmpty(request.getParameter(name)));
		}
		String orderCode = notifyParam.get("c_order");
		List<TUserLogin> listss = userLoginMapper.findTokenByOrderCode(orderCode);
		String useruuid = "";
		if (listss != null && listss.size() > 0) {
			useruuid = listss.get(0).getUserToken();
		}

		PayGateNotifyPayProcess.PaymentResult result = PayServiceSupport.payGateNotify(notifyParam);

		TPayment entity = new TPayment();
		entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
		if (result.getResultCode() == PaymentResult.SUCCESS) {
			TOrder order = new TOrder();
			order.setCode(result.notify.bigOrderCode);
			order.setStatus(1);
			order.setUpdateUser("paygate");
			order.setUpdateTime(DateUtil.getSysDateTime());
			orderService.updateByCode(order);

			// TODO 支付成功则插入日志记录：paymentService TPayment
			entity.setOrderCode(order.getCode());
			entity.setMid(notifyParam.get("c_mid"));
			entity.setAmount(BigDecimal.valueOf(Double.valueOf(notifyParam.get("c_orderamount"))));
			entity.setYmd(notifyParam.get("c_ymd"));
			entity.setMoneyType("人民币");
			entity.setDealtime(notifyParam.get("dealtime"));
			entity.setSuccmark("success");
			entity.setMemo1(notifyParam.get("bankorderid")); //
			entity.setMemo2(notifyParam.get("c_transnum")); //
			entity.setSignstr(notifyParam.get("c_signstr"));
			entity.setPaygate(notifyParam.get("c_paygate"));

		} else {
			// TODO 支付失败则插入日志记录：paymentService
			entity.setOrderCode(result.notify.bigOrderCode);
			entity.setSuccmark("false");
			entity.setCause("支付宝调用失败");
		}
		paymentService.insertSelective(entity, useruuid);

		StringBuilder build = new StringBuilder();
		build.append("<result>1</result><reURL>" + result.reURL + "</reURL>");
		if (result.getResultCode() != PaymentResult.SUCCESS) {
			build.append("<msg>").append(result.getResultMessage()).append("</msg>");
		}
		return build.toString();
	}

}
