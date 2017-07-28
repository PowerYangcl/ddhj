package cn.com.ddhj.mapper.store;

import java.util.List;

import cn.com.ddhj.dto.store.TAreaDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.store.TArea;

/**
 * 城市维护表数据库访问接口
 * 
 * @author user
 *
 */
public interface TAreaMapper extends BaseMapper<TArea, TAreaDto> {
	/**
	 * 
	 * @param parentCode
	 * @return
	 */
	List<TArea> findDataByParent(String parentCode);
}