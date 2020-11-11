package com.springTest.demo;

import com.springTest.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    @Test
//    测试scope(范围)属性
    public void test01(){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) app.getBean("userDao");
        UserDao userDao1 = (UserDao) app.getBean("userDao");
        System.out.println(userDao + "\n" + userDao1);
    }

    @Test
//    Bean配置声明周期 init初始化 destroy销毁方法
    public void test02(){
//        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) app.getBean("userDao");
        System.out.println(userDao);
        app.close();
    }
}
