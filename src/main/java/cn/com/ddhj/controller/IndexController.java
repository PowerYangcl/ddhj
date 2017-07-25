package cn.com.ddhj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.ddhj.model.system.SysMenu;
import cn.com.ddhj.service.system.ISysMenuService;

@Controller
public class IndexController {
	@Autowired
	private ISysMenuService service;

	@RequestMapping("index")
	public String index(ModelMap model) {
		List<SysMenu> list = service.menu("SMG0001", "0");
		model.addAttribute("default", list.get(0));
		model.addAttribute("menus", list);
		return "/jsp/index/index";
	}

	@RequestMapping("subindex")
	public String subIndex(ModelMap model, String groupCode, String parentCode) {
		List<SysMenu> list = service.menu(groupCode, parentCode);
		model.addAttribute("default", list.get(0));
		model.addAttribute("menus", list);
		return "/jsp/index/sub_index";
	}
}
