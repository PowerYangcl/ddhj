package cn.com.ddhj.base;

/**
 * 
 * 类: BaseResult <br>
 * 描述: 响应结果基类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月2日 上午11:54:18
 */
public class BaseResult {

	private Integer resultCode = 1;
	private String resultMessage = "";

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

}
