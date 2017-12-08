package com.cmr.controller;

import com.cmr.entities.ConsoleResult;
import com.cmr.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 控制台首页
     * @return
     */
    @RequestMapping(path = "/index")
    public String index(){
        return "index";
    }

    /**
     *首页
     * @return
     */
    @RequestMapping(path = "/login")
    public String login(){
        return "login";
    }


    /**
     * 退出登录
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/logout")
    public ConsoleResult logout(){
        return ConsoleResult.Success;
    }



    /**
     * 验证
     * @return
     */
    @PostMapping(path = "/validate")
    @ResponseBody
    public ConsoleResult validate(String userName, String password){
        boolean isAllowed=userServiceImpl.isQualify(userName,password);
        if(isAllowed){
            return ConsoleResult.Success;
        }
        return new ConsoleResult(ConsoleResult.ERROR_CODE,"LOGIN FAILURE");
    }

}
