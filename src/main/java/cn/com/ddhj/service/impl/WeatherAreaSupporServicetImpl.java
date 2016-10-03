package cn.com.ddhj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.dto.WeatherAreaSupportDto;
import cn.com.ddhj.mapper.IWeatherAreaSupportMapper;
import cn.com.ddhj.model.WeatherAreaSupport;
import cn.com.ddhj.service.IWeatherAreaSupportService;

@Service
public class WeatherAreaSupporServicetImpl 
				extends BaseServiceImpl<WeatherAreaSupport , IWeatherAreaSupportMapper , WeatherAreaSupportDto>
				implements IWeatherAreaSupportService{
	
	@Autowired
	private IWeatherAreaSupportMapper mapper;
	
	
	
	
}
