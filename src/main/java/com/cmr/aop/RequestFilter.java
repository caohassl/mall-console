package com.cmr.aop;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/22.
 */
@Configuration
@WebFilter(asyncSupported=true, filterName = "requestFilter", urlPatterns = "/login")
public class RequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        RequstContent.setRequestLocal(request);
        RequstContent.setResponseLocal(response);
        filterChain.doFilter(request,response);
    }
}
