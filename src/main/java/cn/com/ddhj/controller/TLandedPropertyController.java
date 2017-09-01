package cn.com.ddhj.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.TLandedPropertyDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.model.system.SysUser;
import cn.com.ddhj.result.lp.TLandedPropertyDataResult;
import cn.com.ddhj.service.ITLandedPropertyService;
import cn.com.ddhj.util.Constant;

@Controller
@RequestMapping("lp/")
public class TLandedPropertyController {

	@Autowired
	private ITLandedPropertyService service;

	@RequestMapping("index")
	public String index() {
		return "jsp/lp/index";
	}

	@RequestMapping("data")
	@ResponseBody
	public TLandedPropertyDataResult getData(TLandedPropertyDto dto) {
		return service.getLpData(dto);
	}

	@RequestMapping("addindex")
	public String addIndex() {
		return "jsp/lp/add";
	}

	@RequestMapping("add")
	@ResponseBody
	public BaseResult add(TLandedProperty entity, HttpSession session) {
		BaseResult result = new BaseResult();
		SysUser user = (SysUser) session.getAttribute("user");
		if (user != null) {
			entity.setCode(WebHelper.getInstance().getUniqueCode("LP"));
			entity.setCreateUser(user.getCode());
			result = service.insertSelective(entity);
		} else {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户尚未登录");
		}
		return result;
	}

	@RequestMapping("editindex")
	public String editIndex(ModelMap model, String code) {
		TLandedProperty lp = service.selectByCode(code);
		model.addAttribute("lp", lp);
		return "jsp/lp/edit";
	}

	@RequestMapping("edit")
	@ResponseBody
	public BaseResult edit(TLandedProperty entity, HttpSession session) {
		BaseResult result = new BaseResult();
		SysUser user = (SysUser) session.getAttribute("user");
		if (user != null) {
			entity.setUpdateUser(user.getCode());
			result = service.updateByCode(entity);
		} else {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户尚未登录");
		}
		return result;
	}
}
