package cn.com.ddhj.result.pollution;

import java.util.List;

/**
 * @descriptions 城市污染查询|支持城市列表||响应报文封装体
 *
 * @date 2016年10月3日 下午5:01:24
 * @author Yangcl 
 * @version 1.0.1
 */
public class PollutionSupportCity {
	private String resultcode;
	private String reason;
	private List<PscData> result;
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
	public List<PscData> getResult() {
		return result;
	}
	public void setResult(List<PscData> result) {
		this.result = result;
	}
	public int getError_code() {
		return error_code;
	}
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}
	
	
}
