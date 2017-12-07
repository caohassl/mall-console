package com.cmr.service;

import com.cmr.entities.User;

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

    boolean isQualify(String name,String password);
}
