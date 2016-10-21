package cn.com.ddhj.service;

import javax.servlet.http.HttpServletRequest;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.TOrderDto;
import cn.com.ddhj.model.TOrder;
import cn.com.ddhj.result.order.OrderAddResult;
import cn.com.ddhj.result.order.OrderAffirmResult;
import cn.com.ddhj.result.order.OrderPayResult;
import cn.com.ddhj.result.order.OrderTotal;
import cn.com.ddhj.result.order.SysOrderDataResult;
import cn.com.ddhj.result.order.TOrderResult;

/**
 * 
 * 类: ITOrderService <br>
 * 描述: 订单表业务处理接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月7日 下午12:58:43
 */
public interface ITOrderService extends IBaseService<TOrder, TOrderDto> {

	/**
	 * 
	 * 方法: insertSelective <br>
	 * 描述: 添加新的订单
	 * 
	 * @param entity
	 * @return
	 * @see cn.com.ddhj.service.IBaseService#insertSelective(cn.com.ddhj.model.BaseModel)
	 */
	OrderAddResult insertSelective(TOrder entity, String userToken);

	/**
	 * 
	 * 方法: updateByCode <br>
	 * 描述: 编辑现有订单 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月7日 下午3:29:13
	 * 
	 * @param entity
	 * @param userToken
	 * @return
	 */
	BaseResult updateByCode(TOrder entity, String userToken);

	/**
	 * 
	 * 方法: findEntityToPage <br>
	 * 描述: 获取订单列表分页
	 * 
	 * @param dto
	 * @return
	 * @see cn.com.ddhj.service.IBaseService#findEntityToPage(cn.com.ddhj.dto.BaseDto)
	 */
	TOrderResult findEntityToPage(TOrderDto dto, String userToken, HttpServletRequest request);

	/**
	 * 按定单编号查询定单
	 * 
	 * @param orderCode
	 * @return
	 */
	TOrder selectByCode(String orderCode);

	/**
	 * 
	 * 方法: orderAffirm <br>
	 * 描述: 根据报告code数组查询报告集合 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月7日 下午4:31:34
	 * 
	 * @param codes
	 * @return
	 */
	OrderAffirmResult orderAffirm(String codes);

	/**
	 * 订单支付
	 * 
	 * @return
	 */
	OrderPayResult orderPay(String openID, String orderCode, String payType, String returnUrl);

	/**
	 * 
	 * 方法: getOrderTotal <br>
	 * 描述: 根据订单状态查询订单数量 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月12日 下午1:39:46
	 * 
	 * @param status
	 * @param userToken
	 * @return
	 */
	OrderTotal getOrderTotal(Integer status, String userToken);

	/**
	 * 
	 * 方法: getOrderBySys <br>
	 * 描述: 后台管理查询订单列表 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月21日 下午9:59:34
	 * 
	 * @param dto
	 * @return
	 */
	SysOrderDataResult getOrderBySys(TOrderDto dto);
}
