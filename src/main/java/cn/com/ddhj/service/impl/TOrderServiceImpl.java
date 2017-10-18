package cn.com.ddhj.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.TOrderDto;
import cn.com.ddhj.helper.PropHelper;
import cn.com.ddhj.helper.ReportHelper;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TOrderMapper;
import cn.com.ddhj.mapper.report.TReportMapper;
import cn.com.ddhj.mapper.user.TUserCarbonOperationMapper;
import cn.com.ddhj.mapper.user.TUserLoginMapper;
import cn.com.ddhj.mapper.user.TUserMapper;
import cn.com.ddhj.model.TOrder;
import cn.com.ddhj.model.report.TReport;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.model.user.TUserCarbonOperation;
import cn.com.ddhj.model.user.TUserLogin;
import cn.com.ddhj.result.ReportResult;
import cn.com.ddhj.result.order.OrderAddResult;
import cn.com.ddhj.result.order.OrderAffirmResult;
import cn.com.ddhj.result.order.OrderPayResult;
import cn.com.ddhj.result.order.OrderTotal;
import cn.com.ddhj.result.order.SysOrderDataResult;
import cn.com.ddhj.result.order.TOrderResult;
import cn.com.ddhj.service.ITOrderService;
import cn.com.ddhj.service.impl.orderpay.OrderPayProcess;
import cn.com.ddhj.service.impl.orderpay.prepare.PayGatePreparePayProcess;
import cn.com.ddhj.util.Constant;
import cn.com.ddhj.util.DateUtil;

/**
 * 
 * 类: TOrderServiceImpl <br>
 * 测试地址：http://api.sys.ecomapit.com/ddhj/api.htm?apiTarget=order_data&api_key=appfamilyhas&apiInput={"pageSize":20,"pageIndex":0,"status":"1"}&api_timespan=2017-08-25+11:35:12&userToken=3b6aa52ba7ea47f093a995660cfa2d6f&api_secret=7b5ab856eb03ebbf6e99119c7b75e611
 * 描述: 订单表业务处理接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月7日 下午12:59:33
 */
@Service
public class TOrderServiceImpl extends BaseServiceImpl<TOrder, TOrderMapper, TOrderDto> implements ITOrderService {

	@Autowired
	private TOrderMapper mapper;
	@Autowired
	private TUserLoginMapper loginMapper;
	@Autowired
	private TUserMapper userMapper;
	@Autowired
	private TReportMapper reportMapper;
	@Autowired
	private TUserCarbonOperationMapper carbonOperationMapper;

