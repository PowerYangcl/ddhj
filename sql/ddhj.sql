/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : ddhj

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2016-10-04 10:29:23
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
-- Table structure for t_chemical_plant
-- ----------------------------
DROP TABLE IF EXISTS `t_chemical_plant`;
CREATE TABLE `t_chemical_plant` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `city` varchar(20) NOT NULL COMMENT '所属区域',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `address` varchar(500) NOT NULL COMMENT '详细地址',
  `lat` varchar(50) NOT NULL COMMENT '纬度',
  `lng` varchar(50) NOT NULL COMMENT '经度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=225 DEFAULT CHARSET=utf8 COMMENT='化工厂';

-- ----------------------------
-- Records of t_chemical_plant
-- ----------------------------
INSERT INTO `t_chemical_plant` VALUES ('1', '北京', '中国石油化工股份有限公司', '北京市朝阳区惠新东街甲6号', '39.9851', '116.42475');
INSERT INTO `t_chemical_plant` VALUES ('2', '北京', '中国石油天然气集团有限公司', '北京市西城区六铺炕', '39.95597', '116.39587');
INSERT INTO `t_chemical_plant` VALUES ('3', '北京', '中国石油天然气股份公司勘探开发研究院', '北京市海淀区学院路20号910信箱', '39.99576', '116.35346');
INSERT INTO `t_chemical_plant` VALUES ('4', '北京', '中国石油化工股份有限公司北京化工研究院', '北京市朝阳区北三环东路14号', '39.96888', '116.42891');
INSERT INTO `t_chemical_plant` VALUES ('5', '北京', '化学工业部规划院', '北京市东城区和平里七号16号楼', '39.9573', '116.423');
INSERT INTO `t_chemical_plant` VALUES ('6', '北京', '中国石化集团燕山石油化工有限公司', '北京房山燕山岗南路1号', '39.72907', '115.96184');
INSERT INTO `t_chemical_plant` VALUES ('7', '北京', '中国石化集团长城润滑油有限公司', '北京市海淀区安宁庄西路6号', '40.04065', '116.3253');
INSERT INTO `t_chemical_plant` VALUES ('8', '北京', '中国石化集团新星石油公司', '北京市海淀区北四环中路263号', '39.98719', '116.35051');
INSERT INTO `t_chemical_plant` VALUES ('9', '北京', '北京石油化学总厂', '北京市朝阳区南皋村', '40.00173', '116.51658');
INSERT INTO `t_chemical_plant` VALUES ('10', '北京', '北京市瑞星石油化工公司', '北京市房山区大董村工业甲2号', '39.71494', '116.06418');
INSERT INTO `t_chemical_plant` VALUES ('11', '北京', '北京北化精细化学品有限责任公司', '北京市丰台区永定门外宋庄路73号院甲2号楼', '39.843003', '116.427902');
INSERT INTO `t_chemical_plant` VALUES ('12', '北京', '北京化工二股份公司', '北京市朝阳区大郊亭', '39.891031', '116.490018');
INSERT INTO `t_chemical_plant` VALUES ('13', '北京', '北京市万富达精细化工厂', '北京市朝阳区化工路西口', '39.8633', '116.51381');
INSERT INTO `t_chemical_plant` VALUES ('14', '北京', '北京有机化工厂', '北京朝阳区东郊大郊亭', '39.891031', '116.490018');
INSERT INTO `t_chemical_plant` VALUES ('15', '北京', '北京兴有化工有限责任公司', '北京朝阳区东郊大郊亭有机化工厂院内', '39.891031', '116.490018');
INSERT INTO `t_chemical_plant` VALUES ('16', '北京', '北京市化工大学精细化工厂', '北京市海淀区紫竹院98号', '39.944612', '116.312445');
INSERT INTO `t_chemical_plant` VALUES ('17', '北京', '首钢集团焦化厂', '北京市石景山区厂东门内', '39.926085', '116.186736');
INSERT INTO `t_chemical_plant` VALUES ('18', '北京', '北京东方锐波化工厂', '北京市丰台区大灰厂东路', '39.856936', '116.166057');
INSERT INTO `t_chemical_plant` VALUES ('19', '北京', '北京燕通石油化工有限公司', '北京市通州区运河西大街13号楼', '39.892994', '116.654025');
INSERT INTO `t_chemical_plant` VALUES ('20', '北京', '北京市华飞化工总公司', '北京市通州区半壁店大街112号', '39.8764', '116.64029');
INSERT INTO `t_chemical_plant` VALUES ('21', '北京', '北京市罗帝东方化工有限公司', '北京市通州区滨河路178号', '39.867294', '116.723442');
INSERT INTO `t_chemical_plant` VALUES ('22', '北京', '北京市科蕊复合肥有限公司', '北京市平谷区南独乐河镇同乐路29号', '40.169735', '117.22118');
INSERT INTO `t_chemical_plant` VALUES ('23', '北京', '北京长阳振兴化工有限公司', '北京市房山区长阳镇篱笆房村北', '39.77213', '116.18782');
INSERT INTO `t_chemical_plant` VALUES ('24', '北京', '北京市北郊精细化工厂', '北京市房山区长阳镇篱笆房村北', '39.77213', '116.18782');
INSERT INTO `t_chemical_plant` VALUES ('25', '北京', '北京市华颖化工有限公司', '北京市房山区马各庄化工四厂内', '39.7007', '116.01583');
INSERT INTO `t_chemical_plant` VALUES ('26', '北京', '北京统一石油化工有限公司', '北京市大兴芦城开发区统一路1号', '39.751114', '116.290812');
INSERT INTO `t_chemical_plant` VALUES ('27', '北京', '北京市大兴区化工厂', '北京市大兴区魏善庄乡黄魏路北侧', '39.661253', '116.412083');
INSERT INTO `t_chemical_plant` VALUES ('28', '北京', '北京杨村化工产品有限公司', '北京马家堡东路96号楼1207室', '39.846295', '116.386875');
INSERT INTO `t_chemical_plant` VALUES ('29', '北京', '北京化工工业集团有限责任公司', '北京市亦庄经济技术开发区', '39.795364', '116.507538');
INSERT INTO `t_chemical_plant` VALUES ('30', '北京', '北京东方化工厂', '北京市通州区滨河路143号', '39.86744', '116.72947');
INSERT INTO `t_chemical_plant` VALUES ('31', '北京', '北京天利海化工有限公司（原北京平谷化工总厂）', '北京市平谷区新平南路124号', '40.1335', '117.11933');
INSERT INTO `t_chemical_plant` VALUES ('32', '北京', '北京化工四厂', '北京市房山区马各庄', '39.705697', '116.020512');
INSERT INTO `t_chemical_plant` VALUES ('33', '北京', '北京门头沟大峪化工厂', '北京门头沟区新桥南大街67号', '39.92651', '116.11031');
INSERT INTO `t_chemical_plant` VALUES ('34', '北京', '中国昊华集团化工总公司', '北京市朝阳区小营路19号中基财富家园A座', '39.99153', '116.424');
INSERT INTO `t_chemical_plant` VALUES ('35', '天津', '天津大港油田集团公司', '天津市大港区', '39.125596', '117.190182');
INSERT INTO `t_chemical_plant` VALUES ('36', '天津', '大港油田公司研究中心', '天津市大港油田团结东路', '38.73034', '117.48256');
INSERT INTO `t_chemical_plant` VALUES ('37', '天津', '天津市大港油田分公司', '天津市大港区', '39.125596', '117.190182');
INSERT INTO `t_chemical_plant` VALUES ('38', '天津', '天津辰鑫石化工程设计有限公司', '天津市大港区', '39.125596', '117.190182');
INSERT INTO `t_chemical_plant` VALUES ('39', '天津', '中国石化集团天津石化股份公司', '天津市大港区上古林西', '38.839705', '117.483551');
INSERT INTO `t_chemical_plant` VALUES ('40', '天津', '天津石化公司第一石化厂', '天津市西青区周李庄', '39.0819', '117.01108');
INSERT INTO `t_chemical_plant` VALUES ('41', '天津', '天津市蓝星石化有限公司化工部', '天津市西青区园田', '39.139446', '117.012247');
INSERT INTO `t_chemical_plant` VALUES ('42', '天津', '天津石化公司第三石化厂', '天津市东丽区程林庄工业区登州路12号', '39.14558', '117.300293');
INSERT INTO `t_chemical_plant` VALUES ('43', '天津', '中国石化天津石化公司炼油部', '天津市大港区', '39.125596', '117.190182');
INSERT INTO `t_chemical_plant` VALUES ('44', '天津', '天津石化公司芳烃部', '天津市大港区', '39.125596', '117.190182');
INSERT INTO `t_chemical_plant` VALUES ('45', '天津', '中国石油大港石化分公司', '天津市大港区', '39.125596', '117.190182');
INSERT INTO `t_chemical_plant` VALUES ('46', '天津', '天津石化乙烯厂', '天津市大港区南环路93-1号', '38.82322', '117.43943');
INSERT INTO `t_chemical_plant` VALUES ('47', '天津', '天津红山石油化工有限公司', '天津市杨北公路6号', '39.363497', '117.115747');
INSERT INTO `t_chemical_plant` VALUES ('48', '天津', '燕化集团天津润滑油油脂有限公司', '天津市汉沽区营城西街', '39.22665', '117.78675');
INSERT INTO `t_chemical_plant` VALUES ('49', '天津', '天津市中天化工有限公司', '天津市津南区大韩庄村', '38.941848', '117.296081');
INSERT INTO `t_chemical_plant` VALUES ('50', '天津', '天津市合材树脂有限公司', '天津市东丽区程林庄工业区澄州路9号', '39.193444', '117.255909');
INSERT INTO `t_chemical_plant` VALUES ('51', '天津', '天津市静邱化工厂', '天津市静海县大邱庄', '38.94218', '116.97576');
INSERT INTO `t_chemical_plant` VALUES ('52', '天津', '天津盛达化工厂', '天津市静海县团泊镇团泊村东500米', '38.90171', '117.163858');
INSERT INTO `t_chemical_plant` VALUES ('53', '天津', '天津胜利化工厂', '天津市武清区黄花店镇西区36号', '39.3608', '116.90392');
INSERT INTO `t_chemical_plant` VALUES ('54', '天津', '天津宝坻区北方化工厂', '天津市宝坻区郝各庄镇', '39.63233', '117.32048');
INSERT INTO `t_chemical_plant` VALUES ('55', '天津', '天津市瑞德化工有限公司', '天津市宝坻区袁罗庄', '39.62538', '117.59807');
INSERT INTO `t_chemical_plant` VALUES ('56', '天津', '天津市晨光化工有限公司', '天津市宝坻区大口屯镇', '39.58528', '117.23819');
INSERT INTO `t_chemical_plant` VALUES ('57', '天津', '天津吉华化工有限公司', '天津市蓟县城西', '40.045342', '117.407449');
INSERT INTO `t_chemical_plant` VALUES ('58', '天津', '天津大沽化工厂有限公司', '天津市塘沽大梁子兴化道1号', '39.01075', '117.64037');
INSERT INTO `t_chemical_plant` VALUES ('59', '天津', '天津市瑞泰化工有限公司', '天津市塘沽区通化路108号', '39.125596', '117.190182');
INSERT INTO `t_chemical_plant` VALUES ('60', '天津', '天津化工厂', '天津市汉沽区新开南路', '39.239582', '117.807572');
INSERT INTO `t_chemical_plant` VALUES ('61', '天津', '天津市津南第二福利化工厂', '天津市静海县陈官屯镇', '38.82039', '116.9137');
INSERT INTO `t_chemical_plant` VALUES ('62', '天津', '天津市天泰精细化学品有限公司', '天津市经济开发区洞庭一街2号', '39.04794', '117.68461');
INSERT INTO `t_chemical_plant` VALUES ('63', '天津', '天津市驰隆化工有限公司', '天津市汉沽区杨家泊镇工业区', '39.29936', '117.90617');
INSERT INTO `t_chemical_plant` VALUES ('64', '天津', '天津市大邱庄宏达化工有限公司', '天津市静海县大邱庄镇', '38.83242', '117.06446');
INSERT INTO `t_chemical_plant` VALUES ('65', '天津', '天津市宏达化工厂', '天津市西青区王稳庄镇', '38.89445', '117.26654');
INSERT INTO `t_chemical_plant` VALUES ('66', '天津', '天津市鑫得利化学总厂', '天津市西青区王稳庄镇', '38.89445', '117.26654');
INSERT INTO `t_chemical_plant` VALUES ('67', '天津', '天津化工研究院津宏化工厂', '天津市北辰区铁东路', '39.18585', '117.20561');
INSERT INTO `t_chemical_plant` VALUES ('68', '天津', '天津市东海化工厂', '天津市西青区王稳庄镇杨科庄村', '38.858292', '117.297446');
INSERT INTO `t_chemical_plant` VALUES ('69', '天津', '天津市星辰化工有限公司', '天津市西青区王稳乡镇', '39.139446', '117.012247');
INSERT INTO `t_chemical_plant` VALUES ('70', '天津', '天津市富利达化工厂', '天津市北辰区西堤头刘快庄', '39.25008', '117.3451');
INSERT INTO `t_chemical_plant` VALUES ('71', '天津', '天津市乐金大沽化学有限公司', '天津市塘沽区顺化道1233号', '38.998847', '117.64005');
INSERT INTO `t_chemical_plant` VALUES ('72', '天津', '天津市圣滨化工有限公司', '天津市津南区咸水沽镇化工小区', '38.97969', '117.39504');
INSERT INTO `t_chemical_plant` VALUES ('73', '天津', '天津市顶福化工总厂', '天津市南开区富康路182号', '38.976832', '117.529639');
INSERT INTO `t_chemical_plant` VALUES ('74', '天津', '天津市西北方化工厂', '天津市西青区李七庄南王兰庄村西', '39.05368', '117.1927');
INSERT INTO `t_chemical_plant` VALUES ('75', '天津', '天津市现代化工总厂', '天津市解放南路处环线17号桥南', '39.076459', '117.231102');
INSERT INTO `t_chemical_plant` VALUES ('76', '天津', '天津市三兴化工厂', '天津市津南区小站镇东西庄房村', '38.889631', '117.450533');
INSERT INTO `t_chemical_plant` VALUES ('77', '天津', '天津市鑫阔化工厂', '天津市西青区南河工业区', '39.03871', '117.106173');
INSERT INTO `t_chemical_plant` VALUES ('78', '天津', '天津市兴华福利化工厂', '天津市西青区张家活乡老君堂村南', '39.03795', '117.05306');
INSERT INTO `t_chemical_plant` VALUES ('79', '天津', '天津市龙江精细化工有限公司', '天津市东丽区津北公路机场处南侧', '39.110505', '117.346192');
INSERT INTO `t_chemical_plant` VALUES ('80', '天津', '天津市津南东亚橡胶厂', '天津市津南区材辛庄', '39.07931', '117.29134');
INSERT INTO `t_chemical_plant` VALUES ('81', '天津', '天津市汇丽精细化学有限公司', '天津市东丽区津塘公路务本道168号', '39.059944', '117.462668');
INSERT INTO `t_chemical_plant` VALUES ('82', '天津', '天津市滨海化工有限公司', '天津市津南区咸水沽', '38.99346', '117.3876');
INSERT INTO `t_chemical_plant` VALUES ('83', '天津', '天津市津南华丽化工厂', '天津市津南区北闸口镇翟家甸村', '38.96695', '117.36618');
INSERT INTO `t_chemical_plant` VALUES ('84', '天津', '天津市津南化工二厂', '天津市津南区八里台镇大韩庄', '38.94268', '117.30261');
INSERT INTO `t_chemical_plant` VALUES ('85', '天津', '天津市绿洲化工有限公司', '天津市津南区八里台镇大韩庄', '38.94268', '117.30261');
INSERT INTO `t_chemical_plant` VALUES ('86', '天津', '天津市天福精细化工有限公司', '天津市津南区咸水沽韩城桥', '38.996593', '117.374234');
INSERT INTO `t_chemical_plant` VALUES ('87', '天津', '天津大港一中精细化工二厂', '天津市大港区上古林村东', '38.83198', '117.48606');
INSERT INTO `t_chemical_plant` VALUES ('88', '天津', '天津市大港宏业化工厂', '天津市大港区太平会镇大村', '38.62234', '117.25501');
INSERT INTO `t_chemical_plant` VALUES ('89', '天津', '天津市大港华明化工厂', '天津市大港区东塘镇', '39.125596', '117.190182');
INSERT INTO `t_chemical_plant` VALUES ('90', '天津', '天津东方红化工厂', '天津市东丽区小东庄', '39.061925', '117.407704');
INSERT INTO `t_chemical_plant` VALUES ('91', '天津', '天津市津东意丽化工厂', '天津市东丽区5号桥', '39.087764', '117.313967');
INSERT INTO `t_chemical_plant` VALUES ('92', '天津', '天津市渤海化工集团有限公司', '天津市和平区湖北路10号', '39.11872', '117.21013');
INSERT INTO `t_chemical_plant` VALUES ('93', '天津', '天津市渤海化工集团有限公司供销总公司', '天津市和平区湖北路10号', '39.11872', '117.21013');
INSERT INTO `t_chemical_plant` VALUES ('94', '天津', '南开大学化工厂', '天津市南开区卫津路94号', '39.10348', '117.17889');
INSERT INTO `t_chemical_plant` VALUES ('95', '天津', '天津市化工设计院', '天津市南开区长江道孤山路2号', '39.125721', '117.159264');
INSERT INTO `t_chemical_plant` VALUES ('96', '天津', '中国石油天然气总公司工程设计研究院', '天津市塘沽区津塘公路40号', '39.03392', '117.64882');
INSERT INTO `t_chemical_plant` VALUES ('97', '天津', '天津市有机化学总公司力生化工厂', '天津市东丽区程林庄工业区', '39.193444', '117.255909');
INSERT INTO `t_chemical_plant` VALUES ('98', '天津', '天津市东方化工厂', '天津市河北区古北道4号', '39.183019', '117.181493');
INSERT INTO `t_chemical_plant` VALUES ('99', '天津', '天津市化学试剂二厂', '天津市东丽区徐庄子', '39.182188', '117.259093');
INSERT INTO `t_chemical_plant` VALUES ('100', '天津', '天津市大港化工三厂有限公司', '天津市大港区中塘镇西正河村', '38.86954', '117.38973');
INSERT INTO `t_chemical_plant` VALUES ('101', '上海', '上海石化股份有限公司-上海金山石化股份公司', '上海市金山区金一路48号', '30.71193', '121.33198');
INSERT INTO `t_chemical_plant` VALUES ('102', '上海', '中国石化集团有限公司上海高桥分公司', '上海市浦东大道3000号', '31.27862', '121.57711');
INSERT INTO `t_chemical_plant` VALUES ('103', '上海', '中国石化高桥分公司化工事业部', '上海市浦东东塘路451号', '31.30544', '121.5669');
INSERT INTO `t_chemical_plant` VALUES ('104', '上海', '中国石化高桥石化公司聚氨酯事业部', '上海市浦东北路1819号', '31.31055', '121.57758');
INSERT INTO `t_chemical_plant` VALUES ('105', '上海', '上海高桥石油化工公司精细化工事业部', '上海市浦东东塘路617号', '31.31709', '121.56661');
INSERT INTO `t_chemical_plant` VALUES ('106', '上海', '上海金菲石油化工有限公司', '上海市金山区卫三路99号', '30.69964', '121.32843');
INSERT INTO `t_chemical_plant` VALUES ('107', '上海', '上海市炼油厂', '上海市浦东江心沙路1号', '31.33166', '121.56217');
INSERT INTO `t_chemical_plant` VALUES ('108', '上海', '上海石油化工研究院', '上海浦东新区浦东北路1658号', '31.30626', '121.57854');
INSERT INTO `t_chemical_plant` VALUES ('109', '上海', '上海市焦化有限公司', '上海闵行区吴泾龙吴路4280号', '31.08619', '121.45939');
INSERT INTO `t_chemical_plant` VALUES ('110', '上海', '中国石化集团上海工程有限公司', '上海市浦东新区张扬路769号', '31.229471', '121.521834');
INSERT INTO `t_chemical_plant` VALUES ('111', '上海', '上海化工研究院', '上海市普陀区云岭东路345号', '31.22259', '121.38965');
INSERT INTO `t_chemical_plant` VALUES ('112', '上海', '中国环球工程公司上海化工设计院有限公司', '上海市肇嘉浜路807号', '31.19801', '121.44779');
INSERT INTO `t_chemical_plant` VALUES ('113', '上海', '上海大联石油化工有限公司', '上海浦东新区唐镇金丰路420号', '31.23161', '121.65829');
INSERT INTO `t_chemical_plant` VALUES ('114', '上海', '上海四达石油化工科技公司', '上海市华东理工化学院实验12楼229室', '31.165774', '121.405262');
INSERT INTO `t_chemical_plant` VALUES ('115', '上海', '上海市海申石油化工厂', '上海市宝山区陈太路2136号', '31.398896', '121.489934');
INSERT INTO `t_chemical_plant` VALUES ('116', '上海', '上海华生化工有限公司', '上海市黄浦区九江路69号', '31.23747', '121.48868');
INSERT INTO `t_chemical_plant` VALUES ('117', '上海', '上海化工装备有限公司', '上海市黄浦区新闸路126号9楼', '31.23875', '121.47132');
INSERT INTO `t_chemical_plant` VALUES ('118', '上海', '上海锦山化工有限公司', '上海市金山区金山卫镇', '30.72462', '121.31778');
INSERT INTO `t_chemical_plant` VALUES ('119', '上海', '上海集能化工有限公司', '上海市金山区金一路1号2308室', '30.71152', '121.33258');
INSERT INTO `t_chemical_plant` VALUES ('120', '上海', '上海华杰精细有限公司', '上海市永嘉路35号茂名大厦17楼', '31.21064', '121.46311');
INSERT INTO `t_chemical_plant` VALUES ('121', '上海', '上海华源精细化工有限公司', '上海市江西中路170号', '31.23491', '121.48754');
INSERT INTO `t_chemical_plant` VALUES ('122', '上海', '上海惠丰石油化工有限公司', '上海市长宁区番愚路75弄1号', '31.20628', '121.42993');
INSERT INTO `t_chemical_plant` VALUES ('123', '上海', '上海明台化学工业有限公司', '上海市崇明县东风农场', '31.71367', '121.47586');
INSERT INTO `t_chemical_plant` VALUES ('124', '上海', '上海中联化工厂', '上海市宝山区沪太路4400号', '31.313143', '121.411024');
INSERT INTO `t_chemical_plant` VALUES ('125', '上海', '上海化工厂有限公司', '上海市杨浦区杨树浦路1578号', '31.25676', '121.53922');
INSERT INTO `t_chemical_plant` VALUES ('126', '上海', '上海澎浦化工厂', '上海市闸北区老沪太路1179号', '31.269355', '121.44466');
INSERT INTO `t_chemical_plant` VALUES ('127', '上海', '上海虹光化工厂', '上海市宝山区呼兰路201弄125号', '31.34903', '121.45696');
INSERT INTO `t_chemical_plant` VALUES ('128', '上海', '上海新光化工厂', '上海市杨浦区长阳路2455号', '31.274848', '121.544028');
INSERT INTO `t_chemical_plant` VALUES ('129', '上海', '上海胶体化工厂', '上海市浦东新区鼓浪路1600弄35号', '31.245944', '121.567706');
INSERT INTO `t_chemical_plant` VALUES ('130', '上海', '上海新浦化工厂', '上海市浦东新区凌桥镇江东路1585号', '31.36412', '121.547807');
INSERT INTO `t_chemical_plant` VALUES ('131', '上海', '上海群力化工有限公司', '上海市沪闵路额桥光华路698号', '31.068415', '121.385275');
INSERT INTO `t_chemical_plant` VALUES ('132', '上海', '上海裕隆化工有限公司', '上海闵行区马桥镇青年路198号', '31.03275', '121.36407');
INSERT INTO `t_chemical_plant` VALUES ('133', '上海', '上海桑迪精细化工研究所', '上海市宝安公路326号', '31.36684', '121.41863');
INSERT INTO `t_chemical_plant` VALUES ('134', '上海', '上海卡博特化工有限公司', '上海市闵行区双柏路15号', '31.09171', '121.45996');
INSERT INTO `t_chemical_plant` VALUES ('135', '上海', '上海新誉化工厂', '上海市闵行区塘湾镇北吴路大河头东345号', '31.053009', '121.439509');
INSERT INTO `t_chemical_plant` VALUES ('136', '上海', '上海伊凡尔精细化工有限公司', '上海市南汇区芦潮岗农场内', '31.231706', '121.472644');
INSERT INTO `t_chemical_plant` VALUES ('137', '上海', '上海市世平塑胶有限公司', '上海市奉贤区彭塘公路280号', '30.912345', '121.458472');
INSERT INTO `t_chemical_plant` VALUES ('138', '上海', '上海燎原第一日用化工厂', '上海市奉贤县燎原农场内', '30.85894', '121.65629');
INSERT INTO `t_chemical_plant` VALUES ('139', '上海', '上海悦新达石油化工厂', '上海市南汇区十八里桥', '31.231706', '121.472644');
INSERT INTO `t_chemical_plant` VALUES ('140', '上海', '上海宏业振泰化工有限公司', '奉贤县南桥镇运河北路88号', '30.92651', '121.471006');
INSERT INTO `t_chemical_plant` VALUES ('141', '上海', '上海平原化工厂', '上海市奉贤区平安镇沿港路4号', '30.904053', '121.729936');
INSERT INTO `t_chemical_plant` VALUES ('142', '上海', '上海龙邦化工有限公司（原上海生福化工厂）', '上海市奉贤县五四农场内', '30.869987', '121.72343');
INSERT INTO `t_chemical_plant` VALUES ('143', '上海', '上海宝达化工有限公司', '上海市奉贤区青村镇城乡东路15号', '30.92208', '121.58716');
INSERT INTO `t_chemical_plant` VALUES ('144', '上海', '上海油墨泗联化工有限公司', '上海市松江区泗泾镇鼓浪路220号', '31.11652', '121.27974');
INSERT INTO `t_chemical_plant` VALUES ('145', '上海', '上海青东化工厂', '上海市青浦外松公路7558号', '31.151209', '121.113021');
INSERT INTO `t_chemical_plant` VALUES ('146', '上海', '上海翱翔化工有限公司', '上海市奉贤青春镇经济园区南奉公路183号', '30.84233', '121.56118');
INSERT INTO `t_chemical_plant` VALUES ('147', '上海', '上海金力泰涂料化工有限公司', '上海市奉贤区沿钱公路2888号', '30.94843', '121.5511');
INSERT INTO `t_chemical_plant` VALUES ('148', '上海', '上海市嘉定区马陆化工厂', '上海嘉定区马陆镇亚岗路303号', '31.37566', '121.2954');
INSERT INTO `t_chemical_plant` VALUES ('149', '上海', '上海市振兴化工二厂', '上海市嘉定区外钱公路1845号（望新镇钱门）', '31.36396', '121.13315');
INSERT INTO `t_chemical_plant` VALUES ('150', '上海', '上海中达化工发展有限公司', '上海市浦东区东方路989号', '31.22163', '121.52977');
INSERT INTO `t_chemical_plant` VALUES ('151', '广东', '中国石化股份公司广州石化分公司', '广东省广州市黄埔石化路176号', '23.105366', '113.469835');
INSERT INTO `t_chemical_plant` VALUES ('152', '广东', '广东省石油化工研究院', '广东省广州市天河区棠下', '23.126862', '113.379179');
INSERT INTO `t_chemical_plant` VALUES ('153', '广东', '广东省石油化工设计院', '广东省广州市沙面大街48号', '23.10738', '113.24419');
INSERT INTO `t_chemical_plant` VALUES ('154', '广东', '广州市化学工业研究所', '广东省广州市白云区石井石潭路潭村桥东', '23.190648', '113.237415');
INSERT INTO `t_chemical_plant` VALUES ('155', '广东', '化学工业部广州地质工程勘察院', '广东省广州市云山大道宾馆新村二幢', '23.38196', '113.21158');
INSERT INTO `t_chemical_plant` VALUES ('156', '广东', '广州市珠江化工集团公司', '广东省广州市荔湾路123号', '23.13105', '113.24424');
INSERT INTO `t_chemical_plant` VALUES ('157', '广东', '广州汽巴精细化工有限公司', '广东省广州市黄埔大道中505号', '23.11657', '113.38863');
INSERT INTO `t_chemical_plant` VALUES ('158', '广东', '广州市人民化工厂', '广东省广州市海珠区工业大道北39号', '23.09623', '113.25509');
INSERT INTO `t_chemical_plant` VALUES ('159', '广东', '广州市精细化学工业有限公司', '广东省广州市海珠区宝岗路32号', '23.10178', '113.26197');
INSERT INTO `t_chemical_plant` VALUES ('160', '广东', '广州市龙沙精细化工有限公司', '广东省广州市海珠区金辉路39号', '23.07035', '113.28726');
INSERT INTO `t_chemical_plant` VALUES ('161', '广东', '广州市广漆化工实业有限公司', '广东省广州市芳村区白鹤洞东朗', '23.07562', '113.2428');
INSERT INTO `t_chemical_plant` VALUES ('162', '广东', '广州市增城东红化工厂（原广州市东红化工厂）', '广东省广州市沙河天平架桐油岗5号', '23.160963', '113.32076');
INSERT INTO `t_chemical_plant` VALUES ('163', '广东', '广东番禺建滔化工公司', '广东省广州市番禺市黄阁镇小虎工业区', '22.838657', '113.544617');
INSERT INTO `t_chemical_plant` VALUES ('164', '广东', '广州市秀珀化工有限公司', '广东省广州市番禺市钟村镇谢石路72号秀珀工业园', '22.99654', '113.283351');
INSERT INTO `t_chemical_plant` VALUES ('165', '广东', '广州市坚红化工厂', '广东省广州市黄埔大道中505号', '23.11657', '113.38863');
INSERT INTO `t_chemical_plant` VALUES ('166', '广东', '广州市东风化工实业公司', '广东省广州市黄埔东路2019号', '23.08331', '113.50452');
INSERT INTO `t_chemical_plant` VALUES ('167', '广东', '广州市黄埔化工厂', '广东省广州市黄埔东路2009号', '23.08329', '113.50171');
INSERT INTO `t_chemical_plant` VALUES ('168', '广东', '广州市华立-萨其宾化工有限公司', '广东省广州市黄埔区南岗西路', '23.08879', '113.548279');
INSERT INTO `t_chemical_plant` VALUES ('169', '广东', '番禺市邦腾化工有限公司（原番禺市国际化工有限公司）', '广东省番禺市市桥镇愚山西路180号', '22.944006', '113.364239');
INSERT INTO `t_chemical_plant` VALUES ('170', '广东', '韶关市化工厂', '广东省韶关市东郊黄金村', '24.82257', '113.62443');
INSERT INTO `t_chemical_plant` VALUES ('171', '广东', '兴宁市精细化工厂', '广东省兴宁市兴田二路望江', '24.152183', '115.7259');
INSERT INTO `t_chemical_plant` VALUES ('172', '广东', '兴宁市华威化工实业公司', '广东省兴宁市永和镇', '24.1438', '115.80389');
INSERT INTO `t_chemical_plant` VALUES ('173', '广东', '汕头化工发展（集团）公司', '广东省汕头市西港路78号', '23.379524', '116.669262');
INSERT INTO `t_chemical_plant` VALUES ('174', '广东', '汕头市精细化工集团有限公司', '广东省汕头市大学路38号', '23.38489', '116.67187');
INSERT INTO `t_chemical_plant` VALUES ('175', '广东', '汕头市金砂企业集团金砂化工厂', '广东省汕头市金园区金园路二横四号', '23.370023', '116.703518');
INSERT INTO `t_chemical_plant` VALUES ('176', '广东', '汕头市西陇化工厂', '广东省汕头市漕山路', '23.37102', '116.708463');
INSERT INTO `t_chemical_plant` VALUES ('177', '广东', '汕头市升平区粤东精细化工厂', '广东省汕头市北墩茶厂东侧', '23.38743', '116.70142');
INSERT INTO `t_chemical_plant` VALUES ('178', '广东', '澄海市安成化工有限公司', '广东省澄海市莲下镇渡亭工业区', '23.48181', '116.78447');
INSERT INTO `t_chemical_plant` VALUES ('179', '广东', '深圳市化工设计院', '广东省深圳市田贝四路二号大院化工大厦3楼', '22.56758', '114.12973');
INSERT INTO `t_chemical_plant` VALUES ('180', '广东', '深圳兴喜化工有限公司（原深圳兴嘉化工有限公司）', '广东省深圳市布吉镇茂宝大厦首层', '22.60547', '114.12675');
INSERT INTO `t_chemical_plant` VALUES ('181', '广东', '深圳天正塑胶股份公司（原深圳石化塑胶股份公司）', '广东省深圳罗湖区水贝布新路3006号', '22.57256', '114.12618');
INSERT INTO `t_chemical_plant` VALUES ('182', '广东', '深圳市南海化工工程公司', '广东省深圳市罗湖区田贝四路2号化工大厦4楼', '22.56755', '114.1298');
INSERT INTO `t_chemical_plant` VALUES ('183', '广东', '勇达精细化工（珠海）公司', '广东省珠海市梅华西路869号勇达大厦', '22.27575', '113.52347');
INSERT INTO `t_chemical_plant` VALUES ('184', '广东', '佛山市燃气集团有限公司', '广东省佛山市禅城区季华五路25号', '23.01229', '113.11222');
INSERT INTO `t_chemical_plant` VALUES ('185', '广东', '佛山市电化总厂', '广东省佛山市罗公路37号', '23.028762', '113.122717');
INSERT INTO `t_chemical_plant` VALUES ('186', '广东', '佛山市三水区南边镇地球劲霸石油化工有限公司', '广东省佛山市三水区南边镇工业大道126号', '23.06311', '112.84541');
INSERT INTO `t_chemical_plant` VALUES ('187', '广东', '佛山市佛山化工厂', '广东省佛山市禅城区上沙九江基2号', '23.04343', '113.0966');
INSERT INTO `t_chemical_plant` VALUES ('188', '广东', '广东高聚化学工业公司', '广东省佛山市高明区沿江路37号', '22.87655', '112.90579');
INSERT INTO `t_chemical_plant` VALUES ('189', '广东', '佛山市澜石镇银星化工厂', '广东省佛山市大福南路东侧石头工业园19栋', '23.20961', '113.13637');
INSERT INTO `t_chemical_plant` VALUES ('190', '广东', '佛山市化工总厂有限公司', '广东省佛山市大沥仙溪长虹岭', '23.12628', '113.05315');
INSERT INTO `t_chemical_plant` VALUES ('191', '广东', '佛山市三水区新联邦化工实业公司', '广东省佛山市三水区金木开发吴等岗', '23.16504', '112.899414');
INSERT INTO `t_chemical_plant` VALUES ('192', '广东', '佛山市南海区华生化工厂', '广东省佛山市南海区西樵大同柏山开发区', '22.87954', '112.98728');
INSERT INTO `t_chemical_plant` VALUES ('193', '广东', '佛山市顺德区恒意石油化工有限公司', '广东省佛山市顺德区龙江镇旧龙良路21公里处', '22.86832', '113.07409');
INSERT INTO `t_chemical_plant` VALUES ('194', '广东', '佛山市顺德区广怡化工实业公司', '广东省佛山市顺德区桂洲镇兴华路', '22.745958', '113.320665');
INSERT INTO `t_chemical_plant` VALUES ('195', '广东', '广东德美精细化工股份有限公司', '广东省佛山市顺德区容里容江西路43号', '22.848794', '113.238624');
INSERT INTO `t_chemical_plant` VALUES ('196', '广东', '佛山市顺德区嘉力化工有限公司', '广东省佛山市顺德区桂洲镇华口管理区', '22.757189', '113.333565');
INSERT INTO `t_chemical_plant` VALUES ('197', '广东', '佛山市顺德区长实化工有限公司', '广东省佛山市顺德区龙江镇西溪工业区', '22.865167', '113.08995');
INSERT INTO `t_chemical_plant` VALUES ('198', '广东', '佛山市顺德区新东方精细化工厂', '广东省佛山市顺德区杏坛桑麻工业区', '22.800274', '113.173319');
INSERT INTO `t_chemical_plant` VALUES ('199', '广东', '广东省佛山市顺德区奇力士石油有限公司', '广东省佛山市顺德区大良大门堤围路8号', '22.8415', '113.115944');
INSERT INTO `t_chemical_plant` VALUES ('200', '广东', '肇庆市月照辉化工厂', '广东省肇庆市厂排街27号', '23.046722', '112.431068');
INSERT INTO `t_chemical_plant` VALUES ('201', '广东', '四会市化工厂', '广东省四会市白沙头', '23.350923', '112.646975');
INSERT INTO `t_chemical_plant` VALUES ('202', '广东', '湛江市化工研究所', '广东省湛江市椹川大道南62号', '21.2028', '110.39399');
INSERT INTO `t_chemical_plant` VALUES ('203', '广东', '湛江中星石油化工有限公司', '广东省湛江市峡山区解放西路22号区府8楼', '21.19178', '110.39785');
INSERT INTO `t_chemical_plant` VALUES ('204', '广东', '湛江市新中美化学工业公司', '广东省湛江市霞山区石港路2号', '21.41613', '110.14256');
INSERT INTO `t_chemical_plant` VALUES ('205', '广东', '湛江华洋石化有限公司', '广东省湛江市霞山区友谊路1号海港大厦8楼', '21.18355', '110.41213');
INSERT INTO `t_chemical_plant` VALUES ('206', '广东', '廉江市化工有限公司', '广东省廉江市龙塘路', '21.610837', '110.295188');
INSERT INTO `t_chemical_plant` VALUES ('207', '广东', '江门市电化厂', '广东省江门市江海三路7号', '22.59781', '113.12171');
INSERT INTO `t_chemical_plant` VALUES ('208', '广东', '江门市化肥总厂', '广东省江门市北街区', '22.590431', '113.094942');
INSERT INTO `t_chemical_plant` VALUES ('209', '广东', '江门市建滔化工有限公司', '广东省江门市江海路123号', '22.571415', '113.103268');
INSERT INTO `t_chemical_plant` VALUES ('210', '广东', '江门市谦信化工发展有限公司', '广东省江门市江海路123号', '22.571415', '113.103268');
INSERT INTO `t_chemical_plant` VALUES ('211', '广东', '台山市化工厂', '广东省台山市城南门路260号', '22.243995', '112.798265');
INSERT INTO `t_chemical_plant` VALUES ('212', '广东', '台山市磷肥有限公司', '广东省台山市大江镇公益圩', '22.43', '112.776446');
INSERT INTO `t_chemical_plant` VALUES ('213', '广东', '恩平市精细化工总厂', '广东省恩平市新平北路1号', '22.1895', '112.31831');
INSERT INTO `t_chemical_plant` VALUES ('214', '广东', '恩平燕华化工有限公司', '广东省恩平市美华东路', '22.184881', '112.330363');
INSERT INTO `t_chemical_plant` VALUES ('215', '广东', '中山市东明化工有限公司', '广东省中山市东区起湾道东祥路', '22.544881', '113.411732');
INSERT INTO `t_chemical_plant` VALUES ('216', '广东', '中山市凯达精细化工股份公司', '广东省中山市石岐青溪路116号', '22.54193', '113.370365');
INSERT INTO `t_chemical_plant` VALUES ('217', '广东', '中山市友成石油化工有限公司', '广东省中山市板芙镇白溪工业区', '22.41686', '113.32235');
INSERT INTO `t_chemical_plant` VALUES ('218', '广东', '中山华孚石油化工公司', '广东省中山小榄镇福兴炼油厂内', '22.6958', '113.22406');
INSERT INTO `t_chemical_plant` VALUES ('219', '广东', '广东中成化工有限公司', '广东省东莞市广州经济技术开发区', '22.932771', '113.887059');
INSERT INTO `t_chemical_plant` VALUES ('220', '广东', '思麦尔石油化工有限公司', '广东省东莞市樟木头镇南城大道31号', '22.89735', '114.06842');
INSERT INTO `t_chemical_plant` VALUES ('221', '广东', '中国石化集团茂名石化股份公司', '广东省茂名市厂前西路8号', '21.67458', '110.90198');
INSERT INTO `t_chemical_plant` VALUES ('222', '广东', '茂名市高山海洋化工有限公司', '广东省茂名市为民路41号', '21.659751', '110.919229');
INSERT INTO `t_chemical_plant` VALUES ('223', '广东', '茂名市利威化工有限公司', '广东省茂名市茂南区金塘镇', '21.74621', '110.84103');
INSERT INTO `t_chemical_plant` VALUES ('224', '广东', '高州市化工总厂', '广东省高州石仔岭区', '21.88678', '110.82594');

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
-- Table structure for t_industrial_park
-- ----------------------------
DROP TABLE IF EXISTS `t_industrial_park`;
CREATE TABLE `t_industrial_park` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `city` varchar(20) NOT NULL COMMENT '所属区域',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `address` varchar(500) NOT NULL COMMENT '详细地址',
  `lat` varchar(50) NOT NULL COMMENT '纬度',
  `lng` varchar(50) NOT NULL COMMENT '经度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=371 DEFAULT CHARSET=utf8 COMMENT='工业园';

