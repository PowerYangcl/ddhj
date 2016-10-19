package cn.com.ddhj.mapper;

import cn.com.ddhj.dto.TPaymentDto;
import cn.com.ddhj.model.TPayment;

public interface TPaymentMapper extends BaseMapper<TPayment, TPaymentDto> {
	int payInsertSelective(TPayment entity);
	
	public TPayment selectByOrderCode(String orderCode);
}
