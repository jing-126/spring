package com.springTest.proxy.jdk;

public class Advice {
//    增强对象
//    后置增强
    public void after(){
        System.out.println("after running....");
    }
//    前置增强
    public void before(){
        System.out.println("before running....");
    }

}
