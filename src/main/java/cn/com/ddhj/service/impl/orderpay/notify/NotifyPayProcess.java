package cn.com.ddhj.service.impl.orderpay.notify;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.ddhj.mapper.TOrderMapper;
import cn.com.ddhj.mapper.TOrderRechargeMapper;
import cn.com.ddhj.mapper.TPaymentMapper;
import cn.com.ddhj.model.TOrder;
import cn.com.ddhj.model.TOrderRecharge;
import cn.com.ddhj.service.impl.orderpay.AbstractPaymentProcess;
import cn.com.ddhj.service.impl.orderpay.config.OrderConfig;

/**
 * 支付网关的支付完成通知处理
 * @author zhaojunling
 */
public abstract class NotifyPayProcess<I extends NotifyPayProcess.PaymentInput, R extends NotifyPayProcess.PaymentResult> extends AbstractPaymentProcess<I, R>{

	@Autowired
	private TOrderMapper orderMapper;
	@Autowired
	private TOrderRechargeMapper rechargeMapper;
	
	@Autowired
	private TPaymentMapper paymentMapper;
	
	@Override
	public R process(I input) {
		R result = getResult();
		String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		
		if(input == null){
			result.setResultMessage("无输入参数");
			return result;
		}
		
		// 验证签名
		if(!verifyInput(input, result)){
			result.setResultCode(0);
			return warpResult(input,result);
		}
		
		NotifyPay notify = getNotifyPay(input);
		result.notify = notify;
		
		if(!notify.success){
			result.setResultCode(0);
			result.setResultMessage("不处理支付失败的通知");
			return warpResult(input,result);
		}
		
		BigDecimal dueMoney = new BigDecimal(0.0);
		TOrder order = orderMapper.selectByCode(StringUtils.trimToEmpty(notify.bigOrderCode));
		if(order == null){
			TOrderRecharge rorder = rechargeMapper.selectByOrderCode(notify.bigOrderCode);
			if(rorder == null) {
				result.setResultCode(0);
				result.setResultMessage("订单不存在: "+notify.bigOrderCode);
				return warpResult(input,result);
			} else {
				dueMoney = rorder.getPayPrice() == null ? new BigDecimal(0.0) : rorder.getPayPrice();
			}
		} else {
			dueMoney = order.getPayPrice() == null ? new BigDecimal(0.0) : order.getPayPrice();
		}
		
		BigDecimal payedMoney = new BigDecimal(notify.payedMoney);
		
		
		// 校验金额
		if((payedMoney.compareTo(dueMoney) != 0)){
			result.setResultCode(0);
			result.setResultMessage("支付金额和应付金额不一致");
			return warpResult(input,result);
		}

		// 保存具体的支付信息
		//savePayment(input, notify);
		
		// 兼容原有程序，所以分表插入一下历史表数据
		Map<String, String> mDataMap = new ConcurrentHashMap<String, String>();
		if(OrderConfig.PAY_TYPE_3.equals(notify.orderPayType)){
			// 保存支付宝支付信息
//			mDataMap.put("out_trade_no", notify.bigOrderCode);
//			mDataMap.put("trade_status", "TRADE_SUCCESS");
//			mDataMap.put("sign_type", "");
//			mDataMap.put("sign", "");
//			mDataMap.put("trade_no", notify.tradeNo);
//			mDataMap.put("total_fee", orderInfoSupper.get("due_money").toString());
//			mDataMap.put("gmt_payment", date);
//			mDataMap.put("gmt_create", date);
//			mDataMap.put("param_value", "");
//			mDataMap.put("mark", "");
//			mDataMap.put("create_time", date);
//			mDataMap.put("process_time", date);
//			mDataMap.put("process_result", "");
//			mDataMap.put("payment_code", "");
//			mDataMap.put("flag_success", "1");
//			payService.saveOrderPayment(mDataMap);
		}else if(OrderConfig.PAY_TYPE_5.equals(notify.orderPayType)){
			// 保存微信支付信息
//			mDataMap.put("appid", "");
//			mDataMap.put("mch_id", "");
//			mDataMap.put("out_trade_no", notify.bigOrderCode);
//			mDataMap.put("transaction_id", notify.tradeNo);
//			mDataMap.put("time_end", date);
//			mDataMap.put("sign", "");
//			
//			mDataMap.put("trade_type", "");
//			mDataMap.put("bank_type", "");
//			mDataMap.put("result_code", "");
//			
//			mDataMap.put("total_fee", orderInfoSupper.get("due_money").toString());
//			mDataMap.put("param_value", "");
//			mDataMap.put("mark", "001");
//			mDataMap.put("process_time", date);
//			mDataMap.put("process_result", "");
//			mDataMap.put("payment_code", "");
//			mDataMap.put("flag_success", "1");
			//payService.saveOrderPaymentWechat(mDataMap);
		}

		return warpResult(input,result);
	}
	
	/** 可能需要对结果做些额外操作,设置返回值等 */
	protected R warpResult(I input, R result){
		return result;
	}
		
	/**
	 * 验证请求的参数签名
	 * @param inputParam
	 * @return true 签名成功， false 签名失败
	 */
	protected abstract boolean verifyInput(I input, R result);
	
	/**
	 * 从参数中解析订单的基础信息
	 * @param inputParam
	 * @return
	 */
	protected abstract NotifyPay getNotifyPay(I input);
	
	/**
	 * 获取支付来源，如：paygate通过网关支付、lianlianpay通过连连支付 
	 * @return
	 */
	protected abstract String getFromType();
	
	/**
	 * 保存交易记录
	 * @param input
	 * @param notify
	 */
	protected abstract void savePayment(I input, NotifyPay notify);
	
	/**
	 * 获取支付请求输入对象
	 */
	public abstract static class PaymentInput extends cn.com.ddhj.service.impl.orderpay.PaymentInput {
		public abstract String getBigOrderCode();
	}
	
	/**
	 * 获取支付参数的输出对象
	 */
	public abstract static class PaymentResult extends cn.com.ddhj.service.impl.orderpay.PaymentResult {
		public NotifyPay notify;
	}
}
