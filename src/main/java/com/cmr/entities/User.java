package com.cmr.entities;

import lombok.Data;

/**
 * Created by Administrator on 2017/12/6.
 */
@Data
public class User {

    private String id;
    private String username;
    private String password;
    private String type;
    private String realname;
}