-- ----------------------------
-- Records of t_industrial_park
-- ----------------------------
INSERT INTO `t_industrial_park` VALUES ('1', '北京', '星光影视园', '北京市大兴区西红门镇北兴路(东段)2号', '39.774501', '116.353456');
INSERT INTO `t_industrial_park` VALUES ('2', '北京', '北京东亿国际传媒产业园', '北京市朝阳区建国路', '39.909067', '116.485597');
INSERT INTO `t_industrial_park` VALUES ('3', '北京', '联想总部(北京)园区', '北京市海淀区', '39.956074', '116.310316');
INSERT INTO `t_industrial_park` VALUES ('4', '北京', '电通创意广场', '北京市朝阳区彩虹路', '39.991626', '116.497747');
INSERT INTO `t_industrial_park` VALUES ('5', '北京', '互联网金融产业园', '北京市海淀区东冉北街9号', '39.9574', '116.26905');
INSERT INTO `t_industrial_park` VALUES ('6', '北京', '北京经济技术开发区', '北京市荣华中路', '39.802209', '116.502703');
INSERT INTO `t_industrial_park` VALUES ('7', '北京', '电子城IT产业园', '北京市朝阳区酒仙桥北路甲10号', '39.98091', '116.50624');
INSERT INTO `t_industrial_park` VALUES ('8', '北京', '77文化创意产业园', '北京市美术馆后街77号', '39.92743', '116.40885');
INSERT INTO `t_industrial_park` VALUES ('9', '北京', '恒通国际创新园', '北京市朝阳区酒仙桥北路9号', '39.9881', '116.49261');
INSERT INTO `t_industrial_park` VALUES ('10', '北京', '数码庄园', '北京亦庄地盛西路1号', '39.78321', '116.49947');
INSERT INTO `t_industrial_park` VALUES ('11', '北京', '铭基国际创意公园', '北京市朝阳区', '39.921489', '116.486409');
INSERT INTO `t_industrial_park` VALUES ('12', '北京', '北京亦庄生物医药园', '北京市亦庄经济开发区科创六街88号', '39.8028', '116.54031');
INSERT INTO `t_industrial_park` VALUES ('13', '北京', '北京国际矿业城', '北京市大兴区亦庄科创东二街5号', '39.818954', '116.537199');
INSERT INTO `t_industrial_park` VALUES ('14', '北京', '中关村互联网文化创意产业园', '北京市海淀区西八里庄路61号', '39.92891', '116.2781');
INSERT INTO `t_industrial_park` VALUES ('15', '北京', '望京国际研发园', '北京市朝阳区望京东路6号', '40.0066', '116.48934');
INSERT INTO `t_industrial_park` VALUES ('16', '北京', '中福丽宫品牌基地', '北京市丰台区新宫体育健身休闲园8号', '39.808915', '116.35712');
INSERT INTO `t_industrial_park` VALUES ('17', '北京', '顺事嘉业创业园', '北京市海淀区清河朱房路66号', '40.02782', '116.33386');
INSERT INTO `t_industrial_park` VALUES ('18', '北京', '中国农大国际创业园', '北京市天秀路10号', '40.02084', '116.27703');
INSERT INTO `t_industrial_park` VALUES ('19', '北京', '中关村软件园（二期）', '北京市海淀区东北旺西路8号', '40.04428', '116.29526');
INSERT INTO `t_industrial_park` VALUES ('20', '北京', '新华1949国际创意设计产业园', '北京市西城区', '39.915309', '116.366794');
INSERT INTO `t_industrial_park` VALUES ('21', '北京', '益园文创基地', '北京市杏石口路', '39.950298', '116.246033');
INSERT INTO `t_industrial_park` VALUES ('22', '北京', '兆维工业园', '北京市朝阳区酒仙桥路14号', '39.97379', '116.49001');
INSERT INTO `t_industrial_park` VALUES ('23', '北京', '北坞创新园', '北京市北坞村路23号', '39.97867', '116.25724');
INSERT INTO `t_industrial_park` VALUES ('24', '北京', '大白楼工业区', '北京市西红门', '39.78815', '116.35534');
INSERT INTO `t_industrial_park` VALUES ('25', '北京', '京忱润业', '北京市朝阳区京哈高速辅路', '39.86891', '116.53816');
INSERT INTO `t_industrial_park` VALUES ('26', '北京', '龙源文化创意园', '北京市朝阳区高碑店乡西店1110', '39.903545', '116.510145');
INSERT INTO `t_industrial_park` VALUES ('27', '北京', '隆盛工业园', '北京市荣昌东街7号院', '39.78403', '116.52174');
INSERT INTO `t_industrial_park` VALUES ('28', '北京', '红莊国际文化保税创新园', '北京市朝阳区朝阳北路白家楼甲1号', '39.926127', '116.544143');
INSERT INTO `t_industrial_park` VALUES ('29', '北京', '亿发工业园', '北京市亿发工业园28号', '39.772313', '116.299843');
INSERT INTO `t_industrial_park` VALUES ('30', '北京', '西红门星光视界中心', '北京市春河路39号', '39.904989', '116.405285');
INSERT INTO `t_industrial_park` VALUES ('31', '北京', '方仕工业园', '北京市和义', '39.816668', '116.401488');
INSERT INTO `t_industrial_park` VALUES ('32', '北京', '蓝涛中心', '北京市朝阳区酒仙桥万红路5号', '39.98052', '116.49416');
INSERT INTO `t_industrial_park` VALUES ('33', '北京', '石龙工业区', '北京市石龙路丽景长安', '39.90498', '116.10959');
INSERT INTO `t_industrial_park` VALUES ('34', '北京', '好景国际创业产业园', '北京市大兴区亦庄经济开发区博兴九路2号', '39.77246', '116.500286');
INSERT INTO `t_industrial_park` VALUES ('35', '北京', '金田恒业工业园', '北京市经海二路28号院', '39.80532', '116.5321');
INSERT INTO `t_industrial_park` VALUES ('36', '北京', '寿宝庄工业园区', '北京市兴顺南路二条', '39.782363', '116.375737');
INSERT INTO `t_industrial_park` VALUES ('37', '北京', '赛蒂国际工业园', '北京市科创十四街6号', '39.77756', '116.56819');
INSERT INTO `t_industrial_park` VALUES ('38', '北京', '268文化产业基地总部（国家音乐产业基地）', '北京市朝阳区褡裢坡268号', '39.93102', '116.559954');
INSERT INTO `t_industrial_park` VALUES ('39', '北京', '东村创意基地', '北京市朝阳区豆各庄乡西马各庄开发区一号', '39.872145', '116.571015');
INSERT INTO `t_industrial_park` VALUES ('40', '北京', '北大生物城', '北京市海淀区上地西路39号', '40.03258', '116.30791');
INSERT INTO `t_industrial_park` VALUES ('41', '北京', '华膳园传媒文化产业园', '北京市朝阳区', '39.921489', '116.486409');
INSERT INTO `t_industrial_park` VALUES ('42', '北京', '宏福创业园', '北京市七北路', '40.102451', '116.39318');
INSERT INTO `t_industrial_park` VALUES ('43', '北京', '新华联工业园南区', '北京市台湖镇台湖村', '39.833388', '116.632958');
INSERT INTO `t_industrial_park` VALUES ('44', '北京', '东风五号', '北京市朝阳区', '39.921489', '116.486409');
INSERT INTO `t_industrial_park` VALUES ('45', '北京', '茂华工场', '北京市彩达三街', '39.904989', '116.405285');
INSERT INTO `t_industrial_park` VALUES ('46', '北京', '安定镇工业北区', '北京市青礼路', '39.620615', '116.492828');
INSERT INTO `t_industrial_park` VALUES ('47', '北京', '沙河科技园', '北京市G6辅路百沙路路口', '40.14195', '116.26081');
INSERT INTO `t_industrial_park` VALUES ('48', '北京', '东晓景文化创意产业园', '北京市管庄路', '39.925051', '116.590679');
INSERT INTO `t_industrial_park` VALUES ('49', '北京', '联东U谷西区', '北京市通州区', '39.902486', '116.658603');
INSERT INTO `t_industrial_park` VALUES ('50', '北京', '二拨子工业园区', '北京市昌平区回龙观', '40.08203', '116.34457');
INSERT INTO `t_industrial_park` VALUES ('51', '北京', '中国石油科技创新基地', '北京市昌平区六环路南侧', '40.218085', '116.235906');
INSERT INTO `t_industrial_park` VALUES ('52', '北京', '西沙工业区', '北京市北七家', '40.11222', '116.41649');
INSERT INTO `t_industrial_park` VALUES ('53', '北京', '东城文化人才（国际）创业园', '北京市青龙胡同甲1号', '39.948235', '116.423431');
INSERT INTO `t_industrial_park` VALUES ('54', '北京', '国门商务区', '北京市李桥镇机场东路', '40.074362', '116.631439');
INSERT INTO `t_industrial_park` VALUES ('55', '北京', '上地国际创业园-西区', '北京市海淀区信息路1号', '40.04615', '116.30336');
INSERT INTO `t_industrial_park` VALUES ('56', '北京', '新华联工业园', '北京市通州区', '39.902486', '116.658603');
INSERT INTO `t_industrial_park` VALUES ('57', '北京', '宏达工业园', '北京市宏达北路8', '39.802678', '116.507195');
INSERT INTO `t_industrial_park` VALUES ('58', '北京', '小沙河工业园', '北京市七小路', '40.113713', '116.32465');
INSERT INTO `t_industrial_park` VALUES ('59', '北京', '华电产业园', '北京市丰台区汽车博物馆东路', '39.826814', '116.302655');
INSERT INTO `t_industrial_park` VALUES ('60', '北京', '联东U谷东区', '北京市景盛南四街甲13', '39.731674', '116.571232');
INSERT INTO `t_industrial_park` VALUES ('61', '北京', '光华科技园', '北京市安宁庄东路18号', '40.04343', '116.33112');
INSERT INTO `t_industrial_park` VALUES ('62', '北京', '马坊工业园区', '北京市盘龙路', '40.042623', '117.00899');
INSERT INTO `t_industrial_park` VALUES ('63', '北京', '西京工业园', '北京市大兴区经海三路', '39.799885', '116.540426');
INSERT INTO `t_industrial_park` VALUES ('64', '北京', '瀛海镇工业园', '北京市大兴区瀛海镇京福路', '39.734864', '116.460492');
INSERT INTO `t_industrial_park` VALUES ('65', '北京', '星红工业园', '北京市大兴区', '39.755477', '116.337733');
INSERT INTO `t_industrial_park` VALUES ('66', '北京', '郭村工业园', '北京市朱大路', '39.685103', '116.592187');
INSERT INTO `t_industrial_park` VALUES ('67', '北京', '西辛峰工业区', '北京市昌平区崔村镇西辛峰村', '40.20223', '116.34178');
INSERT INTO `t_industrial_park` VALUES ('68', '北京', '光机电一体化产业基地', '北京市潞西路', '39.817386', '116.566675');
INSERT INTO `t_industrial_park` VALUES ('69', '北京', '三间房工业区', '北京市大兴区黄村镇新建西街', '39.727654', '116.421988');
INSERT INTO `t_industrial_park` VALUES ('70', '北京', '顶佳文化创业园', '北京市金服大街5号', '39.73137', '116.42576');
INSERT INTO `t_industrial_park` VALUES ('71', '北京', '联东U谷中区', '北京市通州区景盛南四街15号', '39.73023', '116.56814');
INSERT INTO `t_industrial_park` VALUES ('72', '北京', '北京大唐高新技术创业园', '北京市八里桥南街5号', '39.906899', '116.632286');
INSERT INTO `t_industrial_park` VALUES ('73', '北京', '中黎科技园', '北京市海淀区中黎科技园3号', '40.03731', '116.31374');
INSERT INTO `t_industrial_park` VALUES ('74', '北京', '永昌工业园', '北京市大兴区永昌北路3号', '39.80398', '116.5083');
INSERT INTO `t_industrial_park` VALUES ('75', '北京', '光联工业园', '北京市台湖镇台湖村', '39.833388', '116.632958');
INSERT INTO `t_industrial_park` VALUES ('76', '北京', '北京亦庄经济技术开发区南工业区', '北京市大兴区旧头路', '39.797169', '116.462763');
INSERT INTO `t_industrial_park` VALUES ('77', '北京', '通惠河畔文化创意产业园', '北京市朝阳区高碑店乡西店1118号', '39.904125', '116.510276');
INSERT INTO `t_industrial_park` VALUES ('78', '北京', '北京元墨科技产业园', '北京市沙河镇北二村9号', '40.13733', '116.25918');
INSERT INTO `t_industrial_park` VALUES ('79', '北京', '西下营工业区', '北京市通州区台湖镇西下营村', '39.83155', '116.67122');
INSERT INTO `t_industrial_park` VALUES ('80', '北京', '任庄工业园', '北京市通州区宋庄镇', '39.94078', '116.72751');
INSERT INTO `t_industrial_park` VALUES ('81', '北京', '永乐工业园', '北京市久敬庄临甲88号院', '39.82507', '116.40692');
INSERT INTO `t_industrial_park` VALUES ('82', '北京', '大兴工业区', '北京市大兴区京开路', '39.779562', '116.345821');
INSERT INTO `t_industrial_park` VALUES ('83', '北京', '北京正亿实业园', '北京市李遂镇东营村中街1号', '40.07282', '116.7776');
INSERT INTO `t_industrial_park` VALUES ('84', '北京', '迪昌工业园', '北京市科创三街10号', '39.81208', '116.53303');
INSERT INTO `t_industrial_park` VALUES ('85', '北京', '方和正园工业园', '北京市光华路16号', '39.8546', '116.7219');
INSERT INTO `t_industrial_park` VALUES ('86', '北京', '施园工业园', '北京市通州区张家湾镇施园村张台路', '39.832382', '116.661535');
INSERT INTO `t_industrial_park` VALUES ('87', '北京', '北京房山工业园区', '北京市房山区京周路和顾八路交叉口', '39.699328', '116.015123');
INSERT INTO `t_industrial_park` VALUES ('88', '北京', '东方华强工业区', '北京市鹅房村东街南四条', '39.746288', '116.388427');
INSERT INTO `t_industrial_park` VALUES ('89', '北京', '草厂工业区', '北京市通州区郭县镇', '39.780057', '116.787527');
INSERT INTO `t_industrial_park` VALUES ('90', '北京', '彩园工业区标准厂房工业园', '北京市彩祥东路10', '40.137304', '116.696705');
INSERT INTO `t_industrial_park` VALUES ('91', '北京', '大生庄工业园', '北京市兴隆路', '39.766866', '116.402593');
INSERT INTO `t_industrial_park` VALUES ('92', '北京', '北京市大兴区瀛海镇新村工业园', '北京市新村路甲5号', '39.74385', '116.46906');
INSERT INTO `t_industrial_park` VALUES ('93', '北京', '芦城工业园', '北京市黄鹅路', '39.751836', '116.283252');
INSERT INTO `t_industrial_park` VALUES ('94', '北京', '里二泗工业园', '北京市通州区张家湾镇', '39.85834', '116.72043');
INSERT INTO `t_industrial_park` VALUES ('95', '北京', '平西府工业园', '北京市昌平区北七家镇', '40.11786', '116.42421');
INSERT INTO `t_industrial_park` VALUES ('96', '北京', '天融环保产业园', '北京市昌平区下苑路', '40.218085', '116.235906');
INSERT INTO `t_industrial_park` VALUES ('97', '北京', '兴华工业园', '北京市海淀区', '39.956074', '116.310316');
INSERT INTO `t_industrial_park` VALUES ('98', '北京', '顺义马坡聚源工业区', '北京市马坡镇白马路', '40.17094', '116.65797');
INSERT INTO `t_industrial_park` VALUES ('99', '北京', '渔场皮服工业园', '北京市高桥西里72号', '39.830154', '116.395642');
INSERT INTO `t_industrial_park` VALUES ('100', '北京', '马各庄南工业区', '北京市金盏乡', '40.00335', '116.56884');
INSERT INTO `t_industrial_park` VALUES ('101', '北京', '派克兰帝工业园区', '北京市长子营', '39.688007', '116.594493');
INSERT INTO `t_industrial_park` VALUES ('102', '北京', '张庄工业区', '北京市房山区阎村', '39.72093', '116.09151');
INSERT INTO `t_industrial_park` VALUES ('103', '北京', '东马坊工业园', '北京市海淀区上庄镇上庄路', '40.044355', '116.219481');
INSERT INTO `t_industrial_park` VALUES ('104', '北京', '新北工业区', '北京市朝阳区来广营北路', '40.053834', '116.464317');
INSERT INTO `t_industrial_park` VALUES ('105', '北京', '流村工业园区', '北京市流村镇昌流路', '40.183372', '116.16362');
INSERT INTO `t_industrial_park` VALUES ('106', '北京', '北京仪表仪器工业基地', '北京市盛坊路2', '39.769443', '116.346137');
INSERT INTO `t_industrial_park` VALUES ('107', '北京', '屯佃工业园', '北京市海淀区西北旺镇北清路与上庄路交叉口', '40.067915', '116.21763');
INSERT INTO `t_industrial_park` VALUES ('108', '北京', '团南工业园', '北京市大兴区团忠路', '39.756098', '116.405792');
INSERT INTO `t_industrial_park` VALUES ('109', '北京', '佰富苑工业区', '北京市通州区宋庄镇小堡村', '39.94835', '116.72179');
INSERT INTO `t_industrial_park` VALUES ('110', '北京', '讲礼工业区', '北京市小汤山镇讲礼村', '40.14931', '116.38836');
INSERT INTO `t_industrial_park` VALUES ('111', '北京', '高辛庄工业园区', '北京市通州区宋庄镇高辛庄村', '39.92902', '116.74881');
INSERT INTO `t_industrial_park` VALUES ('112', '北京', '今日吉通工业园', '北京市科创三街17号', '39.81039', '116.52696');
INSERT INTO `t_industrial_park` VALUES ('113', '北京', '中国航天工业园', '北京市中和街20号', '39.80192', '116.51967');
INSERT INTO `t_industrial_park` VALUES ('114', '北京', '八仙庄工业园', '北京市北七家镇', '40.11786', '116.42421');
INSERT INTO `t_industrial_park` VALUES ('115', '北京', '大鲁店工业园', '北京市大鲁店工业园甲1号', '39.83693', '116.59985');
INSERT INTO `t_industrial_park` VALUES ('116', '北京', '姚辛庄工业园', '北京市通州区张家湾姚辛庄京塘路', '39.82097', '116.7729');
INSERT INTO `t_industrial_park` VALUES ('117', '北京', '姜场工业区', '北京市大兴区', '39.755477', '116.337733');
INSERT INTO `t_industrial_park` VALUES ('118', '北京', '小辛峰工业区', '北京市昌平区小汤山镇崔村', '40.21831', '116.35555');
INSERT INTO `t_industrial_park` VALUES ('119', '北京', '玉德顺大米加工园区', '北京市通州区', '39.902486', '116.658603');
INSERT INTO `t_industrial_park` VALUES ('120', '北京', '富兴地产康盛工业园', '北京市通州区康定街11号', '39.77451', '116.53984');
INSERT INTO `t_industrial_park` VALUES ('121', '北京', '怡乐新工业园', '北京市大兴区三太路', '39.753728', '116.469593');
INSERT INTO `t_industrial_park` VALUES ('122', '北京', '后夏公庄工业园', '北京市通州区宋庄镇京榆旧线', '39.943782', '116.727501');
INSERT INTO `t_industrial_park` VALUES ('123', '北京', '利达兴工业园', '北京市经海四路15号', '39.81543', '116.53218');
INSERT INTO `t_industrial_park` VALUES ('124', '北京', '北京市政工业基地', '北京市昌平区南口镇南雁路', '40.225317', '116.110112');
INSERT INTO `t_industrial_park` VALUES ('125', '北京', '召里工业区', '北京市通州区潞城镇召里路', '39.92385', '116.731587');
INSERT INTO `t_industrial_park` VALUES ('126', '北京', '中国土产蓄产进出口总公司三利工业园', '北京市工业开发区盛坊路1号', '39.76994', '116.34435');
INSERT INTO `t_industrial_park` VALUES ('127', '北京', '北京路通洪运工业园', '北京市定福庄乙10号', '39.91748', '116.56426');
INSERT INTO `t_industrial_park` VALUES ('128', '北京', '北京化工区', '北京市朝阳区化工路 ', '39.86369', '116.515107');
INSERT INTO `t_industrial_park` VALUES ('129', '北京', '燕山石化区', '北京市房山区燕山', '39.710742', '116.061881');
INSERT INTO `t_industrial_park` VALUES ('130', '天津', '天津新技术产业园区', '', '39.091389', '117.103551');
INSERT INTO `t_industrial_park` VALUES ('131', '天津', '天津经济技术开发区', '', '39.037274', '117.690734');
INSERT INTO `t_industrial_park` VALUES ('132', '天津', '天津港保税区', '', '39.01446', '117.75827');
INSERT INTO `t_industrial_park` VALUES ('133', '天津', '天津出口加工区 ', '', '39.07152', '117.74329');
INSERT INTO `t_industrial_park` VALUES ('134', '天津', '天津空港加工区', '', '39.144186', '117.399747');
INSERT INTO `t_industrial_park` VALUES ('135', '天津', '天津市大港经济技术开发区', '', '39.03301', '117.68111');
INSERT INTO `t_industrial_park` VALUES ('136', '天津', '天津市东丽经济技术开发区 ', '', '39.071335', '117.379195');
INSERT INTO `t_industrial_park` VALUES ('137', '天津', '天津市海洋石化科技园区', '', '38.81954', '117.471975');
INSERT INTO `t_industrial_park` VALUES ('138', '天津', '天津市军粮城产业园区', '', '39.038693', '117.453873');
INSERT INTO `t_industrial_park` VALUES ('139', '天津', '天津市武清经济开发区', '', '39.079847', '117.354243');
INSERT INTO `t_industrial_park` VALUES ('140', '天津', '天津市津南经济技术开发区', '', '39.037274', '117.690734');
INSERT INTO `t_industrial_park` VALUES ('141', '天津', '天津市大王古经济开发区', '', '39.566305', '116.823799');
INSERT INTO `t_industrial_park` VALUES ('142', '天津', '天津市八里台工业园区', '', '38.95305', '117.34367');
INSERT INTO `t_industrial_park` VALUES ('143', '天津', '天津市福源经济开发区', '', '39.359629', '117.197411');
INSERT INTO `t_industrial_park` VALUES ('144', '天津', '天津市鑫达电子工业园区', '', '38.92845', '117.37648');
INSERT INTO `t_industrial_park` VALUES ('145', '天津', '天津市天宝经济技术开发区', '', '39.037274', '117.690734');
INSERT INTO `t_industrial_park` VALUES ('146', '天津', '天津市西青经济开发区', '', '38.984246', '117.262005');
INSERT INTO `t_industrial_park` VALUES ('147', '天津', '天津市京津新城经济技术开发区', '', '39.037274', '117.690734');
INSERT INTO `t_industrial_park` VALUES ('148', '天津', '天津微电子工业区', '', '39.000018', '117.241476');
INSERT INTO `t_industrial_park` VALUES ('149', '天津', '天津市宁河经济技术开发区', '', '39.037274', '117.690734');
INSERT INTO `t_industrial_park` VALUES ('150', '天津', '天津市中北工业园区', '', '39.11001', '117.06248');
INSERT INTO `t_industrial_park` VALUES ('151', '天津', '天津市潘庄工业园区', '', '39.286155', '117.396345');
INSERT INTO `t_industrial_park` VALUES ('152', '天津', '天津市北辰经济技术开发区', '', '39.037274', '117.690734');
INSERT INTO `t_industrial_park` VALUES ('153', '天津', '天津市静海经济技术开发区', '', '39.037274', '117.690734');
INSERT INTO `t_industrial_park` VALUES ('154', '天津', '天津市双口工业园区', '', '39.24033', '117.023915');
INSERT INTO `t_industrial_park` VALUES ('155', '天津', '天津市子牙环保产业园区', '', '38.842857', '116.78113');
INSERT INTO `t_industrial_park` VALUES ('156', '天津', '天津市海洋石油工业园区', '', '38.989318', '117.711506');
INSERT INTO `t_industrial_park` VALUES ('157', '天津', '天津市蓟县经济技术开发区', '', '39.037274', '117.690734');
INSERT INTO `t_industrial_park` VALUES ('158', '天津', '天津化学工业区', '', '39.213014', '117.783462');
INSERT INTO `t_industrial_park` VALUES ('159', '天津', '天津逸仙科学工业区', '', '39.404317', '117.031151');
INSERT INTO `t_industrial_park` VALUES ('160', '天津', '天津市茶淀工业园区', '', '38.95305', '117.34367');
INSERT INTO `t_industrial_park` VALUES ('161', '上海', '上海外高桥保税区', '', '31.33125', '121.601841');
INSERT INTO `t_industrial_park` VALUES ('162', '上海', '上海徐泾工业园区', '', '31.19182', '121.27306');
INSERT INTO `t_industrial_park` VALUES ('163', '上海', '上海堡镇工业园区', '', '31.737076', '121.223964');
INSERT INTO `t_industrial_park` VALUES ('164', '上海', '上海练塘工业园区', '', '31.00032', '121.06398');
INSERT INTO `t_industrial_park` VALUES ('165', '上海', '上海商榻工业园区 ', '', '31.231706', '121.472644');
INSERT INTO `t_industrial_park` VALUES ('166', '上海', '上海张松仓储工业区 ', '', '31.087088', '121.25534');
INSERT INTO `t_industrial_park` VALUES ('167', '上海', '上海茸北工业区 ', '', '31.087088', '121.25534');
INSERT INTO `t_industrial_park` VALUES ('168', '上海', '上海徐行工业园区', '', '31.422083', '121.28289');
INSERT INTO `t_industrial_park` VALUES ('169', '上海', '上海枫泾工业园区', '', '30.88812', '121.04608');
INSERT INTO `t_industrial_park` VALUES ('170', '上海', '上海普陀长征工业区', '', '31.23097', '121.37178');
INSERT INTO `t_industrial_park` VALUES ('171', '上海', '上海顾村工业园区', '', '31.370819', '121.406209');
INSERT INTO `t_industrial_park` VALUES ('172', '上海', '上海漕河泾开发区闵行高科技园 ', '', '31.169629', '121.392777');
INSERT INTO `t_industrial_park` VALUES ('173', '上海', '上海华新工业园 ', '', '31.231706', '121.472644');
INSERT INTO `t_industrial_park` VALUES ('174', '上海', '上海重固工业园区 ', '', '31.19028', '121.18425');
INSERT INTO `t_industrial_park` VALUES ('175', '上海', '上海闵行电气工业园区', '', '30.99533', '121.36381');
INSERT INTO `t_industrial_park` VALUES ('176', '上海', '上海强民经济城', '', '31.03384', '121.21843');
INSERT INTO `t_industrial_park` VALUES ('177', '上海', '上海松江高科技园区', '', '31.093304', '121.319692');
INSERT INTO `t_industrial_park` VALUES ('178', '上海', '上海火炬高技术产业开发中心', '', '31.32189', '121.14684');
INSERT INTO `t_industrial_park` VALUES ('179', '上海', '上海共康都市工业园区 ', '', '31.25855', '121.45289');
INSERT INTO `t_industrial_park` VALUES ('180', '上海', '上海漕河泾开发区新经济园 ', '', '31.2683', '121.54465');
INSERT INTO `t_industrial_park` VALUES ('181', '上海', '上海海港综合经济开发区 ', '', '30.892185', '121.741929');
INSERT INTO `t_industrial_park` VALUES ('182', '上海', '上海南汇工业园区', '', '31.03643', '121.72212');
INSERT INTO `t_industrial_park` VALUES ('183', '上海', '上海有色高科技工业园区 ', '', '31.195619', '121.433491');
INSERT INTO `t_industrial_park` VALUES ('184', '上海', '上海朱家角工业园区 ', '', '31.10839', '121.051929');
INSERT INTO `t_industrial_park` VALUES ('185', '上海', '上海赵巷工业园', '', '31.15713', '121.2057');
INSERT INTO `t_industrial_park` VALUES ('186', '上海', '上海市市北工业新区', '', '31.231706', '121.472644');
INSERT INTO `t_industrial_park` VALUES ('187', '上海', '上海嘉定工业区 ', '', '31.35805', '121.23818');
INSERT INTO `t_industrial_park` VALUES ('188', '上海', '上海人本工业园', '', '31.231706', '121.472644');
INSERT INTO `t_industrial_park` VALUES ('189', '上海', '上海化学工业区奉贤分区', '', '30.82452', '121.46304');
INSERT INTO `t_industrial_park` VALUES ('190', '上海', '上海纺织工业区', '', '31.087088', '121.25534');
INSERT INTO `t_industrial_park` VALUES ('191', '上海', '上海大麦湾工业区', '', '31.031901', '121.608835');
INSERT INTO `t_industrial_park` VALUES ('192', '上海', '上海月浦工业园区', '', '31.46028', '121.39461');
INSERT INTO `t_industrial_park` VALUES ('193', '上海', '上海罗店工业园区 ', '', '31.41761', '121.34846');
INSERT INTO `t_industrial_park` VALUES ('194', '上海', '上海宝山城市工业园区 ', '', '31.39097', '121.48791');
INSERT INTO `t_industrial_park` VALUES ('195', '上海', '上海青浦工业园区 ', '', '31.15153', '121.15164');
INSERT INTO `t_industrial_park` VALUES ('196', '上海', '上海金山工业区 ', '', '30.848198', '121.345944');
INSERT INTO `t_industrial_park` VALUES ('197', '上海', '上海紫竹科学园区', '', '31.02343', '121.45082');
INSERT INTO `t_industrial_park` VALUES ('198', '上海', '上海市北工业新区 ', '', '31.231706', '121.472644');
INSERT INTO `t_industrial_park` VALUES ('199', '上海', '上海康桥工业区 ', '', '31.13106', '121.552224');
INSERT INTO `t_industrial_park` VALUES ('200', '上海', '上海市松江工业区', '', '31.047295', '121.16457');
INSERT INTO `t_industrial_park` VALUES ('201', '上海', '上海市工业综合开发区 ', '', '30.952913', '121.451953');
INSERT INTO `t_industrial_park` VALUES ('202', '上海', '上海崇明工业园区', '', '31.63909', '121.40117');
INSERT INTO `t_industrial_park` VALUES ('203', '上海', '上海化学工业区', '', '30.81863', '121.46506');
INSERT INTO `t_industrial_park` VALUES ('204', '上海', '上海市嘉定工业区', '', '31.35805', '121.23818');
INSERT INTO `t_industrial_park` VALUES ('205', '上海', '上海市星火开发区', '', '30.855394', '121.560502');
INSERT INTO `t_industrial_park` VALUES ('206', '上海', '上海莘庄工业区', '', '31.088954', '121.382162');
INSERT INTO `t_industrial_park` VALUES ('207', '上海', '上海国际汽车城零部件配套工业园区', '', '31.31383', '121.20358');
INSERT INTO `t_industrial_park` VALUES ('208', '上海', '上海宝山工业园区 ', '', '25.048146', '102.88799');
INSERT INTO `t_industrial_park` VALUES ('209', '上海', '上海漕河泾出口加工区', '', '31.10333', '121.50532');
INSERT INTO `t_industrial_park` VALUES ('210', '上海', '上海临港产业区', '', '30.897562', '121.817008');
INSERT INTO `t_industrial_park` VALUES ('211', '上海', '上海漕河泾开发区松江高科技园', '', '31.09259', '121.31885');
INSERT INTO `t_industrial_park` VALUES ('212', '上海', '上海漕河泾开发区新经济园', '', '31.2683', '121.54465');
INSERT INTO `t_industrial_park` VALUES ('213', '上海', '上海虹桥经济技术开发区 ', '', '31.003687', '121.378746');
INSERT INTO `t_industrial_park` VALUES ('214', '上海', '上海闵行出口加工区', '', '30.95287', '121.43291');
INSERT INTO `t_industrial_park` VALUES ('215', '上海', '上海临港重装备产业区 ', '', '31.231706', '121.472644');
INSERT INTO `t_industrial_park` VALUES ('216', '上海', '上海闵行经济技术开发区 ', '', '31.004593', '121.377149');
INSERT INTO `t_industrial_park` VALUES ('217', '上海', '上海陆家嘴金融贸易区 ', '', '31.230026', '121.533228');
INSERT INTO `t_industrial_park` VALUES ('218', '上海', '上海松江出口加工区 ', '', '31.00899', '121.29146');
INSERT INTO `t_industrial_park` VALUES ('219', '上海', '上海青浦出口加工区 ', '', '31.18773', '121.15171');
INSERT INTO `t_industrial_park` VALUES ('220', '上海', '上海洋山保税港区 ', '', '30.87726', '121.88817');
INSERT INTO `t_industrial_park` VALUES ('221', '上海', '上海金桥出口加工区', '', '31.25389', '121.61725');
INSERT INTO `t_industrial_park` VALUES ('222', '上海', '上海市张江高科技园区', '', '31.242838', '121.70357');
INSERT INTO `t_industrial_park` VALUES ('223', '上海', '青浦工业园区 ', '', '31.17199', '121.09656');
INSERT INTO `t_industrial_park` VALUES ('224', '上海', '上海孙桥现代农业园区', '', '31.164245', '121.61926');
INSERT INTO `t_industrial_park` VALUES ('225', '上海', '上海鸿越实业有限公司工业园', '', '31.231706', '121.472644');
INSERT INTO `t_industrial_park` VALUES ('226', '上海', '上海枫泾经济区', '', '30.89022', '121.0308');
INSERT INTO `t_industrial_park` VALUES ('227', '上海', '上海平安经济园区 ', '', '30.898158', '121.731977');
INSERT INTO `t_industrial_park` VALUES ('228', '上海', '上海南汇惠南新城开发区', '', '31.253281', '121.616714');
INSERT INTO `t_industrial_park` VALUES ('229', '上海', '上海富成经济区', '', '31.039825', '121.736617');
INSERT INTO `t_industrial_park` VALUES ('230', '上海', '上海新五厍经济城', '', '30.950915', '121.141075');
INSERT INTO `t_industrial_park` VALUES ('231', '上海', '上海施惠特综合经济区 ', '', '31.036076', '121.247127');
INSERT INTO `t_industrial_park` VALUES ('232', '上海', '上海振华经济小区', '', '31.01314', '121.21761');
INSERT INTO `t_industrial_park` VALUES ('233', '上海', '上海新佘山经济园区', '', '31.26633', '121.54719');
INSERT INTO `t_industrial_park` VALUES ('234', '上海', '上海李塔经济城', '', '30.970149', '121.181083');
INSERT INTO `t_industrial_park` VALUES ('235', '上海', '上海佘山私营经济开发区', '', '31.189693', '121.438667');
INSERT INTO `t_industrial_park` VALUES ('236', '上海', '上海富荣私营经济区', '', '31.231706', '121.472644');
INSERT INTO `t_industrial_park` VALUES ('237', '上海', '上海富民仓桥经济城', '', '31.000067', '121.210011');
INSERT INTO `t_industrial_park` VALUES ('238', '上海', '上海强民经济城', '', '31.03384', '121.21843');
INSERT INTO `t_industrial_park` VALUES ('239', '上海', '上海新闵经济区', '', '36.743425', '119.142507');
INSERT INTO `t_industrial_park` VALUES ('240', '上海', '上海良友经济开发区', '', '31.0027', '121.24217');
INSERT INTO `t_industrial_park` VALUES ('241', '上海', '上海国际中小企业城', '', '31.02453', '121.30517');
INSERT INTO `t_industrial_park` VALUES ('242', '上海', '上海国际中小企业城', '', '31.02453', '121.30517');
INSERT INTO `t_industrial_park` VALUES ('243', '上海', '上海都城经济园区', '', '31.01897', '121.25944');
INSERT INTO `t_industrial_park` VALUES ('244', '上海', '上海兴源经济开发区', '', '31.004384', '121.373019');
INSERT INTO `t_industrial_park` VALUES ('245', '上海', '上海惠民经济开发区 ', '', '31.004384', '121.373019');
INSERT INTO `t_industrial_park` VALUES ('246', '上海', '上海泗泾私营经济开发区', '', '31.115759', '121.273026');
INSERT INTO `t_industrial_park` VALUES ('247', '上海', '上海百颗星私营经济区', '', '31.231706', '121.472644');
INSERT INTO `t_industrial_park` VALUES ('248', '上海', '上海莘莘学子创业园', '', '31.006694', '121.30233');
INSERT INTO `t_industrial_park` VALUES ('249', '上海', '上海民发经济城', '', '30.933726', '121.287376');
INSERT INTO `t_industrial_park` VALUES ('250', '上海', '上海久富经济开发区', '', '31.112879', '121.312192');
INSERT INTO `t_industrial_park` VALUES ('251', '上海', '上海小昆山经济区', '', '31.039215', '121.212635');
INSERT INTO `t_industrial_park` VALUES ('252', '上海', '上海松新经济城', '', '31.231706', '121.472644');
INSERT INTO `t_industrial_park` VALUES ('253', '上海', '上海大昆经济开发区', '', '29.586981', '120.857003');
INSERT INTO `t_industrial_park` VALUES ('254', '上海', '上海松江新区经济城 ', '', '31.231706', '121.472644');
INSERT INTO `t_industrial_park` VALUES ('255', '上海', '上海华生综合经济区', '', '31.234402', '121.409645');
INSERT INTO `t_industrial_park` VALUES ('256', '上海', '上海天马私营经济开发区 ', '', '31.31727', '121.354082');
INSERT INTO `t_industrial_park` VALUES ('257', '上海', '上海颂园经济城', '', '31.231706', '121.472644');
INSERT INTO `t_industrial_park` VALUES ('258', '上海', '上海成功经济小区', '', '30.99881', '121.24503');
INSERT INTO `t_industrial_park` VALUES ('259', '上海', '上海新发经济城', '', '31.231706', '121.472644');
INSERT INTO `t_industrial_park` VALUES ('260', '上海', '海富甲经济区', '', '36.743425', '119.142507');
INSERT INTO `t_industrial_park` VALUES ('261', '上海', '上海绿色科技园区', '', '31.14748', '121.11816');
INSERT INTO `t_industrial_park` VALUES ('262', '上海', '上海中纺科技城', '', '31.1524', '121.12711');
INSERT INTO `t_industrial_park` VALUES ('263', '上海', '上海福泉山经济开发区 ', '', '31.20028', '121.17726');
INSERT INTO `t_industrial_park` VALUES ('264', '上海', '上海白鹤经济城 ', '', '31.25799', '121.14465');
INSERT INTO `t_industrial_park` VALUES ('265', '上海', '上海腾富民营经济城 ', '', '31.220441', '121.450464');
INSERT INTO `t_industrial_park` VALUES ('266', '上海', '上海宏城经济区', '', '31.231706', '121.472644');
INSERT INTO `t_industrial_park` VALUES ('267', '上海', '上海富民经济区', '', '30.97147', '121.03158');
INSERT INTO `t_industrial_park` VALUES ('268', '上海', '上海新城经济区', '', '31.04957', '121.19683');
INSERT INTO `t_industrial_park` VALUES ('269', '上海', '上海徐泾经济城', '', '31.17681', '121.28655');
INSERT INTO `t_industrial_park` VALUES ('270', '上海', '上海龙洲经济城', '', '31.231706', '121.472644');
INSERT INTO `t_industrial_park` VALUES ('271', '上海', '上海盈港经济城', '', '31.231706', '121.472644');
INSERT INTO `t_industrial_park` VALUES ('272', '上海', '上海西郊经济技术开发区', '', '38.02914', '114.6882');
INSERT INTO `t_industrial_park` VALUES ('273', '上海', '上海天府经济区', '', '31.231706', '121.472644');
INSERT INTO `t_industrial_park` VALUES ('274', '上海', '上海太阳岛经济发展城', '', '31.21215', '121.45724');
INSERT INTO `t_industrial_park` VALUES ('275', '上海', '上海西部经济城', '', '31.22797', '121.46841');
INSERT INTO `t_industrial_park` VALUES ('276', '上海', '上海万事发经济开发区', '', '31.004384', '121.373019');
INSERT INTO `t_industrial_park` VALUES ('277', '上海', '上海城郊经济区', '', '31.231706', '121.472644');
INSERT INTO `t_industrial_park` VALUES ('278', '上海', '上海市白鹤工业园区', '', '31.24328', '121.13852');
INSERT INTO `t_industrial_park` VALUES ('279', '上海', '上海淀山湖经济城', '', '31.07162', '120.90745');
INSERT INTO `t_industrial_park` VALUES ('280', '上海', '上海浦东北蔡镇', '', '31.18187', '121.55463');
INSERT INTO `t_industrial_park` VALUES ('281', '上海', '上海资深专家高科技园区', '', '31.13153', '121.30249');
INSERT INTO `t_industrial_park` VALUES ('282', '上海', '上海市五角场高新技术产业园区', '', '31.27472', '121.51898');
INSERT INTO `t_industrial_park` VALUES ('283', '上海', '上海国际家用纺织品产业园', '', '31.26406', '121.53826');
INSERT INTO `t_industrial_park` VALUES ('284', '上海', '上海闸北彭浦经济城', '', '31.27747', '121.42777');
INSERT INTO `t_industrial_park` VALUES ('285', '上海', '同济大学国家大学科技园', '', '31.29424', '121.49646');
INSERT INTO `t_industrial_park` VALUES ('286', '上海', '上海师惠经济园区', '', '30.908538', '121.630153');
INSERT INTO `t_industrial_park` VALUES ('287', '上海', '上海徐行经济城', '', '31.422083', '121.28289');
INSERT INTO `t_industrial_park` VALUES ('288', '上海', '上海希望经济城', '', '31.25964', '121.44911');
INSERT INTO `t_industrial_park` VALUES ('289', '上海', '上海沪嘉经济发展区', '', '31.21431', '121.47478');
INSERT INTO `t_industrial_park` VALUES ('290', '上海', '上海新望经济城', '', '22.82402', '108.320004');
INSERT INTO `t_industrial_park` VALUES ('291', '上海', '上海南翔经济城', '', '31.29154', '121.32512');
INSERT INTO `t_industrial_park` VALUES ('292', '上海', '上海大众经济城 ', '', '31.29654', '121.168333');
INSERT INTO `t_industrial_park` VALUES ('293', '上海', '上海古漪园经济城', '', '31.29213', '121.31702');
INSERT INTO `t_industrial_park` VALUES ('294', '上海', '上海菊园经济城', '', '31.396804', '121.249929');
INSERT INTO `t_industrial_park` VALUES ('295', '上海', '上海蓝天经济城', '', '31.29518', '121.32393');
INSERT INTO `t_industrial_park` VALUES ('296', '上海', '上海宝山大场经济发展区', '', '31.316005', '121.38194');
INSERT INTO `t_industrial_park` VALUES ('297', '上海', '上海淞南经济发展区', '', '31.29924', '121.44599');
INSERT INTO `t_industrial_park` VALUES ('298', '上海', '上海杨行经济开发区', '', '31.397035', '121.481253');
INSERT INTO `t_industrial_park` VALUES ('299', '上海', '上海市杨行工业园区', '', '31.40368', '121.41332');
INSERT INTO `t_industrial_park` VALUES ('300', '上海', '上海宝山经济发展区', '', '31.46658', '121.32466');
INSERT INTO `t_industrial_park` VALUES ('301', '上海', '上海刘行经济发展区', '', '31.354768', '121.368532');
INSERT INTO `t_industrial_park` VALUES ('302', '上海', '上海石洞口经济开发区', '', '31.453914', '121.406221');
INSERT INTO `t_industrial_park` VALUES ('303', '上海', '上海横泰经济发展区 ', '', '31.21431', '121.47478');
INSERT INTO `t_industrial_park` VALUES ('304', '上海', '上海共康经济发展区', '', '31.29924', '121.44599');
INSERT INTO `t_industrial_park` VALUES ('305', '上海', '上海丰翔经济园区', '', '31.312794', '121.345027');
INSERT INTO `t_industrial_park` VALUES ('306', '上海', '上海财富天地企业园 ', '', '31.0717', '121.41705');
INSERT INTO `t_industrial_park` VALUES ('307', '上海', '上海顾村工业园区', '', '31.370819', '121.406209');
INSERT INTO `t_industrial_park` VALUES ('308', '上海', '上海有色高科技工业园区', '', '31.195619', '121.433491');
INSERT INTO `t_industrial_park` VALUES ('309', '上海', '上海大麦湾工业区 ', '', '31.031901', '121.608835');
INSERT INTO `t_industrial_park` VALUES ('310', '上海', '上海青浦工业园区 ', '', '31.15153', '121.15164');
INSERT INTO `t_industrial_park` VALUES ('311', '上海', '上海金陵(松江)工业园', '', '31.03218', '121.233185');
INSERT INTO `t_industrial_park` VALUES ('312', '上海', '上海恒鲁现代产业园', '', '31.2858', '121.65346');
INSERT INTO `t_industrial_park` VALUES ('313', '上海', '上海宝山城市工业园区（南区） ', '', '31.39097', '121.48791');
INSERT INTO `t_industrial_park` VALUES ('314', '上海', '上海紫江科技产业园 ', '', '31.172104', '121.401332');
INSERT INTO `t_industrial_park` VALUES ('315', '上海', '上海科技京城', '', '31.2393', '121.47844');
INSERT INTO `t_industrial_park` VALUES ('316', '上海', '上海理工大学国家大学科技园', '', '31.29424', '121.49646');
INSERT INTO `t_industrial_park` VALUES ('317', '上海', '上海武宁科技园', '', '31.231706', '121.472644');
INSERT INTO `t_industrial_park` VALUES ('318', '上海', '上海国际医学园', '', '39.07427', '117.28708');
INSERT INTO `t_industrial_park` VALUES ('319', '上海', '上海浦南机电工业开发区', '', '31.1228', '121.32219');
INSERT INTO `t_industrial_park` VALUES ('320', '上海', '上海台商工业园区', '', '31.38753', '121.497617');
INSERT INTO `t_industrial_park` VALUES ('321', '上海', '上海希望经济城 ', '', '31.25964', '121.44911');
INSERT INTO `t_industrial_park` VALUES ('322', '上海', '上海松江茸北工业区', '', '31.087088', '121.25534');
INSERT INTO `t_industrial_park` VALUES ('323', '上海', '上海国际包装印刷城', '', '31.27076', '121.3794');
INSERT INTO `t_industrial_park` VALUES ('324', '上海', '上海普陀长征工业区 ', '', '31.23097', '121.37178');
INSERT INTO `t_industrial_park` VALUES ('325', '上海', '樽轩城市工业园区', '', '31.22316', '121.28084');
INSERT INTO `t_industrial_park` VALUES ('326', '上海', '浦江智谷', '', '31.08041', '121.52549');
INSERT INTO `t_industrial_park` VALUES ('327', '上海', '润和国际总部园', '', '31.188783', '121.606707');
INSERT INTO `t_industrial_park` VALUES ('328', '上海', '第一上海中心', '', '31.18554', '121.58503');
INSERT INTO `t_industrial_park` VALUES ('329', '上海', '上海市环保科技工业园', '', '31.231706', '121.472644');
INSERT INTO `t_industrial_park` VALUES ('330', '上海', '东县经济开发区东安工业区', '', '23.059608', '113.527212');
INSERT INTO `t_industrial_park` VALUES ('331', '上海', '上海恒鲁现代产业园 ', '', '31.2858', '121.65346');
INSERT INTO `t_industrial_park` VALUES ('332', '上海', '上海浦东新区', '', '31.245944', '121.567706');
INSERT INTO `t_industrial_park` VALUES ('333', '上海', '上海奉城化学工业园 ', '', '31.14233', '121.426455');
INSERT INTO `t_industrial_park` VALUES ('334', '上海', '上海马汤工业园区', '', '31.231706', '121.472644');
INSERT INTO `t_industrial_park` VALUES ('335', '上海', '静安都市工业园区', '', '31.239441', '121.451119');
INSERT INTO `t_industrial_park` VALUES ('336', '广东', '珠澳跨境工业区', '', '29.267519', '88.885148');
INSERT INTO `t_industrial_park` VALUES ('337', '广东', '东莞东部工业园区', '', '22.981112', '114.054474');
INSERT INTO `t_industrial_park` VALUES ('338', '广东', '佛山高明沧江工业园区', '', '22.889023', '112.83622');
INSERT INTO `t_industrial_park` VALUES ('339', '广东', '佛山南海工业园区', '', '23.248235', '113.00698');
INSERT INTO `t_industrial_park` VALUES ('340', '广东', '佛山三水工业园区', '', '23.248235', '113.00698');
INSERT INTO `t_industrial_park` VALUES ('341', '广东', '佛山顺德工业园区', '', '23.216545', '112.920865');
INSERT INTO `t_industrial_park` VALUES ('342', '广东', '广州白云工业园区', '', '23.38915', '113.44267');
INSERT INTO `t_industrial_park` VALUES ('343', '广东', '广州云埔工业园区', '', '23.146785', '113.54022');
INSERT INTO `t_industrial_park` VALUES ('344', '广东', '广东增城工业园区', '', '23.15255', '113.6287');
INSERT INTO `t_industrial_park` VALUES ('345', '广东', '惠州工业园区', '', '23.079404', '114.412599');
INSERT INTO `t_industrial_park` VALUES ('346', '广东', '台山广海湾工业园区', '', '22.454661', '112.779881');
INSERT INTO `t_industrial_park` VALUES ('347', '广东', '揭阳榕城工业园区', '', '23.54209', '116.40615');
INSERT INTO `t_industrial_park` VALUES ('348', '广东', '汕头金平工业园区', '', '23.39429', '116.68393');
INSERT INTO `t_industrial_park` VALUES ('349', '广东', '汕头龙湖工业园区', '', '23.375612', '116.71877');
INSERT INTO `t_industrial_park` VALUES ('350', '广东', '韶关工业园区', '', '31.299379', '120.619585');
INSERT INTO `t_industrial_park` VALUES ('351', '广东', '始兴工业园区', '', '24.96486', '114.03628');
INSERT INTO `t_industrial_park` VALUES ('352', '广东', '阳江工业园区', '', '29.36408', '86.88061');
INSERT INTO `t_industrial_park` VALUES ('353', '广东', '云浮工业园区', '', '22.70945', '112.20601');
INSERT INTO `t_industrial_park` VALUES ('354', '广东', '湛江临港工业园区', '', '21.151263', '110.377697');
INSERT INTO `t_industrial_park` VALUES ('355', '广东', '肇庆工业园区', '', '23.285673', '112.846141');
INSERT INTO `t_industrial_park` VALUES ('356', '广东', '中山工业园区', '', '22.35656', '113.47675');
INSERT INTO `t_industrial_park` VALUES ('357', '广东', '珠海富山工业园区', '', '22.14078', '113.14279');
INSERT INTO `t_industrial_park` VALUES ('358', '广东', '珠海金湾联港工业园区', '', '22.02342', '113.22617');
INSERT INTO `t_industrial_park` VALUES ('359', '广东', '中联环保电镀工业园', '', '36.02686', '120.18159');
INSERT INTO `t_industrial_park` VALUES ('360', '广东', '深圳科技工业园', '', '22.54393', '113.94636');
INSERT INTO `t_industrial_park` VALUES ('361', '广东', '三灶科技工业园', '', '22.045907', '113.339232');
INSERT INTO `t_industrial_park` VALUES ('362', '广东', '南屏科技工业园', '', '22.216936', '113.476982');
INSERT INTO `t_industrial_park` VALUES ('363', '广东', '南海国家生态工业示范园', '', '23.08617', '112.89186');
INSERT INTO `t_industrial_park` VALUES ('364', '广东', '茂名石化工业区', '', '21.58143', '110.97176');
INSERT INTO `t_industrial_park` VALUES ('365', '广东', '龙溪健达电镀工业园', '', '23.174075', '113.50431');
INSERT INTO `t_industrial_park` VALUES ('366', '广东', '惠州大亚湾石油化学工业区', '', '36.075575', '120.38513');
INSERT INTO `t_industrial_park` VALUES ('367', '广东', '江城区埠场环保工业城', '', '21.785734', '111.938272');
INSERT INTO `t_industrial_park` VALUES ('368', '广东', '广东省英德市英红工业园区', '', '24.305955', '113.41239');
INSERT INTO `t_industrial_park` VALUES ('369', '广东', '清远华侨工业园', '', '24.21976', '113.67473');
INSERT INTO `t_industrial_park` VALUES ('370', '广东', '东莞市城区科技工业园', '', '22.91083', '113.85393');

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
-- Table structure for t_rubbish_recycling
-- ----------------------------
DROP TABLE IF EXISTS `t_rubbish_recycling`;
CREATE TABLE `t_rubbish_recycling` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `city` varchar(20) NOT NULL COMMENT '所属区域',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `address` varchar(500) NOT NULL COMMENT '详细地址',
  `lat` varchar(50) NOT NULL COMMENT '纬度',
  `lng` varchar(50) NOT NULL COMMENT '经度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=511 DEFAULT CHARSET=utf8 COMMENT='垃圾回收站';

