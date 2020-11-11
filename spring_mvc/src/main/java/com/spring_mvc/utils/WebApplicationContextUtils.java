package com.spring_mvc.utils;

import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContext;

public class WebApplicationContextUtils {
    public static ApplicationContext getApplicationContext(ServletContext context){
        return (ApplicationContext) context.getAttribute("app");
    }
}
