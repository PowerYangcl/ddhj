package cn.com.ddhj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.model.TReportEnvironmentLevel;
import cn.com.ddhj.service.ITReportEnvironmentLevelService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TReportEnvironmentLevelTest extends BaseTest {

	@Autowired
	private ITReportEnvironmentLevelService service;

	@Test
	public void insert() {
		TReportEnvironmentLevel noise = new TReportEnvironmentLevel();
		noise.setCreateUser("system");
		noise.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		noise.setLevel(1);
		noise.setType("noise");
		noise.setContent(
				"目前您所处的地理区域噪音标准为：0-1类标准（昼间50-55分贝，夜间40-45分贝）：优，属于适宜居住噪声范围，适用于疗养区、高级别墅区、高级宾馆、居住、文教机关等区域；按照普通人的听力水平，50分贝相当于正常交谈的声音，30-40分贝是比较安静的正常环境。此类区域特别适合要求环境安静的人群。 ");
		service.insertSelective(noise);

		TReportEnvironmentLevel water = new TReportEnvironmentLevel();
		water.setCreateUser("system");
		water.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		water.setLevel(1);
		water.setType("water");
		water.setContent(
				"目前您所处的地理范围区域为：I II 类：优，源头水、国家自然保护区，地表水源地一级保护区、珍惜水生生物栖息地、鱼虾类产卵场等；此区域水质：I II类水质化学需氧量COD不超过15mg/L；汞不超过0.00005mg/L，粪大肠菌群I类不超过200个/L，II类不超过2000个/L。水体自净能力强，水体中有机化合物含量低，重金属含量低，微生物含量低，属于非常优秀的水质。");
		service.insertSelective(water);

		TReportEnvironmentLevel air = new TReportEnvironmentLevel();
		air.setCreateUser("system");
		air.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		air.setLevel(1);
		air.setType("air");
		air.setContent("目前您所处的地理区域AQI为：AQI指数0-50 1级，优：，参加户外活动呼吸清新空气，犹如置身天然氧吧，每一次呼吸都像被洗涤了一次一样，尽情享受户外美好时光。");
		service.insertSelective(air);

		TReportEnvironmentLevel soil = new TReportEnvironmentLevel();
		soil.setCreateUser("system");
		soil.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		soil.setLevel(1);
		soil.setType("soil");
		soil.setContent(
				"目前您所在的地理区域土壤级别为：I类一级，优: 国家规定的自然保护区（原有背景重金属含量高的除外）、集中式生活饮用水源地、茶园、牧场和其他保护地区的土壤，土壤质量基本上保持自然背景水平 ；土壤中汞含量不超过0.15mg/kg，砷不超过15mg/kg，铅不超过35mg/kg，六六六(六氯环己烷)不超过0.05mg/kg，滴滴涕不超过0.05mg/kg。其中汞、砷、铅属于重金属，六六六和滴滴涕属于杀虫剂，现在已被禁用。滴滴涕为白色晶体，不溶于水，溶于煤油，可制成乳剂，是有效的杀虫剂。为20世纪上半叶防止农业病虫害，减轻疟疾伤寒等蚊蝇传播的疾病危害起到了不小的作用。但由于其对环境污染过于严重，目前很多国家和地区已经禁止使用。");
		service.insertSelective(soil);

		TReportEnvironmentLevel rubbish = new TReportEnvironmentLevel();
		rubbish.setCreateUser("system");
		rubbish.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		rubbish.setLevel(1);
		rubbish.setType("rubbish");
		rubbish.setContent(
				"目前您所在的地理区域距离垃圾处理设施距离为：垃圾转运站或填埋场大于1公里，垃圾焚烧厂大于3公里，优：垃圾处理设施距离较远，对您的生活不会造成影响，不存在垃圾臭气、垃圾渗沥液、垃圾焚烧产生的二噁英等污染物。");
		service.insertSelective(rubbish);
		// afforest
		TReportEnvironmentLevel afforest = new TReportEnvironmentLevel();
		afforest.setCreateUser("system");
		afforest.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		afforest.setLevel(1);
		afforest.setType("afforest");
		afforest.setContent(
				"目前您所在的小区绿地率标准为：绿地率30%以上：优，绿地率高，符合《城市居住区规划设计规范》（GB 50180—93）规定：新区建设不应低于30% 旧区改建不宜低于25%的要求，是绿化程度非常好的小区。");
		service.insertSelective(afforest);
		// volume
		TReportEnvironmentLevel volume = new TReportEnvironmentLevel();
		volume.setCreateUser("system");
		volume.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		volume.setLevel(1);
		volume.setType("volume");
		volume.setContent("目前您您所在的小区容积率标准为：容积率小于3：优，居住密度低，舒适型。");
		service.insertSelective(volume);
	}
}
