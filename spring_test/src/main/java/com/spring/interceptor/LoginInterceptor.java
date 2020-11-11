package com.spring.interceptor;

import com.spring.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null){
            return true;
        }else {
            request.getRequestDispatcher(request.getContextPath() + "/login.jsp").forward(request,response);
            return false;
        }
    }
}
