package cn.com.ddhj.model;

/**
 * 
 * 类: TCity <br>
 * 描述: 城市列表 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 上午9:33:08
 */
public class TCity extends BaseModel {

	/**
	 * 城市编码
	 */
	private String cityCode;

	/**
	 * 城市名称
	 */
	private String name;

	/**
	 * 城市名称拼音
	 */
	private String pinyin;

	/**
	 * 是否可查询空气质量
	 */
	private Integer air;

	/**
	 * 是否可查询pm2.5
	 */
	private Integer pm;

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public Integer getAir() {
		return air;
	}

	public void setAir(Integer air) {
		this.air = air;
	}

	public Integer getPm() {
		return pm;
	}

	public void setPm(Integer pm) {
		this.pm = pm;
	}

}