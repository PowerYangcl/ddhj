package cn.com.ddhj.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.report.TReportDto;
import cn.com.ddhj.model.report.TReport;
import cn.com.ddhj.result.report.TReportDataResult;
import cn.com.ddhj.result.report.TReportSelResult;
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
	 * 方法: insertReport <br>
	 * 描述: 添加新的环境报告 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月4日 下午9:43:41
	 * 
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public BaseResult insertReport(TReport entity, HttpServletRequest request) {
		String filePath = request.getSession().getServletContext().getRealPath("");
		return service.insert(entity, filePath);
	}

	/**
	 * 
	 * 方法: updateReport <br>
	 * 描述: 编辑现有的环境报告 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月4日 下午9:43:57
	 * 
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping("edit")
	@ResponseBody
	public BaseResult updateReport(TReport entity, HttpServletRequest request) {
		String filePath = request.getSession().getServletContext().getRealPath("");
		return service.updateByCode(entity, filePath);
	}

	/**
	 * 
	 * 方法: getTReport <br>
	 * 描述: 根据编码查询环境报告 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月4日 下午10:12:31
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping("selbycode")
	@ResponseBody
	public TReportSelResult getTReport(String code) {
		return service.getTReport(code);
	}
}
