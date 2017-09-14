package cn.com.ddhj.service.impl.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.user.TUserCarbonOperationDto;
import cn.com.ddhj.dto.user.TUserStepDto;
import cn.com.ddhj.helper.PropHelper;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.user.TUserCarbonOperationMapper;
import cn.com.ddhj.mapper.user.TUserLoginMapper;
import cn.com.ddhj.mapper.user.TUserMapper;
import cn.com.ddhj.mapper.user.TUserStepMapper;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.model.user.TUserCarbonOperation;
import cn.com.ddhj.model.user.TUserLogin;
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
	private TUserLoginMapper loginMapper;
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
	public BaseResult batchInsert(String data, String userToken) {
		BaseResult result = new BaseResult();
		int isBinding = 0; String userCode = "";
		
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login == null) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户未登录");
			return result;
		}
		
		TUser user = userMapper.findTUserByUuid(login.getUserToken());
		if (user == null) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户不存在");
			return result;
		} else {
			isBinding = 1;
			userCode = user.getUserCode();
		}
		
		if (StringUtils.isBlank(data)) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("参数错误");
			return result;
		}
		
		JSONObject step = JSONObject.parseObject(data);
		if (step == null) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("请确认数据是否为json");
			return result;
		}
		
		
//		TUser user = userMapper.findUserByPhone(step.getString("phone"));
//		if (user != null) {
//			isBinding = 1;
//			userCode = user.getUserCode();
//		}

		String equipmentCode = step.getString("equipmentCode");
		JSONArray array = JSONArray.parseArray(step.getString("data"));
		if (array == null || array.size() == 0) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("同步计步数据为空");
		}
		
		List<TUserStep> userStepList = new ArrayList<TUserStep>();
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
			userStepList.add(entity);
		}
		Collections.sort(userStepList, new Comparator<TUserStep>() {
			@Override
			public int compare(TUserStep s1, TUserStep s2) {
				return s1.getCreateDate().compareTo(s2.getCreateDate());
			}
		});
		// 获取开始日期和结束日期
