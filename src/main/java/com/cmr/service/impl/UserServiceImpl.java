package com.cmr.service.impl;

import com.cmr.dao.UserMapper;
import com.cmr.entities.User;
import com.cmr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/12/6.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findByUserName(String name) {
        return userMapper.findByUserName(name);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public boolean isQualify(String name, String password) {
        User user=findByUserName(name);

        if(null!=user&&user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    @Override
    public void deleteByUserId(String id) {
        userMapper.deleteByUserId(id);
    }

    @Override
    public void addUser(User user) {

        userMapper.addUser(user);
    }
}
