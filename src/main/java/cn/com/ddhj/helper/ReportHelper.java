package cn.com.ddhj.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.annotation.Inject;
import cn.com.ddhj.base.BaseClass;
import cn.com.ddhj.mapper.ITAreaNoiseMapper;
import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.mapper.TWaterEnviromentMapper;
import cn.com.ddhj.mapper.lp.TLpEnvironmentMapper;
import cn.com.ddhj.model.TAreaNoise;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.model.TWaterEnviroment;
import cn.com.ddhj.model.lp.TLpEnvironment;
import cn.com.ddhj.model.lp.TLpEnvironmentIndex;
import cn.com.ddhj.service.ICityAirService;
import cn.com.ddhj.service.ITChemicalPlantService;
import cn.com.ddhj.service.ITRubbishRecyclingService;
import cn.com.ddhj.util.CommonUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class ReportHelper extends BaseClass {

	@Inject
	private TLandedPropertyMapper lpMapper;
	@Inject
	private ICityAirService cityAirService;
	@Inject
	private ITChemicalPlantService chemicalService;
	@Inject
	private TWaterEnviromentMapper waterEnvMapper;
	@Inject
	private ITAreaNoiseMapper noiseMapper;
	@Inject
	private ITRubbishRecyclingService rubbishService;
	@Inject
	private TLpEnvironmentMapper lpEnvMapper;

	private static ReportHelper self;

	public static ReportHelper getInstance() {
		if (self == null) {
			synchronized (ReportHelper.class) {
				if (self == null)
					self = new ReportHelper();
			}
		}
		return self;
	}

	/**
	 * 根据纬度判断气候类型
	 * 
	 * @param lat
	 * @return
	 */
	public static String getWeatherDistribution(Float lat) {
		String val = "";
		if (lat > 35) {
			val = "华北、东北区域（既北京，河北，天津，青岛，大连等城市）为温带季风气候，夏季夏季高温多雨，冬季寒冷干燥。";
		} else if (25 < lat && lat < 35) {
			val = "华东、华南等区域（既上海、广州、深圳、武汉、长沙，重庆，成都等城市）为亚热带季风气候，夏季高温多雨,冬季低温少雨。";
		} else if ((lat >= 30 && lat <= 40) || (lat >= 30 && lat <= 50)) {
			val = "(内蒙古及新疆区域）为温带大陆性气候，冬季寒冷，夏季炎热．终年干旱少雨，降水相对集中于夏季。";
		} else if ((lat >= 10 && lat <= 23.5) || (lat >= 10 && lat <= 23.26)) {
			val = "台湾、海南）为热带季风气候，全年高温，降水季节差异很大，分干季和雨季）。";
		} else {
			val = "(西北区域如甘肃、西藏等）为高原山地气候，海拔高，气温低，但辐射强，日照丰富，降水少，冬半年风力强劲。气温的年较差小，日差较大。";
		}
		return val;
	}

	/**
	 * 获取绿地率等级
	 * 
	 * @param greeningRate
	 * @return
	 */
	public TLpEnvironmentIndex afforestLevel(Double afforest, String city) {
		TLpEnvironmentIndex entity = new TLpEnvironmentIndex();
		entity.setCity(city);
		Integer level = 2;
		String levelName = "良";
		if (afforest != null) {
			entity.setValue(afforest);
			if (afforest > 30) {
				level = 1;
				levelName = "优";
			} else if (afforest > 25 && afforest < 30) {
				level = 2;
				levelName = "良";
			} else if (afforest < 25) {
				level = 3;
				levelName = "差";
			}
		}
		entity.setName("绿地率");
		entity.setLevel(level);
		entity.setLevelName(levelName);
		String evaluate = PropHelper.getValue("afforest_level_" + level);
		entity.setEvaluate(StringUtils.isNotBlank(evaluate) ? evaluate : "");
		/**
		 * 获取楼盘排行
		 */
		if (StringUtils.isNotBlank(city)) {
			entity.setSortTime(PropHelper.getValue("report_sort_time_value"));
			TLpEnvironment lpEnv = new TLpEnvironment();
			lpEnv.setCity(city);
			lpEnv.setAfforest(BigDecimal.valueOf(afforest));
			lpEnv.setSortTime(Integer.parseInt(PropHelper.getValue("report_sort_time_key")));
			Integer sort = lpEnvMapper.findEnvironmentSort(lpEnv);
			if (sort != null) {
				entity.setSort(sort + 1);
			}
		}
		return entity;
	}

	/**
	 * 获取容积率等级
	 * 
	 * @param volumeRate
	 * @return
	 */
	public TLpEnvironmentIndex volumeLevel(Double volume, String city) {
		TLpEnvironmentIndex entity = new TLpEnvironmentIndex();
		entity.setCity(city);
		Integer level = 2;
		String levelName = "良";
		if (volume != null) {
			try {
				entity.setValue(volume);
				if (volume < 1) {
					level = 1;
					levelName = "优";
				} else if (volume > 3 && volume < 5) {
					level = 2;
					levelName = "良";
				} else if (volume > 5) {
					level = 3;
					levelName = "差";
				}
			} catch (Exception e) {
				entity.setValue(Double.valueOf(1));
				level = 2;
				levelName = "良";
			}
		}
		entity.setName("容积率");
		entity.setLevel(level);
		entity.setLevelName(levelName);
		String evaluate = PropHelper.getValue("volume_level_" + level);
		entity.setEvaluate(StringUtils.isNotBlank(evaluate) ? evaluate : "");
		/**
		 * 获取楼盘排行
		 */
		if (StringUtils.isNotBlank(city)) {
			entity.setSortTime(PropHelper.getValue("report_sort_time_value"));
			TLpEnvironment lpEnv = new TLpEnvironment();
			lpEnv.setCity(city);
			lpEnv.setVolume(BigDecimal.valueOf(volume));
			lpEnv.setSortTime(Integer.parseInt(PropHelper.getValue("report_sort_time_key")));
			Integer sort = lpEnvMapper.findEnvironmentSort(lpEnv);
			if (sort != null) {
				entity.setSort(sort + 1);
			}
		}
		return entity;
	}

	/**
	 * 
	 * 方法: noiseLevel <br>
	 * 描述: 获取楼盘的噪音等级 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月15日 下午9:23:44
	 * 
	 * @param lp
	 * @return
	 */
	public TLpEnvironmentIndex noiseLevel(TLandedProperty lp, Double distance) {
		TLpEnvironmentIndex entity = new TLpEnvironmentIndex();
		entity.setCity(lp.getCity());
		Integer level = 2;
		String levelName = "良";
		Double value = Double.valueOf("0.00");
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
					Double distance_new = CommonUtil.getDistanceFromLL(lat, lng, e.getLat(), e.getLng());
					if (distance < distance_new) {
						continue;
					} else {
						distance = distance_new;
					}
					if (distance < 2000) {
						if (e.getFlag() == 1) { // e.getLevel().equals("0类")
							level = 1;
							levelName = "优";
						} else if (e.getFlag() == 3) { // e.getLevel().equals("III类")
							level = 3;
							levelName = "差";
						} else if (e.getFlag() == 4) { // IV类 距离机场2000米以内的，4类
							level = 3;
							levelName = "差";
						} else if (e.getFlag() == 5) { // IV类 距离候车站地点2km以内的，4类
							level = 3;
							levelName = "差";
						}
					} else if (distance < 5000 && e.getFlag() == 4) { // 机场5km以内
						level = 3;
						levelName = "差";
					}
				}
				if (level == 0) {
					try {
						if (lat != null && lng != null) {
							if ((slat < lat && lat < nlat) && (wlng < lng && lng < elng)) { // 五环里
								level = 2;
								levelName = "良";
								value = Double.valueOf(60);
							} else { // 北京：五环外 |上海：外环外
										// |广州：外环外|天津：外环外|深圳：关外全部划为I类标准
								level = 1;
								levelName = "优";
								value = Double.valueOf(45);
							}
						}
					} catch (Exception e) {
						level = 2;
						levelName = "良";
						value = Double.valueOf(60);
					}
				}
			} else {
				level = 1;
			}
		} else {
			level = 1;
		}
		if (level == 1 && value == null) {
			value = Double.valueOf(45);
		}
		entity.setName("噪音");
		entity.setValue(value);
		entity.setLevel(level);
		entity.setLevelName(levelName);
		String evaluate = PropHelper.getValue("volume_level_" + level);
		entity.setEvaluate(StringUtils.isNotBlank(evaluate) ? evaluate : "");
		/**
		 * 获取楼盘排行
		 */
		if (lp != null && StringUtils.isNotBlank(lp.getCity())) {
			entity.setSortTime(PropHelper.getValue("report_sort_time_value"));
			TLpEnvironment lpEnv = new TLpEnvironment();
			lpEnv.setCity(lp.getCity());
			lpEnv.setNosie(BigDecimal.valueOf(value));
			lpEnv.setSortTime(Integer.parseInt(PropHelper.getValue("report_sort_time_key")));
			Integer sort = lpEnvMapper.findEnvironmentSort(lpEnv);
			if (sort != null) {
				entity.setSort(sort + 1);
			}
		}
		return entity;
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
	public TLpEnvironmentIndex waterLevel(TLandedProperty lp, Double oxy) {
		TLpEnvironmentIndex entity = new TLpEnvironmentIndex();
		entity.setCity(lp.getCity());
		Integer level = 2;
		String levelName = "良";
		Double value = Double.valueOf("0.00");
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
					if (oxy == null) {
						level = 2;
						levelName = "良";
					} else {
						if (oxy.equals("Ⅰ")) {
							level = 1;
							levelName = "优";
						} else if (oxy.equals("-") || oxy.equals("Ⅱ")) {
							level = 1;
							levelName = "优";
						} else if (oxy.equals("Ⅲ")) {
							level = 2;
							levelName = "良";
						} else if (oxy.equals("Ⅳ")) {
							level = 3;
							levelName = "差";
						} else {
							level = 3;
							levelName = "差";
						}
						value = oxy;
					}
				} else {
					level = 2;
					levelName = "良";
				}
			} else {
				level = 2;
				levelName = "良";
			}
		}
		entity.setName("水质");
		entity.setLevel(level);
		entity.setLevelName(levelName);
		entity.setValue(value);
		String evaluate = PropHelper.getValue("water_level_" + level);
		entity.setEvaluate(StringUtils.isNotBlank(evaluate) ? evaluate : "");
		/**
		 * 获取楼盘排行
		 */
		if (lp != null && StringUtils.isNotBlank(lp.getCity())) {
			entity.setSortTime(PropHelper.getValue("report_sort_time_value"));
			TLpEnvironment lpEnv = new TLpEnvironment();
			lpEnv.setCity(lp.getCity());
			lpEnv.setWater(BigDecimal.valueOf(value));
			lpEnv.setSortTime(Integer.parseInt(PropHelper.getValue("report_sort_time_key")));
			Integer sort = lpEnvMapper.findEnvironmentSort(lpEnv);
			if (sort != null) {
				entity.setSort(sort + 1);
			}
		}
		return entity;
	}

	/**
	 * 土壤
	 * 
	 * @param soil
	 * @return
	 */
	public TLpEnvironmentIndex soilLevel(Double soil, String city) {
		TLpEnvironmentIndex entity = new TLpEnvironmentIndex();
		entity.setCity(city);
		String levelName = "良";
		entity.setName("土壤");
		entity.setValue(Double.valueOf("0.00"));
		entity.setLevel(2);
		entity.setLevelName(levelName);
		String evaluate = PropHelper.getValue("soil_level_" + 2);
		entity.setEvaluate(StringUtils.isNotBlank(evaluate) ? evaluate : "");
		/**
		 * 获取楼盘排行
		 */
		if (StringUtils.isNotBlank(city)) {
			entity.setSortTime(PropHelper.getValue("report_sort_time_value"));
			TLpEnvironment lpEnv = new TLpEnvironment();
			lpEnv.setCity(city);
			lpEnv.setSoil(BigDecimal.valueOf(soil));
			lpEnv.setSortTime(Integer.parseInt(PropHelper.getValue("report_sort_time_key")));
			Integer sort = lpEnvMapper.findEnvironmentSort(lpEnv);
			if (sort != null) {
				entity.setSort(sort + 1);
			}
		}
		return entity;
	}

	/**
	 * 危险品存放
	 * 
	 * @param hazardousArticle
	 * @return
	 */
	public TLpEnvironmentIndex hazardousArticleLevel(Double hazardousArticle, String city) {
		TLpEnvironmentIndex entity = new TLpEnvironmentIndex();
		entity.setCity(city);
		entity.setName("危险品存放");
		entity.setLevel(2);
		entity.setValue(Double.valueOf("0.00"));
		String levelName = "良";
		entity.setLevelName(levelName);
		String evaluate = PropHelper.getValue("hazardous_article_level_" + 2);
		entity.setEvaluate(StringUtils.isNotBlank(evaluate) ? evaluate : "");
		/**
		 * 获取楼盘排行
		 */
		if (StringUtils.isNotBlank(city)) {
			entity.setSortTime(PropHelper.getValue("report_sort_time_value"));
			TLpEnvironment lpEnv = new TLpEnvironment();
			lpEnv.setCity(city);
			lpEnv.setHazardousArticle(BigDecimal.valueOf(hazardousArticle));
			lpEnv.setSortTime(Integer.parseInt(PropHelper.getValue("report_sort_time_key")));
			Integer sort = lpEnvMapper.findEnvironmentSort(lpEnv);
			if (sort != null) {
				entity.setSort(sort + 1);
			}
		}
		return entity;
	}

	/**
	 * 高压辐射
	 */
	public TLpEnvironmentIndex radiationLevel(Double radiation, String city) {
		TLpEnvironmentIndex entity = new TLpEnvironmentIndex();
		entity.setCity(city);
		entity.setName("高压电辐射");
		entity.setLevel(2);
		String levelName = "良";
		entity.setLevelName(levelName);
		entity.setValue(radiation);
		String evaluate = PropHelper.getValue("radiation_level_" + 2);
		entity.setEvaluate(StringUtils.isNotBlank(evaluate) ? evaluate : "");
		/**
		 * 获取楼盘排行
		 */
		if (StringUtils.isNotBlank(city)) {
			entity.setSortTime(PropHelper.getValue("report_sort_time_value"));
			TLpEnvironment lpEnv = new TLpEnvironment();
			lpEnv.setCity(city);
			lpEnv.setRadiation(BigDecimal.valueOf(radiation));
			lpEnv.setSortTime(Integer.parseInt(PropHelper.getValue("report_sort_time_key")));
			Integer sort = lpEnvMapper.findEnvironmentSort(lpEnv);
			if (sort != null) {
				entity.setSort(sort + 1);
			}
		}
		return entity;
	}

	/**
	 * 垃圾设施
	 */
	public TLpEnvironmentIndex rubbishLevel(TLandedProperty lp) {
		TLpEnvironmentIndex entity = new TLpEnvironmentIndex();
		entity.setCity(lp.getCity());
		Integer level = 2;
		String levelName = "良";
		Double distance = Double.valueOf("0.00");
		try {
			Map<String, String> rubbish = rubbishService.getRubbish(lp.getCity(), lp.getLat(), lp.getLng());
			if (rubbish != null) {
				level = Integer.valueOf(rubbish.get("level"));
				levelName = rubbish.get("levelName");
				distance = Double.valueOf(rubbish.get("distance"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		entity.setName("污染源-垃圾设施");
		entity.setLevel(level);
		entity.setLevelName(levelName);
		entity.setValue(distance);
		String evaluate = PropHelper.getValue("rubbish_level_" + level);
		entity.setEvaluate(StringUtils.isNotBlank(evaluate) ? evaluate : "");
		/**
		 * 获取楼盘排行
		 */
		if (StringUtils.isNotBlank(lp.getCity())) {
			entity.setSortTime(PropHelper.getValue("report_sort_time_value"));
			TLpEnvironment lpEnv = new TLpEnvironment();
			lpEnv.setCity(lp.getCity());
			lpEnv.setRubbish(BigDecimal.valueOf(distance));
			lpEnv.setSortTime(Integer.parseInt(PropHelper.getValue("report_sort_time_key")));
			Integer sort = lpEnvMapper.findEnvironmentSort(lpEnv);
			if (sort != null) {
				entity.setSort(sort + 1);
			}
		}
		return entity;
	}

	/**
	 * 化工厂
	 */
	public TLpEnvironmentIndex chemicalLevel(TLandedProperty lp) {
		TLpEnvironmentIndex entity = new TLpEnvironmentIndex();
		entity.setCity(lp.getCity());
		Integer level = 2;
		String levelName = "良";
		Double distance = Double.valueOf("0.00");
		try {
			Map<String, String> chemical = chemicalService.getChemical(lp.getCity(), lp.getLat(), lp.getLng());
			if (chemical != null) {
				level = Integer.valueOf(chemical.get("level"));
				levelName = chemical.get("levelName");
				distance = Double.valueOf(chemical.get("distance"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		entity.setName("污染源-化工厂");
		entity.setLevel(level);
		entity.setLevelName(levelName);
		entity.setValue(distance);
		String evaluate = PropHelper.getValue("chemical_level_" + level);
		entity.setEvaluate(StringUtils.isNotBlank(evaluate) ? evaluate : "");
		/**
		 * 获取楼盘排行
		 */
		if (StringUtils.isNotBlank(lp.getCity())) {
			entity.setSortTime(PropHelper.getValue("report_sort_time_value"));
			TLpEnvironment lpEnv = new TLpEnvironment();
			lpEnv.setCity(lp.getCity());
			lpEnv.setChemical(BigDecimal.valueOf(distance));
			lpEnv.setSortTime(Integer.parseInt(PropHelper.getValue("report_sort_time_key")));
			Integer sort = lpEnvMapper.findEnvironmentSort(lpEnv);
			if (sort != null) {
				entity.setSort(sort + 1);
			}
		}
		return entity;
	}

	/**
	 * 空气质量
	 */
	public TLpEnvironmentIndex airLevel(JSONArray cityAirLevel, String city) {
		TLpEnvironmentIndex entity = new TLpEnvironmentIndex();
		entity.setCity(city);
		Integer level = 2;
		String levelName = "良";
		Double value = Double.valueOf("0.00");
		if (cityAirLevel != null && cityAirLevel.size() > 0) {
			for (int i = 0; i < cityAirLevel.size(); i++) {
				JSONObject air = cityAirLevel.getJSONObject(i);
				if (StringUtils.equals(city, air.getString("city"))) {
					level = air.getJSONObject("level").getInteger("air");
					levelName = air.getJSONObject("level").getString("levelName");
					value = air.getJSONObject("level").getDouble("aqi");
				}
			}
		}
		entity.setName("空气质量（AQI）");
		entity.setLevel(level);
		entity.setLevelName(levelName);
		entity.setValue(value);
		String evaluate = PropHelper.getValue("air_level_" + level);
		entity.setEvaluate(StringUtils.isNotBlank(evaluate) ? evaluate : "");
		/**
		 * 获取楼盘排行
		 */
		if (StringUtils.isNotBlank(city)) {
			entity.setSortTime(PropHelper.getValue("report_sort_time_value"));
			TLpEnvironment lpEnv = new TLpEnvironment();
			lpEnv.setCity(city);
			lpEnv.setAir(BigDecimal.valueOf(value));
			lpEnv.setSortTime(Integer.parseInt(PropHelper.getValue("report_sort_time_key")));
			Integer sort = lpEnvMapper.findEnvironmentSort(lpEnv);
			if (sort != null) {
				entity.setSort(sort + 1);
			}
		}
		return entity;
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
	public JSONArray getCityAirLevel() {
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

	public String createHtml(TLandedProperty lp, String reportName) {
		String url = "";
		try {
			String path = ReportHelper.class.getResource("/report/" + reportName + ".ftl").getPath();
			// 创建一个合适的Configration对象
			Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
			// 指定模板文件所在路径
			configuration.setDirectoryForTemplateLoading(new File(path).getParentFile());
			// 设置模板编码
			configuration.setDefaultEncoding("UTF-8");
			Template template = configuration.getTemplate(reportName + ".ftl");
			File file = new File("d:/test/" + lp.getCode() + "/");
			if (!file.exists()) {
				file.mkdirs();
			}
			Writer writer = new OutputStreamWriter(
					new FileOutputStream("d:/test/" + lp.getCode() + "/" + reportName + ".html"), "UTF-8");
			template.process(lp, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
}
