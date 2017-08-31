package cn.com.ddhj.controller.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.store.TProductOrderDto;
import cn.com.ddhj.model.TProductOrder;
import cn.com.ddhj.result.EntityResult;
import cn.com.ddhj.result.PageResult;
import cn.com.ddhj.service.store.ITProductOrderService;

@Controller
@RequestMapping("store/order/")
public class TProductOrderController {

	@Autowired
	private ITProductOrderService service;

	@RequestMapping("index")
	public String index() {
		return "/jsp/store/order/index";
	}

	@RequestMapping("data")
	@ResponseBody
	public PageResult data(TProductOrderDto dto) {
		return service.findDataPage(dto);
	}

	@RequestMapping("detail")
	public String detail(String code, ModelMap model) {
		EntityResult result = service.findOrderByCode(code);
		model.addAttribute("order", result.getEntity());
		return "/jsp/store/order/detail";
	}
	
	@RequestMapping("editOrderStatus")
	@ResponseBody
	public BaseResult edit(TProductOrder entity){
		entity.setUpdateUser("system");
		return service.updateByCode(entity);
	}
}