	@Override
	public TOrderResult findEntityToPage(TOrderDto dto, String userToken, HttpServletRequest request) {
		TOrderResult result = new TOrderResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login == null) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户未登录");
			return result;
		}
		TUser user = userMapper.findTUserByUuid(login.getUserToken());
		if (user == null) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户不存在");
			return result;
		}

		dto.setStart(dto.getPageIndex() * dto.getPageSize());
		dto.setCreateUser(user.getUserCode());
		List<TOrder> list = mapper.findEntityAll(dto);
		int total = mapper.findEntityAllCount(dto);
		if (list == null && list.size() == 0) {
			result.setResultCode(Constant.RESULT_NULL);
			result.setResultMessage("获取数据为空");
			list = new ArrayList<TOrder>();
			result.setRepList(list);
			result.setRepCount(total);
			return result;
		}

		if (request != null) {
			for (int i = 0; i < list.size(); i++) {
				TOrder order = list.get(i);
				order.setPath(order.getPath());
				// 报告订单已支付
				if (order.getStatus() == 1 || order.getStatus() == 2 || order.getStatus() == 3) {
					// 计算报告购买时间与当前时间差值,大于半年则提示更新
					try {
						Date buyTime = DateUtil.strToDate(order.getUpdateTime());
						Date deadLine = DateUtil.addDays(buyTime, 31 * 6);
						if (new Date().compareTo(deadLine) >= 0) {
							// 报告要更新
							String reportCode = order.getReportCode();
							if (StringUtils.isNotBlank(reportCode)) {
								TReport report = reportMapper.selectByCode(reportCode);
								order.getReportUpdate().setAddress(order.getAddress());
								order.getReportUpdate().setCity(report.getCity());
								order.getReportUpdate().setLpCode(report.getHousesCode());
								order.getReportUpdate().setPosition(report.getPosition());
							}
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else if (order.getStatus() == 0) {
					// 未支付环境报告完整版返回为空
					order.setReportFull("");
				}
			}
		}
		String ratio = PropHelper.getValue("carbon_money_ratio");
		if (ratio != null) {
			Double limit = Double.valueOf(PropHelper.getValue("present_carbon_total_rmb"));
			BigDecimal b = new BigDecimal(limit / Double.parseDouble(ratio));
			Double max = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			result.setMaxCarbon((int) Math.ceil(max));
		}
		result.setResultCode(Constant.RESULT_SUCCESS);
		result.setRepList(list);
		result.setRepCount(total);
		return result;
	}

	/**
	 * 按定单编号查询定单
	 * 
	 * @param orderCode
	 * @return
	 */
	@Override
	public TOrder selectByCode(String orderCode) {
		return mapper.selectByCode(orderCode);
	}

	/**
	 * 
	 * 方法: insertSelective <br>
	 * 
	 * @param entity
	 * @param userToken
	 * @return
	 * @see cn.com.ddhj.service.ITOrderService#insertSelective(cn.com.ddhj.model.TOrder,
	 *      java.lang.String)
	 */
	@Override
	public OrderAddResult insertSelective(TOrder entity, String userToken) {
		OrderAddResult result = new OrderAddResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login != null) {
			TUser user = userMapper.findTUserByUuid(login.getUserToken());
			if (user != null) {
				if (entity.getCarbonMoney() != null && entity.getCarbonMoney().compareTo(BigDecimal.ZERO) > 0) {
					if (user.getCarbonMoney().compareTo(entity.getCarbonMoney()) < 0) {
						// 用户帐户碳币金额小于支付金额,无法创建 订单
						result.setResultCode(Constant.RESULT_ERROR);
						result.setResultMessage("用户帐户碳币金额小于支付金额,无法创建订单");
						return result;
					}
				}

				entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
				String code = WebHelper.getInstance().getUniqueCode("D");
				entity.setCode(code);
				entity.setCreateUser(user.getUserCode());
				entity.setCreateTime(DateUtil.getSysDateTime());
				entity.setUpdateUser(user.getUserCode());
				entity.setUpdateTime(DateUtil.getSysDateTime());
				/**
				 * 判断炭币是否与应付金额相同，如果金额相同，订单状态为已支付未下载
				 */
				BigDecimal carbonMoney = entity.getCarbonMoney().setScale(2, BigDecimal.ROUND_HALF_UP);
				if (carbonMoney.compareTo(BigDecimal.ZERO) > 0) {
					entity.setCarbonMoney(carbonMoney);
					String carbon_money_ratio = PropHelper.getValue("carbon_money_ratio");
					BigDecimal carbonToMoney = carbonMoney
							.multiply(BigDecimal.valueOf(Double.valueOf(carbon_money_ratio)));
					if (entity.getCheckPayMoney().compareTo(carbonToMoney) == 0) {
						entity.setStatus(1);
					}
				}
				/**
				 * 根据楼盘编码获取报告信息
				 */
				TReport report = reportMapper.findSimplificationReport(entity.getLpCode());
				entity.setReportSimplification(report.getPath());
				ReportResult reportResult = ReportHelper.getInstance().createUserReport(entity.getLpCode(),
						entity.getBuyerCode());
				if (reportResult.getResultCode() == Constant.RESULT_SUCCESS) {
					entity.setReportFull(reportResult.getUrl());
				} else {
					result.setResultCode(reportResult.getResultCode());
					result.setResultMessage(reportResult.getResultMessage());
					return result;
				}
				mapper.insertSelective(entity);
				if (carbonMoney.compareTo(BigDecimal.ZERO) > 0) {
					/**
					 * 如果使用了炭币，扣除炭币
					 */
					TUser updateCarbonMoneyUser = new TUser();
					updateCarbonMoneyUser.setUserCode(user.getUserCode());
					updateCarbonMoneyUser.setCarbonMoney(user.getCarbonMoney().subtract(entity.getCarbonMoney()));
					updateCarbonMoneyUser.setUpdateUser(user.getUserCode());
					updateCarbonMoneyUser.setUpdateTime(DateUtil.getSysDateTime());
					userMapper.updateByCode(updateCarbonMoneyUser);
					/**
					 * 如果使用炭币添加炭币使用日志<br>
					 * 2017-02-09 zhy<br>
					 */
					TUserCarbonOperation carbonOperation = new TUserCarbonOperation();
					carbonOperation.setUuid(WebHelper.getInstance().genUuid());
					carbonOperation.setCode(WebHelper.getInstance().getUniqueCode("LC"));
					carbonOperation.setUserCode(user.getUserCode());
					carbonOperation.setOperationType("DC170208100003");
					carbonOperation.setOperationTypeChild("DC170208100007");
					carbonOperation.setCarbonSum(entity.getCarbonMoney());
					carbonOperation.setCreateUser(user.getUserCode());
					carbonOperation.setCreateTime(DateUtil.getSysDateTime());
					carbonOperationMapper.insertSelective(carbonOperation);
				}
				result.setResultCode(Constant.RESULT_SUCCESS);
				result.setResultMessage("创建订单成功");
				result.setCode(code);
			} else {
				result.setResultCode(Constant.RESULT_ERROR);
				result.setResultMessage("无效用户");
			}
		} else {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("无效用户");
		}
		return result;
	}

	/**
	 * 
	 * 方法: updateByCode <br>
	 * 
	 * @param entity
	 * @param userToken
	 * @return
	 * @see cn.com.ddhj.service.ITOrderService#updateByCode(cn.com.ddhj.model.TOrder,
	 *      java.lang.String)
	 */
	@Override
	public BaseResult updateByCode(TOrder entity, String userToken) {
		BaseResult result = new BaseResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login != null) {
			TUser user = userMapper.findTUserByUuid(login.getUserToken());
			if (user != null) {
				entity.setUpdateUser(user.getUserCode());
				entity.setUpdateTime(DateUtil.getSysDateTime());
				mapper.updateByCode(entity);
				result.setResultCode(Constant.RESULT_SUCCESS);
				result.setResultMessage("编辑订单成功");
			} else {
				result.setResultCode(Constant.RESULT_ERROR);
				result.setResultMessage("无效用户");
			}
		} else {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("无效用户");
		}
		return result;
	}

	/**
	 * 
	 * 方法: orderAffirm <br>
	 * 
	 * @param codes
	 *            报告编号
	 * @param type
	 *            new|update 报告购买类型(new新购, update更新购买)
	 * @return
	 * @see cn.com.ddhj.service.ITOrderService#orderAffirm(java.lang.String)
	 */
	@Override
	public OrderAffirmResult orderAffirm(String codes, String type, String userToken) {
		OrderAffirmResult result = new OrderAffirmResult();
		if (StringUtils.isBlank(codes)) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("无效参数");
			return result;
		}

		List<String> list = Arrays.asList(codes.split(","));
		if (list == null || list.isEmpty()) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("无效参数");
			return result;
		}

		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login == null) {
			result.setResultCode(-1);
			result.setResultMessage("用户未登录");
			return result;
		}

		TUser user = userMapper.findTUserByUuid(login.getUserToken());
		if (user == null) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户不存在");
			return result;
		}

		String carbon_money_ratio = PropHelper.getValue("carbon_money_ratio");
		result.setCarbonMoney(user.getCarbonMoney());
		result.setCarbonToMoneyRatio(BigDecimal.valueOf(Double.valueOf(carbon_money_ratio)));

		List<TReport> reports = reportMapper.findRreportByChart(list);
		if (reports == null || reports.isEmpty()) {
			result.setResultCode(Constant.RESULT_NULL);
			result.setResultMessage("环境报告为空");
			return result;
		}

		double payMoney = 0;
		for (int i = 0; i < reports.size(); i++) {
			TReport r = reports.get(i);
			r.setLevelList(reportMapper.findReportByHousesCode(r.getHousesCode()));
			if (StringUtils.isBlank(type) || type.equals("new")) {
				payMoney += r.getPrice().doubleValue();
			} else if (type.equals("update")) {
				payMoney += r.getUpdatePrice().doubleValue();
			}
		}
		result.setResultCode(Constant.RESULT_SUCCESS);
		result.setResultMessage("获取环境报告成功");
		result.setPayMoney(BigDecimal.valueOf(payMoney));
		result.setReportList(reports);
		return result;
	}

	@Override
	public OrderPayResult orderPay(String openID, String orderCode, String payType, String returnUrl) {

		if (StringUtils.isBlank(returnUrl)) {
			returnUrl = "";
		}

		OrderPayResult payResult = new OrderPayResult();
		String errorMsg = null;

		PayGatePreparePayProcess.PaymentResult result = null;
		if ("PT161007100002".equals(payType)) {
			// 支付宝支付
			result = new OrderPayProcess().aliPayH5Prepare(orderCode, returnUrl, payType);
		} else if ("PT161007100001".equals(payType)) {
			// 微信支付
			if (StringUtils.isNotBlank(openID)) {
				result = new OrderPayProcess().wechatJSAPIPrepare(orderCode, openID, returnUrl, payType);
			} else {
				errorMsg = "openID is Empty!";
			}
		} else if ("PT161007100003".equals(payType)) {
			result = new OrderPayProcess().wechatJSAPIPrepare(orderCode, openID, returnUrl, payType);
		} else if ("PT161007100004".equals(payType)) {
			// app内调h5支付宝方式支付
			result = new OrderPayProcess().aliPayH5Prepare(orderCode, returnUrl, payType);
		} else {
			errorMsg = "PayType Not Implemented!";
		}

		if (result != null && !result.upFlagTrue()) {
			errorMsg = StringUtils.trimToEmpty(result.getResultMessage());
		}

		if (StringUtils.isEmpty(errorMsg)) {
			payResult.setRedirectUrl("redirect:" + result.payUrl);
			return payResult;
		} else {
			payResult.setErrorMsg(errorMsg);
			return payResult;
		}
	}

	/**
	 * 
	 * 方法: getOrderTotal <br>
	 * 
	 * @param status
	 * @param userToken
	 * @return
	 * @see cn.com.ddhj.service.ITOrderService#getOrderTotal(java.lang.Integer,
	 *      java.lang.String)
	 */
	@Override
	public OrderTotal getOrderTotal(Integer status, String userToken) {
		OrderTotal result = new OrderTotal();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login != null) {
			TUser user = userMapper.findTUserByUuid(login.getUserToken());
			if (user != null) {
				TOrderDto dto = new TOrderDto();
				dto.setStatus(status);
				dto.setCreateUser(user.getUserCode());
				int total = mapper.findEntityAllCount(dto);
				result.setTotal(total);
			} else {
				result.setResultCode(Constant.RESULT_ERROR);
				result.setResultMessage("用户不存在");
			}
		} else {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户未登录");
		}
		return result;
	}

	@Override
	public SysOrderDataResult getOrderBySys(TOrderDto dto) {
		SysOrderDataResult result = new SysOrderDataResult();
		PageHelper.startPage(dto.getPageIndex(), dto.getPageSize());
		List<Map<String, String>> list = mapper.findOrderAll(dto);
		System.out.println(JSON.toJSON(list));
		if (list != null && list.size() > 0) {
			result.setResultCode(Constant.RESULT_SUCCESS);
		} else {
			list = new ArrayList<Map<String, String>>();
			result.setResultCode(Constant.RESULT_NULL);
			result.setResultMessage("查询订单列表为空");
		}
		PageInfo<Map<String, String>> page = new PageInfo<Map<String, String>>(list);
		result.setPage(page);
		return result;
	}

	/**
	 * @descriptions 根据order code删除一条记录
	 *
	 * @param input
	 * @date 2017年8月8日 上午10:45:33
	 * @author Yangcl
	 * @version 1.0.0.1
	 */
	public JSONObject deleteReportOrder(JSONObject input) {
		JSONObject re = new JSONObject();
		String orderCode = input.getString("orderCode");
		Integer status = input.getInteger("status");
		if (StringUtils.isBlank(orderCode) || status == null) {
			re.put("resultCode", -1);
			re.put("resultMessage", "关键参数不得为空");
			return re;
		}
		if (status != 3) {
			re.put("resultCode", -1);
			re.put("resultMessage", "已作取消态的订单才能删除，请先取消订单");
			return re;
		}

		TOrder e = new TOrder();
		e.setCode(orderCode);
		e.setStatus(4); // 作废该订单
		e.setUpdateTime(DateUtil.getSysDateTime());
		Integer flag = mapper.updateOrderStatus(e);
		if (flag == 1) {
			re.put("resultCode", Constant.RESULT_SUCCESS);
			re.put("resultMessage", "删除成功");
		} else {
			re.put("resultCode", Constant.RESULT_ERROR);
			re.put("resultMessage", "删除失败");
		}
		return re;
	}

	@Override
	public JSONObject cancelReportOrder(JSONObject input) {
		JSONObject re = new JSONObject();
		String orderCode = input.getString("orderCode");
		Integer status = input.getInteger("status");
		if (StringUtils.isBlank(orderCode) || status == null) {
			re.put("resultCode", -1);
			re.put("resultMessage", "关键参数不得为空");
			return re;
		}
		if (status != 0) {
			re.put("resultCode", -1);
			re.put("resultMessage", "未支付状态的订单才能取消");
			return re;
		}

		TOrder e = new TOrder();
		e.setCode(orderCode);
		e.setUpdateTime(DateUtil.getSysDateTime());
		e.setStatus(3); // 取消该订单
		Integer flag = mapper.updateOrderStatus(e);
		if (flag == 1) {
			re.put("resultCode", Constant.RESULT_SUCCESS);
			re.put("resultMessage", "取消成功");
		} else {
			re.put("resultCode", Constant.RESULT_ERROR);
			re.put("resultMessage", "取消失败");
		}
		return re;
	}

	@Override
	public JSONObject deleteOne(Integer id) {
		JSONObject result = new JSONObject();
		Integer flag = mapper.deleteOne(id);
		if (flag == 1) {
			result.put("code", Constant.RESULT_SUCCESS);
			result.put("msg", "删除成功");
		} else {
			result.put("code", Constant.RESULT_ERROR);
			result.put("msg", "删除失败");
		}

		return result;
	}

	/**
	 * 
	 * 方法: refreshReport <br>
	 * 描述: TODO
	 * 
	 * @param lpCode
	 * @param userCode
	 * @return
	 * @see cn.com.ddhj.service.ITOrderService#refreshReport(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public BaseResult refreshReport(String code) {
		BaseResult result = new BaseResult();
		try {
			TOrder order = mapper.selectByCode(code);
			if (order != null) {
				ReportHelper.getInstance().createUserReport(order.getLpCode(), order.getCreateUser());
				result.setResultCode(Constant.RESULT_SUCCESS);
				result.setResultMessage("刷新成功");
			} else {
				result.setResultCode(Constant.RESULT_ERROR);
				result.setResultMessage("订单不存在");
			}

		} catch (Exception e) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("刷新报告失败，失败原因：" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ReportResult getOrderReportUrl(String code) {
		ReportResult result = new ReportResult();
		try {
			TOrder order = mapper.selectByCode(code);
			if (order != null && StringUtils.isNotBlank(order.getReportFull())) {
				result.setUrl(order.getReportFull());
				result.setResultCode(Constant.RESULT_SUCCESS);
				result.setResultMessage("获取订单报告访问地址成功");
			} else {
				result.setResultCode(Constant.RESULT_ERROR);
				result.setResultMessage("获取订单报告访问地址失败");

			}

		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("获取订单报告访问地址失败");
		}
		return result;
	}

	/**
	 * 
	 * 方法: refreshUserReport <br>
	 * 描述: TODO
	 * 
	 * @return
	 * @see cn.com.ddhj.service.ITOrderService#refreshUserReport()
	 */
	@Override
	public BaseResult refreshUserReport() {
		BaseResult result = new BaseResult();
		try {
			List<TOrder> orders = mapper.findOrderLPAndCreateUser();
			if (orders != null && orders.size() > 0) {
				for (TOrder order : orders) {
					ReportHelper.getInstance().createUserReport(order.getLpCode(), order.getCreateUser());
				}
			}
			result.setResultCode(Constant.RESULT_SUCCESS);
			result.setResultMessage("更新已支付订单环境报告成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("更新已支付订单环境报告失败，失败原因：" + e.getMessage());
		}
		return result;
	}

	@Override
	public BaseResult refreshUserReportAll() {
		BaseResult result = new BaseResult();
		try {
			List<TOrder> orders = mapper.findOrderLPAndCreateUserAll();
			if (orders != null && orders.size() > 0) {
				for (TOrder order : orders) {
					ReportHelper.getInstance().createUserReport(order.getLpCode(), order.getCreateUser());
				}
			}
			result.setResultCode(Constant.RESULT_SUCCESS);
			result.setResultMessage("更新已支付订单环境报告成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("更新已支付订单环境报告失败，失败原因：" + e.getMessage());
		}
		return result;
	}
}
