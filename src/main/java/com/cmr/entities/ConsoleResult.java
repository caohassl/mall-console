package com.cmr.entities;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/4.
 */
@Data
public class ConsoleResult<T> implements Serializable {
    public static final int SUCCESS_CODE=200;
    public static final int ERROR_CODE=500;

    public static ConsoleResult Success=new ConsoleResult(SUCCESS_CODE,null);
    public static ConsoleResult Fail=new ConsoleResult(ERROR_CODE,null);


    private int code;
    private String msg;
    private T content;

    public ConsoleResult(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public ConsoleResult(T content){
        this.code=SUCCESS_CODE;
        this.content=content;
    }

    @Override
    public String toString() {
        return "ConsoleResult [code=" + code + ", msg=" + msg +",content="+"content"+"]";
    }
}
