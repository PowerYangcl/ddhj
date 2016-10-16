package cn.com.ddhj.service.impl.orderpay.prepare;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.ddhj.mapper.TOrderMapper;
import cn.com.ddhj.model.TOrder;
import cn.com.ddhj.service.impl.orderpay.AbstractPaymentProcess;
import cn.com.ddhj.service.impl.orderpay.PaymentChannel;
import cn.com.ddhj.service.impl.orderpay.config.OrderConfig;

/**
 * 请求支付网关获取支付信息基类
 * @author zhaojunling
 */
public abstract class PreparePayProcess<I extends PreparePayProcess.PaymentInput, R extends PreparePayProcess.PaymentResult> extends AbstractPaymentProcess<I, R>{

	@Autowired
	private TOrderMapper orderMapper;
	
	@Override
	public R process(I input) {
		R result = getResult();
		
		if(input == null){
			result.setResultMessage("无输入参数");
			return result;
		}
		
		String bigOrderCode = input.bigOrderCode;

		// 检查订单是否满足支付条件
		checkOrderPreparePay(bigOrderCode, result);
		
		if(result.getResultCode() != PaymentResult.SUCCESS){
			return result;
		}
		
		return doProcess(input);
	}
	
	protected abstract R doProcess(I input);
	
	
	/**
	 * 检查订单是否满足支付条件
	 * @param bigOrderCode
	 * @return
	 */
	private PaymentResult checkOrderPreparePay(String bigOrderCode, PaymentResult result){
		if(StringUtils.isBlank(bigOrderCode)){
			result.setResultCode(0);
			result.setResultMessage("bigOrderCode订单号为空");
			return result;
		}
		
		
		TOrder order = orderMapper.selectByCode(bigOrderCode);
		if(order == null){
			result.setResultCode(0);
			result.setResultMessage("订单不存在[ "+bigOrderCode+"]");
			return result;
		}
		
		if(order.getStatus() == 1){
			result.setResultCode(0);
			result.setResultMessage("订单已支付");
			return result;
		}
		
		if(order.getStatus() == 2){
			result.setResultCode(0);
			result.setResultMessage("订单已取消");
			return result;
		}
		
		if(order.getPayPrice() == null || order.getPayPrice().compareTo(new BigDecimal(0.0)) <= 0) {
			result.setResultCode(0);
			result.setResultMessage("订单支付金额小于等于0.0");
			return result;
		}

		return null;
	}
	
	/**
	 * 获取支付请求输入对象
	 */
	public abstract static class PaymentInput extends cn.com.ddhj.service.impl.orderpay.PaymentInput {
		public PaymentChannel payChannel;
		public String bigOrderCode;
	}
	
	/**
	 * 获取支付参数的输出对象
	 */
	public abstract static class PaymentResult extends cn.com.ddhj.service.impl.orderpay.PaymentResult {}
}
