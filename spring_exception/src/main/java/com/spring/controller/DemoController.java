package com.spring.controller;

import com.spring.exception.MyException;
import com.spring.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("show")
    public String show() throws MyException {
        System.out.println("show running....");
//        demoService.show1();
        demoService.show5();
        return "index";
    }
}
