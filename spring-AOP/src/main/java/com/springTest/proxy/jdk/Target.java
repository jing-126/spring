package com.springTest.proxy.jdk;

public class Target implements TargetInterface{
    @Override
    public int save() {
        System.out.println("save running....");
        return 1;
    }
}
