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
	nick_name VARCHAR (50) NOT NULL UNIQUE COMMENT '昵称',
	head_pic VARCHAR (500) DEFAULT '' COMMENT '头像',
	e_mail VARCHAR (100) DEFAULT '' COMMENT '邮箱',
	is_login INT DEFAULT 0 COMMENT '是否已登录 0 已登录 1 已登出',
	create_user VARCHAR (20) NOT NULL COMMENT '创建人',
	create_time datetime NOT NULL COMMENT '创建时间',
	update_user VARCHAR (20) NOT NULL COMMENT '最后修改人',
	update_time datetime NOT NULL COMMENT '最后修改时间'
) COMMENT '注册用户表';

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


END
#================== end ==================================