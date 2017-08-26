package cn.com.ddhj.service.adver;

import cn.com.ddhj.dto.adver.TAdvertisingDto;
import cn.com.ddhj.model.adver.TAdvertising;
import cn.com.ddhj.result.EntityResult;
import cn.com.ddhj.service.IBaseService;

public interface ITAdvertisingService extends IBaseService<TAdvertising, TAdvertisingDto> {

	/**
	 *
	 * 方法: findUserAdver <br>
	 * 描述: 查询指定用户的广告 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年8月27日 上午12:18:18
	 * @param dto
	 * @return
	 */
	EntityResult findUserAdver(TAdvertisingDto dto);
}
