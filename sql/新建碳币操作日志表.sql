DROP TABLE
IF EXISTS t_user_carbon_operation;

CREATE TABLE t_user_carbon_operation (
	`id` INT (11) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	`uuid` VARCHAR (50) DEFAULT NULL COMMENT 'uuid',
	`code` VARCHAR (50) NOT NULL COMMENT '编码',
	user_code VARCHAR (50) NOT NULL COMMENT '用户编码',
	operation_type VARCHAR (50) NOT NULL COMMENT '碳币操作类型',
	operation_type_child VARCHAR (50) NOT NULL COMMENT '碳币操作细分类型',
	carbon_sum DECIMAL (10, 2) DEFAULT '0.00' COMMENT '碳币总额',
	`create_user` VARCHAR (20) NOT NULL COMMENT '创建人',
	`create_time` datetime NOT NULL COMMENT '创建时间'
) COMMENT '用户碳币收支操作日志表';


#用户表新添加字段
ALTER TABLE t_user ADD carbon_money DECIMAL(10,2) COMMENT '用户拥有碳币总数';
#用户计步数据表添加字段
ALTER TABLE t_user_step ADD is_sync INT DEFAULT 0 COMMENT '是否已同步兑换碳币';
ALTER TABLE t_user_step ADD create_time datetime COMMENT '创建时间';
ALTER TABLE t_user_step ADD update_time datetime COMMENT '修改时间';
#订单新添加字段
ALTER TABLE t_order ADD check_pay_money DECIMAL (18, 2) DEFAULT '0.00' COMMENT '应付款';
ALTER TABLE t_order ADD carbon_money DECIMAL (18, 2) DEFAULT '0.00' COMMENT '炭币';
ALTER TABLE t_order ADD coupon_codes text COMMENT '使用优惠劵编码';
ALTER TABLE t_order ADD buyer_code VARCHAR (50) NOT NULL COMMENT '购买人编码';
ALTER TABLE t_order ADD buyer_mobile VARCHAR (50) NOT NULL COMMENT '购买人手机号';
ALTER TABLE t_order ADD pay_ip VARCHAR (100) DEFAULT '' COMMENT '微信支付时客户端IP地址';
ALTER TABLE t_order ADD uniqid VARCHAR (100) DEFAULT '' COMMENT '设备的唯一标识';
ALTER TABLE t_order ADD app_vision VARCHAR (100) DEFAULT '1.0.0' COMMENT 'app版本信息';
ALTER TABLE t_order ADD os VARCHAR (50) DEFAULT '' COMMENT '手机操作系统';
ALTER TABLE t_order ADD invoice_type VARCHAR (50) DEFAULT '' COMMENT '发票类型';
ALTER TABLE t_order ADD invoice_content VARCHAR (500) DEFAULT '' COMMENT '发票内容';




