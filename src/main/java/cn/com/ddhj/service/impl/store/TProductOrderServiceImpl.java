package cn.com.ddhj.service.impl.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.ProductOrderDto;
import cn.com.ddhj.dto.store.TProductOrderDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TProductInfoMapper;
import cn.com.ddhj.mapper.TProductOrderDetailMapper;
import cn.com.ddhj.mapper.TProductOrderMapper;
import cn.com.ddhj.model.TProductInfo;
import cn.com.ddhj.model.TProductOrder;
import cn.com.ddhj.model.TProductOrderDetail;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.result.DataResult;
import cn.com.ddhj.result.EntityResult;
import cn.com.ddhj.result.ProductOrderResult;
import cn.com.ddhj.result.tuser.UserDataResult;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.store.ITProductOrderService;
import cn.com.ddhj.service.user.ITUserService;
import cn.com.ddhj.util.Constant;
import cn.com.ddhj.util.DateUtil;

@Service
public class TProductOrderServiceImpl extends BaseServiceImpl<TProductOrder, TProductOrderMapper, TProductOrderDto>
		implements ITProductOrderService {

	@Autowired
	private TProductOrderMapper mapper;

	@Autowired
	private ITUserService userService;

	@Autowired
	private TProductOrderDetailMapper detailMapper;

	@Autowired
	private TProductInfoMapper productMapper;

	/**
	 * 
	 * 方法: createOrder <br>
	 * 
	 * @param dto
	 * @param userToken
	 * @return
	 * @see cn.com.ddhj.service.store.ITProductOrderService#createOrder(cn.com.ddhj.dto.store.TProductOrderDto,
	 *      java.lang.String)
	 */
	@Override
	public BaseResult createOrder(TProductOrderDto dto, String userToken) {
		// 验证商品是否存在，库存是否可用
		DataResult result = getProductList(dto.getProductList());
		try {
			if (result.getResultCode() == Constant.RESULT_SUCCESS) {
				UserDataResult userResult = userService.getUser(userToken);
				if (userResult.getResultCode() == Constant.RESULT_SUCCESS) {
					TUser user = userResult.getUser();
					// 创建订单
					String userCode = user.getUserCode();
					String orderCode = WebHelper.getInstance().getUniqueCode("PD");
					TProductOrder order = new TProductOrder();
					order.setCode(orderCode);
					order.setPayMoney(dto.getPayMoney());
					order.setBuyerCode(userCode);
					order.setBuyerPhone(user.getPhone());
					order.setDispatching(dto.getDispatching());
					order.setAddressCode(dto.getAddressCode());
					order.setCreateUser(userCode);
					BaseResult insertResult = super.insertSelective(order);
					if (insertResult.getResultCode() == Constant.RESULT_SUCCESS) {
						detailMapper.batchInsert(orderDetails(dto.getProductList(), orderCode, userCode));
					}
				} else {
					result.setResultCode(userResult.getResultCode());
					result.setResultMessage(userResult.getResultMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("创建订单失败，请联系技术人员");
		}
		return result;
	}
	
	
	/**
	 * 确认订单
	 * 方法: confirmOrder <br>
	 * @author zht
	 * @param dto
	 * @param userToken
	 * @return
	 * @see cn.com.ddhj.service.store.ITProductOrderService#createOrder(cn.com.ddhj.dto.store.TProductOrderDto,
	 *      java.lang.String)
	 */
	@Override
	public BaseResult confirmOrder(TProductOrderDto dto, String userToken) {
		// 验证商品是否存在，库存是否可用
		DataResult result = getProductList(dto.getProductList());
//		try {
//			if (result.getResultCode() == 0) {
//				UserDataResult userResult = userService.getUser(userToken);
//				if (userResult.getResultCode() == 0) {
//					TUser user = userResult.getUser();
//					// 创建订单
//					String userCode = user.getUserCode();
//					String orderCode = WebHelper.getInstance().getUniqueCode("PD");
//					TProductOrder order = new TProductOrder();
//					order.setCode(orderCode);
//					order.setPayMoney(dto.getPayMoney());
//					order.setBuyerCode(userCode);
//					order.setBuyerPhone(user.getPhone());
//					order.setDispatching(dto.getDispatching());
//					order.setAddressCode(dto.getAddressCode());
//					order.setCreateUser(userCode);
//					BaseResult insertResult = super.insertSelective(order);
//					if (insertResult.getResultCode() == 0) {
//						detailMapper.batchInsert(orderDetails(dto.getProductList(), orderCode, userCode));
//					}
//				} else {
//					result.setResultCode(userResult.getResultCode());
//					result.setResultMessage(userResult.getResultMessage());
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.setResultCode(-1);
//			result.setResultMessage("创建订单失败，请联系技术人员");
//		}
		return result;
	}

	/**
	 * 
	 * 方法: findOrderDetailByCode <br>
	 * 
	 * @param orderCode
	 * @return
	 * @see cn.com.ddhj.service.store.ITProductOrderService#findOrderDetailByCode(java.lang.String)
	 */
	@Override
	public EntityResult findOrderDetailByCode(String orderCode) {
		EntityResult result = new EntityResult();
		try {
			TProductOrder order = mapper.findOrderDetailByCode(orderCode);
			if (order != null) {
				List<TProductOrderDetail> list = detailMapper.findOrderProductDetail(orderCode);
				order.setDetails(list);
			}
			result.setResultCode(Constant.RESULT_SUCCESS);
			result.setEntity(order);
			result.setResultMessage("获取订单详情成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("获取订单详情失败，请联系技术人员");
		}
		return result;
	}

	/**
	 * 
	 * 方法: getProductList <br>
	 * 描述: 查询商品当前售价 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年7月24日 上午9:37:54
	 * 
	 * @param list
	 * @return
	 */
	private DataResult getProductList(List<TProductInfo> list) {
		DataResult result = new DataResult();
		List<TProductInfo> products = productMapper.findProductsByList(list);
		if (products == null || products.size() == 0) {
			result.setResultCode(-1);
			result.setResultMessage("所购商品消失了!");
			return result;
		}
		
		int stockError = 0, notFoundError = 0;
		StringBuffer productCodes = new StringBuffer();
		StringBuffer notFoundProduct = new StringBuffer();
		for (TProductInfo info : list) {
			boolean found = false;
			for (TProductInfo product : products) {
				if (StringUtils.equals(info.getProductCode(), product.getProductCode())) {
					if (product.getStockNum() > info.getBuyNum()) {
						info.setCurrentPrice(product.getCurrentPrice());
					} else {
						stockError++;
						productCodes.append(info.getProductCode()).append(",");
					}
					found = true;
					break;
				}
			}
			
			if(!found) {
				notFoundError++;
				notFoundProduct.append(info.getProductCode()).append(",");
			}
		}
		
		if (stockError > 0) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("商品" + productCodes.substring(0, productCodes.length() - 1) + "库存不足.");
		} 
		
		if(notFoundError > 0) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage(result.getResultMessage() + "商品" + productCodes.substring(0, productCodes.length() - 1) + "未查到.");
		}
		
		if(result.getResultCode() == Constant.RESULT_SUCCESS) {
			result.setData(list);
		}
		return result;
	}

	/**
	 * 
	 * 方法: orderDetails <br>
	 * 描述: 整理订单详情信息 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年7月24日 上午10:37:55
	 * 
	 * @param list
	 * @param orderCode
	 * @return
	 */
	private List<TProductOrderDetail> orderDetails(List<TProductInfo> products, String orderCode, String userCode) {
		List<TProductOrderDetail> list = new ArrayList<TProductOrderDetail>();
		for (TProductInfo product : products) {
			TProductOrderDetail entity = new TProductOrderDetail();
			entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
			entity.setOrderCode(orderCode);
			entity.setProductCode(product.getProductCode());
			entity.setBuyNum(product.getBuyNum());
			entity.setCurrentPrice(product.getCurrentPrice());
			entity.setCreateUser(userCode);
			entity.setCreateTime(DateUtil.getSysDateTime());
			list.add(entity);
		}
		return list;
	}

	/**
	 * @description: 返回指定用户的订单列表信息 
	 * @测试地址如下：http://localhost:8080/ddhj/api.htm?apiTarget=order_list&api_key=appfamilyhas&apiInput={"buyerCode":"U161005100033","pageSize":"10","pageIndex":"0"}
	 * 
	 * @返回参数如下：
		{    
		    "resultCode": 0,
		    "resultMessage": "查询成功",
		    "telCS": "010-66668888",
		    "orderList": [
		        {
		            "orderCode": "DD918342863587",
		            "orderMoney": 1500,
		            "orderStatus": "OS8866001",
		            "productList": [
		                {
		                    "productCode": "801613242",
		                    "productName": "测试0",
		                    "imgUrl": "http://image-family.huijiayou.cn/cfiles/staticfiles/upload/2729e/31c4f7b50a9e4a779dd7b57ccbf0b1aa.jpg",
		                    "productPrice": 1000
		                },
		                {
		                    "productCode": "801613243",
		                    "productName": "测试1",
		                    "imgUrl": "http://image-family.huijiayou.cn/cfiles/staticfiles/upload/29ada/f69f89d6601840df9560e4d9b5dcd4d8.jpg",
		                    "productPrice": 500
		                }
		            ]
		        },
		        {
		            "orderCode": "DD918342863676",
		            "orderMoney": 1200,
		            "orderStatus": "OS8866001",
		            "productList": [
		                {
		                    "productCode": "TP170726100003",
		                    "productName": "测试2",
		                    "imgUrl": "http://image-family.huijiayou.cn/cfiles/staticfiles/upload/275be/7391618ccf58429aaec031ba998c4654.jpg",
		                    "productPrice": 800
		                },
		                {
		                    "productCode": "TP170726100004",
		                    "productName": "测试3",
		                    "imgUrl": "http://image-family.huijiayou.cn/cfiles/staticfiles/upload/299ad/01baed55e9184e48a1061cfa7c2f712f.jpg",
		                    "productPrice": 400
		                }
		            ]
		        }
		    ]
		}
	 * productNames 多个商品逗号分隔
	 * mpurl 多个商品主图逗号分隔
	 * 
	 * @param buyerCode
	 * @author Yangcl 
	 * @date 2017年7月27日 上午11:05:19 
	 * @version 1.0.0.1
	 */
	public JSONObject findProductOrderList(JSONObject obj) {
		JSONObject re = new JSONObject();
		String buyerCode = obj.getString("buyerCode");
		if(StringUtils.isBlank(buyerCode)){
			re.put("resultCode", 1);
			re.put("resultMessage", "用户编号不得为空");
			return re;
		}
		Integer pageSize = obj.getInteger("pageSize");
		Integer pageIndex = obj.getInteger("pageIndex");
		
		ProductOrderDto d = new ProductOrderDto();
		d.setBuyerCode(buyerCode);
		d.setPageSize(pageSize);
		d.setPageIndex(pageIndex);
		
		
		List<ProductOrderResult> list = mapper.findProductOrderList(d); 
		if(list == null || list.size() == 0){
			re.put("resultCode", 2);
			re.put("resultMessage" , "用户订单列表为空"); 
			return re;
		}
		
		List<ProductOrderModel> olist= new ArrayList<>();
		for(ProductOrderResult r : list){
			ProductOrderModel m = new ProductOrderModel();
			m.setOrderCode(r.getOrderCode());
			m.setOrderMoney(Double.valueOf(r.getPayMoney()));
			m.setOrderStatus(r.getOrderStatus());
			
			List<ProductModel> productList = new ArrayList<>();
			String[] pnArr = r.getProductNames().split(",");
			String[] mpArr = r.getMpurl().split(",");
			String[] ppArr = r.getProductPrices().split(",");
			String[] pcArr = r.getProductCode().split(",");
			if(pnArr.length != 0){
				for(int i = 0 ; i < pnArr.length ; i ++){
					ProductModel p = new ProductModel();
					p.setProductCode(pcArr[i]);
					p.setProductName(pnArr[i]);
					p.setProductPrice(Double.valueOf(ppArr[i]));
					p.setImgUrl(mpArr[i]);
					productList.add(p);
				}
			}
			m.setProductList(productList);
			olist.add(m);
		}
		re.put("resultCode", 0);
		re.put("resultMessage", "查询成功");
		re.put("orderList", olist);
		re.put("telCS", "010-66668888");
		return re;
	}

}



class ProductOrderModel{
	private String orderCode;
	private Double orderMoney;
	private String orderStatus; // order_status 订单状态 OS8866001下单成功 OS8866002下单未付款 OS8866003订单作废
	private List<ProductModel> productList;
	
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public Double getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(Double orderMoney) {
		this.orderMoney = orderMoney;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public List<ProductModel> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductModel> productList) {
		this.productList = productList;
	} 
}


class ProductModel{
	private String productCode;
	private String productName;
	private String imgUrl;
	private Double productPrice;
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
}















