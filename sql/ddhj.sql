/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : ddhj

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2016-10-04 08:36:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_webcode
-- ----------------------------
DROP TABLE IF EXISTS `sys_webcode`;
CREATE TABLE `sys_webcode` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` char(32) DEFAULT '',
  `code_start` varchar(100) DEFAULT '' COMMENT '编码起始',
  `date_apply` char(6) DEFAULT '' COMMENT '日期参数',
  `min_number` int(11) DEFAULT '100000' COMMENT '最小数字',
  `now_number` int(11) DEFAULT '100000' COMMENT '当前数字',
  `code_note` varchar(45) DEFAULT '' COMMENT '备注',
  `flag_date` int(11) DEFAULT '1' COMMENT '是否日期列',
  PRIMARY KEY (`zid`),
  UNIQUE KEY `code_start` (`code_start`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='系统编码表';

-- ----------------------------
-- Records of sys_webcode
-- ----------------------------
INSERT INTO `sys_webcode` VALUES ('1', '829530e188b811e6b41c8c89a5086523', 'T', '161003', '100000', '100024', '', '1');
INSERT INTO `sys_webcode` VALUES ('2', '4cb21976892d11e69aa28c89a5086523', 'TL', '161003', '100000', '100045', '', '1');
INSERT INTO `sys_webcode` VALUES ('3', '05319fe9895411e69aa28c89a5086523', 'LP', '161003', '100000', '100040', '', '1');

-- ----------------------------
-- Table structure for t_airenviroment
-- ----------------------------
DROP TABLE IF EXISTS `t_airenviroment`;
CREATE TABLE `t_airenviroment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datacode` varchar(20) DEFAULT NULL,
  `areacode` varchar(20) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `lng` varchar(10) DEFAULT NULL,
  `lat` varbinary(10) DEFAULT NULL,
  `hourAQI` varchar(20) DEFAULT NULL,
  `dayAQI` varchar(20) DEFAULT NULL,
  `quality` varchar(20) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ID` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_airenviroment
-- ----------------------------

