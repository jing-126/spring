package com.spring_anno.dao.impl;

import com.spring_anno.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("userDao")//在dao层类上用于实例化Bean，与Component作用相同
public class UserDaoImp implements UserDao {
    @Autowired
    private JdbcTemplate template;
    @Override
    public void out(String outMan, double money) {
        template.update("update user set money = money - ? where name = ?",money,outMan);
    }

    @Override
    public void in(String inMan, double money) {
        template.update("update user set money = money + ? where name = ?",money,inMan);
    }
}
