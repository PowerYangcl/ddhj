package cn.com.ddhj;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.user.TUserStepDto;
import cn.com.ddhj.mapper.user.TUserStepMapper;
import cn.com.ddhj.model.user.TUserStep;
import cn.com.ddhj.result.tuser.UserStepResult;
import cn.com.ddhj.service.user.ITUserStepService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TUserStepTest {

	@Autowired
	private TUserStepMapper mapper;
	@Autowired
	private ITUserStepService service;

	public void insert() {
		List<TUserStep> list = new ArrayList<TUserStep>();
		Calendar cal = Calendar.getInstance();
		int day = 1;
		int max = 5000;
		int min = 1000;
		Random random = new Random();
		for (int i = 0; i < 30; i++) {
			TUserStep entity = new TUserStep();
			entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
			entity.setEquipmentCode("ssssss");
			entity.setStep(random.nextInt(max) % (max - min + 1) + min);

			String month = (cal.get(Calendar.MONTH) + 1) < 10 ? "0" + (cal.get(Calendar.MONTH) + 1)
					: "" + (cal.get(Calendar.MONTH) + 1);
			String d = day < 10 ? "0" + day : "" + day;
			entity.setCreateDate(cal.get(Calendar.YEAR) + "-" + month + "-" + d);
			entity.setIsBinding(0);
			entity.setUserCode("");
			list.add(entity);
			day++;
		}
		mapper.batchInstart(list);
	}

	public void update() {
		TUserStep entity = new TUserStep();
		entity.setEquipmentCode("ssssss");
		entity.setIsBinding(1);
		entity.setUserCode("test");
		mapper.updateByEquipmentCode(entity);
	}

	@Test
	public void data() {
		UserStepResult result = service.findUserStepData("ssssss");
		System.out.println(JSONObject.toJSON(result));
	}
}
