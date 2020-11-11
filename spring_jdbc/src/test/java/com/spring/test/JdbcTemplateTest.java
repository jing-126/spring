package com.spring.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.spring.domain.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;


public class JdbcTemplateTest {
    @Test
//    测试JdbcTemplate开发步骤
    public void test01(){
//        创建数据源对象
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        DruidDataSource dataSource = app.getBean(DruidDataSource.class);
        JdbcTemplate template = new JdbcTemplate();
//        设置数据源
        template.setDataSource(dataSource);
//        执行操作
        String sql = "insert into user values (?,?)";
        int i = template.update(sql, "jing", 100);
        System.out.println(i);
    }
    @Test
//    使用spring创建template对象
    public void test02(){
//        创建JdbcTemplate对象
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate template = app.getBean(JdbcTemplate.class);
//        定义SQL语句
//        String sql = "insert into user values (?,?)";
//        int i = template.update(sql, "j", 10000);
//        System.out.println(i);
        String sql = "select * from user";
        List<User> list = template.query(sql, new BeanPropertyRowMapper<>(User.class));
        for (User user : list) {
            System.out.println(user);
        }
    }
}
