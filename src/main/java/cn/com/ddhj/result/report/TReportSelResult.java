package cn.com.ddhj.result.report;

import java.math.BigDecimal;
import java.util.List;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.report.TReport;

/**
 * 
 * 类: TReportSelResult <br>
 * 描述: 查询报告详细信息 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月4日 下午9:53:40
 */
public class TReportSelResult extends BaseResult {

	private String code;
	private String name;
	private String pic;
	private String image;
	private String detail;
	private String address;
	private Integer isFollow;
	private BigDecimal price;
	private List<TReport> levelList;

	public Integer getIsFollow() {
		return isFollow;
	}

	public void setIsFollow(Integer isFollow) {
		this.isFollow = isFollow;
	}

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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<TReport> getLevelList() {
		return levelList;
	}

	public void setLevelList(List<TReport> levelList) {
		this.levelList = levelList;
	}

}
