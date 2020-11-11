package com.spring_anno.controller;

import com.spring_anno.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserController {
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext-anno.xml");
        UserService service = app.getBean(UserService.class);
        service.transfer("j","jing",100);
    }
}
