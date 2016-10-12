package cn.com.ddhj.service.impl.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.user.TMessageDto;
import cn.com.ddhj.mapper.user.TMessageMapper;
import cn.com.ddhj.mapper.user.TUserLoginMapper;
import cn.com.ddhj.mapper.user.TUserMapper;
import cn.com.ddhj.model.user.TMessage;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.model.user.TUserLogin;
import cn.com.ddhj.result.tuser.MessageData;
import cn.com.ddhj.result.tuser.MessageSelResult;
import cn.com.ddhj.result.tuser.MessageTotal;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.user.ITMessageService;

/**
 * 
 * 类: TMessageServiceImpl <br>
 * 描述: 消息表业务逻辑处理接口实现类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月12日 上午10:50:32
 */
@Service
public class TMessageServiceImpl extends BaseServiceImpl<TMessage, TMessageMapper, TMessageDto>
		implements ITMessageService {

	@Autowired
	private TMessageMapper mapper;
	@Autowired
	private TUserLoginMapper loginMapper;
	@Autowired
	private TUserMapper userMapper;

	/**
	 * 
	 * 方法: findEntityToPage <br>
	 * 
	 * @param dto
	 * @return
	 * @see cn.com.ddhj.service.impl.BaseServiceImpl#findEntityToPage(cn.com.ddhj.dto.BaseDto)
	 */
	@Override
	public MessageData findEntityToPage(TMessageDto dto, String userToken) {
		MessageData result = new MessageData();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login != null) {
			TUser user = userMapper.findTUserByUuid(login.getUserToken());
			if (user != null) {
				dto.setStart(dto.getPageIndex() * dto.getPageSize());
				dto.setReceivedUser(user.getUserCode());
				List<TMessage> list = mapper.findEntityAll(dto);
				int total = mapper.findEntityAllCount(dto);
				if (list != null && list.size() > 0) {
					result.setResultCode(0);
				} else {
					result.setResultCode(-1);
					result.setResultMessage("获取消息列表为空");
					list = new ArrayList<TMessage>();
				}
				result.setRepList(list);
				result.setRepCount(total);
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
	 * 方法: findEntityTotal <br>
	 * 
	 * @param userToken
	 * @return
	 * @see cn.com.ddhj.service.user.ITMessageService#findEntityTotal(java.lang.String)
	 */
	@Override
	public MessageTotal findEntityTotal(Integer isRead, String userToken) {
		MessageTotal result = new MessageTotal();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login != null) {
			TUser user = userMapper.findTUserByUuid(login.getUserToken());
			if (user != null) {
				TMessageDto dto = new TMessageDto();
				dto.setReceivedUser(user.getUserCode());
				dto.setIsRead(isRead);
				int total = mapper.findEntityAllCount(dto);
				result.setTotal(total);
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
	 * 方法: insertSelective <br>
	 * 
	 * @param entity
	 * @param userToken
	 * @return
	 * @see cn.com.ddhj.service.user.ITMessageService#insertSelective(cn.com.ddhj.model.user.TMessage,
	 *      java.lang.String)
	 */
	@Override
	public BaseResult insertSelective(TMessage entity, String userToken) {
		BaseResult result = new BaseResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login != null) {
			TUser user = userMapper.findTUserByUuid(login.getUserToken());
			if (user != null) {

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
	 * 方法: selectByCode <br>
	 * 
	 * @param code
	 * @param userToken
	 * @return
	 * @see cn.com.ddhj.service.user.ITMessageService#selectByCode(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public MessageSelResult selectByCode(String code, String userToken) {
		MessageSelResult result = new MessageSelResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login != null) {
			TUser user = userMapper.findTUserByUuid(login.getUserToken());
			if (user != null) {
				TMessage message = mapper.selectByCode(code);
				result.setMemo(message.getContent());
				result.setOuterCode(message.getOuterCode());
				result.setTime(message.getCreateTime());
				result.setType(message.getTypeName());
				result.setResultCode(0);
				result.setResultMessage("查询消息成功");
				message.setIsRead(1);
				message.setCode(code);
				mapper.updateByCode(message);
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
