package cn.com.ddhj.mapper;

import java.util.List;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.model.TAppBootPic;

public interface TAppBootPicMapper extends BaseMapper<TAppBootPic, BaseDto>{

	public List<TAppBootPic> getListByVersion(String appVersion);  
	
}