package cn.com.ddhj.controller.store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.store.TProductInfoDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.model.TProductInfo;
import cn.com.ddhj.model.system.SysUser;
import cn.com.ddhj.result.PageResult;
import cn.com.ddhj.service.store.ITProductInfoService;
import cn.com.ddhj.util.Constant;

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
	public String detail(String productCode, ModelMap model) {
		TProductInfo product = service.selectByCode(productCode);
		model.addAttribute("product", product);
		return "/jsp/store/product/detail";
	}

	@RequestMapping("addindex")
	public String addIndex() {
		return "/jsp/store/product/add";
	}

	@RequestMapping("add")
	@ResponseBody
	public BaseResult add(TProductInfo entity, HttpSession session) {
		BaseResult result = new BaseResult();
		SysUser user = (SysUser) session.getAttribute("user");
		if (user != null) {
			entity.setProductCode(WebHelper.getInstance().getUniqueCode("TP"));
			entity.setCreateUser(user.getCode());
			result = service.insertSelective(entity);
		} else {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户尚未登录");
		}
		return result;
	}

	@RequestMapping("editindex")
	public String editIndex(String productCode, HttpServletRequest request, ModelMap model) {
		TProductInfo product = service.selectByCode(productCode, request);
		model.addAttribute("product", product);
		return "/jsp/store/product/edit";
	}

	@RequestMapping("edit")
	@ResponseBody
	public BaseResult edit(TProductInfo entity, HttpSession session) {
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

	@RequestMapping("del")
	@ResponseBody
	public BaseResult del(String productCode, HttpSession session) {
		BaseResult result = new BaseResult();
		SysUser user = (SysUser) session.getAttribute("user");
		if (user != null) {
			result = service.deleteByCode(productCode);
		} else {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户尚未登录");
		}
		return result;
	}

	@RequestMapping("upload")
	@ResponseBody
	public JSONArray upload(@RequestParam("uploadFile[]") MultipartFile[] uploadFile, HttpServletRequest request,
			HttpServletResponse response) {
		return service.uploadFile(uploadFile, request, response);
	}

	@RequestMapping("delfile")
	@ResponseBody
	public BaseResult delFile(String file, HttpServletRequest request, HttpServletResponse response) {
		return service.delFile(file, request, response);
	}
}
