package cn.com.ddhj.result.order;

import java.math.BigDecimal;
import java.util.List;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.TProductInfo;

/**
 * apiTarget:order_confirm
 * 订单确认接口返回结果 
 * @author zht
 *
 */
public class ProductOrderConfirmResult extends BaseResult {
	//配送方式
	private Integer dispatching;
	//用户碳币数量
	private BigDecimal carbonMoney = BigDecimal.ZERO;
	
	private List<TProductInfo> productListConfirm;

	public Integer getDispatching() {
		return dispatching;
	}

	public void setDispatching(Integer dispatching) {
		this.dispatching = dispatching;
	}

	public BigDecimal getCarbonMoney() {
		return carbonMoney;
	}

	public void setCarbonMoney(BigDecimal carbonMoney) {
		this.carbonMoney = carbonMoney;
	}

	public List<TProductInfo> getProductListConfirm() {
		return productListConfirm;
	}

	public void setProductListConfirm(List<TProductInfo> productListConfirm) {
		this.productListConfirm = productListConfirm;
	}
}
