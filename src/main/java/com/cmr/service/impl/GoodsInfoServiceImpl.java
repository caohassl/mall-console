package com.cmr.service.impl;

import com.cmr.dao.GoodsMapper;
import com.cmr.entities.GoodsInfo;
import com.cmr.entities.vo.GoodsInfoVo;
import com.cmr.service.GoodsInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @Override
    public void add(GoodsInfoVo goodsInfoVo) {
        GoodsInfo goodsInfo=new GoodsInfo();
        copyProperties(goodsInfoVo, goodsInfo);
        goodsMapper.addGoods(goodsInfo);
    }

    private void copyProperties(GoodsInfoVo goodsInfoVo,GoodsInfo goodsInfo) {
        BeanUtils.copyProperties(goodsInfoVo,goodsInfo);
        goodsInfo.setGoodsId(generateGoodsId());
        goodsInfo.setGoodsStatus("00");//初始状态为上架
        goodsInfo.setInsertTime(new Date());
        goodsInfo.setUpdateTime(new Date());
    }

    @Override
    public synchronized String generateGoodsId() {

        return "CMR_"+UUID.randomUUID().toString().toUpperCase().replaceAll("-","");
    }

    @Override
    public GoodsInfo getGoodsById(long id) {
        return goodsMapper.getGoodsById(id);
    }

    @Override
    public void updateGoods(GoodsInfoVo goodsInfoVo) {
        GoodsInfo goodsInfo=goodsMapper.getGoodsById(Long.parseLong(goodsInfoVo.getId()));
        updateGoods(goodsInfoVo,goodsInfo);
        goodsMapper.updateGoods(goodsInfo);

    }

    private void updateGoods(GoodsInfoVo source, GoodsInfo target) {
        target.setGoodsName(source.getGoodsName());
        target.setGoodsSpec(source.getGoodsSpec());
        target.setGoodsPrice(source.getGoodsPrice());
        target.setGoodsAmount(source.getGoodsAmount());
        target.setPromotionGoodsPrice(source.getPromotionGoodsPrice());
        target.setGoodsDesc(source.getGoodsDesc());
        target.setUpdateTime(new Date());
    }


    public static void main(String[] args) {
        System.out.println("CMR_"+UUID.randomUUID().toString().toUpperCase().replaceAll("-",""));
    }
}
