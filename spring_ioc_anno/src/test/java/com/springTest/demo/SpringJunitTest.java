package com.springTest.demo;

import com.springTest.config.SpringConfiguration;
import com.springTest.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classPath:applicationContext.xml")
@ContextConfiguration(classes = {SpringConfiguration.class})
public class SpringJunitTest {
    @Autowired
    private UserService userService;
    @Autowired
    @Qualifier("c3p0")
    private DataSource dataSource;
    @Test
    public void test01(){
        userService.save();
    }
    @Test
    public void test02() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
}
