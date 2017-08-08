package cn.com.ddhj.service.store;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.dto.store.TUserAddressUpdateDto;
import cn.com.ddhj.dto.user.TUserAddressDto;
import cn.com.ddhj.model.TUserAddress;
import cn.com.ddhj.service.IBaseService;

public interface ITUserAddressService extends IBaseService<TUserAddress, BaseDto> {

	/**
	 * 
	 * 方法: updateByCode <br>
	 * 描述: 修改收货地址 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年7月24日 上午11:29:38
	 * 
	 * @param entity
	 * @param userToken
	 * @return
	 */
	BaseResult updateByCode(TUserAddressUpdateDto dto, String userToken);
	
	/**
	 * @description: 添加用户收货地址
	 * 
	 * @param entity
	 * @author Yangcl 
	 * @date 2017年7月27日 下午2:52:00 
	 * @version 1.0.0.1
	 */
	public JSONObject addUserAddress(JSONObject entity , String userToken);

	/**
	 * @description: 删除收货地址
	 * 
	 * @param obj
	 * @return
	 * @author Yangcl 
	 * @date 2017年7月27日 下午3:27:57 
	 * @version 1.0.0.1
	 */
	public JSONObject deleteUserAddress(JSONObject obj , String userToken);

	/**
	 * 查询用户收货地址列表
	 * @author zht
	 * @param dto
	 * @return
	 */
	BaseResult findUserAddressPage(TUserAddressDto dto);

	public JSONObject findUserAddress(JSONObject obj, String userToken); 
}
