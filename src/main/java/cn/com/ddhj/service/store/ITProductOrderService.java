package cn.com.ddhj.service.store;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.store.TProductOrderDto;
import cn.com.ddhj.model.TProductOrder;
import cn.com.ddhj.service.IBaseService;

public interface ITProductOrderService extends IBaseService<TProductOrder, TProductOrderDto> {

	BaseResult createOrder(TProductOrderDto dto,String userToken);
}
