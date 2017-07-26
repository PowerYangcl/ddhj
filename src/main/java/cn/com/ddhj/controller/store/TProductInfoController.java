package cn.com.ddhj.controller.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.store.TProductInfoDto;
import cn.com.ddhj.result.PageResult;
import cn.com.ddhj.service.store.ITProductInfoService;

@Controller
@RequestMapping("store/product/")
public class TProductInfoController {

	@Autowired
	private ITProductInfoService service;

	@RequestMapping("index")
	public String index() {
		return "/jsp/store/product/index";
	}

	@RequestMapping("data")
	@ResponseBody
	public PageResult data(TProductInfoDto dto) {
		return service.findDataPage(dto);
	}

	@RequestMapping("detail")
	public String detail() {
		return null;
	}

	@RequestMapping("add")
	@ResponseBody
	public BaseResult add() {
		return null;
	}

	@RequestMapping("edit")
	@ResponseBody
	public BaseResult edit() {
		return null;
	}

	@RequestMapping("del")
	@ResponseBody
	public BaseResult del() {
		return null;
	}
}
