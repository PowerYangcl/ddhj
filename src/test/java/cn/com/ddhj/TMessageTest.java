package cn.com.ddhj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.base.PageResult;
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
		entity.setContent("系统消息：测试测试法卡斯加的咖啡开始就分手了看到飞机开始的附件是发动机看电视就发生的纠纷的");
		entity.setCreateUser("system");
		entity.setReceivedUser("U161012100003");
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