-- ----------------------------
-- Records of t_rubbish_recycling
-- ----------------------------
INSERT INTO `t_rubbish_recycling` VALUES ('1', '北京', '北京大学垃圾中转站', '北京市海淀区颐和园路5号-45-甲1层', '39.9946', '116.30454');
INSERT INTO `t_rubbish_recycling` VALUES ('2', '北京', '北京欣园小区垃圾中转站', '北京市丰台区丰台北路22', '39.866465', '116.304295');
INSERT INTO `t_rubbish_recycling` VALUES ('3', '北京', '葆台垃圾中转站', '北京市丰台区居库路', '39.802948', '116.282672');
INSERT INTO `t_rubbish_recycling` VALUES ('4', '北京', '南苑垃圾中转站', '北京市丰台区五爱屯东街', '39.799686', '116.380282');
INSERT INTO `t_rubbish_recycling` VALUES ('5', '北京', '槐房垃圾中转站', '北京市丰台区槐房路', '39.81502', '116.377591');
INSERT INTO `t_rubbish_recycling` VALUES ('6', '北京', '榆树庄垃圾中转站', '北京市丰台区民村路', '39.831118', '116.260466');
INSERT INTO `t_rubbish_recycling` VALUES ('7', '北京', '石榴庄垃圾中转站', '北京市丰台区石榴庄南街和南顶路的交叉口', '39.841506', '116.417054');
INSERT INTO `t_rubbish_recycling` VALUES ('8', '北京', '大瓦窑垃圾中转站', '北京市丰台区大瓦窑北路', '39.867791', '116.245367');
INSERT INTO `t_rubbish_recycling` VALUES ('9', '北京', '将台洼村垃圾中转站', '北京市朝阳区七棵树路', '39.963158', '116.524436');
INSERT INTO `t_rubbish_recycling` VALUES ('10', '北京', '高立庄垃圾中转站', '北京市丰台区樊羊路', '39.815051', '116.310093');
INSERT INTO `t_rubbish_recycling` VALUES ('11', '北京', '丰台区环境卫生服务中心朱云路垃圾中转站', '北京市丰台区云岗路122', '39.804742', '116.176711');
INSERT INTO `t_rubbish_recycling` VALUES ('12', '北京', '丰台区环境卫生服务中心小屯垃圾中转站', '北京市丰台区大瓦窑北路', '39.867791', '116.245367');
INSERT INTO `t_rubbish_recycling` VALUES ('13', '北京', '小红门垃圾中转站', '北京市大兴区博大路', '39.8293', '116.478461');
INSERT INTO `t_rubbish_recycling` VALUES ('14', '北京', '武夷物业管理有限公司垃圾中转站', '通州区水仙东路', '39.916264', '116.695291');
INSERT INTO `t_rubbish_recycling` VALUES ('15', '北京', '丰台区环境卫生服务中心西铁营垃圾中转站', '北京市丰台区丰台区南三环西路', '39.851281', '116.353401');
INSERT INTO `t_rubbish_recycling` VALUES ('16', '北京', '小武基垃圾转运站', '北京市东南郊工业区,朝阳区十八里乡', '39.83286', '116.24526');
INSERT INTO `t_rubbish_recycling` VALUES ('17', '北京', '北京环卫集团四清分公司大屯垃圾转运站', '北京市朝阳区大屯路辛店甲一号', '40.01104', '116.42353');
INSERT INTO `t_rubbish_recycling` VALUES ('18', '北京', '马家楼转运站', '北京市丰台区花乡马家楼桥东侧', '39.83303', '116.343777');
INSERT INTO `t_rubbish_recycling` VALUES ('19', '北京', '北京市昌平区马池口镇垃圾中转站', '北京市昌平区', '40.218085', '116.235906');
INSERT INTO `t_rubbish_recycling` VALUES ('20', '北京', '永顺镇垃圾转运站', '北京市通州区', '39.902486', '116.658603');
INSERT INTO `t_rubbish_recycling` VALUES ('21', '北京', '宋庄垃圾转运站', '北京市徐疃路', '39.986281', '116.702214');
INSERT INTO `t_rubbish_recycling` VALUES ('22', '北京', '北京市石景山垃圾转运站', '北京市京源路甲8号', '39.903623', '116.210922');
INSERT INTO `t_rubbish_recycling` VALUES ('23', '北京', '岳各庄垃圾转运站', '北京市大屯路', '40.002616', '116.397436');
INSERT INTO `t_rubbish_recycling` VALUES ('24', '北京', '南苑垃圾中转站', '警备东路6号西区一号院', '39.796553', '116.382713');
INSERT INTO `t_rubbish_recycling` VALUES ('25', '北京', '槐房垃圾中转站', '北京市槐房路4号', '39.82779', '116.38434');
INSERT INTO `t_rubbish_recycling` VALUES ('26', '北京', '高立庄垃圾中转站', '北京市高立庄600号', '39.794005', '116.29983');
INSERT INTO `t_rubbish_recycling` VALUES ('27', '北京', '开阳里六区垃圾转运站', '北京市丰台区', '39.860337', '116.312765');
INSERT INTO `t_rubbish_recycling` VALUES ('28', '北京', '北京市丰台区环境卫生服务中心小井垃圾中转站', '北京市丰台区小井街', '39.876033', '116.29679');
INSERT INTO `t_rubbish_recycling` VALUES ('29', '北京', '王四营地区垃圾中转站', '北京市朝阳区古塔北路', '39.876455', '116.537515');
INSERT INTO `t_rubbish_recycling` VALUES ('30', '北京', '垃圾中转站（昌盛园三区西南）', '北京市东关环岛', '40.222206', '116.261744');
INSERT INTO `t_rubbish_recycling` VALUES ('31', '北京', '古北口镇垃圾转运站', '北京市京密路', '39.904989', '116.405285');
INSERT INTO `t_rubbish_recycling` VALUES ('32', '北京', '溪翁庄镇垃圾转运站', '北京市密云县', '40.377362', '116.843352');
INSERT INTO `t_rubbish_recycling` VALUES ('33', '北京', '政法大学垃圾中转站', '北京市一中路东口', '40.217154', '116.661706');
INSERT INTO `t_rubbish_recycling` VALUES ('34', '北京', '彭各庄镇垃圾转运站', '北京市大兴区西韩路', '39.584886', '116.246418');
INSERT INTO `t_rubbish_recycling` VALUES ('35', '北京', '门头沟生活垃圾转运站', '北京市新桥南大街81号', '39.698533', '115.984103');
INSERT INTO `t_rubbish_recycling` VALUES ('36', '北京', '通州区生活垃圾转运站', '北京市通州区', '39.902486', '116.658603');
INSERT INTO `t_rubbish_recycling` VALUES ('37', '北京', '葆台垃圾中转站', '北京市居库路', '39.802948', '116.282672');
INSERT INTO `t_rubbish_recycling` VALUES ('38', '北京', '房山区城关生活垃圾转运站', '北京市房山区城关镇', '39.697442', '115.985885');
INSERT INTO `t_rubbish_recycling` VALUES ('39', '北京', '龙水路垃圾中转站', '北京市龙水路', '40.212957', '116.258873');
INSERT INTO `t_rubbish_recycling` VALUES ('40', '北京', '西田各庄镇垃圾中转站', '北京市密云区', '39.904989', '116.405285');
INSERT INTO `t_rubbish_recycling` VALUES ('41', '北京', '张山营垃圾中转站', '北京市旧小路', '40.550202', '116.084435');
INSERT INTO `t_rubbish_recycling` VALUES ('42', '北京', '来广营地区垃圾中转站', '北京市顺白路', '40.122676', '116.641915');
INSERT INTO `t_rubbish_recycling` VALUES ('43', '北京', '马驹桥镇生活垃圾中转站', '北京市六支路', '39.904989', '116.405285');
INSERT INTO `t_rubbish_recycling` VALUES ('44', '北京', '天通苑市政垃圾转运站', '北京市太平庄北街', '39.91935', '116.53705');
INSERT INTO `t_rubbish_recycling` VALUES ('45', '北京', '千禧购物中心垃圾中转站', '北京市丰台区靛厂路', '39.889207', '116.284272');
INSERT INTO `t_rubbish_recycling` VALUES ('46', '北京', '榆树庄垃圾中转站', '北京市民村路', '39.831118', '116.260466');
INSERT INTO `t_rubbish_recycling` VALUES ('47', '北京', '黄村镇保洁中心后辛庄垃圾转运站', '北京市芦求路', '39.73193', '116.283299');
INSERT INTO `t_rubbish_recycling` VALUES ('48', '北京', '北京市丰台区环境卫生服务中心新宫垃圾中转站', '北京市丰台区南苑西路', '39.814021', '116.362359');
INSERT INTO `t_rubbish_recycling` VALUES ('49', '北京', '环卫集团北神树垃圾卫生填埋场', '北京市通州区次渠镇', '39.80532', '116.58693');
INSERT INTO `t_rubbish_recycling` VALUES ('50', '北京', '环卫集团安定垃圾卫生填埋场', '北京市大兴县安定境内', '39.755477', '116.337733');
INSERT INTO `t_rubbish_recycling` VALUES ('51', '北京', '环卫集团阿苏卫垃圾卫生填埋场', '北京昌平区百善乡', '40.16537', '116.32053');
INSERT INTO `t_rubbish_recycling` VALUES ('52', '北京', '朝阳高安屯垃圾卫生填埋场', '北京市朝阳区金盏乡高安屯村', '39.94213', '116.61749');
INSERT INTO `t_rubbish_recycling` VALUES ('53', '北京', '丰台永合庄垃圾卫生填埋场', '北京市丰台区看杨路', '39.829384', '116.27091');
INSERT INTO `t_rubbish_recycling` VALUES ('54', '北京', '海淀六里屯垃圾卫生填埋场', '北京市海淀区永丰乡', '39.956074', '116.310316');
INSERT INTO `t_rubbish_recycling` VALUES ('55', '北京', '门头沟焦家坡垃圾卫生填埋场', '北京市门头沟区永定镇', '39.90687', '116.10352');
INSERT INTO `t_rubbish_recycling` VALUES ('56', '北京', '房山半壁店垃圾卫生填埋场', '北京市房山区大石窝镇半壁店村', '39.552854', '115.83231');
INSERT INTO `t_rubbish_recycling` VALUES ('57', '北京', '通州西田阳垃圾卫生填埋场', '北京市通州区大杜社镇西田阳', '39.74763', '116.65092');
INSERT INTO `t_rubbish_recycling` VALUES ('58', '北京', '平谷峪口垃圾卫生填埋场', '北京市平谷区裕口镇', '40.144783', '117.112335');
INSERT INTO `t_rubbish_recycling` VALUES ('59', '北京', '怀柔垃圾卫生填埋场', '北京市怀柔区庙城镇孙史山村', '40.27524', '116.59228');
INSERT INTO `t_rubbish_recycling` VALUES ('60', '北京', '密云滨阳垃圾卫生填埋场', '北京市密云县滨阳村', '40.382347', '116.858317');
INSERT INTO `t_rubbish_recycling` VALUES ('61', '北京', '延庆小张家口垃圾卫生填埋场', '北京市延庆县大榆树镇小张家口村', '40.39987', '116.03144');
INSERT INTO `t_rubbish_recycling` VALUES ('62', '北京', '延庆永宁垃圾卫生填埋场', '北京市延庆县永宁镇', '40.52915', '116.16312');
INSERT INTO `t_rubbish_recycling` VALUES ('63', '北京', '环卫集团南宫垃圾堆肥厂', '北京市大兴区瀛海镇', '39.75628', '116.45228');
INSERT INTO `t_rubbish_recycling` VALUES ('64', '北京', '国中公司阿苏卫综合处理中心', '北京市昌平区小汤山镇阿苏卫村', '40.1704', '116.36304');
INSERT INTO `t_rubbish_recycling` VALUES ('65', '北京', '顺义垃圾综合处理中心', '北京市顺义区杨镇西庞村南', '40.14144', '116.87659');
INSERT INTO `t_rubbish_recycling` VALUES ('66', '北京', '朝阳高安屯焚烧厂', '北京市朝阳区安立路６８', '39.99802', '116.408935');
INSERT INTO `t_rubbish_recycling` VALUES ('67', '天津', '天津市西青区大寺镇垃圾中转站', '天津市西青区鑫源道', '39.005882', '117.236346');
INSERT INTO `t_rubbish_recycling` VALUES ('68', '天津', '天津市潘楼生活垃圾中转站', '天津市工西路', '39.072269', '117.106183');
INSERT INTO `t_rubbish_recycling` VALUES ('69', '天津', '红桥区芥园大堤垃圾中转站', '天津市南运河南路', '39.150178', '117.176388');
INSERT INTO `t_rubbish_recycling` VALUES ('70', '天津', '天津市河北区二马路垃圾转运站', '天津市二马路76号', '39.15796', '117.19759');
INSERT INTO `t_rubbish_recycling` VALUES ('71', '天津', '天津市河东区八号路垃圾转运站', '天津市大直沽前街2号', '39.10765', '117.2416');
INSERT INTO `t_rubbish_recycling` VALUES ('72', '天津', '天津市河北区东河沿垃圾转运站', '天津市东河沿街71号', '39.100676', '117.242226');
INSERT INTO `t_rubbish_recycling` VALUES ('73', '天津', '天津市河东区六号路垃圾转运站', '天津市王串场六号路115号', '39.15134', '117.23216');
INSERT INTO `t_rubbish_recycling` VALUES ('74', '天津', '天津市河北区幸福道垃圾转运站', '天津市幸福道23号', '39.15403', '117.23825');
INSERT INTO `t_rubbish_recycling` VALUES ('75', '天津', '韶山道垃圾转运站', '天津市韶山道', '39.12991', '117.23593');
INSERT INTO `t_rubbish_recycling` VALUES ('76', '天津', '电台道垃圾转运站', '天津市电台道38号', '39.10989', '117.18158');
INSERT INTO `t_rubbish_recycling` VALUES ('77', '天津', '北辰区王庄村垃圾转运站', '天津市王庄村', '39.20254', '117.12151');
INSERT INTO `t_rubbish_recycling` VALUES ('78', '天津', '体育场北路垃圾转运站', '天津市体育场北路', '38.989431', '117.381299');
INSERT INTO `t_rubbish_recycling` VALUES ('79', '天津', '春曦道垃圾转运站', '天津市静海区', '38.94218', '116.97576');
INSERT INTO `t_rubbish_recycling` VALUES ('80', '天津', '建安里垃圾转运站', '天津市朝阳路', '39.322869', '117.829983');
INSERT INTO `t_rubbish_recycling` VALUES ('81', '天津', '重阳里垃圾转运站', '天津市霞光路', '38.847027', '117.460272');
INSERT INTO `t_rubbish_recycling` VALUES ('82', '天津', '胜利里垃圾转运站', '天津市胜利街', '38.83654', '117.44912');
INSERT INTO `t_rubbish_recycling` VALUES ('83', '天津', '北辰区垃圾转运站（北辰道）', '天津市引河里北道1号', '39.236343', '117.116624');
INSERT INTO `t_rubbish_recycling` VALUES ('84', '天津', '水木天成垃圾转运站', '天津市天平路', '39.187881', '117.133737');
INSERT INTO `t_rubbish_recycling` VALUES ('85', '天津', '中北斜村垃圾转运站', '天津市中北镇中北工业园', '39.1296', '117.078427');
INSERT INTO `t_rubbish_recycling` VALUES ('86', '天津', '垃圾转运站（金沙江路）', '天津市金沙江路', '39.165071', '117.240604');
INSERT INTO `t_rubbish_recycling` VALUES ('87', '天津', '北辰区垃圾转运站（天津现代和谐医院东南）', '天津市三千路', '39.195494', '117.207828');
INSERT INTO `t_rubbish_recycling` VALUES ('88', '天津', '石化农贸市场垃圾转运站', '天津市石化路', '38.839017', '117.44878');
INSERT INTO `t_rubbish_recycling` VALUES ('89', '天津', '王顶堤九区垃圾转运站', '天津市园苑西路', '39.088993', '117.140025');
INSERT INTO `t_rubbish_recycling` VALUES ('90', '天津', '南开区环卫局鼓楼垃圾转运站', '天津市城厢中路29号', '39.141129', '117.179886');
INSERT INTO `t_rubbish_recycling` VALUES ('91', '天津', '河西区环境卫生管理局垃圾转运站', '天津市五号堤路', '39.068273', '117.225279');
INSERT INTO `t_rubbish_recycling` VALUES ('92', '天津', '河西区环境卫生管理局垃圾清运中心苏州街转运站', '天津市河西区南昌路115号', '39.10865', '117.21197');
INSERT INTO `t_rubbish_recycling` VALUES ('93', '天津', '河西区环境卫生管理局垃圾清运中心连荣里转运站', '天津市越秀北路', '39.102987', '117.210534');
INSERT INTO `t_rubbish_recycling` VALUES ('94', '天津', '河西区环境卫生管理局垃圾清运中心（榆林路转运站）', '天津市榆林路11号', '39.05565', '117.2613');
INSERT INTO `t_rubbish_recycling` VALUES ('95', '天津', '徐庄垃圾中转站', '天津市东丽区', '39.087764', '117.313967');
INSERT INTO `t_rubbish_recycling` VALUES ('96', '天津', '双港生活焚烧发电厂', '天津市津南区双港镇', '39.04274', '117.3187');
INSERT INTO `t_rubbish_recycling` VALUES ('97', '天津', '大韩庄垃圾填埋场', '天津市津南区南八里台镇大韩庄村', '38.945412', '117.298236');
INSERT INTO `t_rubbish_recycling` VALUES ('98', '天津', '青光垃圾焚烧发电厂', '天津市北辰区青光镇', '39.19714', '117.05967');
INSERT INTO `t_rubbish_recycling` VALUES ('99', '天津', '双口垃圾填埋场', '天津市北辰区双口镇', '39.22748', '117.03658');
INSERT INTO `t_rubbish_recycling` VALUES ('100', '天津', '潘楼粪便处理厂', '天津市西青区潘楼', '39.05534', '117.10961');
INSERT INTO `t_rubbish_recycling` VALUES ('101', '天津', '碧海餐饮垃圾处理厂', '天津市津南区黄台工业经济发展中心', '38.869812', '117.434471');
INSERT INTO `t_rubbish_recycling` VALUES ('102', '天津', '滨海新区第一垃圾焚烧发电厂', '天津市滨海新区茶淀镇南部', '39.26232', '117.80593');
INSERT INTO `t_rubbish_recycling` VALUES ('103', '天津', '大港焚烧厂', '天津市滨海新区塘沽南港轻纺工业园', '38.82917', '117.528297');
INSERT INTO `t_rubbish_recycling` VALUES ('104', '天津', '武清填埋场', '天津市武清区', '39.376925', '117.057959');
INSERT INTO `t_rubbish_recycling` VALUES ('105', '天津', '天津泉泰生活垃圾处理厂', '天津市宝坻区', '39.716965', '117.308094');
INSERT INTO `t_rubbish_recycling` VALUES ('106', '天津', '蓟县生活垃圾填埋场', '天津市蓟县', '40.045342', '117.407449');
INSERT INTO `t_rubbish_recycling` VALUES ('107', '天津', '塘沽中转站', '天津市塘沽县', '39.125596', '117.190182');
INSERT INTO `t_rubbish_recycling` VALUES ('108', '天津', '静海紫兆生活废弃物处理厂', '天津市静海县', '38.94218', '116.97576');
INSERT INTO `t_rubbish_recycling` VALUES ('109', '天津', '天津贯庄垃圾焚烧厂', '天津市东丽区赤土镇贯庄', '39.15517', '117.40867');
INSERT INTO `t_rubbish_recycling` VALUES ('110', '天津', '汉沽生活垃圾填埋场', '天津汉沽营城乡建华村', '39.20556', '117.78215');
INSERT INTO `t_rubbish_recycling` VALUES ('111', '天津', '大港生活垃圾填埋场', '天津市大港区', '39.125596', '117.190182');
INSERT INTO `t_rubbish_recycling` VALUES ('112', '上海', '上海江桥垃圾焚烧厂', '上海市嘉定区绥德路800号', '31.26739', '121.36403');
INSERT INTO `t_rubbish_recycling` VALUES ('113', '上海', '上海御桥垃圾焚烧厂', '上海市御桥路869号', '31.15388', '121.55073');
INSERT INTO `t_rubbish_recycling` VALUES ('114', '上海', '上海老港填埋场', '\n上海市浦东区老港镇', '31.038133', '121.842931');
INSERT INTO `t_rubbish_recycling` VALUES ('115', '上海', '上海市松江区生活垃圾卫生填埋场', '上海市松江区卖新公路506号', '31.06645', '121.27125');
INSERT INTO `t_rubbish_recycling` VALUES ('116', '上海', '垃圾中转站', '上海市浦东新区前哨路127号', '31.238512', '121.719849');
INSERT INTO `t_rubbish_recycling` VALUES ('117', '上海', '垃圾中转站', '上海市浦东新区Y390（跃丰路）', '31.225479', '121.709193');
INSERT INTO `t_rubbish_recycling` VALUES ('118', '上海', '爱建圆垃圾中转站', '上海市徐汇区田林东路100弄', '31.176107', '121.432587');
INSERT INTO `t_rubbish_recycling` VALUES ('119', '上海', '赵行村垃圾中转站', '上海市赵瓦路南50米', '31.231706', '121.472644');
INSERT INTO `t_rubbish_recycling` VALUES ('120', '上海', '三墩生活垃圾中转站', '上海市浦东新区', '31.245944', '121.567706');
INSERT INTO `t_rubbish_recycling` VALUES ('121', '上海', '浦东新区唐镇垃圾中转站', '上海市浦东新区川沙路1784号-临', '31.25075', '121.68229');
INSERT INTO `t_rubbish_recycling` VALUES ('122', '上海', '界龙村生活垃圾中转站', '上海市界龙大道', '31.175509', '121.683724');
INSERT INTO `t_rubbish_recycling` VALUES ('123', '上海', '九亭镇绿化垃圾中转站', '上海市姚北路9号', '31.10116', '121.31907');
INSERT INTO `t_rubbish_recycling` VALUES ('124', '上海', '纯新村垃圾中转站', '上海市纯新路', '31.137845', '121.704723');
INSERT INTO `t_rubbish_recycling` VALUES ('125', '上海', '崇明三星生活垃圾转运站', '上海市宏海公路', '31.815931', '121.294667');
INSERT INTO `t_rubbish_recycling` VALUES ('126', '上海', '松江区西部垃圾转运站', '上海市昆岗公路3701号', '31.231706', '121.472644');
INSERT INTO `t_rubbish_recycling` VALUES ('127', '上海', '松江区浦南垃圾转运站', '上海市叶发路20号', '30.92851', '121.29946');
INSERT INTO `t_rubbish_recycling` VALUES ('128', '上海', '崇明陈家镇生活垃圾转运站', '上海市新出港路', '31.53854', '121.827805');
INSERT INTO `t_rubbish_recycling` VALUES ('129', '上海', '包头路垃圾压缩中转站', '上海市杨浦区包头南路850-1号', '31.30299', '121.53905');
INSERT INTO `t_rubbish_recycling` VALUES ('130', '上海', '华泾镇ZY25-200T垃圾压缩中转站', '上海市龙吴路2134号', '31.12358', '121.455585');
INSERT INTO `t_rubbish_recycling` VALUES ('131', '上海', '高桥垃圾分类中转站', '上海市李高路410号', '31.02213', '121.3165');
INSERT INTO `t_rubbish_recycling` VALUES ('132', '上海', '长溇垃圾分类中转站', '上海市车墩4', '31.01703', '121.30858');
INSERT INTO `t_rubbish_recycling` VALUES ('133', '上海', '祥东垃圾分类中转站', '上海市车墩011', '31.01703', '121.30858');
INSERT INTO `t_rubbish_recycling` VALUES ('134', '上海', '留业路垃圾分类中转站', '上海市松江区留业路6号', '31.00689', '121.30402');
INSERT INTO `t_rubbish_recycling` VALUES ('135', '上海', '振兴路垃圾分类中转站', '上海市振兴路235号', '31.017023', '121.309559');
INSERT INTO `t_rubbish_recycling` VALUES ('136', '上海', '上海瑞康环保设备有限公司ZYZ5-200T垃圾压缩中转站', '上海市龙吴路2134号', '31.12358', '121.455585');
INSERT INTO `t_rubbish_recycling` VALUES ('137', '上海', '金山区生活垃圾收运系统金山工业区转运站', '上海市漕廊公路', '30.794867', '121.40347');
INSERT INTO `t_rubbish_recycling` VALUES ('138', '广东', '广州市兴丰生活垃圾卫生填埋场', '广州市兴太三路', '23.262438', '113.461066');
INSERT INTO `t_rubbish_recycling` VALUES ('139', '广东', '深圳市下坪固体废弃物填埋场', '深圳市罗湖区与龙岗区坂田交界处的上、下坪谷地', '22.63815', '114.0678');
INSERT INTO `t_rubbish_recycling` VALUES ('140', '广东', '深圳市宝安老虎坑废弃物处置中心', '深圳市宝安区老虎坑环境园', '22.8177', '113.85381');
INSERT INTO `t_rubbish_recycling` VALUES ('141', '广东', '佛山市高明区苗村白石坳填埋场', '佛山市高明区明城镇苗村白石坳', '22.78392', '112.67536');
INSERT INTO `t_rubbish_recycling` VALUES ('142', '广东', '中山市中心组团垃圾综合处理基地卫生填埋场', '', '22.521113', '113.382391');
INSERT INTO `t_rubbish_recycling` VALUES ('143', '广东', '　韶关市新丰县生活垃圾填埋场', '广东省韶关市新丰县', '24.055412', '114.207034');
INSERT INTO `t_rubbish_recycling` VALUES ('144', '广东', '惠州市惠阳区龙尾坑垃圾填埋场', '惠州市惠阳区', '22.78851', '114.469444');
INSERT INTO `t_rubbish_recycling` VALUES ('145', '广东', '清远市青山城市生活垃圾卫生填埋场', '广东省清远市清城区横荷街道办', '23.65719', '113.0507');
INSERT INTO `t_rubbish_recycling` VALUES ('146', '广东', '汕头市雷打石填埋场', '汕头市金平区鮀莲街道雷打石“象山窝”山地', '23.42183', '116.60025');
INSERT INTO `t_rubbish_recycling` VALUES ('147', '广东', '潮洲市锡岗生活垃圾处理场', '潮州市古巷镇锡岗村西北', '23.68309', '116.58447');
INSERT INTO `t_rubbish_recycling` VALUES ('148', '广东', '揭阳市东径外草地垃圾处理场', '广东省揭阳市揭东县云路镇东径村', '23.58319', '116.46884');
INSERT INTO `t_rubbish_recycling` VALUES ('149', '广东', '广东东莞垃圾处理厂', '东莞市麻涌大步村海星沙', '23.04517', '113.58043');
INSERT INTO `t_rubbish_recycling` VALUES ('150', '广东', '广州李坑生活垃圾焚烧发电厂', '广州市白云区太和镇永兴村', '23.26475', '113.33031');
INSERT INTO `t_rubbish_recycling` VALUES ('151', '广东', '中山市蒂峰山垃圾综合处理基地焚烧发电厂', '中山市南朗镇与火炬开发区交界的南朗境内的蒂峰山地区', '22.520359', '113.503829');
INSERT INTO `t_rubbish_recycling` VALUES ('152', '广东', '深圳清水河垃圾焚烧厂(环卫综合处理厂)', '深圳市罗湖区红岗路1233号', '22.58329', '114.10622');
INSERT INTO `t_rubbish_recycling` VALUES ('153', '广东', '深圳南山垃圾焚烧厂', '深圳市南山区妈湾大道', '22.498121', '113.873163');
INSERT INTO `t_rubbish_recycling` VALUES ('154', '广东', '深圳盐田垃圾焚烧厂', '广东省深圳市盐田区青麟坑', '22.555069', '114.235366');
INSERT INTO `t_rubbish_recycling` VALUES ('155', '广东', '深圳宝安垃圾焚烧厂', '深圳市松岗镇塘下涌村老虎坑水库上游', '22.818887', '113.852047');
INSERT INTO `t_rubbish_recycling` VALUES ('156', '广东', '深圳龙岗平湖垃圾发电厂', '深圳市龙岗区平湖街道办辅城坳村白鸽湖', '22.69287', '114.08899');
INSERT INTO `t_rubbish_recycling` VALUES ('157', '广东', '佛山南海环保发电厂', '广东省佛山市南海区狮山镇南海科技工业园', '23.20802', '112.9972');
INSERT INTO `t_rubbish_recycling` VALUES ('158', '广东', '珠海垃圾焚烧发电厂', '广东省珠海市香洲区前山南溪青松路', '22.290818', '113.502145');
INSERT INTO `t_rubbish_recycling` VALUES ('159', '广东', '惠州市垃圾焚烧发电厂', '惠州市市区西郊东江林场湖洋坑', '23.46554', '114.49395');
INSERT INTO `t_rubbish_recycling` VALUES ('160', '广东', '小石垃圾转运站', '广州市小北路与小石街交叉口西南50米', '23.13391', '113.270511');
INSERT INTO `t_rubbish_recycling` VALUES ('161', '广东', '永泰垃圾转运站', '广州市泰兴路23号', '22.942525', '113.405559');
INSERT INTO `t_rubbish_recycling` VALUES ('162', '广东', '好信垃圾转运站', '广州市南州北路163号', '23.067659', '113.300625');
INSERT INTO `t_rubbish_recycling` VALUES ('163', '广东', '沥滘垃圾转运站', '广州市海珠区S81广州环城高速附近', '23.061354', '113.317572');
INSERT INTO `t_rubbish_recycling` VALUES ('164', '广东', '秀全路垃圾转运站', '广州市丽雅西街秀全大道路口附近', '23.37787', '113.20937');
INSERT INTO `t_rubbish_recycling` VALUES ('165', '广东', '长湴村垃圾转运站', '广州市天河区长湴东路77', '23.17057', '113.35338');
INSERT INTO `t_rubbish_recycling` VALUES ('166', '广东', '海珠北垃圾转运站', '广州市越秀区海珠北路212', '23.129528', '113.25858');
INSERT INTO `t_rubbish_recycling` VALUES ('167', '广东', '石溪垃圾转运站', '广州市工业大道南458-1号', '23.068875', '113.283095');
INSERT INTO `t_rubbish_recycling` VALUES ('168', '广东', '建基路垃圾转运站', '广州市海珠区', '23.085191', '113.286198');
INSERT INTO `t_rubbish_recycling` VALUES ('169', '广东', '天河南二路垃圾转运站', '广州市天河南二路1号', '23.13182', '113.33021');
INSERT INTO `t_rubbish_recycling` VALUES ('170', '广东', '洛溪垃圾中转站', '广州市北环路与圃结街交叉口南50米', '23.049502', '113.292803');
INSERT INTO `t_rubbish_recycling` VALUES ('171', '广东', '白马岗垃圾中转站', '广州市白马岗街21号', '23.1238', '113.35575');
INSERT INTO `t_rubbish_recycling` VALUES ('172', '广东', '莲塘村垃圾中转站', '广州市海涌路', '22.934768', '113.463463');
INSERT INTO `t_rubbish_recycling` VALUES ('173', '广东', '胜石村垃圾中转站', '广州市环村西路和环村中路交叉口', '22.972985', '113.393748');
INSERT INTO `t_rubbish_recycling` VALUES ('174', '广东', '天河南垃圾中转站', '广州市天河区天河南二路11号', '23.131415', '113.33464');
INSERT INTO `t_rubbish_recycling` VALUES ('175', '广东', '钟二村垃圾中转站', '广州市北固大街和钟汉璐交叉口', '22.97758', '113.321');
INSERT INTO `t_rubbish_recycling` VALUES ('176', '广东', '龙洞林场垃圾转运站', '广东省广州市天河区', '23.13559', '113.335367');
INSERT INTO `t_rubbish_recycling` VALUES ('177', '广东', '凤阳街道沙东垃圾中转站', '广州市海珠区', '23.085191', '113.286198');
INSERT INTO `t_rubbish_recycling` VALUES ('178', '广东', '棠下垃圾转运站', '广州市棠安路119号', '23.13333', '113.37731');
INSERT INTO `t_rubbish_recycling` VALUES ('179', '广东', '林和垃圾转运站', '广州市林和中路188号恒源大厦副楼附近', '23.14945', '113.3263');
INSERT INTO `t_rubbish_recycling` VALUES ('180', '广东', '河沙垃圾转运站', '广州市东海北路296', '23.14191', '113.22199');
INSERT INTO `t_rubbish_recycling` VALUES ('181', '广东', '赤沙垃圾转运站', '广州市茂兴里外街北五巷附近', '23.089668', '113.361574');
INSERT INTO `t_rubbish_recycling` VALUES ('182', '广东', '南漖垃圾转运站', '广州市环翠南路12号', '23.05019', '113.24973');
INSERT INTO `t_rubbish_recycling` VALUES ('183', '广东', '冼村垃圾转运站', '广州市天河区', '23.13559', '113.335367');
INSERT INTO `t_rubbish_recycling` VALUES ('184', '广东', '燕塘垃圾转运站', '广州市兴华街道广州地铁三号线北延段', '23.16507', '113.32625');
INSERT INTO `t_rubbish_recycling` VALUES ('185', '广东', '华天垃圾转运站', '广州市龙口中路194号附近', '23.13943', '113.34347');
INSERT INTO `t_rubbish_recycling` VALUES ('186', '广东', '信合垃圾转运站', '广州市昌岗中路217', '23.089143', '113.273537');
INSERT INTO `t_rubbish_recycling` VALUES ('187', '广东', '石化垃圾压缩转运站', '广州市石化南一街', '23.105251', '113.475747');
INSERT INTO `t_rubbish_recycling` VALUES ('188', '广东', '西基东垃圾转运站', '广州市海珠区', '23.085191', '113.286198');
INSERT INTO `t_rubbish_recycling` VALUES ('189', '广东', '东湖西垃圾转运站', '广州市东湖西路25', '23.117708', '113.286407');
INSERT INTO `t_rubbish_recycling` VALUES ('190', '广东', '淘金北垃圾转运站', '广州市正平南街5号', '23.14193', '113.28953');
INSERT INTO `t_rubbish_recycling` VALUES ('191', '广东', '芳村花园垃圾转运站', '广州市龙溪东路48号广佛数字创意园', '23.07925', '113.22037');
INSERT INTO `t_rubbish_recycling` VALUES ('192', '广东', '天贵路生活垃圾转运站', '广州市天贵路78号附近', '23.39453', '113.22266');
INSERT INTO `t_rubbish_recycling` VALUES ('193', '广东', '西滘垃圾转运站', '广州市西滘大街', '23.066836', '113.302029');
INSERT INTO `t_rubbish_recycling` VALUES ('194', '广东', '西塱垃圾转运站', '广州市东西路99号', '23.05324', '113.23438');
INSERT INTO `t_rubbish_recycling` VALUES ('195', '广东', '革新路垃圾转运站', '广州市海珠区革新路', '23.08997', '113.25325');
INSERT INTO `t_rubbish_recycling` VALUES ('196', '广东', '葵蓬村垃圾转运站', '广州市葵蓬路', '23.091907', '113.218668');
INSERT INTO `t_rubbish_recycling` VALUES ('197', '广东', '坑口村垃圾转运站', '广州市荔湾区', '23.124943', '113.243038');
INSERT INTO `t_rubbish_recycling` VALUES ('198', '广东', '暨南大学垃圾压缩中转站', '广州市真如东路', '23.129436', '113.350313');
INSERT INTO `t_rubbish_recycling` VALUES ('199', '广东', '石牌东路垃圾转运站', '广州市石牌东路164', '23.128939', '113.343136');
INSERT INTO `t_rubbish_recycling` VALUES ('200', '广东', '吉山垃圾中转站', '广州市吉岐路', '23.133776', '113.438405');
INSERT INTO `t_rubbish_recycling` VALUES ('201', '广东', '南沙区沙螺湾垃圾压缩中转站', '广州市环市北路', '22.808956', '113.562775');
INSERT INTO `t_rubbish_recycling` VALUES ('202', '广东', '固体废物压缩转运站', '广州市越秀区', '23.125624', '113.280714');
INSERT INTO `t_rubbish_recycling` VALUES ('203', '广东', '禅城区垃圾转运站', '佛山市禅城区', '23.019643', '113.112414');
INSERT INTO `t_rubbish_recycling` VALUES ('204', '广东', '南约垃圾中转站', '佛山市南海区', '23.031562', '113.145577');
INSERT INTO `t_rubbish_recycling` VALUES ('205', '广东', '小涌村垃圾中转站', '佛山市顺德区', '22.75851', '113.281826');
INSERT INTO `t_rubbish_recycling` VALUES ('206', '广东', '岳步村垃圾中转站', '佛山市519县道和岳僚路交叉口', '22.949164', '113.145881');
INSERT INTO `t_rubbish_recycling` VALUES ('207', '广东', '葛岸村垃圾中转站', '佛山市顺德区', '22.75851', '113.281826');
INSERT INTO `t_rubbish_recycling` VALUES ('208', '广东', '良教村垃圾中转站', '佛山市顺德区东信南街', '22.963197', '113.061168');
INSERT INTO `t_rubbish_recycling` VALUES ('209', '广东', '大罗村垃圾中转站', '佛山市德顺区', '23.028762', '113.122717');
INSERT INTO `t_rubbish_recycling` VALUES ('210', '广东', '沙滘南村垃圾中转站', '佛山市德顺区', '23.028762', '113.122717');
INSERT INTO `t_rubbish_recycling` VALUES ('211', '广东', '沙滘西村垃圾中转站', '佛山市德顺区', '23.028762', '113.122717');
INSERT INTO `t_rubbish_recycling` VALUES ('212', '广东', '工业二区垃圾中转站', '佛山市南海区', '23.031562', '113.145577');
INSERT INTO `t_rubbish_recycling` VALUES ('213', '广东', '大沥西区生活垃圾转运站', '佛山市博爱东路', '23.11666', '113.067038');
INSERT INTO `t_rubbish_recycling` VALUES ('214', '广东', '西约垃圾中转站', '佛山市南海区桂城街道西约福庆村1号', '23.04086', '113.14477');
INSERT INTO `t_rubbish_recycling` VALUES ('215', '广东', '东约垃圾中转站', '佛山市南海区桂城镇东约区东大街8号', '23.07944', '113.16371');
INSERT INTO `t_rubbish_recycling` VALUES ('216', '广东', '华林垃圾中转站', '佛山市顺德区', '22.75851', '113.281826');
INSERT INTO `t_rubbish_recycling` VALUES ('217', '广东', '平胜垃圾压缩中转站', '佛山市南海区', '23.031562', '113.145577');
INSERT INTO `t_rubbish_recycling` VALUES ('218', '广东', '西平垃圾压缩中转站', '佛山市南海区', '23.031562', '113.145577');
INSERT INTO `t_rubbish_recycling` VALUES ('219', '广东', '佛山市顺德区碧水垃圾处理有限公司容桂垃圾压缩中转站', '佛山市顺德区容光路', '22.758595', '113.309258');
INSERT INTO `t_rubbish_recycling` VALUES ('220', '广东', '桂城市政夏东垃圾压缩中转站', '佛山市南海区', '23.031562', '113.145577');
INSERT INTO `t_rubbish_recycling` VALUES ('221', '广东', '河东建和村民小组生活垃圾压缩转运站', '佛山市南海区', '23.031562', '113.145577');
INSERT INTO `t_rubbish_recycling` VALUES ('222', '广东', '南华垃圾转运站', '湛江市中山二路7号', '21.60235', '110.287');
INSERT INTO `t_rubbish_recycling` VALUES ('223', '广东', '北桥垃圾转运站', '湛江市北桥一横路', '21.279529', '110.365255');
INSERT INTO `t_rubbish_recycling` VALUES ('224', '广东', '银海垃圾中转站', '湛江市麻章区', '21.265997', '110.329167');
INSERT INTO `t_rubbish_recycling` VALUES ('225', '广东', '草菊垃圾中转站', '湛江市椹川大道北', '21.259935', '110.36899');
INSERT INTO `t_rubbish_recycling` VALUES ('226', '广东', '垃圾转运站', '汕头市澄海区', '23.46844', '116.76336');
INSERT INTO `t_rubbish_recycling` VALUES ('227', '广东', '宁江垃圾转运站', '汕头市澄海区', '23.46844', '116.76336');
INSERT INTO `t_rubbish_recycling` VALUES ('228', '广东', '垃圾转运站（珠业一街）', '汕头市龙湖区', '23.373754', '116.732015');
INSERT INTO `t_rubbish_recycling` VALUES ('229', '广东', '学院路垃圾中转站', '梅州市学院路10', '24.320375', '116.131475');
INSERT INTO `t_rubbish_recycling` VALUES ('230', '广东', '中山东路垃圾中转站', '梅州市兴宁市', '24.138077', '115.731648');
INSERT INTO `t_rubbish_recycling` VALUES ('231', '广东', '龙山垃圾中转站', '汕尾市龙山路', '22.949222', '115.648661');
INSERT INTO `t_rubbish_recycling` VALUES ('232', '广东', '星河垃圾转运站', '江门市蓬江区星河路11号', '22.60512', '113.06998');
INSERT INTO `t_rubbish_recycling` VALUES ('233', '广东', '嘉福垃圾转运站', '江门市蓬江区丰泰路6-105号', '22.60196', '113.0806');
INSERT INTO `t_rubbish_recycling` VALUES ('234', '广东', '华园垃圾转运站', '江门市蓬江区华园横路29号', '22.57679', '113.07421');
INSERT INTO `t_rubbish_recycling` VALUES ('235', '广东', '东观垃圾转运站', '江门市蓬江区', '22.59677', '113.07859');
INSERT INTO `t_rubbish_recycling` VALUES ('236', '广东', '明珠垃圾中转站', '江门市台山市', '22.250713', '112.793414');
INSERT INTO `t_rubbish_recycling` VALUES ('237', '广东', '农林垃圾转运站', '江门市蓬江区', '22.59677', '113.07859');
INSERT INTO `t_rubbish_recycling` VALUES ('238', '广东', '德育垃圾转运站', '江门市蓬江区育德街46', '22.608306', '113.085768');
INSERT INTO `t_rubbish_recycling` VALUES ('239', '广东', '骏景湾垃圾转运站', '江门市蓬江区天福路84', '22.613924', '113.078629');
INSERT INTO `t_rubbish_recycling` VALUES ('240', '广东', '北苑垃圾转运站', '江门市蓬江区港口二路22', '22.606467', '113.099822');
INSERT INTO `t_rubbish_recycling` VALUES ('241', '广东', '蟠龙路垃圾中转站', '江门市蟠龙路133', '22.44775', '112.768027');
INSERT INTO `t_rubbish_recycling` VALUES ('242', '广东', '东埠路临时垃圾中转站', '江门市开平市', '22.366286', '112.692262');
INSERT INTO `t_rubbish_recycling` VALUES ('243', '广东', '东湖垃圾中转站', '江门市新会区', '22.518174', '113.038443');
INSERT INTO `t_rubbish_recycling` VALUES ('244', '广东', '中心南垃圾中转站', '江门市新会区', '22.518174', '113.038443');
INSERT INTO `t_rubbish_recycling` VALUES ('245', '广东', '环卫处东门路垃圾中转站', '江门市东门路10号', '22.1838', '112.31416');
INSERT INTO `t_rubbish_recycling` VALUES ('246', '广东', '环卫处锦茂路垃圾中转站', '江门市恩东路育英街路口', '22.184845', '112.318258');
INSERT INTO `t_rubbish_recycling` VALUES ('247', '广东', '垃圾中转站', '江门市台山市', '22.250713', '112.793414');
INSERT INTO `t_rubbish_recycling` VALUES ('248', '广东', '世纪花源垃圾转运站', '江门市蓬江区', '22.59677', '113.07859');
INSERT INTO `t_rubbish_recycling` VALUES ('249', '广东', '六里垃圾转运站', '江门市蓬江区龙环里', '22.59703', '113.09996');
INSERT INTO `t_rubbish_recycling` VALUES ('250', '广东', '金岛垃圾压缩中转站', '江门市蓬江区西环路297号', '22.595705', '113.056942');
INSERT INTO `t_rubbish_recycling` VALUES ('251', '广东', '龙瑞村垃圾中转站', '广东省中山市新濠路', '22.500967', '113.335004');
INSERT INTO `t_rubbish_recycling` VALUES ('252', '广东', '申明亭村垃圾中转站', '中山市沙溪镇中兴村', '22.51113', '113.31483');
INSERT INTO `t_rubbish_recycling` VALUES ('253', '广东', '华南医药城垃圾转运站', '中山市塘兴环村街', '22.52124', '113.539006');
INSERT INTO `t_rubbish_recycling` VALUES ('254', '广东', '窈窕垃圾转运站', '广东省中山市火炬高技术开发区白岗一街10号', '22.53829', '113.44792');
INSERT INTO `t_rubbish_recycling` VALUES ('255', '广东', '龙头环村垃圾中转站', '中山市象龙支路', '22.520756', '113.311621');
INSERT INTO `t_rubbish_recycling` VALUES ('256', '广东', '华柏路垃圾转运站', '中山市华柏路37号', '22.51829', '113.38274');
INSERT INTO `t_rubbish_recycling` VALUES ('257', '广东', '怀城镇垃圾中转站', '肇庆市河南中路', '23.909214', '112.179025');
INSERT INTO `t_rubbish_recycling` VALUES ('258', '广东', '西江南垃圾中转站', '肇庆市端州区', '23.052662', '112.472329');
INSERT INTO `t_rubbish_recycling` VALUES ('259', '广东', '河南垃圾中转站', '肇庆市河南中路', '23.909214', '112.179025');
INSERT INTO `t_rubbish_recycling` VALUES ('260', '广东', '龙日路垃圾转运站', '阳江市龙日路', '21.872771', '112.004181');
INSERT INTO `t_rubbish_recycling` VALUES ('261', '广东', '东源市场垃圾中转站', '阳江市漠江路', '21.860685', '111.966901');
INSERT INTO `t_rubbish_recycling` VALUES ('262', '广东', '下江城村垃圾转运站', '广东省东莞市', '23.048884', '113.760234');
INSERT INTO `t_rubbish_recycling` VALUES ('263', '广东', '凤岗镇垃圾转运站', '东莞市金城路96号', '22.742077', '114.170994');
INSERT INTO `t_rubbish_recycling` VALUES ('264', '广东', '金朗中路垃圾转运站', '东莞市市辖区', '23.048884', '113.760234');
INSERT INTO `t_rubbish_recycling` VALUES ('265', '广东', '厦边社区垃圾中转站', '东莞市兴业二街与岗厦南街交叉口东北50米', '22.798471', '113.735844');
INSERT INTO `t_rubbish_recycling` VALUES ('266', '广东', '青林路垃圾中转站', '东莞市青林路', '23.109798', '113.882175');
INSERT INTO `t_rubbish_recycling` VALUES ('267', '广东', '民生路垃圾中转站', '东莞市民业界一巷路口', '23.048884', '113.760234');
INSERT INTO `t_rubbish_recycling` VALUES ('268', '广东', '塘厦镇大坪垃圾中转站', '东莞市北沿路', '22.790405', '114.038107');
INSERT INTO `t_rubbish_recycling` VALUES ('269', '广东', '松山湖理工垃圾中转站', '', '22.87682', '113.92894');
INSERT INTO `t_rubbish_recycling` VALUES ('270', '广东', '上角社区垃圾转运站', '东莞市上南路146号', '22.79653', '113.71489');
INSERT INTO `t_rubbish_recycling` VALUES ('271', '广东', '新创社区垃圾转运站', '东莞市振兴八横巷与振兴西路交叉口北50米', '23.018547', '113.978415');
INSERT INTO `t_rubbish_recycling` VALUES ('272', '广东', '南城篁村垃圾转运站', '东莞市市辖区', '23.048884', '113.760234');
INSERT INTO `t_rubbish_recycling` VALUES ('273', '广东', '高埗芦村垃圾转运站', '东莞市市辖区', '23.048884', '113.760234');
INSERT INTO `t_rubbish_recycling` VALUES ('274', '广东', '望牛墩商业街垃圾转运站', '东莞市市辖区', '23.048884', '113.760234');
INSERT INTO `t_rubbish_recycling` VALUES ('275', '广东', '南城三元里垃圾转运站', '东莞市三元里卫生站', '23.00955', '113.740955');
INSERT INTO `t_rubbish_recycling` VALUES ('276', '广东', '大朗沙井街垃圾转运站', '东莞市沙井街', '22.933962', '113.942885');
INSERT INTO `t_rubbish_recycling` VALUES ('277', '广东', '大朗怡朗路垃圾转运站', '东莞市市辖区', '23.048884', '113.760234');
INSERT INTO `t_rubbish_recycling` VALUES ('278', '广东', '南城鸿福垃圾转运站南城08', '鸿福西路68号', '23.017524', '113.734432');
INSERT INTO `t_rubbish_recycling` VALUES ('279', '广东', '东莞凤凰岗黄河路垃圾转运站', '东莞市市辖区', '23.048884', '113.760234');
INSERT INTO `t_rubbish_recycling` VALUES ('280', '广东', '锦厦环境办沙浦头垃圾转运站', '东莞市锦厦沙浦头东十一巷', '22.804553', '113.801428');
INSERT INTO `t_rubbish_recycling` VALUES ('281', '广东', '东莞市茶山镇中心区垃圾转运站', '东莞市茶山南路', '23.062208', '113.86988');
INSERT INTO `t_rubbish_recycling` VALUES ('282', '广东', '高埗垃圾转运站', '东莞市振兴东八横路', '23.081514', '113.722058');
INSERT INTO `t_rubbish_recycling` VALUES ('283', '广东', '草塘垃圾转运站', '东莞市莞城南城路34号', '23.037312', '113.756262');
INSERT INTO `t_rubbish_recycling` VALUES ('284', '广东', '东城石井垃圾转运站', '东莞市石井大道', '23.026393', '113.799981');
INSERT INTO `t_rubbish_recycling` VALUES ('285', '广东', '东江西岸垃圾转运站', '东莞市永泰商业街', '23.035134', '113.728105');
INSERT INTO `t_rubbish_recycling` VALUES ('286', '广东', '莞城鸿裕垃圾转运站', '东莞市学院路191号', '23.04734', '113.76966');
INSERT INTO `t_rubbish_recycling` VALUES ('287', '广东', '圣堂社区垃圾转运站', '东莞市富源街', '22.944', '113.94145');
INSERT INTO `t_rubbish_recycling` VALUES ('288', '广东', '东城上桥垃圾转运站', '东莞市东城42', '23.048884', '113.760234');
INSERT INTO `t_rubbish_recycling` VALUES ('289', '广东', '东城堑头垃圾转运站', '东莞市堑何路27', '23.05184', '113.76291');
INSERT INTO `t_rubbish_recycling` VALUES ('290', '广东', '南城亨美垃圾转运站', '东莞市亨通路47号', '23.010895', '113.740715');
INSERT INTO `t_rubbish_recycling` VALUES ('291', '广东', '圣堂社区垃圾转运站（中心花园西北）', '东莞市美景中路', '22.92389', '113.926418');
INSERT INTO `t_rubbish_recycling` VALUES ('292', '广东', '圣堂社区垃圾转运站（红荔购物广场南）', '东莞市富源街', '22.944', '113.94145');
INSERT INTO `t_rubbish_recycling` VALUES ('293', '广东', '圣堂社区垃圾转运站（华成写字楼东北）', '东莞市富华中路', '22.943018', '113.933486');
INSERT INTO `t_rubbish_recycling` VALUES ('294', '广东', '圣堂社区垃圾转运站（图书馆大楼西）', '东莞市市辖区', '23.048884', '113.760234');
INSERT INTO `t_rubbish_recycling` VALUES ('295', '广东', '圣堂社区垃圾转运站（大朗镇综治信访维稳中心西）', '东莞市育才路', '22.838984', '114.173776');
INSERT INTO `t_rubbish_recycling` VALUES ('296', '广东', '南城周溪垃圾转运站', '东莞市周溪大道11号', '22.99768', '113.71213');
INSERT INTO `t_rubbish_recycling` VALUES ('297', '广东', '西湖工业区垃圾中转站', '东莞市石龙镇', '23.10529', '113.8743');
INSERT INTO `t_rubbish_recycling` VALUES ('298', '广东', '东城花园新村垃圾转运站', '东莞市市场路17号', '23.050607', '113.759317');
INSERT INTO `t_rubbish_recycling` VALUES ('299', '广东', '大朗巷头金岭一巷垃圾中转站', '东莞市富康一街172', '22.957187', '113.931832');
INSERT INTO `t_rubbish_recycling` VALUES ('300', '广东', '锦厦环境办中心市场垃圾转运站', '东莞市锦江三路', '22.79721', '113.798663');
INSERT INTO `t_rubbish_recycling` VALUES ('301', '广东', '石排镇中心垃圾压缩转运站', '东莞市工业大道', '22.762457', '114.104457');
INSERT INTO `t_rubbish_recycling` VALUES ('302', '广东', '垃圾转运站', '惠州市惠城区', '23.087252', '114.416646');
INSERT INTO `t_rubbish_recycling` VALUES ('303', '广东', '中山路垃圾中转站', '惠州市惠阳区中山二路18', '22.78301', '114.46954');
INSERT INTO `t_rubbish_recycling` VALUES ('304', '广东', '澳头垃圾转运站', '大亚湾澳头安惠大道', '22.718934', '114.53217');
INSERT INTO `t_rubbish_recycling` VALUES ('305', '广东', '仲恺红旗垃圾转运站', '惠州市惠风五路86号', '23.03763', '114.35035');
INSERT INTO `t_rubbish_recycling` VALUES ('306', '广东', '河南岸中心花园垃圾转运站', '惠州市惠城区银岭路六横街', '23.072032', '114.423138');
INSERT INTO `t_rubbish_recycling` VALUES ('307', '广东', '运进垃圾中转站', '惠州市惠城区', '23.087252', '114.416646');
INSERT INTO `t_rubbish_recycling` VALUES ('308', '广东', '广信垃圾压缩中转站', '珠海市香洲区', '22.271249', '113.55027');
INSERT INTO `t_rubbish_recycling` VALUES ('309', '广东', '白沙河垃圾压缩中转站', '珠海市香洲区白莲路', '22.250097', '113.571798');
INSERT INTO `t_rubbish_recycling` VALUES ('310', '广东', '港二路垃圾压缩中转站', '珠海市香洲区港二路21', '22.228085', '113.534783');
INSERT INTO `t_rubbish_recycling` VALUES ('311', '广东', '白莲路垃圾压缩中转站', '珠海市香洲区白莲路68', '22.250501', '113.572116');
INSERT INTO `t_rubbish_recycling` VALUES ('312', '广东', '景乐路垃圾压缩中转站', '珠海市景乐路10号', '22.24907', '113.58044');
INSERT INTO `t_rubbish_recycling` VALUES ('313', '广东', '河源垃圾中转站', '河源市源城区y272', '23.746255', '114.696828');
INSERT INTO `t_rubbish_recycling` VALUES ('314', '广东', '东门垃圾中转站', '河源市环城东路公园路路口', '23.734027', '114.699658');
INSERT INTO `t_rubbish_recycling` VALUES ('315', '广东', '环城南路垃圾中转站', '云浮市新兴县', '22.703204', '112.23083');
INSERT INTO `t_rubbish_recycling` VALUES ('316', '广东', '东昇花园垃圾中转站', '云浮市新兴县', '22.703204', '112.23083');
INSERT INTO `t_rubbish_recycling` VALUES ('317', '广东', '垃圾转运站', '广东省深圳市福田区红柳道1号', '22.50373', '114.05048');
INSERT INTO `t_rubbish_recycling` VALUES ('318', '广东', '垃圾转运站', '深圳市南山区', '22.531221', '113.92943');
INSERT INTO `t_rubbish_recycling` VALUES ('319', '广东', '皇岗垃圾转运站', '深圳市福田区皇岗吉龙一村109', '22.52709', '114.05901');
INSERT INTO `t_rubbish_recycling` VALUES ('320', '广东', '怡心垃圾转运站', '深圳市龙岗区', '22.721511', '114.251372');
INSERT INTO `t_rubbish_recycling` VALUES ('321', '广东', '茜坑垃圾转运站', '深圳市宝安区茜坑路17', '22.70275', '114.040212');
INSERT INTO `t_rubbish_recycling` VALUES ('322', '广东', '新沙垃圾转运站', '深圳市竹子林立交', '22.532815', '114.01732');
INSERT INTO `t_rubbish_recycling` VALUES ('323', '广东', '江边垃圾转运站', '深圳市宝安区', '22.754741', '113.828671');
INSERT INTO `t_rubbish_recycling` VALUES ('324', '广东', '坪环垃圾转运站', '深圳市龙岗区东纵路', '22.687164', '114.353833');
INSERT INTO `t_rubbish_recycling` VALUES ('325', '广东', '鹏湾垃圾转运站', '深圳市马庙街7号', '22.55428', '114.23693');
INSERT INTO `t_rubbish_recycling` VALUES ('326', '广东', '北斗垃圾转运站', '深圳市北斗路6-1号', '22.54328', '114.13489');
INSERT INTO `t_rubbish_recycling` VALUES ('327', '广东', '弓村垃圾转运站', '深圳市宝安区东环一路288', '22.656993', '114.026357');
INSERT INTO `t_rubbish_recycling` VALUES ('328', '广东', '麻布垃圾转运站', '深圳市宝安区', '22.754741', '113.828671');
INSERT INTO `t_rubbish_recycling` VALUES ('329', '广东', '简竹路垃圾转运站', '深圳市龙岗区', '22.721511', '114.251372');
INSERT INTO `t_rubbish_recycling` VALUES ('330', '广东', '竹子林垃圾转运站', '深圳市深南大道', '22.54533', '113.9098');
INSERT INTO `t_rubbish_recycling` VALUES ('331', '广东', '沙浦围2垃圾转运站', '深圳市宝安区', '22.754741', '113.828671');
INSERT INTO `t_rubbish_recycling` VALUES ('332', '广东', '锦程路垃圾转运站', '深圳市宝安区锦程路1021-9', '22.71341', '113.78123');
INSERT INTO `t_rubbish_recycling` VALUES ('333', '广东', '大水田垃圾转运站', '深圳市裕新路281', '22.547', '114.085947');
INSERT INTO `t_rubbish_recycling` VALUES ('334', '广东', '民田路垃圾转运站', '深圳市福田区', '22.541009', '114.05096');
INSERT INTO `t_rubbish_recycling` VALUES ('335', '广东', '九祥岭垃圾转运站', '深圳市南山区沙河西路4012', '22.582315', '113.955081');
INSERT INTO `t_rubbish_recycling` VALUES ('336', '广东', '黄沙坑垃圾转运站', '深圳市坪山新区合路和体育一路交汇处', '22.675484', '114.342194');
INSERT INTO `t_rubbish_recycling` VALUES ('337', '广东', '岭背东垃圾转运站', '深圳市龙岗区草堆街32号', '22.619262', '114.152582');
INSERT INTO `t_rubbish_recycling` VALUES ('338', '广东', '大宝路垃圾转运站', '深圳市大宝路83号', '22.5811', '113.91049');
INSERT INTO `t_rubbish_recycling` VALUES ('339', '广东', '槽头路垃圾转运站', '深圳市宝安区', '22.754741', '113.828671');
INSERT INTO `t_rubbish_recycling` VALUES ('340', '广东', '龙城公园垃圾转运站', '深圳市龙岗区清林路', '22.719619', '114.226985');
INSERT INTO `t_rubbish_recycling` VALUES ('341', '广东', '鹅公岭松园围垃圾转运站', '深圳市龙岗区', '22.721511', '114.251372');
INSERT INTO `t_rubbish_recycling` VALUES ('342', '广东', '邱屋街东宝龙垃圾转运站', '深圳市龙岗区鹏达路122号', '22.71465', '114.2617');
INSERT INTO `t_rubbish_recycling` VALUES ('343', '广东', '黄蜂岭垃圾转运站', '深圳市宝安区黄风岭工业大道2巷', '22.77512', '113.8083');
INSERT INTO `t_rubbish_recycling` VALUES ('344', '广东', '黄碧围垃圾转运站', '深圳市盐田区北山道138号', '22.59028', '114.26627');
INSERT INTO `t_rubbish_recycling` VALUES ('345', '广东', '星火路垃圾转运站', '深圳市龙岗区', '22.721511', '114.251372');
INSERT INTO `t_rubbish_recycling` VALUES ('346', '广东', '官龙村垃圾转运站', '深圳市南山区', '22.531221', '113.92943');
INSERT INTO `t_rubbish_recycling` VALUES ('347', '广东', '梅富村垃圾转运站', '深圳市景田东路景田北一街路口', '22.55658', '114.04396');
INSERT INTO `t_rubbish_recycling` VALUES ('348', '广东', '花果山垃圾转运站', '深圳市宝安区', '22.754741', '113.828671');
INSERT INTO `t_rubbish_recycling` VALUES ('349', '广东', '园墩路垃圾转运站', '深圳市龙岗区', '22.721511', '114.251372');
INSERT INTO `t_rubbish_recycling` VALUES ('350', '广东', '梅龙路垃圾转运站', '深圳市宝安区', '22.754741', '113.828671');
INSERT INTO `t_rubbish_recycling` VALUES ('351', '广东', '批发市场垃圾转运站', '深圳市龙岗区', '22.721511', '114.251372');
INSERT INTO `t_rubbish_recycling` VALUES ('352', '广东', '欢城广场垃圾中转站', '深圳市龙岗区后园街30号', '22.737663', '114.28049');
INSERT INTO `t_rubbish_recycling` VALUES ('353', '广东', '上步中路垃圾转运站', '深圳市福田区', '22.541009', '114.05096');
INSERT INTO `t_rubbish_recycling` VALUES ('354', '广东', '坂田五和垃圾转运站', '深圳市龙岗区永香西路11', '22.62233', '114.063915');
INSERT INTO `t_rubbish_recycling` VALUES ('355', '广东', '上沙南垃圾转运站', '深圳市上沙东村一巷4', '22.521055', '114.03064');
INSERT INTO `t_rubbish_recycling` VALUES ('356', '广东', '前进村垃圾中转站', '深圳市前进路', '22.583733', '113.890577');
INSERT INTO `t_rubbish_recycling` VALUES ('357', '广东', '蒲排村垃圾中转站', '深圳市龙岗区蒲排北九巷', '22.693789', '114.239719');
INSERT INTO `t_rubbish_recycling` VALUES ('358', '广东', '东海岸垃圾中转站', '深圳市盐田区', '22.555069', '114.235366');
INSERT INTO `t_rubbish_recycling` VALUES ('359', '广东', '桔子坑垃圾中转站', '深圳市桔子坑105号', '22.61384', '114.13596');
INSERT INTO `t_rubbish_recycling` VALUES ('360', '广东', '华富市场垃圾转运站', '深圳市龙华新区龙华街道办', '22.657177', '114.024991');
INSERT INTO `t_rubbish_recycling` VALUES ('361', '广东', '新生广场垃圾转运站', '深圳市龙岗区龙凤路', '22.743305', '114.273234');
INSERT INTO `t_rubbish_recycling` VALUES ('362', '广东', '宝龙社区垃圾转运站', '深圳市龙岗区龙一路', '22.721511', '114.251372');
INSERT INTO `t_rubbish_recycling` VALUES ('363', '广东', '龙华市场垃圾转运站', '深圳市龙华街道办', '22.656453', '114.020569');
INSERT INTO `t_rubbish_recycling` VALUES ('364', '广东', '大浪龙胜垃圾转运站', '深圳市胜利路', '22.651638', '114.0142');
INSERT INTO `t_rubbish_recycling` VALUES ('365', '广东', '前海公园垃圾转运站', '深圳市月亮湾大道', '22.50794', '113.90256');
INSERT INTO `t_rubbish_recycling` VALUES ('366', '广东', '大浪同富垃圾转运站', '深圳市华兴路62号', '22.6787', '113.99471');
INSERT INTO `t_rubbish_recycling` VALUES ('367', '广东', '坂田小学垃圾转运站', '深圳市龙岗区发达路9', '22.631247', '114.075923');
INSERT INTO `t_rubbish_recycling` VALUES ('368', '广东', '文心公园垃圾转运站', '深圳市海德三道238号', '22.52063', '113.92941');
INSERT INTO `t_rubbish_recycling` VALUES ('369', '广东', '南山市场垃圾转运站', '深圳市南新路2010', '22.52607', '113.91952');
INSERT INTO `t_rubbish_recycling` VALUES ('370', '广东', '愉园市场垃圾转运站', '深圳市龙岗区吉福路35', '22.722151', '114.237251');
INSERT INTO `t_rubbish_recycling` VALUES ('371', '广东', '民治公园垃圾转运站', '深圳市阳光路民府路路口', '22.76874', '113.845251');
INSERT INTO `t_rubbish_recycling` VALUES ('372', '广东', '沙河市场垃圾转运站', '深圳市南山区快乐幼儿园附近', '22.54459', '113.97004');
INSERT INTO `t_rubbish_recycling` VALUES ('373', '广东', '万丰西区垃圾转运站', '深圳市晨光路', '22.701422', '114.232337');
INSERT INTO `t_rubbish_recycling` VALUES ('374', '广东', '八卦岭市场垃圾转运站', '深圳市八卦路34', '22.560451', '114.099408');
INSERT INTO `t_rubbish_recycling` VALUES ('375', '广东', '留仙洞工业区垃圾转运站', '深圳市南山区留仙村路25', '22.58531', '113.9291');
INSERT INTO `t_rubbish_recycling` VALUES ('376', '广东', '机场第四号垃圾中转站', '深圳市宝安区宝源路', '22.570618', '113.862502');
INSERT INTO `t_rubbish_recycling` VALUES ('377', '广东', '罗瑞合西街垃圾转运站', '深圳市瑞园五巷', '22.729591', '114.267163');
INSERT INTO `t_rubbish_recycling` VALUES ('378', '广东', '南联龙溪村垃圾转运站', '深圳市龙岗区龙溪路93', '22.716159', '114.270614');
INSERT INTO `t_rubbish_recycling` VALUES ('379', '广东', '凤凰景新北街垃圾转运站', '深圳市龙岗区', '22.721511', '114.251372');
INSERT INTO `t_rubbish_recycling` VALUES ('380', '广东', '志盛社区志健垃圾转运站', '深圳市龙岗区', '22.721511', '114.251372');
INSERT INTO `t_rubbish_recycling` VALUES ('381', '广东', '八卦篮球场垃圾转运站', '深圳市福田区', '22.541009', '114.05096');
INSERT INTO `t_rubbish_recycling` VALUES ('382', '广东', '辅城坳富盛路垃圾转运站', '深圳市嘉城路120-6', '22.691103', '114.108857');
INSERT INTO `t_rubbish_recycling` VALUES ('383', '广东', '大浪商业中心垃圾转运站', '深圳市华荣路359号', '22.673521', '113.997468');
INSERT INTO `t_rubbish_recycling` VALUES ('384', '广东', '简上工业区垃圾转运站', '深圳市简上路', '22.637042', '114.01569');
INSERT INTO `t_rubbish_recycling` VALUES ('385', '广东', '稔山工业区垃圾转运站', '深圳市福瑞路', '22.692296', '113.821925');
INSERT INTO `t_rubbish_recycling` VALUES ('386', '广东', '金丰工业区垃圾转运站', '深圳市永和路45号', '22.686673', '113.791582');
INSERT INTO `t_rubbish_recycling` VALUES ('387', '广东', '南约联合C工业区垃圾转运站', '深圳市南约村', '22.70275', '114.27482');
INSERT INTO `t_rubbish_recycling` VALUES ('388', '广东', '保安社区简一村垃圾转运站', '深圳市龙腾路', '22.667564', '113.945413');
INSERT INTO `t_rubbish_recycling` VALUES ('389', '广东', '四联社区大园街垃圾转运站', '深圳市龙岗区', '22.721511', '114.251372');
INSERT INTO `t_rubbish_recycling` VALUES ('390', '广东', '沿河路杨梅岗市场垃圾转运站', '深圳市龙岗区滨河路', '22.758653', '114.301975');
INSERT INTO `t_rubbish_recycling` VALUES ('391', '广东', '新田创新工业园垃圾转运站', '深圳市宝安区景田路', '22.699713', '114.084916');
INSERT INTO `t_rubbish_recycling` VALUES ('392', '广东', '六约社区深峰路垃圾转运站', '深圳市深峰路', '22.637078', '114.176878');
INSERT INTO `t_rubbish_recycling` VALUES ('393', '广东', '横岗社区新光村垃圾转运站', '深圳市裕民街8', '22.64579', '114.20345');
INSERT INTO `t_rubbish_recycling` VALUES ('394', '广东', '深圳市宝安国际机场三号垃圾转运站', '深圳市宝安区领航三路142', '22.621823', '113.816223');
INSERT INTO `t_rubbish_recycling` VALUES ('395', '广东', '清湖垃圾转运站', '深圳市龙华镇清湖工业园', '22.676295', '114.0469');
INSERT INTO `t_rubbish_recycling` VALUES ('396', '广东', '年丰垃圾转运站', '深圳市坪地镇年丰村', '22.764', '114.33527');
INSERT INTO `t_rubbish_recycling` VALUES ('397', '广东', '乌冲垃圾转运站', '深圳市鹏飞路54', '22.595155', '114.50545');
INSERT INTO `t_rubbish_recycling` VALUES ('398', '广东', '莲花垃圾转运站', '深圳市莲花北村莲花路与雨田路交叉路口', '22.560347', '114.054243');
INSERT INTO `t_rubbish_recycling` VALUES ('399', '广东', '侨香垃圾转运站', '深圳市侨香路3079号', '22.541027', '114.001403');
INSERT INTO `t_rubbish_recycling` VALUES ('400', '广东', '海滨垃圾转运站', '深圳市吉龙四街吉龙七街路口', '22.52514', '114.06659');
INSERT INTO `t_rubbish_recycling` VALUES ('401', '广东', '九街垃圾转运站', '深圳市深南大道', '22.54533', '113.9098');
INSERT INTO `t_rubbish_recycling` VALUES ('402', '广东', '兴围垃圾转运站', '深圳市兴围大道9', '22.638128', '113.832093');
INSERT INTO `t_rubbish_recycling` VALUES ('403', '广东', '发方垃圾转运站', '深圳市富临路38号', '22.79896', '114.33713');
INSERT INTO `t_rubbish_recycling` VALUES ('404', '广东', '沙坣垃圾转运站', '深圳市瑞景路', '22.683985', '114.372004');
INSERT INTO `t_rubbish_recycling` VALUES ('405', '广东', '新大垃圾转运站', '深圳市地质公园路', '22.53147', '114.521363');
INSERT INTO `t_rubbish_recycling` VALUES ('406', '广东', '水头垃圾转运站', '深圳市新大路', '22.555028', '114.50247');
INSERT INTO `t_rubbish_recycling` VALUES ('407', '广东', '水斗垃圾转运站', '深圳市东环二路39号', '22.638495', '114.05123');
INSERT INTO `t_rubbish_recycling` VALUES ('408', '广东', '清华垃圾转运站', '深圳市清湖新村东六巷', '22.667208', '114.050719');
INSERT INTO `t_rubbish_recycling` VALUES ('409', '广东', '广清垃圾转运站', '深圳市清泉路', '22.662912', '114.035418');
INSERT INTO `t_rubbish_recycling` VALUES ('410', '广东', '创艺垃圾转运站', '深圳市创艺路6号', '22.66013', '114.00444');
INSERT INTO `t_rubbish_recycling` VALUES ('411', '广东', '龙井垃圾转运站', '深圳市龙珠大道53', '22.564899', '113.974573');
INSERT INTO `t_rubbish_recycling` VALUES ('412', '广东', '松涛垃圾转运站', '深圳市宝利来步行街', '22.763197', '113.852923');
INSERT INTO `t_rubbish_recycling` VALUES ('413', '广东', '潭头垃圾转运站', '深圳市松裕路25', '22.7548', '113.843875');
INSERT INTO `t_rubbish_recycling` VALUES ('414', '广东', '大和垃圾转运站', '深圳市大和路149号', '22.70385', '114.04786');
INSERT INTO `t_rubbish_recycling` VALUES ('415', '广东', '横岭垃圾转运站', '深圳市民丰路101', '22.612255', '114.045475');
INSERT INTO `t_rubbish_recycling` VALUES ('416', '广东', '新阁垃圾转运站', '深圳市梅东一路6', '22.570325', '114.06689');
INSERT INTO `t_rubbish_recycling` VALUES ('417', '广东', '竹园垃圾转运站', '深圳市农林路55号', '22.54217', '114.01383');
INSERT INTO `t_rubbish_recycling` VALUES ('418', '广东', '天安垃圾转运站', '深圳市泰然十路泰然五路路口', '22.53022', '114.03093');
INSERT INTO `t_rubbish_recycling` VALUES ('419', '广东', '上合垃圾转运站', '深圳市上合路振华学校附近', '22.57722', '113.8999');
INSERT INTO `t_rubbish_recycling` VALUES ('420', '广东', '大新垃圾转运站', '深圳市南头街333号', '22.53632', '113.91583');
INSERT INTO `t_rubbish_recycling` VALUES ('421', '广东', '朝阳围垃圾转运站', '深圳市盐田北四街东海道路口', '22.5905', '114.25364');
INSERT INTO `t_rubbish_recycling` VALUES ('422', '广东', '怡心路垃圾转运站', '深圳市观澜大道西观澜医院附近', '22.711053', '114.044');
INSERT INTO `t_rubbish_recycling` VALUES ('423', '广东', '良安田垃圾转运站', '深圳市丹平公路239号', '22.663979', '114.150114');
INSERT INTO `t_rubbish_recycling` VALUES ('424', '广东', '登山口垃圾转运站', '深圳市梧桐路2024号', '22.55649', '114.23348');
INSERT INTO `t_rubbish_recycling` VALUES ('425', '广东', '通心岭垃圾转运站', '深圳市同德路18', '22.713069', '114.315336');
INSERT INTO `t_rubbish_recycling` VALUES ('426', '广东', '华强北垃圾转运站', '深圳市华强北地铁站', '22.543766', '114.085727');
INSERT INTO `t_rubbish_recycling` VALUES ('427', '广东', '华强南垃圾转运站', '深圳市福华路10', '22.538275', '114.08648');
INSERT INTO `t_rubbish_recycling` VALUES ('428', '广东', '彩田村垃圾转运站', '深圳市北环大道辅路', '22.560961', '114.051148');
INSERT INTO `t_rubbish_recycling` VALUES ('429', '广东', '松元厦垃圾转运站', '深圳市观光路1028号', '22.739593', '113.945139');
INSERT INTO `t_rubbish_recycling` VALUES ('430', '广东', '福华村垃圾转运站', '深圳市皇岗路2001-2', '22.534985', '114.07255');
INSERT INTO `t_rubbish_recycling` VALUES ('431', '广东', '上沙北垃圾转运站', '深圳市上沙椰树路和福强路交叉口', '22.520631', '114.052188');
INSERT INTO `t_rubbish_recycling` VALUES ('432', '广东', '红星（2）垃圾转运站', '深圳市河滨南路', '22.767918', '113.837675');
INSERT INTO `t_rubbish_recycling` VALUES ('433', '广东', '沙浦围垃圾转运站', '深圳市沙浦围工业大道', '22.775144', '113.825596');
INSERT INTO `t_rubbish_recycling` VALUES ('434', '广东', '宝民路垃圾转运站', '深圳市宝民一路223号', '22.56971', '113.89379');
INSERT INTO `t_rubbish_recycling` VALUES ('435', '广东', '牛角龙垃圾转运站', '深圳市锦龙大道', '22.679164', '114.32807');
INSERT INTO `t_rubbish_recycling` VALUES ('436', '广东', '圳埔岭垃圾转运站', '深圳市南联街道', '22.717227', '114.261954');
INSERT INTO `t_rubbish_recycling` VALUES ('437', '广东', '水头沙垃圾转运站', '深圳市东沙路', '22.549313', '114.485242');
INSERT INTO `t_rubbish_recycling` VALUES ('438', '广东', '下水径垃圾转运站', '深圳市吉华路243号', '22.61286', '114.1109');
INSERT INTO `t_rubbish_recycling` VALUES ('439', '广东', '松岭南垃圾转运站', '深圳市松岭路2号', '22.53572', '114.09471');
INSERT INTO `t_rubbish_recycling` VALUES ('440', '广东', '同心南垃圾转运站', '深圳市同心南路2', '22.762518', '114.319407');
INSERT INTO `t_rubbish_recycling` VALUES ('441', '广东', '凯丰南垃圾转运站', '深圳市奥士达路7号', '22.56375', '114.06058');
INSERT INTO `t_rubbish_recycling` VALUES ('442', '广东', '振华西垃圾转运站', '深圳市振华西路附近', '22.544496', '114.075875');
INSERT INTO `t_rubbish_recycling` VALUES ('443', '广东', '景田南垃圾转运站', '深圳市景田南路28号', '22.54385', '114.04383');
INSERT INTO `t_rubbish_recycling` VALUES ('444', '广东', '桃源居垃圾转运站', '深圳市洲石路', '22.641201', '113.864443');
INSERT INTO `t_rubbish_recycling` VALUES ('445', '广东', '大望村垃圾压缩转运站', '深圳市罗湖区大望大道', '22.603329', '114.172377');
INSERT INTO `t_rubbish_recycling` VALUES ('446', '广东', '爱联岗贝垃圾转运站', '深圳市彩云一路10号', '22.70566', '114.24213');
INSERT INTO `t_rubbish_recycling` VALUES ('447', '广东', '环观中路垃圾转运站', '深圳市环观中路45号', '22.70423', '114.0584');
INSERT INTO `t_rubbish_recycling` VALUES ('448', '广东', '莲花一村垃圾转运站', '深圳市笋岗西街4001号', '22.56434', '114.11316');
INSERT INTO `t_rubbish_recycling` VALUES ('449', '广东', '丹梓大道垃圾转运站', '深圳市启一路', '22.720002', '114.360795');
INSERT INTO `t_rubbish_recycling` VALUES ('450', '广东', '花卉世界垃圾转运站', '深圳市笋岗西路附近', '22.556162', '114.086158');
INSERT INTO `t_rubbish_recycling` VALUES ('451', '广东', '应人石路垃圾转运站', '深圳市应人石路附近', '22.647754', '113.927636');
INSERT INTO `t_rubbish_recycling` VALUES ('452', '广东', '民主大道垃圾转运站', '深圳市蚝三民主丰泽园', '22.73526', '113.78402');
INSERT INTO `t_rubbish_recycling` VALUES ('453', '广东', '葵涌三溪垃圾转运站', '深圳市金岭路6号', '22.639503', '114.420143');
INSERT INTO `t_rubbish_recycling` VALUES ('454', '广东', '盐田四村垃圾转运站', '深圳市永安北三街2北门', '22.595706', '114.24676');
INSERT INTO `t_rubbish_recycling` VALUES ('455', '广东', '东和公园垃圾转运站', '深圳市海涛路66号', '22.54676', '114.23429');
INSERT INTO `t_rubbish_recycling` VALUES ('456', '广东', '园岭三街垃圾转运站', '深圳市园岭三街8', '22.550548', '114.10133');
INSERT INTO `t_rubbish_recycling` VALUES ('457', '广东', '桂园北垃圾转运站', '深圳市蛟湖路', '22.550117', '114.117451');
INSERT INTO `t_rubbish_recycling` VALUES ('458', '广东', '福田南垃圾转运站', '深圳市福田路32号', '22.53428', '114.07732');
INSERT INTO `t_rubbish_recycling` VALUES ('459', '广东', '景田西垃圾转运站', '深圳市景田西路36号', '22.548434', '114.041062');
INSERT INTO `t_rubbish_recycling` VALUES ('460', '广东', '黄龙陂二巷垃圾转运站', '黄龙陂三巷黄龙陂二巷路口', '22.7221', '114.25962');
INSERT INTO `t_rubbish_recycling` VALUES ('461', '广东', '新坡塘简易垃圾转运站', '深圳市松柏路健民医药商店附近', '22.646397', '114.209591');
INSERT INTO `t_rubbish_recycling` VALUES ('462', '广东', '深圳城管泰然垃圾转运站', '深圳市泰然六路泰然文化广场附近', '22.53304', '114.02823');
INSERT INTO `t_rubbish_recycling` VALUES ('463', '广东', '保税区东垃圾转运站', '深圳市福田区桃花路', '22.504798', '114.056514');
INSERT INTO `t_rubbish_recycling` VALUES ('464', '广东', '保税区西垃圾转运站', '深圳市福田区香樟道', '22.508271', '114.043566');
INSERT INTO `t_rubbish_recycling` VALUES ('465', '广东', '新和路东垃圾转运站', '深圳市新和大道28号', '22.74041', '113.8405');
INSERT INTO `t_rubbish_recycling` VALUES ('466', '广东', '葵涌葵新（二站）垃圾转运站', '深圳市葵丰社区公园东北门附近', '22.629133', '114.416407');
INSERT INTO `t_rubbish_recycling` VALUES ('467', '广东', '马西垃圾转运站', '深圳市建设路14-1', '22.65625', '114.03467');
INSERT INTO `t_rubbish_recycling` VALUES ('468', '广东', '龙园垃圾转运站', '深圳市龙平东路321号', '22.734625', '114.263892');
INSERT INTO `t_rubbish_recycling` VALUES ('469', '广东', '田心垃圾转运站', '深圳市田荣路7号', '22.55001', '114.23131');
INSERT INTO `t_rubbish_recycling` VALUES ('470', '广东', '吉祥路垃圾转运站', '深圳市吉祥路9号', '22.67729', '113.94137');
INSERT INTO `t_rubbish_recycling` VALUES ('471', '广东', '文光村垃圾转运站', '深圳市西丽南路23号', '22.57139', '113.95041');
INSERT INTO `t_rubbish_recycling` VALUES ('472', '广东', '高新南垃圾转运站', '深圳市高新南四道11', '22.535857', '113.949096');
INSERT INTO `t_rubbish_recycling` VALUES ('473', '广东', '大王山垃圾转运站', '深圳市大上山工业二路', '22.482669', '113.913932');
INSERT INTO `t_rubbish_recycling` VALUES ('474', '广东', '中山路垃圾转运站', '深圳市大鹏镇中山路', '22.595468', '114.47925');
INSERT INTO `t_rubbish_recycling` VALUES ('475', '广东', '黄阁路垃圾转运站', '深圳市白灰围二路132号', '22.721263', '114.220963');
INSERT INTO `t_rubbish_recycling` VALUES ('476', '广东', '黄木岗垃圾转运站', '深圳市笋岗西路3009号', '22.55533', '114.08114');
INSERT INTO `t_rubbish_recycling` VALUES ('477', '广东', '红荔村垃圾转运站', '深圳市华发北路102', '22.548583', '114.08934');
INSERT INTO `t_rubbish_recycling` VALUES ('478', '广东', '船步街垃圾转运站', '深圳市和平路1155', '22.5359', '114.11555');
INSERT INTO `t_rubbish_recycling` VALUES ('479', '广东', '新围村垃圾转运站', '深圳市新高路109', '22.58503', '113.9504');
INSERT INTO `t_rubbish_recycling` VALUES ('480', '广东', '岗厦村垃圾转运站', '深圳市岗厦村东二坊和岗厦统建街交叉口', '22.53742', '114.071479');
INSERT INTO `t_rubbish_recycling` VALUES ('481', '广东', '玉泉路垃圾转运站', '深圳市玉泉路1-15', '22.546071', '113.932129');
INSERT INTO `t_rubbish_recycling` VALUES ('482', '广东', '后海村垃圾转运站', '深圳市登良路14-10南油（B区）', '22.510435', '113.9294');
INSERT INTO `t_rubbish_recycling` VALUES ('483', '广东', '薯田埔垃圾转运站', '深圳市科裕一路', '22.785879', '113.863551');
INSERT INTO `t_rubbish_recycling` VALUES ('484', '广东', '荔园路垃圾转运站', '深圳市桥和路', '22.688854', '113.797029');
INSERT INTO `t_rubbish_recycling` VALUES ('485', '广东', '花果路垃圾转运站', '深圳市花果路16号', '22.48859', '113.92987');
INSERT INTO `t_rubbish_recycling` VALUES ('486', '广东', '金康路垃圾转运站', '深圳市金康路84', '22.75432', '114.40117');
INSERT INTO `t_rubbish_recycling` VALUES ('487', '广东', '友谊路垃圾转运站', '深圳市友谊路5号', '22.74576', '114.23505');
INSERT INTO `t_rubbish_recycling` VALUES ('488', '广东', '福田村垃圾转运站', '深圳市福安街135号', '22.53404', '114.07879');
INSERT INTO `t_rubbish_recycling` VALUES ('489', '广东', '龙胜村垃圾转运站', '深圳市宝华路龙胜科技楼附近', '22.64908', '114.01634');
INSERT INTO `t_rubbish_recycling` VALUES ('490', '广东', '沙尾村垃圾转运站', '深圳市沙尾路92-94', '22.520265', '114.04172');
INSERT INTO `t_rubbish_recycling` VALUES ('491', '广东', '松坪山垃圾转运站', '深圳市乌石头路18-3', '22.558746', '113.949316');
INSERT INTO `t_rubbish_recycling` VALUES ('492', '广东', '南山村垃圾转运站', '深圳市老四川附近', '22.66813', '113.80229');
INSERT INTO `t_rubbish_recycling` VALUES ('493', '广东', '燕川社区垃圾压缩中转站', '深圳市广田路49号', '22.80502', '113.86849');
INSERT INTO `t_rubbish_recycling` VALUES ('494', '广东', '荷花市场垃圾压缩中转站', '深圳市罗湖区', '22.555341', '114.123885');
INSERT INTO `t_rubbish_recycling` VALUES ('495', '广东', '黄阁南路垃圾转运站', '深圳市龙岗区', '22.721511', '114.251372');
INSERT INTO `t_rubbish_recycling` VALUES ('496', '广东', '布吉广场垃圾中转站', '深圳市吉政路31-1', '22.606105', '114.127256');
INSERT INTO `t_rubbish_recycling` VALUES ('497', '广东', '市民广场垃圾转运站', '深圳市上星路', '22.721158', '113.833058');
INSERT INTO `t_rubbish_recycling` VALUES ('498', '广东', '新安五路垃圾转运站', '深圳市新安五路74-76', '22.57289', '113.884463');
INSERT INTO `t_rubbish_recycling` VALUES ('499', '广东', '工业路南垃圾转运站', '深圳市中华路1号', '22.63958', '114.03453');
INSERT INTO `t_rubbish_recycling` VALUES ('500', '广东', '风和日丽垃圾转运站', '深圳市龙峰一路223-225', '22.642875', '114.03014');
INSERT INTO `t_rubbish_recycling` VALUES ('501', '广东', '钟屋一路垃圾转运站', '深圳市三围北路', '22.618111', '113.844114');
INSERT INTO `t_rubbish_recycling` VALUES ('502', '广东', '爱联新屯垃圾转运站', '深圳市牛仔园新村一巷新屯南路路口', '22.69909', '114.23958');
INSERT INTO `t_rubbish_recycling` VALUES ('503', '广东', '北环东路垃圾转运站', '深圳市北环路', '22.740011', '113.829111');
INSERT INTO `t_rubbish_recycling` VALUES ('504', '广东', '龙岭公厕垃圾转运站', '深圳市龙岭路19-2', '22.604745', '114.115915');
INSERT INTO `t_rubbish_recycling` VALUES ('505', '广东', '环镇南路垃圾转运站', '鹏诚飞科技发展（深圳）有限公司附近', '22.712712', '113.81171');
INSERT INTO `t_rubbish_recycling` VALUES ('506', '广东', '机场九道垃圾转运站', '深圳市机场六道1040', '22.648275', '113.822545');
INSERT INTO `t_rubbish_recycling` VALUES ('507', '广东', '宝民二路垃圾转运站', '深圳市130乡道', '22.59106', '113.882524');
INSERT INTO `t_rubbish_recycling` VALUES ('508', '广东', '福宁村一巷垃圾转运站', '深圳市上宁四巷', '22.732157', '114.272717');
INSERT INTO `t_rubbish_recycling` VALUES ('509', '广东', '南山区奥海垃圾转运站', '深圳市兴南路23', '22.510947', '113.929634');
INSERT INTO `t_rubbish_recycling` VALUES ('510', '广东', '松坪山（二）垃圾转运站', '深圳市朗山一路6号', '22.55824', '113.9368');

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
-- Table structure for t_sewage_treatment_plant
-- ----------------------------
DROP TABLE IF EXISTS `t_sewage_treatment_plant`;
CREATE TABLE `t_sewage_treatment_plant` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `city` varchar(20) NOT NULL COMMENT '所属区域',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `address` varchar(500) NOT NULL COMMENT '详细地址',
  `lat` varchar(50) NOT NULL COMMENT '纬度',
  `lng` varchar(50) NOT NULL COMMENT '经度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8 COMMENT='污水处理厂';

