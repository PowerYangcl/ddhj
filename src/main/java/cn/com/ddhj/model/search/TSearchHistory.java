package cn.com.ddhj.model.search;

import cn.com.ddhj.model.BaseModel;

public class TSearchHistory extends BaseModel {
	private String userCode;
	private String keyWord;
	private Integer score;
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
}
