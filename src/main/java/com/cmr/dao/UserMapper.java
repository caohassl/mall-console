package com.cmr.dao;

import com.cmr.entities.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/12/6.
 */
public interface UserMapper {

    User findByUserName(@Param("userName") String userName);

    List<User> findAll();

    void deleteByUserId(@Param("id") String id);

    void addUser(User user);
}
