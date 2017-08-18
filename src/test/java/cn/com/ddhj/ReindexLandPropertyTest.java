package cn.com.ddhj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.service.search.SolrDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class ReindexLandPropertyTest extends BaseTest {
	@Autowired
	private SolrDataService service;
	
	@Test
	public void test() {
//		ReindexLandPropertyForSolr r = new ReindexLandPropertyForSolr();
//		r.reindex();
		service.getListSolrData(null);
	}
}
