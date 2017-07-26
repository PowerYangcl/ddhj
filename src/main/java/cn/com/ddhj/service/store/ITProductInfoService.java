package cn.com.ddhj.service.store;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.store.TProductInfoDto;
import cn.com.ddhj.model.TProductInfo;
import cn.com.ddhj.result.PageResult;
import cn.com.ddhj.service.IBaseService;

public interface ITProductInfoService extends IBaseService<TProductInfo, TProductInfoDto> {

	PageResult findDataPage(TProductInfoDto dto);
	
	public JSONObject getProductInfo(String productCode);
	
}
