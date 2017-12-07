package com.cmr.entities;

/**
 * Created by Administrator on 2017/12/4.
 */
public class ConsoleResult<T> {
    private static final int SUCCESS_CODE=200;
    private static final int ERROR_CODE=500;

    public static ConsoleResult Success=new ConsoleResult(SUCCESS_CODE,null);
    public static ConsoleResult Fail=new ConsoleResult(ERROR_CODE,null);


    private int code;
    private T msg;

    public ConsoleResult(int code,T msg){
        this.code=code;
        this.msg=msg;
    }
}
