package com.cmr.service;

import com.cmr.entities.User;

import java.util.List;

/**
 * Created by Administrator on 2017/12/6.
 */
public interface UserService {
    /**
     * input name and return Object User
     * @param name
     * @return User
     */
    User findByUserName(String name);

    /**
     * find all user
     * @return
     */
    List<User> findAll();


    boolean isQualify(String name,String password);

    /**
     * delete a user
     * @param id
     */
    void deleteByUserId(String id);

    void addUser(User user);
}
