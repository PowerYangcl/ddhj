package cn.com.ddhj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.base.PageResult;
import cn.com.ddhj.dto.TPaymentDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TOrderMapper;
import cn.com.ddhj.mapper.TPaymentMapper;
import cn.com.ddhj.mapper.TUserLoginMapper;
import cn.com.ddhj.mapper.TUserMapper;
import cn.com.ddhj.model.TPayment;
import cn.com.ddhj.model.TUser;
import cn.com.ddhj.model.TUserLogin;
import cn.com.ddhj.result.order.OrderAddResult;
import cn.com.ddhj.service.IPaymentService;
import cn.com.ddhj.util.DateUtil;

@Service
public class TPaymentServiceImpl extends BaseServiceImpl<TPayment, TPaymentMapper, TPaymentDto> implements IPaymentService {
	@Autowired
	private TPaymentMapper mapper;
	@Autowired
	private TUserLoginMapper loginMapper;
	@Autowired
	private TUserMapper userMapper;	
	
	@Override
	public BaseResult insertSelective(TPayment entity, String userToken) {
		BaseResult result = new BaseResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login != null) {
			TUser user = userMapper.findTUserByUuid(login.getUserToken());
			if (user != null) {
				entity.setDealtime(DateUtil.getSysDateTime());
				entity.setCreateUser(user.getUserCode());
				entity.setCreateTime(DateUtil.getSysDateTime());
				entity.setUpdateUser(user.getUserCode());
				entity.setUpdateTime(DateUtil.getSysDateTime());
				mapper.insertSelective(entity);
				result.setResultCode(1);
				result.setResultMessage("记录支付成功");
			}
		} else {
			result.setResultCode(-1);
			result.setResultMessage("无效用户");
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
	public PageResult<TPayment> findEntityToPage(TPaymentDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
