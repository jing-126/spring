package com.springTest.aop;

public class Target implements TargetInterface {
//    目标类
    @Override
    public void save() {
        System.out.println("save running....");
//        int i = 1/0;
    }
}
