package cn.com.ddhj.result.llConvertion;


/**
 * @descriptions 地图坐标服务|经纬度转换|响应报文封装体
 *
 * @date 2016年10月2日 下午9:07:37
 * @author Yangcl 
 * @version 1.0.1
 */
public class ConversionResult {
	private String resultcode;
	private String reason;
	private CResult result;
	private int error_code;

	
	public String getResultcode() {
		return resultcode;
	}
	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}
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
	public CResult getResult() {
		return result;
	}
	public void setResult(CResult result) {
		this.result = result;
	}
	
}	
