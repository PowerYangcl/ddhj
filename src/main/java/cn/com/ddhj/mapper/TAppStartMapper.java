package cn.com.ddhj.mapper;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.model.TAppStart;

public interface TAppStartMapper extends BaseMapper<TAppStart , BaseDto>{

	public TAppStart getEntityByVersion(String appVersion); 

}