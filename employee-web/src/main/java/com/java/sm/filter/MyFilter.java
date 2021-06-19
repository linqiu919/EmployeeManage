package com.java.sm.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 722A-08-CXB
 * @version 1.0.0
 * @ClassName MyFilter.java
 * @CreateTime 2021年06月16日 13:49:00
 */
@WebFilter("/*") //进入springmvc之前，处理跨域问题

public class MyFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    //在过滤器中解决跨域问题
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器执行了");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        //设置跨域请求
        String origin = req.getHeader("Origin");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(origin == null) {
            origin = req.getHeader("Referer");
        }

        response.setHeader("Access-Control-Allow-Origin", origin);            // 允许指定域访问跨域资源
        response.setHeader("Access-Control-Allow-Credentials", "true");   // 允许客户端携带跨域cookie，此时origin值不能为“*”，只能为指定单一域名
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Cookie,token");
        filterChain.doFilter(req, response);

    }

    @Override
    public void destroy() {

    }
}
