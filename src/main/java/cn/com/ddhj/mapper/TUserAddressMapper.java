package cn.com.ddhj.mapper;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.model.TUserAddress;

public interface TUserAddressMapper extends BaseMapper<TUserAddress, BaseDto>{

	public int deleteUserAddress(TUserAddress e); 
	
}