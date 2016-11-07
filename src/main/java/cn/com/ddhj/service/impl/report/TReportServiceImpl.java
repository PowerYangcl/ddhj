package cn.com.ddhj.service.impl.report;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.dto.TLandedPropertyDto;
import cn.com.ddhj.dto.report.TReportDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.ITAreaNoiseMapper;
import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.mapper.TWaterEnviromentMapper;
import cn.com.ddhj.mapper.report.TReportEnvironmentLevelMapper;
import cn.com.ddhj.mapper.report.TReportLogMapper;
import cn.com.ddhj.mapper.report.TReportMapper;
import cn.com.ddhj.mapper.report.TReportTemplateMapper;
import cn.com.ddhj.mapper.user.TUserLoginMapper;
import cn.com.ddhj.mapper.user.TUserLpFollowMapper;
import cn.com.ddhj.mapper.user.TUserMapper;
import cn.com.ddhj.model.TAreaNoise;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.model.TWaterEnviroment;
import cn.com.ddhj.model.report.TReport;
import cn.com.ddhj.model.report.TReportEnvironmentLevel;
import cn.com.ddhj.model.report.TReportLog;
import cn.com.ddhj.model.report.TReportTemplate;
import cn.com.ddhj.model.system.SysUser;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.model.user.TUserLogin;
import cn.com.ddhj.model.user.TUserLpFollow;
import cn.com.ddhj.result.report.PDFReportResult;
import cn.com.ddhj.result.report.TReportDataResult;
import cn.com.ddhj.result.report.TReportLResult;
import cn.com.ddhj.result.report.TReportSelResult;
import cn.com.ddhj.service.ICityAirService;
import cn.com.ddhj.service.ITChemicalPlantService;
import cn.com.ddhj.service.ITRubbishRecyclingService;
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
	private ITRubbishRecyclingService rubbishService;
	@Autowired
	private TUserLpFollowMapper followMapper;
	@Autowired
	private TUserLoginMapper loginMapper;
	@Autowired
	private TUserMapper userMapper;
	@Autowired
	private ITAreaNoiseMapper noiseMapper;
	@Autowired
	private TReportLogMapper rLogMapper;
	@Autowired
	private ITChemicalPlantService chemicalService;
	@Autowired
	private TWaterEnviromentMapper waterEnvMapper;

	/**
	 * 
	 * 方法: getReportData <br>
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
				result.setDetail(lp.getOverview());
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
	 * 
	 * @param entity
	 * @return
	 * @see cn.com.ddhj.service.report.ITReportService#insert(cn.com.ddhj.model.report.TReport)
	 */
	@Override
	public BaseResult insert(TReport entity, String path) {
		BaseResult result = new BaseResult();
		String code = WebHelper.getInstance().getUniqueCode("R");
		PDFReportResult pdfResult = createPDF(code, entity.getHousesCode(), path, null);
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
		PDFReportResult pdfResult = this.createPDF(entity.getCode(), entity.getHousesCode(), path, null);
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
			List<String> housesCodes = this.getLpCodes();
			List<TReport> list = new ArrayList<TReport>();
			/**
			 * 如果楼盘编码不在限制楼盘列表中，获取所有报告，如果存在，只获取普通的环境报告
			 */
			if (housesCodes.contains(report.getHousesCode())) {
				List<TReport> reports = mapper.findReportByHousesCode(report.getHousesCode());
				for (TReport r : reports) {
					if ("RL161006100001".equals(r.getLevelCode())) {
						list.add(r);
					}
				}
			} else {
				list = mapper.findReportByHousesCode(report.getHousesCode());
			}
			result.setLevelList(list);
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
	 * 
	 * @param list
	 * @return
	 * @see cn.com.ddhj.service.report.ITReportService#insertReportData(java.util.List)
	 */
	@Override
	public BaseResult insertReportData(List<TReport> list) {
		BaseResult result = new BaseResult();
		if (list != null && list.size() > 0) {
			// 生成pdf环境报告
			for (int i = 0; i < list.size(); i++) {
				PDFReportResult pdfResult = createPDF(list.get(i).getCode(), list.get(i).getHousesCode(), "E:/",
						this.getCityAirLevel());
				if (pdfResult.getResultCode() == 0) {
					list.get(i).setPath(pdfResult.getPath());
				}
			}
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

	public PDFReportResult createPDF(String code, String housesCode, String path, JSONArray cityAir) {
		PDFReportResult result = new PDFReportResult();
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			// 根据code获取地产楼盘信息
			TLandedProperty lp = lpMapper.selectByCode(housesCode);
			List<TReportTemplate> templateList = templateMapper.findReportTemplateAll();
			List<TReportEnvironmentLevel> levelList = levelMapper.findTReportEnvironmentLevelAll();
			// 获取绿地率等级
			int afforestLevel = 1;
			if (StringUtils.isNotBlank(lp.getGreeningRate())) {
				try {
					Double afforest = Double
							.valueOf(lp.getGreeningRate().substring(0, lp.getGreeningRate().indexOf("%")));
					if (afforest > 25 && afforest < 30) {
						afforestLevel = 2;
					} else if (afforest < 25) {
						afforestLevel = 3;
					}
				} catch (Exception e) {
					afforestLevel = 1;
				}
			}
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
			if (StringUtils.isNotBlank(lp.getCity())) {
				JSONArray cityAirLevel = null;
				if (cityAir != null && cityAir.size() > 0) {
					cityAirLevel = cityAir;
				} else {
					cityAirLevel = this.getCityAirLevel();
				}
				if (cityAirLevel != null && cityAirLevel.size() > 0) {
					for (int i = 0; i < cityAirLevel.size(); i++) {
						JSONObject level = cityAirLevel.getJSONObject(i);
						if (StringUtils.equals(lp.getCity(), level.getString("city"))) {
							airLevel = level.getJSONObject("level").getInteger("air");
						}
					}
				}
			}
			// 水质量等级
			Integer waterLevel = 2;
			if (StringUtils.isNoneBlank(lp.getCity())) {
				waterLevel = this.waterEnv(lp);
			}

			// 垃圾设施等级
			Integer rubbishLevel = 1;
			if (StringUtils.isNotBlank(lp.getCity()) && StringUtils.isNotBlank(lp.getLat())
					&& StringUtils.isNotBlank(lp.getLng())) {
				rubbishLevel = rubbishService.getRubbishLevel(lp.getCity(), lp.getLat(), lp.getLng());
			}
			// 化工厂
			Integer chemicalLevel = 1;
			if (StringUtils.isNotBlank(lp.getCity()) && StringUtils.isNotBlank(lp.getLat())
					&& StringUtils.isNotBlank(lp.getLng())) {
				chemicalLevel = chemicalService.chemicalLevel(lp.getCity(), lp.getLat(), lp.getLng());
			}
			// 噪音等级
			int nosieLevel = getNoiseLevel(lp);
			if (templateList != null && templateList.size() > 0) {
				JSONArray array = new JSONArray();
				for (int i = 0; i < templateList.size(); i++) {
					TReportTemplate model = templateList.get(i);
					JSONObject obj = new JSONObject();
					obj.put("title", model.getName());
					obj.put("pic", model.getPic());
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
					} else if ("noise".equals(model.getType())) {
						obj.put("level", getLevelContent(model.getType(), nosieLevel, levelList));
					} else if ("chemical".equals(model.getType())) {
						obj.put("level", getLevelContent(model.getType(), chemicalLevel, levelList));
					} else {
						obj.put("level", getLevelContent(model.getType(), 1, levelList));
					}
					array.add(obj);
				}
				String levelName = mapper.findLevel(code);
				path = PdfUtil.instance().createPDF(lp.getTitle(), levelName, array, path, code);
				result.setResultCode(0);
				result.setResultMessage("创建报告成功");
				result.setPath(path);
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

	@Override
	public TReportSelResult getTReportByLp(String lpCode, String userTocken) {
		TReportSelResult result = new TReportSelResult();
		TLandedProperty lp = lpMapper.selectByCode(lpCode);
		if (lp != null) {
			// 如果楼盘不为空
			List<TReport> list = mapper.findReportByHousesCode(lpCode);
			List<TReport> reports = new ArrayList<TReport>();
			if (this.getLpCodes().contains(lpCode)) {
				for (TReport r : list) {
					if ("RL161006100001".equals(r.getLevelCode())) {
						reports.add(r);
					}
				}
			} else {
				reports = mapper.findReportByHousesCode(lpCode);
			}

			// 如果userTocken不为空，查询楼盘是否已关注
			int isFollow = 0;
			if (StringUtils.isNotBlank(userTocken)) {
				TUserLogin login = loginMapper.findLoginByUuid(userTocken);
				if (login != null) {
					TUser user = userMapper.findTUserByUuid(login.getUserToken());
					if (user != null) {
						TUserLpFollow dto = new TUserLpFollow();
						dto.setLpCode(lpCode);
						dto.setUserCode(user.getUserCode());
						TUserLpFollow follow = followMapper.findFollowIsExists(dto);
						if (follow != null) {
							isFollow = 1;
						}
					}
				}
			}
			result.setIsFollow(isFollow);
			result.setLevelList(reports);
			result.setAddress(lp.getAddressFull());
			result.setDetail(lp.getOverview());
			result.setImage(lp.getImages());
			result.setPic(lp.getImages());
			result.setName(lp.getTitle());
			result.setResultCode(0);
			result.setResultMessage("查询报告成功");
		} else {
			result.setResultCode(-1);
			result.setResultMessage("楼盘数据不存在");
		}
		return result;
	}

	/**
	 * 
	 * 方法: getPageData <br>
	 * 
	 * @param dto
	 * @return
	 * @see cn.com.ddhj.service.report.ITReportService#getPageData(cn.com.ddhj.dto.report.TReportDto)
	 */
	@Override
	public TReportDataResult getPageData(TReportDto dto) {
		TReportDataResult result = new TReportDataResult();
		PageHelper.startPage(dto.getPageIndex(), dto.getPageSize());
		List<Map<String, String>> list = mapper.findReportDataAll(dto);
		if (list != null && list.size() > 0) {
			result.setResultCode(0);
		} else {
			list = new ArrayList<Map<String, String>>();
			result.setResultCode(-1);
			result.setResultMessage("查询环境报告列表为空");
		}
		PageInfo<Map<String, String>> page = new PageInfo<Map<String, String>>(list);
		result.setPage(page);
		return result;
	}

	/**
	 * 
	 * 方法: createReport <br>
	 * 
	 * @param dto
	 * @return
	 * @see cn.com.ddhj.service.report.ITReportService#createReport(cn.com.ddhj.dto.report.TReportDto)
	 */
	@Override
	public BaseResult createReport(TReportDto dto, String path, SysUser user) {
		BaseResult result = new BaseResult();
		// 查询报告是否已存在,获取报告的
		List<String> lpCodes = new ArrayList<String>();
		lpCodes.add(dto.getLpCode());
		TReport report = mapper.findReportByLpCodeAndLevelCode(lpCodes);
		if (report != null) {
			// 如果存在，根据等级生成新的环境报告
			PDFReportResult createResult = createPDF(report.getCode(), dto.getLpCode(), path, null);
			result.setResultCode(createResult.getResultCode());
			result.setResultMessage(createResult.getResultMessage());
			report.setUpdateTime(DateUtil.getSysDateTime());
			report.setUpdateUser(user != null ? user.getCode() : "system");
			mapper.updateByCode(report);
		} else {
			result.setResultCode(1);
			result.setResultMessage("报告未创建");
		}
		return result;
	}

	@Override
	public BaseResult insertSelective(TReport entity, String path) {
		BaseResult result = new BaseResult();
		if (!StringUtils.isNotBlank(entity.getHousesCode())) {
			result.setResultCode(-1);
			result.setResultMessage("楼盘不能为空");
		} else {
			String code = WebHelper.getInstance().getUniqueCode("R");
			PDFReportResult createResult = createPDF(code, entity.getHousesCode(), path, null);
			if (createResult.getResultCode() == 0) {
				entity.setCode(code);
				entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
				entity.setPath(createResult.getPath());
				entity.setCreateTime(DateUtil.getSysDateTime());
				entity.setUpdateUser(entity.getCreateUser());
				entity.setUpdateTime(entity.getCreateTime());
				int flag = mapper.insertSelective(entity);
				if (flag > 0) {
					result.setResultCode(0);
					result.setResultMessage("创建报告成功");
				} else {
					result.setResultCode(-1);
					result.setResultMessage("失败");
				}
			} else {
				result.setResultCode(createResult.getResultCode());
				result.setResultMessage(createResult.getResultMessage());
			}
		}
		return result;
	}

	/**
	 * 
	 * 方法: batchCreateReport <br>
	 * 
	 * @see cn.com.ddhj.service.report.ITReportService#batchCreateReport()
	 */
	@Override
	public void batchCreateReport() {
		Long start = System.currentTimeMillis();
		String lock = "";
		try {
			lock = WebHelper.getInstance().addLock(10, "batchCreateReport");
			if (StringUtils.isNoneBlank(lock)) {
				// 获取报告列表
				List<TLandedProperty> lpList = lpMapper.findTLandedPropertyAll();
				// 需要添加的报告列表
				List<TReport> insertData = new ArrayList<TReport>();
				// 需要编辑的报告列表
				List<TReport> updateData = new ArrayList<TReport>();
				// 存储日志信息
				List<TReportLog> logData = new ArrayList<TReportLog>();
				List<String> codes = new ArrayList<String>();
				String path = "/opt/ddhj/";
				JSONArray cityAir = this.getCityAirLevel();
				if (lpList != null && lpList.size() > 0) {
					// 根据楼盘列表获取报告列表
					List<TReport> reports = getTreportByLpCodes(lpList);
					for (int i = 0; i < lpList.size(); i++) {
						TLandedProperty lp = lpList.get(i);
						TReportLog log = new TReportLog();// 日志
						TReportDto dto = new TReportDto();
						dto.setLpCode(lp.getCode());
						dto.setLevelCode("RL161006100001");
						// 根据楼盘编码查询报告
						TReport entity = null;
						if (reports != null && reports.size() > 0) {
							for (TReport r : reports) {
								if (StringUtils.equals(r.getHousesCode(), lp.getCode())) {
									entity = r;
									break;
								}
							}
						}
						String date = isSync(entity != null ? entity.getReportDate() : null);
						if (StringUtils.isNotBlank(date)) {
							if (entity != null) {
								codes.add(entity.getCode());
								PDFReportResult result = createPDF(entity.getCode(), lp.getCode(), path, cityAir);
								entity.setPath(result.getPath());
								entity.setReportDate(date);
								entity.setUpdateUser("system");
								entity.setUpdateTime(DateUtil.getSysDateTime());
								updateData.add(entity);
								log.setReportCode(entity.getCode());
								log.setDetail(JSON.toJSONString(result));
							} else {
								String code = WebHelper.getInstance().getUniqueCode("R");
								codes.add(code);
								PDFReportResult result = createPDF(code, lp.getCode(), path, null);
								entity = new TReport();
								entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
								entity.setCode(code);
								entity.setHousesCode(lp.getCode());
								entity.setTitle(lp.getTitle() + "-环境报告-普通");
								entity.setLevelCode("RL161006100001");
								entity.setPic("");
								entity.setImage("");
								entity.setRang(10);
								entity.setPrice(BigDecimal.valueOf(0.01));
								entity.setPath(result.getPath());
								entity.setDetail(lp.getTitle() + "-环境报告说明-普通");
								entity.setCreateUser("system");
								entity.setCreateTime(DateUtil.getSysDateTime());
								entity.setUpdateUser("system");
								entity.setUpdateTime(DateUtil.getSysDateTime());
								insertData.add(entity);
								log.setReportCode(code);
								log.setDetail(JSON.toJSONString(result));
							}
							log.setUuid(UUID.randomUUID().toString().replace("-", ""));
							log.setLpCode(lp.getCode());
							log.setCreateUser("system");
							log.setCreateTime(DateUtil.getSysDateTime());
							logData.add(log);
						}
					}
					// 批量添加日志到日志表
					if (logData != null && logData.size() > 0) {
						// rLogMapper.batchInsertLog(logData);
						subInsertLog(logData, rLogMapper);
					}
					// 批量添加报告到报告表
					if (insertData != null && insertData.size() > 0) {
						subInsertReport(insertData, mapper);
						// mapper.insertReportData(insertData);
					}
					// 批量从临时表同步数据到报告表
					if (updateData != null && updateData.size() > 0) {
						// mapper.batchInsertReportToTmp(updateData);
						subInsertReportTmp(updateData, mapper);
						mapper.importReportFormTmp();
						mapper.delReportTmp();
					}
					// 将临时文件pdf生成带水印的报告
					if (codes != null && codes.size() > 0) {
						for (int i = 0; i < codes.size(); i++) {
							File file = new File(path + "report/temp/" + codes.get(i) + ".pdf");
							if (file.exists()) {
								PdfUtil.instance().createWatermark(path, codes.get(i));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			WebHelper.getInstance().unLock(lock);
		}
		Long end = System.currentTimeMillis();
		System.out.println("定时执行时间为:" + (end - start));
	}

	/**
	 * 
	 * 方法: getCityAirLevel <br>
	 * 描述: 查询楼盘城市列表的空气质量和水质量等级 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月10日 上午10:03:34
	 * 
	 * @return
	 */
	private JSONArray getCityAirLevel() {
		JSONArray array = new JSONArray();
		List<String> citys = lpMapper.findTLandedPropertyCity();
		if (citys != null && citys.size() > 0) {
			for (int i = 0; i < citys.size(); i++) {
				if (StringUtils.isNotBlank(citys.get(i))) {
					int air = cityAirService.getAQILevel(citys.get(i));
					JSONObject obj = new JSONObject();
					obj.put("air", air);
					JSONObject cityLevel = new JSONObject();
					cityLevel.put("city", citys.get(i));
					cityLevel.put("level", obj);
					array.add(cityLevel);
				}
			}
		}
		return array;
	}

	/**
	 * 
	 * 方法: getNoiseLevel <br>
	 * 描述: 获取楼盘的噪音等级 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月15日 下午9:23:44
	 * 
	 * @param lp
	 * @return
	 */
	private Integer getNoiseLevel(TLandedProperty lp) {
		int level = 0;
		if (StringUtils.isNoneBlank(lp.getLat()) || StringUtils.isNoneBlank(lp.getLng())) {
			Double lat = Double.valueOf(lp.getLat());
			Double lng = Double.valueOf(lp.getLng());
			Double nlat = null; // 北纬
			Double slat = null; // 南纬
			Double elng = null; // 东经
			Double wlng = null; // 西经
			List<TAreaNoise> list = noiseMapper.selectByArea(lp.getCity());
			List<TAreaNoise> areaList = new ArrayList<>();
			if (list != null && list.size() > 0) {
				for (TAreaNoise e : list) {
					if (e.getFlag() == 2) {
						if (e.getName().equals("WN")) { // 坐标西北点
							nlat = e.getLat();
							wlng = e.getLng();
						} else if (e.getName().equals("ES")) {// 坐标东南点
							elng = e.getLng();
							slat = e.getLat();
						}
					} else {
						areaList.add(e);
					}
				}
				for (TAreaNoise e : areaList) {
					Double distance = CommonUtil.getDistanceFromLL(lat, lng, e.getLat(), e.getLng());
					if (distance < 2000) {
						if (e.getFlag() == 1) { // e.getLevel().equals("0类")
							level = 1;
						} else if (e.getFlag() == 3) { // e.getLevel().equals("III类")
							level = 3;
						} else if (e.getFlag() == 4) { // IV类 距离机场2000米以内的，4类
							level = 4;
						} else if (e.getFlag() == 5) { // IV类 距离候车站地点2km以内的，4类
							level = 4;
						}
					} else if (distance < 5000 && e.getFlag() == 4) { // 机场5km以内
																		// 4类
						level = 4;
					}
				}
				if (level == 0) {
					try {
						if (lat != null && lng != null) {
							if ((slat < lat && lat < nlat) && (wlng < lng && lng < elng)) { // 五环里
								level = 2;
							} else { // 北京：五环外 |上海：外环外
										// |广州：外环外|天津：外环外|深圳：关外全部划为I类标准
								level = 1;
							}
						}
					} catch (Exception e) {
						level = 1;
					}
				}
			} else {
				level = 1;
			}
		} else {
			level = 1;
		}

		return level;
	}

	/**
	 * 
	 * 方法: getLpCodes <br>
	 * 描述: 获取限制的楼盘编码 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月12日 下午3:06:25
	 * 
	 * @return
	 */
	private List<String> getLpCodes() {
		List<String> codes = new ArrayList<String>();
		double[] r = CommonUtil.getAround(39.9659730000, 116.3325020000, 10 * 1000);
		TLandedPropertyDto dto = new TLandedPropertyDto();
		dto.setMinLat(r[0]);
		dto.setMinLng(r[1]);
		dto.setMaxLat(r[2]);
		dto.setMaxLng(r[3]);
		List<TLandedProperty> list = lpMapper.findLpAllByCoord(dto);
		if (list != null && list.size() > 0) {
			for (TLandedProperty lp : list) {
				codes.add(lp.getCode());
			}
		}
		return codes;
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

	/**
	 * 
	 * 方法: isSync <br>
	 * 描述: 根据报告生成日期与当前日期比较，如果小于当前日期重新生成报告，如果大于不做处理 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月26日 下午8:48:15
	 * 
	 * @param reportDate
	 * @return
	 */
	private static String isSync(String reportDate) {
		String date = "";
		try {
			Calendar a = Calendar.getInstance();
			a.setTime(new Date());
			a.set(Calendar.DAY_OF_MONTH, 1);
			if (StringUtils.isNoneBlank(reportDate)) {
				Date report = DateUtil.strToDate(reportDate);
				int compare = report.compareTo(a.getTime());
				if (compare <= 0) {
					date = DateUtil.dateToString(a.getTime());
				}
			} else {
				date = DateUtil.dateToString(a.getTime());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * 方法: subInsertData <br>
	 * 描述: 分批次添加报告 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月27日 上午2:00:02
	 * 
	 * @param list
	 * @param type
	 */
	private void subInsertReport(List<TReport> list, TReportMapper mapper) {
		int total = list.size();
		int avg = total / 10000;
		if (avg > 0) {
			for (int i = 0; i < avg; i++) {
				List<TReport> sublist = list.subList(i * 10000, (i + 1) * 10000 - 1);
				mapper.insertReportData(sublist);
			}
			list = list.subList(avg * 10000, list.size());
			mapper.insertReportData(list);
		}else{
			mapper.insertReportData(list);
		}
	}

	/**
	 * 
	 * 方法: subInsertReportTmp <br>
	 * 描述: 分批次添加报告到临时表 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月27日 上午2:07:42
	 * 
	 * @param list
	 * @param mapper
	 */
	private void subInsertReportTmp(List<TReport> list, TReportMapper mapper) {
		int total = list.size();
		int avg = total / 10000;
		if (avg > 0) {
			for (int i = 0; i < avg; i++) {
				List<TReport> sublist = list.subList(i * 10000, (i + 1) * 10000 - 1);
				mapper.batchInsertReportToTmp(sublist);
			}
			list = list.subList(avg * 10000, list.size());
			mapper.batchInsertReportToTmp(list);
		} else {
			mapper.batchInsertReportToTmp(list);
		}

	}

	/**
	 * 
	 * 方法: subInsertLog <br>
	 * 描述: 批量添加日志到日志表 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月29日 下午6:33:43
	 * 
	 * @param list
	 * @param mapper
	 */
	private void subInsertLog(List<TReportLog> list, TReportLogMapper mapper) {
		int total = list.size();
		int avg = total / 10000;
		if (avg > 0) {
			for (int i = 0; i < avg; i++) {
				List<TReportLog> sublist = list.subList(i * 10000, (i + 1) * 10000 - 1);
				mapper.batchInsertLog(sublist);
			}
			list = list.subList(avg * 10000, list.size());
			mapper.batchInsertLog(list);
		} else {
			mapper.batchInsertLog(list);
		}
	}

	public List<TReport> getTreportByLpCodes(List<TLandedProperty> list) {
		List<TReport> reports = null;
		List<String> lpCodes = new ArrayList<String>();
		for (TLandedProperty lp : list) {
			lpCodes.add(lp.getCode());
		}
		if (lpCodes != null && lpCodes.size() > 0) {
			reports = mapper.findPriceByCode(lpCodes);
		}
		return reports;
	}

	/**
	 * 
	 * 方法: waterEnv <br>
	 * 描述: 获取水质量环境等级 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年11月6日 上午11:41:08
	 * 
	 * @param position
	 * @param city
	 * @param list
	 * @return
	 */
	private Integer waterEnv(TLandedProperty lp) {
		int level = 2;

		Map<String, String> map = new HashMap<String, String>();
		map.put("北京", "北京");
		map.put("上海", "上海");
		map.put("天津", "天津");
		map.put("广州", "广州");
		map.put("深圳", "广州"); // 广州和深圳的水源地取同一个
		String city_ = map.get(lp.getCity());
		if (StringUtils.isBlank(city_)) { // 不在我定义的城市中则给默认值
			level = 2;
		} else {
			if (StringUtils.isNoneBlank(lp.getLat()) && StringUtils.isNotBlank(lp.getLng())) {
				List<TWaterEnviroment> list = waterEnvMapper.selectByCity(city_);
				if (list != null && list.size() != 0) {
					Double lat = Double.valueOf(lp.getLat());
					Double lng = Double.valueOf(lp.getLng());
					TreeMap<Integer, TWaterEnviroment> map_ = new TreeMap<Integer, TWaterEnviroment>();
					for (TWaterEnviroment e : list) {
						Integer d = CommonUtil.getMeterDistance(lat, lng, Double.valueOf(e.getLat()),
								Double.valueOf(e.getLng()));
						map_.put(d, e);
					}
					TWaterEnviroment w = map_.get(map_.firstKey());
					String oxy = w.getOxygenquality();
					if (StringUtils.isBlank(oxy)) {
						level = 2;
					} else if (oxy.equals("Ⅰ")) {
						level = 1;
					} else if (oxy.equals("-") || oxy.equals("Ⅱ")) {
						level = 1;
					} else if (oxy.equals("Ⅲ")) {
						level = 3;
					} else if (oxy.equals("Ⅳ")) {
						level = 4;
					} else {
						level = 5;
					}
				} else {
					level = 2;
				}
			} else {
				level = 2;
			}
		}
		return level;
	}
}