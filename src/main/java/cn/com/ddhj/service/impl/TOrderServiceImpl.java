package cn.com.ddhj.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.TOrderDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TOrderMapper;
import cn.com.ddhj.mapper.report.TReportMapper;
import cn.com.ddhj.mapper.user.TUserLoginMapper;
import cn.com.ddhj.mapper.user.TUserMapper;
import cn.com.ddhj.model.TOrder;
import cn.com.ddhj.model.report.TReport;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.model.user.TUserLogin;
import cn.com.ddhj.result.order.OrderAddResult;
import cn.com.ddhj.result.order.OrderAffirmResult;
import cn.com.ddhj.result.order.OrderPayResult;
import cn.com.ddhj.result.order.OrderTotal;
import cn.com.ddhj.result.order.SysOrderDataResult;
import cn.com.ddhj.result.order.TOrderResult;
import cn.com.ddhj.service.ITOrderService;
import cn.com.ddhj.service.impl.orderpay.OrderPayProcess;
import cn.com.ddhj.service.impl.orderpay.prepare.PayGatePreparePayProcess;
import cn.com.ddhj.util.DateUtil;

/**
 * 
 * 类: TOrderServiceImpl <br>
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

	@Override
	public TOrderResult findEntityToPage(TOrderDto dto, String userToken, HttpServletRequest request) {
		TOrderResult result = new TOrderResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login != null) {
			TUser user = userMapper.findTUserByUuid(login.getUserToken());
			if (user != null) {
				dto.setStart(dto.getPageIndex() * dto.getPageSize());
				dto.setCreateUser(user.getUserCode());
				List<TOrder> list = mapper.findEntityAll(dto);
				int total = mapper.findEntityAllCount(dto);
				if (list != null && list.size() > 0) {
					if (request != null) {
						String path = request.getContextPath();
						String basePath = request.getScheme() + "://" + request.getServerName() + ":"
								+ request.getServerPort() + path + "/";
						for (int i = 0; i < list.size(); i++) {
							TOrder order = list.get(i);
							order.setPath(basePath + order.getPath());
						}
					}
					result.setResultCode(0);
				} else {
					result.setResultCode(-1);
					result.setResultMessage("获取数据为空");
					list = new ArrayList<TOrder>();
				}
				result.setRepList(list);
				result.setRepCount(total);
			} else {
				result.setResultCode(-1);
				result.setResultMessage("用户不存在");
			}
		} else {
			result.setResultCode(-1);
			result.setResultMessage("用户未登录");
		}
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
				entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
				String code = WebHelper.getInstance().getUniqueCode("D");
				entity.setCode(code);
				entity.setCreateUser(user.getUserCode());
				entity.setCreateTime(DateUtil.getSysDateTime());
				entity.setUpdateUser(user.getUserCode());
				entity.setUpdateTime(DateUtil.getSysDateTime());
				mapper.insertSelective(entity);
				result.setResultCode(0);
				result.setResultMessage("创建订单成功");
				result.setCode(code);
			} else {
				result.setResultCode(-1);
				result.setResultMessage("无效用户");
			}
		} else {
			result.setResultCode(-1);
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
				result.setResultCode(0);
				result.setResultMessage("编辑订单成功");
			} else {
				result.setResultCode(-1);
				result.setResultMessage("无效用户");
			}
		} else {
			result.setResultCode(-1);
			result.setResultMessage("无效用户");
		}
		return result;
	}

	/**
	 * 
	 * 方法: orderAffirm <br>
	 * 
	 * @param codes
	 * @return
	 * @see cn.com.ddhj.service.ITOrderService#orderAffirm(java.lang.String)
	 */
	@Override
	public OrderAffirmResult orderAffirm(String codes) {
		OrderAffirmResult result = new OrderAffirmResult();
		if (codes != null && !"".equals(codes)) {
			try {
				List<String> list = Arrays.asList(codes.split(","));
				if (list != null && list.size() > 0) {
					List<TReport> reports = reportMapper.findRreportByChart(list);
					if (reports != null && reports.size() > 0) {
						double payMoney = 0;
						for (int i = 0; i < reports.size(); i++) {
							TReport r = reports.get(i);
							r.setLevelList(reportMapper.findReportByHousesCode(r.getHousesCode()));
							payMoney += r.getPrice().doubleValue();
						}
						result.setResultCode(0);
						result.setResultMessage("获取环境报告成功");
						result.setPageMoney(BigDecimal.valueOf(payMoney));
						result.setReportList(reports);
					} else {
						result.setResultCode(-1);
						result.setResultMessage("环境报告为空");
					}
				} else {
					result.setResultCode(-1);
					result.setResultMessage("无效参数");
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.setResultCode(-1);
				result.setResultMessage("无效参数");
			}
		} else {
			result.setResultCode(-1);
			result.setResultMessage("无效参数");
		}
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
				result.setResultCode(-1);
				result.setResultMessage("用户不存在");
			}
		} else {
			result.setResultCode(-1);
			result.setResultMessage("用户未登录");
		}
		return result;
	}

	@Override
	public SysOrderDataResult getOrderBySys(TOrderDto dto) {
		SysOrderDataResult result = new SysOrderDataResult();
		PageHelper.startPage(dto.getPageIndex(), dto.getPageSize());
		List<Map<String, String>> list = mapper.findOrderAll(dto);
		if (list != null && list.size() > 0) {
			result.setResultCode(0);
		} else {
			list = new ArrayList<Map<String, String>>();
			result.setResultCode(-1);
			result.setResultMessage("查询订单列表为空");
		}
		PageInfo<Map<String, String>> page = new PageInfo<Map<String, String>>(list);
		result.setPage(page);
		return result;
	}
}