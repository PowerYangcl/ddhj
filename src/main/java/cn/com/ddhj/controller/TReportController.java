package cn.com.ddhj.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseAPI;
import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.report.TReportDto;
import cn.com.ddhj.model.report.TReport;
import cn.com.ddhj.result.report.TReportLResult;
import cn.com.ddhj.result.report.TReportSelResult;
import cn.com.ddhj.service.report.ITReportService;

@Controller
@RequestMapping("report/")
public class TReportController {

	@Autowired
	private ITReportService service;

	@RequestMapping("api")
	@ResponseBody
	public BaseResult api(BaseAPI api) {
		JSONObject obj = JSONObject.parseObject(api.getApiInput());
		if ("data".equals(api.getApiTarget())) {
			TReportDto dto = obj.toJavaObject(TReportDto.class);
			TReportLResult result = service.getReportData(dto);
			return result;
		} else if ("selreport".equals(api.getApiTarget())) {
			String code = obj.getString("code");
			TReportSelResult result = service.getTReport(code);
			return result;
		} else {
			BaseResult result = new BaseResult();
			result.setResultCode(-1);
			result.setResultMessage("调用接口失败");
			return result;
		}
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
	 * 方法: getData <br>
	 * 描述: 获取分页数据 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月4日 下午9:49:49
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping("data")
	@ResponseBody
	public TReportLResult getData(TReportDto dto) {
		return service.getReportData(dto);
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
