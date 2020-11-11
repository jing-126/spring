package com.spring.service.impl;

import com.spring.dao.UserDao;
import com.spring.service.UserService;

public class UserServiceImp implements UserService {
    private UserDao userDao;
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    @Override
    public void transfer(String outMan, String inMan, double money) {
        userDao.out(outMan,money);
//        int i = 1 / 0;
        userDao.in(inMan, money);
    }
}
