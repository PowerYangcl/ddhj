package cn.com.ddhj.service.store;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.model.TUserAddresss;
import cn.com.ddhj.service.IBaseService;

public interface ITUserAddresssService extends IBaseService<TUserAddresss, BaseDto> {

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
	BaseResult updateByCode(TUserAddresss entity, String userToken);
}
