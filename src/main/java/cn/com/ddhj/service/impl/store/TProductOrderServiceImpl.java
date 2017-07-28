package cn.com.ddhj.service.impl.store;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
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
			if (result.getResultCode() == 0) {
				UserDataResult userResult = userService.getUser(userToken);
				if (userResult.getResultCode() == 0) {
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
					if (insertResult.getResultCode() == 0) {
						detailMapper.batchInsert(orderDetails(dto.getProductList(), orderCode, userCode));
					}
				} else {
					result.setResultCode(userResult.getResultCode());
					result.setResultMessage(userResult.getResultMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(-1);
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
			result.setResultCode(0);
			result.setEntity(order);
			result.setResultMessage("获取订单详情成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode(-1);
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
			result.setResultCode(-1);
			result.setResultMessage("商品" + productCodes.substring(0, productCodes.length() - 1) + "库存不足.");
		} 
		
		if(notFoundError > 0) {
			result.setResultCode(-1);
			result.setResultMessage(result.getResultMessage() + "商品" + productCodes.substring(0, productCodes.length() - 1) + "未查到.");
		}
		
		if(result.getResultCode() == 0) {
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
	 * @测试地址如下：http://localhost:8080/ddhj/api.htm?apiTarget=product_order_list&api_key=appfamilyhas&apiInput={"buyerCode":"U161005100033"}
	 * 
	 * @返回参数如下：
		 {    
		    "status": "success",
		    "msg": "查询成功",
		    "buyerCode": "U161005100033",
		    "list": [
		        {
		            "orderCode": "DD918342863587",
		            "orderStatus": "OS8866001",
		            "payMoney": 1500,
		            "mobile": "15800000000",
		            "productNames": "测试0,测试1",
		            "mpurl": "http://image-family.huijia29e/31c4779dd7b57ccbf0b1aa.jpg,http://image-cd4d8.jpg"
		        },
		        {
		            "orderCode": "DD918342863676",
		            "orderStatus": "OS8866001",
		            "payMoney": 1200,
		            "mobile": "15800000000",
		            "productNames": "测试2,测试3",
		            "mpurl": "http://image-family.huij1618ccf58429aaec031ba998c4654.jpg,http://image-a7c2f712f.jpg"
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
	public JSONObject findProductOrderList(String buyerCode) {
		JSONObject re = new JSONObject();
		if(StringUtils.isBlank(buyerCode)){
			re.put("status", "error");
			re.put("msg", "用户编号不得为空");
			return re;
		}
		
		List<ProductOrderResult> list = mapper.findProductOrderList(buyerCode);
		if(list == null || list.size() == 0){
			re.put("status", "error");
			re.put("msg", "用户订单列表为空");
			return re;
		}
		re.put("status", "success");
		re.put("msg", "查询成功");
		re.put("list", list);
		re.put("buyerCode", buyerCode);
		return re;
	}

}






















