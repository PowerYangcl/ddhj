package cn.com.ddhj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.ddhj.dto.TLandedPropertyDto;
import cn.com.ddhj.result.lp.TLandedPropertyDataResult;
import cn.com.ddhj.service.ITLandedPropertyService;

@Controller
@RequestMapping("lp/")
public class TLandedPropertyController {

	@Autowired
	private ITLandedPropertyService service;

	@RequestMapping("index")
	public String index(){
		return "jsp/lp/index";
	}
	@RequestMapping("data")
	@ResponseBody
	public TLandedPropertyDataResult getData(TLandedPropertyDto dto) {
		return service.getLpData(dto);
	}
}
