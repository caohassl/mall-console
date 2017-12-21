CREATE TABLE `cmr_goods_info` (
  `id` bigint(36) NOT NULL AUTO_INCREMENT,
  `goods_id` varchar(36) NOT NULL COMMENT '商品编号',
  `goods_name` varchar(50) NOT NULL COMMENT '商品名称',
  `goods_spec` varchar(20) NOT NULL COMMENT '商品规格',
  `goods_price` VARCHAR(20) NOT NULL COMMENT '商品价格',
  `goods_amount` VARCHAR(20) NOT NULL COMMENT '商品数量',
  `goods_status` VARCHAR(2) DEFAULT NULL COMMENT '商品状态 00--上架  01--下架',
  `promotion_goods_price` VARCHAR(20) DEFAULT NULL COMMENT '商品活动价格',
  `goods_desc` varchar(200) DEFAULT NULL COMMENT '商品描述',
  `insert_time` datetime DEFAULT NULL COMMENT '插入时间',
  `update_time` datetime DEFAULT NULL COMMENT '跟新时间',
  PRIMARY KEY (`id`),
  KEY `idx_goodsid_goodsstatus` (`goods_id`,`goods_status`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


CREATE TABLE `xxl_api_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '账号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户类型：0-普通用户、1-超级管理员',
  `realname` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert into xxl_api_user(username,password,type,realname) values('admin','123456',1,'超级管理员');