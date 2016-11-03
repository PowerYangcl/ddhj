package cn.com.ddhj.mapper;

import java.util.List;

import cn.com.ddhj.model.TWaterEnviroment;

public interface TWaterEnviromentMapper {
	
	public Integer insertSelective(TWaterEnviroment e);
	
	public List<TWaterEnviroment> selectByCity(String city);  
}
