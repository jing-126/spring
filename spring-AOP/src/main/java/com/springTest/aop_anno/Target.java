package com.springTest.aop_anno;

import org.springframework.stereotype.Component;

@Component("target")
public class Target implements TargetInterface {
//    目标类
    @Override
    public void save() {
        System.out.println("save running....");
//        int i = 1/0;
    }
}
