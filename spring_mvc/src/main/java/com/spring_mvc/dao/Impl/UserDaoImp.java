package com.spring_mvc.dao.Impl;

import com.spring_mvc.dao.UserDao;

public class UserDaoImp implements UserDao {
    @Override
    public void save() {
        System.out.println("save running....");
    }
}
