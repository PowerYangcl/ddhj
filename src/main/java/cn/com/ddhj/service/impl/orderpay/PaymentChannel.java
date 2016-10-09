package cn.com.ddhj.service.impl.orderpay;

/**
 * 支付渠道
 * 
 * @author zhaojunling
 */
public enum PaymentChannel {
	
	/**
	 * APP原生支付
	 */
	APP,
	/**
	 * PC网站渠道支付
	 */
	WEB,
	/**
	 * WAP网站渠道支付
	 */
	WAP,
	/**
	 * 微信JSAPI支付
	 */
	JSAPI,
	/**
	 * 微信二维码支付
	 */
	BARCODE
}
