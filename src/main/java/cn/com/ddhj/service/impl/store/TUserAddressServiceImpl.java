package cn.com.ddhj.service.impl.store;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.swing.text.html.parser.Entity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TUserAddressMapper;
import cn.com.ddhj.model.TUserAddress;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.result.tuser.UserDataResult;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.store.ITUserAddressService;
import cn.com.ddhj.service.user.ITUserService;

@Service
public class TUserAddressServiceImpl extends BaseServiceImpl<TUserAddress, TUserAddressMapper, BaseDto>
		implements ITUserAddressService {

	@Autowired
	private ITUserService userService;
	@Autowired
	private TUserAddressMapper mapper;

	/**
	 * 
	 * 方法: updateByCode <br>
	 * 
	 * @param entity
	 * @param userToken
	 * @return
	 * @see cn.com.ddhj.service.store.ITUserAddressService#updateByCode(cn.com.ddhj.model.TUserAddress,
	 *      java.lang.String)
	 */
	@Override
	public BaseResult updateByCode(TUserAddress entity, String userToken) {
		BaseResult result = new BaseResult();
		UserDataResult userResult = userService.getUser(userToken);
		if (userResult.getResultCode() == 0) {
			TUser user = userResult.getUser();
			entity.setUpdateUser(user.getUserCode());
			result = super.updateByCode(entity);
		} else {
			result.setResultCode(userResult.getResultCode());
			result.setResultMessage(userResult.getResultMessage());
		}

		return result;
	}

	
	
	/**
	 * @description: 添加用户收货地址
	 * @测试地址如下：http://localhost:8080/ddhj/api.htm?apiTarget=add_user_address&api_key=appfamilyhas&
	 * apiInput={"userCode":"U161005100033","name":"出柜斌","phone":"15800002222","provinces":"天津市河西区鸭厂镇","street":"河东区成林庄道100号","isDefault":1}
	 * @返回数据如下：
		{
		    "status": "success",
		    "msg": "地址添加成功"
		}
	 * @param entity
	 * @author Yangcl 
	 * @date 2017年7月27日 下午2:52:00 
	 * @version 1.0.0.1
	 */
	public JSONObject addUserAddress(JSONObject e_) {
		JSONObject re = new JSONObject();
		TUserAddress e = null;
		try {
			e = e_.toJavaObject(TUserAddress.class);
		} catch (Exception ex) {
			re.put("status", "error");
			re.put("msg", "地址信息解析错误");
			return re;
		}
		if(e == null){
			re.put("status", "error");
			re.put("msg", "地址信息解析错误，有效数据为空");
			return re;
		}
		
		if(StringUtils.isAnyBlank(e.getUserCode() , e.getName() , e.getPhone() , e.getProvinces() , e.getStreet() , e.getIsDefault()==null ? "" :e.getIsDefault().toString())){
			re.put("status", "error");
			re.put("msg", "地址关键信息不得为空");
			return re;
		}
		e.setUuid(UUID.randomUUID().toString().replace("-", "")); 
		e.setCode(WebHelper.getInstance().getUniqueCode("AR")); 
		e.setCreateUser(e.getUserCode());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String cdate = sdf.format(new Date());
		e.setCreateTime(cdate);
		e.setUpdateUser(e.getUserCode());
		e.setUpdateTime(cdate);
		
		int flag = mapper.insertSelective(e);
		if(flag == 1){
			re.put("status", "success");
			re.put("msg", "地址添加成功");
		}else{
			re.put("status", "error");
			re.put("msg", "添加收货地址失败");
		}
		return re;
	}



	/**
	 * @description: 删除收货地址 
	 * @测试地址如下：http://localhost:8080/ddhj/api.htm?apiTarget=delete_user_address&api_key=appfamilyhas&apiInput={"userCode":"U161005100033","addrCode":"AR170727100004"}
	 * @返回数据如下：
		{
		    "status": "success",
		    "msg": "用户收货地址删除成功"
		}
	 * 
	 * @param obj
	 * @author Yangcl 
	 * @date 2017年7月27日 下午3:28:30 
	 * @version 1.0.0.1
	 */
	public JSONObject deleteUserAddress(JSONObject obj) {
		JSONObject re = new JSONObject();
		String userCode = obj.getString("userCode"); 
		String addrCode = obj.getString("addrCode"); 
		if(StringUtils.isAnyBlank(userCode , addrCode)){
			re.put("status", "error");
			re.put("msg", "关键参数不得为空");
			return re;
		}
		TUserAddress e = new TUserAddress();
		e.setUserCode(userCode);
		e.setCode(addrCode);
		int flag = mapper.deleteUserAddress(e);
		if(flag == 1){
			re.put("status", "success");
			re.put("msg", "用户收货地址删除成功");
		}else{
			re.put("status", "error");
			re.put("msg", "用户收货地址删除失败");
		}
		
		return re;
	}

}

















