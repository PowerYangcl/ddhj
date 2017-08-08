package cn.com.ddhj.model.trade;

import java.math.BigDecimal;

import cn.com.ddhj.model.BaseModel;

/**
 * 
 * 类: TTradeDeal <br>
 * 描述: 交易行情<br>
 * 作者: zht<br>
 * 时间: 2017年3月12日
 */
public class TTradeDeal extends BaseModel {

	private String cityId;

    private String cityName;

    private String dealDate;

    private BigDecimal openPrice;

    private BigDecimal highPrice;

    private BigDecimal lowPrice;

    private BigDecimal closePrice;

    /**
     * 当日成交价
     */
    private BigDecimal dealPrice;

    private BigDecimal dealAmount;

    private BigDecimal dealNum;
    
    /**
     * 当日成交价较前一日涨跌幅
     */
    private BigDecimal dealUpDownRatio = BigDecimal.ZERO;
    
    /**
     * 当日市场均价
     */
    private BigDecimal marketAvgPrice = BigDecimal.ZERO;
    
    /**
     * 当日市场均价较前一日涨跌幅
     */
    private BigDecimal marketAvgPriceUpDownRatio = BigDecimal.ZERO;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

	public String getDealDate() {
		return dealDate;
	}

	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}

	public BigDecimal getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
	}

	public BigDecimal getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(BigDecimal highPrice) {
		this.highPrice = highPrice;
	}

	public BigDecimal getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
	}

	public BigDecimal getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(BigDecimal closePrice) {
		this.closePrice = closePrice;
	}

	public BigDecimal getDealPrice() {
		return dealPrice;
	}

	public void setDealPrice(BigDecimal dealPrice) {
		this.dealPrice = dealPrice;
	}

	public BigDecimal getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(BigDecimal dealAmount) {
		this.dealAmount = dealAmount;
	}

	public BigDecimal getDealNum() {
		return dealNum;
	}

	public void setDealNum(BigDecimal dealNum) {
		this.dealNum = dealNum;
	}

	public BigDecimal getMarketAvgPrice() {
		return marketAvgPrice;
	}

	public void setMarketAvgPrice(BigDecimal marketAvgPrice) {
		this.marketAvgPrice = marketAvgPrice;
	}

	public BigDecimal getDealUpDownRatio() {
		return dealUpDownRatio;
	}

	public void setDealUpDownRatio(BigDecimal dealUpDownRatio) {
		this.dealUpDownRatio = dealUpDownRatio;
	}

	public BigDecimal getMarketAvgPriceUpDownRatio() {
		return marketAvgPriceUpDownRatio;
	}

	public void setMarketAvgPriceUpDownRatio(BigDecimal marketAvgPriceUpDownRatio) {
		this.marketAvgPriceUpDownRatio = marketAvgPriceUpDownRatio;
	}
}