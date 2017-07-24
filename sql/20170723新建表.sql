DROP TABLE
IF EXISTS t_product_info;

CREATE TABLE t_product_info (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	uuid VARCHAR (50) DEFAULT '' COMMENT 'uuid',
	product_code VARCHAR (50) NOT NULL UNIQUE COMMENT '商品编码',
	product_name VARCHAR (50) NOT NULL COMMENT '商品名称',
	main_pic_url VARCHAR (50) NOT NULL COMMENT '商品主图',
	current_price INT DEFAULT 0 COMMENT '当前价格(碳币)',
	stock_num INT DEFAULT 0 COMMENT '库存',
	product_tip VARCHAR (500) DEFAULT '' COMMENT '商品说明提示',
	`create_user` VARCHAR (20) NOT NULL COMMENT '创建人',
	`create_time` datetime NOT NULL COMMENT '创建时间',
	`update_user` VARCHAR (20) NOT NULL COMMENT '最后修改人',
	`update_time` datetime NOT NULL COMMENT '最后修改时间'
) COMMENT '商品表';

DROP TABLE
IF EXISTS t_product_pic;

CREATE TABLE t_product_pic (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	uuid VARCHAR (50) DEFAULT '' COMMENT 'uuid',
	product_code VARCHAR (50) NOT NULL UNIQUE COMMENT '商品编码',
	pic_url VARCHAR (200) NOT NULL COMMENT '图片链接地址',
	`create_user` VARCHAR (20) NOT NULL COMMENT '创建人',
	`create_time` datetime NOT NULL COMMENT '创建时间',
	`update_user` VARCHAR (20) NOT NULL COMMENT '最后修改人',
	`update_time` datetime NOT NULL COMMENT '最后修改时间'
) COMMENT '商品图片';

DROP TABLE
IF EXISTS t_product_order;

#sys_define_code添加配送方式维护
CREATE TABLE t_product_order (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	uuid VARCHAR (50) DEFAULT '' COMMENT 'uuid',
	`code` VARCHAR (50) NOT NULL UNIQUE COMMENT '订单编码',
	pay_money INT DEFAULT 0 COMMENT '支付碳币',
	buyer_code VARCHAR (50) NOT NULL COMMENT '购买人',
	buyer_phone VARCHAR (20) NOT NULL COMMENT '购买人电话',
	dispatching INT DEFAULT 1 COMMENT '配送方式',
	address_code VARCHAR (50) NOT NULL COMMENT '收货地址编码',
	`create_user` VARCHAR (20) NOT NULL COMMENT '创建人',
	`create_time` datetime NOT NULL COMMENT '创建时间',
	`update_user` VARCHAR (20) NOT NULL COMMENT '最后修改人',
	`update_time` datetime NOT NULL COMMENT '最后修改时间'
) COMMENT '商品订单';

DROP TABLE
IF EXISTS t_product_order_detail;

CREATE TABLE t_product_order_detail (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	uuid VARCHAR (50) DEFAULT '' COMMENT 'uuid',
	order_code VARCHAR (50) NOT NULL COMMENT '订单编码',
	product_code VARCHAR (50) NOT NULL COMMENT '商品编码',
	current_price INT NOT NULL COMMENT '商品当前售价',
	buy_num INT NOT NULL COMMENT '购买数量',
	`create_user` VARCHAR (20) NOT NULL COMMENT '创建人',
	`create_time` datetime NOT NULL COMMENT '创建时间',
	`update_user` VARCHAR (20) NOT NULL COMMENT '最后修改人',
	`update_time` datetime NOT NULL COMMENT '最后修改时间',
	UNIQUE order_product (order_code, product_code) COMMENT '订单商品唯一键'
) COMMENT '商品订单详情';

DROP TABLE
IF EXISTS t_user_address;

CREATE TABLE t_user_address (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	uuid VARCHAR (50) DEFAULT '' COMMENT 'uuid',
	`code` VARCHAR (50) NOT NULL UNIQUE COMMENT '地址编码',
	user_code VARCHAR (50) NOT NULL COMMENT '用户编码',
	`name` VARCHAR (50) NOT NULL COMMENT '收货人姓名',
	phone VARCHAR (20) NOT NULL COMMENT '收货人手机号',
	area_code VARCHAR (50) NOT NULL COMMENT '区编码',
	provinces VARCHAR (100) NOT NULL COMMENT '省市区',
	street VARCHAR (500) NOT NULL COMMENT '详细地址',
	is_default INT DEFAULT 0 COMMENT '是否为默认地址 0 否 1 是',
	`create_user` VARCHAR (20) NOT NULL COMMENT '创建人',
	`create_time` datetime NOT NULL COMMENT '创建时间',
	`update_user` VARCHAR (20) NOT NULL COMMENT '最后修改人',
	`update_time` datetime NOT NULL COMMENT '最后修改时间'
) COMMENT '用户地址维护表';

DROP TABLE
IF EXISTS t_area;

CREATE TABLE t_area (
	id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	uuid VARCHAR (50) DEFAULT '' COMMENT 'uuid',
	`code` VARCHAR (50) NOT NULL UNIQUE COMMENT '区域编码',
	parent_code VARCHAR (50) NOT NULL COMMENT '父级编码',
	`name` VARCHAR (50) NOT NULL COMMENT '名称',
	`create_user` VARCHAR (20) NOT NULL COMMENT '创建人',
	`create_time` datetime NOT NULL COMMENT '创建时间',
	`update_user` VARCHAR (20) NOT NULL COMMENT '最后修改人',
	`update_time` datetime NOT NULL COMMENT '最后修改时间'
) COMMENT '城市区域维护表';

