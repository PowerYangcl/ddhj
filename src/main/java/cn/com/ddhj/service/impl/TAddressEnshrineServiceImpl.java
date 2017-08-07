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
	
	@Override
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

	
	
	
	@Override
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
			re.put("resultCode", -1);
			re.put("resultMessage", "获取用户收藏地址列表为空");
		}
		
		return re;
	}

}
























































