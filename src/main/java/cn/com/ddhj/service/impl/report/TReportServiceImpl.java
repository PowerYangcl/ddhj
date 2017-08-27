package cn.com.ddhj.service.impl.report;

import java.io.File;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
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
import cn.com.ddhj.dto.TOrderDto;
import cn.com.ddhj.dto.report.TReportDto;
import cn.com.ddhj.helper.PropHelper;
import cn.com.ddhj.helper.ReportHelper;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.ITAreaNoiseMapper;
import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.mapper.TOrderMapper;
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
import cn.com.ddhj.model.TOrder;
import cn.com.ddhj.model.TWaterEnviroment;
import cn.com.ddhj.model.lp.TLpEnvironmentIndex;
import cn.com.ddhj.model.report.TReport;
import cn.com.ddhj.model.report.TReportEnvironmentLevel;
import cn.com.ddhj.model.report.TReportLog;
import cn.com.ddhj.model.report.TReportTemplate;
import cn.com.ddhj.model.system.SysUser;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.model.user.TUserLogin;
import cn.com.ddhj.model.user.TUserLpFollow;
import cn.com.ddhj.result.ReportResult;
import cn.com.ddhj.result.report.CreateReportResult;
import cn.com.ddhj.result.report.TReportDataResult;
import cn.com.ddhj.result.report.TReportLResult;
import cn.com.ddhj.result.report.TReportSelResult;
import cn.com.ddhj.service.ICityAirService;
import cn.com.ddhj.service.ITChemicalPlantService;
import cn.com.ddhj.service.ITRubbishRecyclingService;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.report.ITReportService;
import cn.com.ddhj.util.CommonUtil;
import cn.com.ddhj.util.Constant;
import cn.com.ddhj.util.DateUtil;
import cn.com.ddhj.util.PPTUtil;
import cn.com.ddhj.util.PdfUtil;
import freemarker.template.Configuration;

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
	private TOrderMapper orderMapper;
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
	@Autowired
	private TReportMapper reportMapper;

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
					result.setResultCode(Constant.RESULT_SUCCESS);
					result.setResultMessage("查询楼盘环境报告列表成功");
				} else {
					result.setRepList(new ArrayList<TReport>());
					result.setRepCount(0);
					result.setResultCode(Constant.RESULT_NULL);
					result.setResultMessage("环境报告为空");
				}
			} else {
				result.setResultCode(Constant.RESULT_ERROR);
				result.setResultMessage("查询楼盘不存在");
			}
		} else {
			result.setResultCode(Constant.RESULT_ERROR);
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
		CreateReportResult pdfResult = createPDF(code, entity.getHousesCode(), path, null);
		if (pdfResult.getResultCode() == Constant.RESULT_SUCCESS) {
			entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
			entity.setCode(code);
			entity.setCreateUser("system");
			entity.setUpdateUser("system");
			entity.setCreateTime(DateUtil.getSysDateTime());
			entity.setUpdateTime(DateUtil.getSysDateTime());
			entity.setPath(pdfResult.getPath());
			int flag = mapper.insertSelective(entity);
			if (flag > 0) {
				result.setResultCode(Constant.RESULT_SUCCESS);
				result.setResultMessage("添加成功");
			} else {
				result.setResultCode(Constant.RESULT_ERROR);
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
		CreateReportResult pdfResult = this.createPDF(entity.getCode(), entity.getHousesCode(), path, null);
		if (pdfResult.getResultCode() == Constant.RESULT_SUCCESS) {
			entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
			entity.setUpdateUser("system");
			entity.setUpdateTime(DateUtil.getSysDateTime());
			entity.setPath(pdfResult.getPath());
			int flag = mapper.updateByCode(entity);
			if (flag > 0) {
				result.setResultCode(Constant.RESULT_SUCCESS);
				result.setResultMessage("修改成功");
			} else {
				result.setResultCode(Constant.RESULT_ERROR);
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
			List<String> housesCodes = new ArrayList<String>();
			TLandedProperty lp = lpMapper.selectByCode(report.getHousesCode());
			if (StringUtils.isNotBlank(lp.getLat()) && StringUtils.isNotBlank(lp.getLng())) {
				housesCodes = this.getLpCodes(Double.valueOf(lp.getLat()), Double.valueOf(lp.getLng()), 10 * 1000);
			}
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
			result.setResultCode(Constant.RESULT_SUCCESS);
			result.setResultMessage("查询报告成功");
		} else {
			result.setResultCode(Constant.RESULT_NULL);
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
			// for (int i = 0; i < list.size(); i++) {
			// CreateReportResult createResult =
			// createPDF(list.get(i).getCode(), list.get(i).getHousesCode(),
			// "E:/",
			// this.getCityAirLevel());
			// if (createResult.getResultCode() == Constant.RESULT_SUCCESS) {
			// list.get(i).setPath(createResult.getPath());
			// }
			// }
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
				result.setResultCode(Constant.RESULT_SUCCESS);
				result.setResultMessage("批量添加报告集合成功");
			} else {
				result.setResultCode(Constant.RESULT_ERROR);
				result.setResultMessage("批量添加报告集合失败");
			}
		} else {
			result.setResultCode(Constant.RESULT_NULL);
			result.setResultMessage("批量添加报告集合为空");
		}
		return result;
	}

	public CreateReportResult createPDF(String code, String housesCode, String path, JSONArray cityAir) {
		CreateReportResult result = new CreateReportResult();
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
				Map<String, String> rubbish = rubbishService.getRubbish(lp.getCity(), lp.getLat(), lp.getLng());
				rubbishLevel = Integer.valueOf(rubbish.get("level"));
			}
			// 化工厂
			Integer chemicalLevel = 1;
			if (StringUtils.isNotBlank(lp.getCity()) && StringUtils.isNotBlank(lp.getLat())
					&& StringUtils.isNotBlank(lp.getLng())) {
				Map<String, String> chemical = chemicalService.getChemical(lp.getCity(), lp.getLat(), lp.getLng());
				chemicalLevel = Integer.valueOf(chemical.get("level"));
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
						obj.put("level", getLevelContent(model.getType(), 2, levelList));
					}
					array.add(obj);
				}
				String levelName = mapper.findLevel(code);
				path = PdfUtil.instance().createPDF(lp.getTitle(), levelName, array, path, code);
				result.setResultCode(Constant.RESULT_SUCCESS);
				result.setResultMessage("创建报告成功");
				result.setPath(path);
			} else {
				result.setResultCode(Constant.RESULT_ERROR);
				result.setResultMessage("创建报告失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("创建报告失败");
		}
		return result;
	}

	@Override
	public TReportSelResult getTReportByLp(String lpCode, String userTocken) {
		TReportSelResult result = new TReportSelResult();
		TLandedProperty lp = lpMapper.selectByCode(lpCode);
		if (lp == null) {
			result.setResultCode(Constant.RESULT_NULL);
			result.setResultMessage("楼盘数据不存在");
			return result;
		}

		// 如果楼盘不为空
		// List<TReport> list = mapper.findReportByHousesCode(lpCode);
		// List<TReport> reports = new ArrayList<TReport>();
		// List<String> lpCodes = new ArrayList<String>();
		// if (StringUtils.isNotBlank(lp.getLat()) &&
		// StringUtils.isNotBlank(lp.getLng())) {
		// lpCodes = this.getLpCodes(Double.valueOf(lp.getLat()),
		// Double.valueOf(lp.getLng()), 10 * 1000);
		// }
		// if (lpCodes.contains(lpCode)) {
		// for (TReport r : list) {
		// if ("RL161006100001".equals(r.getLevelCode())) {
		// reports.add(r);
		// }
		// }
		// } else {
		// reports = mapper.findReportByHousesCode(lpCode);
		// }

		List<TReport> del = new ArrayList<TReport>();
		List<TReport> reports = mapper.findReportByHousesCode(lpCode);
		for (TReport r : reports) {
			if (!Constant.REPORT_LEVEL_FULL.equals(r.getLevelCode())) {
				del.add(r);
			}
		}
		reports.removeAll(del);

		// 如果userTocken不为空，查询楼盘是否已关注
		TUser user = null;
		int isFollow = 0;
		if (StringUtils.isNotBlank(userTocken)) {
			TUserLogin login = loginMapper.findLoginByUuid(userTocken);
			if (login != null) {
				user = userMapper.findTUserByUuid(login.getUserToken());
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

		// 检查报告是否需要更新
		if (user != null) {
			if(reports != null && !reports.isEmpty()) {
				TOrderDto orderDto = new TOrderDto();
				for (TReport report : reports) {
					orderDto.setCreateUser(user.getUserCode());
					orderDto.setReportCode(report.getCode());
					List<TOrder> orderList = orderMapper.findOrderByReportCodeAndUserCode(orderDto);
					if (orderList == null || orderList.isEmpty()) {
						//用户未购买该楼盘报告,把报告模板中的报告路径设为空
						report.setPath("");
						continue;
					}
						
					//用户购买过该楼盘报告,检验报告是否要更新并返回用户目录下的报告路径
					for (TOrder order : orderList) {
						// 报告订单已支付
						if (order.getStatus() == 1 || order.getStatus() == 2) {
							// 计算报告购买时间与当前时间差值,大于半年则提示更新
							try {
								Date buyTime = DateUtil.strToDate(order.getUpdateTime());
								Date deadLine = DateUtil.addDays(buyTime, 31 * 6);
								if (new Date().compareTo(deadLine) >= 0) {
									// 报告要更新
									String reportCode = order.getReportCode();
									if (StringUtils.isNotBlank(reportCode)) {
										// report对象中没有address,city,lpcode,
										// position信息
										// 用reportMapper的selectByCode方法左联合查询一下四个lp属性
										TReport reportJoinInfo = reportMapper.selectByCode(reportCode);
										report.getReportUpdate().setAddress(reportJoinInfo.getAddress());
										report.getReportUpdate().setCity(reportJoinInfo.getCity());
										report.getReportUpdate().setLpCode(reportJoinInfo.getHousesCode());
										report.getReportUpdate().setPosition(reportJoinInfo.getPosition());
									}
								}
							} catch (ParseException e) {
								e.printStackTrace();
							}
							String url = PropHelper.getValue("user_report_url") + user.getUserCode()  + "/" + order.getLpCode() + "/full.html";
							if(isConnect(url)) {
								report.setPath(url);
							} else {
								ReportResult rr = ReportHelper.getInstance().createUserReport(order.getLpCode(), user.getUserCode());
								if(rr.getResultCode() == Constant.RESULT_SUCCESS) {
									report.setPath(rr.getUrl());
								} else {
									report.setPath("");
								}
							}
							break;
						} else if (order.getStatus() == 0) {
//							order.setReportFull("");
							report.setPath("");
						}
					}
				}
			}
		} else if (user == null && reports != null && !reports.isEmpty()) {
			//用户未登录把报告模板中的报告路径设为空
			for (TReport treport : reports) {
				treport.setPath("");
			}
		}

		result.setIsFollow(isFollow);
		result.setLevelList(reports);
		result.setAddress(lp.getAddressFull());
		result.setDetail(lp.getOverview());
		result.setImage(lp.getImages());
		result.setPic(lp.getImages());
		result.setName(lp.getTitle());
		result.setResultCode(Constant.RESULT_SUCCESS);
		result.setResultMessage("查询报告成功");

		return result;
	}
	
	public static void main(String[] args) {
		String url = "http://api.sys.ecomapit.com/ddhj/report/user/U161009100001/LP161004113677/full.html";
		TReportServiceImpl imp = new TReportServiceImpl();
		imp.isConnect(url);
	}
	
	private boolean isConnect(String urlStr) {
		boolean result = false;
		int counts = 0;
		if (urlStr == null || urlStr.length() <= 0) {
			return result;
		}
		while (counts < 5) {
			try {
				URL url = new URL(urlStr);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				int state = con.getResponseCode();
				if (state == 200) {
					result = true;
				}
				break;
			} catch (Exception ex) {
				counts++;
				urlStr = null;
				continue;
			}
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
			result.setResultCode(Constant.RESULT_SUCCESS);
		} else {
			list = new ArrayList<Map<String, String>>();
			result.setResultCode(Constant.RESULT_NULL);
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
		// 查询报告是否已存在,获取报告
		List<String> lpCodes = new ArrayList<String>();
		lpCodes.add(dto.getLpCode());
		TReport report = mapper.findReportByLpCodeAndLevelCode(lpCodes);
		if (report != null) {
			// 如果存在，根据等级生成新的环境报告
			CreateReportResult createResult = createPPT(report.getCode(), dto.getLpCode(), null, null);
			result.setResultCode(createResult.getResultCode());
			result.setResultMessage(createResult.getResultMessage());
			report.setUpdateTime(DateUtil.getSysDateTime());
			report.setUpdateUser(user != null ? user.getCode() : "system");
			mapper.updateByCode(report);
		} else {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("报告未创建");
		}
		return result;
	}

	@Override
	public BaseResult insertSelective(TReport entity, String path) {
		BaseResult result = new BaseResult();
		if (!StringUtils.isNotBlank(entity.getHousesCode())) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("楼盘不能为空");
		} else {
			String code = WebHelper.getInstance().getUniqueCode("R");
			CreateReportResult createResult = createPDF(code, entity.getHousesCode(), path, null);
			if (createResult.getResultCode() == Constant.RESULT_SUCCESS) {
				entity.setCode(code);
				entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
				entity.setPath(createResult.getPath());
				entity.setCreateTime(DateUtil.getSysDateTime());
				entity.setUpdateUser(entity.getCreateUser());
				entity.setUpdateTime(entity.getCreateTime());
				int flag = mapper.insertSelective(entity);
				if (flag > 0) {
					result.setResultCode(Constant.RESULT_SUCCESS);
					result.setResultMessage("创建报告成功");
				} else {
					result.setResultCode(Constant.RESULT_ERROR);
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
	@Deprecated
	public int batchCreateReport() {
		int reCode = 1;
		Long start = System.currentTimeMillis();
		String lock = "";
		try {
			lock = WebHelper.getInstance().addLock(10, "batchCreateReport");
			if (StringUtils.isNoneBlank(lock)) {
				// 获取报告列表
				List<TLandedProperty> lpList = lpMapper.findTLandedPropertyAll();
				// 环境等级
				List<TReportEnvironmentLevel> elList = levelMapper.findTReportEnvironmentLevelAll();
				// 需要添加的报告列表
				List<TReport> insertData = new ArrayList<TReport>();
				// 需要编辑的报告列表
				List<TReport> updateData = new ArrayList<TReport>();
				// 存储日志信息
				List<TReportLog> logData = new ArrayList<TReportLog>();
				List<String> codes = new ArrayList<String>();
				// 获取城市空气质量列表

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
								// CreateReportResult result =
								// createPDF(entity.getCode(), lp.getCode(),
								// path, cityAir);
								CreateReportResult result = createPPT(entity.getCode(), lp.getCode(), cityAir, elList);
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
								CreateReportResult result = createPPT(code, lp.getCode(), cityAir, elList);
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
					/**
					 * 批量修改报告的最后生成时间
					 */
					mapper.updateReportTime();
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
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			reCode = 0;
		} finally {
			WebHelper.getInstance().unLock(lock);
		}
		Long end = System.currentTimeMillis();
		getLogger().logInfo("定时执行时间为:" + (end - start));
		return reCode;

	}

	/**
	 * 批量生成H5报告，用于定时任务
	 */
	@Override
	public int batchCreateH5Report() {
		long start = System.currentTimeMillis();
		int reCode = 1;
		String lock = "";
		try {
			lock = WebHelper.getInstance().addLock(10, "batchCreateReport");
			if (StringUtils.isNoneBlank(lock)) {
				// 获取报告列表
				List<TLandedProperty> lpList = lpMapper.findTLandedPropertyAll();
				List<TLandedProperty> reportLps = new ArrayList<TLandedProperty>();
				if (lpList != null && lpList.size() > 0) {
					JSONArray airArray = ReportHelper.getInstance().getCityAirLevel();
					for (TLandedProperty lp : lpList) {
						long lpStart = System.currentTimeMillis();
						if (StringUtils.isNotBlank(lp.getLat())) {
							lp.setWeatherDistribution(ReportHelper.getWeatherDistribution(Float.valueOf(lp.getLat())));
						} else {
							continue;
						}
						List<TLpEnvironmentIndex> list = ReportHelper.getInstance().getLpEnvironmentIndexs(lp,
								airArray);
						if (list != null && list.size() > 0) {
							lp.setEnvironmentIndexs(list);
							lp.setUpdateTime(DateUtil.getCurrentDate());
							reportLps.add(lp);
						} else {
							continue;
						}
						// /*
						// * 精简版
						// */
						// TReport simplification = new TReport();
						// simplification.setHousesCode(lp.getCode());
						// simplification.setLevelCode("RL161006100001");
						// String path =
						// ReportHelper.getInstance().createHtml(lp,
						// "simplification");
						// simplification.setPath(path);
						// String reportDate = DateUtil.getSysDateTime();
						// simplification.setCreateTime(reportDate);
						// /*
						// * 完整版
						// */
						// TReport full = new TReport();
						// full.setHousesCode(lp.getCode());
						// full.setLevelCode("RL161006100002");
						// String fullPath =
						// ReportHelper.getInstance().createHtml(lp, "full");
						// full.setPath(fullPath);
						// String fullReportDate = DateUtil.getSysDateTime();
						// full.setCreateTime(fullReportDate);
						// long lpEnd = System.currentTimeMillis();
						// getLogger().logInfo("获取楼盘信息时间为:" + (lpEnd -
						// lpStart));
					}
					Configuration config = new Configuration(Configuration.VERSION_2_3_23);
					try {
						int size = reportLps.size() / 10000;
						int current = 10000;
						for (int i = 0; i < size; i++) {
							List<TLandedProperty> subList = reportLps.subList(current * i, current * (i + 1));
							new CreateReport(subList, config).run();
						}
						List<TLandedProperty> subList = reportLps.subList(current * size, lpList.size());
						new CreateReport(subList, config).run();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
					}

					/**
					 * 查询用户最新下单购买报告记录<br>
					 * 根据用户报告将指定楼盘报告复制到用户文件下<br>
					 */
//					List<TOrder> orders = orderMapper.findOrderLPAndCreateUser();
//					if (orders != null && orders.size() > 0) {
//						for (TOrder order : orders) {
//							ReportHelper.getInstance().createUserReport(order.getLpCode(), order.getCreateUser());
//						}
//					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			reCode = 0;
		} finally {
			WebHelper.getInstance().unLock(lock);
		}
		Long end = System.currentTimeMillis();
		getLogger().logInfo("定时执行时间为:" + (end - start));
		return reCode;
	}

	/**
	 * 
	 * 方法: createPPT <br>
	 * 描述: 创建ppt格式环境报告 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年2月26日 上午12:29:32
	 * 
	 * @param code
	 * @param lpCode
	 * @param cityAir
	 * @return
	 */
	@Override
	public CreateReportResult createPPT(String code, String lpCode, JSONArray cityAir,
			List<TReportEnvironmentLevel> list) {
		if (cityAir == null) {
			cityAir = this.getCityAirLevel();
		}
		CreateReportResult result = new CreateReportResult();
		if (list == null) {
			list = levelMapper.findTReportEnvironmentLevelAll();
		}
		try {
			Map<String, String> map = getReportParam(code, lpCode, cityAir, list);
			String path = PPTUtil.instance().createReport(map, code);
			System.out.println(lpCode + " generate success. path:" + path);
			getLogger().logDebug(lpCode + "generate success. path:" + path);
			if (StringUtils.isNotBlank(path)) {
				result.setResultCode(Constant.RESULT_SUCCESS);
				result.setResultMessage("生成报告成功");
				result.setPath(path);
			} else {
				result.setResultCode(Constant.RESULT_ERROR);
				result.setResultMessage("生成报告错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public BaseResult createHtmlAll() {
		BaseResult result = new BaseResult();
		try {
			List<TLandedProperty> lpList = lpMapper.findTLandedPropertyAll();
			JSONArray airArray = ReportHelper.getInstance().getCityAirLevel();
			String date = DateUtil.getCurrentDate();
			if (lpList != null && lpList.size() > 0) {
				for (TLandedProperty lp : lpList) {
					try {
						lp.setWeatherDistribution(ReportHelper.getWeatherDistribution(Float.valueOf(lp.getLat())));
						List<TLpEnvironmentIndex> list = ReportHelper.getInstance().getLpEnvironmentIndexs(lp,
								airArray);
						lp.setEnvironmentIndexs(list);
						lp.setEnvironmentIndexs1(list.subList(0, 3));
						lp.setEnvironmentIndexs2(list.subList(3, 6));
						lp.setEnvironmentIndexs3(list.subList(6, 9));
						lp.setEnvironmentIndexs4(list.subList(9, list.size()));
						lp.setUpdateTime(date);
						new ReportHelper().createHtml(lp, "full");
						new ReportHelper().createHtml(lp, "simplification");
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
			}
			result.setResultCode(Constant.RESULT_SUCCESS);
			result.setResultMessage("创建H5报告成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("创建H5报告失败，失败原因：" + e.getMessage());
		}
		return result;
	}

	@Override
	public ReportResult createHtmlByLpCode(String lpCode, String type) {
		ReportResult result = new ReportResult();
		try {
			TLandedProperty lp = lpMapper.selectByCode(lpCode);
			if (lp != null) {
				JSONArray airArray = ReportHelper.getInstance().getCityAirLevel();
				lp.setWeatherDistribution(ReportHelper.getWeatherDistribution(Float.valueOf(lp.getLat())));
				List<TLpEnvironmentIndex> list = ReportHelper.getInstance().getLpEnvironmentIndexs(lp, airArray);
				lp.setEnvironmentIndexs(list);
				lp.setEnvironmentIndexs1(list.subList(0, 3));
				lp.setEnvironmentIndexs2(list.subList(3, 6));
				lp.setEnvironmentIndexs3(list.subList(6, 9));
				lp.setEnvironmentIndexs4(list.subList(9, list.size()));
				String url = new ReportHelper().createHtml(lp, type);
				result.setResultCode(Constant.RESULT_SUCCESS);
				result.setResultMessage("生成htm报告成功");
				result.setUrl(url);
			} else {
				result.setResultCode(Constant.RESULT_ERROR);
				result.setResultMessage("楼盘不存在");
			}
		} catch (Exception e) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("生成html报告失败，失败原因：" + e.getMessage());
			e.printStackTrace();
		}
		return result;
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
					array.add(cityAirService.getAQILevel(citys.get(i)));
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
		int level = 1;
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
							level = 3;
						} else if (e.getFlag() == 5) { // IV类 距离候车站地点2km以内的，4类
							level = 3;
						}
					} else if (distance < 5000 && e.getFlag() == 4) { // 机场5km以内
																		// 4类
						level = 3;
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
	private List<String> getLpCodes(double lat, double lng, int raidus) {
		List<String> codes = new ArrayList<String>();
		double[] r = CommonUtil.getAround(lat, lng, raidus);
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
		int avg = total / 5000;
		if (avg > 0) {
			for (int i = 0; i < avg; i++) {
				List<TReport> sublist = list.subList(i * 5000, (i + 1) * 5000 - 1);
				mapper.insertReportData(sublist);
			}
			list = list.subList(avg * 5000, list.size());
			mapper.insertReportData(list);
		} else {
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
		int avg = total / 5000;
		if (avg > 0) {
			for (int i = 0; i < avg; i++) {
				List<TReport> sublist = list.subList(i * 5000, (i + 1) * 5000 - 1);
				mapper.batchInsertReportToTmp(sublist);
			}
			list = list.subList(avg * 5000, list.size());
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
		int avg = total / 5000;
		if (avg > 0) {
			for (int i = 0; i < avg; i++) {
				List<TReportLog> sublist = list.subList(i * 5000, (i + 1) * 5000 - 1);
				mapper.batchInsertLog(sublist);
			}
			list = list.subList(avg * 5000, list.size());
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
						level = 2;
					} else if (oxy.equals("Ⅳ")) {
						level = 3;
					} else {
						level = 3;
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

	private Map<String, String> getReportParam(String reportCode, String lpCode, JSONArray airArray,
			List<TReportEnvironmentLevel> list) {
		Map<String, String> map = new HashMap<String, String>();
		// 根据lpCode获取地产楼盘信息
		TLandedProperty lp = lpMapper.selectByCode(lpCode);
		if (lp != null) {
			map.put("lp.name", lp.getTitle());
			JSONObject air = null;
			if (airArray != null) {
				for (int i = 0; i < airArray.size(); i++) {
					JSONObject obj = airArray.getJSONObject(i);
					if (StringUtils.equals(obj.getString("city"), lp.getCity())) {
						air = obj;
						break;
					}
				}
			}
			// 获取空气AQI指数和等级
			int airLevel = 1;
			if (air != null) {
				airLevel = Integer.valueOf(air.getString("level"));
			}
			map.put("air.level", String.valueOf(airLevel));
			// 空气进度条旁边显示的百分比(一共6级,1级最好)
			double tmp = Double.parseDouble(map.get("air.level"));
			map.put("air.level.percent", String.valueOf((1 + (1 - tmp) / 6.0)));

			// 噪音数值和等级(一共3级,1级最好)
			int noiseLevel = getNoiseLevel(lp);
			map.put("noise.level", String.valueOf(noiseLevel));
			tmp = Double.parseDouble(map.get("noise.level"));
			map.put("noise.level.percent", String.valueOf((1 + (1 - tmp) / 3.0)));

			// 水质等级(一共3级,1级最好)
			int waterLevel = waterEnv(lp);
			map.put("water.level", String.valueOf(waterLevel));
			tmp = Double.parseDouble(map.get("water.level"));
			map.put("water.level.percent", String.valueOf((1 + (1 - tmp) / 3.0)));

			// 土壤等级(一共3级,1级最好)
			int soilLevel = 2;
			map.put("soil.level", String.valueOf(soilLevel));
			tmp = Double.parseDouble(map.get("soil.level"));
			map.put("soil.level.percent", String.valueOf((1 + (1 - tmp) / 3.0)));

			// 污染源和距离(一共3级,1级最好)
			// 垃圾场
			Map<String, String> rubbish = rubbishService.getRubbish(lp.getCity(), lp.getLat(), lp.getLng());
			Double rubbishDistance = BigDecimal.valueOf((Double.valueOf(rubbish.get("distance")) / 1000))
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			String rubbishStr = "垃圾场：等级为" + rubbish.get("level") + "级，距离小区" + rubbishDistance + "公里";
			// 化工厂
			Map<String, String> chemical = chemicalService.getChemical(lp.getCity(), lp.getLat(), lp.getLng());
			String distance = chemical.get("distance") == null ? "10000" : chemical.get("distance");
			Double chemicalDistance = BigDecimal.valueOf((Double.valueOf(distance) / 1000))
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			String chemicalStr = "化工厂：等级为" + chemical.get("level") + "级，距离小区" + chemicalDistance + "公里";
			/**
			 * 垃圾场和化工厂取离小区最近的
			 */
			String sourceOfPollutionType = "";
			Integer sourceOfPollutionLevel = 0;
			if (rubbishDistance.doubleValue() < chemicalDistance.doubleValue()) {
				map.put("sourceOfPollution", rubbishStr);
				map.put("sourceOfPollution.typeName", "垃圾场");
				map.put("sourceOfPollution.distance", rubbishDistance.doubleValue() + "公里");
				sourceOfPollutionType = "rubbish";
				sourceOfPollutionLevel = Integer.valueOf(rubbish.get("level"));
			} else {
				map.put("sourceOfPollution", chemicalStr);
				map.put("sourceOfPollution.typeName", "化工厂");
				map.put("sourceOfPollution.distance", chemicalDistance.doubleValue() + "公里");
				sourceOfPollutionType = "chemical";
				sourceOfPollutionLevel = Integer.valueOf(chemical.get("level"));
			}
			map.put("sourceOfPollution.level", String.valueOf(sourceOfPollutionLevel));
			map.put("sourceOfPollution.type", sourceOfPollutionType);
			tmp = Double.parseDouble(map.get("sourceOfPollution.level"));
			map.put("sourceOfPollution.level.percent", String.valueOf((1 + (1 - tmp) / 3.0)));

			// 辐射源距离
			int sourceOfRadiationLevel = 1;
			map.put("sourceOfRadiation.level", String.valueOf(sourceOfRadiationLevel));
			tmp = Double.parseDouble(map.get("sourceOfRadiation.level"));
			map.put("sourceOfRadiation.level.percent", String.valueOf((1 + (1 - tmp) / 3.0)));

			// 容积率(一共3级,1级最好)
			int volumeLevel = 1;
			try {
				double volumeRate = Double.valueOf(lp.getVolumeRate());
				if (volumeRate > 3 && volumeRate < 5) {
					volumeLevel = 2;
				} else if (volumeRate > 5) {
					volumeLevel = 3;
				}

			} catch (Exception e) {
				volumeLevel = 1;
			}
			map.put("volume.level", String.valueOf(volumeLevel));
			tmp = Double.parseDouble(map.get("volume.level"));
			map.put("volume.level.percent", String.valueOf((1 + (1 - tmp) / 3.0)));

			// 危险品存放等级
			int hazardousArticleLevel = 1;
			map.put("hazardousArticle.level", String.valueOf(hazardousArticleLevel));

			// 获取绿地率数值
			int afforestLevel = 1;
			if (StringUtils.isNotBlank(lp.getGreeningRate())) {
				String greenRateStr = StringUtils.substring(lp.getGreeningRate(), 0, lp.getGreeningRate().indexOf("%"));
				try {
					if (Double.valueOf(greenRateStr) > 25 && Double.valueOf(greenRateStr) < 30) {
						afforestLevel = 2;
					} else if (Double.valueOf(greenRateStr) < 25) {
						afforestLevel = 3;
					}
				} catch (Exception e) {
					afforestLevel = 1;
				}
			}
			map.put("afforest.level", String.valueOf(afforestLevel));
			if (StringUtils.isNotBlank(lp.getGreeningRate())) {
				String percent = StringUtils.substringBefore(lp.getGreeningRate(), "%");
				if (StringUtils.isNoneBlank(percent)) {
					tmp = Double.parseDouble(percent) / 100.0;
					map.put("afforest.level.percent", String.valueOf(tmp));
				}
			}
			if (map.get("afforest.level.percent") == null) {
				tmp = Double.parseDouble(map.get("afforest.level"));
				map.put("afforest.level.percent", String.valueOf((1 + (1 - tmp) / 3.0)));
			}

			/**
			 * 获取环境描述信息
			 */
			for (TReportEnvironmentLevel t : list) {
				if (StringUtils.equals(t.getType(), "noise")) {
					if (noiseLevel == t.getLevel()) {
						/**
						 * 噪音污染数值和描述
						 */
						map.put("noise.index", t.getValue());
						map.put("noise.comment", t.getContent());
					}
				} else if (StringUtils.equals(t.getType(), "water")) {
					if (waterLevel == t.getLevel()) {
						/**
						 * 水质量数值和描述
						 */
						map.put("water.index", t.getValue());
						map.put("water.comment", t.getContent());
					}
				} else if (StringUtils.equals(t.getType(), "air")) {
					if (airLevel == t.getLevel()) {
						/**
						 * 空气质量数值和描述
						 */
						map.put("air.index", t.getValue());
						map.put("air.comment", t.getContent());
					}
				} else if (StringUtils.equals(t.getType(), "soil")) {
					if (soilLevel == t.getLevel()) {
						/**
						 * 土壤数值和面搜
						 */
						map.put("soil.index", t.getValue());
						map.put("soil.comment", t.getContent());
					}
				} else if (StringUtils.equals(t.getType(), "afforest")) {
					if (afforestLevel == t.getLevel()) {
						/**
						 * 绿化率数值和描述
						 */
						map.put("afforest.index", t.getValue());
						map.put("afforest.comment", t.getContent());
					}
				} else if (StringUtils.equals(t.getType(), "volume")) {
					if (volumeLevel == t.getLevel()) {
						/**
						 * 容积率数值和描述
						 */
						map.put("volume.index", t.getValue());
						map.put("volume.comment", t.getContent());
					}
				} else if (StringUtils.equals(t.getType(), sourceOfPollutionType)) {
					/**
					 * 污染源
					 */
					if (sourceOfPollutionLevel == t.getLevel()) {
						map.put("sourceOfPollution.index", t.getValue());
						map.put("sourceOfPollution.comment", t.getContent());
					}
				} else if (StringUtils.equals(t.getType(), "hazardousArticle")) {
					if (hazardousArticleLevel == t.getLevel()) {
						/**
						 * 危险品存放数值和描述
						 */
						map.put("hazardousArticle.index", t.getValue());
						map.put("hazardousArticle.comment", t.getContent());
					}
				} else if (StringUtils.equals(t.getType(), "sourceOfRadiation")) {
					/**
					 * 辐射源数值和描述
					 */
					if (sourceOfRadiationLevel == t.getLevel()) {
						map.put("sourceOfRadiation.index", t.getValue());
						map.put("sourceOfRadiation.comment", t.getContent());
					}
				}
			}
			/**
			 * 获取一定范围内环境综合评分小于当前楼盘的总数<br>
			 * 2017-02-28 zhy
			 */
			int count = 0;
			if (StringUtils.isNotBlank(lp.getLat()) && StringUtils.isNotBlank(lp.getLng())) {
				double[] r = CommonUtil.getAround(Double.valueOf(lp.getLat()), Double.valueOf(lp.getLng()), 2000);
				TLandedPropertyDto dto = new TLandedPropertyDto();
				dto.setMinLat(r[0]);
				dto.setMinLng(r[1]);
				dto.setMaxLat(r[2]);
				dto.setMaxLng(r[3]);
				count = lpMapper.findScoreLessThanLpCount(dto);
			}
			map.put("lp.lesscount", String.valueOf(count));
		}
		return map;
	}
}