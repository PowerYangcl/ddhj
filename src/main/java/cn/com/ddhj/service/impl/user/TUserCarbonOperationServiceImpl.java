package cn.com.ddhj.service.impl.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.dto.user.TUserCarbonOperationDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TOrderRechargeMapper;
import cn.com.ddhj.mapper.user.TUserCarbonOperationMapper;
import cn.com.ddhj.mapper.user.TUserLoginMapper;
import cn.com.ddhj.mapper.user.TUserMapper;
import cn.com.ddhj.model.TOrderRecharge;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.model.user.TUserCarbonOperation;
import cn.com.ddhj.model.user.TUserLogin;
import cn.com.ddhj.result.carbon.CarbonDetailResult;
import cn.com.ddhj.result.carbon.CarbonRechargeResult;
import cn.com.ddhj.result.carbon.CarbonTypeDetailResult;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.user.ITUserCarbonOperationService;

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
							 * 开户炒碳
							 */
							result.setSpeculateCarbon(model.getCarbonSum());
						}
					}
				} else {
					result.setResultCode(-1);
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
				result.setResultCode(-1);
				result.setResultMessage("用户不存在");
			}
		} else {
			result.setResultCode(-1);
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
	public CarbonTypeDetailResult getCarbonOperationByType(String userToken, String type) {
		CarbonTypeDetailResult result = new CarbonTypeDetailResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		List<TUserCarbonOperation> list = null;
		if (login != null) {
			TUser user = userMapper.findTUserByUuid(login.getUserToken());
			if (user != null) {
				TUserCarbonOperationDto dto = new TUserCarbonOperationDto();
				dto.setUserCode(user.getUserCode());
				dto.setOperationTypeChild(type);
				list = mapper.findCarbonOperationByTime(dto);
				if (list != null && list.size() > 0) {
					result.setList(list);
					result.setResultCode(0);
					result.setResultMessage("获取数据成功");
				} else {
					result.setList(new ArrayList<TUserCarbonOperation>());
					result.setResultCode(-1);
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
		if(login == null) {
			result.setResultCode(-1);
			result.setResultMessage("用户未登录");
			return result;
		}
		
		TUser user = userMapper.findTUserByUuid(login.getUserToken());
		if(user == null) {
			result.setResultCode(-1);
			result.setResultMessage("用户不存在");
			return result;
		}
		
		//Recharge order
		String rechargeOrder = WebHelper.getInstance().getUniqueCode("RO");
		recharge.setCode(rechargeOrder);
		recharge.setBuyerCode(user.getUserCode());
		recharge.setCreateTime(format.format(new Date()));
		recharge.setCreateUser(user.getUserCode());
		recharge.setStatus(new Integer(0));
		recharge.setUuid(UUID.randomUUID().toString().replace("-", ""));
		int success = rechargeMapper.insertSelective(recharge);
		if(success ==1) {
			result.setOrderCode(rechargeOrder);
			result.setResultCode(1);
			result.setResultMessage("创建充值订单成功");
		} else {
			result.setResultCode(-1);
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
}
