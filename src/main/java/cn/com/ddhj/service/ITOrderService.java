package cn.com.ddhj.service;

import javax.servlet.http.HttpServletRequest;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.TOrderDto;
import cn.com.ddhj.model.TOrder;
import cn.com.ddhj.result.order.OrderAddResult;
import cn.com.ddhj.result.order.OrderAffirmResult;
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
	TOrderResult findEntityToPage(TOrderDto dto, HttpServletRequest request);

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
}
