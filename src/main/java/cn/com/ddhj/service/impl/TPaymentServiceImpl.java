package cn.com.ddhj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.base.PageResult;
import cn.com.ddhj.dto.TPaymentDto;
import cn.com.ddhj.mapper.TPaymentMapper;
import cn.com.ddhj.mapper.user.TUserLoginMapper;
import cn.com.ddhj.mapper.user.TUserMapper;
import cn.com.ddhj.model.TPayment;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.service.IPaymentService;
import cn.com.ddhj.util.Constant;
import cn.com.ddhj.util.DateUtil;

@Service
public class TPaymentServiceImpl extends BaseServiceImpl<TPayment, TPaymentMapper, TPaymentDto>
		implements IPaymentService {
	@Autowired
	private TPaymentMapper mapper;
	@Autowired
	private TUserLoginMapper loginMapper;
	@Autowired
	private TUserMapper userMapper;

	@Override
	public BaseResult insertSelective(TPayment entity, String userToken) {
		BaseResult result = new BaseResult();
		
		TPayment p = mapper.selectByOrderCode(entity.getOrderCode());
		if(p != null ){
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("订单已支付"); 
			return result;
		}
		
		TUser user = userMapper.findTUserByUuid(userToken);
		if (user != null) {
			entity.setDealtime(DateUtil.getSysDateTime());
			entity.setCreateUser(user.getUserCode());
			entity.setCreateTime(DateUtil.getSysDateTime());
			entity.setUpdateUser(user.getUserCode());
			entity.setUpdateTime(DateUtil.getSysDateTime());
			mapper.payInsertSelective(entity);
			result.setResultCode(Constant.RESULT_SUCCESS);
			result.setResultMessage("记录支付成功");
		}else {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("无效用户");
		}
//		TUserLogin login = loginMapper.findLoginByUuid(userToken);
//		if (login != null) {
//		} else {
//			result.setResultCode(Constant.RESULT_ERROR);
//			result.setResultMessage("无效用户");
//		}
		return result;
	}
	
	@Override
	public BaseResult insertSelective(TPayment entity) {
		BaseResult result = new BaseResult();
		int success = mapper.insertSelective(entity);
		if(success == 1) {
			result.setResultCode(Constant.RESULT_SUCCESS);
		} else {
			result.setResultCode(Constant.RESULT_ERROR);
		}
		return result;	
	}

	@Override
	public BaseResult updateByCode(TPayment entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult deleteByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TPayment selectByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TPayment selectByEntity(TPayment entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult findEntityToPage(TPaymentDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
