package cn.com.ddhj.model.trade;

import java.math.BigDecimal;

import cn.com.ddhj.model.BaseModel;

/**
 * 
 * 类: TTradeOrder 
 * 描述: 碳交易订单
 * 作者: zht
 * 时间: 2017年3月8日 下午9:43:07 
 *
 */
public class TTradeOrder extends BaseModel {
	/**
	 * 订单编号
	 */
	private String orderCode;
	
	/**
	 * 交易标的编码
	 */
	private String objectCode;
	
	/**
	 * 买或卖.B买.S卖
	 */
	private String buySell;
	
	/**
	 * 客户编号
	 */
	private String userCode;
	
	/**
	 * 委托单价
	 */
	private BigDecimal price;
	
	/**
	 * 委托数量
	 */
	private Integer amount;
	
	/**
	 * 0.已委托,未成交  1.全部已成交  2.部分成交  3.已撤单
	 */
	private Integer status;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getObjectCode() {
		return objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBuySell() {
		return buySell;
	}

	public void setBuySell(String buySell) {
		this.buySell = buySell;
	}
	
}
