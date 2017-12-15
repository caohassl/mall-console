package com.cmr.entities.vo;

import lombok.Data;

/**
 * Created by Caomr on 2017/12/10.
 */
@Data
public class GoodsInfoVo {

    private String id;
    private String goodsId;
    private String goodsName ;
    private String goodsSpec;
    private String goodsPrice ;

    private String goodsAmount ;

    private String goodsStatus  ;// '商品状态 00--上架  01--下架',

    private String promotionGoodsPrice; //'商品活动价格',

    private String goodsDesc;//'商品描述'

    private String insertTime  ;//'插入时间'
    private String updateTime  ;//'跟新时间'
}
