package cn.com.ddhj;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.dto.user.TUserCarbonOperationDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.user.TUserCarbonOperationMapper;
import cn.com.ddhj.model.user.TUserCarbonOperation;
import cn.com.ddhj.result.carbon.CarbonDetailResult;
import cn.com.ddhj.result.carbon.CarbonTypeDetailResult;
import cn.com.ddhj.service.user.ITUserCarbonOperationService;
import cn.com.ddhj.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TUserCarbonOperationTest extends BaseTest {

	@Autowired
	private TUserCarbonOperationMapper mapper;
	@Autowired
	private ITUserCarbonOperationService serivce;

	public void insert() {
		for (int i = 1; i < 11; i++) {
			TUserCarbonOperation entity = new TUserCarbonOperation();
			entity.setUuid(WebHelper.getInstance().genUuid());
			entity.setCode(WebHelper.getInstance().getUniqueCode("LC"));
			entity.setUserCode("U161022100001");
			entity.setOperationType("DC170208100002");
			entity.setOperationTypeChild("DC170208100004");
			entity.setCarbonSum(BigDecimal.valueOf(i));
			entity.setCreateUser("U161022100001");
			entity.setCreateTime(DateUtil.getSysDateTime());
			mapper.insertSelective(entity);
		}
		for (int i = 1; i < 11; i++) {
			TUserCarbonOperation entity = new TUserCarbonOperation();
			entity.setUuid(WebHelper.getInstance().genUuid());
			entity.setCode(WebHelper.getInstance().getUniqueCode("LC"));
			entity.setUserCode("U161022100001");
			entity.setOperationType("DC170208100002");
			entity.setOperationTypeChild("DC170208100005");
			entity.setCarbonSum(BigDecimal.valueOf(i * 2));
			entity.setCreateUser("U161022100001");
			entity.setCreateTime(DateUtil.getSysDateTime());
			mapper.insertSelective(entity);
		}
		for (int i = 1; i < 11; i++) {
			TUserCarbonOperation entity = new TUserCarbonOperation();
			entity.setUuid(WebHelper.getInstance().genUuid());
			entity.setCode(WebHelper.getInstance().getUniqueCode("LC"));
			entity.setUserCode("U161022100001");
			entity.setOperationType("DC170208100002");
			entity.setOperationTypeChild("DC170208100006");
			entity.setCarbonSum(BigDecimal.valueOf(i * 3));
			entity.setCreateUser("U161022100001");
			entity.setCreateTime(DateUtil.getSysDateTime());
			mapper.insertSelective(entity);
		}

		for (int i = 1; i < 11; i++) {
			TUserCarbonOperation entity = new TUserCarbonOperation();
			entity.setUuid(WebHelper.getInstance().genUuid());
			entity.setCode(WebHelper.getInstance().getUniqueCode("LC"));
			entity.setUserCode("U161022100001");
			entity.setOperationType("DC170208100003");
			entity.setOperationTypeChild("DC170208100007");
			entity.setCarbonSum(BigDecimal.valueOf(i * 4));
			entity.setCreateUser("U161022100001");
			entity.setCreateTime(DateUtil.getSysDateTime());
			mapper.insertSelective(entity);
		}
		for (int i = 1; i < 11; i++) {
			TUserCarbonOperation entity = new TUserCarbonOperation();
			entity.setUuid(WebHelper.getInstance().genUuid());
			entity.setCode(WebHelper.getInstance().getUniqueCode("LC"));
			entity.setUserCode("U161022100001");
			entity.setOperationType("DC170208100003");
			entity.setOperationTypeChild("DC170208100008");
			entity.setCarbonSum(BigDecimal.valueOf(i * 5));
			entity.setCreateUser("U161022100001");
			entity.setCreateTime(DateUtil.getSysDateTime());
			mapper.insertSelective(entity);
		}
		for (int i = 1; i < 11; i++) {
			TUserCarbonOperation entity = new TUserCarbonOperation();
			entity.setUuid(WebHelper.getInstance().genUuid());
			entity.setCode(WebHelper.getInstance().getUniqueCode("LC"));
			entity.setUserCode("U161022100001");
			entity.setOperationType("DC170208100003");
			entity.setOperationTypeChild("DC170208100009");
			entity.setCarbonSum(BigDecimal.valueOf(i * 6));
			entity.setCreateUser("U161022100001");
			entity.setCreateTime(DateUtil.getSysDateTime());
			mapper.insertSelective(entity);
		}
	}

	@Test
	public void sel() {
		TUserCarbonOperationDto dto = new TUserCarbonOperationDto();
		dto.setUserCode("U161022100001");
		dto.setOperationTypeChild("DC170208100009");
		dto.setDay(120);
		CarbonTypeDetailResult result = serivce.getCarbonOperationByType("a91cc4c0fcfb4ffea4c581322446cb4b", dto);
		System.out.println(JSON.toJSON(result));
	}

	public void getCarbonSum() {
		CarbonDetailResult result = serivce.getCarbonOperationDetail("a91cc4c0fcfb4ffea4c581322446cb4b");
		System.out.println(JSON.toJSON(result));
	}
}
