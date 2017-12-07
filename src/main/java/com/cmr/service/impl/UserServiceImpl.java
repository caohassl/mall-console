package com.cmr.service.impl;

import com.cmr.dao.UserMapper;
import com.cmr.entities.User;
import com.cmr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/12/6.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findByUserName(String name) {
        userMapper.findByUserName(name);
        return userMapper.findByUserName(name);
    }
}
