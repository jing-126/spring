package com.springTest.dao.daoImp;

import com.springTest.dao.UserDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//    <bean id="userDao" class="com.springTest.dao.daoImp.UserDaoImp_Anno"></bean>
//@Component("userDao")
@Repository("userDao")
public class UserDaoImp_Anno implements UserDao {
   /* private String username;
    private int age;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }*/
    @PostConstruct
    public void init(){
        System.out.println("初始化方法执行");
    }
    
    public void save() {
//        System.out.println(username + "===" + age);
        System.out.println("save running....");
    }

    //Spring调用无参构造方法创建对象
    public UserDaoImp_Anno() {
        System.out.println("userDao对象创建");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("销毁方法执行");
    }
}