-- ----------------------------
-- Records of t_sewage_treatment_plant
-- ----------------------------
INSERT INTO `t_sewage_treatment_plant` VALUES ('1', '北京 ', '北京城市排水集团北小河污水处理厂 ', '北京市朝阳区大屯乡辛店村甲162号', '40.01079', '116.42473');
INSERT INTO `t_sewage_treatment_plant` VALUES ('2', '北京 ', '北京市自来水集团檀州污水处理有限责任公司 ', '北京市北京市密云县', '40.369469', '116.843798');
INSERT INTO `t_sewage_treatment_plant` VALUES ('3', '北京 ', '北京燕山威立雅水务有限公司（牛口峪污水处理厂） ', '北京市北京市房山区迎风街道', '39.68986', '115.96185');
INSERT INTO `t_sewage_treatment_plant` VALUES ('4', '北京 ', '北京城市排水集团方庄污水处理厂 ', '江苏省苏州市吴中区', '31.13071', '120.46302');
INSERT INTO `t_sewage_treatment_plant` VALUES ('5', '北京 ', '中航云岗污水处理厂 ', '北京市北京市丰台区', '39.8519', '116.11919');
INSERT INTO `t_sewage_treatment_plant` VALUES ('6', '北京 ', '北京城市排水集团高碑店污水处理厂 ', '河北省保定市高碑店市', '39.328099', '115.861895');
INSERT INTO `t_sewage_treatment_plant` VALUES ('7', '北京 ', '密云县太师屯镇污水处理厂 ', '北京市北京市密云县', '40.53735', '117.12821');
INSERT INTO `t_sewage_treatment_plant` VALUES ('8', '北京 ', '北京市怀柔区污水处理厂 ', '北京市北京市怀柔区', '40.324272', '116.637122');
INSERT INTO `t_sewage_treatment_plant` VALUES ('9', '北京 ', '北京兴水水务有限责任公司污水处理厂 ', '北京市北京市大兴区', '39.708918', '116.330015');
INSERT INTO `t_sewage_treatment_plant` VALUES ('10', '北京 ', '北京城市排水集团酒仙桥污水处理厂 ', '北京市朝阳区东风乡将台洼52号', '39.955588', '116.510721');
INSERT INTO `t_sewage_treatment_plant` VALUES ('11', '北京 ', '北京燕山威立雅水务有限公司（星城污水处理厂） ', '北京市北京市延庆县', '40.449864', '115.966763');
INSERT INTO `t_sewage_treatment_plant` VALUES ('12', '北京 ', '北京市自来水集团夏都缙阳污水处理有限公司 ', '北京市北京市朝阳区湖光中街2号', '39.99654', '116.46671');
INSERT INTO `t_sewage_treatment_plant` VALUES ('13', '北京 ', '北京中设水处理有限公司 ', '北京市北京市西城区广安门外大街178号', '39.88917', '116.33835');
INSERT INTO `t_sewage_treatment_plant` VALUES ('14', '北京 ', '北京市昌平区水务局小汤山污水处理厂 ', '北京市北京市昌平区昌平路25号', '40.21151', '116.22195');
INSERT INTO `t_sewage_treatment_plant` VALUES ('15', '北京 ', '北京金源经开污水处理有限公司 ', '北京市北京市大兴区', '39.786497', '116.503635');
INSERT INTO `t_sewage_treatment_plant` VALUES ('16', '北京 ', '北京肖家河污水处理有限公司 ', '北京市北京市海淀区', '40.01617', '116.28133');
INSERT INTO `t_sewage_treatment_plant` VALUES ('17', '北京 ', '北京城市排水集团清河污水处理厂 ', '北京市海淀区东升乡马房村村北', '40.04129', '116.36898');
INSERT INTO `t_sewage_treatment_plant` VALUES ('18', '北京 ', '北京市顺义区水质净化厂 ', '北京市北京市顺义区', '40.128936', '116.653525');
INSERT INTO `t_sewage_treatment_plant` VALUES ('19', '北京 ', '北京市昌平污水处理中心 ', '北京市北京市昌平区', '40.176771', '116.396417');
INSERT INTO `t_sewage_treatment_plant` VALUES ('20', '北京 ', '北京城市排水集团吴家村污水处理厂 ', '北京市丰台区卢沟桥乡大屯村建工二建砼搅拌站北侧 ', '39.880264', '116.263008');
INSERT INTO `t_sewage_treatment_plant` VALUES ('21', '北京 ', '北京卢南污水运营有限责任公司 ', '北京市丰台区西四环富丰桥往西2公里', '39.92602', '116.36875');
INSERT INTO `t_sewage_treatment_plant` VALUES ('22', '北京 ', '北京市自来水集团门城污水处理有限公司 ', '北京市北京市东城区', '39.904989', '116.405285');
INSERT INTO `t_sewage_treatment_plant` VALUES ('23', '北京 ', '北京市碧水污水处理厂 ', '北京市北京市延庆县', '40.449864', '115.966763');
INSERT INTO `t_sewage_treatment_plant` VALUES ('24', '北京 ', '北京城市排水集团小红门污水处理厂 ', '北京市朝阳区小红门乡肖村', '39.83532', '116.44053');
INSERT INTO `t_sewage_treatment_plant` VALUES ('25', '北京 ', '北京清晨水处理剂有限公司水处理分公司 ', '北京市北京市东城区', '39.904989', '116.405285');
INSERT INTO `t_sewage_treatment_plant` VALUES ('26', '北京 ', '重庆康达环保股份有限公司北京采育污水处理厂 ', '北京市北京市延庆县', '40.449864', '115.966763');
INSERT INTO `t_sewage_treatment_plant` VALUES ('27', '北京 ', '温泉太舟坞污水处理厂 ', '北京市北京市海淀区', '40.040645', '116.20791');
INSERT INTO `t_sewage_treatment_plant` VALUES ('28', '北京 ', '昌平四海镇污水处理厂 ', '北京市北京市延庆县', '40.53152', '116.41327');
INSERT INTO `t_sewage_treatment_plant` VALUES ('29', '北京 ', '北京天竺污水处理厂 ', '北京市北京市延庆县', '40.449864', '115.966763');
INSERT INTO `t_sewage_treatment_plant` VALUES ('30', '北京 ', '门头沟门城污水处理厂 ', '北京市门头沟区永定镇侯庄子村南', '39.9257', '116.125576');
INSERT INTO `t_sewage_treatment_plant` VALUES ('31', '北京 ', '顺义区污水处理厂 ', '北京市北京市顺义区', '40.128936', '116.653525');
INSERT INTO `t_sewage_treatment_plant` VALUES ('32', '天津 ', '天津创业环保股份有限公司东郊污水处理厂 ', '天津市天津市滨海新区', '39.071576', '117.717154');
INSERT INTO `t_sewage_treatment_plant` VALUES ('33', '天津 ', '天津世升水治理有限公司 ', '天津市天津市和平区', '39.125596', '117.190182');
INSERT INTO `t_sewage_treatment_plant` VALUES ('34', '天津 ', '天津市津南区环科污水处理有限公司 ', '天津市天津市津南区', '38.989577', '117.382549');
INSERT INTO `t_sewage_treatment_plant` VALUES ('35', '天津 ', '天津市大港区环科蓝天污水处理有限公司 ', '天津市天津市滨海新区第二大街3号', '39.034272', '117.683919');
INSERT INTO `t_sewage_treatment_plant` VALUES ('36', '天津 ', '天津市塘沽区环科新河污水处理有限公司 ', '天津市天津市滨海新区', '39.039893', '117.630638');
INSERT INTO `t_sewage_treatment_plant` VALUES ('37', '天津 ', '天津保税区天保市政公司污水处理厂 ', '天津市天津市滨海新区', '39.071576', '117.717154');
INSERT INTO `t_sewage_treatment_plant` VALUES ('38', '天津 ', '天津创业环保股份有限公司北辰污水处理厂 ', '天津市天津市南开区卫津南路76号', '39.08657', '117.18021');
INSERT INTO `t_sewage_treatment_plant` VALUES ('39', '天津 ', '天津市蓟县绿洁环卫管理有限公司污水处理厂 ', '天津市天津市滨海新区', '39.071576', '117.717154');
INSERT INTO `t_sewage_treatment_plant` VALUES ('40', '天津 ', '天津重科水处理有限公司污水处理厂 ', '天津市天津市滨海新区', '39.071576', '117.717154');
INSERT INTO `t_sewage_treatment_plant` VALUES ('41', '天津 ', '华静污水处理有限公司 ', '天津市静海开发区天海道东侧', '38.932304', '116.997615');
INSERT INTO `t_sewage_treatment_plant` VALUES ('42', '天津 ', '武清世晟污水处理厂 ', '天津市天津市武清区', '39.376925', '117.057959');
INSERT INTO `t_sewage_treatment_plant` VALUES ('43', '天津 ', '宝坻区环科碧水污水处理有限公司 ', '天津市天津市宝坻区苑北路13号', '39.716965', '117.308094');
INSERT INTO `t_sewage_treatment_plant` VALUES ('44', '天津 ', '西青区污水处理厂 ', '天津市天津市西青区', '39.139446', '117.012247');
INSERT INTO `t_sewage_treatment_plant` VALUES ('45', '天津 ', '蓟县益通污水处理厂 ', '天津市天津市滨海新区', '39.071576', '117.717154');
INSERT INTO `t_sewage_treatment_plant` VALUES ('46', '天津 ', '咸阳路污水处理厂 ', '天津市天津市滨海新区', '39.071576', '117.717154');
INSERT INTO `t_sewage_treatment_plant` VALUES ('47', '天津 ', '纪庄子污水处理厂 ', '天津市天津市滨海新区', '39.071576', '117.717154');
INSERT INTO `t_sewage_treatment_plant` VALUES ('48', '上海 ', '上海市曹杨水质净化厂 ', '上海市上海市普陀区', '31.24569', '121.4103');
INSERT INTO `t_sewage_treatment_plant` VALUES ('49', '上海 ', '上海阳晨排水运营有限公司闵行水质净化厂 ', '上海市上海市黄浦区南京西路231号', '31.231706', '121.472644');
INSERT INTO `t_sewage_treatment_plant` VALUES ('50', '上海 ', '上海市北郊水质净化厂 ', '上海市上海市黄浦区南京西路231号', '31.231706', '121.472644');
INSERT INTO `t_sewage_treatment_plant` VALUES ('51', '上海 ', '上海嘉定新城污水处理厂 ', '上海市上海市嘉定区', '31.33359', '121.27906');
INSERT INTO `t_sewage_treatment_plant` VALUES ('52', '上海 ', '闵行区污水处理厂 ', '上海市上海市闵行区', '31.138349', '121.378947');
INSERT INTO `t_sewage_treatment_plant` VALUES ('53', '上海 ', '上海市金山区水质净化厂 ', '上海市上海市金山区', '30.724697', '121.330736');
INSERT INTO `t_sewage_treatment_plant` VALUES ('54', '上海 ', '上海市曲阳水质净化厂 ', '上海市上海市黄浦区南京西路231号', '31.231706', '121.472644');
INSERT INTO `t_sewage_treatment_plant` VALUES ('55', '上海 ', '上海松江污水处理厂 ', '上海市上海市黄浦区南京西路231号', '31.231706', '121.472644');
INSERT INTO `t_sewage_treatment_plant` VALUES ('56', '上海 ', '上海市南汇区周浦水质净化厂 ', '上海市上海市浦东新区', '31.10793', '121.5819');
INSERT INTO `t_sewage_treatment_plant` VALUES ('57', '上海 ', '上海市阳晨排水运营有限公司龙华水质净化厂 ', '上海市上海市徐汇区龙华路2933号', '31.17558', '121.45155');
INSERT INTO `t_sewage_treatment_plant` VALUES ('58', '上海 ', '上海市天山水质净化厂 ', '上海市上海市黄浦区南京西路231号', '31.231706', '121.472644');
INSERT INTO `t_sewage_treatment_plant` VALUES ('59', '上海 ', '上海松江洞泾污水处理厂 ', '上海市上海市松江区', '31.08825', '121.27005');
INSERT INTO `t_sewage_treatment_plant` VALUES ('60', '上海 ', '上海市程桥水质净化厂 ', '上海市上海市长宁区', '31.190238', '121.37319');
INSERT INTO `t_sewage_treatment_plant` VALUES ('61', '上海 ', '上海阳晨排水运营有限公司长桥水质净化厂 ', '上海市龙漕路180号', '31.16959', '121.44125');
INSERT INTO `t_sewage_treatment_plant` VALUES ('62', '上海 ', '泗塘水质净化厂 ', '上海市上海市宝山区', '31.33466', '121.4576');
INSERT INTO `t_sewage_treatment_plant` VALUES ('63', '上海 ', '奉贤区排水运行管理中心污水处理厂 ', '上海市上海市奉贤区', '30.912345', '121.458472');
INSERT INTO `t_sewage_treatment_plant` VALUES ('64', '上海 ', '上海市城市排水市南运营有限公司白龙港水质净化厂 ', '上海市上海市浦东新区', '31.233277', '121.744125');
INSERT INTO `t_sewage_treatment_plant` VALUES ('65', '上海 ', '吴淞水质净化厂 ', '上海市宝山区海江路1号', '31.39957', '121.50307');
INSERT INTO `t_sewage_treatment_plant` VALUES ('66', '上海 ', '商塌污水处理厂 ', '上海市上海市黄浦区南京西路231号', '31.231706', '121.472644');
INSERT INTO `t_sewage_treatment_plant` VALUES ('67', '上海 ', '上海腾北实业有限公司 （腾北污水处理厂） ', '上海市上海市黄浦区南京西路231号', '31.231706', '121.472644');
INSERT INTO `t_sewage_treatment_plant` VALUES ('68', '上海 ', '上海复旦水务工程技术有限公司污水处理厂 ', '上海市上海市黄浦区南京西路231号', '31.231706', '121.472644');
INSERT INTO `t_sewage_treatment_plant` VALUES ('69', '上海 ', '上海练塘污水处理厂 ', '上海市上海市黄浦区南京西路231号', '31.231706', '121.472644');
INSERT INTO `t_sewage_treatment_plant` VALUES ('70', '上海 ', '上海城投徐泾污水处理有限公司 ', '上海市青浦区崧泽大道1800号', '31.18313', '121.26698');
INSERT INTO `t_sewage_treatment_plant` VALUES ('71', '上海 ', '上海松东水环境净化有限公司 ', '上海市上海市黄浦区南京西路231号', '31.231706', '121.472644');
INSERT INTO `t_sewage_treatment_plant` VALUES ('72', '上海 ', '上海石洞口城市污水处理管理有限公司 ', '上海市上海市宝山区', '31.453914', '121.406221');
INSERT INTO `t_sewage_treatment_plant` VALUES ('73', '上海 ', '上海嘉定环境建设管理有限公司污水处理厂 ', '上海市上海市嘉定区', '31.383524', '121.250333');
INSERT INTO `t_sewage_treatment_plant` VALUES ('74', '上海 ', '上海友联竹园第一污水处理投资发展有限公司 ', '上海市上海市黄浦区合肥路271号', '31.21431', '121.47478');
INSERT INTO `t_sewage_treatment_plant` VALUES ('75', '上海 ', '上海大众嘉定污水处理有限公司 ', '上海市上海市嘉定区建华路1号', '31.259455', '121.347355');
INSERT INTO `t_sewage_treatment_plant` VALUES ('76', '上海 ', '上海金山枫泾水质净化有限公司 ', '上海市上海市金山区', '30.89022', '121.0308');
INSERT INTO `t_sewage_treatment_plant` VALUES ('77', '上海 ', '南汇污水处理厂 ', '上海市上海市黄浦区南京西路231号', '31.231706', '121.472644');
INSERT INTO `t_sewage_treatment_plant` VALUES ('78', '上海 ', '上海松申水环境净化有限公司 ', '上海市上海市黄浦区南京西路231号', '31.231706', '121.472644');
INSERT INTO `t_sewage_treatment_plant` VALUES ('79', '上海 ', '上海朱家角污水处理工程建设有限公司污水处理厂 ', '上海市上海市青浦区', '31.10839', '121.051929');
INSERT INTO `t_sewage_treatment_plant` VALUES ('80', '上海 ', '松江区松西污水处理厂 ', '上海市上海市松江区', '31.03047', '121.223543');
INSERT INTO `t_sewage_treatment_plant` VALUES ('81', '上海 ', '青浦区污水处理厂（二期） ', '上海市上海市青浦区', '31.151209', '121.113021');
INSERT INTO `t_sewage_treatment_plant` VALUES ('82', '上海 ', '青浦区第二污水处理厂 ', '上海市上海市徐汇区枫林路333号', '31.192873', '121.457433');
INSERT INTO `t_sewage_treatment_plant` VALUES ('83', '上海 ', '虹口曲阳污水处理厂 ', '上海市上海市虹口区东江湾路444号', '31.271171', '121.480545');
INSERT INTO `t_sewage_treatment_plant` VALUES ('84', '上海 ', '金山区枫亭污水处理厂 ', '上海市上海市金山区', '30.724697', '121.330736');
INSERT INTO `t_sewage_treatment_plant` VALUES ('85', '上海 ', '金山区新江污水处理厂 ', '上海市上海市金山区', '30.73283', '121.36115');
INSERT INTO `t_sewage_treatment_plant` VALUES ('86', '上海 ', '奉贤区东部污水处理厂 ', '上海市上海市奉贤区', '30.912345', '121.458472');
INSERT INTO `t_sewage_treatment_plant` VALUES ('87', '上海 ', '奉贤区西部污水处理厂 ', '上海市上海市徐汇区桂林路100号', '31.15942', '121.41645');
INSERT INTO `t_sewage_treatment_plant` VALUES ('88', '上海 ', '崇明区城桥污水处理厂 ', '上海市上海市崇明县', '31.63191', '121.4037');
INSERT INTO `t_sewage_treatment_plant` VALUES ('89', '上海 ', '浦东区竹园第二污水处理厂 ', '江苏省苏州市张家港市', '31.87732', '120.513402');
INSERT INTO `t_sewage_treatment_plant` VALUES ('90', '广东 ', '深圳市水务集团有限公司滨河污水处理厂 ', '广东省深圳市福田区深南中路1019号', '22.54025', '114.1028');
INSERT INTO `t_sewage_treatment_plant` VALUES ('91', '广东 ', '广州市大坦沙污水处理厂 ', '广东省广州市荔湾区', '23.123418', '113.219768');
INSERT INTO `t_sewage_treatment_plant` VALUES ('92', '广东 ', '珠海力合环保有限公司污水处理厂 ', '广东省珠海市香洲区富柠街38号', '22.255899', '113.552724');
INSERT INTO `t_sewage_treatment_plant` VALUES ('93', '广东 ', '深圳市水务(集团)有限公司南山污水处理厂 ', '广东省深圳市福田区深南中路1019号', '22.54025', '114.1028');
INSERT INTO `t_sewage_treatment_plant` VALUES ('94', '广东 ', '南海发展股份有限公司排水事业总部桂城污水处理厂 ', '广东省佛山市南海区', '23.031604', '113.141379');
INSERT INTO `t_sewage_treatment_plant` VALUES ('95', '广东 ', '广州市从化水质净化厂 ', '广东省广州市越秀区', '23.125178', '113.280637');
INSERT INTO `t_sewage_treatment_plant` VALUES ('96', '广东 ', '广州经济技术开发区污水处理厂西区厂 ', '广东省广州市黄埔区', '23.061299', '113.526489');
INSERT INTO `t_sewage_treatment_plant` VALUES ('97', '广东 ', '东莞市凤岗虾公潭污水处理厂 ', '广东省东莞市', '22.72235', '114.17389');
INSERT INTO `t_sewage_treatment_plant` VALUES ('98', '广东 ', '鹤山市污水净化厂 ', '广东省江门市鹤山市', '22.768104', '112.961795');
INSERT INTO `t_sewage_treatment_plant` VALUES ('99', '广东 ', '广州市花都区新华净水厂 ', '广东省广州市花都区', '23.39205', '113.211184');
INSERT INTO `t_sewage_treatment_plant` VALUES ('100', '广东 ', '中山市污水处理公司 ', '广东省中山市', '22.521113', '113.382391');
INSERT INTO `t_sewage_treatment_plant` VALUES ('101', '广东 ', '深圳市水务(集团)有限公司罗芳污水处理厂 ', '广东省深圳市福田区深南中路1019号', '22.54025', '114.1028');
INSERT INTO `t_sewage_treatment_plant` VALUES ('102', '广东 ', '小梅沙污水处理厂 ', '广东省深圳市盐田区', '22.60289', '114.32685');
INSERT INTO `t_sewage_treatment_plant` VALUES ('103', '广东 ', '深圳市龙岗区平湖污水处理厂 ', '广东省深圳市龙岗区', '22.68612', '114.13215');
INSERT INTO `t_sewage_treatment_plant` VALUES ('104', '广东 ', '肇庆市污水净化处理厂 ', '广东省肇庆市端州区', '23.051546', '112.472529');
INSERT INTO `t_sewage_treatment_plant` VALUES ('105', '广东 ', '东莞常平陈屋贝污水处理厂 ', '广东省东莞市', '22.782771', '114.108932');
INSERT INTO `t_sewage_treatment_plant` VALUES ('106', '广东 ', '广州永和污水处理厂 ', '广东省广州市越秀区', '23.125178', '113.280637');
INSERT INTO `t_sewage_treatment_plant` VALUES ('107', '广东 ', '江门市文昌沙水质净化厂 ', '广东省江门市江海区', '22.558516', '113.08315');
INSERT INTO `t_sewage_treatment_plant` VALUES ('108', '广东 ', '三水区白坭镇污水处理厂 ', '广东省佛山市三水区', '23.182925', '112.896456');
INSERT INTO `t_sewage_treatment_plant` VALUES ('109', '广东 ', '深圳市水务(集团)有限公司盐田污水处理厂 ', '广东省深圳市福田区深南中路1019号', '22.54025', '114.1028');
INSERT INTO `t_sewage_treatment_plant` VALUES ('110', '广东 ', '阳江市第一净水厂 ', '广东省阳江市江城区', '21.859222', '111.975107');
INSERT INTO `t_sewage_treatment_plant` VALUES ('111', '广东 ', '深圳市龙岗区沙田污水处理厂 ', '广东省深圳市龙岗区龙翔大道9002号', '22.721511', '114.251372');
INSERT INTO `t_sewage_treatment_plant` VALUES ('112', '广东 ', '深圳市龙岗区龙田污水处理厂 ', '广东省深圳市龙岗区', '22.75496', '114.36212');
INSERT INTO `t_sewage_treatment_plant` VALUES ('113', '广东 ', '广州市猎德污水处理厂 ', '广东省广州市天河区', '23.110849', '113.349481');
INSERT INTO `t_sewage_treatment_plant` VALUES ('114', '广东 ', '清远市污水处理中心 ', '广东省清远市清城区', '23.704188', '113.036779');
INSERT INTO `t_sewage_treatment_plant` VALUES ('115', '广东 ', '佛山市南海区金沙镇污水处理厂 ', '广东省佛山市南海区', '23.09269', '113.023784');
INSERT INTO `t_sewage_treatment_plant` VALUES ('116', '广东 ', '东莞市东江水务有限公司市区污水处理厂 ', '广东省东莞市桥东路北五街200号', '23.034145', '114.124153');
INSERT INTO `t_sewage_treatment_plant` VALUES ('117', '广东 ', '东莞樟木头镇污水处理厂 ', '广东省东莞市', '22.782771', '114.108932');
INSERT INTO `t_sewage_treatment_plant` VALUES ('118', '广东 ', '顺德新城区污水处理厂 ', '广东省佛山市陈村镇龙桥油库前500米', '22.95414', '113.23477');
INSERT INTO `t_sewage_treatment_plant` VALUES ('119', '广东 ', '英德市西城污水处理厂 ', '广东省清远市英德市', '24.18612', '113.405404');
INSERT INTO `t_sewage_treatment_plant` VALUES ('120', '广东 ', '东莞市塘厦林村应急污水处理厂 ', '广东省东莞市', '22.782771', '114.108932');
INSERT INTO `t_sewage_treatment_plant` VALUES ('121', '广东 ', '观澜污水处理厂 ', '广东省深圳市罗湖区', '22.541459', '114.149823');
INSERT INTO `t_sewage_treatment_plant` VALUES ('122', '广东 ', '佛山市顺德区华清源环保有限公司（大门污水处理厂） ', '广东省佛山市顺德区', '22.817161', '113.182972');
INSERT INTO `t_sewage_treatment_plant` VALUES ('123', '广东 ', '横岗污水处理厂 ', '广东省深圳市罗湖区', '22.541459', '114.149823');
INSERT INTO `t_sewage_treatment_plant` VALUES ('124', '广东 ', '广州市番禺区前锋净水厂 ', '广东省广州市番禺区', '22.938582', '113.364619');
INSERT INTO `t_sewage_treatment_plant` VALUES ('125', '广东 ', '韶关市第一污水处理厂 ', '广东省韶关市武江区新龙路17号', '24.801322', '113.591544');
INSERT INTO `t_sewage_treatment_plant` VALUES ('126', '广东 ', '江门市新会区龙泉污水处理厂 ', '广东省江门市新会区', '22.414325', '113.169504');
INSERT INTO `t_sewage_treatment_plant` VALUES ('127', '广东 ', '佛山市南海小塘污水处理有限公司 ', '广东省佛山市南海区小塘站前路35号', '23.091093', '112.976973');
INSERT INTO `t_sewage_treatment_plant` VALUES ('128', '广东 ', '广州市沥滘污水处理厂 ', '广东省广州市海珠区', '23.056852', '113.314389');
INSERT INTO `t_sewage_treatment_plant` VALUES ('129', '广东 ', '广州西朗污水处理有限公司 ', '广西壮族自治区贵港市港北区', '23.0936', '109.602146');
INSERT INTO `t_sewage_treatment_plant` VALUES ('130', '广东 ', '东莞市塘厦石桥头污水处理厂 ', '广东省东莞市', '22.782771', '114.108932');
INSERT INTO `t_sewage_treatment_plant` VALUES ('131', '广东 ', '深圳市坂雪岗污水处理有限公司 ', '广东省深圳市龙岗区', '22.648397', '114.068695');
INSERT INTO `t_sewage_treatment_plant` VALUES ('132', '广东 ', '江门市丰乐污水处理厂 ', '广东省江门市蓬江区', '22.60498', '113.088666');
INSERT INTO `t_sewage_treatment_plant` VALUES ('133', '广东 ', '罗定市生活污水处理厂有限公司 ', '广东省云浮市罗定市', '22.765415', '111.578201');
INSERT INTO `t_sewage_treatment_plant` VALUES ('134', '广东 ', '茂名市第一污水处理厂 ', '广东省茂名市茂南区', '21.659751', '110.919229');
INSERT INTO `t_sewage_treatment_plant` VALUES ('135', '广东 ', '佛山市南海区丹灶横江生活污水处理厂 ', '广东省佛山市南海区', '23.09269', '113.023784');
INSERT INTO `t_sewage_treatment_plant` VALUES ('136', '广东 ', '东莞市凤岗雁田污水处理厂 ', '广东省东莞市', '22.697546', '114.167921');
INSERT INTO `t_sewage_treatment_plant` VALUES ('137', '广东 ', '梅州市清源水质净化中心 ', '广东省梅州市梅江区', '24.299112', '116.117582');
INSERT INTO `t_sewage_treatment_plant` VALUES ('138', '广东 ', '湛江市赤坎水质净化厂 ', '广东省湛江市赤坎区', '21.272645', '110.368925');
INSERT INTO `t_sewage_treatment_plant` VALUES ('139', '广东 ', '河源市污水处理厂 ', '广东省河源市源城区', '23.746266', '114.697802');
INSERT INTO `t_sewage_treatment_plant` VALUES ('140', '广东 ', '肇庆市羚山污水处理厂 ', '广东省肇庆市端州区', '23.08412', '112.53317');
INSERT INTO `t_sewage_treatment_plant` VALUES ('141', '广东 ', '云浮市德禹环保有限公司城区污水处理厂 ', '广东省云浮市云城区', '22.929801', '112.044439');
INSERT INTO `t_sewage_treatment_plant` VALUES ('142', '广东 ', '高要市城市污水处理厂 ', '广东省肇庆市高要市', '23.027694', '112.460846');
INSERT INTO `t_sewage_treatment_plant` VALUES ('143', '广东 ', '乐昌市污水处理厂 ', '广东省韶关市乐昌市', '25.128445', '113.352413');
INSERT INTO `t_sewage_treatment_plant` VALUES ('144', '广东 ', '连平县污水处理厂 ', '广东省河源市连平县东园大道1号', '24.364227', '114.495952');
INSERT INTO `t_sewage_treatment_plant` VALUES ('145', '广东 ', '南海发展股份有限公司排水事业总部平洲污水处理厂 ', '广东省佛山市南海区', '23.09269', '113.023784');
INSERT INTO `t_sewage_treatment_plant` VALUES ('146', '广东 ', '阳春市水质净化有限公司 ', '广东省阳江市阳春市环城中路78号', '22.169598', '111.7905');
INSERT INTO `t_sewage_treatment_plant` VALUES ('147', '广东 ', '佛山市南海罗村污水处理有限公司 ', '广东省佛山市南海区', '23.05303', '113.04813');
INSERT INTO `t_sewage_treatment_plant` VALUES ('148', '广东 ', '四会市城市污水处理有限公司 ', '广东省肇庆市四会市四会大道南24-28号', '23.340324', '112.695028');
INSERT INTO `t_sewage_treatment_plant` VALUES ('149', '广东 ', '广州市南沙污水处理厂 ', '广东省广州市南沙区', '22.794531', '113.53738');
INSERT INTO `t_sewage_treatment_plant` VALUES ('150', '广东 ', '始兴县污水处理厂 ', '广东省韶关市始兴县', '24.948364', '114.067205');
INSERT INTO `t_sewage_treatment_plant` VALUES ('151', '广东 ', '深圳市国祯环保科技股份有限公司布吉河水质净化厂 ', '广东省深圳市龙岗区', '22.60248', '114.1305');
INSERT INTO `t_sewage_treatment_plant` VALUES ('152', '广东 ', '坪山上洋污水处理厂 ', '广东深圳坪山新区田心村环境园路', '23.15177', '113.30486');
INSERT INTO `t_sewage_treatment_plant` VALUES ('153', '广东 ', '南雄市城市污水处理厂 ', '广东省韶关市南雄市三元里步行街17号', '25.115328', '114.311231');
INSERT INTO `t_sewage_treatment_plant` VALUES ('154', '广东 ', '坪地横岭污水处理厂 ', '广东省深圳市龙岗区', '22.7673', '114.31035');
INSERT INTO `t_sewage_treatment_plant` VALUES ('155', '广东 ', '东莞市塘厦白泥湖水质净化厂 ', '广东省东莞市', '22.81929', '114.11097');
INSERT INTO `t_sewage_treatment_plant` VALUES ('156', '广东 ', '珠海市城市排水有限公司污水处理厂 ', '广东省珠海市香洲区富柠街38号', '22.255899', '113.552724');
INSERT INTO `t_sewage_treatment_plant` VALUES ('157', '广东 ', '珠海威立雅水务污水处理公司 ', '广东省珠海市香洲区富柠街38号', '22.255899', '113.552724');
INSERT INTO `t_sewage_treatment_plant` VALUES ('158', '广东 ', '新之源污水处理有限公司(镇安厂) ', '广西壮族自治区贵港市桂平市大湾镇', '23.15342', '109.87744');
INSERT INTO `t_sewage_treatment_plant` VALUES ('159', '广东 ', '潮州市第一污水处理厂 ', '广东省潮州市湘桥区', '23.661701', '116.632301');
INSERT INTO `t_sewage_treatment_plant` VALUES ('160', '广东 ', '汕头龙珠水质净化厂 ', '广东省汕头市金平区金园路30号', '23.37102', '116.708463');
INSERT INTO `t_sewage_treatment_plant` VALUES ('161', '广东 ', '惠州市梅湖水质净化中心二期 ', '广东省惠州市惠城区', '23.13136', '114.37389');
INSERT INTO `t_sewage_treatment_plant` VALUES ('162', '广东 ', '新之源污水处理有限公司(沙岗厂) ', '广东省佛山市禅城区石湾沙岗村', '22.99481', '113.09278');
INSERT INTO `t_sewage_treatment_plant` VALUES ('163', '广东 ', '新之源污水处理有限公司(东鄱厂) ', '广东省佛山市东鄱北路38号', '23.036921', '113.097025');
INSERT INTO `t_sewage_treatment_plant` VALUES ('164', '广东 ', '佛山华清源环保有限公司容桂污水处理厂 ', '广东省佛山市顺德区', '22.817161', '113.182972');
INSERT INTO `t_sewage_treatment_plant` VALUES ('165', '广东 ', '珠海威立雅水务污水处理公司（北区） ', '广东省珠海市香洲区富柠街38号', '22.255899', '113.552724');
INSERT INTO `t_sewage_treatment_plant` VALUES ('166', '广东 ', '高明污水处理有限公司 ', '广东省佛山市高明区', '22.893855', '112.882123');
INSERT INTO `t_sewage_treatment_plant` VALUES ('167', '广东 ', '顺德区华盈环保水务有限公司（北窖） ', '广东省佛山市顺德区风华路21座', '22.75851', '113.281826');
INSERT INTO `t_sewage_treatment_plant` VALUES ('168', '广东 ', '中山小榄水务公司污水处理分公司 ', '广东省中山市', '22.617285', '113.24753');
INSERT INTO `t_sewage_treatment_plant` VALUES ('169', '广东 ', '佛山市驿岗污水处理有限公司 ', '广东省佛山市禅城区', '23.028762', '113.122717');
INSERT INTO `t_sewage_treatment_plant` VALUES ('170', '广东 ', '顺德区南和环保水务有限公司（勒流） ', '广东省佛山市顺德区风华路21座', '22.75851', '113.281826');
INSERT INTO `t_sewage_treatment_plant` VALUES ('171', '广东 ', '佛山汇之源西樵污水处理厂 ', '广东省佛山市三水区', '23.182925', '112.896456');
INSERT INTO `t_sewage_treatment_plant` VALUES ('172', '广东 ', '博罗县园洲镇污水处理厂 ', '广东惠州市博罗县园洲镇', '23.12556', '113.96477');
INSERT INTO `t_sewage_treatment_plant` VALUES ('173', '广东 ', '中山市污水处理公司二期 ', '广东省中山市', '22.521113', '113.382391');
INSERT INTO `t_sewage_treatment_plant` VALUES ('174', '广东 ', '罗定市生活污水处理厂二期 ', '广东省云浮市罗定市', '22.765415', '111.578201');
INSERT INTO `t_sewage_treatment_plant` VALUES ('175', '广东 ', '增城市荔城污水处理厂(一、二期) ', '广东省广州市增城区', '23.29005', '113.8236');
INSERT INTO `t_sewage_treatment_plant` VALUES ('176', '广东 ', '南海和顺和桂污水处理厂 ', '广东省佛山市南海区', '23.245878', '113.139453');
INSERT INTO `t_sewage_treatment_plant` VALUES ('177', '广东 ', '南海大沥城南污水处理厂 ', '广东省佛山市南海区', '23.09269', '113.023784');
INSERT INTO `t_sewage_treatment_plant` VALUES ('178', '广东 ', '南海西樵污水处理厂（二期） ', '广东省佛山市南海区', '23.09269', '113.023784');
INSERT INTO `t_sewage_treatment_plant` VALUES ('179', '广东 ', '顺德龙江第一污水处理厂 ', '广东省佛山市三水区', '23.182925', '112.896456');
INSERT INTO `t_sewage_treatment_plant` VALUES ('180', '广东 ', '顺德均安污水处理厂 ', '广东省佛山市顺德区德华十一街A72号', '22.954107', '113.102307');
INSERT INTO `t_sewage_treatment_plant` VALUES ('181', '广东 ', '高明三洲污水处理厂 ', '广东省佛山市高明区', '22.881722', '112.8413');
INSERT INTO `t_sewage_treatment_plant` VALUES ('182', '广东 ', '顺德陈村污水处理厂 ', '广东省佛山市顺德区陈村', '22.966821', '113.214705');
INSERT INTO `t_sewage_treatment_plant` VALUES ('183', '广东 ', '顺德杏坛污水处理厂 ', '广东省佛山市三水区', '23.182925', '112.896456');
INSERT INTO `t_sewage_treatment_plant` VALUES ('184', '广东 ', '博罗县污水处理厂 ', '广东省惠州市博罗县', '23.167575', '114.284254');
INSERT INTO `t_sewage_treatment_plant` VALUES ('185', '广东 ', '石湾污水处理厂 ', '广东省惠州市博罗县石湾镇湖山村', '23.12645', '113.86825');
INSERT INTO `t_sewage_treatment_plant` VALUES ('186', '广东 ', '龙溪污水处理厂 ', '广东省惠州市博罗县龙溪镇夏寮村球岗沟', '23.134775', '114.123095');
INSERT INTO `t_sewage_treatment_plant` VALUES ('187', '广东 ', '惠阳城区生活污水处理厂 ', '广东省惠州市惠阳区中心淡水镇古屋', '22.81681', '114.496652');
INSERT INTO `t_sewage_treatment_plant` VALUES ('188', '广东 ', '新会区双水镇生活污水厂 ', '广东省江门市新会区', '22.43362', '113.00359');
INSERT INTO `t_sewage_treatment_plant` VALUES ('189', '广东 ', '开平市迳头污水处理厂 ', '广东省江门市开平市', '22.349089', '112.701689');
INSERT INTO `t_sewage_treatment_plant` VALUES ('190', '广东 ', '台山市台城污水处理厂 ', '广东省台山市台城镇通济河下游白水桥段', '22.24747', '112.78398');
INSERT INTO `t_sewage_treatment_plant` VALUES ('191', '广东 ', '电白县污水处理厂 ', '广东省茂名市电白区', '21.507219', '111.007264');

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
-- Table structure for t_villa
-- ----------------------------
DROP TABLE IF EXISTS `t_villa`;
CREATE TABLE `t_villa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(20) NOT NULL COMMENT '城市',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `lat` varchar(50) NOT NULL COMMENT '纬度',
  `lng` varchar(50) NOT NULL COMMENT '经度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8 COMMENT='别墅区';

-- ----------------------------
-- Records of t_villa
-- ----------------------------
INSERT INTO `t_villa` VALUES ('1', '北京', '北京卓锦万代国际别墅区', '40.02313', '116.53285');
INSERT INTO `t_villa` VALUES ('2', '北京', '北京协和医院别墅区', '39.91329', '116.4189');
INSERT INTO `t_villa` VALUES ('3', '北京', '北京温泉别墅区', '40.13751', '117.0855');
INSERT INTO `t_villa` VALUES ('4', '北京', '北京中央别墅区', '39.904989', '116.405285');
INSERT INTO `t_villa` VALUES ('5', '北京', '北京雪梨澳乡别墅区', '40.04616', '116.33849');
INSERT INTO `t_villa` VALUES ('6', '北京', '北京建兴别墅区', '39.904989', '116.405285');
INSERT INTO `t_villa` VALUES ('7', '北京', '北京河南村别墅区', '40.10632', '116.69757');
INSERT INTO `t_villa` VALUES ('8', '北京', '北京毓秀园别墅区', '40.108578', '116.693971');
INSERT INTO `t_villa` VALUES ('9', '北京', '北京依水庄园别墅区', '40.138012', '116.406085');
INSERT INTO `t_villa` VALUES ('10', '北京', '北京郁金香温泉度假村-温泉别墅区', '40.13751', '117.0855');
INSERT INTO `t_villa` VALUES ('11', '天津', '天津滨海湖高尔夫中央别墅区', '39.13739', '117.56297');
INSERT INTO `t_villa` VALUES ('12', '天津', '天津金旺家园别墅区', '39.037672', '117.705622');
INSERT INTO `t_villa` VALUES ('13', '天津', '天津绮景家园普吉岛别墅区', '39.19866', '117.19549');
INSERT INTO `t_villa` VALUES ('14', '天津', '天津锦绣园别墅区', '39.00458', '117.35957');
INSERT INTO `t_villa` VALUES ('15', '天津', '天津市松江乡村俱乐部马会别墅区', '38.93953', '117.34397');
INSERT INTO `t_villa` VALUES ('16', '天津', '天津龙居园别墅区', '38.90062', '117.43665');
INSERT INTO `t_villa` VALUES ('17', '天津', '天津益农里别墅区', '38.929828', '116.943104');
INSERT INTO `t_villa` VALUES ('18', '上海', '上海莘闵别墅区', '31.088057', '121.338503');
INSERT INTO `t_villa` VALUES ('19', '上海', '上海中科大别墅区', '31.12629', '121.54568');
INSERT INTO `t_villa` VALUES ('20', '上海', '上海东郊宾馆别墅区', '29.654693', '94.362348');
INSERT INTO `t_villa` VALUES ('21', '上海', '上海新凤城别墅区', '31.27683', '121.51996');
INSERT INTO `t_villa` VALUES ('22', '上海', '上海东湖宾馆别墅区', '31.21563', '121.455');
INSERT INTO `t_villa` VALUES ('23', '上海', '上海三湘海尚别墅区', '31.34236', '121.47803');
INSERT INTO `t_villa` VALUES ('24', '上海', '上海怡静园别墅区', '31.20861', '121.43466');
INSERT INTO `t_villa` VALUES ('25', '上海', '上海华漕华美路100弄别墅区', '31.22115', '121.33384');
INSERT INTO `t_villa` VALUES ('26', '上海', '上海格兰云天别墅区', '31.1446', '121.44748');
INSERT INTO `t_villa` VALUES ('27', '上海', '上海罗南别墅区', '31.384414', '121.358676');
INSERT INTO `t_villa` VALUES ('28', '广东', '广州市盛景别墅区', '23.125178', '113.280637');
INSERT INTO `t_villa` VALUES ('29', '广东', '广州市凤凰城别墅区', '23.13353', '113.58202');
INSERT INTO `t_villa` VALUES ('30', '广东', '广州市荔芳园别墅区', '23.026529', '113.309633');
INSERT INTO `t_villa` VALUES ('31', '广东', '广州市桥虹花园别墅区', '22.94974', '113.39134');
INSERT INTO `t_villa` VALUES ('32', '广东', '广州市从化碧水新村阳光别墅区', '23.70372', '113.73244');
INSERT INTO `t_villa` VALUES ('33', '广东', '广州市海天花园别墅区', '23.04471', '113.295');
INSERT INTO `t_villa` VALUES ('34', '广东', '广州市世家公馆别墅区', '23.06385', '113.28699');
INSERT INTO `t_villa` VALUES ('35', '广东', '广州市公益别墅区', '23.40662', '113.21391');
INSERT INTO `t_villa` VALUES ('36', '广东', '广州市增城白水寨别墅区', '23.579462', '113.7628');
INSERT INTO `t_villa` VALUES ('37', '广东', '广州市莲花山高尔夫别墅区', '22.99929', '113.50163');
INSERT INTO `t_villa` VALUES ('38', '广东', '广州市官厅别墅区', '23.125178', '113.280637');
INSERT INTO `t_villa` VALUES ('39', '广东', '广州市高尔夫果岭别墅区', '23.63565', '113.6495');
INSERT INTO `t_villa` VALUES ('40', '广东', '梅州市梅县华侨城香港花园高级别墅区', '24.287302', '116.103556');
INSERT INTO `t_villa` VALUES ('41', '广东', '梅州市梅园新村别墅区', '24.275753', '116.127993');
INSERT INTO `t_villa` VALUES ('42', '广东', '梅州市汤湖热矿泥山庄别墅区', '24.37545', '115.92278');
INSERT INTO `t_villa` VALUES ('43', '广东', '中山市鎏金山别墅区', '22.494193', '113.412541');
INSERT INTO `t_villa` VALUES ('44', '广东', '中山市东河北别墅区', '22.55393', '113.39433');
INSERT INTO `t_villa` VALUES ('45', '广东', '中山市远洋城半山别墅区', '22.49521', '113.41462');
INSERT INTO `t_villa` VALUES ('46', '广东', '中山市大三角酒店别墅区', '22.521113', '113.382391');
INSERT INTO `t_villa` VALUES ('47', '广东', '中山市檀香山别墅区', '22.3348', '113.42466');
INSERT INTO `t_villa` VALUES ('48', '广东', '中山市雅居乐别墅区', '22.356572', '113.444211');
INSERT INTO `t_villa` VALUES ('49', '广东', '珠海市蓝天别墅区', '22.22023', '113.5583');
INSERT INTO `t_villa` VALUES ('50', '广东', '珠海市南湾水门仔别墅区', '22.192757', '113.30231');
INSERT INTO `t_villa` VALUES ('51', '广东', '湛江市三帆海景新邨别墅区', '21.22049', '110.41284');
INSERT INTO `t_villa` VALUES ('52', '广东', '清远市沿江花园别墅区', '24.78693', '112.37135');
INSERT INTO `t_villa` VALUES ('53', '广东', '江门市福泉新邨别墅区', '22.62696', '113.03406');
INSERT INTO `t_villa` VALUES ('54', '广东', '江门市台山富华重工别墅区', '22.264225', '112.781455');
INSERT INTO `t_villa` VALUES ('55', '广东', '江门市天力苑别墅区', '22.59925', '113.02915');
INSERT INTO `t_villa` VALUES ('56', '广东', '江门市荣誉市民别墅区', '22.590431', '113.094942');
INSERT INTO `t_villa` VALUES ('57', '广东', '江门市光华路别墅区', '22.378917', '112.701618');
INSERT INTO `t_villa` VALUES ('58', '广东', '广东台山上川岛猕猴省级自然保护区别墅区', '21.73742', '112.82856');
INSERT INTO `t_villa` VALUES ('59', '广东', '阳江市金骏园别墅区', '21.87985', '111.97499');
INSERT INTO `t_villa` VALUES ('60', '广东', '阳江市金碧湾别墅区', '21.859222', '111.975107');
INSERT INTO `t_villa` VALUES ('61', '广东', '阳江市新乐苑别墅区', '21.87382', '112.00246');
INSERT INTO `t_villa` VALUES ('62', '广东', '惠州市哈施塔特别墅区', '23.079404', '114.412599');
INSERT INTO `t_villa` VALUES ('63', '广东', '惠州市三角洲岛A型别墅区', '22.625085', '114.735368');
INSERT INTO `t_villa` VALUES ('64', '广东', '惠州市润园红花湖生态别墅区', '23.083618', '114.37269');
INSERT INTO `t_villa` VALUES ('65', '广东', '惠州市平层别墅区', '23.079404', '114.412599');
INSERT INTO `t_villa` VALUES ('66', '广东', '惠州市凤山别墅区', '23.07829', '114.38265');
INSERT INTO `t_villa` VALUES ('67', '广东', '揭阳市江滨花园别墅区', '23.54772', '116.38627');
INSERT INTO `t_villa` VALUES ('68', '广东', '东莞市西头别墅区', '22.78372', '113.69595');
INSERT INTO `t_villa` VALUES ('69', '广东', '东莞市年丰山庄别墅区', '23.03572', '113.81041');
INSERT INTO `t_villa` VALUES ('70', '广东', '东莞市蓝山锦湾别墅区', '22.70327', '114.18011');
INSERT INTO `t_villa` VALUES ('71', '广东', '东莞市天鹅湖别墅区', '22.98279', '113.9948');
INSERT INTO `t_villa` VALUES ('72', '广东', '东莞市华侨新村别墅区', '23.01431', '113.9675');
INSERT INTO `t_villa` VALUES ('73', '广东', '东莞市葡萄庄园别墅区', '23.04013', '113.72341');
INSERT INTO `t_villa` VALUES ('74', '广东', '东莞市沙头别墅区', '22.79183', '113.77577');
INSERT INTO `t_villa` VALUES ('75', '广东', '东莞市大岭古别墅区', '22.80977', '114.09038');
INSERT INTO `t_villa` VALUES ('76', '广东', '东莞市长荣别墅区', '22.81508', '113.80754');
INSERT INTO `t_villa` VALUES ('77', '广东', '佛山市城北别墅区', '22.88112', '113.21046');
INSERT INTO `t_villa` VALUES ('78', '广东', '佛山市2005佛山至尊别墅区', '23.028762', '113.122717');
INSERT INTO `t_villa` VALUES ('79', '广东', '佛山市中信山语湖别墅区', '23.278752', '113.114257');
INSERT INTO `t_villa` VALUES ('80', '广东', '佛山市后花园别墅区', '23.17203', '112.87334');
INSERT INTO `t_villa` VALUES ('81', '广东', '佛山市玉兰湖别墅区', '23.19029', '113.20511');
INSERT INTO `t_villa` VALUES ('82', '广东', '佛山市中海金沙湾别墅区', '23.141174', '113.208403');
INSERT INTO `t_villa` VALUES ('83', '广东', '佛山市博澳城别墅区', '22.84528', '113.20859');
INSERT INTO `t_villa` VALUES ('84', '广东', '肇庆市出头别墅区', '23.068314', '112.4581');
INSERT INTO `t_villa` VALUES ('85', '广东', '汕头市中信世贸花园别墅区', '23.36301', '116.73305');
INSERT INTO `t_villa` VALUES ('86', '广东', '汕头市香域水岸别墅区', '23.37285', '116.76822');
INSERT INTO `t_villa` VALUES ('87', '广东', '潮州市饶平绿岛度假村别墅区', '23.668171', '117.00205');
INSERT INTO `t_villa` VALUES ('88', '广东', '深圳市十二橡树庄园别墅区', '22.62508', '114.06469');
INSERT INTO `t_villa` VALUES ('89', '广东', '深圳市聚豪天下别墅区', '22.5657', '113.850686');
INSERT INTO `t_villa` VALUES ('90', '广东', '深圳市西坊别墅区', '22.756199', '113.842221');
INSERT INTO `t_villa` VALUES ('91', '广东', '深圳市波托菲诺别墅区', '22.54539', '113.97728');
INSERT INTO `t_villa` VALUES ('92', '广东', '深圳市谭海酒店别墅区', '22.57158', '113.87807');
INSERT INTO `t_villa` VALUES ('93', '广东', '深圳市西山别墅区', '22.75567', '113.84443');
INSERT INTO `t_villa` VALUES ('94', '广东', '深圳市南海花园别墅区', '22.505445', '113.92987');
INSERT INTO `t_villa` VALUES ('95', '广东', '深圳市中信高尔夫别墅区', '22.69966', '114.26792');
INSERT INTO `t_villa` VALUES ('96', '广东', '深圳市天籁别墅区', '22.69349', '114.26195');
INSERT INTO `t_villa` VALUES ('97', '广东', '深圳市和一新村别墅区', '22.713729', '113.788902');
INSERT INTO `t_villa` VALUES ('98', '广东', '深圳市谭罗别墅区', '22.663999', '113.993066');
INSERT INTO `t_villa` VALUES ('99', '广东', '深圳市万科天琴湾别墅区', '22.599545', '114.31717');
INSERT INTO `t_villa` VALUES ('100', '广东', '深圳市万科第五园别墅区', '22.61618', '114.067973');
INSERT INTO `t_villa` VALUES ('101', '广东', '深圳市桂花新村别墅区', '22.728499', '114.060886');
INSERT INTO `t_villa` VALUES ('102', '广东', '深圳市笔架山别墅区', '22.56148', '114.08155');
INSERT INTO `t_villa` VALUES ('103', '广东', '深圳市荔湖山庄别墅区', '22.548566', '114.099731');
INSERT INTO `t_villa` VALUES ('104', '广东', '深圳市金丰雅园别墅区', '22.735057', '113.796837');
INSERT INTO `t_villa` VALUES ('105', '广东', '深圳市云深处别墅区', '22.557435', '114.226715');
INSERT INTO `t_villa` VALUES ('106', '广东', '深圳市塘头又一村别墅区', '22.650891', '113.917829');
INSERT INTO `t_villa` VALUES ('107', '广东', '深圳市下油松别墅区', '22.643681', '114.038396');
INSERT INTO `t_villa` VALUES ('108', '广东', '深圳市共乐别墅区', '22.579864', '113.858632');

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
