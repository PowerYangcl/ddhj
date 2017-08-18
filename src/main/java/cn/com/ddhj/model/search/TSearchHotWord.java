package cn.com.ddhj.model.search;

import cn.com.ddhj.model.BaseModel;

public class TSearchHotWord extends BaseModel {
	private String keyWord;
	private Integer score;
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
