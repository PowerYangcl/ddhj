package cn.com.ddhj.mapper;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.dto.user.TUserAddressDto;
import cn.com.ddhj.model.TUserAddress;

public interface TUserAddressMapper extends BaseMapper<TUserAddress, BaseDto>{

	public int deleteUserAddress(TUserAddress e); 
	
	public void selectByUserCode(TUserAddressDto userDto);
	
}