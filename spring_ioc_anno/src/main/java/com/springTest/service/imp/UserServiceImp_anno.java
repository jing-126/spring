package com.springTest.service.imp;

import com.springTest.dao.UserDao;
import com.springTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

//    <bean id="userDao" class="com.springTest.service.imp.UserServiceImp_anno"></bean>
//@Component("userService")
@Service("userService")
@Scope("singleton")
public class UserServiceImp_anno implements UserService {
//    @Value("value注解练习")
    @Value("${t.url}")
    private String msg;
//    @Autowired
//    @Qualifier("userDao")
    @Resource(name="userDao")
    private UserDao userDao;

/*
    private final UserDao userDao;
    public UserServiceImp_anno(@Qualifier("userDao") UserDao userDao) {
        this.userDao = userDao;
        System.out.println("userService对象");
    }
 */

//public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }

    @Override
    public void save() {
        userDao.save();
        System.out.println(msg);
    }
    @PreDestroy
    public void destroy(){
        System.out.println("UserService销毁方法执行");
    }
    @PostConstruct
    public void init(){
        System.out.println("UserService初始化方法执行");
    }

}
