package com.cmr.controller;

import com.cmr.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/12/4.
 */
@Controller
@RequestMapping(value = "/")
@Slf4j
public class GoodsController {

    @Resource
    UserService userServiceImpl;

    /**
     * 首页
     * @return
     */
    @RequestMapping(path = "/goodsInfo")
    public String goodsInfo(){
        return "good/goodsInfo";
    }




}
