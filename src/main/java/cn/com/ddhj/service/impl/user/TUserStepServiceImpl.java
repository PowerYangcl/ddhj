package cn.com.ddhj.service.impl.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.user.TUserStepDto;
import cn.com.ddhj.mapper.user.TUserLoginMapper;
import cn.com.ddhj.mapper.user.TUserMapper;
import cn.com.ddhj.mapper.user.TUserStepMapper;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.model.user.TUserLogin;
import cn.com.ddhj.model.user.TUserStep;
import cn.com.ddhj.result.tuser.UserStepResult;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.user.ITUserStepService;
import cn.com.ddhj.util.DateUtil;

/**
 * 
 * 类: TUserStepServiceImpl <br>
 * 描述: TODO <br>
 * 作者: zhy<br>
 * 时间: 2016年10月14日 下午5:40:36
 */
@Service
public class TUserStepServiceImpl extends BaseServiceImpl<TUserStep, TUserStepMapper, TUserStepDto>
		implements ITUserStepService {

	@Autowired
	private TUserStepMapper mapper;
	@Autowired
	private TUserLoginMapper loginMapper;
	@Autowired
	private TUserMapper userMapper;

	/**
	 * 
	 * 方法: batchInstart <br>
	 * 描述: TODO
	 * 
	 * @param data
	 * @param userTocken
	 * @return
	 * @see cn.com.ddhj.service.user.ITUserStepService#batchInstart(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public BaseResult batchInstart(String data, String userTocken) {
		int isBinding = 0;
		String userCode = "";
		TUserLogin login = loginMapper.findLoginByUuid(userTocken);
		if (login != null) {
			TUser user = userMapper.findTUserByUuid(login.getUserToken());
			if (user != null) {
				isBinding = 1;
				userCode = user.getUserCode();
			}
		}
		BaseResult result = new BaseResult();
		if (StringUtils.isNotBlank(data)) {
			JSONObject step = JSONObject.parseObject(data);
			if (step != null) {
				String equipmentCode = step.getString("equipmentCode");
				JSONArray array = step.getJSONArray("time");
				if (array != null && array.size() > 0) {
					List<TUserStep> list = new ArrayList<TUserStep>();
					for (int i = 0; i < array.size(); i++) {
						JSONObject obj = array.getJSONObject(i);
						TUserStep entity = new TUserStep();
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
	 * 描述: TODO
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
			TUserStepDto dto = new TUserStepDto();
			// 历史七天记录
			String[] weekDate = getDates(7);
			dto.setStartDate(weekDate[0]);
			dto.setEndDate(weekDate[1]);
			List<TUserStep> week = mapper.findUserStepData(dto);
			week = trimData(week, weekDate[0], weekDate[1]);
			result.setWeek(week);
			// 历史一个月记录
			String[] monthDate = getDates(30);
			dto.setStartDate(monthDate[0]);
			dto.setEndDate(monthDate[1]);
			List<TUserStep> month = mapper.findUserStepData(dto);
			month = trimData(month, monthDate[0], monthDate[1]);
			result.setMonth(month);
			// 历史一年记录
			String[] yearDate = getDates(30);
			dto.setStartDate(yearDate[0]);
			dto.setEndDate(yearDate[1]);
			List<TUserStep> year = mapper.findUserStepData(dto);
			year = trimData(year, yearDate[0], yearDate[1]);
			result.setYear(year);
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
	 * 方法: getDates <br>
	 * 描述: 根据天数查询开始日期和结束日期数组 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月14日 下午5:24:55
	 * 
	 * @param day
	 * @return
	 */
	private String[] getDates(int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) - (day + 1));
		String startDate = DateUtil.dateToString(cal.getTime());
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		cal2.set(cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH), cal2.get(Calendar.DAY_OF_MONTH) - 1);
		String endDate = DateUtil.dateToString(cal2.getTime());
		return new String[] { startDate, endDate };
	}

	/**
	 * 
	 * 方法: trimData <br>
	 * 描述: TODO <br>
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
			List<String> dateList = new ArrayList<String>();
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
				dateList.add(date);
				day = 1;
			}
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					TUserStep entity = list.get(i);
					if (!dateList.contains(entity.getCreateDate())) {
						TUserStep model = new TUserStep();
						model.setCreateDate(dateList.get(i));
						model.setStep(0);
						newList.add(model);
					} else {
						newList.add(entity);
					}
				}
			} else {
				// 如果查询列表为空，默认step均为0
				for (int i = 0; i < dateList.size(); i++) {
					TUserStep entity = new TUserStep();
					entity.setCreateDate(dateList.get(i));
					entity.setStep(0);
					newList.add(entity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newList;
	}
}
