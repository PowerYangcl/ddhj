package cn.com.ddhj.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.ddhj.result.report.PDFReportResult;
import cn.com.ddhj.service.ITReportService;

@Controller
@RequestMapping("report/")
public class TReportController {

	@Autowired
	private ITReportService service;

	@RequestMapping("createpdf")
	@ResponseBody
	public PDFReportResult createPDF(String code, HttpServletRequest request) {
		// 获取项目路径
		String filePath = request.getSession().getServletContext().getRealPath("");
		System.out.println(filePath);
		return service.createPDF(code, filePath);
	}
}
