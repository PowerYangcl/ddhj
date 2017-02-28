package cn.com.ddhj.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.ddhj.mapper.TTradeObjectMapper;
import cn.com.ddhj.model.TTradeObject;
import cn.com.ddhj.service.ITTradeObjectService;

@Service
public class TTradeObjectServiceImpl implements ITTradeObjectService {

	@Resource
	private TTradeObjectMapper mapper;
	
	
	
	
	
	
	
	public static void main(String[] args) {
		TTradeObject trade = new TTradeObject();
	}
}