-- ----------------------------
-- Table structure for t_area
-- ----------------------------
DROP TABLE IF EXISTS `t_area`;
CREATE TABLE `t_area` (
  `areacode` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`areacode`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_area
-- ----------------------------

-- ----------------------------
-- Table structure for t_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_authority`;
CREATE TABLE `t_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authority` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_authority
-- ----------------------------

-- ----------------------------
-- Table structure for t_basicinform
-- ----------------------------
DROP TABLE IF EXISTS `t_basicinform`;
CREATE TABLE `t_basicinform` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `tsemail` varchar(50) DEFAULT NULL,
  `port` varchar(10) DEFAULT NULL,
  `pop3` varchar(30) DEFAULT NULL,
  `compaddress` varchar(100) DEFAULT NULL,
  `compcontract` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_basicinform
-- ----------------------------

-- ----------------------------
-- Table structure for t_building
-- ----------------------------
DROP TABLE IF EXISTS `t_building`;
CREATE TABLE `t_building` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `areacode` varchar(20) DEFAULT NULL,
  `location` varchar(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `lng` varchar(10) DEFAULT NULL,
  `lat` varchar(10) DEFAULT NULL,
  `greenrateindex` varchar(10) DEFAULT NULL,
  `volumeratioindex` varchar(10) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_building
-- ----------------------------

-- ----------------------------
-- Table structure for t_city
-- ----------------------------
DROP TABLE IF EXISTS `t_city`;
CREATE TABLE `t_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(50) DEFAULT NULL COMMENT 'uuid',
  `city_code` varchar(50) NOT NULL COMMENT '城市编码',
  `name` varchar(100) NOT NULL COMMENT '城市中文名称',
  `pinyin` varchar(100) DEFAULT NULL COMMENT '城市名称拼音',
  `air` int(11) DEFAULT '0' COMMENT '空气质量 0 可查询 1 不可查询',
  `pm` int(11) DEFAULT '0' COMMENT 'pm2.5查询 0 可查询 1 不可查询',
  `create_user` varchar(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(20) NOT NULL COMMENT '最后修改人',
  `update_time` datetime NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `city_code` (`city_code`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='城市列表';

-- ----------------------------
-- Records of t_city
-- ----------------------------

-- ----------------------------
-- Table structure for t_colletion
-- ----------------------------
DROP TABLE IF EXISTS `t_colletion`;
CREATE TABLE `t_colletion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `areacode` varchar(20) DEFAULT NULL,
  `location` varchar(20) DEFAULT NULL,
  `lng` varchar(10) DEFAULT NULL,
  `lat` varchar(10) DEFAULT NULL,
  `hourAQI` varchar(10) DEFAULT NULL,
  `dayAQI` varchar(10) DEFAULT NULL,
  `s` varchar(5) DEFAULT NULL,
  `z1` varchar(5) DEFAULT NULL,
  `z2` varchar(5) DEFAULT NULL,
  `l` varchar(5) DEFAULT NULL,
  `j` varchar(5) DEFAULT NULL,
  `t` varchar(5) DEFAULT NULL,
  `score` varchar(10) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `returntime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_colletion
-- ----------------------------

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(10) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `envreportid` int(11) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------

-- ----------------------------
-- Table structure for t_ecologystate
-- ----------------------------
DROP TABLE IF EXISTS `t_ecologystate`;
CREATE TABLE `t_ecologystate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `areacode` varchar(20) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `lng` varchar(10) DEFAULT NULL,
  `lat` varchar(10) DEFAULT NULL,
  `greenrateindex` varchar(10) DEFAULT NULL,
  `volumeratioindex` varchar(10) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ecologystate
-- ----------------------------

-- ----------------------------
-- Table structure for t_envreport
-- ----------------------------
DROP TABLE IF EXISTS `t_envreport`;
CREATE TABLE `t_envreport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `areacode` varchar(20) DEFAULT NULL,
  `location` varchar(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `lng` varchar(10) DEFAULT NULL,
  `lat` varchar(10) DEFAULT NULL,
  `score` varchar(10) DEFAULT NULL,
  `colletionid` int(11) DEFAULT NULL,
  `level` char(4) DEFAULT NULL,
  `generatedtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_envreport
-- ----------------------------
INSERT INTO `t_envreport` VALUES ('1', null, null, null, null, null, null, null, '中级', null);
INSERT INTO `t_envreport` VALUES ('2', null, null, null, null, null, null, null, '高级', null);
INSERT INTO `t_envreport` VALUES ('3', null, null, null, null, null, null, null, '普通', null);
INSERT INTO `t_envreport` VALUES ('4', null, null, null, null, null, null, null, '高级', null);
INSERT INTO `t_envreport` VALUES ('5', null, null, null, null, null, null, null, '中级', null);

-- ----------------------------
-- Table structure for t_landed_property
-- ----------------------------
DROP TABLE IF EXISTS `t_landed_property`;
CREATE TABLE `t_landed_property` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(50) DEFAULT '' COMMENT 'uuid',
  `code` varchar(50) NOT NULL COMMENT '楼盘编码',
  `title` varchar(50) NOT NULL COMMENT '楼盘名称',
  `addressFull` text COMMENT '楼盘详细地址',
  `total` varchar(50) DEFAULT NULL COMMENT '户数',
  `city` varchar(20) DEFAULT NULL COMMENT '所属城市',
  `completion` varchar(50) DEFAULT NULL COMMENT '楼盘完成时间',
  `propertyType` varchar(50) DEFAULT NULL COMMENT '类型',
  `propertyCompany` varchar(100) DEFAULT NULL COMMENT '物业公司',
  `price` varchar(50) DEFAULT NULL COMMENT '价格',
  `volumeRate` varchar(50) DEFAULT NULL COMMENT '容积率',
  `propertyCosts` varchar(20) DEFAULT NULL COMMENT '物业费',
  `parking` varchar(50) DEFAULT NULL COMMENT '停车位',
  `greeningRate` varchar(50) DEFAULT NULL COMMENT '绿化率',
  `gfa` varchar(50) DEFAULT NULL COMMENT '楼盘面积',
  `metro` varchar(50) DEFAULT NULL COMMENT '周边地铁',
  `bus` text COMMENT '公交车',
  `lat` varchar(20) DEFAULT NULL COMMENT '维度',
  `lng` varchar(20) DEFAULT NULL COMMENT '经度',
  `images` text COMMENT '图片列表',
  `overview` text COMMENT '楼盘综述',
  `create_user` varchar(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(20) NOT NULL COMMENT '最后修改人',
  `update_time` datetime NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  UNIQUE KEY `title` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='地产楼盘列表';

-- ----------------------------
-- Records of t_landed_property
-- ----------------------------
INSERT INTO `t_landed_property` VALUES ('11', '25271b05bef54acbbe26083b2a658a3b', 'LP161003100031', '远洋山水', '长安街西延长线玉泉路路口西南侧;...', '13500户', '北京', '2005-06', '普通住宅', '北京远洋基业物业管理有限公司', '48982元/平米', '1.9元/平方米·月', '3.0', '地上150，地下350', '32%（绿化率高）', '1300000平方米（大型小区）', '1号线八宝山', '八宝山(地铁1号线/373路/76/337/特10/546路/373/运通120线/337路)<br />鲁谷公交场站(452路/79/473路/597路/389路/特10路/546路/特10/736路/472路)<br />鲁谷(308路/308/958路/597路/212/212夜班/546路/特10)', '39.9091529846191', '116.244064331055', '[]', '远洋山水[1]项目坐落在北京市环城绿化隔离带上，总占地面积204公顷。其中开发建设用地45.84公顷；大型绿化用地114公顷，分布在石景山路南北两侧，项目绿化率达到56%。项目拟建设成总建筑面积130万平方米以上，配套完善、环境优美、适宜居住的大规模高尚社区。工程计划分四期实施，5-6年完成。 远洋山水项目作为中远房地产开发有限公司在长安街沿线开发的又一大型住宅项目，定将以其卓越的品质、先进的理念成为北京地产界一颗熠熠闪耀的西线明珠。优越的地理位置　　远洋山水项目地处西四环与西五环之间，距西四环五棵松地铁站1.8公里，距西三环4.8公里，周边路网发达、交通便利。项目北侧是长安街西延长线，为城市主干道，一线地铁贯穿东西长安街；南侧为莲石路，属城市主干道；东、西两侧紧邻城市主干道--待拓宽的玉泉路和规划新建的鲁谷村西路。项目用地内还有现状城市次干道--鲁谷路。项目周边除一线地铁外，现分布着近17条公交线路。由此构成四通八达的交通网络，为将来项目入住居民的出行提供了快速、充分、便捷的条件。一流的居住环境　　远洋山水项目为北京市确定的绿化隔离带试点项目之一。项目北侧为长安街百米绿化带北京国际雕塑公园，项目东侧用地将建成占地约20公顷的绿化公园，项目西侧可邀看西山峰峦叠翠。周边还有石景山游乐园、松林公园等大型绿色公园。整个项目建成后，将成为绿地环抱、环境优美、生活舒适、交通便捷的大型生态社区，非常适宜人们居住生活。浓郁的人文气氛　　远洋山水项目周边分布着众多的科研单位、院校、国家机关等，文化氛围浓郁。其中有中国测绘科学研究院、市建筑工程研究院等研究院所7家；中国科技大学研究生院、国防大学等教育机构4家；解放军陆军总医院、武警总医院等医疗单位6家；政府部门3家；以及中国国际广播电台、空政歌舞团等单位。项目周边浓厚的文化氛围为小区居民创造了良好的人文环境。　　远洋山水分为南、北二区，整个小区预计于2010年前完工。', 'system', '2016-10-03 18:35:23', 'system', '2016-10-03 18:35:23');
INSERT INTO `t_landed_property` VALUES ('12', '08c523e162d74e28bc9cf99e484e7a55', 'LP161003100032', '沿海赛洛城', '百子湾路5号广渠东路33号', '4983户', '北京', '2010-10', '公寓、普通住宅、别墅', '美佳物业管理公司', '39682元/平米', '1.5元/平方米.月(不带电梯)、2.4元/平方米.月(带电梯)', '2.64', '3517', '30%（绿化率高）', '922223平方米（大型小区）', '', '百子湾家园西站(摆站495路)<br />水南庄东(摆站495路)<br />陈家林(运通121线/475路/312/468路/312区间/312路/628)', '39.9030952453613', '116.509201049805', '[]', '沿海赛洛城(丽江新城)位于朝阳区CBD区域东四环外，距CBD核心区域约1300米，位于广渠路与石门东路交汇十字路口东北角。沿海赛洛城具有相对良好的交通体系。四环路、百子湾路与改造后的广渠路构成了快捷的交通系统，可以方便地进出市中心或远郊区县；改造后的石门东路与规划中的建筑木材厂西路、东郊仓库一路、金海湾花苑中街共同形成社区周边的交通系统，可以从社区快速的进入城市主干路，很好地与城市融为一体。沿海赛洛城(丽江新城)的规划体现了大体量、配套完善、特色明晰的产品特质。其产品形态丰富，有住宅、酒店式公寓及商铺；配套有运动会所、医院、学校、幼儿园、地下车库。在低总价，高品质、差异化的市场策略前提下，住宅以板式南北高层、小高层为主，结合部分类板式高层，配备精装、主力户型为80-90平方米，成为添补这一区域需求空白的综合物业。以满足青年人不断提升的居住需求为核心，呈现出一个时尚、便利、充满活力、都市感强烈、休闲氛围浓厚的街区，一个鼓励交流与互动的高品位健康社区；86万平方米的超大社区，将居住区、园林、广场、学校、办公、购物中心等多项功能复合化，从而营造都市氛围，塑造先进的都市生活方式。园林及绿化近10万平方米，绿化率30％以上，车位数3517个，总户数为4983户，车户比约1：1.5。沿海赛洛城(丽江新城)定位是25-35岁的都市青年，该项目所有户型全部采用只有高档公寓才有的3米层高；所有产品提供精装修。沿海赛洛城(丽江新城)设计最大的特点就是开放式社区，将整个地块拆成23个组团，而将中间的可开发用地用做道路建设。设计师认为，如果将一大块土地规划设计得适宜城市人居住，就应该用规划城市的理念来规划，要将地块打散分割成若干街区，要添加棋盘形的道路和绿地，并在街两边安排餐厅、酒吧、咖啡屋、服饰店、便利店等商业设施。有了这些城市元素可让整个社区活起来。在做开放式社区的同时又注意在组团内部营造适宜居住的安静环境。户型设计合理化：沿海赛洛城的主力户型面积适中经济，户型格局紧凑务实。在有限的面积中尽量保证功能的齐全及合理的布局，且在局部又有丰富的变化及个性表现，非常符合现代生活的需要。沿海赛洛城(丽江新城)力图将产品做成南北通透的板楼。但与之矛盾的是中小户型不容易南北通透，所以这就要求设计师必须在不增大每户面积的前提下削薄板楼，创造一些“南厅北卧”或“南卧北厅”的户型。此外还尽量减小公摊损耗，将大部分户型的使用率控制在80%以上。赛洛城生活', 'system', '2016-10-03 18:35:23', 'system', '2016-10-03 18:35:23');
INSERT INTO `t_landed_property` VALUES ('13', '6d6fcaa9a14f4453b7e6d7337e239c2e', 'LP161003100033', '富力又一城', '鲁店北路', '5340户', '北京', '2006-05', '普通住宅', '北京恒富物业管理公司', '32450元/平米', '2.2元/平方米/月', '2.38', '4334个', '35%（绿化率高）', '1150000平方米（大型小区）', '', '豆各庄乡政府(411路)<br />郎各庄西(348路/457区间)<br />水牛房(457区间)', '39.8533838', '116.57101', '[]', '富力又一城占地面积约36公顷，总建筑面积115万多平方米，其中住宅面积为76万多平方米，配套商业2.6万平方米，综合商业及办公8.6万平方米。总体规划以低建筑密度、高绿化率为特色，绿化率达到35%。整个用地由城市规划道路分成四大块，最北侧规划为九年或十二年一贯制学校、公交总站、相关配套及绿化带，其他部分由东至西分别为A区、B区、C区，项目规划有两所幼儿园，B区规划有运动主题会所，C区西侧规划有综合商业和写字楼，其他各项配套设施一应俱全。周边环境：项目所处地势平坦开阔，远山近水，再加设计师师法自然，改造风水，形成“结庐在人径，而无车马喧”的居所环境。项目三面环水，南侧萧太后河是古永定河故道，河面开阔，夕阳下，长河落日，对岸鸡鸣犬吠，一派千年流淌的沧桑。萧太后河与项目之间为北京罕见的森林滨河公园，绵延千米的水滨走廊，30年原生树木环抱的森林慢跑径，多功能运动主题公园。园林设计：社区内园林由国际知名园林设计事务所ACLA担纲，北京首个真正东南亚主题风情园林，北半球温热的阳光洒在白色的滨水沙滩，高大的棕榈树、椰子林，满目青翠，绿叶婆娑，所有的植物在阳光下肆虐的生长，苍翠林荫间散落着茅草顶的凉亭，坐在凉亭下，满饮一杯浓浓的椰汁或与家人美美的共进一顿泰餐。恍兮惚兮，仿佛置身芭堤雅。完备配套：百万平米超大社区，完备的综合配套让您真正可以远离人流嘈杂的都市而安静的生活，幼稚园到中学的一条龙式教育配套，2000平米社区卫生站，30万平米超大商业面积，三条公交线路，两条小区班车线路，1万平米运动主题会所。一切都只为城中的您齐备。建筑设计：项目整体建筑设计，白色外墙立面，褐色坡屋尖顶，色调明快鲜亮，褐色屋顶既凸显了东南亚建筑风格同时恰到好处的与白色的明快风格形成呼应。在北方开创性运用东南亚主题进行建筑选型、立面包装，告别城市建筑复制时代，是建筑中具有先锋意义的作品。富力又一城3期“蝴蝶湄”，凝聚了诗意的美、绚丽的美、斑斓的美、风情的美、水逸的美，令人沉醉的唯美生活，富力又一城3期惊艳揭幕。东南亚风情的建筑、园林规划让精睿生活体现出风情万种，姿态万千。进入项目主入口，具有强烈视觉冲击力的东南亚建筑群直您的眼帘，婀娜多姿的植物群落，形态丰富，趣味盎然。透过高低错落的植物组群，星星点点的映射出中心湖区的粼粼波光，享受惬意的同时不禁有了曲径探幽的冲动，栈道蜿蜒，渐闻水声潺潺， 4500平米的中心湖面，香花，碧草，叠石，无不透露出东南亚园林的自', 'system', '2016-10-03 18:35:23', 'system', '2016-10-03 18:35:23');
INSERT INTO `t_landed_property` VALUES ('14', 'c5aa62c321974107bec8f1dc4b98b725', 'LP161003100034', '空港家园', '固安孔雀大道北侧', '3450户', '北京', '2017-05', '普通住宅', '华夏幸福基业物业服务有限公司', '8104元/平米', '2.5元/平/月', '2.0', '免', '50%（绿化率高）', '344000平方米（大型小区）', '', '顺义十中(顺38路/855路)<br />后沙峪(顺27路/地铁15号线/顺26路)<br />地铁后沙峪站(顺28路/923路/顺38路/867路/顺26路/顺31区间/顺28区间[汉石桥]/郊81路[密云-大兴]/空港3路/916西线/855路/顺28区间[道口])', '40.115259432392', '116.55635788883', '[]', '地铁4号线沿线孔雀城空港家园、剑桥郡、英国宫40分钟到西单金融街。紧邻首都第二机场10公里，升值潜力高 特大喜讯1，首都第二机场区带动产业经济发展，北京大七环2015年全线贯通， 秒杀理由2，不限购，可以贷款，。北京人，外地人都可以买不限购，70年大产权！ 秒杀理由3，价格优惠，3万抵6万，均价6000元每平米，全款99折。 秒杀理由4，交通方便，社区班车到地铁大兴天宫院16分钟，46分钟到西单金融街，交通非常方便！ 秒杀理由5，70年大产权，开发商（京御）非常有实力一级资质，北京周边打造8个孔雀城，1个大卫城，2个英国宫，诚心度高，您可以放心购买，安全又保障！ 秒杀理由6，配套齐全，绿化面积大，汇佳国际教育?幸福军区医院?北京八中学校.社区班车无逢对接4号线天宫院！ 户型‘两居室户型：61平米-103平米，赠送5-15平米 三居室户型：91-103平米，赠送10-15平 四居室户型；117平-135平米 赠送10-15平 优惠政策 优惠活动:3万抵8折 .全款99折 我是永定河孔雀城剑桥郡 空港家园 英国宫一手事业部网络营销部置业顾问金波，房源有限如有需求请速来电咨询，更多优惠 2.本小区还有更多优质房源，您可以点击我的头像进入我的个人店铺，随意挑选 3，24小时财富热线金波13722631172本人还有更多优质房源！欢迎您的来电！ 4，来之前请提前打电话有专车接送！', 'system', '2016-10-03 18:35:23', 'system', '2016-10-03 18:35:23');
INSERT INTO `t_landed_property` VALUES ('15', 'd7a75ed6639546e687ea8a8602693bac', 'LP161003100035', '福城五期', '燕郊行宫东大街', '23000户', '北京', '2009-01', '其它、普通住宅', '银茂泰德物业管理有限公司', '12516元/平米', '1.1', '3.42', '车位充足', '30%（绿化率高）', '180000平方米（大型小区）', '', '燕郊上上城五期(814路)<br />燕郊上上城二期(814路)<br />天洋城小区南(818路)', '39.963244206483', '116.84515855778', '[]', '福成五期是成熟社区，环境优雅，美观大方，生态良好，邻里和睦，祥和文明，康乐和谐。物业管理完善，贴心人性化，居住人群文化素质层次高，稳定，地理环境优越，交通便利，周边配套设施完善，各种档次的饭店、酒楼、娱乐城、文化活动馆等休闲场所应有尽有，满足生活所需。', 'system', '2016-10-03 18:35:23', 'system', '2016-10-03 18:35:23');
INSERT INTO `t_landed_property` VALUES ('16', '3fc5061b72574f9db0a5da4da47a4cb1', 'LP161003100036', '金隅万科城', '京藏高速30出口往东800米，昌平城...', '1548户', '北京', '2013-05', '普通住宅', '万科物业', '28861元/平米', '2.4', '2.7', '960个 (1:0.5)', '30%（绿化率高）', '615418平方米（大型小区）', '', '昌平中心公园东门(493路)<br />昌平二中(郊94路[怀柔-昌平]/昌31支线)<br />昌平区中医院(493路)', '40.2183312', '116.237779', '[]', '金隅万科城是由北京万科、金隅嘉业两大业内龙头品牌，联合打造的60万平米京北首席人居大盘。历时四年深耕，目前已成为万人入主的大型成熟社区。 \r\n    本项目地处上风上水的昌平城区的核心地段，路网如织，通达四方，社区门前有885、345快等多路公交40分钟即可直达北二环德胜门；地铁昌平线一期已正式通车，连通13号线、8号线，可迅达地铁西二旗站，未来更有望南延至北二环积水潭站。 \r\n    金隅万科城项目社区内独享的15万平米都心型商业中——金隅·万科广场，聚合最成熟的商业氛围，全面荟萃时尚百货、生活超市、娱乐餐饮、品牌专卖及生活服务于一体，营造都市人群的“第二客厅”。首都电影院领衔入住，满足全龄客层的一站式生活中心，全面引领昌平区乃至京北广大地区的体验型消费纪元，让您足不出户零距离畅享繁华便利。 \r\n    金隅万科城三期住宅建筑立面采用ARTDECO风格，突出建筑的尊贵典雅。造型处理上着重体现建筑的雕塑感，用框式构架强调建筑的立面单元。建筑物的高耸、挺拔的竖向线条，削弱建筑的冗长感，给人以拔地而起、傲然屹立的非凡气势。建筑自下而上采用三段式，近入尺度底部采用浅米色仿石涂料，建筑主体选用红褐色仿面砖涂料，局部点缀深灰色仿面砖涂料。顶部处理在空间上形成退台的效果，丰富了建筑的天际线。 \r\n    金隅万科城三期收官之作将继续演绎万科“全家居解决方案”，将生活烦恼降至最低。8年，20多个城市，9万家庭的实践，成就万科完美精装体系。三期主力户型为80-90平米两居、105平米三居，以人性化的理念，完美升级空间的利用度、使用功能以及收纳功能，为客户打造性能更多元、居住更自如的悦然空间。', 'system', '2016-10-03 18:35:23', 'system', '2016-10-03 18:35:23');
INSERT INTO `t_landed_property` VALUES ('17', '659092d84b6d49b89ef1a8174b6e16ea', 'LP161003100037', '中弘北京像素', '朝阳北路与草房西路交汇处', '4706户', '北京', '2011-07', '暂无数据', '北京中弘文昌物业管理有限公司', '35738元/平米', '2.98元/平米/月', '2.38', '5000', '40%（绿化率高）', '700000平方米（大型小区）', '6号线草房', '五里桥(639/306)<br />五里桥路口北(306)<br />地铁草房站(991路/675路/342路)', '39.9346839', '116.623965', '[]', '中弘•北京像素 ——朝阳北路 6号线上 创意空间汇聚商务精英 \r\n有人说我的样子很“风车”，有人说我的造型很“魔方”。那只是他们的认为，这并不重要，我就是我——中弘•北京像素，位于CBD东，朝阳北路，出了地铁6号线就是。但最最重要的是，这里有北京独特的创意空间，有大有小。\r\n中弘北京像素区域： \r\nCBD东扩 朝阳北路东中心 传媒文化创意走廊 \r\n中弘北京像素作为CBD东扩后的重要区域，又位于朝阳北路沿线，更是“两轴两带多中心”北京城市新规划中重点建设的轴带交汇点， 随着CCTV、BTV东迁，这里已然成为来自一线城市文化创意产业的抢滩热土。 \r\n目前区域内已集聚上千家文化创意企业。依托中国传媒大学和北京第二外国语学院的技术研发与人才培养优势，加之全球著名传媒企业大多集中于CBD，催生这里成为与CBD形成良好互补性的产业链条，成为新兴的文化创意传媒走廊，更将为区域的发展带来不可估量的发展潜势。 \r\n当然还有眼前最近距离可以看到的利好，随着地铁六号线的贯通，将直抵CBD的腹心，依托开发力度的提速，这里必将成为CBD先锋精英们首选的聚集区。 \r\n中弘北京像素交通： \r\n今天，想用什么方式出行全凭你的心情 \r\n地铁、高速、公交……挑一种就好。 \r\n一路直通到脚底的六号线，不远处还有八通线； \r\n要享受独自开车的方便也可以，距朝阳路1.5公里，距京通快速路2公里，还有一条直线通到CBD的朝阳北路； \r\n对了，别忘了更有只需15分钟车程就可抵达机场的机场第二高速； \r\n当然，如果你根本不考虑时间，那就惬意的步行到公交车站吧…… \r\n中弘北京像素规划、建筑、园林、园区配套： \r\n基因决定无法从众 \r\n在这个事事都标榜个性的时代，其实只想做真实的自己。 \r\n遵循地块轴线的本真原貌，21栋建筑以宛如风车的包围旋转式形状排列分布，从而确保使用者的隐私安全以及充足的日照。同时建筑结合园林，整体布局上呈现出“风车旋转”的独特景观。 \r\n中弘北京像素仿生建筑 凹凸魔方 \r\n每栋单体建筑风格灵感则来源于自然界中的蜂巢形象，建筑长向立面众多凹凸墙面构成，力求通过模拟自然界常见并熟悉的生物形态创造亲近自然的建筑体。 \r\n中弘北京像素建筑立面色彩层次变化与建筑形体变化相得益彰，通过建筑的转折、凹凸、体块的加减穿插，有效的解决了建筑立面的呆板。立面风格又恰似魔方，更增添整个项目独有的时尚、动感气质。 \r\n中弘北京像素在设计时为避免高层建筑物由于间距', 'system', '2016-10-03 18:35:23', 'system', '2016-10-03 18:35:23');
INSERT INTO `t_landed_property` VALUES ('18', 'd69362618a44411494dcf991dd0eed19', 'LP161003100038', '潮白家园', '国贸正东朝通州潮白河友谊大桥东...', '2580户', '北京', '2016-09', '普通住宅', '暂时不详', '11365元/平米', '2.3元每平米', '2.6', '地上车位和地下498车位', '30%（绿化率高）', '24万平方米（小型小区）', '', '', '39.863858355893', '116.85614456132', '[]', '本项目西至西燕路（在建）、东临待开发住宅用地，南至滨河大道（在建）、北靠定福路（规划），地理位置优越，交通便捷。项目总占地面积8万平米，总建筑面积24万平米，容积率2.6，绿地率30%，共设计建造16栋住宅楼，共2580套住宅。整个地块被西燕路分成东西两区，其中西区1栋、东区15栋，3#楼2单元为廉租房。其中1#-7#楼、9#-12#楼、14#-16#楼为28层；8#楼为25层，13#楼为26层；1#、3#、4#、5#、15#楼为2个单元，其余剩余楼栋均为1个单元。商业位于东区社区出入口两侧，面积约1600平米。\r\n本项目采取的典雅建筑风格，该风格是新古典主义建筑风格的繁杂雕饰经过简化，并与现代的材质相结合，呈现出古典而简约的一种新风貌建筑风格——典雅风格建筑。它将怀古的浪漫情怀融入现代人对生活的需求，兼容了华贵典雅与现代时尚，反映出后工业时代个性化的美学观念和文化品位。利用新古典主义建筑语法去构建和户型平面高度吻合的立面形态，赋予建筑以灵魂和生命，精细、严谨而有变化，丰富而又流畅生动。\r\n建筑立面通过底部浅褐色真石漆、中间米黄色涂料，顶部深咖啡色仿石涂料合理的色彩比例划分，使立面具有较强的美观性和识别性。\r\n项目总体规划采用点线结合的手法，整体以一条南北向主轴贯穿其中，周边放置点式景观集散区域，起到围合小区的作用。中央处布置了6个点式景观点，结合南侧主入口广场，形成了整个小区建筑与景观的主要轴线。此外点与线相符交错布置，使中央区域的景观得到了很好的渗透，更增跳了围合空间的趣味性。同时通过轴线的运用营造区域的重要性及礼仪性，加深空间的纵深感。\r\n小区内共设置三个社区出入口，均位于西燕路两侧，其中东区两个出入口，西区一个出入口，东区社区主入口位于4#楼与8#楼之间、次入口位于13#楼和15#楼之间。东区设置地下车库，共498个地下停车位，地下车库出入口位于7#楼南侧，保证车辆能以最短距离驶入地下车库。下穿路与地下车库出入口相结合的创新设计，解决了较长的车行动线，保证了景观完整性，使步行系统与中央景观有机结合在一起，这也是本项目亮点之一。\r\n整体住宅采用矩阵式排列，南北向最小楼间距约为40米（6#楼与10#楼之间、11#与14#楼之间、12#楼与13#楼之间），最大楼间距约为95米（4#楼与8#楼之间），侧向最小间距约23米（2#楼与5#楼之间），保证每一栋楼都可以获得最大限度的日照，并保持良好的通风。', 'system', '2016-10-03 18:35:23', 'system', '2016-10-03 18:35:23');
INSERT INTO `t_landed_property` VALUES ('19', 'f357cbcf2c45436cb125099a3f216dad', 'LP161003100039', '北京新天地', '朝阳路5号院和7号院', '5000户', '北京', '2008-12', '普通住宅', '天诚广厦物业管理有限公司', '34946元/平米', '1.98元/平方米·月', '3.11', '3250个 (0.56:1)', '30%（绿化率高）', '610000平方米（大型小区）', '八通线管庄', '东十里堡村(675路/342路/991路)<br />八里桥北站(快速公交2线支线/648路/快速公交2线支区间)<br />西军庄(快速公交2线支区间/648路/快速公交2线支线)', '39.9190939', '116.60941', '[]', '“北京新天地”项目，坐落于北京市朝阳区管庄杨闸环岛东北角，紧临朝阳路主路。产品以实用、紧凑、舒适型户型为主；一居室和两居室占到了80％以上。楼体为短板式产品，采用时尚墙砖及宽采光玻璃窗打造时尚外立面。社区内有双会所、立体复合园林及水系、主题商业街。紧凑型设计，面积控制一居室在59-78平方米之间，两居室在88-106平米之间，三居室在120-150平米左右，四居室在170平米左右，力争户户朝阳、动静分离、明厨明卫。追求人性化设计，无论户型大小，均设计了玄关功能，保证了私密性和功能的完备性，特别针对一居室有更合理的设计要求，即要有独立的起居室和卧室，同时还要充分保证居住者的舒适程度。品质、现代的外立面整体风格，飘窗、落地阳台设计，保证室内足够采光，新城市主义的设计风格体现了项目超高的性价比。朝阳路公交快速道、地铁八通线、地铁六号线，京通快速路直达；政府参与的朝阳路改造工程已经启动，做为本市的第二条大容量快速公交线路朝阳路工程，西起东大桥，东至杨闸环岛，全长约15公里。这条线路的车辆将采用新型大容量无轨电车，并建设封闭的专用车道。以实用、紧凑、舒适型户型为主；一居室和两居室占到了80%以上。楼体为短板式产品，采用时尚墙砖及宽采光玻璃窗打造时尚外立面。　　社区内有双会所、立体复合园林及水系、主题商业街。　　紧凑型设计，面积控制一居室在60-65平方米之间，两居室在90-100平米之间，三居室在135平米左右，力争户户朝阳、动静分离、明厨明卫。追求人性化设计，无论户型大小，70%的房间均设计了玄关功能，保证了私密性和功能的完备性，特别针对一居室有更合理的设计要求，即要有独立的起居室和卧室，同时还要充分保证居住者的舒适程度。　　外立面风格；品质、现代的外立面整体风格，飘窗、落地阳台设计，保证室内足够采光，新城市主义的设计风格体现了项目超高的性价比。　朝阳北路地铁6号线常营站，从四期门口步行仅13分钟即到地铁　朝阳路公交快速道、城铁八通线、京通快速路直达；　　紧邻城铁八号线管庄站，朝阳路沿线公交342、382、731、930、312、728和快速公交2（支线）路直达，加上京通快速路构成了“北京新天地”项目现有的立体交通路网。政府参与的朝阳路改造工程已经启动，作为本市的第二条大容量快速公交线路朝阳路工程，西起东大桥，东至杨闸环岛，全长约15公里。这条线路的车辆将采用新型大容量无轨电车，并建设封闭的专用车道。60万平米大型社区，', 'system', '2016-10-03 18:35:23', 'system', '2016-10-03 18:35:23');
INSERT INTO `t_landed_property` VALUES ('20', 'fef9e687a52b4a32abc467f0306ff1d0', 'LP161003100040', '星河皓月', '北京市正东燕郊经济技术开发区潮...', '4000户', '北京', '2008-11', '公寓、普通住宅', '燕达物业管理有限公司', '15558元/平米', '1.46元/平方米·月', '1.17', '3845', '45%（绿化率高）', '920000平方米（大型小区）', '', '老检查站(顺18路)<br />北务检查站(顺18路)<br />北务镇马庄(顺18路)', '39.932498262511', '116.70656803217', '[]', '配套设施：学校：三河第六小、第七小学；中学有三河第八中学、第十中学、第六中学、中央美术学院附属中学、三河市二中，华北科技学院、交通干部管理学院、民政干部管理学院、中国防卫科技学院、中国防灾技术高等专科等。\r\n医院：由住达地产上属公司燕达集团独资建设的燕达医疗国际健康城，人民医院、京东中美医院、朝阳医院燕郊分院（二三医院）冶金医院等。\r\n商业：京客隆超市燕顺路店 、天客隆超市、新世纪商城、行宫商业街、燕郊商业步行街、金山电器城、燕隆家具城等。\r\n餐饮：汇福大酒店、燕龙绿色生态园、运河苑度假村、鼎香园饺子城、万家福海鲜城、加州牛肉面、肯德基等。\r\n银行：中行、工行、农行、建行，在社区对面有农村信用社，邮局\r\n康体娱乐：华堂高尔夫球场、诺富特燕苑国际度假村、京华高尔夫球场（国际标准18洞）新月国际会议中心、行宫宾馆、燕龙马术场、马来西亚大成功广场等。\r\n星河皓月位于北京市正东燕郊经济技术开发区潮白河畔，毗邻华堂高尔夫球场和诺夫特燕苑国际度假村，濒临燕顺路，紧邻即将要开通的朝阳北路。交通方便，延京通快速、京哈高速一路畅通无阻，自驾车仅需30分钟：930公交车直达社区门口，从国贸（郎家园）乘车只需40分钟即可到达，发车频率为5分钟一班。距北京首都国际机场仅25公里。星河皓月占地613亩；总建筑面积约百万平米，共分三期开发：一期为40栋4—6层的欧式花园洋房，二期为41栋4—11层的花园洋房、亚别墅板式小高层。三期为28栋商业和住宅楼。目前正在热销的为三期，三期产品结构依地势而起的板式小高层成阶梯状向北延伸，11层至30层错落设计，让每一户业主包揽小区全貌，领略潮白河独有风光。社区配套设施完整，120亩体育休闲公园，6000平米商业文化广场，5000玉米天鹅湖，3500平米星河皓月双语幼儿园，800平米商务娱乐中心，占地50亩的930公交总站，并区域内首家引进大型京客隆超市，使业主真正的感受到中产阶级的江前林后生活。', 'system', '2016-10-03 18:35:23', 'system', '2016-10-03 18:35:23');

-- ----------------------------
-- Table structure for t_majorpollusources
-- ----------------------------
DROP TABLE IF EXISTS `t_majorpollusources`;
CREATE TABLE `t_majorpollusources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `areacode` char(20) DEFAULT NULL,
  `location` char(20) DEFAULT NULL,
  `lng` char(20) DEFAULT NULL,
  `lat` char(20) DEFAULT NULL,
  `distance` char(20) DEFAULT NULL,
  `updatetime` char(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_majorpollusources
-- ----------------------------

-- ----------------------------
-- Table structure for t_map
-- ----------------------------
DROP TABLE IF EXISTS `t_map`;
CREATE TABLE `t_map` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `areacode` int(11) DEFAULT NULL,
  `lng` varchar(20) DEFAULT NULL,
  `lat` varchar(20) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ID` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_map
-- ----------------------------

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `messageid` int(11) NOT NULL AUTO_INCREMENT,
  `content` longtext,
  `userid` int(11) DEFAULT NULL,
  `status` char(4) DEFAULT NULL,
  `sendtime` datetime DEFAULT NULL,
  PRIMARY KEY (`messageid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_message
-- ----------------------------

-- ----------------------------
-- Table structure for t_noiseenviroment
-- ----------------------------
DROP TABLE IF EXISTS `t_noiseenviroment`;
CREATE TABLE `t_noiseenviroment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `areacode` varchar(20) DEFAULT NULL,
  `location` varchar(20) DEFAULT NULL,
  `lng` varchar(10) DEFAULT NULL,
  `lat` varchar(10) DEFAULT NULL,
  `dayvalue` varchar(10) DEFAULT NULL,
  `nightvalue` varchar(10) DEFAULT NULL,
  `updatetime` char(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_noiseenviroment
-- ----------------------------

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(10) DEFAULT NULL,
  `orderno` varchar(20) DEFAULT NULL,
  `envreportid` int(11) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `valid` varchar(8) DEFAULT NULL,
  `status` char(4) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', '2', '1', '1', '300.00', null, '1', '2016-08-10 00:45:41');
INSERT INTO `t_order` VALUES ('2', '3', '2', '2', '200.00', null, '2', '2016-07-13 00:46:26');
INSERT INTO `t_order` VALUES ('3', '3', '3', '3', '555.00', null, '3', '2016-08-02 00:46:21');
INSERT INTO `t_order` VALUES ('4', '4', '4', '4', '222.00', null, '4', '2016-06-07 00:46:45');
INSERT INTO `t_order` VALUES ('5', '5', '5', '5', '555.00', null, '5', '2016-06-04 15:08:02');

-- ----------------------------
-- Table structure for t_payrecord
-- ----------------------------
DROP TABLE IF EXISTS `t_payrecord`;
CREATE TABLE `t_payrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(10) DEFAULT NULL,
  `paymenttype` char(4) DEFAULT NULL,
  `money` double(10,2) DEFAULT NULL,
  `paymentaccount` varchar(30) DEFAULT NULL,
  `envreportid` int(11) DEFAULT NULL,
  `paymenttime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_payrecord
-- ----------------------------
INSERT INTO `t_payrecord` VALUES ('1', '1', null, '4.00', null, null, null);
INSERT INTO `t_payrecord` VALUES ('2', '2', null, '5.00', null, null, null);

-- ----------------------------
-- Table structure for t_radiation
-- ----------------------------
DROP TABLE IF EXISTS `t_radiation`;
CREATE TABLE `t_radiation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `areacode` varchar(20) DEFAULT NULL,
  `location` varchar(20) DEFAULT NULL,
  `lng` varchar(10) DEFAULT NULL,
  `lat` varchar(10) DEFAULT NULL,
  `distance` varchar(10) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_radiation
-- ----------------------------

-- ----------------------------
-- Table structure for t_report
-- ----------------------------
DROP TABLE IF EXISTS `t_report`;
CREATE TABLE `t_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(50) DEFAULT NULL COMMENT 'uuid',
  `code` varchar(50) DEFAULT NULL COMMENT '报告编码',
  `name` varchar(50) DEFAULT NULL COMMENT '楼盘名称',
  `address` varchar(200) DEFAULT NULL COMMENT '楼盘地址',
  `pic` varchar(200) DEFAULT NULL COMMENT '图标',
  `image` varchar(200) DEFAULT NULL COMMENT '大图',
  `levels` varchar(100) DEFAULT NULL COMMENT '报告等级',
  `price` decimal(18,2) DEFAULT '0.00' COMMENT '价格',
  `create_user` varchar(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(20) NOT NULL COMMENT '最后修改人',
  `update_time` datetime NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报告列表';

-- ----------------------------
-- Records of t_report
-- ----------------------------

-- ----------------------------
-- Table structure for t_report_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_report_comment`;
CREATE TABLE `t_report_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(50) DEFAULT NULL COMMENT 'uuid',
  `user_code` varchar(50) NOT NULL COMMENT '用户编号',
  `report_code` varchar(50) NOT NULL COMMENT '报告编号',
  `content` text NOT NULL COMMENT '评论内容',
  `rate` int(11) DEFAULT '0' COMMENT '好评等级',
  `create_user` varchar(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(20) NOT NULL COMMENT '最后修改人',
  `update_time` datetime NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报告评论表';

-- ----------------------------
-- Records of t_report_comment
-- ----------------------------

-- ----------------------------
-- Table structure for t_report_environment_level
-- ----------------------------
DROP TABLE IF EXISTS `t_report_environment_level`;
CREATE TABLE `t_report_environment_level` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(50) DEFAULT '' COMMENT 'uuid',
  `code` varchar(50) NOT NULL COMMENT '环境报告环境等级编码',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `level` int(11) DEFAULT '0' COMMENT '环境等级',
  `content` text COMMENT '等级描述内容',
  `create_user` varchar(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(20) NOT NULL COMMENT '最后修改人',
  `update_time` datetime NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='环境报告环境等级描述';

-- ----------------------------
-- Records of t_report_environment_level
-- ----------------------------
INSERT INTO `t_report_environment_level` VALUES ('15', 'c316fffbc3dc4d59854731c4d5165f17', 'TL161003100022', 'noise', '1', '目前您所处的地理区域噪音标准为：0-1类标准（昼间50-55分贝，夜间40-45分贝）：优，属于适宜居住噪声范围，适用于疗养区、高级别墅区、高级宾馆、居住、文教机关等区域；按照普通人的听力水平，50分贝相当于正常交谈的声音，30-40分贝是比较安静的正常环境。此类区域特别适合要求环境安静的人群。 ', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('16', '992e560c08634935b5f0719d790aae56', 'TL161003100023', 'noise', '2', '目前您所处的地理区域噪音标准为：2类（昼间60分贝，夜间50分贝）：良，可居住噪声范围，适用于居住、商业和工业混杂区。按照普通人的听力水平，40-60分贝属于正常交谈的声音范围，60分贝以上就属于吵闹范围了，此类区域标准在60分贝以下，适合对环境噪声不太敏感的一般人群。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('17', '42c00955f7fc4fc38df7efb5c5dcea81', 'TL161003100024', 'noise', '3', '目前您所处的地理区域噪音标准为： 3-4类（昼间65-70分贝，夜间55分贝）：差，不适宜居住噪声范围，40-60分贝属于正常交谈的声音范围，60分贝以上就属于吵闹范围了，长期居住在此噪声范围内会干扰休息和睡眠，降低工作学习效率，会引起耳鸣、耳痛、听力损伤等，造成神经衰弱，引发心血管疾病，甚至引起神经系统紊乱、精神障碍、内分泌紊乱等。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('18', '53132d529e72444a83aae54553425595', 'TL161003100025', 'water', '1', '目前您所处的地理范围区域为：I II 类：优，源头水、国家自然保护区，地表水源地一级保护区、珍惜水生生物栖息地、鱼虾类产卵场等；此区域水质：I II类水质化学需氧量COD不超过15mg/L；汞不超过0.00005mg/L，粪大肠菌群I类不超过200个/L，II类不超过2000个/L。水体自净能力强，水体中有机化合物含量低，重金属含量低，微生物含量低，属于非常优秀的水质。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('19', 'd37b6f6d8baa46879bf584c71751a879', 'TL161003100026', 'water', '2', '目前您所处的地理范围区域为：III类：良，地表水源地二级保护区、游泳区水质；；此区域水质：化学需氧量COD不超过20mg/L；汞不超过0.0001mg/L，粪大肠菌群不超过10000个/L。水体自净能力较好，水体中有机化合物含量较低，重金属含量较低，微生物含量较低，属于可饮用水源水质。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('20', '68c4033f6ef54d11bb2c0946e795fd02', 'TL161003100027', 'water', '3', '目前您所处的地理范围区域为：IV、V类： 差，非直接接触水质。此区域水质：化学需氧量COD，IV类不超过30mg/L，V类不超过40 mg/L；汞不超过0.001mg/L，IV类水中粪大肠菌群不超过20000个/L，V类水中不超过40000个/L。水体自净能力一般，水体中有机化合物含量较高，重金属含量较高，微生物含量较高，属于不可直接饮用的水质。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('21', 'fc502927932b49f3a98c2464dbddcd9d', 'TL161003100028', 'air', '1', '目前您所处的地理区域AQI为：AQI指数0-50 1级，优：，参加户外活动呼吸清新空气，犹如置身天然氧吧，每一次呼吸都像被洗涤了一次一样，尽情享受户外美好时光。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('22', 'd33e47fc808e48408523bb6d9ef8e8a7', 'TL161003100029', 'air', '2', '目前您所处的地理区域AQI为：AQI指数50-100，2级，良：可以正常进行室外活动，空气质量尚可，但某些污染物可能对极少数异常敏感人群健康有较弱影响，建议极少数异常敏感人群应减少户外活动。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('23', '973a478298e74af6b6d070b56f8a2abe', 'TL161003100030', 'air', '3', '目前您所处的地理区域AQI为：AQI指数101-150，3级，轻度污染：敏感人群减少体力消耗大的户外活动，建议儿童、老年人及心脏病、呼吸系统疾病患者应减少长时间、高强度的户外锻炼。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('24', 'a4d8e243fd204535aa50073f0fd6ceaf', 'TL161003100031', 'air', '4', '目前您所处的地理区域AQI为：AQI指数151-200，4级，中度污染：对敏感人群影响较大，长期接触，易感人群病状有轻度加剧，健康人群出现刺激症状。可能对健康人群心脏、呼吸系统有影响，建议疾病患者避免长时间、高强度的户外锻练，一般人群适量减少户外运动。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('25', '5c25184dc85646379bba07fd99a7a14e', 'TL161003100032', 'air', '5', '目前您所处的地理区域AQI为：AQI指数201-300，5级，重度污染：心脏病和肺病患者症状显著加剧，运动耐受力降低，健康人群普遍出现症状，建议儿童、老年人和心脏病、肺病患者应停留在室内，停止户外运动，一般人群减少户外运动。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('26', '94426089e4db4990bc69eb68b5c7c91a', 'TL161003100033', 'air', '6', '目前您所处的地理区域AQI为：AQI指数 300以上，6级，严重污染：健康人群运动耐受力降低，有明显强烈症状，提前出现某些疾病，建议儿童、老年人和病人应当留在室内，避免体力消耗，一般人群应避免户外活动。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('27', '1514c4e8c24f4649ac696f8d6af68246', 'TL161003100034', 'soil', '1', '目前您所在的地理区域土壤级别为：I类一级，优: 国家规定的自然保护区（原有背景重金属含量高的除外）、集中式生活饮用水源地、茶园、牧场和其他保护地区的土壤，土壤质量基本上保持自然背景水平 ；土壤中汞含量不超过0.15mg/kg，砷不超过15mg/kg，铅不超过35mg/kg，六六六(六氯环己烷)不超过0.05mg/kg，滴滴涕不超过0.05mg/kg。其中汞、砷、铅属于重金属，六六六和滴滴涕属于杀虫剂，现在已被禁用。滴滴涕为白色晶体，不溶于水，溶于煤油，可制成乳剂，是有效的杀虫剂。为20世纪上半叶防止农业病虫害，减轻疟疾伤寒等蚊蝇传播的疾病危害起到了不小的作用。但由于其对环境污染过于严重，目前很多国家和地区已经禁止使用。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('28', 'b729fa9438304cf692a7e4df16957608', 'TL161003100035', 'soil', '2', '目前您所在的地理区域土壤级别为：II类二级,良: 适用于一般农田、蔬菜地、茶园果园、牧场等到土壤，土壤质量基本上对植物和环境不造成危害和污染；土壤中汞含量当pH值小于6.5时不超过0.3mg/kg，6.5-7.5时不超过0.5 mg/kg，大于7.5时不超过1.0 mg/kg；砷当pH值小于6.5时不超过40mg/kg，6.5-7.5时不超过30 mg/kg，大于7.5时不超过25 mg/kg，铅当pH值小于6.5时不超过250mg/kg，6.5-7.5时不超过300 mg/kg，大于7.5时不超过350 mg/kg。六六六(六氯环己烷)不超过0.5mg/kg，滴滴涕不超过0.5mg/kg。其中汞、砷、铅属于重金属，六六六和滴滴涕属于杀虫剂，现在已被禁用。滴滴涕为白色晶体，不溶于水，溶于煤油，可制成乳剂，是有效的杀虫剂。为20世纪上半叶防止农业病虫害，减轻疟疾伤寒等蚊蝇传播的疾病危害起到了不小的作用。但由于其对环境污染过于严重，目前很多国家和地区已经禁止使用。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('29', '416e044f34d143bba4a8f62df3a5da69', 'TL161003100036', 'soil', '3', '目前您所在的地理区域土壤级别为：III类三级,差: 适用于林地土壤及污染物容量较大的高背景值土壤和矿产附近等地的农田土壤（蔬菜地除外）。土壤质量基本上对植物和环境不造成危害和污染。土壤中汞含量不超过1.5mg/kg，砷不超过40mg/kg，铅不超过500mg/kg，六六六(六氯环己烷)不超过1.0mg/kg，滴滴涕不超过1.0mg/kg。其中汞、砷、铅属于重金属，六六六和滴滴涕属于杀虫剂，现在已被禁用。滴滴涕为白色晶体，不溶于水，溶于煤油，可制成乳剂，是有效的杀虫剂。为20世纪上半叶防止农业病虫害，减轻疟疾伤寒等蚊蝇传播的疾病危害起到了不小的作用。但由于其对环境污染过于严重，目前很多国家和地区已经禁止使用。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('30', '7ff99f0691f24e9ebe9be718a021e794', 'TL161003100037', 'rubbish', '1', '目前您所在的地理区域距离垃圾处理设施距离为：垃圾转运站或填埋场大于1公里，垃圾焚烧厂大于3公里，优：垃圾处理设施距离较远，对您的生活不会造成影响，不存在垃圾臭气、垃圾渗沥液、垃圾焚烧产生的二噁英等污染物。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('31', 'd15e534663124f079151c4694a265c4a', 'TL161003100038', 'rubbish', '2', '目前您所在的地理区域距离垃圾处理设施距离为：垃圾转运站或填埋场大于300米小于1000米，垃圾焚烧厂大于1000米小于3000米，良：潜在风险较小。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('32', '0b83953046cf4b9a8ac1ffa65e56651a', 'TL161003100039', 'rubbish', '3', '目前您所在的地理区域距离垃圾处理设施距离为：垃圾转运站、填埋场小于300米或垃圾焚烧厂小于1000米，差，潜在风险较高。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('33', '10d5aca171eb43ab8a238d415f1e6c42', 'TL161003100040', 'afforest', '1', '目前您所在的小区绿地率标准为：绿地率30%以上：优，绿地率高，符合《城市居住区规划设计规范》（GB 50180—93）规定：新区建设不应低于30% 旧区改建不宜低于25%的要求，是绿化程度非常好的小区。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('34', 'c1684b13932c402aa9b9816cb0de51d4', 'TL161003100041', 'afforest', '2', '目前您所在的小区绿地率标准为：25%-30% 良，绿地率一般；绿地率=绿地面积/用地面积×100%。《城市居住区规划设计规范》（GB 50180—93）规定：新区建设不应低于30% 旧区改建不宜低于25%。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('35', '287632ca6ec04e4bb1b903ee5e142142', 'TL161003100042', 'afforest', '3', '目前您所在的小区绿地率标准为：25%以下：差，绿地率较低。绿地率=绿地面积/用地面积×100%。《城市居住区规划设计规范》（GB 50180—93）规定：新区建设不应低于30% 旧区改建不宜低于25%。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('36', 'e0d25785bef8445498e29c417fc419b2', 'TL161003100043', 'volume', '1', '目前您您所在的小区容积率标准为：容积率小于3：优，居住密度低，舒适型。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('37', '4e027183a1c84be1be3b252fad5aa972', 'TL161003100044', 'volume', '2', '目前您您所在的小区容积率标准为：容积率大于3小于5，居住密度尚可，较为舒适：良。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');
INSERT INTO `t_report_environment_level` VALUES ('38', 'ab95442714b549f7854793ceca4cb9d1', 'TL161003100045', 'volume', '3', '目前您您所在的小区容积率标准为：容积率大于5：差，居住密度太大，不适宜居住。', 'system', '2016-10-03 16:23:39', 'system', '2016-10-03 16:23:39');

-- ----------------------------
-- Table structure for t_report_level
-- ----------------------------
DROP TABLE IF EXISTS `t_report_level`;
CREATE TABLE `t_report_level` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(50) DEFAULT NULL COMMENT 'uuid',
  `code` varchar(50) NOT NULL COMMENT '等级编号',
  `name` varchar(50) NOT NULL COMMENT '等级名称',
  `status` int(11) DEFAULT '0' COMMENT '是否可用 0 可用 1 不可用',
  `create_user` varchar(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(20) NOT NULL COMMENT '最后修改人',
  `update_time` datetime NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报告等级表';

-- ----------------------------
-- Records of t_report_level
-- ----------------------------

-- ----------------------------
-- Table structure for t_report_template
-- ----------------------------
DROP TABLE IF EXISTS `t_report_template`;
CREATE TABLE `t_report_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(50) DEFAULT '' COMMENT 'uuid',
  `code` varchar(50) NOT NULL COMMENT '环境报告模板编码',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `type` varchar(50) DEFAULT NULL COMMENT '模板类型',
  `content` text COMMENT '模板内容',
  `create_user` varchar(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(20) NOT NULL COMMENT '最后修改人',
  `update_time` datetime NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='报告模板表';

-- ----------------------------
-- Records of t_report_template
-- ----------------------------
INSERT INTO `t_report_template` VALUES ('1', '2e09aef859574fccbf17658afce32758', 'T161003100017', '', 'report', '环境质量报告根据所监测的各项环境数据，按一定的标准和方法对某区域范围内的环境质量进行说明、评定和预测。本报告包含以下环境指标：噪音、水质、空气质量、土壤、垃圾处理设施、绿地率（小区）、容积率（小区）。', 'system', '2016-10-03 14:21:29', 'system', '2016-10-03 14:21:29');
INSERT INTO `t_report_template` VALUES ('2', '9447ad6ed27440fd8c23bea1f01595ab', 'T161003100018', '噪音', 'noise', '噪音，会影响人类的生活。从总体讲噪音是由物体振动产生，凡是妨碍人们正常休息、学习和工作的声音，以及对人们要听的声音产生干扰的声音。噪音污染主要来源于交通运输、车辆鸣笛、工业噪音、建筑施工、社会噪音如音乐厅、高音喇叭、早市和人的大声说话等。且随工业与交通的发展而日 趋严重，噪音污染是除大气污 染，水体污染外的城市第三大污染。', 'system', '2016-10-03 14:21:29', 'system', '2016-10-03 14:21:29');
INSERT INTO `t_report_template` VALUES ('3', '58719035a7b94125b8610aa0728a3bc2', 'T161003100019', '水质', 'water', '水质是水体的物理性质（如色度、浊度、臭味等）、化学组成（无机物和有机物的含量）、生物学特性（细菌、微生物、浮游生物、底栖生物）的总称。水是氢和氧的化合物，但绝对纯净的水在天然状态下是不存在的。所有的水都含有来自自然界或人类活动的各种溶质及废弃物。降雨吸收大气中的气体和空气中的颗粒物而变成地表径流，部分降水落地穿过含有机质和矿物质的地层进入地下，吸收矿物质和气体；部分降水落地流入江河湖及大海，溶解有机与无机物以及携带细菌和其他生物。类活动使有毒化合物、放射性物质、致病的细菌和病毒污染水体。因此，水体受到自然界及人类活动的影响不同，其质量特点就不同。表征水体水质特征的参数有：感官参数（透明度、嗅和味等）、物理参数（水温、浊度、电导率、盐度和颜色等）、生物学参数（叶绿素、藻类生产力和浮游生物等）、微生物学参数（细菌总数、大肠菌群、病原微生物和致病病毒等）和化学参数（指溶解于水中的所有天然和人造的有机与无机物、溶解性气体和放射性物质）', 'system', '2016-10-03 14:21:29', 'system', '2016-10-03 14:21:29');
INSERT INTO `t_report_template` VALUES ('4', '77c4af9e9a154e948b98a057c1207b61', 'T161003100020', '空气质量', 'air', '空气质量直接反映空气的污染程度，它是依据空气中污染物浓度的高低来判断的。空气污染是一个复杂的现象，在特定时间和地点空气污染物浓度受到许多因素影响。空气污染源也可分为自然的和人为的两大类。自然污染源是由于自然原因（如火山爆发，森林火灾等）而形成，人为污染源是由于人们从事生产和生活活动而形成，其中包括车辆、船舶、飞机的尾气、工业污染、居民生活和取暖、垃圾焚烧等。城市的发展密度、地形地貌和气象等也是影响空气质量的重要因素。空气中的污染物主要包括：\n总悬浮颗粒物：是指漂浮在空气中的固态和液态颗粒物的总称，其粒径范围约为0.1-100 微米。有些颗粒物因粒径大或颜色黑可以为肉眼所见，如烟尘。有些则小到使用电子显微镜才可观察到。通常把环境空气中空气动力学当量直径在10微米以下的颗粒物称为PM10，又称为可吸入颗粒物或飘尘。其中粒径小于等于 2.5微米的颗粒物为PM2.5，它能较长时间悬浮于空气中，其在空气中含量浓度越高，就代表空气污染越严重。虽然PM2.5只是地球大气成分中含量很少的组分，但它对空气质量和能见度等有重要的影响。与较粗的大气颗粒物相比，PM2.5粒径小，面积大，活性强，易附带有毒、有害物质（例如，重金属、微生物等），且在大气中的停留时间长、输送距离远，因而对人体健康和大气环境质量的影响极大。颗粒物的直径越小，进入呼吸道的部位越深。10微米直径的颗粒物通常沉积在上呼吸道，5微米直径的可进入呼吸道的深部，2微米以下的可100%深入到细支气管和肺泡。\n二氧化氮：是一种棕红色、高度活性的气态物质。二氧化氮在臭氧的形成过程中起着重要作用。人为产生的二氧化氮主要来自高温燃烧过程的释放，比如机动车、电厂废气的排放等。 二氧化氮还是酸雨的成因之一。二氧化硫：是一种常见的和重要的大气污染物，是一种无色有刺激性的气体。二氧化硫主要来源于含硫燃料（如煤和石油）的燃烧；含硫矿石（特别是含硫较多的有色金属矿石）的冶炼；化工、炼油和硫酸厂等的生产过程。氮氧化物：种类很多，但主要是一氧化氮(NO)和(NO2)，它们是常见的大气污染物。一氧化碳：是煤、石油等含碳物质不完全燃烧的产物，是一种无色、无臭、无刺激性的有毒气体，几乎不溶于水，在空气中不易与其他物质产生化学反应，故可在大气中停留2～3年之久。如局部污染严重，对人群健康有一定危害。\n空气污染指数(API)是一种反映和评价空气质量的方法，这个指数通常是通过监测二氧化硫、PM10、PM2.5、二氧化氮、一氧化碳、臭氧得出的。空气质量指数(AQI)与市民们的直观感受更加接近。其结果简明直观，使用方便，适用于表示城市的短期空气质量状况和变化趋势。空气污染指数是根据环境空气质量标准和各项污染物对人体健康和生态环境的影响来确定污染指数的分级及相应的污染物浓度限值。', 'system', '2016-10-03 14:21:29', 'system', '2016-10-03 14:21:29');
INSERT INTO `t_report_template` VALUES ('5', 'a970eafbb31044458f368da17a209219', 'T161003100021', '土壤', 'soil', '土壤是地球陆地的表面由矿物质、有机质、水、空气和生物组成的，具有肥力并能生长植物的疏松表层。矿物质和腐殖质组成的固体土粒是土壤的主体，约占土壤体积的50%，固体颗粒间的孔隙由气体和水分占据。土壤气体中绝大部分是由大气层进入的氧气、氮气等，小部分为土壤内的生命活动产生的二氧化碳和水汽等。土壤中的水分主要由地表进入土中，其中包括许多溶解物质。', 'system', '2016-10-03 14:21:29', 'system', '2016-10-03 14:21:29');
INSERT INTO `t_report_template` VALUES ('6', '888c9eecbf88434aa91536ef2e9a19f4', 'T161003100022', '垃圾处理设施', 'rubbish', '垃圾处理设施包括垃圾转运站、垃圾填埋场和垃圾焚烧厂。垃圾转运站的安全距离是300米，垃圾焚烧厂的安全距离是1000米，在安全距离范围内，如果是垃圾转运站或填埋场，有可能面临垃圾成堆，蚊蝇漫天，臭气熏天，污水顺着马路横流，无法行走，夏天不敢开窗的情况，垃圾渗沥液还有可能渗入地下，污染地下水。如果是垃圾焚烧厂，产生的二噁英属于致癌物质，对人体健康非常有害。\n垃圾渗滤液是指来源于垃圾填埋场中垃圾本身含有的水分、进入填埋场的雨雪水及其他水分，扣除垃圾、覆土层的饱和持水量，并经历垃圾层和覆土层而形成的一种高浓度的有机废水。二恶英，又称二氧杂芑(qǐ)，是一种无色无味、毒性严重的脂溶性物质，二恶英实际上是二恶英类一个简称，它指的并不是一种单一物质，而是结构和性质都很相似的包含众多同类物或异构体的两大类有机化合物。二恶英包括210种化合物，这类物质非常稳定，熔点较高，极难溶于水，可以溶于大部分有机溶剂，是无色无味的脂溶性物质，所以非常容易在生物体内积累。', 'system', '2016-10-03 14:21:29', 'system', '2016-10-03 14:21:29');
INSERT INTO `t_report_template` VALUES ('7', '43096fb9432b4387980814042fc1af73', 'T161003100023', '绿地率', 'afforest', '城市的总绿地率是指城市建成区内各绿化用地总面积占城市建成区总面积的比例。也可计算建成区内一定地区的绿地率。如居住区绿地率（描述的是居住区用地范围内各类绿地的总和与居住区用地的比率（% ）。绿地率所指的\"居住区用地范围内各类绿地\"主要包括公共绿地、宅旁绿地等。其中，公共绿地，又包括居住区公园、小游园、组团绿地及其他的一些块状、带状化公共绿地。绿地率=绿地面积/用地面积×100%。\n什么是绿地率？什么是绿化覆盖率？开发商平时在售楼书上印制的有关绿化的指标究竟是绿地率还是绿化覆盖率？地下停车场上、化粪池等上面的绿化算不算绿地率？开发商做的屋顶绿化算不算绿地率……许多购房者对此并不了解，他们想弄清楚绿地率和绿化覆盖率究竟是怎么回事？在计算绿地率时，对绿地的要求非常严格。绿地率所指的“居住区用地范围内各类绿地”主要包括公共绿地、宅旁绿地等。其中，公共绿地，又包括居住区公园、小游园、组团绿地及其他的一些块状、带状化公共绿地。而宅旁绿地等庭院绿化的用地面积，在涉及计算时也要求距建筑外墙1.5米和道路边线1米以内的用地，不得计入绿化用地。此外，还有几种情况也不能计入绿地率的绿化面积，如地下车库、化粪池。这些设施的地表覆土一般达不到3米的深度，在上面种植大型乔木，成活率较低，所以计算绿地率时不能计入。在通常的情况下，许多开发商都是在售楼书上印制出“绿化率”一词，其实这是不准确、不规范的用词，国家有关园林绿化用语根本就没有这个用语，准确的只有“绿地率”和“绿化覆盖率”两种说法。绿化覆盖率可以在小区绿得广。像地下车库这样大面积的底下设施，它的地表虽然种不了树，但可以种草；像距建筑外墙1.5米这样的范围，虽然不算正式绿地，但若能种一些草，总比地砖铺砌更吸引人.在小区规划设计中，计算绿化覆盖率所指的绿地，简单地说，就是有块草皮便可以计入，所以绿化覆盖率有时能做到60%以上。在开发商销售楼盘的时候，有些开发商当然喜欢引用绿化覆盖率的概念。绿化覆盖率一般比绿地率高出20%-40%。', 'system', '2016-10-03 14:21:29', 'system', '2016-10-03 14:21:29');
INSERT INTO `t_report_template` VALUES ('8', 'b1d42be1450f499c99026cb8d21ec5c9', 'T161003100024', '容积率', 'volume', '容积率又称建筑面积毛密度，指项目用地范围内地上总建筑面积与项目总用地面积的比值。容积率是衡量建设用地使用强度的一项重要指标，对于开发商来说，容积率决定地价成本在房屋中占的比例，而对于住户来说，容积率直接涉及到居住的舒适度。现行城市规划法规体系下编制的各类居住用地的控制性详细规划，一般而言，容积率分为：独立别墅为0.2~0.5,联排别墅为0.4~0.7,6层以下多层住宅为0.8~1.2,，11层小高层住宅为1.5~2.0,18层高层住宅为1.8~2.5,19层以上住宅为2.4~4.5,住宅小区容积率小于1.0的，为非普通住宅。 在计算容积率时，建筑面积一般按照《建筑工程建筑面积计算规范》（GB/T50353-2013）的规定计算；存在以下特殊情况: 1、建筑底层架空作为通道、公共停车、布置绿化小品、居民休闲、配套设施等公共用途的，架空层层高宜在2.8 米至3.6米之间，其建筑面积不计入容积率; 2、建筑物顶部有围护结构的楼梯间、水箱间、电梯机房，结构（设备管道）转换层，底层车库、杂物间等。当层高在2.2米及以上的按全面积计入容积率，若层高不足2.2米的按1/2面积计入容积率; 3、建筑物的阳台，不论是凹阳台、挑阳台、封闭阳台、不封闭阳台均按其水平投影面积的一半计算，当进深超过1.8米的各类阳台，均按全面积计入容积率; 4、半地下室凡顶板标高超出室外地坪标高1.0米以上的建筑部分应计入地上建筑面积计算值；不足1.0米的，不计入容积率; 5、如建筑室外地坪标高不一致时，以周边最近的城市道路标高为准加上0.2米作为室外地坪，之后再按上述规定核准。', 'system', '2016-10-03 14:21:29', 'system', '2016-10-03 14:21:29');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '管理员');

-- ----------------------------
-- Table structure for t_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_role_authority`;
CREATE TABLE `t_role_authority` (
  `roleid` int(11) NOT NULL,
  `authorityid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_authority
-- ----------------------------

-- ----------------------------
-- Table structure for t_scoretips
-- ----------------------------
DROP TABLE IF EXISTS `t_scoretips`;
CREATE TABLE `t_scoretips` (
  `scorerange` varchar(20) DEFAULT NULL,
  `tipscontent` longtext
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_scoretips
-- ----------------------------

-- ----------------------------
-- Table structure for t_shopcart
-- ----------------------------
DROP TABLE IF EXISTS `t_shopcart`;
CREATE TABLE `t_shopcart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(10) DEFAULT NULL,
  `envreportid` varchar(20) DEFAULT NULL,
  `valid` varchar(8) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `processtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_shopcart
-- ----------------------------

-- ----------------------------
-- Table structure for t_soil
-- ----------------------------
DROP TABLE IF EXISTS `t_soil`;
CREATE TABLE `t_soil` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `areacode` varchar(20) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `lng` varchar(10) DEFAULT NULL,
  `lat` varchar(10) DEFAULT NULL,
  `grade` varchar(5) DEFAULT NULL,
  `contamindex` varchar(20) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_soil
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(50) DEFAULT NULL COMMENT 'uuid',
  `user_code` varchar(50) NOT NULL COMMENT '用户编码',
  `phone` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `nick_name` varchar(50) DEFAULT '' COMMENT '昵称',
  `head_pic` varchar(500) DEFAULT '' COMMENT '头像',
  `e_mail` varchar(100) DEFAULT '' COMMENT '邮箱',
  `is_login` int(11) DEFAULT '0' COMMENT '是否已登录 0 已登录 1 已登出',
  `create_user` varchar(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(20) NOT NULL COMMENT '最后修改人',
  `update_time` datetime NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_code` (`user_code`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `nick_name` (`nick_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='注册用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1');

-- ----------------------------
-- Table structure for t_waterenviroment
-- ----------------------------
DROP TABLE IF EXISTS `t_waterenviroment`;
CREATE TABLE `t_waterenviroment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `location` varchar(100) DEFAULT NULL,
  `ph` varchar(10) DEFAULT NULL,
  `phquality` varchar(10) DEFAULT NULL,
  `oxygen` varchar(10) DEFAULT NULL,
  `oxygenquality` varchar(10) DEFAULT NULL,
  `nitrogen` varchar(10) DEFAULT NULL,
  `nitrogenquality` varchar(10) DEFAULT NULL,
  `permangan` varchar(10) DEFAULT NULL,
  `permanganquality` varchar(10) DEFAULT NULL,
  `orgacarbon` varchar(10) DEFAULT NULL,
  `orgacarbonquality` varchar(10) DEFAULT NULL,
  `section` varchar(10) DEFAULT NULL,
  `profile` varchar(10) DEFAULT NULL,
  `belong` varchar(10) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_waterenviroment
-- ----------------------------

-- ----------------------------
-- Table structure for t_weatherinform
-- ----------------------------
DROP TABLE IF EXISTS `t_weatherinform`;
CREATE TABLE `t_weatherinform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `temperature` varchar(10) DEFAULT NULL,
  `humidity` varchar(10) DEFAULT NULL,
  `pressure` varchar(10) DEFAULT NULL,
  `wind` varchar(10) DEFAULT NULL,
  `pm2d5` varchar(10) DEFAULT NULL,
  `pm10` varchar(10) DEFAULT NULL,
  `otherparams` varchar(200) DEFAULT NULL,
  `receivedchannel` varchar(20) DEFAULT NULL,
  `receivedtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_weatherinform
-- ----------------------------

-- ----------------------------
-- Procedure structure for proc_get_unique_code
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_get_unique_code`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_get_unique_code`(IN p_code_start VARCHAR(100))
BEGIN

DECLARE p_date CHAR (6);


DECLARE p_now CHAR (6);


DECLARE p_return VARCHAR (30);


DECLARE p_nowno INT;


SET p_now = DATE_FORMAT(NOW(), '%y%m%d');


SET p_date = IFNULL(
	(
		SELECT
			a.date_apply
		FROM
			sys_webcode a
		WHERE
			a.code_start = p_code_start
	),
	''
);


IF (p_date = '') THEN
	INSERT INTO `sys_webcode` (
		`uid`,
		`code_start`,
		`date_apply`,
		`min_number`,
		`now_number`
	)
VALUES
	(
		REPLACE (UUID(), '-', ''),
		p_code_start,
		p_now,
		100000,
		100000
	);


SET p_date = p_now;


END
IF;


IF (p_date != p_now) THEN

IF LENGTH(p_date) != 6 THEN

SET p_now = '';


ELSE
	UPDATE sys_webcode
SET now_number = min_number,
 date_apply = p_now
WHERE
	code_start = p_code_start
AND flag_date = 1;


END
IF;


END
IF;

START TRANSACTION;


SET p_return = (
	SELECT
		now_number
	FROM
		sys_webcode zwwc
	WHERE
		zwwc.code_start = p_code_start FOR UPDATE
);


SET p_return = p_return + 1;

UPDATE sys_webcode
SET now_number = p_return
WHERE
	code_start = p_code_start;

COMMIT;

SELECT
	CONCAT(
		p_code_start,
		p_now,
		p_return
	) AS webcode;


END
;;
DELIMITER ;
