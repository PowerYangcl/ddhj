package cn.com.ddhj.model;

import java.math.BigDecimal;

/**
 * 
 * 类: TOrder <br>
 * 描述: 订单表 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月7日 下午12:56:14
 */
public class TOrder extends BaseModel {

	private String code;

	private String reportCode;

	private BigDecimal payPrice;

	private String payCode;

	private String invoiceTitle;

	private Integer status;

	private String title;

	private String pic;

	private String image;

	private String detail;

	private String path;

	private String housesCode;

	private String levelCode;

	private String levelName;

	private String address;

	/**
	 * ==============新加字段 start ===============
	 */

	/**
	 * 应付款
	 */
	private BigDecimal checkPayMoney;

	/**
	 * 炭币
	 */
	private BigDecimal carbonMoney;

	/**
	 * 优惠券
	 */
	private String couponCodes;
	/**
	 * 购买人编码
	 */
	private String buyerCode;
	/**
	 * 购买人手机号
	 */
	private String buyerMobile;
	/**
	 * 微信支付时客户端IP地址
	 */
	private String payIp;
	/**
	 * 设备的唯一标识
	 */
	private String uniqid;

	/**
	 * app版本信息
	 */
	private String appVision;

	/**
	 * 手机操作系统
	 */
	private String os;

	/**
	 * 发票类型
	 */
	private String invoiceType;

	/**
	 * 发票内容
	 */
	private String invoiceContent;

	/**
	 * ==============新加字段 end ===============
	 */

	public String getHousesCode() {
		return housesCode;
	}

	public BigDecimal getCheckPayMoney() {
		return checkPayMoney;
	}

	public void setCheckPayMoney(BigDecimal checkPayMoney) {
		this.checkPayMoney = checkPayMoney;
	}

	public BigDecimal getCarbonMoney() {
		return carbonMoney;
	}

	public void setCarbonMoney(BigDecimal carbonMoney) {
		this.carbonMoney = carbonMoney;
	}

	public String getCouponCodes() {
		return couponCodes;
	}

	public void setCouponCodes(String couponCodes) {
		this.couponCodes = couponCodes;
	}

	public String getBuyerCode() {
		return buyerCode;
	}

	public void setBuyerCode(String buyerCode) {
		this.buyerCode = buyerCode;
	}

	public String getBuyerMobile() {
		return buyerMobile;
	}

	public void setBuyerMobile(String buyerMobile) {
		this.buyerMobile = buyerMobile;
	}

	public String getPayIp() {
		return payIp;
	}

	public void setPayIp(String payIp) {
		this.payIp = payIp;
	}

	public String getUniqid() {
		return uniqid;
	}

	public void setUniqid(String uniqid) {
		this.uniqid = uniqid;
	}

	public String getAppVision() {
		return appVision;
	}

	public void setAppVision(String appVision) {
		this.appVision = appVision;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getInvoiceContent() {
		return invoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	public void setHousesCode(String housesCode) {
		this.housesCode = housesCode;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getReportCode() {
		return reportCode;
	}

	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}

	public BigDecimal getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

}