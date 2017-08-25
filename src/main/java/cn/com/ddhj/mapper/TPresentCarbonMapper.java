package cn.com.ddhj.mapper;

import cn.com.ddhj.dto.TPresentCarbonDto;
import cn.com.ddhj.model.TPresentCarbon;

public interface TPresentCarbonMapper extends BaseMapper<TPresentCarbon, TPresentCarbonDto>  {
	Double selectSumCarbonByUserCode(String userCode);
}
