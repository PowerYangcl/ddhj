package cn.com.ddhj.service.impl.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.store.TProductInfoDto;
import cn.com.ddhj.mapper.TProductInfoMapper;
import cn.com.ddhj.model.TProductInfo;
import cn.com.ddhj.result.TProductInfoResult;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.store.ITProductInfoService;

@Service
public class TProductInfoServiceImpl extends BaseServiceImpl<TProductInfo, TProductInfoMapper, TProductInfoDto> implements ITProductInfoService{

	@Autowired
	private TProductInfoMapper mapper;

	@Override
	public JSONObject getProductInfo(String productCode) {
		JSONObject re = new JSONObject();
		
		TProductInfoResult e = mapper.getProductInfo(productCode);
		if(e == null){
			re.put("status", "error");
			re.put("msg", "未找到对应的商品信息");
			return re;
		}
		
		re.put("status", "success");
		re.put("msg", "查询成功");
		re.put("entity", e);
		return re;
	}
	
	
	
	
}
