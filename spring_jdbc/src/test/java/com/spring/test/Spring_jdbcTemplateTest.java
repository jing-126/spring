package com.spring.test;

import com.spring.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")//加载配置文件
public class Spring_jdbcTemplateTest {
    @Autowired
    private JdbcTemplate template;
    @Test
//    改
    public void updateTest(){
//        定义SQL语句
        String sql = "update user set money = ? where name = ?";
        template.update(sql,12000,"j");
    }
    @Test
//    查
    public void queryTest(){
//        定义SQL语句
        String sql = "select * from user";
        List<User> list = template.query(sql, new BeanPropertyRowMapper<>(User.class));
        System.out.println(list);
    }
    @Test
    public void queryCountTest(){
//        定义SQL语句
        String sql = "select count(*) from user";
        Integer count = template.queryForObject(sql, Integer.class);
        System.out.println(count);
    }
    @Test
//    删
    public void deleteTest(){
//        定义SQL语句
        String sql = "delete from user where money=? AND name = ?";
        template.update(sql,12000,"j");
    }
    @Test
//    增
    public void createTest(){
//        定义SQL语句
        String sql = "insert into user values (?,?)";
        template.update(sql,"j",12000);
    }
}
