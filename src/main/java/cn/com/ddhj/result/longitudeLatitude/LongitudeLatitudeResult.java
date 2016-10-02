package cn.com.ddhj.result.longitudeLatitude;


/**
 * @descriptions 经纬度地址解析|响应报文封装体
 *
 * @date 2016年10月2日 下午7:58:43
 * @author Yangcl 
 * @version 1.0.1
 */
public class LongitudeLatitudeResult {
	private String resultcode;
	private String reason;
	private Result result;
	private int error_code;

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public String getResultcode() {
		return this.resultcode;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return this.reason;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Result getResult() {
		return this.result;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	public int getError_code() {
		return this.error_code;
	}

}
