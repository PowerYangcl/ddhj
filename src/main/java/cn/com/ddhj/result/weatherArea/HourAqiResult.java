package cn.com.ddhj.result.weatherArea;


/**
 * @descriptions 
 *
 * @date 2016年10月3日 下午11:49:35
 * @author Yangcl 
 * @version 1.0.1
 */
public class HourAqiResult {
	private String reason;
	private int error_code;
	private HaData result;
	

	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getError_code() {
		return error_code;
	}
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}
	public HaData getResult() {
		return result;
	}
	public void setResult(HaData result) {
		this.result = result;
	}
	
	
}
