package com.springTest.factory;

import com.springTest.dao.UserDao;
import com.springTest.dao.daoImp.UserDaoImp_Anno;

public class DynamicFactory {
    public UserDao getUserDao(){
        System.out.println("工厂实例方法实例化");
        return new UserDaoImp_Anno();
    }
}
