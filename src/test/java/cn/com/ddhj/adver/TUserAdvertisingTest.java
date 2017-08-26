package cn.com.ddhj.adver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.model.adver.TUserAdvertising;
import cn.com.ddhj.service.adver.ITUserAdvertisingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TUserAdvertisingTest extends BaseTest {

	@Autowired
	private ITUserAdvertisingService service;

	@Test
	public void insert() {
		/**
		 * AD170826100008<br>
		 * AD170826100009<br>
		 * AD170826100010<br>
		 * AD170826100011<br>
		 * AD170826100012<br>
		 * AD170826100013<br>
		 * AD170826100014<br>
		 * AD170826100015<br>
		 * AD170826100016<br>
		 * AD170826100017<br>
		 */
		TUserAdvertising entity = new TUserAdvertising();
		entity.setAdCode("AD170826100008");
		entity.setNextAdCode("AD170826100009");
		entity.setUserCode("U161009100001");
		service.insertSelective(entity);
		
		TUserAdvertising entity1 = new TUserAdvertising();
		entity1.setAdCode("AD170826100009");
		entity1.setNextAdCode("AD170826100010");
		entity1.setUserCode("U161009100001");
		service.insertSelective(entity1);
		
		TUserAdvertising entity2 = new TUserAdvertising();
		entity2.setAdCode("AD170826100010");
		entity2.setNextAdCode("AD170826100011");
		entity2.setUserCode("U161009100001");
		service.insertSelective(entity2);
	}
}
