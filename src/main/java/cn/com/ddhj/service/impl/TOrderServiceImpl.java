package cn.com.ddhj.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.TOrderDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TOrderMapper;
import cn.com.ddhj.mapper.TUserLoginMapper;
import cn.com.ddhj.mapper.TUserMapper;
import cn.com.ddhj.mapper.report.TReportMapper;
import cn.com.ddhj.model.TOrder;
import cn.com.ddhj.model.TUser;
import cn.com.ddhj.model.TUserLogin;
import cn.com.ddhj.model.report.TReport;
import cn.com.ddhj.result.order.OrderAddResult;
import cn.com.ddhj.result.order.OrderAffirmResult;
import cn.com.ddhj.result.order.TOrderResult;
import cn.com.ddhj.service.ITOrderService;
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
	public TOrderResult findEntityToPage(TOrderDto dto, HttpServletRequest request) {
		TOrderResult result = new TOrderResult();
		dto.setStart(dto.getPageIndex() * dto.getPageSize());
		List<TOrder> list = mapper.findEntityAll(dto);
		int total = mapper.findEntityAllCount(dto);
		if (list != null && list.size() > 0) {
			if (request != null) {
				String path = request.getContextPath();
				String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ path + "/";
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
		return result;
	}

	/**
	 * 
	 * 方法: insertSelective <br>
	 * 描述: TODO
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
	 * 描述: TODO
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
	 * 描述: TODO
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

}
