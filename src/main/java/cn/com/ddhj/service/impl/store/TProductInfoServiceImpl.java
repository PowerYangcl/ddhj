package cn.com.ddhj.service.impl.store;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.ddhj.dto.store.TProductInfoDto;
import cn.com.ddhj.mapper.TProductInfoMapper;
import cn.com.ddhj.model.TProductInfo;
import cn.com.ddhj.result.TProductInfoResult;
import cn.com.ddhj.result.PageResult;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.store.ITProductInfoService;

@Service
public class TProductInfoServiceImpl extends BaseServiceImpl<TProductInfo, TProductInfoMapper, TProductInfoDto> implements ITProductInfoService{
	@Autowired
	private TProductInfoMapper mapper;

	public PageResult findDataPage(TProductInfoDto dto) {
		PageResult result = new PageResult();
		PageHelper.startPage(dto.getPageIndex(), dto.getPageSize());
		List<TProductInfo> list = mapper.findEntityAll(dto);
		if (list != null && list.size() > 0) {
			result.setResultCode(0);
		} else {
			list = new ArrayList<TProductInfo>();
			result.setResultCode(-1);
			result.setResultMessage("查询商品列表为空");
		}
		PageInfo<TProductInfo> page = new PageInfo<TProductInfo>(list);
		result.setPage(page);
		return result;
	}
	

	/**
	 * @description: 获取商品详细信息
	 * @测试地址如下：http://localhost:8080/ddhj/api.htm?apiTarget=product_info&api_key=appfamilyhas&apiInput={%22productCode%22:%22801613242%22}
	 * @返回参数如下：
		 {
		    "status": "success",
		    "msg": "查询成功",
		    "entity": {
		        "productCode": "801613242",
		        "productName": "测试0",
		        "mainPicUrl": "default.jpg",
		        "currentPrice": 89,
		        "stockNum": 99,
		        "productTip": "",
		        "flagSellable": 0,
		        "picUrls": "purl-1,purl-2,purl-3,purl-4,purl-5"
		    }
		}
	 * 
	 * @param productCode
	 * @author Yangcl 
	 * @date 2017年7月26日 下午3:34:47 
	 * @version 1.0.0.1
	 */
	public JSONObject getProductInfo(String productCode) {
		JSONObject re = new JSONObject();
		if(StringUtils.isBlank(productCode)){
			re.put("status", "error");
			re.put("msg", "商品编号不得为空");
			return re;
		}
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























