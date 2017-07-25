package cn.com.ddhj.controller.store;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.result.DataResult;

@Controller
@RequestMapping("/store/product/")
public class TProductInfoController {

	
	@RequestMapping("index")
	public String index() {
		return null;
	}

	@RequestMapping("data")
	@ResponseBody
	public DataResult data() {
		return null;
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
