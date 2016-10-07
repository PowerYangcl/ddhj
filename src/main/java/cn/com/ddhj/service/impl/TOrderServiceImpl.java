package cn.com.ddhj.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.TOrderDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TOrderMapper;
import cn.com.ddhj.mapper.TUserLoginMapper;
import cn.com.ddhj.mapper.TUserMapper;
import cn.com.ddhj.model.TOrder;
import cn.com.ddhj.model.TUser;
import cn.com.ddhj.model.TUserLogin;
import cn.com.ddhj.result.order.TOrderResult;
import cn.com.ddhj.service.ITOrderService;
import cn.com.ddhj.util.DateUtil;

/**
 * 
 * 类: TOrderServiceImpl <br>
 * 描述: 订单表业务处理接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月7日 下午12:59:33
 */
@Service
public class TOrderServiceImpl extends BaseServiceImpl<TOrder, TOrderMapper, TOrderDto> implements ITOrderService {

	@Autowired
	private TOrderMapper mapper;
	@Autowired
	private TUserLoginMapper loginMapper;
	@Autowired
	private TUserMapper userMapper;

	@Override
	public TOrderResult findEntityToPage(TOrderDto dto) {
		TOrderResult result = new TOrderResult();
		dto.setStart(dto.getPageIndex() * dto.getPageSize());
		List<TOrder> list = mapper.findEntityAll(dto);
		int total = mapper.findEntityAllCount(dto);
		if (list != null && list.size() > 0) {
			result.setResultCode(0);
		} else {
			result.setResultCode(-1);
			result.setResultMessage("获取数据为空");
			list = new ArrayList<TOrder>();
		}
		result.setRepList(list);
		result.setRepCount(total);
		return result;
	}

	/**
	 * 
	 * 方法: insertSelective <br>
	 * 描述: TODO
	 * 
	 * @param entity
	 * @param userToken
	 * @return
	 * @see cn.com.ddhj.service.ITOrderService#insertSelective(cn.com.ddhj.model.TOrder,
	 *      java.lang.String)
	 */
	@Override
	public BaseResult insertSelective(TOrder entity, String userToken) {
		BaseResult result = new BaseResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login != null) {
			TUser user = userMapper.findTUserByUuid(login.getUserToken());
			if (user != null) {
				entity.setCode(WebHelper.getInstance().getUniqueCode("D"));
				entity.setCreateUser(user.getUserCode());
				entity.setCreateTime(DateUtil.getSysDateTime());
				entity.setUpdateUser(user.getUserCode());
				entity.setUpdateTime(DateUtil.getSysDateTime());
				mapper.insertSelective(entity);
				result.setResultCode(0);
				result.setResultMessage("创建订单成功");
			} else {
				result.setResultCode(-1);
				result.setResultMessage("无效用户");
			}
		} else {
			result.setResultCode(-1);
			result.setResultMessage("无效用户");
		}
		return result;
	}

	/**
	 * 
	 * 方法: updateByCode <br>
	 * 描述: TODO
	 * 
	 * @param entity
	 * @param userToken
	 * @return
	 * @see cn.com.ddhj.service.ITOrderService#updateByCode(cn.com.ddhj.model.TOrder,
	 *      java.lang.String)
	 */
	@Override
	public BaseResult updateByCode(TOrder entity, String userToken) {
		BaseResult result = new BaseResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login != null) {
			TUser user = userMapper.findTUserByUuid(login.getUserToken());
			if (user != null) {
				entity.setUpdateUser(user.getUserCode());
				entity.setUpdateTime(DateUtil.getSysDateTime());
				mapper.updateByCode(entity);
				result.setResultCode(0);
				result.setResultMessage("编辑订单成功");
			} else {
				result.setResultCode(-1);
				result.setResultMessage("无效用户");
			}
		} else {
			result.setResultCode(-1);
			result.setResultMessage("无效用户");
		}
		return result;
	}

}
