package com.cmr.service;

import com.cmr.entities.GoodsInfo;
import com.cmr.entities.vo.GoodsInfoVo;

import java.util.List;

/**
 * Created by Administrator on 2017/12/6.
 */
public interface GoodsInfoService {


    /**
     * find all GoodsInfo
     * @return
     */
    List<GoodsInfo> findAll();


    void deleteById(long id);

    void upGoods(long id);

    void downGoods(long id);

    void add(GoodsInfoVo goodsInfoVo);

    String generateGoodsId();

    GoodsInfo getGoodsById(long id);

    void updateGoods(GoodsInfoVo goodsInfoVo);
}
