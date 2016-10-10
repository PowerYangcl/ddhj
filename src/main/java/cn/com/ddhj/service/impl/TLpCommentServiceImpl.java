package cn.com.ddhj.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.TLpCommentDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TLpCommentMapper;
import cn.com.ddhj.mapper.TOrderMapper;
import cn.com.ddhj.mapper.TUserLoginMapper;
import cn.com.ddhj.mapper.TUserMapper;
import cn.com.ddhj.model.TLpComment;
import cn.com.ddhj.model.TOrder;
import cn.com.ddhj.model.TUser;
import cn.com.ddhj.model.TUserLogin;
import cn.com.ddhj.result.lp.TLpCommentData;
import cn.com.ddhj.result.lp.TLpCommentTopData;
import cn.com.ddhj.service.ITLpCommentService;
import cn.com.ddhj.util.DateUtil;

/**
 * 
 * 类: TLpCommentServiceImpl <br>
 * 描述: 楼盘评论业务逻辑处理接口实现类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月10日 下午12:52:39
 */
@Service
public class TLpCommentServiceImpl extends BaseServiceImpl<TLpComment, TLpCommentMapper, TLpCommentDto>
		implements ITLpCommentService {

	@Autowired
	private TLpCommentMapper mapper;
	@Autowired
	private TUserLoginMapper loginMapper;
	@Autowired
	private TOrderMapper orderMapper;
	@Autowired
	private TUserMapper userMapper;

	/**
	 * 
	 * 方法: findDataTop5 <br>
	 * 
	 * @param dto
	 * @return
	 * @see cn.com.ddhj.service.ITLpCommentService#findDataTop5(cn.com.ddhj.dto.TLpCommentDto)
	 */
	@Override
	public TLpCommentTopData findDataTop5(TLpCommentDto dto) {
		TLpCommentTopData result = new TLpCommentTopData();
		List<TLpComment> list = mapper.findDataTop5(dto);
		if (list != null && list.size() > 0) {
			int total = mapper.findEntityAllCount(dto);
			dto.setLevel(0);
			int goodTotal = mapper.findEntityAllCount(dto);
			double goodPercent = BigDecimal.valueOf(goodTotal)
					.divide(BigDecimal.valueOf(total), 2, RoundingMode.HALF_UP).doubleValue();
			result.setGoodPercent((int) (goodPercent * 100));
			result.setResultCode(0);
			result.setResultMessage("获取楼盘最新5条数据成功");
		} else {
			list = new ArrayList<TLpComment>();
			result.setResultCode(-1);
			result.setResultMessage("暂无评价");
			result.setGoodPercent(0);
		}
		result.setList(list);
		return result;
	}

	/**
	 * 
	 * 方法: findData <br>
	 * 
	 * @param dto
	 * @return
	 * @see cn.com.ddhj.service.ITLpCommentService#findData(cn.com.ddhj.dto.TLpCommentDto)
	 */
	@Override
	public TLpCommentData findData(TLpCommentDto dto) {
		TLpCommentData result = new TLpCommentData();
		dto.setStart(dto.getPageIndex() * dto.getPageSize());
		List<TLpComment> list = mapper.findEntityAll(dto);
		if (list != null && list.size() > 0) {
			result.setList(list);
			// 获取全部,好评，中评，差评总数
			result.setTotal(commnetTotal(dto.getLpCode(), null));
			result.setGoodTotal(commnetTotal(dto.getLpCode(), 0));
			result.setMediumTotal(commnetTotal(dto.getLpCode(), 1));
			result.setBadTotal(commnetTotal(dto.getLpCode(), 2));
			result.setResultCode(0);
			result.setResultMessage("查询评论成功");
		} else {
			result.setResultCode(-1);
			result.setResultMessage("查询评论失败");
		}
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
	 * @see cn.com.ddhj.service.ITLpCommentService#insertSelective(cn.com.ddhj.model.TLpComment,
	 *      java.lang.String)
	 */
	@Override
	public BaseResult insertSelective(TLpComment entity, String userToken) {
		BaseResult result = new BaseResult();
		// 验证用户是否已登录
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login != null) {
			TUser user = userMapper.findTUserByUuid(login.getUserToken());
			if (user != null) {
				/**
				 * 查询用户最新购买楼盘记录
				 */
				TOrder dto = new TOrder();
				dto.setHousesCode(entity.getLpCode());
				dto.setCreateUser(user.getUserCode());
				TOrder order = orderMapper.findOrderByComment(dto);
				if (order != null) {
					entity.setrCode(order.getReportCode());
					entity.setRlCode(order.getLevelCode());
					entity.setRlName(order.getLevelName());
					entity.setOrderCode(order.getCode());
					entity.setOrderTime(order.getCreateTime());
				}
				entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
				entity.setCode(WebHelper.getInstance().getUniqueCode("LPC"));
				entity.setCreateUser(user.getUserCode());
				entity.setCreateTime(DateUtil.getSysDateTime());
				entity.setUpdateUser(user.getUserCode());
				entity.setUpdateTime(entity.getCreateTime());
				int flag = mapper.insertSelective(entity);
				if (flag > 0) {
					result.setResultCode(0);
					result.setResultMessage("添加评论成功");
				} else {
					result.setResultCode(-1);
					result.setResultMessage("添加评论失败");
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
	 * 方法: commnetTotal <br>
	 * 描述: 查询等级总数 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月10日 下午2:07:53
	 * 
	 * @param lpCode
	 * @param level
	 * @return
	 */
	private Integer commnetTotal(String lpCode, Integer level) {
		TLpCommentDto dto = new TLpCommentDto();
		dto.setLpCode(lpCode);
		dto.setLevel(level);
		return mapper.findEntityAllCount(dto);
	}

}