//		String startDate = "", endDate = "";
		TUserStepDto dto = new TUserStepDto();
		List<JSONObject> userStepFilterList = new LinkedList<JSONObject>();
		for (int i = 0; i < userStepList.size(); i++) {
			TUserStep userStep = userStepList.get(i);
//			if (i == 0) {
//				startDate = userStep.getCreateDate();
//			} else if (i == userStepList.size() - 1) {
//				endDate = userStep.getCreateDate();
//			}
			
			dto.setEquipmentCode(userStep.getEquipmentCode());
			dto.setCreateDate(userStep.getCreateDate() + " 00:00:00");
			dto.setUserCode(userCode);
			List<TUserStep> userStepStoredList = mapper.findUserStepOne(dto);
			JSONObject us = new JSONObject();
			if(userStepStoredList == null || userStepStoredList.isEmpty()) {
				//U用户的Y设备的X日步数第一次同步到后库,插入
				int count = mapper.insertSelective(userStep);
				if(count == 1) {
					us.put("type", "new");
					us.put("newStep", userStep.getStep());
					us.put("createDate", userStep.getCreateDate());
					userStepFilterList.add(us);
				}
			} else {
				//虽然返回的是List(容错),只取第一条U用户的Y设备的X日步数记录应该只有一条
				TUserStep userStepInDb = userStepStoredList.get(0);
				if(userStep.getStep() > userStepInDb.getStep()) {
					//本次前端上传的U用户的Y设备的X日步数大于
					//数据库中存储的对应记录的步数则处理,否则忽略
					int oldStep = userStepInDb.getStep();
					userStepInDb.setStep(userStep.getStep());
					userStepInDb.setCreateDate(userStepInDb.getCreateDate() + " 00:00:00");
					userStepInDb.setUpdateTime(DateUtil.getSysDateTime());
					int count = mapper.updateByEquipCodeAndUserCodeAndCreateDate(userStepInDb);
					if(count == 1) {
						us.put("type", "update");
						us.put("oldStep", oldStep);
						us.put("newStep", userStep.getStep());
						us.put("substractStep", userStep.getStep() - oldStep);
						us.put("createDate", userStep.getCreateDate());
						us.put("isSync", userStepInDb.getIsSync());
						userStepFilterList.add(us);
					}
				}
			}
		}
		
		if(userStepFilterList.isEmpty()) {
			result.setResultCode(Constant.RESULT_NULL);
			result.setResultMessage("步数兑换碳币后无新增碳币");
			return result;
		}
		
		boolean exchanged = false;
		for(JSONObject userStepFilter : userStepFilterList) {
			String type = userStepFilter.get("type").toString();
			String createDate = userStepFilter.get("createDate").toString();
			if(type.equals("new")) {
				//新增步数
				Integer newStep = Integer.parseInt(userStepFilter.get("newStep").toString());
				//增加新步数对应的碳币
				TUser uCarbon = new TUser();
				BigDecimal nsb = exchangeCarbonMoneyFromStep(newStep);
				if(nsb.compareTo(BigDecimal.valueOf(0)) == 1) {
					//新步数按比例兑换的碳币数(保留小数点后2位)不为0
					uCarbon.setCarbonMoney(nsb.setScale(2, BigDecimal.ROUND_HALF_UP));
					uCarbon.setUpdateTime(DateUtil.getSysDateTime());
					uCarbon.setUserCode(userCode);
					int count = userMapper.updateCarbonByUserCode(uCarbon);
					if(count > 0) {
						exchanged = true;
						//记录兑换新增步数对应的碳币
						TUserCarbonOperation entity = new TUserCarbonOperation();
						entity.setUuid(WebHelper.getInstance().genUuid());
						entity.setCode(WebHelper.getInstance().getUniqueCode("LC"));
						entity.setUserCode(user.getUserCode());
						entity.setOperationType("DC170208100002");
						entity.setOperationTypeChild("DC170208100004");
						entity.setCreateUser(user.getUserCode());
						entity.setCreateTime(createDate + " 00:00:00");
						entity.setCarbonSum(nsb);
						carbonOperationMapper.insertSelective(entity);
						
						TUserStep step1 = new TUserStep();
						step1.setIsSync(1);
						step1.setUpdateTime(DateUtil.getSysDateTime());
						step1.setEquipmentCode(equipmentCode);
						step1.setUserCode(user.getUserCode());
						step1.setCreateDate(createDate + " 00:00:00");
						mapper.updateByEquipCodeAndUserCodeAndCreateDate(step1);
					}
				}
			} else {
				//修改步数
				Integer isSync = Integer.parseInt(userStepFilter.get("isSync").toString());
				Integer oldStep = Integer.parseInt(userStepFilter.get("oldStep").toString());
				Integer newStep = Integer.parseInt(userStepFilter.get("newStep").toString());
				Integer substractStep = Integer.parseInt(userStepFilter.get("substractStep").toString());
				//如果上次U用户的Y设备的X日步数已同步兑换过则扣减原步数对应的碳币.
				//存在只同步成功,未兑换是因为步数过小,换成碳币不足0.01
				TUser uCarbon = new TUser();
				if(isSync == 1) {
					BigDecimal osb = exchangeCarbonMoneyFromStep(oldStep);
					if(osb.compareTo(BigDecimal.valueOf(0)) == 1) {
						//旧步数按比例兑换的碳币数(保留小数点后2位)不为0
						uCarbon.setCarbonMoney(BigDecimal.valueOf(0).subtract(osb).setScale(2, BigDecimal.ROUND_HALF_UP));
						uCarbon.setUpdateTime(DateUtil.getSysDateTime());
						uCarbon.setUserCode(userCode);
						userMapper.updateCarbonByUserCode(uCarbon);
					}
				}
				
				//增加新步数对应的碳币
				uCarbon = new TUser();
				BigDecimal nsb = exchangeCarbonMoneyFromStep(newStep);
				if(nsb.compareTo(BigDecimal.valueOf(0)) == 1) {
					//新步数按比例兑换的碳币数(保留小数点后2位)不为0
					uCarbon.setCarbonMoney(nsb.setScale(2, BigDecimal.ROUND_HALF_UP));
					uCarbon.setUpdateTime(DateUtil.getSysDateTime());
					uCarbon.setUserCode(userCode);
					int count = userMapper.updateCarbonByUserCode(uCarbon);
					if(count > 0) {
						//记录兑换新旧步数差值对应的碳币
						exchanged = true;
						BigDecimal ssb = BigDecimal.valueOf(0);
						if(isSync == 1) {
							//同步过,用差值记录
							ssb = exchangeCarbonMoneyFromStep(substractStep);
						} else {
							//未同步过,用新步数记录
							ssb = exchangeCarbonMoneyFromStep(newStep);
						}
						TUserCarbonOperation entity = new TUserCarbonOperation();
						entity.setUuid(WebHelper.getInstance().genUuid());
						entity.setCode(WebHelper.getInstance().getUniqueCode("LC"));
						entity.setUserCode(user.getUserCode());
						entity.setOperationType("DC170208100002");
						entity.setOperationTypeChild("DC170208100004");
						entity.setCreateUser(user.getUserCode());
						entity.setCreateTime(createDate + " 00:00:00");
						entity.setCarbonSum(ssb);
						carbonOperationMapper.insertSelective(entity);
						
						TUserStep step1 = new TUserStep();
						step1.setIsSync(1);
						step1.setUpdateTime(DateUtil.getSysDateTime());
						step1.setEquipmentCode(equipmentCode);
						step1.setUserCode(user.getUserCode());
						step1.setCreateDate(createDate + " 00:00:00");
						mapper.updateByEquipCodeAndUserCodeAndCreateDate(step1);
					}
				}
			}
		}
		
		if(exchanged) {
			result.setResultCode(Constant.RESULT_SUCCESS);
			result.setResultMessage("同步成功");
		} else {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("步数兑换碳币后无新增碳币");
		}
		return result;
