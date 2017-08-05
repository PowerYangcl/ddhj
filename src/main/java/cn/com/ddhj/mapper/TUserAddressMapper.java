package cn.com.ddhj.mapper;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.dto.store.TUserAddressUpdateDto;
import cn.com.ddhj.dto.user.TUserAddressDto;
import cn.com.ddhj.model.TUserAddress;

public interface TUserAddressMapper extends BaseMapper<TUserAddress, BaseDto> {

	public int deleteUserAddress(TUserAddress e);

	public void selectByUserCode(TUserAddressDto userDto);

	public TUserAddress findUserAddress(TUserAddress e);

	/**
	 * 地址编码查询地址详细信息
	 * 
	 * @param addressCode
	 * @return
	 */
	String findAddressDetail(String addressCode);

	public Integer updateByAddressID(TUserAddressUpdateDto dto);
}