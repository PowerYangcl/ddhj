package cn.com.ddhj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.TOrderDto;
import cn.com.ddhj.result.order.SysOrderDataResult;
import cn.com.ddhj.service.ITOrderService;

@Controller
@RequestMapping("order/")
public class TOrderController {

	@Autowired
	private ITOrderService service;

	@RequestMapping("index")
	public String index() {
		return "jsp/order/index";
	}

	@RequestMapping("data")
	@ResponseBody
	public SysOrderDataResult getData(TOrderDto dto) {
		return service.getOrderBySys(dto);
	}

	@RequestMapping("deleteOne")
	@ResponseBody
	public JSONObject deleteOne(Integer id) {
		return service.deleteOne(id);
	}

	@RequestMapping("refresh_report")
	@ResponseBody
	public BaseResult refreshReport(String code) {
		return service.refreshReport(code);
	}
}
