package cn.com.ddhj.mapper;

import java.util.List;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.model.TProductPic;

public interface TProductPicMapper extends BaseMapper<TProductPic, BaseDto> {

	List<TProductPic> selectByProductCode(String productCode);

	int batchInsert(List<TProductPic> list);

	int deleteByProductCode(String productCode);
}