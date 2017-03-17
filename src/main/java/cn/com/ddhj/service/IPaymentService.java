package cn.com.ddhj.service;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.TPaymentDto;
import cn.com.ddhj.model.TPayment;

public interface IPaymentService extends IBaseService<TPayment, TPaymentDto> {
	BaseResult insertSelective(TPayment entity, String userToken);
	
	BaseResult insertSelective(TPayment entity);
}
