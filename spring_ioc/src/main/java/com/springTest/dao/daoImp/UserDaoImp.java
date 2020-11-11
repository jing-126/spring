package com.springTest.dao.daoImp;

import com.springTest.dao.UserDao;

public class UserDaoImp implements UserDao {
    private String username;
    private int age;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void init(){
        System.out.println("初始化方法");
    }
    public void save() {
        System.out.println(username + "===" + age);
        System.out.println("save running....");
    }

    //Spring调用无参构造方法创建对象
    public UserDaoImp() {
        System.out.println("userDao对象创建");
    }

    public void destroy(){
        System.out.println("销毁方法");
    }
}
