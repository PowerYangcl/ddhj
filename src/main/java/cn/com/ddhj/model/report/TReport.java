package cn.com.ddhj.model.report;

import java.math.BigDecimal;

import cn.com.ddhj.model.BaseModel;

/**
 * 
 * 类: TReport <br>
 * 描述: 环境报告 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月4日 下午7:34:03
 */
public class TReport extends BaseModel {

	private String code;

	private String housesCode;

	private String title;

	private String levelCode;

	private String pic;

	private String image;

	private Integer rang;

	private BigDecimal price;

	private String path;

	private String detail;

	private String address;

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

	public String getHousesCode() {
		return housesCode;
	}

	public void setHousesCode(String housesCode) {
		this.housesCode = housesCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
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

	public Integer getRang() {
		return rang;
	}

	public void setRang(Integer rang) {
		this.rang = rang;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}