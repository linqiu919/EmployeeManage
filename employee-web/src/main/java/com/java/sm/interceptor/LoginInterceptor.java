package com.java.sm.interceptor;

import com.java.sm.exception.LoginException;
import com.java.sm.result.AxiosStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 722A-08-CXB
 * @version 1.0.0
 * @ClassName LoginInterceptor.java
 * @DescriPtion TODO
 * @CreateTime 2021年06月16日 13:35:00
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object userInfo = session.getAttribute("userInfo");
        if(userInfo==null){
            //如果session信息不存在，向前端传递未登录错误数据
            throw new LoginException(AxiosStatus.NOLOGIN);
        }
        return true;
    }
}
