package com.spring_mvc.web.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        获取ServletContext对象
        ServletContext servletContext = sce.getServletContext();
//        读取web.xml中的全局参数
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        ApplicationContext app = new ClassPathXmlApplicationContext(contextConfigLocation);
//        将spring的应用上下文对象存储到ServletContext域中
        servletContext.setAttribute("app",app);
        System.out.println("spring容器创建完成");
    }
}
