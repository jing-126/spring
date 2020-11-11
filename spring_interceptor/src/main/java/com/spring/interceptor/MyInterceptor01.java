package com.spring.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor01 implements HandlerInterceptor {
    @Override
//    目标方法执行之前执行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle执行。。。。");
        if (request.getRequestURI().contains("favicon.ico")){
            System.out.println("favicon.ico请求拦截");
        }
        String param = request.getParameter("param");
        if ("yes".equalsIgnoreCase(param)){
            return true;
        }else {
            request.getRequestDispatcher(request.getContextPath() + "/error.jsp").forward(request,response);
            return false;
        }
    }

    @Override
//    目标方法执行之后 视图对象返回之前 执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle执行。。。。");
        modelAndView.addObject("name","景");
    }

    @Override
//    整个流程执行完毕之后执行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion执行。。。。");
    }
}
