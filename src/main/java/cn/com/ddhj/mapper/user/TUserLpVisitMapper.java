package cn.com.ddhj.mapper.user;

import java.util.List;

import cn.com.ddhj.dto.user.TUserLpVisitDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.model.user.TUserLpVisit;

/**
 * 
 * 类: TUserLpVisitMapper <br>
 * 描述: 用户浏览记录表数据库访问接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月11日 下午2:03:57
 */
public interface TUserLpVisitMapper extends BaseMapper<TUserLpVisit, TUserLpVisitDto> {

	/**
	 * 
	 * 方法: deleteByLpCode <br>
	 * 描述: 根据楼盘编码集合删除浏览记录 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月11日 下午2:51:09
	 * 
	 * @param list
	 * @return
	 */
	int deleteByLpCode(TUserLpVisitDto dto);

	/**
	 * 
	 * 方法: deleteByUserCode <br>
	 * 描述: 根据用户编码批量删除浏览记录 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月11日 下午2:51:37
	 * 
	 * @param userCode
	 * @return
	 */
	int deleteByUserCode(String userCode);

	/**
	 * 
	 * 方法: findLpCodeAll <br>
	 * 描述: 获取用户关注的所有楼盘编码 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月11日 下午2:46:18
	 * 
	 * @param userCode
	 * @return
	 */
	List<String> findLpCodeAll(String userCode);

	/**
	 * 
	 * 方法: findVisitIsExists <br>
	 * 描述: 查询浏览记录是否已存在 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月11日 下午11:16:53
	 * 
	 * @param entity
	 * @return
	 */
	TUserLpVisit findVisitIsExists(TUserLpVisit entity);

	/**
	 * 
	 * 方法: findLpForVisit <br>
	 * 描述: 查询浏览楼盘记录 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年8月24日 下午10:44:42
	 * 
	 * @param dto
	 * @return
	 */
	List<TLandedProperty> findLpForVisit(TUserLpVisitDto dto);


	/**
	 * 
	 * 方法: findLpForVisit <br>
	 * 描述: 查询浏览楼盘记录总数 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年8月24日 下午10:44:42
	 * 
	 * @param dto
	 * @return
	 */
	Integer findLpForVisitCount(TUserLpVisitDto dto);
}