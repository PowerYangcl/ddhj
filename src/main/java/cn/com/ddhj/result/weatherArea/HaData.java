package cn.com.ddhj.result.weatherArea;

import java.util.List;

public class HaData {
	private String startTime;
	private String endTime;
	private List<Integer> series ;
	private int count;
	private String reqTime;
	
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public List<Integer> getSeries() {
		return series;
	}
	public void setSeries(List<Integer> series) {
		this.series = series;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getReqTime() {
		return reqTime;
	}
	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}
}
