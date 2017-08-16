package cn.com.ddhj.service.impl.lp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.landedProperty.TLpEnvironmentDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.ITAreaNoiseMapper;
import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.mapper.TWaterEnviromentMapper;
import cn.com.ddhj.mapper.lp.TLpEnvironmentMapper;
import cn.com.ddhj.model.TAreaNoise;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.model.TWaterEnviroment;
import cn.com.ddhj.model.lp.TLpEnvironment;
import cn.com.ddhj.service.ITChemicalPlantService;
import cn.com.ddhj.service.ITRubbishRecyclingService;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.lp.ITLpEnvironmentService;
import cn.com.ddhj.util.CommonUtil;
import cn.com.ddhj.util.Constant;
import cn.com.ddhj.util.DateUtil;

@Service
public class TLpEnvironmentServiceImpl extends BaseServiceImpl<TLpEnvironment, TLpEnvironmentMapper, TLpEnvironmentDto>
		implements ITLpEnvironmentService {

	@Autowired
	private TLpEnvironmentMapper mapper;
	@Autowired
	private TLandedPropertyMapper lpMapper;
	@Autowired
	private ITChemicalPlantService chemicalService;
	@Autowired
	private ITRubbishRecyclingService rubbishService;
	@Autowired
	private TWaterEnviromentMapper waterEnvMapper;
	@Autowired
	private ITAreaNoiseMapper noiseMapper;

	@Override
	public BaseResult batchInsert() {
		BaseResult result = new BaseResult();
		try {
			/**
			 * 获取所有楼盘信息
			 */
			List<TLpEnvironment> list = getData();
			if (list != null && list.size() > 0) {
				int size = list.size() / 10000;
				int current = 10000;
				for (int i = 0; i <= size-1; i++) {
					System.out.println((current * i + i) + "|" + (current * (i + 1)));
					List<TLpEnvironment> subList = list.subList(current * i, current * (i + 1));
					mapper.batchInsert(subList);
				}
				List<TLpEnvironment> subList = list.subList(current * size + 1, list.size());
				mapper.batchInsert(subList);
				result.setResultCode(Constant.RESULT_SUCCESS);
				result.setResultMessage("批量添加成功");
			} else {
				result.setResultCode(Constant.RESULT_NULL);
				result.setResultMessage("楼盘数据为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("批量添加失败，失败原因:" + e.getMessage());
		}
		return result;
	}

	private List<TLpEnvironment> getData() {
		List<TLpEnvironment> list = new ArrayList<TLpEnvironment>();
		try {
			// JSONArray cityAirLevel = helper.getCityAirLevel();
			List<TLandedProperty> lpList = lpMapper.findTLandedPropertyAll();
			if (lpList != null && lpList.size() > 0) {
				for (TLandedProperty lp : lpList) {
					TLpEnvironment entity = new TLpEnvironment();
					entity.setCity(StringUtils.isNotBlank(lp.getCity()) ? lp.getCity() : "");
					entity.setUuid(WebHelper.getInstance().genUuid());
					entity.setLpCode(lp.getCode());
					// 获取绿地率等级
					BigDecimal afforest = BigDecimal.ZERO;
					try {
						Double _afforest = Double
								.valueOf(lp.getGreeningRate().substring(0, lp.getGreeningRate().indexOf("%")));
						afforest = BigDecimal.valueOf(_afforest);
					} catch (Exception e) {
					}
					entity.setAfforest(afforest);
					// 获取容积率等级
					BigDecimal volume = BigDecimal.ZERO;
					if (lp.getVolumeRate() != null && !"".equals(lp.getVolumeRate())) {
						try {
							volume = BigDecimal.valueOf(Double.valueOf(lp.getVolumeRate()));
						} catch (Exception e) {
						}
					}
					entity.setVolume(volume);
					// 空气质量等级
					Integer airLevel = 1;
					// if (StringUtils.isNotBlank(lp.getCity())) {
					// if (cityAirLevel != null && cityAirLevel.size() > 0) {
					// for (int i = 0; i < cityAirLevel.size(); i++) {
					// JSONObject level = cityAirLevel.getJSONObject(i);
					// if (StringUtils.equals(lp.getCity(),
					// level.getString("city"))) {
					// airLevel =
					// level.getJSONObject("level").getInteger("air");
					// }
					// }
					// }
					// }
					entity.setAir(BigDecimal.ZERO);
					// 水质量数值
					BigDecimal water = BigDecimal.ZERO;
					try {
						List<TWaterEnviroment> waterEnvs = waterEnvMapper.selectByCity(lp.getCity());
						if (list != null && list.size() != 0) {
							Double lat = Double.valueOf(lp.getLat());
							Double lng = Double.valueOf(lp.getLng());
							TreeMap<Integer, TWaterEnviroment> map_ = new TreeMap<Integer, TWaterEnviroment>();
							for (TWaterEnviroment e : waterEnvs) {
								Integer d = CommonUtil.getMeterDistance(lat, lng, Double.valueOf(e.getLat()),
										Double.valueOf(e.getLng()));
								map_.put(d, e);
							}
							TWaterEnviroment w = map_.get(map_.firstKey());
							String oxy = w.getOxygenquality();
							if (StringUtils.isNotBlank(oxy)) {
								water = BigDecimal.valueOf(Double.valueOf(oxy));
							}
						}
					} catch (Exception e) {
					}
					entity.setWater(water);
					// 垃圾设施数值
					String rubbishDistance = "0.00";
					if (StringUtils.isNotBlank(lp.getCity()) && StringUtils.isNotBlank(lp.getLat())
							&& StringUtils.isNotBlank(lp.getLng())) {
						Map<String, String> rubbish = rubbishService.getRubbish(lp.getCity(), lp.getLat(), lp.getLng());
						if (rubbish != null && StringUtils.isNotBlank(rubbish.get("distance"))) {
							rubbishDistance = rubbish.get("distance");
						}
					}
					entity.setRubbish(BigDecimal.valueOf(Double.valueOf(rubbishDistance)));
					// 化工厂数值
					String chemicalDistance = "0.00";
					if (StringUtils.isNotBlank(lp.getCity()) && StringUtils.isNotBlank(lp.getLat())
							&& StringUtils.isNotBlank(lp.getLng())) {
						Map<String, String> chemical = chemicalService.getChemical(lp.getCity(), lp.getLat(),
								lp.getLng());
						if (chemical != null && StringUtils.isNotBlank(chemical.get("distance"))) {
							chemicalDistance = chemical.get("distance");
						}
					}
					entity.setChemical(BigDecimal.valueOf(Double.valueOf(chemicalDistance)));
					// 噪音等级
					Double nosie = noiseLevel(lp);
					entity.setNosie(BigDecimal.valueOf(nosie));
					// 高压电辐射
					entity.setRadiation(BigDecimal.valueOf(1));
					// 危险品存放
					entity.setHazardousArticle(BigDecimal.valueOf(1));
					entity.setCreateUser("timer");
					entity.setCreateTime(DateUtil.getSysDateTime());
					entity.setUpdateUser("timer");
					entity.setUpdateTime(DateUtil.getSysDateTime());
					list.add(entity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
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
	@SuppressWarnings("unused")
	public Double noiseLevel(TLandedProperty lp) {
		Double distance = Double.valueOf("0.00");
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
				}
			}
		}
		return distance;
	}

}
