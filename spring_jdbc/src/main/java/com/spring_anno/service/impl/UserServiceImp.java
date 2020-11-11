package com.spring_anno.service.impl;

import com.spring_anno.dao.UserDao;
import com.spring_anno.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional(isolation = Isolation.DEFAULT)//配置所有方法
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
//    @Transactional(isolation = Isolation.DEFAULT,timeout = -1,readOnly = false,propagation = Propagation.REQUIRED)//配置单个方法
    public void transfer(String outMan, String inMan, double money) {
        userDao.out(outMan,money);
        int i = 1 / 0;
        userDao.in(inMan, money);
    }
}
