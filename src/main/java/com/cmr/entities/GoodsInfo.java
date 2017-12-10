package com.cmr.entities;

import lombok.Data;

import java.util.Date;

/**
 * Created by Caomr on 2017/12/10.
 */
@Data
public class GoodsInfo {

    private long id;
    private String goodsId;
    private String goodsName ;
    private String goodsSpec;
    private String goodsPrice ;

    private int goodsAmount ;

    private String goodsStatus  ;// '商品状态 00--上架  01--下架',

    private double promotionGoodsPrice; //'商品活动价格',

    private String goodsDesc;//'商品描述'

    private Date insertTime  ;//'插入时间'
    private Date updateTime  ;//'跟新时间'
}
