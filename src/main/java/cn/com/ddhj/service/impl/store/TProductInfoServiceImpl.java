package cn.com.ddhj.service.impl.store;

import java.util.ArrayList;
import java.util.Arrays;
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
import cn.com.ddhj.result.PageResult;
import cn.com.ddhj.result.product.TPageProductListResult;
import cn.com.ddhj.result.product.TProductInfoResult;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.store.ITProductInfoService;
import cn.com.ddhj.util.Constant;

@Service
public class TProductInfoServiceImpl extends BaseServiceImpl<TProductInfo, TProductInfoMapper, TProductInfoDto> implements ITProductInfoService{
	@Autowired
	private TProductInfoMapper mapper;

	/**
	 * 查询商品列表.后台管理使用
	 */
	public PageResult findDataPage(TProductInfoDto dto) {
		PageResult result = new PageResult();
		PageHelper.startPage(dto.getPageIndex(), dto.getPageSize());
		List<TProductInfo> list = mapper.findEntityAll(dto);
		if (list != null && list.size() > 0) {
			result.setResultCode(Constant.RESULT_SUCCESS);
		} else {
			list = new ArrayList<TProductInfo>();
			result.setResultCode(Constant.RESULT_NULL);
			result.setResultMessage("查询商品列表为空");
		}
		PageInfo<TProductInfo> page = new PageInfo<TProductInfo>(list);
		result.setPage(page);
		return result;
	}
	
	/**
	 * 查询商品列表.接口使用
	 * @author zht
	 * @param dto
	 * @return
	 */
	public TPageProductListResult findProductListPage(TProductInfoDto dto) {
		TPageProductListResult recResult = new TPageProductListResult();
		PageResult result = findDataPage(dto);
		if(result.getResultCode() > 0) {
			recResult.setProductList(result.getPage().getList());
			recResult.setRecCount(result.getPage().getTotal());
		}
		recResult.setResultCode(result.getResultCode());
		recResult.setResultMessage(result.getResultMessage());
		return recResult;
	}
	

	/**
	 * @description: 获取商品详细信息
	 * @测试地址如下：http://localhost:8080/ddhj/api.htm?apiTarget=product_detail&api_key=appfamilyhas&apiInput={"productCode":"801613242"}
	 * @返回参数如下：
		 {
		    "resultCode": 0,
		    "resultMessage": "查询成功",
		    "productName": "测试0", 
		    "stockNum": 99,
		    "productCode": "801613242",
		    "discriptPicList": [
		        "http://image-family.huijiayou.cn/cfiles/staticfiles/upload/275b1/f56c0f2c02fd44dc85428116438e4c8f.jpg",
		        "http://image-family.huijiayou.cn/cfiles/staticfiles/upload/275b1/5c3c40cebeab451cac0548cee4875ada.jpg",
		        "http://image-family.huijiayou.cn/cfiles/staticfiles/upload/275b1/c8085670a5d44aa0b01c6b675881ae9b.jpg",
		        "http://image-family.huijiayou.cn/cfiles/staticfiles/imzoom/29a16/7612922a79ce4faa92884678e9093e69.jpg",
		        "http://image-family.huijiayou.cn/cfiles/staticfiles/imzoom/29a16/6ac99b8bb2c945c09b65cc53ca5ecf73.jpg"
		    ],
		    "productTip": "",
		    "mainpicUrl": [
		        "http://image-family.huijiayou.cn/cfiles/staticfiles/upload/2729e/31c4f7b50a9e4a779dd7b57ccbf0b1aa.jpg"
		    ], 
		    "currentPrice": 1000,
		    "telCS": "010-66668888",
		    "QQCS": "66828979658" 
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
			re.put("resultCode", 1);
			re.put("resultMessage", "商品编号不得为空");
			return re;
		}
		TProductInfoResult e = mapper.getProductInfo(productCode);
		if(e == null){
			re.put("resultCode", 1);
			re.put("resultMessage", "未找到对应的商品信息");
			return re;
		}
		
		re.put("resultCode", 0);
		re.put("resultMessage", "查询成功");
		re.put("productCode", e.getProductCode());
		re.put("productName", e.getProductName());
		re.put("mainpicUrl", new ArrayList<String>(Arrays.asList(e.getMainPicUrl().split(","))));
		re.put("currentPrice",e.getCurrentPrice());
		re.put("stockNum", e.getStockNum());
		re.put("discriptPicList", new ArrayList<String>(Arrays.asList(e.getPicUrls().split(","))));
		re.put("productTip", e.getProductTip());
		re.put("telCS", "010-66668888");
		re.put("QQCS", "66828979658");
		return re;
	}
	
 
}







































