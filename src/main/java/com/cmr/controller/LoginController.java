package com.cmr.controller;

import com.cmr.entities.ConsoleResult;
import com.cmr.service.UserService;
import com.cmr.util.Login;
import com.cmr.util.Logout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/12/4.
 */
@Controller
@RequestMapping(value = "/")
@Slf4j
public class LoginController {

    @Resource
    UserService userServiceImpl;

    @Autowired
    Environment env;
    /**
     * 控制台首页
     * @return
     */
    @RequestMapping(path = "/index")
    public String index(HttpServletRequest request){
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
    @Logout
    public ConsoleResult logout(){
        return ConsoleResult.Success;
    }



    /**
     * 验证
     * @return
     */
    @PostMapping(path = "/validate")
    @ResponseBody
    @Login
    public ConsoleResult validate(String userName, String password){
        boolean isAllowed=userServiceImpl.isQualify(userName,password);
        if(isAllowed){
            return ConsoleResult.Success;
        }
        return new ConsoleResult(ConsoleResult.ERROR_CODE,"LOGIN FAILURE");
    }

    public static void main(String[] args) {

    }

}
