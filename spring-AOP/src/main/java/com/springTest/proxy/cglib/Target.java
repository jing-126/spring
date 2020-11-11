package com.springTest.proxy.cglib;


public class Target {
    public int save() {
        System.out.println("save running....");
        return 1;
    }
}
