package com.spring_mvc.web.servlet;

import com.spring_mvc.service.UserService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userServlet_Spring")
public class UserServlet_Spring extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取servletContext对象
        ServletContext context = request.getServletContext();
//        使用spring提供的方法获取上下文对象
        WebApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(context);
        UserService service = app.getBean(UserService.class);
        service.save();
    }
}
