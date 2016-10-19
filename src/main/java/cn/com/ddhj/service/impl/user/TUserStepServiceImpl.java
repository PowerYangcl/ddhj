package cn.com.ddhj.service.impl.user;

import java.util.ArrayList;
import java.util.Calendar;
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
import cn.com.ddhj.mapper.user.TUserMapper;
import cn.com.ddhj.mapper.user.TUserStepMapper;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.model.user.TUserStep;
import cn.com.ddhj.result.tuser.UserStepResult;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.user.ITUserStepService;
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
						entity.setStep(obj.getInteger("step"));
						entity.setCreateDate(obj.getString("date"));
						entity.setIsBinding(isBinding);
						entity.setUserCode(userCode);
						entity.setCreateDate(DateUtil.getSysDate());
						list.add(entity);
					}
					int flag = mapper.batchInstart(list);
					if (flag > 0) {
						result.setResultCode(0);
						result.setResultMessage("同步成功");
					} else {
						result.setResultCode(-1);
						result.setResultMessage("批量同步计步数据错误");
					}
				}else{
					result.setResultCode(-1);
					result.setResultMessage("同步计步数据为空");
				}
			} else {
				result.setResultCode(-1);
				result.setResultMessage("请确认数据是否为json");
			}
		} else {
			result.setResultCode(-1);
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
	public UserStepResult findUserStepData(String equipmentCode) {
		UserStepResult result = new UserStepResult();
		if (StringUtils.isNotBlank(equipmentCode)) {
			// 历史七天记录
			result.setWeek(getWeekStep(equipmentCode));
			// 历史一个月记录
			result.setMonth(getMonthStep(equipmentCode));
			// 历史一年记录
			result.setYear(getYearStep(equipmentCode));

			result.setResultCode(0);
			result.setResultMessage("查询列表成功");
		} else {
			result.setResultCode(-1);
			result.setResultMessage("设备编码为空");
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
	private List<TUserStep> getWeekStep(String equipmentCode) {
		// 获取开始日期和结束日期
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) - 7);
		String startDate = DateUtil.dateToString(cal.getTime());
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		cal2.set(cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH), cal2.get(Calendar.DAY_OF_MONTH) - 1);
		String endDate = DateUtil.dateToString(cal2.getTime());
		TUserStepDto dto = new TUserStepDto();
		dto.setEquipmentCode(equipmentCode);
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		List<TUserStep> list = mapper.findUserStepData(dto);
		if (list == null || list.size() <= 0) {
			list = new ArrayList<TUserStep>();
		}
		list = trimData(list, startDate, endDate);
		return list;
	}

	private List<TUserStep> getMonthStep(String equipmentCode) {
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
		dto.setEquipmentCode(equipmentCode);
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
	private List<Map<String, Integer>> getYearStep(String equipmentCode) {
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
			dto.setEquipmentCode(equipmentCode);
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
	public static int getCurrentMonthDay(Calendar cal) {
		cal.set(Calendar.DATE, 1);
		cal.roll(Calendar.DATE, -1);
		int maxDate = cal.get(Calendar.DATE);
		return maxDate;
	}

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) - 14, cal.get(Calendar.DAY_OF_MONTH));
		System.out.println(cal.get(Calendar.MONTH));
		System.out.println(DateUtil.dateTimeToString(cal.getTime()));
	}
}
