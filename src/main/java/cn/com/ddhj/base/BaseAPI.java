package cn.com.ddhj.base;

/**
 * 
 * 类: BaseAPI <br>
 * 描述: 接口参数类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月5日 下午7:12:04
 */
public class BaseAPI {

	/**
	 * 系统分配的ApiKey
	 */
	private String apiKey;
	/**
	 * 调用接口名称
	 */
	private String apiTarget;
	/**
	 * 应用输入参数的JSON格式
	 */
	private String apiInput;

	private String userToken;

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiTarget() {
		return apiTarget;
	}

	public void setApiTarget(String apiTarget) {
		this.apiTarget = apiTarget;
	}

	public String getApiInput() {
		return apiInput;
	}

	public void setApiInput(String apiInput) {
		this.apiInput = apiInput;
	}

}
