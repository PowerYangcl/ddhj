<#-- 环境报告h5模板 -->
<!DOCTYPE html>
<html>
<head>
<base id="base" href="http://api.sys.ecomapit.com/ddhj/">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>点点-环境报告</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width, user-scalable=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0" />
<link rel="stylesheet" type="text/css" href="css/report/base.css" />
<link rel="stylesheet" type="text/css" href="css/report/style.css" />
<link rel="stylesheet" type="text/css" href="css/report/swiper.min.css" />
<link rel="stylesheet" type="text/css" href="css/report/report.css" />
<style>
</style>
</head> 
<body>
    <div id="btnNext" class="swiper-button-next" style="display:none"></div>
    <div id="btnPrev" class="swiper-button-prev" style="display:none"></div>
    <div class="baogaoLookMore" style="display:none" id="btnUp">向上滚动查看更多</div>
    <!-- Swiper -->
    <div class="swiper-container verticalPage">
    	<div class="swiper-wrapper">
            <div class="swiper-slide">
                <div class="oLi">
		            <img class="bg_logo01" src="images/report/bg_logo01.png">
		            <h1><span>楼盘质量报告</span></h1>
		            <div class="time">更新日期${updateTime}</div>
		            <div class="position">${title}</div>
		            <div class="copyright">北京亿科云科有限公司版权所有</div>
		            <div class="point"></div>
                </div>
            </div>
            <div class="swiper-slide">
                <div class="oLi">
		            <img class="bg_logo02" src="images/report/bg_logo02.png">
		            <div class="dir">目录</div>
		            <div class="con">
		                <p>区域环境质量总述</p>
		                <p>区域环境质量排名</p>
		                <p>区域环境质量评价</p>
		                <p>各环境质量参数详解</p>
		            </div>
		            <div class="point"></div>
                </div>
            </div>
            <div class="swiper-slide">
                <div class="oLi">
		            <img class="bg_logo02" src="images/report/bg_logo02.png">
		            <div class="detail_top" id="detail_top_2">
			            <div class="dir02">区域环境质量总述</div>
			            <div class="point"></div>
			            <div class="detail" style="top:14%">
			                <p class="txt">环境质量是指在一个具体的环境内，环境的总体或环境的某些要素，对人群的生存和繁衍以及社会经济发展的适宜程度，是反映人类的具体要求而形成的对环境评定的一种概念。到20世纪60年代，随着环境问题的出现，常用环境质量的好坏来表示环境遭受污染的程度。环境质量可分为自然环境质量和社会环境质量，本报告中主要针对您所在区域的自然环境质量，通过分析各项环境数据，为您的生活、学习、工作、居住、出行提供一定的参考与帮助。</p>
			                <p class="txt">自然环境再细分可分为物理环境质量、化学环境质量及生物环境质量。物理环境质量是用来衡量周围物理环境条件的，比如自然界气候、水文、土壤、噪音、辐射源、绿化率、容积率等等。化学环境质量是指周围工业是否产生化学环境要素，如果周围的重污染工业比较多，那么产生的化学环境要素就多一些，产生的污染比较严重，化学环境质量就比较差。生物环境质量是针对周围生物群落的构成特点而言的。不同地区的生物群落结构及组成的特点不同，其生物环境质量就显出差别，生物群落比较合理的地区，生物环境质量就比较好，生物群落比较差的地区生物环境质量就比较差。由于您所在区域处于城市，生物群落相对比较单一，因此本报告主要分析您周边的物理环境质量和化学环境质量。</p>
			                <p class="txt">您所在小区位于（北纬${lat},东经${lng}），属于${weatherDistribution}</p>
			            </div>
		            </div>
	            </div>
            </div>
            <div class="swiper-slide">
                <div class="oLi">
		            <img class="bg_logo02" src="images/report/bg_logo02.png">
		            <div class="point"></div>
		            <div class="detail_top"  id="detail_top_3">
			            <div class="detail detail02" >
			                <p class="txt" style="width:100%;text-align: left;">区域附近一个月内各项自然环境质量参数如下表所示</p>
			                <div class="detail_table" style="margin-top: -10px;">
			                    <table border="1">
			                      <thead>
			                        <tr>
			                          <th class="cel-26">环境参数</th>
			                          <th class="cel-37">参数值</th>
			                          <th class="cel-37">等级</th>
			                        </tr>
			                      </thead>
			                      <tbody>
			                      	<#list environmentIndexs as env>
			                        <tr>
			                          <td>${env.name}</td>
			                          <td>${env.value}</td>
			                          <td>${env.level}</td>
			                        </tr>
			                        </#list>
			                      </tbody>
			                    </table>
			                </div>
			            </div>
		            </div>
	            </div>
            </div>
            <div class="swiper-slide">
                <div class="oLi">
		            <img class="bg_logo02" src="images/report/bg_logo02.png">
		            <div class="point"></div>
		            <div class="detail_top" id="detail_top_4">
			            <div class="detail detail02">
			            	<h2>区域环境质量排名</h2>
			                <p class="txt" style="text-align: center;margin-top: -5px;">您所在区域环境质量在所处城市排名如下</p>
			                <div class="detail_table" style="margin-top: -10px;">
			                    <table border="1">
			                      <thead>
			                        <tr>
			                          <th class="cel-26">环境数据</th>
			                          <th style="width:20%;">排名</th>
			                          <th class="cel-26">时效</th>
			                          <th class="cel-26">区域</th>
			                        </tr>
			                      </thead>
			                     <tbody>
			                     	<#list environmentIndexs as env>
				                        <tr>
				                          <td>${env.name}</td>
				                          <td>${env.sort}</td>
				                          <td>${env.sortTime}</td>
				                          <td>${env.city}</td>
				                        </tr>
			                     	</#list>
			                      </tbody>
			                      </table>
							</div>
						</div>
					</div>
	            </div>
            </div>
           <div class="swiper-slide">
                <div class="oLi">
		            <img class="bg_logo02" src="images/report/bg_logo02.png">
		            <div class="point"></div>
		            <div class="detail_top" id="detail_top_5">
			            <div class="detail detail02">
			                <h2>区域环境质量评价</h2>
			                <div class="detail_table">
								<table border="1">
								  <thead>
									<tr>
									  <th class="cel-26">环境参数</th>
									  <th class="cel-26">等级</th>
									  <th style="width:48%;">评价</th>
								  </thead>
								 <tbody>
									<#list environmentIndexs1 as env>
									<tr>
										<#if env.name=='噪音'>
										  <td>${env.name}</td>
										  <td>${env.level}</td>
										  <td style="height:65px;">${env.evaluate}</td>
										<#else>
										  <td>${env.name}</td>
										  <td>${env.level}</td>
										  <td style="height:110px;">${env.evaluate}</td>
										</#if>
									</tr>
									</#list>
								 </tbody>
								 </table>
			                </div>
			           	</div>
		           	</div>
                </div>
            </div>
           <div class="swiper-slide">
                <div class="oLi">
		            <img class="bg_logo02" src="images/report/bg_logo02.png">
		            <div class="point"></div>
		            <div class="detail_top" id="detail_top_6">
			            <div class="detail detail02">
			                <h2>区域环境质量评价</h2>
			                <div class="detail_table">
								<table border="1">
								  <thead>
									<tr>
									  <th class="cel-26">环境参数</th>
									  <th class="cel-26">等级</th>
									  <th style="width:48%;">评价</th>
								  </thead>
								 <tbody>
									<#list environmentIndexs2 as env>
									<tr>
										<#if env.name=='高压电辐射'>
										  <td>${env.name}</td>
										  <td>${env.level}</td>
										  <td style="height:75px;">${env.evaluate}</td>
										<#else>
										  <td>${env.name}</td>
										  <td>${env.level}</td>
										  <td style="height:110px;">${env.evaluate}</td>
										</#if>
									</tr>
									</#list>
								 </tbody>
								 </table>
			                </div>
			           	</div>
		           	</div>
                </div>
            </div>
           <div class="swiper-slide">
                <div class="oLi">
		            <img class="bg_logo02" src="images/report/bg_logo02.png">
		            <div class="point"></div>
		            <div class="detail_top">
		            <div class="detail detail02">
		                <h2>区域环境质量评价</h2>
		                <div class="detail_table">
							<table border="1">
							  <thead>
								<tr>
								  <th class="cel-26">环境参数</th>
								  <th class="cel-26">等级</th>
								  <th style="width:48%;">评价</th>
							  </thead>
							 <tbody>
								<#list environmentIndexs3 as env>
									<tr>
										<#if env.name=='污染源<br>化工厂'>
										  <td>${env.name}</td>
										  <td>${env.level}</td>
										  <td style="height:65px;">${env.evaluate}</td>
										<#else>
										  <td>${env.name}</td>
										  <td>${env.level}</td>
										  <td style="height:110px;">${env.evaluate}</td>
										</#if>
									</tr>
								</#list>
							 </tbody>
							 </table>
		                </div>
		           	</div>
		           	</div>
                </div>
            </div>
           <div class="swiper-slide">
                <div class="oLi">
		            <img class="bg_logo02" src="images/report/bg_logo02.png">
		            <div class="point"></div>
		            <div class="detail_top">
		            <div class="detail detail02">
		                <h2>区域环境质量评价</h2>
		                <div class="detail_table">
							<table border="1">
							  <thead>
								<tr>
								  <th class="cel-26">环境参数</th>
								  <th class="cel-26">等级</th>
								  <th style="width:48%;">评价</th>
							  </thead>
							 <tbody>
								<#list environmentIndexs4 as env>
								<tr>
									<#if env.name==''>
									<#else>
									  <td>${env.name}</td>
									  <td>${env.level}</td>
									  <td style="height:110px;">${env.evaluate}</td>
									</#if>
								</tr>
								</#list>
							 </tbody>
							 </table>
		                </div>
		           	</div>
		           	</div>
                </div>
            </div>
            <div class="swiper-slide">
                <div class="oLi">
                    <img class="bg_logo02" src="images/report/bg_logo02.png">
                    <div class="detail_top">
                    <div class="dir02">各环境质量参数详解</div>
					<div class="detail" id="detail" style="top:14%">
		                <h2>空气质量</h2>
		                <p class="txt">空气质量直接反映空气的污染程度，它是依据空气中污染物浓度的高低来判断的。空气中的污染物主要包括：总悬浮颗粒物：是指漂浮在空气中的固态和液态颗粒物的总称，其粒径范围约为0.1-100 微米。通常把环境空气中空气动力学当量直径在10微米以下的颗粒物称为PM10。其中粒径小于等于 2.5微米的颗粒物为PM2.5，PM2.5粒径小，面积大，活性强，易附带有毒、有害物质，且在大气中的停留时间长、输送距离远，因而对人体健康和大气环境质量的影响极大。二氧化氮：是一种棕红色、高度活性的气态物质。二氧化硫：是一种常见的和重要的大气污染物，是一种无色有刺激性的气体。</p>
		                <p class="txt">空气污染指数(API)是一种反映和评价空气质量的方法，这个指数通常是通过监测二氧化硫、PM10、PM2.5、二氧化氮、一氧化碳、臭氧得出的。空气质量指数(AQI)与市民们的直观感受更加接近。</p>
                    </div>
                    </div>
                </div>
            </div>
            <div class="swiper-slide">
                <div class="oLi">
                    <img class="bg_logo02" src="images/report/bg_logo02.png">
                    <div class="detail_top">
                    <div class="detail" id="detail">
                    	<p class="txt">其结果简明直观，使用方便，适用于表示城市的短期空气质量状况和变化趋势。</p>
		                <h2>噪音</h2>
		                <p class="txt">噪音，会影响人类的生活。从总体讲噪音是由物体振动产生，凡是妨碍人们正常休息、学习和工作的声音，以及对人们要听的声音产生干扰的声音。噪音污染主要来源于交通运输、车辆鸣笛、工业噪音、建筑施工、社会噪音如音乐厅、高音喇叭、早市和人的大声说话等。且随工业与交通的发展而日 趋严重，噪音污染是除大气污 染，水体污染外的城市第三大污染。</p>
		                <h2>水质</h2>
		                <p class="txt">水质是水体的物理性质（如色度、浊度、臭味等）、化学组成（无机物和有机物的含量）、生物学特性（细菌、微生物、浮游生物、底栖生物）的总称。所有的水都含有来自自然界或人类活动的各种溶质及废弃物。</p>
                    </div>
                    </div>
                </div>
            </div>
            <div class="swiper-slide">
                <div class="oLi">
                    <img class="bg_logo02" src="images/report/bg_logo02.png">
                    <div class="detail_top">
                    <div class="detail" id="detail">
                    	<p class="txt">表征水体水质特征的参数有：感官参数（透明度、嗅和味等）、物理参数（水温、浊度、电导率、盐度和颜色等）、生物学参数（叶绿素、藻类生产力和浮游生物等）、微生物学参数（细菌总数、大肠菌群、病原微生物和致病病毒等）和化学参数（指溶解于水中的所有天然和人造的有机与无机物、溶解性气体和放射性物质）。</p>
		                <h2>土壤</h2>
		                <p class="txt">土壤是地球陆地的表面由矿物质、有机质、水、空气和生物组成的，具有肥力并能生长植物的疏松表层。矿物质和腐殖质组成的固体土粒是土壤的主体，约占土壤体积的50%，固体颗粒间的孔隙由气体和水分占据。土壤气体中绝大部分是由大气层进入的氧气、氮气等，小部分为土壤内的生命活动产生的二氧化碳和水汽等。土壤中的水分主要由地表进入土中，其中包括许多溶解物质。</p>
		                <h2>污染源　－　垃圾处理设施</h2>
		                <p class="txt">垃圾处理设施包括垃圾转运站、垃圾填埋场和垃圾焚烧厂。垃圾转运站的安全距离是300米，垃圾焚烧厂的安全距离是1000米，</p>
                    </div>
                    </div>
                </div>
            </div>
            <div class="swiper-slide">
                <div class="oLi">
                    <img class="bg_logo02" src="images/report/bg_logo02.png">
                    <div class="detail_top">
                    <div class="detail" id="detail">
		                <p class="txt">在安全距离范围内，如果是垃圾转运站或填埋场，有可能面临垃圾成堆，蚊蝇漫天，臭气熏天，夏天不敢开窗的情况，垃圾渗沥液还有可能渗入地下，污染地下水。如果是垃圾焚烧厂，产生的二噁英属于致癌物质，对人体健康非常有害。垃圾渗滤液是指来源于垃圾填埋场中垃圾本身含有的水分、进入填埋场的雨雪水及其他水分，扣除垃圾、覆土层的饱和持水量，并经历垃圾层和覆土层而形成的一种高浓度的有机废水。二恶英，又称二氧杂芑，是一种无色无味、毒性严重的脂溶性物质，二恶英实际上是二恶英类一个简称，它指的并不是一种单一物质，而是结构和性质都很相似的包含众多同类物或异构体的两大类有机化合物。这类物质非常稳定，熔点较高，极难溶于水，可以溶于大部分有机溶剂，是无色无味的脂溶性物质，所以非常容易在生物体内积累。</p>
		                <h2>污染源　－　化工厂</h2>
		                <p class="txt">伴随着中国经济高速发展，中国的化工产品价格也在节节攀升。
                    </div>
                    </div>
                </div>
            </div>
            <div class="swiper-slide">
                <div class="oLi">
                    <img class="bg_logo02" src="images/report/bg_logo02.png">
                    <div class="detail_top">
                    <div class="detail" id="detail">
		                <p class="txt">一位内部人士说，当年有的化工产品价格甚至能达到原材料成本的5至6倍。所以说，一个地区的化工产业可以极好地带动该地区的经济发展。化工厂多依水而建，往往充足的水源可以吸引更多的化工企业。但是，中国的化工产业因此存在很多问题。很多企业一般不愿意购买处理污染的设备。这导致了严重的空气污染和水污染。</p>
		                <h2>危险品存放</h2>
		                <p class="txt">危险品包括爆炸品、易燃物、氧化剂、毒害品、放射性物、腐蚀品等。根据《危险化学品经营企业开业条件和技术要求》，大中型危险化学品仓库应与周围公共建筑物、交通干线（公路、铁路、水路）、工矿企业等距离至少保持1000米。</p>
		                <h2>辐射源　－　高压电辐射</h2>
		                <p class="txt">高压线产生的电磁场一般称为极低频电磁场或者叫工频电场、工频磁场。</p>
                    </div>
                    </div>
                </div>
            </div>
            <div class="swiper-slide">
                <div class="oLi">
                    <img class="bg_logo02" src="images/report/bg_logo02.png">
                    <div class="detail_top">
                    <div class="detail" id="detail">
		                <p class="txt">也就是说高压线、变电站根本就不会产生所谓的电磁辐射，它产生的工频电场、工频磁场是感应电场感应磁场，因为它的波长非常长，所以它不会像电磁辐射那样被人体直接吸收，但是会在人体里头感应出电流来，这个感应电流需要控制。工频电场和工频磁场会在人体中产生感应电流，为了防止对人体产生影响，需要将感应电流密度控制在一定的范围内。国际非电离辐射防护委员会在1998年发布了一个指导原则，规定工频电场强度对公众的安全值是5000伏每米，工频磁感应强度对公众的限值是100微特斯拉。目前我们国家采用的标准比这个还严格，为4000伏每米和100微特斯拉。</p>
		                <h2>绿地率</h2>
		                <p class="txt">在计算绿地率时，对绿地的要求非常严格。绿地率所指的“居住区用地范围内各类绿地”主要包括公共绿地、宅旁绿地等。其中，公共绿地，</p>
                    </div>
                    </div>
                </div>
            </div>
            <div class="swiper-slide">
                <div class="oLi">
                    <img class="bg_logo02" src="images/report/bg_logo02.png">
                    <div class="detail_top">
                    <div class="detail" id="detail">
						<p class="txt">又包括居住区公园、小游园、组团绿地及其他的一些块状、带状化公共绿地。而宅旁绿地等庭院绿化的用地面积，在涉及计算时也要求距建筑外墙1.5米和道路边线1米以内的用地，不得计入绿化用地。在通常的情况下，许多开发商都是在售楼书上印制出“绿化率”一词，其实这是不准确、不规范的用词，准确的只有“绿地率”和“绿化覆盖率”两种说法。</p>
                		<h2>容积率</h2>
                		<p class="txt">容积率又称建筑面积毛密度，指项目用地范围内地上总建筑面积与项目总用地面积的比值。容积率是衡量建设用地使用强度的一项重要指标，对于开发商来说，容积率决定地价成本在房屋中占的比例，而对于住户来说，容积率直接涉及到居住的舒适度。现行城市规划法规体系下编制的各类居住用地的控制性详细规划，一般而言，容积率分为：独立别墅为0.2~0.5,联排别墅为0.4~0.7,6层以下多层住宅为0.8~1.2,，11层小高层住宅为1.5~2.0,18层高层住宅为1.8~2.5,19层以上住宅为2.4~4.5,住宅小区容积率小于1.0的，为非普通住宅。</p>
                    </div>
                    </div>
                </div>
            </div>
    	</div>
    </div>
<script src="js/plugins/jquery-2.1.4-min.js"></script>
<script src="js/plugins/swiper/swiper.min.js"></script>
<script src="js/report/report.js"></script>
</body>
</html>