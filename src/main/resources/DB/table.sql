create table cmr_goods_info(

  id int not null auto_increment PRIMARY KEY ,
  goods_id VARCHAR (36) not null comment '商品编号',
  goods_name VARCHAR (50) not null comment '商品名称',
  goods_spec VARCHAR (20) not null comment '商品规格',
  goods_price BIGINT not null comment '商品价格',

  goods_amount bigint not null comment '商品数量',

  goods_status  tinyint (2) comment '商品状态 00--上架  01--下架',

  promotion_goods_pricce bigint comment '商品活动价格',

  goods_desc VARCHAR (200) default NULL comment '商品描述',

  insert_time datetime  comment '插入时间',
  update_time datetime COMMENT '跟新时间'
);
create index idx_goodsid_goodsstatus on cmr_goods_info(goods_id,goods_status);

alter table cmr_goods_info change promotion_goods_pricce promotion_goods_price bigint