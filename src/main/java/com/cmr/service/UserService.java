package com.cmr.service;

import com.cmr.entities.User;

/**
 * Created by Administrator on 2017/12/6.
 */
public interface UserService {
    User findByUserName(String name);
}
