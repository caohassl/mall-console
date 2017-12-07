package com.cmr.dao;

import com.cmr.entities.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/12/6.
 */
public interface UserMapper {

    User findByUserName(@Param("userName") String userName);
}
