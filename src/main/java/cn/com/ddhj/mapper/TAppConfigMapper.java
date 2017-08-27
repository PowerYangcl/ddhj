package cn.com.ddhj.mapper;

import java.util.List;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.model.TAppConfig;

public interface TAppConfigMapper  extends BaseMapper<TAppConfig , BaseDto>{

	public List<TAppConfig> findEntityList(); 

}