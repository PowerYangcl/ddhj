package cn.com.ddhj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.mapper.TReportEnvironmentLevelMapper;
import cn.com.ddhj.mapper.TReportMapper;
import cn.com.ddhj.mapper.TReportTemplateMapper;
import cn.com.ddhj.model.TReport;
import cn.com.ddhj.model.TReportEnvironmentLevel;
import cn.com.ddhj.model.TReportTemplate;
import cn.com.ddhj.service.ITReportService;
import cn.com.ddhj.util.PdfUtil;

/**
 * 
 * 类: TReportServiceImpl <br>
 * 描述: 环境报告业务逻辑处理接口实现类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月3日 下午1:58:12
 */
@Service
public class TReportServiceImpl extends BaseServiceImpl<TReport, TReportMapper, BaseDto> implements ITReportService {

	@Autowired
	private TReportTemplateMapper templateMapper;

	@Autowired
	private TReportEnvironmentLevelMapper levelMapper;

	/**
	 * 
	 * 方法: createPDF <br>
	 * 描述: TODO
	 * 
	 * @param array
	 * @return
	 * @see cn.com.ddhj.service.ITReportService#createPDF(com.alibaba.fastjson.JSONArray)
	 */
	@Override
	public String createPDF() {
		String path = "D:\\report.pdf";
		List<TReportTemplate> templateList = templateMapper.findReportTemplateAll();
		List<TReportEnvironmentLevel> levelList = levelMapper.findTReportEnvironmentLevelAll();
		if (templateList != null && templateList.size() > 0) {
			JSONArray array = new JSONArray();
			for (int i = 0; i < templateList.size(); i++) {
				TReportTemplate model = templateList.get(i);
				JSONObject obj = new JSONObject();
				obj.put("title", model.getName());
				obj.put("content", model.getContent());
				obj.put("level", getLevelContent(model.getType(), 1, levelList));
				System.out.println(obj.getString("level"));
				array.add(obj);
			}
			try {
				PdfUtil.instance().createPDF(array, path);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return path;
	}

	/**
	 * 
	 * 方法: getLevelContent <br>
	 * 描述: 获取环境等级描述信息 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月3日 下午2:27:42
	 * 
	 * @param type
	 * @param level
	 * @param list
	 * @return
	 */
	private static String getLevelContent(String type, Integer level, List<TReportEnvironmentLevel> list) {
		String content = "";
		if (list != null && list.size() > 0) {
			for (TReportEnvironmentLevel model : list) {
				if (type.equals(model.getType()) && level == model.getLevel()) {
					content = model.getContent();
					break;
				}
			}
		}
		return content;
	}
}
