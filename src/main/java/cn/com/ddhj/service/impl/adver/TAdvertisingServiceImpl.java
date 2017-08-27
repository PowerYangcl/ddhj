package cn.com.ddhj.service.impl.adver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.dto.adver.TAdvertisingDto;
import cn.com.ddhj.mapper.adver.TAdvertisingMapper;
import cn.com.ddhj.model.adver.TAdvertising;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.result.EntityResult;
import cn.com.ddhj.result.tuser.UserDataResult;
import cn.com.ddhj.service.adver.ITAdvertisingService;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.user.ITUserService;
import cn.com.ddhj.util.Constant;

@Service
public class TAdvertisingServiceImpl extends BaseServiceImpl<TAdvertising, TAdvertisingMapper, TAdvertisingDto>
		implements ITAdvertisingService {
	@Autowired
	private TAdvertisingMapper mapper;
	@Autowired
	private ITUserService userService;

	/**
	 * 
	 * 方法: findUserAdver <br>
	 * 
	 * @param dto
	 * @return
	 * @see cn.com.ddhj.service.adver.ITAdvertisingService#findUserAdver(cn.com.ddhj.dto.adver.TAdvertisingDto)
	 */
	@Override
	public EntityResult findUserAdver(TAdvertisingDto dto, String usertoken) {
		EntityResult result = new EntityResult();
		try {
			TAdvertising entity = null;
			UserDataResult userResult = userService.getUser(usertoken);
			if (userResult.getResultCode() == Constant.RESULT_SUCCESS) {
				TUser user = userResult.getUser();
				dto.setUserCode(user.getUserCode());
				entity = getAdver(dto);
				if (entity == null) {
					/**
					 * 如果用户没有关注广告，查询所有已发布的广告中的第一条
					 */
					TAdvertisingDto dto_ = new TAdvertisingDto();
					dto_.setStatus(1);
					List<TAdvertising> list = mapper.findEntityAll(dto);
					if (list != null && list.size() > 0) {
						entity = list.get(0);
					}
				}
			} else {
				/**
				 * 如果用户为空，查询所有已发布的广告中的第一条
				 */
				TAdvertisingDto dto_ = new TAdvertisingDto();
				dto_.setStatus(1);
				List<TAdvertising> list = mapper.findEntityAll(dto);
				if (list != null && list.size() > 0) {
					entity = list.get(0);
				}
			}
			result.setEntity(entity);
			result.setResultCode(Constant.RESULT_SUCCESS);
			result.setResultMessage("查询用户广告成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("查询用户广告失败");
		}
		return result;
	}

	private TAdvertising getAdver(TAdvertisingDto dto) {
		TAdvertising entity = null;
		try {
			List<TAdvertising> list = mapper.findUserAdver(dto);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					TAdvertising adver = list.get(i);
					if (adver.getStatus() == 1) {
						entity = adver;
						break;
					} else {
						continue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
}
