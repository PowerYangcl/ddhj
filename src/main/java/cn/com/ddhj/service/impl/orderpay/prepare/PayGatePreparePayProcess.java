package cn.com.ddhj.service.impl.orderpay.prepare;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import cn.com.ddhj.mapper.TOrderMapper;
import cn.com.ddhj.mapper.TOrderRechargeMapper;
import cn.com.ddhj.model.TOrder;
import cn.com.ddhj.model.TOrderRecharge;
import cn.com.ddhj.service.impl.orderpay.PaymentChannel;
import cn.com.ddhj.service.impl.orderpay.config.PayGateConfig;
import cn.com.ddhj.service.impl.orderpay.config.XmasPayConfig;
import cn.com.ddhj.service.impl.orderpay.utils.PayGateUtils;

/**
 * 请求支付网关获取支付信息基类
 * @author zhaojunling
 */
public abstract class PayGatePreparePayProcess<I extends PayGatePreparePayProcess.PaymentInput, R extends PayGatePreparePayProcess.PaymentResult> extends PreparePayProcess<I, R>{

	@Autowired
	private TOrderMapper orderMapper;
	@Autowired
	private TOrderRechargeMapper rechargeMapper;
	
	@Override
	protected R doProcess(I input) {
		R result = getResult();
		String bigOrderCode = input.bigOrderCode;
		BigDecimal payPrice = null;
		
		boolean rechargeOrder = false;
		TOrder order = orderMapper.selectByCode(bigOrderCode);
		if(order != null) {
			payPrice = order.getPayPrice();
		} else {
			TOrderRecharge rorder = rechargeMapper.selectByOrderCode(bigOrderCode);
			payPrice = rorder.getPayPrice();
			rechargeOrder = true;
		}

		Map<String,String> param = createPayGateParam(input);
		
		param.put("c_mid", XmasPayConfig.getPayGateMid());
		param.put("c_order", bigOrderCode);
		//modify XXX
		param.put("c_orderamount", String.valueOf(payPrice));
		param.put("c_ymd", DateFormatUtils.format(new Date(), "yyyyMMdd"));
		param.put("c_moneytype", "1");
		param.put("c_retflag", "1");
		if(rechargeOrder) {
			param.put("c_returl", XmasPayConfig.getPayGateReturnUrlForRecharge());
		} else {
			param.put("c_returl", XmasPayConfig.getPayGateReturnUrl());
		}
		
		param.put("notifytype", "1");
		param.put("c_version", "v2.0");
		
		if(input.memo1 != null){
			param.put("c_memo1", input.memo1);
		}
		if(input.memo2 != null){
			param.put("c_memo2", input.memo2);
		}
		
		// 如果设置了reurl则表示需要前台同步支付结果
		if(StringUtils.isNotEmpty(input.reurl)){
			param.put("c_reurl", input.reurl);
			param.put("c_retflag", "2");
		} else {
			if(rechargeOrder) {
				param.put("c_reurl", XmasPayConfig.getPayGateDefaultReURLForRecharge());
			} else {
				param.put("c_reurl", XmasPayConfig.getPayGateDefaultReURL());
			}
//			param.put("c_retflag", "2");
		}
		
		param.put("c_signstr", PayGateUtils.createSign(param, XmasPayConfig.getPayGatePass()));
		
		// 网页支付直接构造提交表单，非网页支付则用接口请求支付参数
		if(PaymentChannel.APP == input.payChannel 
				|| PaymentChannel.BARCODE == input.payChannel){
			String responseText = null;
			
			Map<String, String> logMap = new HashMap<String, String>();
			logMap.put("action", "submitPayGate");
			logMap.put("target", bigOrderCode);
			logMap.put("method", "com.srnpr.xmaspay.process.prepare.PayGatePreparePayProcess#doProcess");
			logMap.put("request", JSON.toJSONString(param));
			logMap.put("request_time", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			logMap.put("exception", "");
			
			try {
				
				responseText = PayGateUtils.createOrder(param, XmasPayConfig.getPayGateURL());
				
				logMap.put("response", responseText);
				logMap.put("response_time", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			} catch (Exception e) {
				result.setResultCode(0);
				result.setResultMessage("请求支付网关异常:"+e);
				return result;
			}
			
			// 解析网关返回参数
			prepareResult(input.payChannel, result, responseText);
			
			// 记录请求支付网关的返回结果日志
			logMap.put("flag_success", result.getResultCode()+"");
			logMap.put("create_time", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			//payService.saveXmasPayLog(logMap);
			
			if(result.getResultCode() != PaymentResult.SUCCESS){
				return result;
			}
		}else{
			if ("PT161007100003".equals(input.payType)) {
				param.put("c_paygate", PayGateConfig.getPayGateVal(PayGateConfig.Type.WECHAT_APP)+"");
//				param.remove("c_signstr");
//				param.put("c_signstr", PayGateUtils.createSign(param, XmasPayConfig.getPayGatePass()));
			} else if ("PT161007100004".equals(input.payType)) {
				param.put("c_paygate", PayGateConfig.getPayGateVal(PayGateConfig.Type.ALIPAY_APP)+"");
//				param.remove("c_signstr");
//				param.put("c_signstr", PayGateUtils.createSign(param, XmasPayConfig.getPayGatePass()));
			}
			param.put("c_retflag", "2");
			param.remove("c_signstr");
			param.put("c_signstr", PayGateUtils.createSign(param, XmasPayConfig.getPayGatePass()));
//			&c_paygate_account=653
//					&c_paygate_type=4
//					c_memo2=15210383700%7
//					c_name=15210383700
//			param.put("c_paygate_account", param.get("c_paygate").toString());
//			param.put("c_paygate_type", "4");
//			param.put("c_name", "15313168722");
//			param.put("c_memo2", "15313168722");
			result.payUrl = PayGateUtils.createPayGateUrl(param, XmasPayConfig.getPayGateURL());
		}
		
		return result;
	}
	
	/**
	 * 创建支付网关的请求参数,根据支付类型设置不同的支付网管参数
	 * @param input
	 * @return 设置好的参数集合map <br>
	 * 可能需要设置的参数: <br>
	 * c_paygate 支付方式 <br>
	 * c_paygate_type 支付接口类型 <br>
	 * c_paygate_account 接口类型账号<br>
	 * c_openid JSAPI支付时微信用户的OpenId<br>
	 * c_reurl js前台同步支付结果返回地址<br>
	 */
	protected abstract Map<String,String> createPayGateParam(I input); 
	
	/**
	 * 解析支付网关返回的参数,提取调起支付所需的参数
	 * @param responseText
	 * @return
	 */
	protected abstract void prepareResult(PaymentChannel channel,R result,String responseText);
	
	/**
	 * 获取支付请求输入对象
	 */
	public static class PaymentInput extends PreparePayProcess.PaymentInput {
		/** 需要同步返回支付结果的地址 */
		public String reurl;
		// 微信JSAPI支付时的用户openid
		public String openid;
		// 扩展参数一
		public String memo1;
		// 扩展参数二
		public String memo2;
		
		public String payType;
	}
	
	/**
	 * 获取支付参数的输出对象
	 */
	public static class PaymentResult extends PreparePayProcess.PaymentResult {
		// WEB页面跳转到支付网关支付
		public String payUrl;

	}
}
