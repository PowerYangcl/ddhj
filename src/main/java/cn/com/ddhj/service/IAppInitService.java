package cn.com.ddhj.service;

import javax.servlet.ServletContext;

import com.alibaba.fastjson.JSONObject;

public interface IAppInitService {

	/**
	 * @descriptions 2001 接口 app启动接口
	 *
	 * @param isFirstLaunch
	 * @return
	 * @date 2017年8月27日 下午8:21:46
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject appInitialization(JSONObject obj , ServletContext application);
	
	
	/**
	 * @descriptions 2002	app版本接口
	 *
	 * @param obj
	 * @return
	 * @date 2017年8月27日 下午8:23:09
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject appVersionInfo(JSONObject obj , ServletContext application); 
}
