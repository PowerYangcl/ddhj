package cn.com.ddhj.mapper;

import java.util.List;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.model.TAppVersion;

public interface TAppVersionMapper extends BaseMapper<TAppVersion , BaseDto>{

	public List<TAppVersion> findEntityList(); 

}