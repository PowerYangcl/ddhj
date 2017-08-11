package cn.com.ddhj.service.lp;


import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.landedProperty.TLpEnvironmentDto;
import cn.com.ddhj.model.lp.TLpEnvironment;
import cn.com.ddhj.service.IBaseService;

public interface ITLpEnvironmentService extends IBaseService<TLpEnvironment, TLpEnvironmentDto> {

	/**
	 * 批量添加楼盘环境参数
	 * 
	 * @param list
	 * @return
	 */
	BaseResult batchInsert();
}
