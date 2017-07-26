package cn.com.ddhj.mapper;

import java.util.List;

import cn.com.ddhj.dto.store.TProductInfoDto;
import cn.com.ddhj.model.TProductInfo;
import cn.com.ddhj.result.TProductInfoResult;

public interface TProductInfoMapper extends BaseMapper<TProductInfo, TProductInfoDto> {

	/**
	 * 
	 * 方法: getProductsByList <br>
	 * 描述: 根据集合参数查询商品列表 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年7月24日 上午9:41:50
	 * 
	 * @param list
	 * @return
	 */
	List<TProductInfo> findProductsByList(List<TProductInfo> list);

	/**
	 * @description:根据product code 获取商品信息 
	 * 
	 * @param productCode
	 * @return
	 * @author Yangcl 
	 * @date 2017年7月26日 下午3:08:54 
	 * @version 1.0.0.1
	 */
	public TProductInfoResult getProductInfo(String productCode); 
}