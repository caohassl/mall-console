package com.cmr.dao;

import com.cmr.entities.GoodsInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/12/6.
 */
public interface GoodsMapper {

    List<GoodsInfo> findAll();

    void deleteById(long id);

    void upGoods(long id);

    void downGoods(long id);
}
