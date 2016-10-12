package cn.com.ddhj.service.user;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.user.TMessageDto;
import cn.com.ddhj.model.user.TMessage;
import cn.com.ddhj.result.tuser.MessageData;
import cn.com.ddhj.result.tuser.MessageSelResult;
import cn.com.ddhj.result.tuser.MessageTotal;
import cn.com.ddhj.service.IBaseService;

/**
 * 
 * 类: ITMessageService <br>
 * 描述: 消息表业务逻辑处理接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月12日 上午10:48:45
 */
public interface ITMessageService extends IBaseService<TMessage, TMessageDto> {

	/**
	 * 
	 * 方法: findEntityToPage <br>
	 * 描述: 获取消息列表
	 * 
	 * @param dto
	 * @return
	 * @see cn.com.ddhj.service.IBaseService#findEntityToPage(cn.com.ddhj.dto.BaseDto)
	 */
	MessageData findEntityToPage(TMessageDto dto, String userToken);

	/**
	 * 
	 * 方法: findEntityTotal <br>
	 * 描述: 查询用户消息总数 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月12日 下午12:38:53
	 * 
	 * @param userToken
	 * @return
	 */
	MessageTotal findEntityTotal(Integer isReader, String userToken);

	/**
	 * 
	 * 方法: insertSelective <br>
	 * 描述: 添加新消息 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月12日 下午12:37:37
	 * 
	 * @param entity
	 * @param userToken
	 * @return
	 */
	BaseResult insertSelective(TMessage entity, String userToken);

	/**
	 * 
	 * 方法: selectByCode <br>
	 * 描述: 查询消息详情 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月12日 下午12:37:46
	 * 
	 * @param code
	 * @param userToken
	 * @return
	 */
	MessageSelResult selectByCode(String code, String userToken);
}
