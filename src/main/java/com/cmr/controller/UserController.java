package com.cmr.controller;

import com.alibaba.fastjson.JSONObject;
import com.cmr.entities.ConsoleResult;
import com.cmr.entities.InsertWorkRequest;
import com.cmr.entities.User;
import com.cmr.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/4.
 */
@Controller
@RequestMapping(value = "/userInfo")
@Slf4j
public class UserController {

    @Resource
    UserService userServiceImpl;

    /**
     * 用户
     * @return
     */
    @RequestMapping(path = "/")
    public String userInfo(ModelMap modelMap){

        List<User> userList= userServiceImpl.findAll();
        modelMap.put("userList", JSONObject.toJSONString(userList));
        return "user/userInfo";
    }

    @RequestMapping(path = "/getAllUserInfo")
    @ResponseBody
    public ConsoleResult getAllUserInfo(){

        List<User> userList= userServiceImpl.findAll();
        Map map =new HashMap<>();
        map.put("userList",userList);
        return new ConsoleResult(map);
    }

    /**
     * delete user
     * @return
     */
    @RequestMapping(path = "/delete")
    @ResponseBody
    public ConsoleResult userInfoDelete(String id){

        userServiceImpl.deleteByUserId(id);
        return ConsoleResult.Success;
    }

    /**
     * add user
     * @return
     */
    @RequestMapping(path = "/add")
    @ResponseBody
    public ConsoleResult userInfoAdd(User user){

        userServiceImpl.addUser(user);
        return ConsoleResult.Success;
    }

    @RequestMapping(value = "/insertWork", method = RequestMethod.POST)
    @ResponseBody
    // public Object insertWork(HttpServletRequest request) {
    public Object insertWork(@RequestBody InsertWorkRequest request) {
        System.out.println("hahah");
        return null;
    }


}
