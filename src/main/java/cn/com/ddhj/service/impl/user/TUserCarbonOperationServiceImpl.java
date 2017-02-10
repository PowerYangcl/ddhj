package cn.com.ddhj.service.impl.user;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.dto.user.TUserCarbonOperationDto;
import cn.com.ddhj.mapper.user.TUserCarbonOperationMapper;
import cn.com.ddhj.mapper.user.TUserLoginMapper;
import cn.com.ddhj.mapper.user.TUserMapper;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.model.user.TUserCarbonOperation;
import cn.com.ddhj.model.user.TUserLogin;
import cn.com.ddhj.result.carbon.CarbonDetailResult;
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
				List<TUserCarbonOperation> incomeCarbon = mapper.findCarbonOperationByTime(dto);
				if (incomeCarbon != null && incomeCarbon.size() > 0) {
					result.setIncomeCarbon(incomeCarbon);
				}
				/**
				 * 碳币支出信息
				 */
				dto.setOperationType("DC170208100002");
				List<TUserCarbonOperation> expendCarbon = mapper.findCarbonOperationByTime(dto);
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

}
