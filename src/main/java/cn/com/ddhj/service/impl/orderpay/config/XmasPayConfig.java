package cn.com.ddhj.service.impl.orderpay.config;

/**
 * 支付模块配置获取类
 * 
 * @author zhaojunling
 */
public class XmasPayConfig{

//	private static class Config extends BaseClass{}
//	
//	private static Config config = new Config();
//	
//	/**
//	 * 获取支付网关的请求地址
//	 * @return
//	 */
	public static String getPayGateURL(){
////		return config.bConfig("xmaspay.paygate_url");
//		return "http://test.pay.lacues.cn/createorder/";
		return "https://pay.huijiayou.cn/createorder/";
	}
//	
//	/**
//	 * 获取支付网关的商户编号
//	 * @return
//	 */
	public static String getPayGateMid(){
////		return config.bConfig("xmaspay.paygate_mid");
//		return "1000447";
		return "1000448";
	}
//	
//	/**
//	 * 获取支付网关的商户密钥
//	 * @return
//	 */
	public static String getPayGatePass(){
////		return config.bConfig("xmaspay.paygate_pass");
//		return "ffffff";
		return "t36g9xxpshl3bfha6f24a7p0ut3ilvw6";
	}
//	
//	/**
//	 * 获取提供给支付网关的支付通知回调配置
//	 * @return
//	 */
	public static String getPayGateReturnUrl() {
////		return config.bConfig("xmaspay.paygate_return_url");
//		return "http://qhbeta-cfamily.qhw.yshqi.com/cfamily/manage/paygate_notify.ftl";
//		return "http://api.sys.ecomapit.com/payNotify.htm";
//		return "http://stockwyz.xicp.net/ddhj/payNotify.htm";
		return "http://api.sys.ecomapit.com/ddhj/payNotify.htm";
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
		return "http://api.sys.ecomapit.com/ddhj/payNotifyForRecharge.htm";
	}
//	
//	/**
//	 * 默认返回为支付网关的跳转地址
//	 * @return
//	 */
	public static String getPayGateDefaultReURL(){
//////		return config.bConfig("xmaspay.paygate_reurl");
//		return "http://ddhj.eplans.cn/orderlist.html";
		return "http://www.ecomapit.com/orderlist.html";
	}
//	
//	/**
//	 * 默认微信商城的跳转地址
//	 * @return
//	 */
//	public static String getPayGateWapDefaultReURL(){
////		return config.bConfig("xmaspay.paygate_reurl_wap");
//		return "";
//	}
//	
//	/**
//	 * 查询连连支付平台的商户编号
//	 * @return
//	 */
	public static String getApplePayOidPartner(){
////		return config.bConfig("applepay.oid_partner");
		return "";
	}
//	
//	/**
//	 * 苹果支付的merchant_id
//	 * @return
//	 */
	public static String getApplePayApMerchantId(){
////		return config.bConfig("applepay.ap_merchant_id");
		return "";
	}
//	
//	/**
//	 * MD5签名使用的key
//	 * @return
//	 */
	public static String getApplePayKeyMd5(){
////		return config.bConfig("applepay.key_md5");
		return "";
	}
//	
//	/**
//	 * 连连支付异步通知地址
//	 * @return
//	 */
	public static String getApplePayNotifyUrl(){
////		return config.bConfig("applepay.notify_url_v2");
		return "";
	}
}
