DROP TABLE
IF EXISTS sys_webcode;

CREATE TABLE `sys_webcode` (
	`zid` INT (11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`uid` CHAR (32) DEFAULT '',
	`code_start` VARCHAR (100) DEFAULT '' UNIQUE COMMENT '编码起始',
	`date_apply` CHAR (6) DEFAULT '' COMMENT '日期参数',
	`min_number` INT (11) DEFAULT '100000' COMMENT '最小数字',
	`now_number` INT (11) DEFAULT '100000' COMMENT '当前数字',
	`code_note` VARCHAR (45) DEFAULT '' COMMENT '备注',
	`flag_date` INT (11) DEFAULT '1' COMMENT '是否日期列'
) COMMENT = '系统编码表';

DROP TABLE
IF EXISTS t_city;

CREATE TABLE t_city (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	uuid VARCHAR (50) COMMENT 'uuid',
	city_code VARCHAR (50) NOT NULL UNIQUE COMMENT '城市编码',
	`name` VARCHAR (100) NOT NULL UNIQUE COMMENT '城市中文名称',
	pinyin VARCHAR (100) COMMENT '城市名称拼音',
	air INT DEFAULT 0 COMMENT '空气质量 0 可查询 1 不可查询',
	pm INT DEFAULT 0 COMMENT 'pm2.5查询 0 可查询 1 不可查询',
	create_user VARCHAR (20) NOT NULL COMMENT '创建人',
	create_time datetime NOT NULL COMMENT '创建时间',
	update_user VARCHAR (20) NOT NULL COMMENT '最后修改人',
	update_time datetime NOT NULL COMMENT '最后修改时间'
) COMMENT '城市列表';

DROP TABLE
IF EXISTS t_user;

CREATE TABLE t_user (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	uuid VARCHAR (50) COMMENT 'uuid',
	user_code VARCHAR (50) NOT NULL UNIQUE COMMENT '用户编码',
	phone VARCHAR (20) NOT NULL UNIQUE COMMENT '手机号',
	`password` VARCHAR (50) NOT NULL COMMENT '密码',
	nick_name VARCHAR (50) DEFAULT '' UNIQUE COMMENT '昵称',
	head_pic VARCHAR (500) DEFAULT '' COMMENT '头像',
	e_mail VARCHAR (100) DEFAULT '' COMMENT '邮箱',
	is_login INT DEFAULT 0 COMMENT '是否已登录 0 已登录 1 已登出',
	create_user VARCHAR (20) NOT NULL COMMENT '创建人',
	create_time datetime NOT NULL COMMENT '创建时间',
	update_user VARCHAR (20) NOT NULL COMMENT '最后修改人',
	update_time datetime NOT NULL COMMENT '最后修改时间'
) COMMENT '注册用户表';

DROP TABLE
IF EXISTS t_report_level;

CREATE TABLE t_report_level (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	uuid VARCHAR (50) COMMENT 'uuid',
	`code` VARCHAR (50) UNIQUE COMMENT '等级编码',
	`name` VARCHAR (50) COMMENT '等级名称',
	create_user VARCHAR (20) NOT NULL COMMENT '创建人',
	create_time datetime NOT NULL COMMENT '创建时间',
	update_user VARCHAR (20) NOT NULL COMMENT '最后修改人',
	update_time datetime NOT NULL COMMENT '最后修改时间'
) COMMENT '环境报告等级';

DROP TABLE
IF EXISTS t_report;

CREATE TABLE t_report (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	uuid VARCHAR (50) COMMENT 'uuid',
	`code` VARCHAR (50) UNIQUE COMMENT '报告编码',
	houses_code VARCHAR (50) COMMENT '楼盘编码',
	title VARCHAR (200) DEFAULT '' COMMENT '报告标题',
	`level_code` VARCHAR (50) DEFAULT 'RL161004100007' COMMENT '报告等级编码 默认等级为普通',
	pic VARCHAR (200) DEFAULT '' COMMENT '图标',
	image VARCHAR (200) DEFAULT '' COMMENT '大图',
	rang INT DEFAULT 3 COMMENT '范围,默认为公里,默认设置为3',
	price DECIMAL (18, 2) DEFAULT '0.00' COMMENT '价格',
	path VARCHAR (500) DEFAULT '' COMMENT '文件路径',
	detail text COMMENT '报告说明',
	create_user VARCHAR (20) NOT NULL COMMENT '创建人',
	create_time datetime NOT NULL COMMENT '创建时间',
	update_user VARCHAR (20) NOT NULL COMMENT '最后修改人',
	update_time datetime NOT NULL COMMENT '最后修改时间'
) COMMENT '环境报告列表';

DROP TABLE
IF EXISTS t_report_environment_level;

CREATE TABLE t_report_environment_level (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	uuid VARCHAR (50) DEFAULT '' COMMENT 'uuid',
	`code` VARCHAR (50) NOT NULL UNIQUE COMMENT '环境报告环境等级编码',
	type VARCHAR (50) COMMENT '类型',
	`level` INT DEFAULT 0 COMMENT '环境等级',
	content text COMMENT '等级描述内容',
	create_user VARCHAR (20) NOT NULL COMMENT '创建人',
	create_time datetime NOT NULL COMMENT '创建时间',
	update_user VARCHAR (20) NOT NULL COMMENT '最后修改人',
	update_time datetime NOT NULL COMMENT '最后修改时间'
) COMMENT '环境报告环境等级描述';

DROP TABLE
IF EXISTS t_report_template;

CREATE TABLE t_report_template (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	uuid VARCHAR (50) DEFAULT '' COMMENT 'uuid',
	`code` VARCHAR (50) NOT NULL UNIQUE COMMENT '环境报告模板编码',
	`name` VARCHAR (20) NOT NULL COMMENT '名称',
	type VARCHAR (50) UNIQUE COMMENT '模板类型',
	content text COMMENT '模板内容',
	create_user VARCHAR (20) NOT NULL COMMENT '创建人',
	create_time datetime NOT NULL COMMENT '创建时间',
	update_user VARCHAR (20) NOT NULL COMMENT '最后修改人',
	update_time datetime NOT NULL COMMENT '最后修改时间'
) COMMENT '报告模板表';

DROP TABLE
IF EXISTS t_report_comment;

CREATE TABLE t_report_comment (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	uuid VARCHAR (50) COMMENT 'uuid',
	user_code VARCHAR (50) NOT NULL COMMENT '用户编号',
	report_code VARCHAR (50) NOT NULL COMMENT '报告编号',
	content text NOT NULL COMMENT '评论内容',
	rate INT DEFAULT 0 COMMENT '好评等级',
	create_user VARCHAR (20) NOT NULL COMMENT '创建人',
	create_time datetime NOT NULL COMMENT '创建时间',
	update_user VARCHAR (20) NOT NULL COMMENT '最后修改人',
	update_time datetime NOT NULL COMMENT '最后修改时间'
) COMMENT '报告评论表';

DROP TABLE
IF EXISTS t_landed_property;

CREATE TABLE t_landed_property (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	uuid VARCHAR (50) DEFAULT '' COMMENT 'uuid',
	`code` VARCHAR (50) NOT NULL UNIQUE COMMENT '楼盘编码',
	title VARCHAR (50) NOT NULL  COMMENT '楼盘名称',
	addressFull text COMMENT '楼盘详细地址',
	total VARCHAR (50) COMMENT '户数',
	city VARCHAR (20) COMMENT '所属城市',
	`completion` VARCHAR (50) COMMENT '楼盘完成时间',
	propertyType VARCHAR (50) COMMENT '类型',
	propertyCompany VARCHAR (100) COMMENT '物业公司',
	price VARCHAR (50) COMMENT '价格',
	volumeRate VARCHAR (50) COMMENT '容积率',
	propertyCosts VARCHAR (20) COMMENT '物业费',
	parking VARCHAR (50) COMMENT '停车位',
	greeningRate VARCHAR (50) COMMENT '绿化率',
	gfa VARCHAR (50) COMMENT '楼盘面积',
	metro VARCHAR (50) COMMENT '周边地铁',
	bus text COMMENT '公交车',
	lat VARCHAR (20) COMMENT '维度',
	lng VARCHAR (20) COMMENT '经度',
	images text COMMENT '图片列表',
	overview text COMMENT '楼盘综述',
	create_user VARCHAR (20) NOT NULL COMMENT '创建人',
	create_time datetime NOT NULL COMMENT '创建时间',
	update_user VARCHAR (20) NOT NULL COMMENT '最后修改人',
	update_time datetime NOT NULL COMMENT '最后修改时间'
) COMMENT '地产楼盘列表';

DROP TABLE
IF EXISTS t_rubbish_recycling;

CREATE TABLE t_rubbish_recycling (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	city VARCHAR (20) NOT NULL COMMENT '所属区域',
	`name` VARCHAR (50) NOT NULL COMMENT '名称',
	address VARCHAR (500) NOT NULL COMMENT '详细地址',
	lat VARCHAR (50) NOT NULL COMMENT '纬度',
	lng VARCHAR (50) NOT NULL COMMENT '经度'
) COMMENT '垃圾回收站';

DROP TABLE
IF EXISTS t_villa;

CREATE TABLE t_villa (
	`id` INT (11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	city VARCHAR (20) NOT NULL COMMENT '城市',
	`name` VARCHAR (50) NOT NULL COMMENT '名称',
	lat VARCHAR (50) NOT NULL COMMENT '纬度',
	lng VARCHAR (50) NOT NULL COMMENT '经度'
) COMMENT '别墅区';

DROP TABLE
IF EXISTS t_industrial_park;

CREATE TABLE t_industrial_park (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	city VARCHAR (20) NOT NULL COMMENT '所属区域',
	`name` VARCHAR (50) NOT NULL COMMENT '名称',
	ADDress VARCHAR (500) NOT NULL COMMENT '详细地址',
	lat VARCHAR (50) NOT NULL COMMENT '纬度',
	lng VARCHAR (50) NOT NULL COMMENT '经度'
) COMMENT '工业园';

DROP TABLE
IF EXISTS t_chemical_plant;

CREATE TABLE t_chemical_plant (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	city VARCHAR (20) NOT NULL COMMENT '所属区域',
	`name` VARCHAR (50) NOT NULL COMMENT '名称',
	address VARCHAR (500) NOT NULL COMMENT '详细地址',
	lat VARCHAR (50) NOT NULL COMMENT '纬度',
	lng VARCHAR (50) NOT NULL COMMENT '经度'
) COMMENT '化工厂';

DROP TABLE
IF EXISTS t_sewage_treatment_plant;

CREATE TABLE t_sewage_treatment_plant (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	city VARCHAR (20) NOT NULL COMMENT '所属区域',
	`name` VARCHAR (50) NOT NULL COMMENT '名称',
	address VARCHAR (500) NOT NULL COMMENT '详细地址',
	lat VARCHAR (50) NOT NULL COMMENT '纬度',
	lng VARCHAR (50) NOT NULL COMMENT '经度'
) COMMENT '污水处理厂';

DROP TABLE
IF EXISTS t_report_comment_type;

CREATE TABLE t_report_comment_type (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	uuid VARCHAR (50) COMMENT 'uuid',
	`code` VARCHAR (50) NOT NULL UNIQUE COMMENT '类型编码',
	`name` VARCHAR (50) NOT NULL COMMENT '类型名称',
	create_user VARCHAR (20) NOT NULL COMMENT '创建人',
	create_time datetime NOT NULL COMMENT '创建时间',
	update_user VARCHAR (20) NOT NULL COMMENT '最后修改人',
	update_time datetime NOT NULL COMMENT '最后修改时间'
) COMMENT '环境报告评论类型';

DROP TABLE
IF EXISTS t_report_comment;

CREATE TABLE t_report_comment (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	uuid VARCHAR (50) COMMENT 'uuid',
	`code` VARCHAR (50) NOT NULL UNIQUE COMMENT '评论编码',
	content text COMMENT '评论内容',
	type_code varchar(50) DEFAULT '' COMMENT '评论类型',
	create_user VARCHAR (20) NOT NULL COMMENT '创建人',
	create_time datetime NOT NULL COMMENT '创建时间',
	update_user VARCHAR (20) NOT NULL COMMENT '最后修改人',
	update_time datetime NOT NULL COMMENT '最后修改时间'
) COMMENT '环境报告评论';

#============获取唯一键函数 start ========================
DROP PROCEDURE
IF EXISTS proc_get_unique_code;

CREATE PROCEDURE `proc_get_unique_code` (IN p_code_start VARCHAR(100))
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


END #================== end ==================================