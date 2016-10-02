package cn.com.ddhj.result.estateInfo;

import java.util.List;

/**
 * @descriptions 地产数据|支持城市列表|响应报文封装体
 *
 * @date 2016年10月2日 下午10:48:38
 * @author Yangcl 
 * @version 1.0.1
 */
public class CityResult {
	private String resultcode;
	private String reason;
	private List<CData> result;
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
	public List<CData> getResult() {
		return result;
	}
	public void setResult(List<CData> result) {
		this.result = result;
	}
	
	
}