//		// 根据设备识别码和时间段删除原有数据
//		TUserStepDto dto = new TUserStepDto();
//		dto.setEquipmentCode(equipmentCode);
//		dto.setEndDate(endDate);
//		dto.setStartDate(startDate);
//		mapper.deletByEquipmentCode(dto);
//		// 批量添加数据到计步表
//		int flag = mapper.batchInsert(list);
//		if (flag > 0) {
//			result.setResultCode(Constant.RESULT_SUCCESS);
//			/**
//			 * 汇总兑换碳币数量,同步到t_user表中
//			 */
//			List<TUser> carbonUsers = userCarbonList(list);
//			for (TUser uCarbon : carbonUsers) {
//				/**
//				 * 碳币保留两位小数
//				 */
//				uCarbon.setCarbonMoney(uCarbon.getCarbonMoney().setScale(2, BigDecimal.ROUND_HALF_UP));
//				uCarbon.setUpdateTime(DateUtil.getSysDateTime());
//				userMapper.updateCarbonByUserCode(uCarbon);
//			}
//			/**
//			 * 同步到碳币操作日志表
//			 */
//			List<TUserCarbonOperation> operations = carbonOperationData(carbonUsers);
//			if (operations != null && operations.size() > 0) {
//				carbonOperationMapper.batchInsert(operations);
//			}
//			result.setResultMessage("同步成功");
//		} else {
//			result.setResultCode(Constant.RESULT_ERROR);
//			result.setResultMessage("批量同步计步数据错误");
//		}
//			
//		
//		return result;
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
			result.setWeek(new ArrayList<TUserStep>());
			result.setYear(new ArrayList<Map<String, Integer>>());
			result.setMonth(new ArrayList<TUserStep>());
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
		} 
//		else {
//			dto.setUserCode(userCode);
//		}
		dto.setUserCode(userCode);
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
		}
//		else {
//			dto.setUserCode(userCode);
//		}
		dto.setUserCode(userCode);
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
			} 
