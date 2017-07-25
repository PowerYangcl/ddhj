package cn.com.ddhj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.ddhj.service.system.ISysMenuService;

@Controller
public class IndexController {
	@Autowired
	private ISysMenuService service;

	@RequestMapping("index")
	public String index(ModelMap model) {
		model.addAttribute("default", service.menu().get(0));
		model.addAttribute("menu", service.menu());
		return "/jsp/index/index";
	}

	@RequestMapping("subindex")
	public String subIndex(ModelMap model, String menuCode) {
		model.addAttribute("default", service.subMenu().get(0));
		model.addAttribute("menu", service.subMenu());
		return "/jsp/index/sub_index";
	}
}
