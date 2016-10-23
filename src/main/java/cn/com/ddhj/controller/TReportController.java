package cn.com.ddhj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.report.TReportDto;
import cn.com.ddhj.model.system.SysUser;
import cn.com.ddhj.result.report.TReportDataResult;
import cn.com.ddhj.service.report.ITReportService;

@Controller
@RequestMapping("report/")
public class TReportController {

	@Autowired
	private ITReportService service;

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
}
