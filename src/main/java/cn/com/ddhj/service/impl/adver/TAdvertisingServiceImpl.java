package cn.com.ddhj.service.impl.adver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.dto.adver.TAdvertisingDto;
import cn.com.ddhj.mapper.adver.TAdvertisingMapper;
import cn.com.ddhj.model.adver.TAdvertising;
import cn.com.ddhj.result.EntityResult;
import cn.com.ddhj.service.adver.ITAdvertisingService;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.util.Constant;

@Service
public class TAdvertisingServiceImpl extends BaseServiceImpl<TAdvertising, TAdvertisingMapper, TAdvertisingDto>
		implements ITAdvertisingService {
	@Autowired
	private TAdvertisingMapper mapper;

	/**
	 * 
	 * 方法: findUserAdver <br>
	 * 
	 * @param dto
	 * @return
	 * @see cn.com.ddhj.service.adver.ITAdvertisingService#findUserAdver(cn.com.ddhj.dto.adver.TAdvertisingDto)
	 */
	@Override
	public EntityResult findUserAdver(TAdvertisingDto dto) {
		EntityResult result = new EntityResult();
		try {
			TAdvertising entity = getAdver(dto);
			result.setEntity(entity);
			result.setResultCode(Constant.RESULT_SUCCESS);
			result.setResultMessage("查询用户广告失败");
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
