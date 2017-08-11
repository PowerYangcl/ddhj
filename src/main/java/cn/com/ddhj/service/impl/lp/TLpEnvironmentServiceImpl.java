package cn.com.ddhj.service.impl.lp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.landedProperty.TLpEnvironmentDto;
import cn.com.ddhj.helper.ReportHelper;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.mapper.lp.TLpEnvironmentMapper;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.model.lp.TLpEnvironment;
import cn.com.ddhj.service.ITChemicalPlantService;
import cn.com.ddhj.service.ITRubbishRecyclingService;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.lp.ITLpEnvironmentService;
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

	@Override
	public BaseResult batchInsert() {
		BaseResult result = new BaseResult();
		try {
			/**
			 * 获取所有楼盘信息
			 */
			List<TLpEnvironment> list = getData();
			if (list != null && list.size() > 0) {
				mapper.batchInsert(list);
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
		ReportHelper helper = new ReportHelper();
		try {
			// JSONArray cityAirLevel = helper.getCityAirLevel();
			List<TLandedProperty> lpList = lpMapper.findTLandedPropertyAll();
			if (lpList != null && lpList.size() > 0) {
				for (TLandedProperty lp : lpList) {
					TLpEnvironment entity = new TLpEnvironment();
					entity.setUuid(WebHelper.getInstance().genUuid());
					entity.setLpCode(lp.getCode());
					// 获取绿地率等级
					Integer afforestLevel = 1;
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
					entity.setAfforest(afforestLevel);
					// 获取容积率等级
					Integer volumeLevel = 1;
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
					entity.setVolume(volumeLevel);
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
					entity.setAir(airLevel);
					// 水质量等级
					Integer waterLevel = 2;
					if (StringUtils.isNoneBlank(lp.getCity())) {
						waterLevel = helper.waterEnv(lp);
					}
					entity.setWater(waterLevel);
					// 垃圾设施等级
					Integer rubbishLevel = 1;
					if (StringUtils.isNotBlank(lp.getCity()) && StringUtils.isNotBlank(lp.getLat())
							&& StringUtils.isNotBlank(lp.getLng())) {
						Map<String, String> rubbish = rubbishService.getRubbish(lp.getCity(), lp.getLat(), lp.getLng());
						rubbishLevel = Integer.valueOf(rubbish.get("level"));
					}
					entity.setRubbish(rubbishLevel);
					// 化工厂
					Integer chemicalLevel = 1;
					if (StringUtils.isNotBlank(lp.getCity()) && StringUtils.isNotBlank(lp.getLat())
							&& StringUtils.isNotBlank(lp.getLng())) {
						Map<String, String> chemical = chemicalService.getChemical(lp.getCity(), lp.getLat(),
								lp.getLng());
						chemicalLevel = Integer.valueOf(chemical.get("level"));
					}
					entity.setChemical(chemicalLevel);
					// 噪音等级
					Integer nosieLevel = helper.getNoiseLevel(lp);
					entity.setNosie(nosieLevel);
					// 高压电辐射
					entity.setRadiation(1);
					// 危险品存放
					entity.setHazardousArticle(1);
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

}
