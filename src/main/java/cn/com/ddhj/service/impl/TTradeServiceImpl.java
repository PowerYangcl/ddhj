package cn.com.ddhj.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.ddhj.mapper.TTradeObjectMapper;
import cn.com.ddhj.mapper.TTradeOrderMapper;
import cn.com.ddhj.service.ITradeService;

@Service
public class TTradeServiceImpl implements ITradeService {
	@Resource
	private TTradeObjectMapper mapper;
	
	@Resource
	private TTradeOrderMapper tradeOrderMapper;
	
	
	
}
