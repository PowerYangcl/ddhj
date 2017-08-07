package cn.com.ddhj.service;

import com.alibaba.fastjson.JSONObject;


public interface ITAddressEnshrineService {

	/**
	 * @descriptions 同步客户端收藏的地址到数据库
	 *
	 * @param input
	 * @param userToken
	 * @return
	 * @date 2017年8月7日 上午10:22:31
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject rsyncAddressEnshrine(JSONObject input, String userToken);  
	
	/**
	 * @descriptions 获取用户收藏地址列表
	 *
	 * @param userToke
	 * @return
	 * @date 2017年8月7日 上午10:23:28
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject getUserAddressEnshrineList(String userToke);
}
