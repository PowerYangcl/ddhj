package cn.com.ddhj.result.carbon;

import cn.com.ddhj.base.BaseResult;

/**
 * 
 * 类: CarbonRechargeResult <br>
 * 描述: 用户碳币充值结果<br>
 * 作者: 海涛<br>
 * 时间: 2017年3月17日
 */
public class CarbonRechargeResult extends BaseResult {
	private String orderCode;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
}
