package cn.com.ddhj.mapper;

import java.util.List;

import cn.com.ddhj.model.TAreaNoise;

public interface ITAreaNoiseMapper {

	public Integer insertSelective(TAreaNoise entity);
	
	public List<TAreaNoise> selectByArea(String city);  
}
