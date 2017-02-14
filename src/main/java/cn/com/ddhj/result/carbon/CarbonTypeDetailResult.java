package cn.com.ddhj.result.carbon;

import java.util.List;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.user.TUserCarbonOperation;

/**
 * 
 * 类: CarbonTypeDetailResult <br>
 * 描述: 碳币分类明细 <br>
 * 作者: zhy<br>
 * 时间: 2017年2月13日 下午3:50:43
 */
public class CarbonTypeDetailResult extends BaseResult {

	private List<TUserCarbonOperation> list;

	public List<TUserCarbonOperation> getList() {
		return list;
	}

	public void setList(List<TUserCarbonOperation> list) {
		this.list = list;
	}

}