//			else {
//				dto.setUserCode(userCode);
//			}
			dto.setUserCode(userCode);
			dto.setStartDate(startDate);
			dto.setEndDate(endDate);
			List<TUserStep> month = mapper.findUserStepData(dto);
			month = trimData(month, startDate, endDate);
			Integer total = 0;
			for (TUserStep step : month) {
				total += step.getStep() != null ? step.getStep() : 0;
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
	private List<TUserStep> trimData(List<TUserStep> list, String startDate, String endDate) {
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
	private int getCurrentMonthDay(Calendar cal) {
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
	
	private BigDecimal exchangeCarbonMoneyFromStep(Integer step) {
		String carbon_exchange_ratio = PropHelper.getValue("carbon_exchange_ratio");
		BigDecimal carbonMoney = BigDecimal.valueOf(Double.valueOf(carbon_exchange_ratio) * step);
		carbonMoney = carbonMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
		if (carbonMoney.compareTo(BigDecimal.ZERO) == 1) {
			return carbonMoney;
		} else {
			return BigDecimal.valueOf(0);
		}
	}
	
	public void dailyCountStepPresent(String createDate) {
		List<TUserStep> list = mapper.dailyCountStepPresent(createDate);
		for(TUserStep userStep : list) {
			Integer step = userStep.getStep();
			if(step < 10000) {
				continue;
			}
			
			TUser user = userMapper.selectByCode(userStep.getUserCode());
			TUserCarbonOperationDto dto = new TUserCarbonOperationDto();
			dto.setOperationTypeChild("DC170208100004");
			dto.setDay(1);
			dto.setUserCode(user.getUserCode());
			List<TUserCarbonOperation> yesterDayList = carbonOperationMapper.findCarbonOperationDetailDailyOne(dto);
			if(yesterDayList == null || yesterDayList.isEmpty()) {
				continue;
			}
			//前一天步行兑换的碳币数
			TUserCarbonOperation oper = yesterDayList.get(0);
			BigDecimal carbon = oper.getCarbonSum();
			if(carbon.compareTo(BigDecimal.valueOf(0)) == 0) {
				continue;
			}
			
			if(step >= 15000 && step < 20000) {
				//倍增
				user.setCarbonMoney(carbon.multiply(BigDecimal.valueOf(2)).setScale(2,  BigDecimal.ROUND_HALF_UP));
				userMapper.updateCarbonByUserCode(user);
				//记日志
				TUserCarbonOperation entity = new TUserCarbonOperation();
				entity.setUuid(WebHelper.getInstance().genUuid());
				entity.setCode(WebHelper.getInstance().getUniqueCode("LC"));
				entity.setUserCode(user.getUserCode());
				entity.setOperationType("DC170208100002");
				entity.setOperationTypeChild("DC170208100012");
				entity.setCreateUser(user.getUserCode());
				entity.setCreateTime(DateUtil.getSysDateTime());
				entity.setCarbonSum(carbon.multiply(BigDecimal.valueOf(2)).setScale(2,  BigDecimal.ROUND_HALF_UP));
				carbonOperationMapper.insertSelective(entity);
			} else if(step >= 20000) {
				//三倍增
				user.setCarbonMoney(carbon.multiply(BigDecimal.valueOf(3)).setScale(2,  BigDecimal.ROUND_HALF_UP));
				userMapper.updateCarbonByUserCode(user);
				//记日志
				TUserCarbonOperation entity = new TUserCarbonOperation();
				entity.setUuid(WebHelper.getInstance().genUuid());
				entity.setCode(WebHelper.getInstance().getUniqueCode("LC"));
				entity.setUserCode(user.getUserCode());
				entity.setOperationType("DC170208100002");
				entity.setOperationTypeChild("DC170208100012");
				entity.setCreateUser(user.getUserCode());
				entity.setCreateTime(DateUtil.getSysDateTime());
				entity.setCarbonSum(carbon.multiply(BigDecimal.valueOf(3)).setScale(2, BigDecimal.ROUND_HALF_UP));
				carbonOperationMapper.insertSelective(entity);
			}
		}
	}
}
