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
		// 噪音
		TReportEnvironmentLevel noise = new TReportEnvironmentLevel();
		noise.setCreateUser("system");
		noise.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		noise.setLevel(1);
		noise.setType("noise");
		noise.setContent(
				"目前您所处的地理区域噪音标准为：0-1类标准（昼间50-55分贝，夜间40-45分贝）：优，属于适宜居住噪声范围，适用于疗养区、高级别墅区、高级宾馆、居住、文教机关等区域；按照普通人的听力水平，50分贝相当于正常交谈的声音，30-40分贝是比较安静的正常环境。此类区域特别适合要求环境安静的人群。 ");
		service.insertSelective(noise);
		TReportEnvironmentLevel noise2 = new TReportEnvironmentLevel();
		noise2.setCreateUser("system");
		noise2.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		noise2.setLevel(2);
		noise2.setType("noise");
		noise2.setContent(
				"目前您所处的地理区域噪音标准为：2类（昼间60分贝，夜间50分贝）：良，可居住噪声范围，适用于居住、商业和工业混杂区。按照普通人的听力水平，40-60分贝属于正常交谈的声音范围，60分贝以上就属于吵闹范围了，此类区域标准在60分贝以下，适合对环境噪声不太敏感的一般人群。");
		service.insertSelective(noise2);

		TReportEnvironmentLevel noise3 = new TReportEnvironmentLevel();
		noise3.setCreateUser("system");
		noise3.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		noise3.setLevel(3);
		noise3.setType("noise");
		noise3.setContent(
				"目前您所处的地理区域噪音标准为： 3-4类（昼间65-70分贝，夜间55分贝）：差，不适宜居住噪声范围，40-60分贝属于正常交谈的声音范围，60分贝以上就属于吵闹范围了，长期居住在此噪声范围内会干扰休息和睡眠，降低工作学习效率，会引起耳鸣、耳痛、听力损伤等，造成神经衰弱，引发心血管疾病，甚至引起神经系统紊乱、精神障碍、内分泌紊乱等。");
		service.insertSelective(noise3);

		// 水质
		TReportEnvironmentLevel water = new TReportEnvironmentLevel();
		water.setCreateUser("system");
		water.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		water.setLevel(1);
		water.setType("water");
		water.setContent(
				"目前您所处的地理范围区域为：I II 类：优，源头水、国家自然保护区，地表水源地一级保护区、珍惜水生生物栖息地、鱼虾类产卵场等；此区域水质：I II类水质化学需氧量COD不超过15mg/L；汞不超过0.00005mg/L，粪大肠菌群I类不超过200个/L，II类不超过2000个/L。水体自净能力强，水体中有机化合物含量低，重金属含量低，微生物含量低，属于非常优秀的水质。");
		service.insertSelective(water);

		TReportEnvironmentLevel water2 = new TReportEnvironmentLevel();
		water2.setCreateUser("system");
		water2.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		water2.setLevel(2);
		water2.setType("water");
		water2.setContent(
				"目前您所处的地理范围区域为：III类：良，地表水源地二级保护区、游泳区水质；；此区域水质：化学需氧量COD不超过20mg/L；汞不超过0.0001mg/L，粪大肠菌群不超过10000个/L。水体自净能力较好，水体中有机化合物含量较低，重金属含量较低，微生物含量较低，属于可饮用水源水质。");
		service.insertSelective(water2);

		TReportEnvironmentLevel water3 = new TReportEnvironmentLevel();
		water3.setCreateUser("system");
		water3.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		water3.setLevel(3);
		water3.setType("water");
		water3.setContent(
				"目前您所处的地理范围区域为：IV、V类： 差，非直接接触水质。此区域水质：化学需氧量COD，IV类不超过30mg/L，V类不超过40 mg/L；汞不超过0.001mg/L，IV类水中粪大肠菌群不超过20000个/L，V类水中不超过40000个/L。水体自净能力一般，水体中有机化合物含量较高，重金属含量较高，微生物含量较高，属于不可直接饮用的水质。");
		service.insertSelective(water3);

		// 空气质量
		TReportEnvironmentLevel air = new TReportEnvironmentLevel();
		air.setCreateUser("system");
		air.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		air.setLevel(1);
		air.setType("air");
		air.setContent("目前您所处的地理区域AQI为：AQI指数0-50 1级，优：，参加户外活动呼吸清新空气，犹如置身天然氧吧，每一次呼吸都像被洗涤了一次一样，尽情享受户外美好时光。");
		service.insertSelective(air);

		TReportEnvironmentLevel air2 = new TReportEnvironmentLevel();
		air2.setCreateUser("system");
		air2.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		air2.setLevel(2);
		air2.setType("air");
		air2.setContent(
				"目前您所处的地理区域AQI为：AQI指数50-100，2级，良：可以正常进行室外活动，空气质量尚可，但某些污染物可能对极少数异常敏感人群健康有较弱影响，建议极少数异常敏感人群应减少户外活动。");
		service.insertSelective(air2);

		TReportEnvironmentLevel air3 = new TReportEnvironmentLevel();
		air3.setCreateUser("system");
		air3.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		air3.setLevel(3);
		air3.setType("air");
		air3.setContent("目前您所处的地理区域AQI为：AQI指数101-150，3级，轻度污染：敏感人群减少体力消耗大的户外活动，建议儿童、老年人及心脏病、呼吸系统疾病患者应减少长时间、高强度的户外锻炼。");
		service.insertSelective(air3);

		TReportEnvironmentLevel air4 = new TReportEnvironmentLevel();
		air4.setCreateUser("system");
		air4.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		air4.setLevel(4);
		air4.setType("air");
		air4.setContent(
				"目前您所处的地理区域AQI为：AQI指数151-200，4级，中度污染：对敏感人群影响较大，长期接触，易感人群病状有轻度加剧，健康人群出现刺激症状。可能对健康人群心脏、呼吸系统有影响，建议疾病患者避免长时间、高强度的户外锻练，一般人群适量减少户外运动。");
		service.insertSelective(air4);

		TReportEnvironmentLevel air5 = new TReportEnvironmentLevel();
		air5.setCreateUser("system");
		air5.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		air5.setLevel(5);
		air5.setType("air");
		air5.setContent(
				"目前您所处的地理区域AQI为：AQI指数201-300，5级，重度污染：心脏病和肺病患者症状显著加剧，运动耐受力降低，健康人群普遍出现症状，建议儿童、老年人和心脏病、肺病患者应停留在室内，停止户外运动，一般人群减少户外运动。");
		service.insertSelective(air5);

		TReportEnvironmentLevel air6 = new TReportEnvironmentLevel();
		air6.setCreateUser("system");
		air6.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		air6.setLevel(6);
		air6.setType("air");
		air6.setContent(
				"目前您所处的地理区域AQI为：AQI指数 300以上，6级，严重污染：健康人群运动耐受力降低，有明显强烈症状，提前出现某些疾病，建议儿童、老年人和病人应当留在室内，避免体力消耗，一般人群应避免户外活动。");
		service.insertSelective(air6);

		// 土壤
		TReportEnvironmentLevel soil = new TReportEnvironmentLevel();
		soil.setCreateUser("system");
		soil.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		soil.setLevel(1);
		soil.setType("soil");
		soil.setContent(
				"目前您所在的地理区域土壤级别为：I类一级，优: 国家规定的自然保护区（原有背景重金属含量高的除外）、集中式生活饮用水源地、茶园、牧场和其他保护地区的土壤，土壤质量基本上保持自然背景水平 ；土壤中汞含量不超过0.15mg/kg，砷不超过15mg/kg，铅不超过35mg/kg，六六六(六氯环己烷)不超过0.05mg/kg，滴滴涕不超过0.05mg/kg。其中汞、砷、铅属于重金属，六六六和滴滴涕属于杀虫剂，现在已被禁用。滴滴涕为白色晶体，不溶于水，溶于煤油，可制成乳剂，是有效的杀虫剂。为20世纪上半叶防止农业病虫害，减轻疟疾伤寒等蚊蝇传播的疾病危害起到了不小的作用。但由于其对环境污染过于严重，目前很多国家和地区已经禁止使用。");
		service.insertSelective(soil);

		TReportEnvironmentLevel soil2 = new TReportEnvironmentLevel();
		soil2.setCreateUser("system");
		soil2.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		soil2.setLevel(2);
		soil2.setType("soil");
		soil2.setContent(
				"目前您所在的地理区域土壤级别为：II类二级,良: 适用于一般农田、蔬菜地、茶园果园、牧场等到土壤，土壤质量基本上对植物和环境不造成危害和污染；土壤中汞含量当pH值小于6.5时不超过0.3mg/kg，6.5-7.5时不超过0.5 mg/kg，大于7.5时不超过1.0 mg/kg；砷当pH值小于6.5时不超过40mg/kg，6.5-7.5时不超过30 mg/kg，大于7.5时不超过25 mg/kg，铅当pH值小于6.5时不超过250mg/kg，6.5-7.5时不超过300 mg/kg，大于7.5时不超过350 mg/kg。六六六(六氯环己烷)不超过0.5mg/kg，滴滴涕不超过0.5mg/kg。其中汞、砷、铅属于重金属，六六六和滴滴涕属于杀虫剂，现在已被禁用。滴滴涕为白色晶体，不溶于水，溶于煤油，可制成乳剂，是有效的杀虫剂。为20世纪上半叶防止农业病虫害，减轻疟疾伤寒等蚊蝇传播的疾病危害起到了不小的作用。但由于其对环境污染过于严重，目前很多国家和地区已经禁止使用。");
		service.insertSelective(soil2);

		TReportEnvironmentLevel soil3 = new TReportEnvironmentLevel();
		soil3.setCreateUser("system");
		soil3.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		soil3.setLevel(3);
		soil3.setType("soil");
		soil3.setContent(
				"目前您所在的地理区域土壤级别为：III类三级,差: 适用于林地土壤及污染物容量较大的高背景值土壤和矿产附近等地的农田土壤（蔬菜地除外）。土壤质量基本上对植物和环境不造成危害和污染。土壤中汞含量不超过1.5mg/kg，砷不超过40mg/kg，铅不超过500mg/kg，六六六(六氯环己烷)不超过1.0mg/kg，滴滴涕不超过1.0mg/kg。其中汞、砷、铅属于重金属，六六六和滴滴涕属于杀虫剂，现在已被禁用。滴滴涕为白色晶体，不溶于水，溶于煤油，可制成乳剂，是有效的杀虫剂。为20世纪上半叶防止农业病虫害，减轻疟疾伤寒等蚊蝇传播的疾病危害起到了不小的作用。但由于其对环境污染过于严重，目前很多国家和地区已经禁止使用。");
		service.insertSelective(soil3);

		// 垃圾处理设施
		TReportEnvironmentLevel rubbish = new TReportEnvironmentLevel();
		rubbish.setCreateUser("system");
		rubbish.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		rubbish.setLevel(1);
		rubbish.setType("rubbish");
		rubbish.setContent(
				"目前您所在的地理区域距离垃圾处理设施距离为：垃圾转运站或填埋场大于1公里，垃圾焚烧厂大于3公里，优：垃圾处理设施距离较远，对您的生活不会造成影响，不存在垃圾臭气、垃圾渗沥液、垃圾焚烧产生的二噁英等污染物。");
		service.insertSelective(rubbish);

		TReportEnvironmentLevel rubbish2 = new TReportEnvironmentLevel();
		rubbish2.setCreateUser("system");
		rubbish2.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		rubbish2.setLevel(2);
		rubbish2.setType("rubbish");
		rubbish2.setContent("目前您所在的地理区域距离垃圾处理设施距离为：垃圾转运站或填埋场大于300米小于1000米，垃圾焚烧厂大于1000米小于3000米，良：潜在风险较小。");
		service.insertSelective(rubbish2);

		TReportEnvironmentLevel rubbish3 = new TReportEnvironmentLevel();
		rubbish3.setCreateUser("system");
		rubbish3.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		rubbish3.setLevel(3);
		rubbish3.setType("rubbish");
		rubbish3.setContent("目前您所在的地理区域距离垃圾处理设施距离为：垃圾转运站、填埋场小于300米或垃圾焚烧厂小于1000米，差，潜在风险较高。");
		service.insertSelective(rubbish3);

		// 绿地率
		TReportEnvironmentLevel afforest = new TReportEnvironmentLevel();
		afforest.setCreateUser("system");
		afforest.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		afforest.setLevel(1);
		afforest.setType("afforest");
		afforest.setContent(
				"目前您所在的小区绿地率标准为：绿地率30%以上：优，绿地率高，符合《城市居住区规划设计规范》（GB 50180—93）规定：新区建设不应低于30% 旧区改建不宜低于25%的要求，是绿化程度非常好的小区。");
		service.insertSelective(afforest);

		TReportEnvironmentLevel afforest2 = new TReportEnvironmentLevel();
		afforest2.setCreateUser("system");
		afforest2.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		afforest2.setLevel(2);
		afforest2.setType("afforest");
		afforest2.setContent(
				"目前您所在的小区绿地率标准为：25%-30% 良，绿地率一般；绿地率=绿地面积/用地面积×100%。《城市居住区规划设计规范》（GB 50180—93）规定：新区建设不应低于30% 旧区改建不宜低于25%。");
		service.insertSelective(afforest2);

		TReportEnvironmentLevel afforest3 = new TReportEnvironmentLevel();
		afforest3.setCreateUser("system");
		afforest3.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		afforest3.setLevel(3);
		afforest3.setType("afforest");
		afforest3.setContent(
				"目前您所在的小区绿地率标准为：25%以下：差，绿地率较低。绿地率=绿地面积/用地面积×100%。《城市居住区规划设计规范》（GB 50180—93）规定：新区建设不应低于30% 旧区改建不宜低于25%。");
		service.insertSelective(afforest3);

		// 容积率
		TReportEnvironmentLevel volume = new TReportEnvironmentLevel();
		volume.setCreateUser("system");
		volume.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		volume.setLevel(1);
		volume.setType("volume");
		volume.setContent("目前您您所在的小区容积率标准为：容积率小于3：优，居住密度低，舒适型。");
		service.insertSelective(volume);
		
		TReportEnvironmentLevel volume2 = new TReportEnvironmentLevel();
		volume2.setCreateUser("system");
		volume2.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		volume2.setLevel(2);
		volume2.setType("volume");
		volume2.setContent("目前您您所在的小区容积率标准为：容积率大于3小于5，居住密度尚可，较为舒适：良。");
		service.insertSelective(volume2);
		
		TReportEnvironmentLevel volume3 = new TReportEnvironmentLevel();
		volume3.setCreateUser("system");
		volume3.setCode(WebHelper.getInstance().getUniqueCode("TL"));
		volume3.setLevel(3);
		volume3.setType("volume");
		volume3.setContent("目前您您所在的小区容积率标准为：容积率大于5：差，居住密度太大，不适宜居住。");
		service.insertSelective(volume3);
		
	}
}