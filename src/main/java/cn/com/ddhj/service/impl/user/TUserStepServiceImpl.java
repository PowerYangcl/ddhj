package cn.com.ddhj.service.impl.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.user.TUserStepDto;
import cn.com.ddhj.helper.PropHelper;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.user.TUserCarbonOperationMapper;
import cn.com.ddhj.mapper.user.TUserMapper;
import cn.com.ddhj.mapper.user.TUserStepMapper;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.model.user.TUserCarbonOperation;
import cn.com.ddhj.model.user.TUserStep;
import cn.com.ddhj.result.EntityResult;
import cn.com.ddhj.result.tuser.UserDataResult;
import cn.com.ddhj.result.tuser.UserStepResult;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.user.ITUserService;
import cn.com.ddhj.service.user.ITUserStepService;
import cn.com.ddhj.util.Constant;
import cn.com.ddhj.util.DateUtil;

/**
 * 
 * 类: TUserStepServiceImpl <br>
 * 描述: 计步器业务处理接口实现类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月14日 下午5:40:36
 */
@Service
public class TUserStepServiceImpl extends BaseServiceImpl<TUserStep, TUserStepMapper, TUserStepDto>
		implements ITUserStepService {

	@Autowired
	private TUserStepMapper mapper;
	@Autowired
	private TUserMapper userMapper;
	@Autowired
	private TUserCarbonOperationMapper carbonOperationMapper;

	@Autowired
	private ITUserService userService;

	/**
	 * 
	 * 方法: batchInstart <br>
	 * 
	 * @param data
	 * @param userTocken
	 * @return
	 * @see cn.com.ddhj.service.user.ITUserStepService#batchInstart(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public BaseResult batchInsert(String data) {
		int isBinding = 0;
		String userCode = "";
		BaseResult result = new BaseResult();
		if (StringUtils.isNotBlank(data)) {
			JSONObject step = JSONObject.parseObject(data);
			if (step != null) {
				TUser user = userMapper.findUserByPhone(step.getString("phone"));
				if (user != null) {
					isBinding = 1;
					userCode = user.getUserCode();
				}
				String equipmentCode = step.getString("equipmentCode");
				JSONArray array = JSONArray.parseArray(step.getString("data"));
				if (array != null && array.size() > 0) {
					List<TUserStep> list = new ArrayList<TUserStep>();
					for (int i = 0; i < array.size(); i++) {
						JSONObject obj = array.getJSONObject(i);
						TUserStep entity = new TUserStep();
						entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
						entity.setEquipmentCode(equipmentCode);
						entity.setStep(obj.getInteger("step") != null ? obj.getInteger("step") : 0);
						entity.setCreateDate(obj.getString("date"));
						entity.setCreateTime(DateUtil.getSysDateTime());
						entity.setUpdateTime(DateUtil.getSysDateTime());
						entity.setIsBinding(isBinding);
						entity.setUserCode(userCode);
						if (StringUtils.isNotBlank(userCode)) {
							entity.setIsSync(1);
						}
						list.add(entity);
					}
					Collections.sort(list, new Comparator<TUserStep>() {
						@Override
						public int compare(TUserStep s1, TUserStep s2) {
							return s1.getCreateDate().compareTo(s2.getCreateDate());
						}
					});
					// 获取开始日期和结束日期
					String startDate = "";
					String endDate = "";
					for (int i = 0; i < list.size(); i++) {
						if (i == 0) {
							startDate = list.get(i).getCreateDate();
						} else if (i == list.size() - 1) {
							endDate = list.get(i).getCreateDate();
						}
					}
					// 根据设备识别码和时间段删除原有数据
					TUserStepDto dto = new TUserStepDto();
					dto.setEquipmentCode(equipmentCode);
					dto.setEndDate(endDate);
					dto.setStartDate(startDate);
					mapper.deletByEquipmentCode(dto);
					// 批量添加数据到计步表
					int flag = mapper.batchInsert(list);
					if (flag > 0) {
						result.setResultCode(Constant.RESULT_SUCCESS);
						/**
						 * 汇总兑换碳币数量,同步到t_user表中
						 */
						List<TUser> carbonUsers = userCarbonList(list);
						for (TUser uCarbon : carbonUsers) {
							uCarbon.setUpdateTime(DateUtil.getSysDateTime());
							userMapper.updateCarbonByUserCode(uCarbon);
						}
						/**
						 * 同步到碳币操作日志表
						 */
						List<TUserCarbonOperation> operations = carbonOperationData(carbonUsers);
						if (operations != null && operations.size() > 0) {
							carbonOperationMapper.batchInsert(operations);
						}
						result.setResultMessage("同步成功");
					} else {
						result.setResultCode(Constant.RESULT_ERROR);
						result.setResultMessage("批量同步计步数据错误");
					}
				} else {
					result.setResultCode(Constant.RESULT_ERROR);
					result.setResultMessage("同步计步数据为空");
				}
			} else {
				result.setResultCode(Constant.RESULT_ERROR);
				result.setResultMessage("请确认数据是否为json");
			}
		} else {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("参数错误");
		}
		return result;
	}

	/**
	 * 
	 * 方法: findUserStepData <br>
	 * 
	 * @param equipmentCode
	 * @param time
	 * @return
	 * @see cn.com.ddhj.service.user.ITUserStepService#findUserStepData(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public UserStepResult findUserStepData(String equipmentCode, String userToken) {
		UserStepResult result = new UserStepResult();
		try {
			String userCode = "";
			if (StringUtils.isNotBlank(userToken)) {
				UserDataResult userResult = userService.getUser(userToken);
				if (userResult.getResultCode() == Constant.RESULT_SUCCESS) {
					TUser user = userResult.getUser();
					userCode = user.getUserCode();
				}
			}
			// 历史七天记录
			result.setWeek(getWeekStep(equipmentCode, userCode));
			// 历史一个月记录
			result.setMonth(getMonthStep(equipmentCode, userCode));
			// 历史一年记录
			result.setYear(getYearStep(equipmentCode, userCode));
			result.setResultCode(Constant.RESULT_SUCCESS);
			result.setResultMessage("查询列表成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("查询用户计步信息失败");
		}
		return result;
	}

	/**
	 * 
	 * 方法: syncStepDataToCarbon <br>
	 * 
	 * @return
	 * @see cn.com.ddhj.service.user.ITUserStepService#syncStepDataToCarbon()
	 */
	@Override
	public BaseResult syncStepDataToCarbon() {
		BaseResult result = new BaseResult();
		try {
			List<TUserStep> steps = mapper.findStepDataIsNotSync();
			if (steps != null && steps.size() > 0) {
				List<TUser> list = userCarbonList(steps);
				for (TUser tUser : list) {
					tUser.setUpdateTime(DateUtil.getSysDateTime());
					userMapper.updateCarbonByUserCode(tUser);
				}
				List<TUserCarbonOperation> operations = carbonOperationData(list);
				carbonOperationMapper.batchInsert(operations);
				result.setResultCode(Constant.RESULT_SUCCESS);
				result.setResultMessage("同步成功");
			} else {
				result.setResultCode(Constant.RESULT_NULL);
				result.setResultMessage("同步数据为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage(e.getMessage());
		}
		return result;
	}

	@Override
	public EntityResult findStepByDate(TUserStepDto dto, String userToken) {
		EntityResult result = new EntityResult();
		try {
			UserDataResult userResult = userService.getUser(userToken);
			if (userResult.getResultCode() == Constant.RESULT_SUCCESS) {
				TUser user = userResult.getUser();
				dto.setUserCode(user.getUserCode());
				TUserStep step = mapper.findStepByDate(dto);
				if (step != null) {
					String carbonRatio = PropHelper.getValue("carbon_exchange_ratio");
					String dischargeRatio = PropHelper.getValue("step_discharge");
					Double carbon = step.getStep() * Double.valueOf(carbonRatio);
					step.setCarbon(carbon);
					Double discharge = step.getStep() * Double.valueOf(dischargeRatio);
					step.setDischarge(discharge);
					result.setEntity(step);
				} else {
					result.setResultMessage("获取用户步数为空");
				}
			} else {
				result.setResultCode(-1);
				result.setResultMessage("用户尚未登录");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(-1);
			result.setResultMessage("获取用户步数错误，请联系技术人员");
		}
		return result;
	}

	/**
	 * 
	 * 方法: getWeekStep <br>
	 * 描述: 获取历史七天的计步数据 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月16日 下午5:11:39
	 * 
	 * @return
	 */
	private List<TUserStep> getWeekStep(String equipmentCode, String userCode) {
		// 获取开始日期和结束日期
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) - 6);
		String startDate = DateUtil.dateToString(cal.getTime());
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		cal2.set(cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH), cal2.get(Calendar.DAY_OF_MONTH));
		String endDate = DateUtil.dateToString(cal2.getTime());
		TUserStepDto dto = new TUserStepDto();
		if (StringUtils.isNotBlank(equipmentCode)) {
			dto.setEquipmentCode(equipmentCode);
		} else {
			dto.setUserCode(userCode);
		}
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		List<TUserStep> list = mapper.findUserStepData(dto);
		if (list == null || list.size() <= 0) {
			list = new ArrayList<TUserStep>();
		}
		list = trimData(list, startDate, endDate);
		return list;
	}

	private List<TUserStep> getMonthStep(String equipmentCode, String userCode) {
		// 获取开始日期和结束日期
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) - 1, cal.get(Calendar.DAY_OF_MONTH));
		String startDate = DateUtil.dateToString(cal.getTime());
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		cal2.set(cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH), cal2.get(Calendar.DAY_OF_MONTH) - 1);
		String endDate = DateUtil.dateToString(cal2.getTime());
		TUserStepDto dto = new TUserStepDto();
		if (StringUtils.isNotBlank(equipmentCode)) {
			dto.setEquipmentCode(equipmentCode);
		} else {
			dto.setUserCode(userCode);
		}
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		List<TUserStep> list = mapper.findUserStepData(dto);
		if (list == null || list.size() <= 0) {
			list = new ArrayList<TUserStep>();
		}
		list = trimData(list, startDate, endDate);
		return list;
	}

	/**
	 * 
	 * 方法: getYearStep <br>
	 * 描述: 获取历史一年的计步数据 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月16日 下午5:49:53
	 * 
	 * @return
	 */
	private List<Map<String, Integer>> getYearStep(String equipmentCode, String userCode) {
		List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
		for (int i = 0; i < 12; i++) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) - (i + 1), cal.get(Calendar.DAY_OF_MONTH));
			String startDate = DateUtil.dateToString(cal.getTime());
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			cal2.set(cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH) - i, cal2.get(Calendar.DAY_OF_MONTH) - 1);
			String endDate = DateUtil.dateToString(cal2.getTime());
			TUserStepDto dto = new TUserStepDto();
			if (StringUtils.isNotBlank(equipmentCode)) {
				dto.setEquipmentCode(equipmentCode);
			} else {
				dto.setUserCode(userCode);
			}
			dto.setStartDate(startDate);
			dto.setEndDate(endDate);
			List<TUserStep> month = mapper.findUserStepData(dto);
			month = trimData(month, startDate, endDate);
			Integer total = 0;
			for (TUserStep step : month) {
				total += step.getStep();
			}
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("month", i);
			if (total == 0) {
				map.put("step", 0);
			} else {
				map.put("step", total / getCurrentMonthDay(cal));
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * 
	 * 方法: trimData <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月14日 下午5:47:19
	 * 
	 * @param list
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private static List<TUserStep> trimData(List<TUserStep> list, String startDate, String endDate) {
		List<TUserStep> newList = new ArrayList<TUserStep>();
		try {
			int day = 0;
			// 开始时间
			Calendar startCal = Calendar.getInstance();
			startCal.setTime(DateUtil.strToDate(startDate));
			// 结束时间
			Calendar endCal = Calendar.getInstance();
			endCal.setTime(DateUtil.strToDate(endDate));
			while (startCal.getTime().before(endCal.getTime())) {
				startCal.set(startCal.get(Calendar.YEAR), startCal.get(Calendar.MONTH),
						startCal.get(Calendar.DAY_OF_MONTH) + day);
				String date = DateUtil.dateToString(startCal.getTime());
				TUserStep entity = new TUserStep();
				entity.setStep(0);
				entity.setCreateDate(date);
				newList.add(entity);
				day = 1;
			}
			if (list != null && list.size() > 0) {
				for (int i = 0; i < newList.size(); i++) {
					TUserStep newStep = newList.get(i);
					for (TUserStep step : list) {
						if (StringUtils.equals(newStep.getCreateDate(), step.getCreateDate())) {
							newStep.setStep(step.getStep());
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newList;
	}

	/**
	 * 
	 * 方法: getCurrentMonthDay <br>
	 * 描述: 获取当月的天数<br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月16日 下午5:45:52
	 * 
	 * @return
	 */
	private static int getCurrentMonthDay(Calendar cal) {
		cal.set(Calendar.DATE, 1);
		cal.roll(Calendar.DATE, -1);
		int maxDate = cal.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 
	 * 方法: userCarbonList <br>
	 * 描述: 获取用户碳币信息 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年2月13日 下午4:27:58
	 * 
	 * @param steps
	 * @return
	 */
	private static List<TUser> userCarbonList(List<TUserStep> steps) {
		List<TUser> users = new ArrayList<TUser>();
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		if (steps != null && steps.size() > 0) {
			String carbon_exchange_ratio = PropHelper.getValue("carbon_exchange_ratio");
			for (TUserStep step : steps) {
				if (StringUtils.isNotBlank(step.getUserCode())) {
					TUser user = new TUser();
					user.setUserCode(step.getUserCode());
					// 获取碳币
					BigDecimal carbonMoney = BigDecimal.valueOf(Double.valueOf(carbon_exchange_ratio) * step.getStep());
					carbonMoney = carbonMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
					if (carbonMoney.compareTo(BigDecimal.ZERO) == 1) {
						/**
						 * 判断在map中是否已存在userCode，如果存在获取已存储的碳币值与获取碳币值相加后重新存储
						 */
						if (map.containsKey(step.getUserCode())) {
							carbonMoney = map.get(step.getUserCode()).add(carbonMoney);
							map.remove(step.getUserCode());
							map.put(step.getUserCode(), carbonMoney);
						} else {
							map.put(step.getUserCode(), carbonMoney);
						}
					}
				}
			}

			/**
			 * 遍历map获取用户同步步数兑换的碳币
			 */
			for (Map.Entry<String, BigDecimal> entry : map.entrySet()) {
				TUser user = new TUser();
				user.setUserCode(entry.getKey());
				user.setCarbonMoney(entry.getValue());
				users.add(user);
			}

		}
		return users;
	}

	/**
	 * 
	 * 方法: carbonOperationData <br>
	 * 描述: 整理用户步数兑换碳币信息到操作日志表 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年2月14日 上午10:29:45
	 * 
	 * @param users
	 * @return
	 */
	private List<TUserCarbonOperation> carbonOperationData(List<TUser> users) {
		List<TUserCarbonOperation> list = new ArrayList<TUserCarbonOperation>();
		for (TUser user : users) {
			TUserCarbonOperation entity = new TUserCarbonOperation();
			entity.setUuid(WebHelper.getInstance().genUuid());
			entity.setCode(WebHelper.getInstance().getUniqueCode("LC"));
			entity.setUserCode(user.getUserCode());
			entity.setOperationType("DC170208100002");
			entity.setOperationTypeChild("DC170208100004");
			entity.setCreateUser(user.getUserCode());
			entity.setCreateTime(DateUtil.getSysDateTime());
			entity.setCarbonSum(user.getCarbonMoney());
			list.add(entity);
		}
		return list;
	}
}
