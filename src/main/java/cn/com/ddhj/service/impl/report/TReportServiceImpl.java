package cn.com.ddhj.service.impl.report;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.dto.report.TReportDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.mapper.report.TReportEnvironmentLevelMapper;
import cn.com.ddhj.mapper.report.TReportMapper;
import cn.com.ddhj.mapper.report.TReportTemplateMapper;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.model.report.TReport;
import cn.com.ddhj.model.report.TReportEnvironmentLevel;
import cn.com.ddhj.model.report.TReportTemplate;
import cn.com.ddhj.result.report.PDFReportResult;
import cn.com.ddhj.result.report.TReportLResult;
import cn.com.ddhj.result.report.TReportSelResult;
import cn.com.ddhj.service.ICityAirService;
import cn.com.ddhj.service.ITRubbishRecyclingService;
import cn.com.ddhj.service.IWaterQualityService;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.report.ITReportService;
import cn.com.ddhj.util.CommonUtil;
import cn.com.ddhj.util.DateUtil;
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
	private TReportMapper mapper;
	@Autowired
	private TReportTemplateMapper templateMapper;

	@Autowired
	private TReportEnvironmentLevelMapper levelMapper;
	@Autowired
	private TLandedPropertyMapper lpMapper;
	@Autowired
	private ICityAirService cityAirService;
	@Autowired
	private IWaterQualityService waterQualityService;
	@Autowired
	private ITRubbishRecyclingService rubbishService;

	/**
	 * 
	 * 方法: createPDF <br>
	 * 描述: TODO
	 * 
	 * @param array
	 * @return
	 * @see cn.com.ddhj.service.report.ITReportService#createPDF(com.alibaba.fastjson.JSONArray)
	 */
	@Override
	public PDFReportResult createPDF(String code, String housesCode, String path) {
		PDFReportResult result = new PDFReportResult();
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			String filePath = "report/" + code + ".pdf";
			path = path + "/" + filePath;
			// 根据code获取地产楼盘信息
			TLandedProperty lp = lpMapper.selectByCode(housesCode);
			List<TReportTemplate> templateList = templateMapper.findReportTemplateAll();
			List<TReportEnvironmentLevel> levelList = levelMapper.findTReportEnvironmentLevelAll();
			// 获取绿地率等级
			int afforestLevel = 1;
			// if (lp.getGreeningRate() != null &&
			// !"".equals(lp.getGreeningRate())
			// && !StringUtils.isEmpty(lp.getGreeningRate())) {
			// Double afforest =
			// Double.valueOf(lp.getGreeningRate().substring(0,
			// lp.getGreeningRate().indexOf("%")));
			// if (afforest > 25 && afforest < 30) {
			// afforestLevel = 2;
			// } else if (afforest < 25) {
			// afforestLevel = 3;
			// }
			// }
			// 获取容积率等级
			int volumeLevel = 1;
			if (lp.getVolumeRate() != null && !"".equals(lp.getVolumeRate())) {
				try {
					Double volume = Double.valueOf(lp.getVolumeRate());
					if (volume > 3 && volume < 5) {
						volumeLevel = 2;
					} else if (volume > 5) {
						volumeLevel = 3;
					}
				} catch (Exception e) {
					volumeLevel = 1;
				}
			}
			// 空气质量等级
			Integer airLevel = 1;
			// if (lp.getCity() != null && !"".equals(lp.getCity())) {
			// airLevel = cityAirService.getAQILevel(lp.getCity());
			// }
			// 水质量等级
			Integer waterLevel = 1;// waterQualityService.getWaterLevel("北京密云古北口");
			// 垃圾设施等级
			Integer rubbishLevel = 1;
			if (StringUtils.isEmpty(lp.getCity()) && StringUtils.isEmpty(lp.getLat())
					&& StringUtils.isEmpty(lp.getLng())) {
				rubbishLevel = rubbishService.getRubbishLevel(lp.getCity(), lp.getLat(), lp.getLng());
			}
			if (templateList != null && templateList.size() > 0) {
				JSONArray array = new JSONArray();
				for (int i = 0; i < templateList.size(); i++) {
					TReportTemplate model = templateList.get(i);
					JSONObject obj = new JSONObject();
					obj.put("title", model.getName());
					obj.put("content", model.getContent());
					if ("afforest".equals(model.getType())) {
						obj.put("level", getLevelContent(model.getType(), afforestLevel, levelList));
					} else if ("volume".equals(model.getType())) {
						obj.put("level", getLevelContent(model.getType(), volumeLevel, levelList));
					} else if ("air".equals(model.getType())) {
						obj.put("level", getLevelContent(model.getType(), airLevel, levelList));
					} else if ("water".equals(model.getType())) {
						obj.put("level", getLevelContent(model.getType(), waterLevel, levelList));
					} else if ("rubbish".equals(model.getType())) {
						obj.put("level", getLevelContent(model.getType(), rubbishLevel, levelList));
					} else {
						obj.put("level", getLevelContent(model.getType(), 1, levelList));
					}
					array.add(obj);
				}
				PdfUtil.instance().createPDF(array, path);
				result.setResultCode(0);
				result.setResultMessage("");
				result.setPath(filePath);
			} else {
				result.setResultCode(-1);
				result.setResultMessage("创建报告失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(-1);
			result.setResultMessage("创建报告失败");
		}
		return result;
	}

	/**
	 * 
	 * 方法: getReportData <br>
	 * 描述: TODO
	 * 
	 * @param dto
	 * @return
	 * @see cn.com.ddhj.service.report.ITReportService#getReportData(cn.com.ddhj.dto.report.TReportDto)
	 */
	@Override
	public TReportLResult getReportData(TReportDto dto) {
		TReportLResult result = new TReportLResult();
		if (dto.getCode() != null && !"".equals(dto.getCode())) {
			// 获取传入坐标
			TLandedProperty lp = lpMapper.selectByCode(dto.getCode());
			if (lp != null) {
				// 查询环境报告的楼盘名称
				result.setName(lp.getTitle());
				int raidus = 10 * 1000;
				if (dto.getRaidus() != null) {
					raidus = dto.getRaidus() * 1000;
				}
				double[] around = CommonUtil.getAround(Double.valueOf(lp.getLat()), Double.valueOf(lp.getLng()),
						raidus);
				dto.setMinLat(String.valueOf(around[0]));
				dto.setMinLng(String.valueOf(around[1]));
				dto.setMaxLat(String.valueOf(around[2]));
				dto.setMaxLng(String.valueOf(around[3]));
				dto.setStart(dto.getPageIndex() * dto.getPageSize());
				List<TReport> list = mapper.findEntityAll(dto);
				if (list != null && list.size() > 0) {
					result.setRepList(list);
					Integer total = mapper.findEntityAllCount(dto);
					result.setRepCount(total);
					result.setResultCode(0);
					result.setResultMessage("查询楼盘环境报告列表成功");
				} else {
					result.setRepList(new ArrayList<TReport>());
					result.setRepCount(0);
					result.setResultCode(-1);
					result.setResultMessage("环境报告为空");
				}
			} else {
				result.setResultCode(-1);
				result.setResultMessage("查询楼盘不存在");
			}
		} else {
			result.setResultCode(-1);
			result.setResultMessage("查询楼盘编码不能为空");
		}
		return result;
	}

	/**
	 * 
	 * 方法: insert <br>
	 * 描述: TODO
	 * 
	 * @param entity
	 * @return
	 * @see cn.com.ddhj.service.report.ITReportService#insert(cn.com.ddhj.model.report.TReport)
	 */
	@Override
	public BaseResult insert(TReport entity, String path) {
		BaseResult result = new BaseResult();
		String code = WebHelper.getInstance().getUniqueCode("R");
		PDFReportResult pdfResult = this.createPDF(code, entity.getHousesCode(), path);
		pdfResult.setResultCode(0);
		if (pdfResult.getResultCode() == 0) {
			entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
			entity.setCode(code);
			entity.setCreateUser("system");
			entity.setUpdateUser("system");
			entity.setCreateTime(DateUtil.getSysDateTime());
			entity.setUpdateTime(DateUtil.getSysDateTime());
			entity.setPath(pdfResult.getPath());
			int flag = mapper.insertSelective(entity);
			if (flag > 0) {
				result.setResultCode(0);
				result.setResultMessage("添加成功");
			} else {
				result.setResultCode(-1);
				result.setResultMessage("添加失败");
			}
		} else {
			result.setResultCode(pdfResult.getResultCode());
			result.setResultMessage(pdfResult.getResultMessage());
		}
		return result;
	}

	@Override
	public BaseResult updateByCode(TReport entity, String path) {
		BaseResult result = new BaseResult();
		PDFReportResult pdfResult = this.createPDF(entity.getCode(), entity.getHousesCode(), path);
		pdfResult.setResultCode(0);
		if (pdfResult.getResultCode() == 0) {
			entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
			entity.setUpdateUser("system");
			entity.setUpdateTime(DateUtil.getSysDateTime());
			entity.setPath(pdfResult.getPath());
			int flag = mapper.updateByCode(entity);
			if (flag > 0) {
				result.setResultCode(0);
				result.setResultMessage("修改成功");
			} else {
				result.setResultCode(-1);
				result.setResultMessage("修改失败");
			}
		} else {
			result.setResultCode(pdfResult.getResultCode());
			result.setResultMessage(pdfResult.getResultMessage());
		}
		return result;

	}

	/**
	 * 
	 * 方法: getTReport <br>
	 * 描述: TODO
	 * 
	 * @param code
	 * @return
	 * @see cn.com.ddhj.service.report.ITReportService#getTReport(java.lang.String)
	 */
	@Override
	public TReportSelResult getTReport(String code) {
		TReportSelResult result = new TReportSelResult();
		TReport report = mapper.selectByCode(code);
		if (report != null) {
			result.setLevelList(mapper.findReportByHousesCode(report.getHousesCode()));
			result.setAddress(report.getAddress());
			result.setDetail(report.getDetail());
			result.setImage(report.getImage());
			result.setPic(report.getPic());
			result.setPrice(report.getPrice());
			result.setName(report.getHousesName());
			result.setResultCode(0);
			result.setResultMessage("查询报告成功");
		} else {
			result.setResultCode(-1);
			result.setResultMessage("环境报告为空");
		}
		return result;
	}

	/**
	 * 
	 * 方法: insertReportData <br>
	 * 描述: TODO
	 * 
	 * @param list
	 * @return
	 * @see cn.com.ddhj.service.report.ITReportService#insertReportData(java.util.List)
	 */
	@Override
	public BaseResult insertReportData(List<TReport> list) {
		BaseResult result = new BaseResult();
		if (list != null && list.size() > 0) {
			boolean flag = false;
			if (list.size() <= 10000) {
				mapper.insertReportData(list);
			} else {
				int multiple = list.size() / 10000;
				int count = 0;
				for (int i = 0; i < multiple; i++) {
					List<TReport> subList = list.subList(count * 10000, (count + 1) * 10000 - 1);
					mapper.insertReportData(subList);
					count++;
				}
				List<TReport> remainderList = list.subList(count * 10000, list.size());
				mapper.insertReportData(remainderList);
			}
			if (flag) {
				result.setResultCode(0);
				result.setResultMessage("批量添加报告集合成功");
			} else {
				result.setResultCode(-1);
				result.setResultMessage("批量添加报告集合失败");
			}
		} else {
			result.setResultCode(-1);
			result.setResultMessage("批量添加报告集合为空");
		}
		return result;
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