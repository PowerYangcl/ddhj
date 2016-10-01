package cn.com.ddhj.model;
/**
 * 
 * 类: TCity <br>
 * 描述: 城市列表 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月1日 下午10:49:49
 */
public class TCityModel extends BaseModel {
	
	/**
	 * 城市名称
	 */
	private String name;

	/**
	 * 城市拼音
	 */
	private String pinyin;

	/**
	 * 空气质量 0 可查询 1 不可查询
	 */
	private Integer air;

	/**
	 * pm2.5查询 0 可查询 1 不可查询
	 */
	private Integer pm;

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