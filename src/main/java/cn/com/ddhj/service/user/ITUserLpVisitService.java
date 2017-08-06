package cn.com.ddhj.service.user;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.user.TUserLpVisitDto;
import cn.com.ddhj.model.user.TUserLpVisit;
import cn.com.ddhj.result.tuser.VisitResult;
import cn.com.ddhj.service.IBaseService;

/**
 * 
 * 类: ITUserLpVisitService <br>
 * 描述: 用户楼盘浏览记录业务逻辑处理接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月11日 下午2:56:22
 */
public interface ITUserLpVisitService extends IBaseService<TUserLpVisit, TUserLpVisitDto> {
	/**
	 * 
	 * 方法: delFollow <br>
	 * 描述: 删除浏览记录 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月11日 下午2:55:41
	 * 
	 * @param dto
	 * @return
	 */
	BaseResult delVisit(TUserLpVisitDto dto, String userToken);

	/**
	 * 
	 * 方法: insert <br>
	 * 描述: 添加浏览记录 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月11日 下午3:36:30
	 * 
	 * @param lpCode
	 * @param userToken
	 * @return
	 */
	BaseResult insert(String lpCode, String userToken);

	/**
	 * 
	 * 方法: findVisitLpData <br>
	 * 描述: 获取浏览记录的楼盘列表 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月11日 下午3:37:13
	 * 
	 * @param list
	 * @return
	 */
	VisitResult findVisitLpData(TUserLpVisitDto dto, String userToken);

	public JSONObject deleteLpVisitByUserCode(String userToken); 
}
