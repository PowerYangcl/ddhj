DROP TABLE
IF EXISTS t_lp_environment;

CREATE TABLE t_lp_environment (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	uuid VARCHAR (50) NOT NULL COMMENT 'uuid',
	lp_code VARCHAR (50) NOT NULL COMMENT '楼盘编码',
	city varchar(50) not null COMMENT '城市',
	air DECIMAL (10, 2) DEFAULT '0.00' COMMENT '空气质量数值',
	afforest DECIMAL (10, 2) DEFAULT '0.00' COMMENT '绿地率数值',
	volume DECIMAL (10, 2) DEFAULT '0.00' COMMENT '容积率数值',
	water DECIMAL (10, 2) DEFAULT '0.00' COMMENT '水质量数值',
	rubbish DECIMAL (10, 2) DEFAULT '0.00' COMMENT '垃圾设施距离数值',
	chemical DECIMAL (10, 2) DEFAULT '0.00' COMMENT '化工厂距离数值',
	nosie DECIMAL (10, 2) DEFAULT '0.00' COMMENT '噪音数值',
	radiation DECIMAL (10, 2) DEFAULT '0.00' COMMENT '高压电辐射距离数值',
	hazardous_article DECIMAL (10, 2) DEFAULT '0.00' COMMENT '危险品存放数值',
	create_user VARCHAR (20) NOT NULL COMMENT '创建人',
	create_time datetime NOT NULL COMMENT '创建时间',
	update_user VARCHAR (20) NOT NULL COMMENT '最后修改人',
	update_time datetime NOT NULL COMMENT '最后修改时间'
) COMMENT '楼盘环境参数';