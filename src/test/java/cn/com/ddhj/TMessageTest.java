package cn.com.ddhj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.dto.user.TMessageDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.model.user.TMessage;
import cn.com.ddhj.result.tuser.MessageData;
import cn.com.ddhj.service.user.ITMessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TMessageTest extends BaseTest {

	@Autowired
	private ITMessageService service;

	@Test
	public void insert() {
		TMessage entity = new TMessage();
		entity.setCode(WebHelper.getInstance().getUniqueCode("M"));
		entity.setContent("活动消息：是大家看法是开放式空间方式打开了房间速度快放假快乐圣诞节疯狂数据库的附件三季度");
		entity.setCreateUser("system");
		entity.setReceivedUser("U161005100035");
		entity.setTypeCode("DC161012100003");
		service.insertSelective(entity);
	}

	public void edit() {
		TMessage entity = new TMessage();
		entity.setCode("M161012100005");
		entity.setIsRead(1);
		entity.setUpdateUser("system");
		service.updateByCode(entity);
	}

	public void data() {
		TMessageDto dto = new TMessageDto();
		dto.setPageIndex(0);
		dto.setPageSize(10);
		dto.setReceivedUser("U161012100003");
		MessageData page = (MessageData) service.findEntityToPage(dto);
		System.out.println(JSON.toJSON(page));
	}
}
