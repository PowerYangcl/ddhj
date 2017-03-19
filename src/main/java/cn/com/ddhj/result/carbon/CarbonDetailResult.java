package cn.com.ddhj.result.carbon;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.user.TUserCarbonOperation;

/**
 * 
 * 类: CarbonDetailResult <br>
 * 描述: 碳币详细信息结果集 <br>
 * 作者: zhy<br>
 * 时间: 2017年2月10日 下午5:28:05
 */
public class CarbonDetailResult extends BaseResult {

	/**
	 * 步行碳币
	 */
	private BigDecimal walkCarbon = BigDecimal.ZERO;
	/**
	 * 新能源里程碳币
	 */
	private BigDecimal newEnergyCarbon = BigDecimal.ZERO;
	/**
	 * 购买碳币
	 */
	private BigDecimal buyCarbon = BigDecimal.ZERO;
	/**
	 * 兑换环境质量报告
	 */
	private BigDecimal exchangeReport = BigDecimal.ZERO;
	/**
	 * 兑换奖品
	 */
	private BigDecimal exchangeGift = BigDecimal.ZERO;
	/**
	 * 炒碳支出
	 */
	private BigDecimal spendForCarbonDeal = BigDecimal.ZERO;
	/**
	 * 炒碳支出
	 */
	private BigDecimal inComeFormCarbonDeal = BigDecimal.ZERO;
	

	/**
	 * 碳币收入
	 */
	private List<TUserCarbonOperation> incomeCarbon = new ArrayList<TUserCarbonOperation>();

	/**
	 * 碳币支出
	 */
	private List<TUserCarbonOperation> expendCarbon = new ArrayList<TUserCarbonOperation>();

	public BigDecimal getWalkCarbon() {
		return walkCarbon;
	}

	public void setWalkCarbon(BigDecimal walkCarbon) {
		this.walkCarbon = walkCarbon;
	}

	public BigDecimal getNewEnergyCarbon() {
		return newEnergyCarbon;
	}

	public void setNewEnergyCarbon(BigDecimal newEnergyCarbon) {
		this.newEnergyCarbon = newEnergyCarbon;
	}

	public BigDecimal getBuyCarbon() {
		return buyCarbon;
	}

	public void setBuyCarbon(BigDecimal buyCarbon) {
		this.buyCarbon = buyCarbon;
	}

	public BigDecimal getExchangeReport() {
		return exchangeReport;
	}

	public void setExchangeReport(BigDecimal exchangeReport) {
		this.exchangeReport = exchangeReport;
	}

	public BigDecimal getExchangeGift() {
		return exchangeGift;
	}

	public void setExchangeGift(BigDecimal exchangeGift) {
		this.exchangeGift = exchangeGift;
	}

	public BigDecimal getSpendForCarbonDeal() {
		return spendForCarbonDeal;
	}

	public void setSpendForCarbonDeal(BigDecimal spendForCarbonDeal) {
		this.spendForCarbonDeal = spendForCarbonDeal;
	}

	public BigDecimal getInComeFormCarbonDeal() {
		return inComeFormCarbonDeal;
	}

	public void setInComeFormCarbonDeal(BigDecimal inComeFormCarbonDeal) {
		this.inComeFormCarbonDeal = inComeFormCarbonDeal;
	}

	public List<TUserCarbonOperation> getIncomeCarbon() {
		return incomeCarbon;
	}

	public void setIncomeCarbon(List<TUserCarbonOperation> incomeCarbon) {
		this.incomeCarbon = incomeCarbon;
	}

	public List<TUserCarbonOperation> getExpendCarbon() {
		return expendCarbon;
	}

	public void setExpendCarbon(List<TUserCarbonOperation> expendCarbon) {
		this.expendCarbon = expendCarbon;
	}

}
