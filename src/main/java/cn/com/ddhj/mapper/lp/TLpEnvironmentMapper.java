package cn.com.ddhj.mapper.lp;

import java.util.List;

import cn.com.ddhj.dto.landedProperty.TLpEnvironmentDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.lp.TLpEnvironment;

/**
 * 楼盘环境参数数据库访问接口
 * 
 * @author user
 *
 */
public interface TLpEnvironmentMapper extends BaseMapper<TLpEnvironment, TLpEnvironmentDto> {

	/**
	 * 批量生成楼盘环境参数数据
	 * 
	 * @param list
	 * @return
	 */
	int batchInsert(List<TLpEnvironment> list);
}