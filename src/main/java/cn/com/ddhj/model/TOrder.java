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

	private Integer status;

	private String pic;
	private String image;
	private String levelName;
	private String address;
	
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

}