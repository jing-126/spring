package com.spring_mvc.web.servlet;

import com.spring_mvc.service.UserService;
import com.spring_mvc.utils.WebApplicationContextUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        获取ServletContext对象
        ServletContext context = req.getServletContext();
//        获取应用上下文对象
//        通过工具类解耦
//        ApplicationContext app = (ApplicationContext) context.getAttribute("app");
        ApplicationContext app = WebApplicationContextUtils.getApplicationContext(context);
        UserService service = app.getBean(UserService.class);
        service.save();
    }
}
