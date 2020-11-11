package com.springTest.demo;

import com.springTest.config.SpringConfiguration;
import com.springTest.service.UserService;
import com.springTest.service.imp.UserServiceImp_anno;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserController {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationContext app = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        UserService userService =  app.getBean(UserServiceImp_anno.class);
        userService.save();
//        app.close();
    }
}
