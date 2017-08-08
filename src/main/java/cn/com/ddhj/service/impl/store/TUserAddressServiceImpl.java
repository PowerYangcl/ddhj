package cn.com.ddhj.service.impl.store;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.dto.store.TUserAddressUpdateDto;
import cn.com.ddhj.dto.user.TUserAddressDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TUserAddressMapper;
import cn.com.ddhj.model.TUserAddress;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.result.PageResult;
import cn.com.ddhj.result.tuser.UserDataResult;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.store.ITUserAddressService;
import cn.com.ddhj.service.user.ITUserService;
import cn.com.ddhj.util.Constant;
import cn.com.ddhj.util.DateUtil;

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
	public BaseResult updateByCode(TUserAddressUpdateDto entity, String userToken) {
		BaseResult result = new BaseResult();
		UserDataResult userResult = userService.getUser(userToken);
		if (userResult.getResultCode() == Constant.RESULT_SUCCESS) {
			TUser user = userResult.getUser();
			entity.setUpdateUser(user.getUserCode());
			entity.setCode(entity.getAddressID());
			entity.setUpdateTime(DateUtil.getSysDateTime());
			if(entity.getIsDefault() == 1){
				mapper.updateAddressByUsercode(user.getUserCode()); 
			}
			int flag = mapper.updateByAddressID(entity);
			if(flag == 1){
				result.setResultCode(Constant.RESULT_SUCCESS);
				result.setResultMessage("更新成功"); 
			}else{
				result.setResultCode(Constant.RESULT_ERROR);
				result.setResultMessage("更新失败"); 
			}
		} else {
			result.setResultCode(userResult.getResultCode());
			result.setResultMessage(userResult.getResultMessage());
		}

		return result;
	}

	
	
	/**
	 * @description: 添加用户收货地址
	 * @测试地址如下：http://localhost:8080/ddhj/api.htm?apiTarget=address_add&api_key=appfamilyhas&
	 * apiInput={"name":"出柜斌","phone":"15800002222","provinces":"天津市河西区鸭厂镇","street":"河东区成林庄道100号","isDefault":1}
	 * @返回数据如下：
		{
		    "resultCode": 0,
		    "resultMessage": "地址添加成功"
		}
	 * @param entity
	 * @author Yangcl 
	 * @date 2017年7月27日 下午2:52:00 
	 * @version 1.0.0.1
	 */
	public JSONObject addUserAddress(JSONObject e_ , String userToken) {
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
		
		TUserAddress e = null;
		try {
			e = e_.toJavaObject(TUserAddress.class);
		} catch (Exception ex) {
			re.put("resultCode", -1);
			re.put("resultMessage", "地址信息解析错误");
			return re;
		}
		if(e == null){
			re.put("resultCode", -1);
			re.put("resultMessage", "地址信息解析错误，有效数据为空");
			return re;
		}
		
		if(StringUtils.isAnyBlank(e.getPhone() , e.getStreet() , e.getName() , e.getProvinces() , e.getIsDefault()==null ? "" :e.getIsDefault().toString())){
			re.put("resultCode", -1);
			re.put("resultMessage", "地址关键信息不得为空");
			return re;
		}
		e.setUserCode(userCode); 
		e.setUuid(UUID.randomUUID().toString().replace("-", "")); 
		e.setCode(WebHelper.getInstance().getUniqueCode("AR")); 
		e.setCreateUser(e.getUserCode());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String cdate = sdf.format(new Date());
		e.setCreateTime(cdate);
		e.setUpdateUser(e.getUserCode());
		e.setUpdateTime(cdate);
		if(e.getIsDefault() == 1){
			mapper.updateAddressByUsercode(userCode);
		}
		int flag = mapper.insertSelective(e);
		if(flag == 1){
			re.put("resultCode", 1);
			re.put("resultMessage", "地址添加成功");
		}else{
			re.put("resultCode", -1);
			re.put("resultMessage", "添加收货地址失败");
		}
		return re;
	}



	/**
	 * @description: 删除收货地址 
	 * @测试地址如下：http://localhost:8080/ddhj/api.htm?apiTarget=address_del&api_key=appfamilyhas&apiInput={"addressID":"AR170727100004"}
	 * @返回数据如下：
		{
		    "resultCode": 0,
		    "resultMessage": "用户收货地址删除成功"
		}
	 * 
	 * @param obj
	 * @author Yangcl 
	 * @date 2017年7月27日 下午3:28:30 
	 * @version 1.0.0.1
	 */
	public JSONObject deleteUserAddress(JSONObject obj , String userToken) {
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
		
		String addrCode = obj.getString("addressID"); 
		if(StringUtils.isAnyBlank(userCode , addrCode)){
			re.put("resultCode", -1);
			re.put("resultMessage", "addressID关键参数不得为空");
			return re;
		}
		TUserAddress e = new TUserAddress();
		e.setUserCode(userCode);
		e.setCode(addrCode);
		int flag = mapper.deleteUserAddress(e);
		if(flag == 1){
			re.put("resultCode", 1);
			re.put("resultMessage", "用户收货地址删除成功");
		}else{
			re.put("resultCode", -1);
			re.put("resultMessage", "用户收货地址删除失败");
		}
		
		return re;
	}

	
	public PageResult findUserAddressPage(TUserAddressDto dto) {
		PageResult result = new PageResult();
		PageHelper.startPage(dto.getPageIndex(), dto.getPageSize());
		List<TUserAddress> list = mapper.findEntityAll(dto);
		if (list != null && list.size() > 0) {
			result.setResultCode(Constant.RESULT_SUCCESS);
		} else {
			list = new ArrayList<TUserAddress>();
			result.setResultCode(Constant.RESULT_NULL);
			result.setResultMessage("查询商品列表为空");
		}
		PageInfo<TUserAddress> page = new PageInfo<TUserAddress>(list);
		result.setPage(page);
		return result;
	}



	/**
	 * @description: 获取一条收货地址的数据  
	 * 
	 * @param obj
	 * @param userToken
	 * @author Yangcl 
	 * @date 2017年8月3日 下午5:17:20 
	 * @version 1.0.0.1
	 */
	public JSONObject findUserAddress(JSONObject obj, String userToken) {
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
		
		String addrCode = obj.getString("addressID"); 
		if(StringUtils.isAnyBlank(userCode , addrCode)){
			re.put("resultCode", -1);
			re.put("resultMessage", "addressID关键参数不得为空");
			return re;
		}
		TUserAddress e = new TUserAddress();
		e.setUserCode(userCode);
		e.setCode(addrCode);
		TUserAddress ua = mapper.findUserAddress(e);
		if(ua != null){
			re.put("resultCode", 1);
			re.put("resultMessage", "操作成功");
			re.put("phone", ua.getPhone());
			re.put("areaCode", ua.getAreaCode());
			re.put("street", ua.getStreet());
			re.put("isDefault", ua.getIsDefault().toString());
			re.put("name", ua.getName());
			re.put("province", ua.getProvinces());
		}else{
			re.put("resultCode", -1);
			re.put("resultMessage", "用户尚无收货地址");
		}
		return re;
	}
}

















