package cn.com.ddhj.service.apporderpay;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.ddhj.base.BaseClass;

/**
 * 创建支付订单
 * @author wz
 * @deprecated
 */
public class CreateOrderPay extends BaseClass{
	
	/**
	 * 创建支付单号相应数据
	 * @param type
	 * @param orderCodes
	 */
	@Autowired
	public String  createOrderPayInfo(String payCode,String sLockUuid,String type,String orderCodes, String sellerCode){
		
//		try {
//			String countDueMoney = "";
//			String dueMoneyBefore = "0.00";
//			BigDecimal payed_money = null;
//			List<Map<String, Object>> listClient =  new ArrayList<Map<String, Object>>();
//			
//			String createTime = DateUtil.getSysDateTime();
//			
//			
//			// 开始锁定交易的流水号和交易类型10秒
//			if (StringUtils.isNotEmpty(sLockUuid)) {
//				//查询订单主表信息(区分出大订单还是小订单)
//				if(!"".equals(orderCodesSmall) && "".equals(orderCodesBig)){
//					listClient = DbUp.upTable("oc_orderinfo").dataSqlList(
//							"select order_code,due_money,big_order_code from oc_orderinfo where order_code in (" + orderCodesSmall + ") order by create_time desc", new MDataMap());
//				}else if("".equals(orderCodesSmall) && !"".equals(orderCodesBig)){
//					listClient = DbUp.upTable("oc_orderinfo").dataSqlList(
//							"select order_code,due_money,big_order_code from oc_orderinfo where big_order_code in (" + orderCodesBig + ") order by create_time desc", new MDataMap());
//				}else if(!"".equals(orderCodesSmall) && !"".equals(orderCodesBig)){
//					listClient = DbUp.upTable("oc_orderinfo").dataSqlList(
//							"select order_code,due_money,big_order_code from oc_orderinfo where big_order_code in (" + orderCodesBig + ") or order_code in (" + orderCodesSmall + ") order by create_time desc", new MDataMap());
//				}
//				
//				for(Map<String,Object> map : listClient){
//					String bigOrderCode = String.valueOf(map.get("big_order_code"));
//					
//					String orderCode = String.valueOf(map.get("order_code"));
//					//当前金额
//					String dueMoneyNow = String.valueOf(map.get("due_money"));
//					//总金额
//					payed_money = new BigDecimal(dueMoneyBefore).add(new BigDecimal(String.valueOf(map.get("due_money"))));
//					//上一个订单的金额
//					dueMoneyBefore = String.valueOf(payed_money);
//					
//					//插入支付单号明细表
//					DbUp.upTable("oc_paydetail").dataInsert(new MDataMap("pay_code",payCode,"order_code",orderCode,
//							"due_money",dueMoneyNow,"big_order_code",bigOrderCode,"create_time",createTime,
//							"update_time",createTime,"state","0"));
//				}
//				
//				
//				countDueMoney = String.valueOf(payed_money);
//				
//				//插入支付单号主表信息
//				DbUp.upTable("oc_pay_info").dataInsert(new MDataMap("pay_code",payCode,
//						"due_money",countDueMoney,"create_time",createTime,
//						"update_time",createTime,"pay_type",type,"state","0","seller_code",sellerCode));
//			}
//			
//			return payCode;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		return null;
	}
	
}
