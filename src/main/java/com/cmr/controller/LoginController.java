package com.cmr.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/12/4.
 */
@Controller
@RequestMapping(value = "/loginController")
@Slf4j
public class LoginController {


    @RequestMapping(path = "/login")
    public String login(){
        log.info("what is the matter?");
        return "/login";
    }
}
