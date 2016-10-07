package cn.com.ddhj;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TPayTypeMapper;
import cn.com.ddhj.model.TPayType;
import cn.com.ddhj.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TPayTypeTest extends BaseTest {

	@Autowired
	private TPayTypeMapper mapper;

	@Test
	public void insert() {
		TPayType entity = new TPayType();
		entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
		entity.setCode(WebHelper.getInstance().getUniqueCode("PT"));
		entity.setName("微信支付");
		entity.setCreateUser("system");
		entity.setCreateTime(DateUtil.getSysDateTime());
		entity.setUpdateUser("system");
		entity.setUpdateTime(DateUtil.getSysDateTime());
		mapper.insertSelective(entity);
		TPayType entity2 = new TPayType();
		entity2.setUuid(UUID.randomUUID().toString().replace("-", ""));
		entity2.setCode(WebHelper.getInstance().getUniqueCode("PT"));
		entity2.setName("支付宝支付");
		entity2.setCreateUser("system");
		entity2.setCreateTime(DateUtil.getSysDateTime());
		entity2.setUpdateUser("system");
		entity2.setUpdateTime(DateUtil.getSysDateTime());
		mapper.insertSelective(entity2);
	}
}
