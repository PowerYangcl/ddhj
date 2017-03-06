package cn.com.ddhj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.report.TReportDto;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.model.report.TReport;
import cn.com.ddhj.model.report.TReportLevel;
import cn.com.ddhj.model.system.SysUser;
import cn.com.ddhj.result.report.TReportDataResult;
import cn.com.ddhj.service.ITLandedPropertyService;
import cn.com.ddhj.service.report.ITReportLevelService;
import cn.com.ddhj.service.report.ITReportService;

@Controller
@RequestMapping("sys/report/")
public class TReportController {

	@Autowired
	private ITReportService service;
	@Autowired
	private ITLandedPropertyService lpService;
	@Autowired
	private ITReportLevelService rlService;

	@RequestMapping("index")
	public String index() {
		return "jsp/report/index";
	}

	/**
	 * 
	 * 方法: getData <br>
	 * 描述: 获取所有报告列表 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月21日 下午11:55:24
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping("data")
	@ResponseBody
	public TReportDataResult data(TReportDto dto) {
		return service.getPageData(dto);
	}

	/**
	 * 
	 * 方法: createPDF <br>
	 * 描述: 根据楼盘编码更新报告 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月22日 下午8:54:30
	 * 
	 * @param dto
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("create")
	@ResponseBody
	public BaseResult createPDF(TReportDto dto, HttpServletRequest request, HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("user");
		String path = request.getSession().getServletContext().getRealPath("");
		return service.createReport(dto, path, user);
	}
	
	/**
	 * 
	 * 方法: createPDF <br>
	 * 描述: 根据楼盘编码更新报告 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月22日 下午8:54:30
	 * 
	 * @param dto
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("createAll")
	@ResponseBody
	public BaseResult createAllPDF(TReportDto dto, HttpServletRequest request, HttpSession session) {
		BaseResult result = new BaseResult();
		result.setResultCode(service.batchCreateReport());
		return result;
	}

	@RequestMapping("addindex")
	public String addIndex(String lpCode, ModelMap model) {
		TLandedProperty lp = lpService.selectByCode(lpCode);
		model.addAttribute("lp", lp);
		// 报告等级
		List<TReportLevel> rl = rlService.findLevelAll();
		model.addAttribute("rl", rl);
		return "jsp/report/add";
	}

	@RequestMapping("add")
	@ResponseBody
	public BaseResult add(TReport entity, HttpServletRequest request) {
		entity.setCreateUser("system");
		String path = request.getSession().getServletContext().getRealPath("");
		return service.insertSelective(entity, path);
	}

	@RequestMapping("editindex")
	public String editIndex(String code, ModelMap model) {
		TReport r = service.selectByCode(code);
		model.addAttribute("r", r);
		// 报告等级
		List<TReportLevel> rl = rlService.findLevelAll();
		model.addAttribute("rl", rl);
		return "jsp/report/edit";
	}

	@RequestMapping("edit")
	@ResponseBody
	public BaseResult edit(TReport entity, HttpServletRequest request) {
		entity.setCreateUser("system");
		String path = request.getSession().getServletContext().getRealPath("");
		return service.updateByCode(entity, path);
	}
}
