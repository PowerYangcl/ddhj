package cn.com.ddhj.result.weatherArea;

import java.util.List;

import cn.com.ddhj.model.WeatherAreaSupport;

public class WeatherAreaSupportResult {
	private String reason;
	private List<WeatherAreaSupport> result;
	private int error_code;
	
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public List<WeatherAreaSupport> getResult() {
		return result;
	}
	public void setResult(List<WeatherAreaSupport> result) {
		this.result = result;
	}
	public int getError_code() {
		return error_code;
	}
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}
	
	
}
