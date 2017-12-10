package com.cmr.entities;

import lombok.Data;

/**
 * Created by Administrator on 2017/12/6.
 */
@Data
public class User {

    private long id;
    private String username;
    private String password;
    private int type;
    private String realname;
}
