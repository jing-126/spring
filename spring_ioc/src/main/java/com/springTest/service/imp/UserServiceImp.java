package com.springTest.service.imp;

import com.springTest.dao.UserDao;
import com.springTest.dao.daoImp.UserDaoImp;
import com.springTest.service.UserService;

public class UserServiceImp implements UserService {
    private UserDao userDao;

    public UserServiceImp() {
        System.out.println("userService对象");
    }

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
        System.out.println("userService对象");
    }


/*public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }*/

    @Override
    public void save() {
        userDao.save();
    }


}
