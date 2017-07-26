package cn.com.ddhj.store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.model.TProductInfo;
import cn.com.ddhj.service.store.ITProductInfoService;
import cn.com.ddhj.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TProductInfoTest extends BaseTest {

	@Autowired
	private ITProductInfoService service;

	@Test
	public void insert() {
		for (int i = 0; i < 23; i++) {
			int val = Double.valueOf(Math.random() * 100).intValue();
			TProductInfo entity = new TProductInfo();
			entity.setProductCode(WebHelper.getInstance().getUniqueCode("TP"));
			entity.setProductName("测试" + i);
			entity.setCurrentPrice(val);
			entity.setStockNum(10 + val);
			entity.setMainPicUrl("default.jpg");
			entity.setCreateUser("test");
			entity.setCreateTime(DateUtil.getSysDateTime());
			service.insertSelective(entity);
		}
	}
}
