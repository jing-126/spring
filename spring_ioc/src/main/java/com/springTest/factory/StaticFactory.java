package com.springTest.factory;

import com.springTest.dao.UserDao;
import com.springTest.dao.daoImp.UserDaoImp;

public class StaticFactory {
//    工厂静态方法实例化
    public static UserDao getUserDao(){
        System.out.println("工厂静态方法实例化");
        return new UserDaoImp();
    }
}
