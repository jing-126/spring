package com.spring.dao.impl;

import com.spring.dao.UserDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImp implements UserDao {
    private JdbcTemplate template;
    public void setTemplate(JdbcTemplate template){
        this.template = template;
    }
    @Override
    public void out(String outMan, double money) {
        template.update("update user set money = money - ? where name = ?",money,outMan);
    }

    @Override
    public void in(String inMan, double money) {
        template.update("update user set money = money + ? where name = ?",money,inMan);
    }
}
