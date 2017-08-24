package cn.com.ddhj.service.impl.user;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.user.TUserLpVisitDto;
import cn.com.ddhj.mapper.user.TUserLoginMapper;
import cn.com.ddhj.mapper.user.TUserLpVisitMapper;
import cn.com.ddhj.mapper.user.TUserMapper;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.model.user.TUserLogin;
import cn.com.ddhj.model.user.TUserLpVisit;
import cn.com.ddhj.result.tuser.UserDataResult;
import cn.com.ddhj.result.tuser.VisitResult;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.user.ITUserLpVisitService;
import cn.com.ddhj.service.user.ITUserService;
import cn.com.ddhj.util.CommonUtil;
import cn.com.ddhj.util.Constant;
import cn.com.ddhj.util.DateUtil;

/**
 * 
 * 类: TUserLpVisitServiceImpl <br>
 * 描述: 用户楼盘浏览记录业务逻辑处理接口实现类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月11日 下午2:59:50
 */
@Service
public class TUserLpVisitServiceImpl extends BaseServiceImpl<TUserLpVisit, TUserLpVisitMapper, TUserLpVisitDto>
		implements ITUserLpVisitService {

	@Autowired
	private TUserLpVisitMapper mapper;
	@Autowired
	private TUserLoginMapper loginMapper;
	@Autowired
	private TUserMapper userMapper;
	@Autowired
	private ITUserService userService;

	@Override
	public BaseResult delVisit(TUserLpVisitDto dto, String userToken) {
		BaseResult result = new BaseResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login != null) {
			TUser user = userMapper.findTUserByUuid(login.getUserToken());
			if (StringUtils.isNotBlank(dto.getIdList())) {
				String[] codes = dto.getIdList().split(",");
				List<String> list = Arrays.asList(codes);
				TUserLpVisitDto dto2 = new TUserLpVisitDto();
				dto2.setCodes(list);
				dto2.setUserCode(user.getUserCode());
				int flag = mapper.deleteByLpCode(dto2);
				if (flag >= 0) {
					result.setResultCode(Constant.RESULT_SUCCESS);
					result.setResultMessage("删除成功");
				} else {
					result.setResultCode(Constant.RESULT_ERROR);
					result.setResultMessage("删除失败");
				}
			} else {
				int flag = mapper.deleteByUserCode(user.getUserCode());
				if (flag >= 0) {
					result.setResultCode(Constant.RESULT_SUCCESS);
					result.setResultMessage("删除成功");
				} else {
					result.setResultCode(Constant.RESULT_ERROR);
					result.setResultMessage("删除失败");
				}
			}
		} else {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户未登录");
		}
		return result;
	}

	@Override
	public BaseResult insert(String lpCode, String userToken) {
		BaseResult result = new BaseResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login != null) {
			TUser user = userMapper.findTUserByUuid(login.getUserToken());
			TUserLpVisit entity = new TUserLpVisit();
			entity.setLpCode(lpCode);
			entity.setUserCode(user.getUserCode());
			entity.setCreateTime(DateUtil.getSysDateTime());
			if (mapper.findVisitIsExists(entity) == null) {
				int flag = mapper.insertSelective(entity);
				if (flag > 0) {
					result.setResultCode(Constant.RESULT_SUCCESS);
					result.setResultMessage("添加浏览记录成功");
				} else {
					result.setResultCode(Constant.RESULT_ERROR);
					result.setResultMessage("添加浏览记录失败");
				}
			} else {
				result.setResultCode(Constant.RESULT_ERROR);
				result.setResultMessage("浏览记录已存在");
			}

		} else {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户未登录");
		}
		return result;
	}

	@Override
	public VisitResult findVisitLpData(TUserLpVisitDto dto, String userToken) {
		VisitResult result = new VisitResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if (login != null) {
			TUser user = userMapper.findTUserByUuid(login.getUserToken());
			if (user != null) {
				// 查询浏览楼盘列表
				dto.setUserCode(user.getUserCode());
				dto.setStart(dto.getPageIndex() * dto.getPageSize());
				dto.setPageSize(dto.getPageSize());
				List<TLandedProperty> list = mapper.findLpForVisit(dto);
				if (list != null && list.size() > 0) {
					for (TLandedProperty lp : list) {
						Double distance = CommonUtil.getDistanceFromLL(Double.parseDouble(dto.getLat()),
								Double.parseDouble(dto.getLng()), Double.parseDouble(lp.getLat()),
								Double.parseDouble(lp.getLng())) / 1000;
						lp.setDistance(distance.intValue());
					}
					result.setList(list);
					result.setTotal(mapper.findLpForVisitCount(dto));
					result.setResultCode(Constant.RESULT_SUCCESS);
					result.setResultMessage("获取浏览记录成功");
				} else {
					result.setResultCode(Constant.RESULT_NULL);
					result.setResultMessage("暂无浏览记录");
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
	 * @descriptions 删除所有访问记录
	 *
	 * @param userToken
	 * @return
	 * @date 2017年8月6日 下午9:46:19
	 * @author Yangcl
	 * @version 1.0.0.1
	 */
	public JSONObject deleteLpVisitByUserCode(String userToken) {
		JSONObject result = new JSONObject();
		String userCode = "";
		UserDataResult userResult = userService.getUser(userToken);
		if (userResult.getResultCode() == Constant.RESULT_SUCCESS) {
			TUser user = userResult.getUser();
			userCode = user.getUserCode();
		} else {
			result.put("resultCode", -1);
			result.put("resultMessage", "用户尚未登录");
			return result;
		}

		int flag = mapper.deleteByUserCode(userCode);
		if (flag == 1) {
			result.put("resultCode", 1);
			result.put("resultMessage", "访问记录删除成功");
		} else {
			result.put("resultCode", -1);
			result.put("resultMessage", "访问记录删除失败");
		}

		return result;
	}

}
