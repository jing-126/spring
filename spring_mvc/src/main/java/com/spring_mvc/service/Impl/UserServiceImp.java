package com.spring_mvc.service.Impl;

import com.spring_mvc.dao.UserDao;
import com.spring_mvc.service.UserService;

public class UserServiceImp implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        userDao.save();
    }
}
