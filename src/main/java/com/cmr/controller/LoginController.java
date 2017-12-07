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
public class LoginController {

    @Resource
    UserService userServiceImpl;

    @RequestMapping(path = "/index")
    public String index(){
        return "/index";
    }
    @RequestMapping(path = "/login")
    public String login(){
        return "/login";
    }

}
