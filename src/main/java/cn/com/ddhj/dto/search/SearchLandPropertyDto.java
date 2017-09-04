package cn.com.ddhj.dto.search;

import cn.com.ddhj.dto.BaseDto;

public class SearchLandPropertyDto extends BaseDto {
	private String keyWord;
	private String city;
	private Integer sortType = Integer.valueOf(0);
	private Integer sortFlag = Integer.valueOf(0);
	private Double minScore;
	private Double maxScore;
	private String base64;
	private Integer pageIndex = Integer.valueOf(0);
	
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public Integer getSortType() {
		return sortType;
	}
	public void setSortType(Integer sortType) {
		this.sortType = sortType;
	}
	public Integer getSortFlag() {
		return sortFlag;
	}
	public void setSortFlag(Integer sortFlag) {
		this.sortFlag = sortFlag;
	}
	public Double getMinScore() {
		return minScore;
	}
	public void setMinScore(Double minScore) {
		this.minScore = minScore;
	}
	public Double getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(Double maxScore) {
		this.maxScore = maxScore;
	}
	public String getBase64() {
		return base64;
	}
	public void setBase64(String base64) {
		this.base64 = base64;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	
}
