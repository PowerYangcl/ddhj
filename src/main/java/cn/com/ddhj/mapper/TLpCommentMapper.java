package cn.com.ddhj.mapper;

import java.util.List;

import cn.com.ddhj.dto.TLpCommentDto;
import cn.com.ddhj.model.TLpComment;

public interface TLpCommentMapper extends BaseMapper<TLpComment, TLpCommentDto> {

	/**
	 * 
	 * 方法: findDatTop5 <br>
	 * 描述: 查询楼盘的最新5条评价 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月10日 下午12:03:22
	 * 
	 * @param dto
	 * @return
	 */
	List<TLpComment> findDataTop5(TLpCommentDto dto);
}