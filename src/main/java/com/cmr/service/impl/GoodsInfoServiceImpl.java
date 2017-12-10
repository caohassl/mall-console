package com.cmr.service.impl;

import com.cmr.dao.GoodsMapper;
import com.cmr.entities.GoodsInfo;
import com.cmr.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/12/6.
 */
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<GoodsInfo> findAll() {
        return goodsMapper.findAll();
    }

    @Override
    public void deleteById(long id) {
        goodsMapper.deleteById(id);
    }

    @Override
    public void upGoods(long id) {
        goodsMapper.upGoods(id);
    }

    @Override
    public void downGoods(long id) {
        goodsMapper.downGoods(id);
    }

}
