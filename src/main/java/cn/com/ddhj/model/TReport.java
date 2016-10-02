package cn.com.ddhj.model;

import java.math.BigDecimal;

/**
 * 
 * 类: TReport <br>
 * 描述: 环境报告表 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 下午11:11:03
 */
public class TReport extends BaseModel {

	/**
	 * 编码
	 */
	private String code;

	/**
	 * 报告名称
	 */
	private String name;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 图片
	 */
	private String pic;

	/**
	 * 大图
	 */
	private String image;

	/**
	 * 等级
	 */
	private String levels;

	/**
	 * 价格
	 */
	private BigDecimal price;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}