package cn.com.ddhj.service;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.TLpCommentDto;
import cn.com.ddhj.model.TLpComment;
import cn.com.ddhj.result.lp.TLpCommentData;
import cn.com.ddhj.result.lp.TLpCommentTopData;

/**
 * 
 * 类: ITLpCommentService <br>
 * 描述: 楼盘评论业务逻辑处理接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月10日 下午12:43:07
 */
public interface ITLpCommentService extends IBaseService<TLpComment, TLpCommentDto> {

	/**
	 * 
	 * 方法: insertSelective <br>
	 * 描述: 添加新评价
	 * 
	 * @param entity
	 * @return
	 * @see cn.com.ddhj.service.IBaseService#insertSelective(cn.com.ddhj.model.BaseModel)
	 */
	BaseResult insertSelective(TLpComment entity,String userToken);

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
	TLpCommentTopData findDataTop5(TLpCommentDto dto);

	/**
	 * 
	 * 方法: findData <br>
	 * 描述: 查询楼盘的评价列表 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月10日 下午12:50:43
	 * 
	 * @param dto
	 * @return
	 */
	TLpCommentData findData(TLpCommentDto dto);
}
