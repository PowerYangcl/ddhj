package cn.com.ddhj.service.impl.user;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.user.TUserCarbonOperationDto;
import cn.com.ddhj.helper.PropHelper;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TOrderRechargeMapper;
import cn.com.ddhj.mapper.TPresentCarbonMapper;
import cn.com.ddhj.mapper.user.TUserCarbonOperationMapper;
import cn.com.ddhj.mapper.user.TUserLoginMapper;
import cn.com.ddhj.mapper.user.TUserMapper;
import cn.com.ddhj.model.TOrderRecharge;
import cn.com.ddhj.model.TPresentCarbon;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.model.user.TUserCarbonOperation;
import cn.com.ddhj.model.user.TUserLogin;
import cn.com.ddhj.result.DataResult;
import cn.com.ddhj.result.carbon.CarbonDetailResult;
import cn.com.ddhj.result.carbon.CarbonRechargeResult;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.user.ITUserCarbonOperationService;
import cn.com.ddhj.util.Constant;
import cn.com.ddhj.util.DateUtil;

/**
 * 
 * 类: ITUserCarbonOperationService <br>
 * 描述: 碳币收支情况相关业务逻辑处理接口实现类 <br>
 * 作者: zhy<br>
 * 时间: 2017年2月10日 上午10:04:38
 */
