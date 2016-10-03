package cn.com.ddhj.result.pollution;

import java.util.List;

/**
 * @descriptions 城市污染查询|城市污染源列表||响应报文封装体
 *
 * @date 2016年10月3日 下午7:47:53
 * @author Yangcl 
 * @version 1.0.1
 */
public class PollutionDistributionResult {
	private String reason;
	private int error_code;
	private List<PdData> result;
	
	
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
	public List<PdData> getResult() {
		return result;
	}
	public void setResult(List<PdData> result) {
		this.result = result;
	}
	
}
