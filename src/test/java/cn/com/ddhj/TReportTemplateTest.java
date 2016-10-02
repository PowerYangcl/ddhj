package cn.com.ddhj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.model.TReportTemplate;
import cn.com.ddhj.service.ITReportTemplateService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TReportTemplateTest extends BaseTest {

	@Autowired
	private ITReportTemplateService service;

	@Test
	public void insert() {
		TReportTemplate report = new TReportTemplate();
		report.setCode(WebHelper.getInstance().getUniqueCode("T"));
		report.setType("report");
		report.setContent(
				"环境质量报告根据所监测的各项环境数据，按一定的标准和方法对某区域范围内的环境质量进行说明、评定和预测。本报告包含以下环境指标：噪音、水质、空气质量、土壤、垃圾处理设施、绿地率（小区）、容积率（小区）。");
		report.setCreateUser("system");
		service.insertSelective(report);

		TReportTemplate noise = new TReportTemplate();
		noise.setCode(WebHelper.getInstance().getUniqueCode("T"));
		noise.setType("noise");
		noise.setContent(
				"噪音，会影响人类的生活。从总体讲噪音是由物体振动产生，凡是妨碍人们正常休息、学习和工作的声音，以及对人们要听的声音产生干扰的声音。噪音污染主要来源于交通运输、车辆鸣笛、工业噪音、建筑施工、社会噪音如音乐厅、高音喇叭、早市和人的大声说话等。且随工业与交通的发展而日 趋严重，噪音污染是除大气污 染，水体污染外的城市第三大污染。");
		noise.setCreateUser("system");
		service.insertSelective(noise);

		TReportTemplate water = new TReportTemplate();
		water.setCode(WebHelper.getInstance().getUniqueCode("T"));
		water.setType("water");
		water.setContent(
				"水质是水体的物理性质（如色度、浊度、臭味等）、化学组成（无机物和有机物的含量）、生物学特性（细菌、微生物、浮游生物、底栖生物）的总称。水是氢和氧的化合物，但绝对纯净的水在天然状态下是不存在的。所有的水都含有来自自然界或人类活动的各种溶质及废弃物。降雨吸收大气中的气体和空气中的颗粒物而变成地表径流，部分降水落地穿过含有机质和矿物质的地层进入地下，吸收矿物质和气体；部分降水落地流入江河湖及大海，溶解有机与无机物以及携带细菌和其他生物。类活动使有毒化合物、放射性物质、致病的细菌和病毒污染水体。因此，水体受到自然界及人类活动的影响不同，其质量特点就不同。表征水体水质特征的参数有：感官参数（透明度、嗅和味等）、物理参数（水温、浊度、电导率、盐度和颜色等）、生物学参数（叶绿素、藻类生产力和浮游生物等）、微生物学参数（细菌总数、大肠菌群、病原微生物和致病病毒等）和化学参数（指溶解于水中的所有天然和人造的有机与无机物、溶解性气体和放射性物质）");
		water.setCreateUser("system");
		service.insertSelective(water);

		TReportTemplate air = new TReportTemplate();
		air.setCode(WebHelper.getInstance().getUniqueCode("T"));
		air.setType("air");
		air.setContent(
				"空气质量直接反映空气的污染程度，它是依据空气中污染物浓度的高低来判断的。空气污染是一个复杂的现象，在特定时间和地点空气污染物浓度受到许多因素影响。空气污染源也可分为自然的和人为的两大类。自然污染源是由于自然原因（如火山爆发，森林火灾等）而形成，人为污染源是由于人们从事生产和生活活动而形成，其中包括车辆、船舶、飞机的尾气、工业污染、居民生活和取暖、垃圾焚烧等。城市的发展密度、地形地貌和气象等也是影响空气质量的重要因素。空气中的污染物主要包括：\n"
						+ "总悬浮颗粒物：是指漂浮在空气中的固态和液态颗粒物的总称，其粒径范围约为0.1-100 微米。有些颗粒物因粒径大或颜色黑可以为肉眼所见，如烟尘。有些则小到使用电子显微镜才可观察到。通常把环境空气中空气动力学当量直径在10微米以下的颗粒物称为PM10，又称为可吸入颗粒物或飘尘。其中粒径小于等于 2.5微米的颗粒物为PM2.5，它能较长时间悬浮于空气中，其在空气中含量浓度越高，就代表空气污染越严重。虽然PM2.5只是地球大气成分中含量很少的组分，但它对空气质量和能见度等有重要的影响。与较粗的大气颗粒物相比，PM2.5粒径小，面积大，活性强，易附带有毒、有害物质（例如，重金属、微生物等），且在大气中的停留时间长、输送距离远，因而对人体健康和大气环境质量的影响极大。颗粒物的直径越小，进入呼吸道的部位越深。10微米直径的颗粒物通常沉积在上呼吸道，5微米直径的可进入呼吸道的深部，2微米以下的可100%深入到细支气管和肺泡。\n"
						+ "二氧化氮：是一种棕红色、高度活性的气态物质。二氧化氮在臭氧的形成过程中起着重要作用。人为产生的二氧化氮主要来自高温燃烧过程的释放，比如机动车、电厂废气的排放等。 二氧化氮还是酸雨的成因之一。二氧化硫：是一种常见的和重要的大气污染物，是一种无色有刺激性的气体。二氧化硫主要来源于含硫燃料（如煤和石油）的燃烧；含硫矿石（特别是含硫较多的有色金属矿石）的冶炼；化工、炼油和硫酸厂等的生产过程。氮氧化物：种类很多，但主要是一氧化氮(NO)和(NO2)，它们是常见的大气污染物。一氧化碳：是煤、石油等含碳物质不完全燃烧的产物，是一种无色、无臭、无刺激性的有毒气体，几乎不溶于水，在空气中不易与其他物质产生化学反应，故可在大气中停留2～3年之久。如局部污染严重，对人群健康有一定危害。\n"
						+ "空气污染指数(API)是一种反映和评价空气质量的方法，这个指数通常是通过监测二氧化硫、PM10、PM2.5、二氧化氮、一氧化碳、臭氧得出的。空气质量指数(AQI)与市民们的直观感受更加接近。其结果简明直观，使用方便，适用于表示城市的短期空气质量状况和变化趋势。空气污染指数是根据环境空气质量标准和各项污染物对人体健康和生态环境的影响来确定污染指数的分级及相应的污染物浓度限值。");
		air.setCreateUser("system");
		service.insertSelective(air);

		TReportTemplate soil = new TReportTemplate();
		soil.setCode(WebHelper.getInstance().getUniqueCode("T"));
		soil.setType("soil");
		soil.setContent(
				"土壤是地球陆地的表面由矿物质、有机质、水、空气和生物组成的，具有肥力并能生长植物的疏松表层。矿物质和腐殖质组成的固体土粒是土壤的主体，约占土壤体积的50%，固体颗粒间的孔隙由气体和水分占据。土壤气体中绝大部分是由大气层进入的氧气、氮气等，小部分为土壤内的生命活动产生的二氧化碳和水汽等。土壤中的水分主要由地表进入土中，其中包括许多溶解物质。");
		soil.setCreateUser("system");
		service.insertSelective(soil);

		TReportTemplate rubbish = new TReportTemplate();
		rubbish.setCode(WebHelper.getInstance().getUniqueCode("T"));
		rubbish.setType("rubbish");
		rubbish.setContent(
				"垃圾处理设施包括垃圾转运站、垃圾填埋场和垃圾焚烧厂。垃圾转运站的安全距离是300米，垃圾焚烧厂的安全距离是1000米，在安全距离范围内，如果是垃圾转运站或填埋场，有可能面临垃圾成堆，蚊蝇漫天，臭气熏天，污水顺着马路横流，无法行走，夏天不敢开窗的情况，垃圾渗沥液还有可能渗入地下，污染地下水。如果是垃圾焚烧厂，产生的二噁英属于致癌物质，对人体健康非常有害。\n垃圾渗滤液是指来源于垃圾填埋场中垃圾本身含有的水分、进入填埋场的雨雪水及其他水分，扣除垃圾、覆土层的饱和持水量，并经历垃圾层和覆土层而形成的一种高浓度的有机废水。二恶英，又称二氧杂芑(qǐ)，是一种无色无味、毒性严重的脂溶性物质，二恶英实际上是二恶英类一个简称，它指的并不是一种单一物质，而是结构和性质都很相似的包含众多同类物或异构体的两大类有机化合物。二恶英包括210种化合物，这类物质非常稳定，熔点较高，极难溶于水，可以溶于大部分有机溶剂，是无色无味的脂溶性物质，所以非常容易在生物体内积累。");
		rubbish.setCreateUser("system");
		service.insertSelective(rubbish);

		TReportTemplate afforest = new TReportTemplate();
		afforest.setCode(WebHelper.getInstance().getUniqueCode("T"));
		afforest.setType("afforest");
		afforest.setContent(
				"城市的总绿地率是指城市建成区内各绿化用地总面积占城市建成区总面积的比例。也可计算建成区内一定地区的绿地率。如居住区绿地率（描述的是居住区用地范围内各类绿地的总和与居住区用地的比率（% ）。绿地率所指的\"居住区用地范围内各类绿地\"主要包括公共绿地、宅旁绿地等。其中，公共绿地，又包括居住区公园、小游园、组团绿地及其他的一些块状、带状化公共绿地。绿地率=绿地面积/用地面积×100%。\n什么是绿地率？什么是绿化覆盖率？开发商平时在售楼书上印制的有关绿化的指标究竟是绿地率还是绿化覆盖率？地下停车场上、化粪池等上面的绿化算不算绿地率？开发商做的屋顶绿化算不算绿地率……许多购房者对此并不了解，他们想弄清楚绿地率和绿化覆盖率究竟是怎么回事？在计算绿地率时，对绿地的要求非常严格。绿地率所指的“居住区用地范围内各类绿地”主要包括公共绿地、宅旁绿地等。其中，公共绿地，又包括居住区公园、小游园、组团绿地及其他的一些块状、带状化公共绿地。而宅旁绿地等庭院绿化的用地面积，在涉及计算时也要求距建筑外墙1.5米和道路边线1米以内的用地，不得计入绿化用地。此外，还有几种情况也不能计入绿地率的绿化面积，如地下车库、化粪池。这些设施的地表覆土一般达不到3米的深度，在上面种植大型乔木，成活率较低，所以计算绿地率时不能计入。在通常的情况下，许多开发商都是在售楼书上印制出“绿化率”一词，其实这是不准确、不规范的用词，国家有关园林绿化用语根本就没有这个用语，准确的只有“绿地率”和“绿化覆盖率”两种说法。绿化覆盖率可以在小区绿得广。像地下车库这样大面积的底下设施，它的地表虽然种不了树，但可以种草；像距建筑外墙1.5米这样的范围，虽然不算正式绿地，但若能种一些草，总比地砖铺砌更吸引人.在小区规划设计中，计算绿化覆盖率所指的绿地，简单地说，就是有块草皮便可以计入，所以绿化覆盖率有时能做到60%以上。在开发商销售楼盘的时候，有些开发商当然喜欢引用绿化覆盖率的概念。绿化覆盖率一般比绿地率高出20%-40%。");
		afforest.setCreateUser("system");
		service.insertSelective(afforest);

		TReportTemplate volume = new TReportTemplate();
		volume.setCode(WebHelper.getInstance().getUniqueCode("T"));
		volume.setType("volume");
		volume.setContent(
				"容积率又称建筑面积毛密度，指项目用地范围内地上总建筑面积与项目总用地面积的比值。容积率是衡量建设用地使用强度的一项重要指标，对于开发商来说，容积率决定地价成本在房屋中占的比例，而对于住户来说，容积率直接涉及到居住的舒适度。现行城市规划法规体系下编制的各类居住用地的控制性详细规划，一般而言，容积率分为：独立别墅为0.2~0.5,联排别墅为0.4~0.7,6层以下多层住宅为0.8~1.2,，11层小高层住宅为1.5~2.0,18层高层住宅为1.8~2.5,19层以上住宅为2.4~4.5,住宅小区容积率小于1.0的，为非普通住宅。 在计算容积率时，建筑面积一般按照《建筑工程建筑面积计算规范》（GB/T50353-2013）的规定计算；存在以下特殊情况: 1、建筑底层架空作为通道、公共停车、布置绿化小品、居民休闲、配套设施等公共用途的，架空层层高宜在2.8 米至3.6米之间，其建筑面积不计入容积率; 2、建筑物顶部有围护结构的楼梯间、水箱间、电梯机房，结构（设备管道）转换层，底层车库、杂物间等。当层高在2.2米及以上的按全面积计入容积率，若层高不足2.2米的按1/2面积计入容积率; 3、建筑物的阳台，不论是凹阳台、挑阳台、封闭阳台、不封闭阳台均按其水平投影面积的一半计算，当进深超过1.8米的各类阳台，均按全面积计入容积率; 4、半地下室凡顶板标高超出室外地坪标高1.0米以上的建筑部分应计入地上建筑面积计算值；不足1.0米的，不计入容积率; 5、如建筑室外地坪标高不一致时，以周边最近的城市道路标高为准加上0.2米作为室外地坪，之后再按上述规定核准。");
		volume.setCreateUser("system");
		service.insertSelective(volume);

	}
}
