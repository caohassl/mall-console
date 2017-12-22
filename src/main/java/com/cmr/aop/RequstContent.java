package com.cmr.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/12/22.
 */
public class RequstContent {
    private static ThreadLocal<HttpServletRequest> requestLocal=new ThreadLocal<HttpServletRequest>();
    private static ThreadLocal<HttpServletResponse> responseLocal=new ThreadLocal<HttpServletResponse>();

    public static HttpServletRequest getRequestLocal() {
        return requestLocal.get();
    }

    public static void setRequestLocal(HttpServletRequest request) {
        requestLocal.set(request);
    }

    public static HttpServletResponse getResponseLocal() {
        return responseLocal.get();
    }

    public static void setResponseLocal(HttpServletResponse response) {
        responseLocal.set(response);
    }

    public static void removeRequset(){
        requestLocal.remove();
    }


    public static HttpSession getSession() {
        return (requestLocal.get()).getSession();
    }
}
