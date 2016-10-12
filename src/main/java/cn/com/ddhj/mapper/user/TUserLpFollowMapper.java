package cn.com.ddhj.mapper.user;

import java.util.List;

import cn.com.ddhj.dto.user.TUserLpFollowDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.user.TUserLpFollow;

/**
 * 
 * 类: TUserLpFollowMapper <br>
 * 描述: 用户楼盘关注表数据库接口访问接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月11日 下午2:03:21
 */
public interface TUserLpFollowMapper extends BaseMapper<TUserLpFollow, TUserLpFollowDto> {

	/**
	 * 
	 * 方法: deleteByLpCode <br>
	 * 描述: 根据楼盘编码集合删除关注 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月11日 下午2:51:09
	 * 
	 * @param list
	 * @return
	 */
	int deleteByLpCode(TUserLpFollowDto dto);

	/**
	 * 
	 * 方法: deleteByUserCode <br>
	 * 描述: 根据用户编码批量删除关注 <br>
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
	 * 方法: findFollowIsExists <br>
	 * 描述: 查询关注是否已存在 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月11日 下午11:15:26
	 * 
	 * @param entity
	 * @return
	 */
	TUserLpFollow findFollowIsExists(TUserLpFollow entity);
}