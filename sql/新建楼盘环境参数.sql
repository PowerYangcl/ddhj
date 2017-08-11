DROP TABLE
IF EXISTS t_lp_environment;

CREATE TABLE t_lp_environment (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	uuid VARCHAR (50) NOT NULL COMMENT 'uuid',
	lp_code VARCHAR (50) NOT NULL COMMENT '楼盘编码',
	air INT DEFAULT 1 COMMENT '空气质量等级',
	afforest INT DEFAULT 1 COMMENT '绿地率等级',
	volume INT DEFAULT 1 COMMENT '容积率等级',
	water INT DEFAULT 1 COMMENT '水质量等级',
	rubbish INT DEFAULT 1 COMMENT '垃圾设施等级',
	chemical INT DEFAULT 1 COMMENT '化工厂',
	nosie INT DEFAULT 1 COMMENT '噪音等级',
	radiation INT DEFAULT 1 COMMENT '高压电辐射',
	hazardous_article INT DEFAULT 1 COMMENT '危险品存放',
	create_user VARCHAR (20) NOT NULL COMMENT '创建人',
	create_time datetime NOT NULL COMMENT '创建时间',
	update_user VARCHAR (20) NOT NULL COMMENT '最后修改人',
	update_time datetime NOT NULL COMMENT '最后修改时间'
) COMMENT '楼盘环境参数';