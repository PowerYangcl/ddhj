package cn.com.ddhj.service.impl.orderpay.config;

import cn.com.ddhj.helper.PropHelper;

/**
 * 支付模块配置获取类
 * 
 * @author zhaojunling
 */
public class XmasPayConfig {
	/**
	 * 获取支付网关的请求地址
	 * @return
	 */
	public static String getPayGateURL(){
		return PropHelper.getValue("payGateUrl");
	}
	
	/**
	 * 获取支付网关的商户编号
	 * @return
	 */
	public static String getPayGateMid(){
		return PropHelper.getValue("mid");
	}

	/**
	 * 获取支付网关的商户密钥
	 * @return
	 */
	public static String getPayGatePass() {
		return PropHelper.getValue("mpassword");
	}
	
	/**
	 * 获取提供给支付网关的支付通知回调配置
	 * @return
	 */
	public static String getPayGateReturnUrl() {
		return PropHelper.getValue("rtnUrl");
	}
	
	/**
	 * 
	 * 方法: getPayGateReturnUrlForRecharge<br>
	 * 描述: 配置用户充值后,网关回调通知接口<br>
	 * 作者: 海涛<br>
	 * 时间: 2017年3月17日 下午7:46:14
	 * 
	 * @param 
	 * @return
	 */
	public static String getPayGateReturnUrlForRecharge() {
		return PropHelper.getValue("rtnUrlForCharge");
	}
	
	/**
	 * 默认返回为支付网关的跳转地址
	 * @return
	 */
	public static String getPayGateDefaultReURL(){
		return PropHelper.getValue("defaulRtnUrl");
	}
	
	/**
	 * 默认返回为支付网关的跳转地址
	 * @return
	 */
	public static String getPayGateDefaultReURLForRecharge(){
		return PropHelper.getValue("defaulRtnUrlForCharge");
	}

	
	/**
	 * 查询连连支付平台的商户编号
	 * @return
	 */
	public static String getApplePayOidPartner() {
		return "";
	}

	/**
	 * 苹果支付的merchant_id
	 * @return
	 */
	public static String getApplePayApMerchantId() {
		return "";
	}

	/**
	 * MD5签名使用的key
	 * @return
	 */
	public static String getApplePayKeyMd5(){
		return "";
	}
	
	/**
	 * 连连支付异步通知地址
	 * @return
	 */
	public static String getApplePayNotifyUrl(){
		return "";
	}
}