@Service
public class TUserCarbonOperationServiceImpl
		extends BaseServiceImpl<TUserCarbonOperation, TUserCarbonOperationMapper, TUserCarbonOperationDto>
		implements ITUserCarbonOperationService {

	@Autowired
	private TUserCarbonOperationMapper mapper;
	@Autowired
	private TUserLoginMapper loginMapper;
	@Autowired
	private TUserMapper userMapper;
	@Autowired
	private TOrderRechargeMapper rechargeMapper;
	@Autowired
	private TPresentCarbonMapper presentCarbonMapper;

	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 
	 * 方法: getCarbonOperationDetail <br>
	 * 
	 * @return
	 * @see cn.com.ddhj.service.user.ITUserCarbonOperationService#getCarbonOperationDetail()
	 */
	@Override
	public CarbonDetailResult getCarbonOperationDetail(String userToken) {
		CarbonDetailResult result = new CarbonDetailResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login != null) {
			TUser user = userMapper.findTUserByUuid(login.getUserToken());
			if (user != null) {
				TUserCarbonOperationDto dto = new TUserCarbonOperationDto();
				dto.setUserCode(user.getUserCode());
				List<TUserCarbonOperation> list = mapper.getCarbonOperationSum(dto);
				if (list != null && list.size() > 0) {
					for (TUserCarbonOperation model : list) {
						if (StringUtils.equals("DC170208100004", model.getOperationTypeChild())) {
							/**
							 * 步行炭币
							 */
							result.setWalkCarbon(model.getCarbonSum());
						} else if (StringUtils.equals("DC170208100005", model.getOperationTypeChild())) {
							/**
							 * 新能源里程炭币
							 */
							result.setNewEnergyCarbon(model.getCarbonSum());
						} else if (StringUtils.equals("DC170208100006", model.getOperationTypeChild())) {
							/**
							 * 购买炭币
							 */
							result.setBuyCarbon(model.getCarbonSum());
						} else if (StringUtils.equals("DC170208100007", model.getOperationTypeChild())) {
							/**
							 * 兑换环境质量报告
							 */
							result.setExchangeReport(model.getCarbonSum());
						} else if (StringUtils.equals("DC170208100008", model.getOperationTypeChild())) {
							/**
							 * 兑换奖品
							 */
							result.setExchangeGift(model.getCarbonSum());
						} else if (StringUtils.equals("DC170208100009", model.getOperationTypeChild())) {
							/**
							 * 炒碳支出
							 */
							result.setSpendForCarbonDeal(model.getCarbonSum());
						} else if (StringUtils.equals("DC170208100010", model.getOperationTypeChild())) {
							/**
							 * 炒碳收入
							 */
							result.setInComeFormCarbonDeal(model.getCarbonSum());
						}
					}
				} else {
					result.setResultCode(Constant.RESULT_ERROR);
					result.setResultMessage("查询数据错误!");
				}
				/**
				 * 碳币收入信息
				 */
				dto.setOperationType("DC170208100002");
				List<TUserCarbonOperation> incomeCarbon = mapper.findCarbonOperationByDate(dto);
				if (incomeCarbon != null && incomeCarbon.size() > 0) {
					result.setIncomeCarbon(incomeCarbon);
				}
				/**
				 * 碳币支出信息
				 */
				dto.setOperationType("DC170208100003");
				List<TUserCarbonOperation> expendCarbon = mapper.findCarbonOperationByDate(dto);
				if (expendCarbon != null && expendCarbon.size() > 0) {
					result.setExpendCarbon(expendCarbon);
				}
			} else {
				result.setResultCode(Constant.RESULT_ERROR);
				result.setResultMessage("用户不存在");
			}
		} else {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户未登录");
		}

		return result;
	}

	/**
	 * 
	 * 方法: getCarbonOperationByType <br>
	 * 
	 * @param userToken
	 * @param type
	 * @return
	 * @see cn.com.ddhj.service.user.ITUserCarbonOperationService#getCarbonOperationByType(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public DataResult getCarbonOperationByType(String userToken, TUserCarbonOperationDto dto) {
		DataResult result = new DataResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		List<TUserCarbonOperation> list = null;
		if (login != null) {
			TUser user = userMapper.findTUserByUuid(login.getUserToken());
			if (user != null) {
				dto.setUserCode(user.getUserCode());
				if (dto.getPageIndex() != null && dto.getPageSize() != null) {
					dto.setStart(dto.getPageIndex() * dto.getPageSize());
					dto.setPageSize(dto.getPageSize());
				}
				list = mapper.findCarbonOperationByTime(dto);
				if (list != null && list.size() > 0) {
					if (dto.getDay() != null) {
						list = trimData(list, dto.getDay());
					}
					result.setData(list);
					result.setResultCode(Constant.RESULT_SUCCESS);
					result.setResultMessage("获取数据成功");
				} else {
					result.setData(new ArrayList<TUserCarbonOperation>());
					result.setResultCode(Constant.RESULT_NULL);
					result.setResultMessage("查询数据为空");
				}
			}
		}
		return result;
	}

	/**
	 * 用户充值碳币
	 */
	@Override
	public CarbonRechargeResult carbonRecharge(String userToken, TOrderRecharge recharge) {
		CarbonRechargeResult result = new CarbonRechargeResult();
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
		}

		// Recharge order
		String rechargeOrder = WebHelper.getInstance().getUniqueCode("RO");
		recharge.setCode(rechargeOrder);
		recharge.setBuyerCode(user.getUserCode());
		recharge.setCreateTime(format.format(new Date()));
		recharge.setCreateUser(user.getUserCode());
		recharge.setStatus(new Integer(0));
		recharge.setUuid(UUID.randomUUID().toString().replace("-", ""));
		int success = rechargeMapper.insertSelective(recharge);
		if (success == 1) {
			result.setOrderCode(rechargeOrder);
			result.setResultCode(Constant.RESULT_SUCCESS);
			result.setResultMessage("创建充值订单成功");
		} else {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("创建充值订单失败");
		}
		return result;
	}

	public TOrderRecharge selectRechargeRecByOrderCode(String orderCode) {
		return rechargeMapper.selectByOrderCode(orderCode);
	}

	public int updateRechargeRec(TOrderRecharge rec) {
		return rechargeMapper.updateByPrimaryKey(rec);
	}

	public BaseResult insertSelective(TUserCarbonOperation carbonOperation) {
		BaseResult result = new BaseResult();
		int success = mapper.insertSelective(carbonOperation);
		if (success == 1) {
			result.setResultCode(Constant.RESULT_SUCCESS);
		} else {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("插入碳币购买记录失败");
		}
		return result;
	}

	/**
	 * 显示碳币交易明细 精确到秒
	 */
	@Override
	public DataResult findCarbonOperationDetail(TUserCarbonOperationDto dto, String userToken) {
		DataResult result = new DataResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
//		List<TUserCarbonOperation> list = null;
		if (login != null) {
			TUser user = userMapper.findTUserByUuid(login.getUserToken());
			if (user != null) {
				dto.setUserCode(user.getUserCode());
				if (dto.getPageIndex() != null && dto.getPageSize() != null) {
					dto.setStart(dto.getPageIndex() * dto.getPageSize());
					dto.setPageSize(dto.getPageSize());
				}
				
				List<TUserCarbonOperation> list = null;
				if(StringUtils.isBlank(dto.getOperationType()) && StringUtils.isBlank(dto.getOperationTypeChild())) {
					//按全部类型查询
					list = mapper.findCarbonOperationDetail(dto);
				} else {
					if(StringUtils.isNotBlank(dto.getOperationTypeChild()) && dto.getOperationTypeChild().equals("DC170208100004")) {
						//按步行换碳币类型查询
						list = mapper.findCarbonOperationDetailDailyOne(dto);
					} else {
						//其他查询
						list = mapper.findCarbonOperationDetail(dto);
					}
				}
				
//				SimpleDateFormat sdf = new SimpleDateFormat("");
//				
//				Map<String, TUserCarbonOperation> stepMap = new HashMap<String, TUserCarbonOperation>();
//				List<TUserCarbonOperation> list = mapper.findCarbonOperationDetail(dto);
//				List<TUserCarbonOperation> delList = new LinkedList<TUserCarbonOperation>();
//				if(list != null) {
//					for(TUserCarbonOperation tco : list) {
//						if(tco.getOperationTypeChild().equals("DC170208100004")) {
//							//步行换碳币按日期合并
//							String date = tco.getCreateTime().substring(0, tco.getCreateTime().indexOf(" "));
//							if(stepMap.containsKey(date)) {
//								TUserCarbonOperation oneday = stepMap.get(date);
//								oneday.setCarbonSum(oneday.getCarbonSum().add(tco.getCarbonSum()).setScale(2, BigDecimal.ROUND_HALF_UP));
//								delList.add(tco);
//							} else {
//								stepMap.put(date, tco);
//							}
//						}
//					}
//					
//					list.removeAll(delList);
//					Collections.sort(list, new Comparator<TUserCarbonOperation>() {
//						@Override
//						public int compare(TUserCarbonOperation o1, TUserCarbonOperation o2) {
//							return o2.getCreateTime().compareTo(o1.getCreateTime());
//						}
//					});
//					
////					list = list.subList(0, (dto.getPageSize() / 3) > list.size() ? list.size() : (dto.getPageSize() / 3));
//				}
				
				
				
//				if(StringUtils.isBlank(dto.getOperationType()) 
//						&& StringUtils.isBlank(dto.getOperationTypeChild())) {
//					//前端按全部类型查询所有碳币收支记录
//					//先查询步行换碳币记录
//					list = new LinkedList<TUserCarbonOperation>();
//					dto.setOperationType(null);
//					dto.setOperationTypeChild("DC170208100004");
//					List<TUserCarbonOperation> oneList = mapper.findCarbonOperationDetailDailyOne(dto);
//					list.addAll(oneList);
//					//再查询其他类型换碳币记录
//					dto.setExcludeChildType("true");
//					dto.setOperationType(null);
//					dto.setOperationTypeChild("DC170208100004");
//					List<TUserCarbonOperation> otherList = mapper.findCarbonOperationDetail(dto);
//					list.addAll(otherList);
//					Collections.sort(list, new Comparator<TUserCarbonOperation>() {
//						@Override
//						public int compare(TUserCarbonOperation o1, TUserCarbonOperation o2) {
//							return o2.getCreateTime().compareTo(o1.getCreateTime());
//						}
//					});
//					
//					list = list.subList(0, dto.getPageSize() > list.size() ? list.size() : dto.getPageSize());
//					
//				} else if(StringUtils.isNotBlank(dto.getOperationTypeChild())) {
//					//按碳币小类型查
//					if(dto.getOperationTypeChild().equals("DC170208100004")) {
//						//按碳币小类型-步行换碳币查,按日期合并---因为同步的步行碳币记录过多
//						list = mapper.findCarbonOperationDetailDailyOne(dto);
//					} else {
//						//按碳币小类型查,不按日期合并
//						list = mapper.findCarbonOperationDetail(dto);
//					}
//				} else if(StringUtils.isNotBlank(dto.getOperationType())) {
//					//按碳币操作大类型查
//					if(dto.getOperationType().equals("DC170208100002")) {
//						//步行换碳币收入小类
//						dto.setOperationTypeChild("DC170208100004");
//						list = mapper.findCarbonOperationDetailDailyOne(dto);
//						//其他收入小类
//						dto.setExcludeChildType("true");
//						List<TUserCarbonOperation> otherList = mapper.findCarbonOperationDetail(dto);
//						list.addAll(otherList);
//						Collections.sort(list, new Comparator<TUserCarbonOperation>() {
//							@Override
//							public int compare(TUserCarbonOperation o1, TUserCarbonOperation o2) {
//								return o2.getCreateTime().compareTo(o1.getCreateTime());
//							}
//						});
//						
//						list = list.subList(0, dto.getPageSize() > list.size() ? list.size() : dto.getPageSize());
//					}
//					
//					list = mapper.findCarbonOperationDetail(dto);
//				}
				
				if (list != null && list.size() > 0) {
					result.setData(list);
					result.setResultCode(Constant.RESULT_SUCCESS);
					result.setResultMessage("获取数据成功");
				} else {
					result.setData(new ArrayList<TUserCarbonOperation>());
					result.setResultCode(Constant.RESULT_NULL);
					result.setResultMessage("查询数据为空");
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * 方法: trimData <br>
	 * 描述: 初始化碳币操作记录数据 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年8月6日 下午4:07:24
	 * 
	 * @param list
	 * @param day
	 * @return
	 */
	private List<TUserCarbonOperation> trimData(List<TUserCarbonOperation> list, int days) {
		List<TUserCarbonOperation> data = new ArrayList<TUserCarbonOperation>();
		try {
			int day = 0;
			// 开始时间
			Calendar startCal = Calendar.getInstance();
			startCal.setTime(new Date());
			startCal.set(startCal.get(Calendar.YEAR), startCal.get(Calendar.MONTH),
					startCal.get(Calendar.DAY_OF_MONTH) - days);
			// 结束时间
			Calendar endCal = Calendar.getInstance();
			endCal.set(endCal.get(Calendar.YEAR), endCal.get(Calendar.MONTH), endCal.get(Calendar.DAY_OF_MONTH) - 1);
			endCal.setTime(new Date());
			while (startCal.getTime().before(endCal.getTime())) {
				startCal.set(Calendar.DATE, startCal.get(Calendar.DATE) + day);
				String date = DateUtil.dateToString(startCal.getTime());
				TUserCarbonOperation entity = new TUserCarbonOperation();
				entity.setCarbonSum(BigDecimal.ZERO);
				entity.setOperationTypeChild("DC170208100004");
				entity.setOperationTypeChildName("步行碳币");
				entity.setCreateTime(date);
				data.add(entity);
				day = 1;
			}
			if (list != null && list.size() > 0) {
				for (int i = 0; i < data.size(); i++) {
					TUserCarbonOperation obj = data.get(i);
					for (TUserCarbonOperation oper : list) {
						if (StringUtils.equals(obj.getCreateTime(), oper.getCreateTime())) {
							obj.setOperationTypeChild(oper.getOperationTypeChild());
							obj.setOperationTypeChildName(oper.getOperationTypeChildName());
							obj.setCarbonSum(oper.getCarbonSum());
							break;
						}
					}
				}
				Collections.reverse(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 分享报告赠送碳币接口
	 * @author zht
	 */
	public BaseResult presentCarbon(String userToken) {
		BaseResult result = new BaseResult();
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
		}
		
		//查询已赠送该用户的碳币数
		Double carbon = presentCarbonMapper.selectSumCarbonByUserCode(user.getUserCode());
		if(carbon == null) carbon = 0.0;
		String ratio = PropHelper.getValue("carbon_money_ratio");
		Double rmb = carbon * Double.parseDouble(ratio);
		Double limit = Double.valueOf(PropHelper.getValue("present_carbon_total_rmb"));
		if(rmb >= limit) {
			//将已赠送而碳币转换成人民币,判断是否超额
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("该用户分享送碳币额度已满");
			return result;
		}
		
		Double once = Double.valueOf(PropHelper.getValue("present_carbon_once"));
		Double presentThis = (carbon + once) * Double.parseDouble(ratio);
		if(presentThis >= limit) {
			//本次赠送后就超额,则取最大额度(RMB)对应的碳币数为本次赠送碳币数
			BigDecimal b = new BigDecimal(limit / Double.parseDouble(ratio));  
			presentThis = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
		} else {
			//本次赠送后不超额,赠送碳币数为原碳币数加本次赠送数
			presentThis = carbon + once;
		}
		
		//新增或修改用户分享报告赠送碳币数
		int count = 0;
		TPresentCarbon tc = presentCarbonMapper.selectByCode(user.getUserCode());
		if(tc == null) {
			tc = new TPresentCarbon();
			tc.setUserCode(user.getUserCode());
			tc.setCarbonMoney(presentThis);
			tc.setUuid(UUID.randomUUID().toString().replaceAll("-",""));
			tc.setCreateTime(DateUtil.getSysDateTime());
			tc.setCreateUser(user.getUserCode());
			tc.setUpdateUser(user.getUserCode());
			tc.setUpdateTime(DateUtil.getSysDateTime());
			count = presentCarbonMapper.insertSelective(tc);
		} else {
			tc.setUserCode(user.getUserCode());
			tc.setCarbonMoney(presentThis);
			tc.setUpdateUser(user.getUserCode());
			tc.setUpdateTime(DateUtil.getSysDateTime());
			count = presentCarbonMapper.updateByCode(tc);
		}
		
		if(count == 1) {
			//修改用户表中用户碳币总数
			user.setCarbonMoney(new BigDecimal(once).setScale(2, BigDecimal.ROUND_HALF_UP));
			count = userMapper.updateCarbonByUserCode(user);
			if(count == 1) {
				// 增加碳币购买记录
				TUserCarbonOperation carbonOperation = new TUserCarbonOperation();
				carbonOperation.setUuid(WebHelper.getInstance().genUuid());
				carbonOperation.setCode(WebHelper.getInstance().getUniqueCode("LC"));
				carbonOperation.setUserCode(user.getUserCode());
				carbonOperation.setOperationType("DC170208100002");
				carbonOperation.setOperationTypeChild("DC170208100011");
				carbonOperation.setCarbonSum(new BigDecimal(once).setScale(2,  BigDecimal.ROUND_HALF_UP));
				carbonOperation.setCreateUser(user.getUserCode());
				carbonOperation.setCreateTime(DateUtil.getSysDateTime());
				count = mapper.insertSelective(carbonOperation);
				if(count == 1) {
					result.setResultCode(Constant.RESULT_SUCCESS);
					result.setResultMessage("赠送碳币成功.增加" + once + "个碳币");
				} else {
					result.setResultCode(Constant.RESULT_ERROR);
					result.setResultMessage("赠送碳币失败");
				}
			} else {
				result.setResultCode(Constant.RESULT_ERROR);
				result.setResultMessage("赠送碳币失败");
			}
		} else {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("赠送碳币失败");
		}
		return result;
	 }
}
