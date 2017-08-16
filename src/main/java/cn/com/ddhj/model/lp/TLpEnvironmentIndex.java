package cn.com.ddhj.model.lp;

public class TLpEnvironmentIndex {

	private String name;
	private String city;
	/**
	 * 数值
	 */
	private Double value;
	/**
	 * 等级
	 */
	private Integer level;
	/**
	 * 等级名称
	 */
	private String levelName;
	/**
	 * 排行
	 */
	private Integer sort;
	/**
	 * 排行时效
	 */
	private String sortTime;
	/**
	 * 评价
	 */
	private String evaluate;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getSortTime() {
		return sortTime;
	}

	public void setSortTime(String sortTime) {
		this.sortTime = sortTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}

}
