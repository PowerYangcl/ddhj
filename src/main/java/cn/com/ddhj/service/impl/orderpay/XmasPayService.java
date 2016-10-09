package cn.com.ddhj.service.impl.orderpay;

import java.util.List;
import java.util.Map;

public class XmasPayService {

	/**
	 * 获取大订单信息
	 * @param bigOrderInfo
	 * @return
	 */
	public Map getOrderInfoSupper(String bigOrderCode){
		//return DbUp.upTable("oc_orderinfo_upper").one("big_order_code",bigOrderCode);
		return null;
	}
	
	/**
	 * 更新表oc_orderinfo_upper的已支付金额、支付类型、更新时间
	 * @param orderInfoSupper
	 */
	public void updateOrderSupperPayedMoney(Map orderInfoSupper){
		//DbUp.upTable("oc_orderinfo_upper").dataUpdate(orderInfoSupper, "payed_money,pay_type,update_time", "big_order_code");
		return ;
	}
	
	/**
	 * 更新表oc_orderinfo的已支付金额
	 * @param orderInfoSupper
	 */
	public void updateOrderInfoPayedMoney(Map mDataMap){
		//DbUp.upTable("oc_orderinfo").dataUpdate(mDataMap, "payed_money,update_time", "order_code");
		return ;
	}
	
	/**
	 * 获取小订单信息列表
	 * @param bigOrderCode
	 * @return
	 */
	public List<Map> getOrderInfoList(String bigOrderCode){
//		return DbUp.upTable("oc_orderinfo").queryByWhere("big_order_code",bigOrderCode);
		return null;
	}
	
	/**
	 * 查询订单的收货地址
	 * @param orderCode
	 * @return
	 */
	public Map getOrderAddress(String orderCode){
//		return DbUp.upTable("oc_orderadress").one("order_code",orderCode);
		return null;
	}
	
	/**
	 * 获取订单的支付信息
	 * @param bigOrderCode 大订单号
	 */
	public Map getOrderInfoSupperPayment(String bigOrderCode){
//		return DbUp.upTable("oc_orderinfo_upper_payment").one("big_order_code",bigOrderCode);
		return null;
	}
	
	/**
	 * 获取大订单的支付信息
	 * @param bigOrderCode 大订单号
	 * @param payType 支付类型
	 */
	public Map getOrderInfoUpperPayment(String bigOrderCode, String payType){
//		return DbUp.upTable("oc_orderinfo_upper_payment").one("big_order_code",bigOrderCode,"pay_type",payType);
		return null;
	}
	
	/**
	 * 保存订单的支付信息
	 * @param mDataMap 大订单信息
	 * @return
	 */
	public Map saveOrderInfoUpperPayment(Map mDataMap){
//		DbUp.upTable("oc_orderinfo_upper_payment").dataInsert(mDataMap);
//		return mDataMap;
		return null;
	}
	
	/**
	 * 保存支付网关的支付信息
	 * @param mDataMap
	 */
	public void savePayGatePayment(Map mDataMap){
//		MDataMap whereMDataMap = new MDataMap();
//		whereMDataMap.put("c_order", mDataMap.get("c_order"));
//		List<MDataMap> orderList = DbUp.upTable("oc_payment_paygate").queryAll("zid", "", "", whereMDataMap);
//		if(orderList.isEmpty()){
//			DbUp.upTable("oc_payment_paygate").dataInsert(mDataMap);
//		}
		return ;
	}
	
	/**
	 * 保存苹果支付的支付信息
	 * @param mDataMap
	 */
	public void saveApplePayment(Map mDataMap){
//		MDataMap whereMDataMap = new MDataMap();
//		whereMDataMap.put("no_order", mDataMap.get("no_order"));
//		List<MDataMap> orderList = DbUp.upTable("oc_payment_applepay").queryAll("zid", "", "", whereMDataMap);
//		if(orderList.isEmpty()){
//			DbUp.upTable("oc_payment_applepay").dataInsert(mDataMap);
//		}
		return ;
	}
	
	// 支付宝交易信息
	public Map saveOrderPayment(Map payment){
//		DbUp.upTable("oc_payment").dataInsert(payment);
//		return payment;
		return null;
	}
	
	// 微信交易信息
	public Map saveOrderPaymentWechat(Map payment){
//		DbUp.upTable("oc_payment_wechatNew").dataInsert(payment);
//		return payment;
		return null;
	}
	
	/**
	 * 查询小订单的支付信息
	 * @param orderCode
	 * @param payType
	 * @return
	 */
	public Map getOrderPay(String orderCode, String payType){
//		return DbUp.upTable("oc_order_pay").one("order_code",orderCode,"pay_type",payType);
		return null;
		
	}
	
	/**
	 * 保存小订单支付信息
	 * @param mDataMap
	 */
	public void saveOrderPay(Map mDataMap){
//		DbUp.upTable("oc_order_pay").dataInsert(mDataMap);
		return ;
	}
	
	/**
	 * 查询用户信息
	 * @param memberCode
	 * @return
	 */
	public Map getMemberInfo(String memberCode){
//		return DbUp.upTable("mc_member_info").one("member_code", memberCode);
		return null;
	}
	
	/**
	 * 保存支付处理日志
	 * @param mDataMap
	 */
	public void saveXmasPayLog(Map mDataMap){
//		DbUp.upTable("lc_payprocess_log").dataInsert(mDataMap);
		return ;
	}
	
}
