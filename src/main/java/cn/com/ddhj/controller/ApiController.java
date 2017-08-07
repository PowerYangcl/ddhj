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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.annotation.Inject;
import cn.com.ddhj.base.BaseAPI;
import cn.com.ddhj.base.BaseClass;
import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.TLpCommentDto;
import cn.com.ddhj.dto.TOrderDto;
import cn.com.ddhj.dto.report.TReportDto;
import cn.com.ddhj.dto.store.TProductInfoDto;
import cn.com.ddhj.dto.store.TProductOrderDto;
import cn.com.ddhj.dto.store.TUserAddressUpdateDto;
import cn.com.ddhj.dto.trade.TTradeDealDto;
import cn.com.ddhj.dto.trade.TTradeOrderDto;
import cn.com.ddhj.dto.user.TMessageDto;
import cn.com.ddhj.dto.user.TUserAddressDto;
import cn.com.ddhj.dto.user.TUserCarbonOperationDto;
import cn.com.ddhj.dto.user.TUserDto;
import cn.com.ddhj.dto.user.TUserLpFollowDto;
import cn.com.ddhj.dto.user.TUserLpVisitDto;
import cn.com.ddhj.dto.user.TUserStepDto;
import cn.com.ddhj.helper.PropHelper;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.user.TUserLoginMapper;
import cn.com.ddhj.model.TLpComment;
import cn.com.ddhj.model.TOrder;
import cn.com.ddhj.model.TOrderRecharge;
import cn.com.ddhj.model.TPayment;
import cn.com.ddhj.model.TUserAddress;
import cn.com.ddhj.model.trade.TTradeOrder;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.model.user.TUserCarbonOperation;
import cn.com.ddhj.model.user.TUserLogin;
import cn.com.ddhj.result.CityResult;
import cn.com.ddhj.result.DataResult;
import cn.com.ddhj.result.EntityResult;
import cn.com.ddhj.result.carbon.CarbonDetailResult;
import cn.com.ddhj.result.carbon.CarbonRechargeResult;
import cn.com.ddhj.result.carbon.CarbonTypeDetailResult;
import cn.com.ddhj.result.lp.TLpCommentData;
import cn.com.ddhj.result.lp.TLpCommentTopData;
import cn.com.ddhj.result.order.OrderAddResult;
import cn.com.ddhj.result.order.OrderAffirmResult;
import cn.com.ddhj.result.order.OrderPayResult;
import cn.com.ddhj.result.order.OrderTotal;
import cn.com.ddhj.result.order.TOrderResult;
import cn.com.ddhj.result.product.TPageProductListResult;
import cn.com.ddhj.result.report.TReportLResult;
import cn.com.ddhj.result.report.TReportSelResult;
import cn.com.ddhj.result.trade.TradeBalanceResult;
import cn.com.ddhj.result.trade.TradeCityResult;
import cn.com.ddhj.result.trade.TradeDealChartResult;
import cn.com.ddhj.result.trade.TradeDealResult;
import cn.com.ddhj.result.trade.TradeOrderResult;
import cn.com.ddhj.result.trade.TradePriceAvaiAmountResult;
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
import cn.com.ddhj.service.ITAddressEnshrineService;
import cn.com.ddhj.service.ITCityService;
import cn.com.ddhj.service.ITLpCommentService;
import cn.com.ddhj.service.ITOrderService;
import cn.com.ddhj.service.ITradeService;
import cn.com.ddhj.service.impl.TPaymentServiceImpl;
import cn.com.ddhj.service.impl.orderpay.PayServiceSupport;
import cn.com.ddhj.service.impl.orderpay.config.XmasPayConfig;
import cn.com.ddhj.service.impl.orderpay.notify.NotifyPayProcess.PaymentResult;
import cn.com.ddhj.service.impl.orderpay.notify.PayGateNotifyPayProcess;
import cn.com.ddhj.service.report.ITReportService;
import cn.com.ddhj.service.store.ITAreaService;
import cn.com.ddhj.service.store.ITProductInfoService;
import cn.com.ddhj.service.store.ITProductOrderService;
import cn.com.ddhj.service.store.ITUserAddressService;
import cn.com.ddhj.service.user.ITMessageService;
import cn.com.ddhj.service.user.ITUserCarbonOperationService;
import cn.com.ddhj.service.user.ITUserLpFollowService;
import cn.com.ddhj.service.user.ITUserLpVisitService;
import cn.com.ddhj.service.user.ITUserService;
import cn.com.ddhj.service.user.ITUserStepService;
import cn.com.ddhj.util.Constant;
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
	@Autowired
	private ITradeService tradeService;
	@Autowired
	private ITUserCarbonOperationService userCarbonOperService;
	@Autowired
	private ITProductOrderService productOrderService;
	@Autowired
	private ITUserAddressService userAddressService;
	@Autowired
	private ITProductInfoService productInfoService;
	@Autowired
	private ITAreaService areaService;
	
	@Autowired
	private ITAddressEnshrineService addressEnshrineService;

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
			if (result.getResultCode() == Constant.RESULT_SUCCESS) {
				session.setAttribute(result.getUserToken(), entity);
			}
			return JSONObject.parseObject(JSONObject.toJSONString(result)); // 修正所有的返回值为JSONObject
		} else if ("user_login".equals(api.getApiTarget())) {
			TUserDto dto = obj.toJavaObject(TUserDto.class);
			LoginResult result = userService.login(dto);
			if (result.getResultCode() == Constant.RESULT_SUCCESS) {
				session.setAttribute(result.getUserToken(), result.getUser());
			}
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		} else if ("user_logout".equals(api.getApiTarget())) {
			BaseResult result = userService.logOut(obj.getString("userToken"));
			if (result.getResultCode() == Constant.RESULT_SUCCESS) {
				session.removeAttribute(obj.getString("userToken"));
			}
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 根据userTocken查询用户信息
		else if ("user_data".equals(api.getApiTarget())) {
			UserDataResult userResult = userService.getUser(api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(userResult));
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
			if (result.getResultCode() == Constant.RESULT_SUCCESS) {
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
			JSONObject result_ = estateEnvService.apiEnvScore(position, city, radius, application);
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
			JSONObject result_ = estateEnvService.apiAreaEnv(position, city, application);
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
		} else if ("2052".equals(api.getApiTarget())) { // t_landed_score
														// 楼盘综合评分统计接口
			String city = obj.getString("city"); // "北京"
			String type = obj.getString("type"); // year：按年份平均|quarter：按季度平均|month：按月份平均
			String date = obj.getString("date"); // 2016 1|2|3|4 2016-01
			String year = "";
			if (type.equals("quarter")) {
				year = obj.getString("year");
			}
			Integer pageIndex = obj.getInteger("pageIndex");
			Integer pageSize = obj.getInteger("pageSize");

			// JSONObject result_ = estateEnvService.landedScoreAverage("天津" ,
			// "year" , "2017" , "" , 0 , 10);
			// JSONObject result_ = estateEnvService.landedScoreAverage("天津" ,
			// "quarter" , "2" , "2017" , 0 , 10);
			// JSONObject result_ = estateEnvService.landedScoreAverage("天津" ,
			// "month" , "2017-01" , "" , 0 , 10);

			JSONObject result_ = estateEnvService.landedScoreAverage(city, type, date, year, pageIndex, pageSize);
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
		// 根据用户token删除所有楼盘浏览记录
		else if("lp_visit_del_by_user_code".equals(api.getApiTarget())){
			return uvService.deleteLpVisitByUserCode(api.getUserToken());
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
			UserStepResult result = stepService.findUserStepData(obj.getString("equipmentCode"),api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 批量导入计步数据
		else if ("step_sync".equals(api.getApiTarget())) {
			BaseResult result = stepService.batchInsert(api.getApiInput());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		//获取用户步数信息
		else if("step_date".equals(api.getApiTarget())){
			TUserStepDto dto = obj.toJavaObject(TUserStepDto.class);
			EntityResult result =stepService.findStepByDate(dto,api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		/**
		 * ================= 碳币相关 start zht ======================
		 */
		// 获取用户碳币交易详情
		else if ("user_carbon_detail".equals(api.getApiTarget())) {
			CarbonDetailResult result = userCarbonOperationserivce.getCarbonOperationDetail(api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 根据碳币类型查询交易明细
		else if ("user_carbon_type_detail".equals(api.getApiTarget())) {
			JSONObject param = JSONObject.parseObject(api.getApiInput());
			TUserCarbonOperationDto dto = JSON.toJavaObject(param, TUserCarbonOperationDto.class);
			CarbonTypeDetailResult result = userCarbonOperationserivce.getCarbonOperationByType(api.getUserToken(),
					dto);
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 查询支持碳交易的城市列表
		else if ("trade_city".equals(api.getApiTarget())) {
			TradeCityResult result = tradeService.queryAllTradeCity();
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 按城市ID/全国(cityId为空)分页查询碳交易行情
		else if ("trade_deals".equals(api.getApiTarget())) {
			TTradeDealDto dto = obj.toJavaObject(TTradeDealDto.class);
			TradeDealResult result = tradeService.queryDealsByCityId(dto);
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		//按城市ID/全国(cityId为空)和时间(当前时间向前查询1月|3月|6月|1年)碳交易行情用于绘制折线图
		else if("trade_deals_chart".equals(api.getApiTarget())) {
			TTradeDealDto dto = obj.toJavaObject(TTradeDealDto.class);
			TradeDealChartResult result = tradeService.queryDealsByCityIdAndPeriod(dto);
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 用户委托买/卖
		else if ("trade_send_order".equals(api.getApiTarget())) {
			TTradeOrder tradeOrder = obj.toJavaObject(TTradeOrder.class);
			BaseResult result = tradeService.sendTradeOrder(tradeOrder, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 查询用户在某个标的上的可买,可卖,现价
		else if ("trade_deal_price_amount".equals(api.getApiTarget())) {
			TTradeDealDto dto = obj.toJavaObject(TTradeDealDto.class);
			TradePriceAvaiAmountResult result = tradeService.getCurrentPriceAndAvailableAmount(dto, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 查询当前用户持仓
		else if ("trade_balance".equals(api.getApiTarget())) {
			TradeBalanceResult result = tradeService.getUserBalance(api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 查询用户委托(成交)
		else if ("trade_query_order".equals(api.getApiTarget())) {
			TTradeOrderDto dto = null;
			if (obj != null) {
				dto = obj.toJavaObject(TTradeOrderDto.class);
			} else {
				dto = new TTradeOrderDto();
			}
			TradeOrderResult result = tradeService.getUserTradeOrder(dto, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 换算接口(碳币冲值)
		else if ("exchange_money".equals(api.getApiTarget())) {
			String carbonMoney = obj.getString("carbonMoney");
			String result = "{money:0.0}";
			if (StringUtils.isNotBlank(carbonMoney)) {
				String ratio = PropHelper.getValue("carbon_money_ratio");
				result = "{money:" + String.format("%.2f", Double.parseDouble(carbonMoney) * Double.valueOf(ratio))
						+ "}";
			}
			return JSONObject.parseObject(result);
		}
		// 创建碳币充值订单
		else if ("carbon_money_order".equals(api.getApiTarget())) {
			TOrderRecharge recharge = obj.toJavaObject(TOrderRecharge.class);
			CarbonRechargeResult result = userCarbonOperService.carbonRecharge(api.getUserToken(), recharge);
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 创建充值网关支付参数
		else if ("r".equals(api.getApiTarget())) {
			TOrderRecharge recharge = obj.toJavaObject(TOrderRecharge.class);
			CarbonRechargeResult result = userCarbonOperService.carbonRecharge(api.getUserToken(), recharge);
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		/**
		 * ================= 碳币相关 end ======================
		 */
		/**
		 * ================= 点点商城相关 start ======================
		 */
		// 创建订单
		else if ("order_create".equals(api.getApiTarget())) {
			TProductOrderDto dto = obj.toJavaObject(TProductOrderDto.class);
			BaseResult result = productOrderService.createOrder(dto, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 订单确认 - zht
		else if ("order_confirm".equals(api.getApiTarget())) {
			TProductOrderDto dto = obj.toJavaObject(TProductOrderDto.class);
			BaseResult result = productOrderService.confirmOrder(dto, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 订单详情
		else if ("order_detail".equals(api.getApiTarget())) {
			String orderCode = obj.getString("orderCode");
			EntityResult result = productOrderService.findOrderDetailByCode(orderCode, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 修改收货地址
		else if ("address_update".equals(api.getApiTarget())) {
			TUserAddressUpdateDto entity = obj.toJavaObject(TUserAddressUpdateDto.class);
			BaseResult result = userAddressService.updateByCode(entity, api.getUserToken());
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 获取商品详细信息 - Yangcl
		else if ("product_detail".equals(api.getApiTarget())) {
			String pcode = obj.getString("productCode");
			return productInfoService.getProductInfo(pcode);
		}
		// 我的主页-商品订单-6.6.2. 商品订单列表 - Yangcl
		else if ("order_list".equals(api.getApiTarget())) {
			return productOrderService.findProductOrderList(obj , api.getUserToken());
		}
		// 新增收货地址 - Yangcl
		else if ("address_add".equals(api.getApiTarget())) {
			return userAddressService.addUserAddress(obj, api.getUserToken());
		}
		// 删除收货地址 - Yangcl
		else if ("address_del".equals(api.getApiTarget())) {
			return userAddressService.deleteUserAddress(obj, api.getUserToken());
		}
		// 一条收货地址的数据 - Yangcl
		else if ("address_detail".equals(api.getApiTarget())) {
			return userAddressService.findUserAddress(obj, api.getUserToken());
		}
		// 同步客户端收藏的地址到数据库 - Yangcl
		else if ("rsync_address_enshrine".equals(api.getApiTarget())) {
			return addressEnshrineService.rsyncAddressEnshrine(obj, api.getUserToken());
		}
		//  获取用户收藏地址列表 - Yangcl
		else if ("address_enshrine_list".equals(api.getApiTarget())) {
			return addressEnshrineService.getUserAddressEnshrineList(api.getUserToken());
		}
		
		
		// 商品列表 - zht
		else if ("product_list".equals(api.getApiTarget())) {
			TProductInfoDto dto = obj.toJavaObject(TProductInfoDto.class);
			// 0 可售
			dto.setFlagSellable(0);
			// 库存大于0
			dto.setStockNumFlag("gt0");
			TPageProductListResult result = productInfoService.findProductListPage(dto);
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 获取城市列表
		else if ("area_select".equals(api.getApiTarget())) {
			String parentCode = obj.getString("areaId");
			DataResult result = areaService.findDataByParent(parentCode);
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		// 收货地址列表-zht
		else if ("address_list".equals(api.getApiTarget())) {
			TUserAddressDto dto = obj.toJavaObject(TUserAddressDto.class);
			BaseResult result = userAddressService.findUserAddressPage(dto);
			return JSONObject.parseObject(JSONObject.toJSONString(result));
		}
		/**
		 * ================= 点点商城相关 end ======================
		 */
		else {
			BaseResult result = new BaseResult();
			result.setResultCode(Constant.RESULT_ERROR);
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

	@RequestMapping("rechargePay/{orderCode}/{payType}")
	public String rechargePay(@PathVariable("orderCode") String orderCode, @PathVariable("payType") String payType,
			HttpServletRequest request, HttpServletResponse response) {
		String openID = request.getParameter("openID");
		String returnUrl = request.getParameter("returnUrl");
		OrderPayResult result = orderService.orderPay(openID, orderCode, payType, returnUrl);
		// 将支付回调接口换成充值回调接口
		if (result.getRedirectUrl().contains(XmasPayConfig.getPayGateReturnUrl())) {
			String newUrl = result.getRedirectUrl().replace(XmasPayConfig.getPayGateReturnUrl(),
					XmasPayConfig.getPayGateReturnUrlForRecharge());
			result.setRedirectUrl(newUrl);
		}

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

	@RequestMapping("payNotifyForRecharge")
	@ResponseBody
	public String payNotifyForRecharge(HttpServletRequest request, HttpServletResponse response) {
		Enumeration<String> names = request.getParameterNames();
		Map<String, String> notifyParam = new HashMap<String, String>();
		String name;
		while (names.hasMoreElements()) {
			name = names.nextElement();
			notifyParam.put(name, StringUtils.trimToEmpty(request.getParameter(name)));
		}

		PayGateNotifyPayProcess.PaymentResult result = PayServiceSupport.payGateNotify(notifyParam);
		TPayment entity = new TPayment();
		entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
		if (result.getResultCode() == PaymentResult.SUCCESS) {
			// 更新用户充值记录状态为已支付
			String orderCode = notifyParam.get("c_order");
			TOrderRecharge rechargeRec = userCarbonOperService.selectRechargeRecByOrderCode(orderCode);

			// 更新用户碳币
			TUser user = new TUser();
			user.setUserCode(rechargeRec.getBuyerCode());
			user = userService.selectByCode(user.getUserCode());
			user.setCarbonMoney(
					user.getCarbonMoney().add(rechargeRec.getCarbonMoney()).setScale(2, BigDecimal.ROUND_DOWN));

			// 更新用户充值记录状态为已支付
			rechargeRec.setStatus(new Integer(3));
			userCarbonOperService.updateRechargeRec(rechargeRec);

			// 增加碳币购买记录
			TUserCarbonOperation carbonOperation = new TUserCarbonOperation();
			carbonOperation.setUuid(WebHelper.getInstance().genUuid());
			carbonOperation.setCode(WebHelper.getInstance().getUniqueCode("LC"));
			carbonOperation.setUserCode(user.getUserCode());
			carbonOperation.setOperationType("DC170208100003");
			carbonOperation.setOperationTypeChild("DC170208100006");
			carbonOperation.setCarbonSum(rechargeRec.getCarbonMoney());
			carbonOperation.setCreateUser(user.getUserCode());
			carbonOperation.setCreateTime(DateUtil.getSysDateTime());
			userCarbonOperationserivce.insertSelective(carbonOperation);

			// TODO 支付成功则插入日志记录：paymentService TPayment
			entity.setOrderCode(orderCode);
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
			entity.setCreateUser(rechargeRec.getBuyerCode());

		} else {
			// TODO 支付失败则插入日志记录：paymentService
			entity.setOrderCode(result.notify.bigOrderCode);
			entity.setSuccmark("false");
			entity.setCause("支付宝调用失败");
		}
		paymentService.insertSelective(entity);

		if (StringUtils.equals(result.reURL, XmasPayConfig.getPayGateDefaultReURL())) {
			result.reURL = XmasPayConfig.getPayGateDefaultReURLForRecharge();
		}
		StringBuilder build = new StringBuilder();
		build.append("<result>1</result><reURL>" + result.reURL + "</reURL>");
		if (result.getResultCode() != PaymentResult.SUCCESS) {
			build.append("<msg>").append(result.getResultMessage()).append("</msg>");
		}
		return build.toString();
	}
}
