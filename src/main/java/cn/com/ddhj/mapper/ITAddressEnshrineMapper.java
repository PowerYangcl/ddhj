package cn.com.ddhj.mapper;

import java.util.List;

import cn.com.ddhj.model.TAddressEnshrine;

public interface ITAddressEnshrineMapper {

	public int insertSelective(TAddressEnshrine e); 

	public Integer selectCountByName(TAddressEnshrine e);
	
	public List<TAddressEnshrine> getListByUserCode(String userCode);
}
