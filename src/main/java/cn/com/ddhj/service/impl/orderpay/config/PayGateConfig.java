package cn.com.ddhj.service.impl.orderpay.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 网关接口配置
 */
public class PayGateConfig {

	private static Map<Type,Integer> payGateValMap = new HashMap<Type, Integer>();
	private static Map<Type,Integer> payGateTypeValMap = new HashMap<Type, Integer>();
	
	private static Map<Integer,Type> payGateEnumMap = new HashMap<Integer,Type>();
	
	public static enum Type {
		/** 支付宝WEB */
		ALIPAY_WEB,
		/** 支付宝WAP（H5） */
		ALIPAY_WAP,
		/** 支付宝移动支付（APP） */
		ALIPAY_APP,
		/** 支付宝H5版移动支付（H5） */
		ALIPAY_H5,
		/** 微信原生（二维码） */
		WECHAT_BARCODE,
		/** 微信WAP支付 */
		WECHAT_WAP,
		/** 微信JSAPI（微信客户端内） */
		WECHAT_JSAPI,
		/** 微信APP支付 （自身的APP内） */
		WECHAT_APP
	}
	
	static {
		payGateValMap.put(Type.ALIPAY_WEB, 65);
		payGateTypeValMap.put(Type.ALIPAY_WEB, 1);
		payGateEnumMap.put(65, Type.ALIPAY_WEB);
		
		// 以下类型通过支付宝WEB间接支持
		payGateEnumMap.put(77, Type.ALIPAY_WEB);  // 银联
		payGateEnumMap.put(73, Type.ALIPAY_WEB); // 财付通
		payGateEnumMap.put(1, Type.ALIPAY_WEB); // 招商银行
		payGateEnumMap.put(2, Type.ALIPAY_WEB); // 建设银行
		payGateEnumMap.put(3, Type.ALIPAY_WEB); // 工商银行
		payGateEnumMap.put(42, Type.ALIPAY_WEB); // 兴业银行
		payGateEnumMap.put(47, Type.ALIPAY_WEB); // 交通银行
		payGateEnumMap.put(48, Type.ALIPAY_WEB); // 光大银行
		payGateEnumMap.put(49, Type.ALIPAY_WEB); // 北京农村商业银行
		payGateEnumMap.put(69, Type.ALIPAY_WEB); // 中国银行
		payGateEnumMap.put(86, Type.ALIPAY_WEB); // 农业银行
		payGateEnumMap.put(70, Type.ALIPAY_WEB); // 中信银行
		payGateEnumMap.put(94, Type.ALIPAY_WEB); // 平安银行
		payGateEnumMap.put(901, Type.ALIPAY_WEB); // 民生银行
		// 支付宝间接支持
		
		payGateValMap.put(Type.ALIPAY_WAP, 651);
		payGateTypeValMap.put(Type.ALIPAY_WAP, 2);
		payGateEnumMap.put(651, Type.ALIPAY_WAP);
		
		payGateValMap.put(Type.ALIPAY_APP, 653);
		payGateTypeValMap.put(Type.ALIPAY_APP, 4);
		payGateEnumMap.put(653, Type.ALIPAY_APP);
		
		payGateValMap.put(Type.ALIPAY_H5, 654);
		payGateTypeValMap.put(Type.ALIPAY_H5, 1);
		payGateEnumMap.put(654, Type.ALIPAY_H5);
		
		payGateValMap.put(Type.WECHAT_BARCODE, 76);
		payGateTypeValMap.put(Type.WECHAT_BARCODE, 3);
		payGateEnumMap.put(76, Type.WECHAT_BARCODE);
		
		payGateValMap.put(Type.WECHAT_WAP, 761);
		payGateTypeValMap.put(Type.WECHAT_WAP, 0);
		payGateEnumMap.put(761, Type.WECHAT_WAP);
		
		payGateValMap.put(Type.WECHAT_JSAPI, 762);
		payGateTypeValMap.put(Type.WECHAT_JSAPI, 3);
		payGateEnumMap.put(762, Type.WECHAT_JSAPI);
		
		payGateValMap.put(Type.WECHAT_APP, 763);
		payGateTypeValMap.put(Type.WECHAT_APP, 3);
		payGateEnumMap.put(763, Type.WECHAT_APP);
	}
	
	/**
	 * 获取网关的支付方式
	 * @param type
	 * @return
	 */
	public static int getPayGateVal(Type type){
		Integer v = payGateValMap.get(type);
		return v == null ? 0 : v;
	}
	
	/**
	 * 获取网关的支付接口类型
	 * @param type
	 * @return
	 */
	public static int getPayGateTypeVal(Type type){
		Integer v = payGateTypeValMap.get(type);
		return v == null ? 0 : v;
	}
	
	/**
	 * 获取网关的支付方式枚举
	 * @param type
	 * @return
	 */
	public static Type getPayGateEnum(int gate){
		return payGateEnumMap.get(gate);
	}
	
}
