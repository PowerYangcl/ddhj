package cn.com.ddhj;

import java.net.URLEncoder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.service.ITReportService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TReportTest extends BaseTest {

	@Autowired
	private ITReportService service;

	public void createPDF() {
		service.createPDF("LP161003100031","d:/");
	}

	@Test
	public void t(){
		try {
			String str = "%E5%8C%97%E4%BA%AC";
			System.out.println(URLEncoder.encode("北京"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}