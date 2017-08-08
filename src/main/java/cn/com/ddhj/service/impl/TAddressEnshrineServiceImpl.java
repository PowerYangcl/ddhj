package cn.com.ddhj.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.mapper.ITAddressEnshrineMapper;
import cn.com.ddhj.model.TAddressEnshrine;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.result.tuser.UserDataResult;
import cn.com.ddhj.service.ITAddressEnshrineService;
import cn.com.ddhj.service.user.ITUserService;
import cn.com.ddhj.util.Constant;

@Service("addressEnshrineService")  
public class TAddressEnshrineServiceImpl implements ITAddressEnshrineService {

	@Autowired
	private ITAddressEnshrineMapper mapper;
	
	@Autowired
	private ITUserService userService;
	
	/**
	 * @descriptions 同步客户端收藏的地址到数据库
	 * @测试地址如下：http://localhost:8080/ddhj/api.htm?apiTarget=rsync_address_enshrine&api_key=appfamilyhas&&apiInput={"lat":39.1439299,"lng":117.21081309,"level":"优","address":"天津市 河北区 民生路 52号","name":"龙亭家园","province":"天津市","city":"天津市","district":"河北区"}&api_timespan=2017-08-07+15:07:34&userToken=fc27799473fe4e828c8113a038c9d69a&api_secret=2f8f627488dbc05f822214d55561aa56
	 *
	 * @param input
	 * @param userToken
	 * @date 2017年8月7日 下午3:37:39
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject rsyncAddressEnshrine(JSONObject input, String userToken) {
		JSONObject re = new JSONObject();
		
		TAddressEnshrine e = null;
		try {
			e = input.toJavaObject(TAddressEnshrine.class);
		} catch (Exception ex) {
			ex.printStackTrace(); 
			re.put("resultCode", -1);
			re.put("resultMessage", "数据解析异常，请核实所传递数据");
			return re;
		}
		
		if(StringUtils.isAnyBlank(e.getLat() , e.getLng() , e.getLevel() , e.getAddress() , e.getName())){
			re.put("resultCode", -1);
			re.put("resultMessage", "关键字段不得为空,请核实经纬度、环境质量等级、地址和项目名称");
			return re;
		}
		String userCode = "";
		UserDataResult userResult = userService.getUser(userToken);
		if (userResult.getResultCode() == Constant.RESULT_SUCCESS) {
			TUser user = userResult.getUser();
			userCode = user.getUserCode();
		}else{
			re.put("resultCode", -1);
			re.put("resultMessage", "用户尚未登录");
			return re;
		}
		e.setUuid(UUID.randomUUID().toString().replace("-", ""));
		e.setUserCode(userCode);
		e.setCreateTime(new Date());
		
		
		int count = mapper.selectCountByName(e);
		if(count != 0){
			re.put("resultCode", 1);
			re.put("resultMessage", "同步成功");
			return re;
		}
		
		int flag = mapper.insertSelective(e);
		if(flag == 1){
			re.put("resultCode", 1);
			re.put("resultMessage", "同步成功");
		}else{
			re.put("resultCode", -1);
			re.put("resultMessage", "同步失败");
		}
		
		return re;
	}

	
	
	
	/**
	 * @descriptions 获取用户收藏地址列表
	 * @测试地址如下：http://api.sys.ecomapit.com/ddhj/api.htm?apiTarget=address_enshrine_list&api_key=appfamilyhas&userToken=fc27799473fe4e828c8113a038c9d69a&api_secret=2f8f627488dbc05f822214d55561aa56
	 *
	 * @param userToken
	 * @return
	 * @date 2017年8月7日 下午3:39:19
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject getUserAddressEnshrineList(String userToken) {
		JSONObject re = new JSONObject();
		String userCode = "";
		UserDataResult userResult = userService.getUser(userToken);
		if (userResult.getResultCode() == Constant.RESULT_SUCCESS) {
			TUser user = userResult.getUser();
			userCode = user.getUserCode();
		}else{
			re.put("resultCode", -1);
			re.put("resultMessage", "用户尚未登录");
			return re;
		}
		List<TAddressEnshrine> list = mapper.getListByUserCode(userCode);
		if(list != null && list.size() != 0){
			re.put("resultCode", 1);
			re.put("resultMessage", "获取成功");
			re.put("data", list);
		}else{
			re.put("resultCode", 1);
			re.put("resultMessage", "获取用户收藏地址列表为空");
		}
		
		return re;
	}




	/**
	 * @descriptions 根据经纬度删除一条记录
	 *
	 * @param input
	 * @param userToken
	 * @date 2017年8月7日 下午5:16:23
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject delUserAddressEnshrine(JSONObject input, String userToken) {
		JSONObject re = new JSONObject();
		String userCode = "";
		UserDataResult userResult = userService.getUser(userToken);
		if (userResult.getResultCode() == Constant.RESULT_SUCCESS) {
			TUser user = userResult.getUser();
			userCode = user.getUserCode();
		}else{
			re.put("resultCode", -1);
			re.put("resultMessage", "用户尚未登录");
			return re;
		}
		String lat = input.getString("lat");
		String lng = input.getString("lng");
		if(StringUtils.isAnyBlank(lat , lng)){
			re.put("resultCode", -1);
			re.put("resultMessage", "经纬度信息不得为空");
			return re;
		}
		
		TAddressEnshrine e = new TAddressEnshrine();
		e.setLat(lat);
		e.setLng(lng);
		e.setUserCode(userCode);
		int flag = mapper.deleteByLatlng(e);
		if(flag == 1){
			re.put("resultCode", 1);
			re.put("resultMessage", "删除成功");
		}else{
			re.put("resultCode", -1);
			re.put("resultMessage", "删除失败");
		}
		return re;
	}

}
























































