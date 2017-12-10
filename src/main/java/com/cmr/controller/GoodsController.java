package com.cmr.controller;

import com.alibaba.fastjson.JSONObject;
import com.cmr.entities.ConsoleResult;
import com.cmr.service.GoodsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/12/4.
 */
@Controller
@RequestMapping(value = "/goodsInfo")
@Slf4j
public class GoodsController {

    @Resource
    GoodsInfoService goodsInfoServiceImpl;

    /**
     * 商品首页
     * @return
     */
    @RequestMapping(path = "/")
    public String goodsInfo(Model model){
        model.addAttribute("goodsList", JSONObject.toJSONString(goodsInfoServiceImpl.findAll()));
        return "good/goodsInfo";
    }

    /**
     * 删除商品
     * @return
     */
    @RequestMapping(path = "/delete")
    @ResponseBody
    public ConsoleResult deleteGoods(long id){
        goodsInfoServiceImpl.deleteById(id);
        return ConsoleResult.Success;
    }

    /**
     * 上架商品
     * @return
     */
    @RequestMapping(path = "/upGoods")
    @ResponseBody
    public ConsoleResult upGoods(long id){
        goodsInfoServiceImpl.upGoods(id);
        return ConsoleResult.Success;
    }

    /**
     * 下架商品
     * @return
     */
    @RequestMapping(path = "/downGoods")
    @ResponseBody
    public ConsoleResult downGoods(long id){
        goodsInfoServiceImpl.downGoods(id);
        return ConsoleResult.Success;
    }

}
