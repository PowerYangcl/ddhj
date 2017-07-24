package cn.com.ddhj.service.impl.store;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		int stockError = 0;
		StringBuffer productCodes = new StringBuffer();
		if (products != null && products.size() > 0) {
			for (TProductInfo product : products) {
				for (TProductInfo info : list) {
					if (product.getStockNum() > info.getStockNum()) {
						if (StringUtils.equals(info.getProductCode(), product.getProductCode())) {
							info.setCurrentPrice(product.getCurrentPrice());
						}
					} else {
						stockError++;
						productCodes.append(info.getProductCode()).append(",");
					}
				}
			}
		}
		if (stockError == 0) {
			result.setData(list);
		} else {
			result.setResultCode(-1);
			result.setResultMessage("商品" + productCodes.substring(0, productCodes.length() - 1) + "库存不足");
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
			entity.setBuyNum(product.getStockNum());
			entity.setCurrentPrice(product.getCurrentPrice());
			entity.setCreateUser(userCode);
			entity.setCreateTime(DateUtil.getSysDateTime());
			list.add(entity);
		}
		return list;
	}

}
