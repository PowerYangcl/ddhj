package cn.com.ddhj.store;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.dto.store.TProductOrderDto;
import cn.com.ddhj.model.TProductInfo;
import cn.com.ddhj.result.EntityResult;
import cn.com.ddhj.service.store.ITProductOrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TProductOrderTest extends BaseTest {

	@Autowired
	private ITProductOrderService service;

	@Test
	public void createOrder() {
		TProductOrderDto dto = new TProductOrderDto();
		dto.setPayMoney(100);
		dto.setAddressCode("111222");
		dto.setDispatching(1);
		List<TProductInfo> list = new ArrayList<TProductInfo>();
		TProductInfo product = new TProductInfo();
		product.setProductCode("TP170726100003");
		product.setStockNum(10);
		product.setCurrentPrice(11);
		list.add(product);
		dto.setProductList(list);
		BaseResult result = service.createOrder(dto,"6a0a01f3378a459580b20ac89eada0fd");
		System.out.println(JSON.toJSON(result));
	}

	public void findOrderDetailByCode() {
		EntityResult result = service.findOrderDetailByCode("PD170728100001");
		System.out.println(JSON.toJSON(result));
	}
}
