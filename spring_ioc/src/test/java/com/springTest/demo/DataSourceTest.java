package com.springTest.demo;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import javax.sql.PooledConnection;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class DataSourceTest {
    @Test
//    测试手动创建c3p0数据源
    public void test01() throws PropertyVetoException, SQLException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/travel?serverTimezone=UTC");
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
    @Test
//    测试手动创建druid数据源
    public void test02() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/travel?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        DruidPooledConnection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
    @Test
//    测试手动创建c3p0数据源(使用配置文件)
    public void test03() throws PropertyVetoException, SQLException {
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        String driver = rb.getString("jdbc.driver");
        String url = rb.getString("jdbc.url");
        String username = rb.getString("jdbc.username");
        String password = rb.getString("jdbc.password");
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
    @Test
//    测试手动创建druid数据源(配置文件)
    public void test04() throws Exception {
        Properties prop = new Properties();
        InputStream s = DataSourceTest.class.getClassLoader().getResourceAsStream("druid.properties");
        prop.load(s);
        DataSource ds = DruidDataSourceFactory.createDataSource(prop);
        Connection connection = ds.getConnection();
        System.out.println(connection);
        connection.close();
    }
    @Test
//    测试Spring容器创建c3p0数据源
    public void test05() throws PropertyVetoException, SQLException {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
//        DataSource dataSource = (DataSource) app.getBean("c3p0");
        ComboPooledDataSource dataSource = app.getBean(ComboPooledDataSource.class);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
    @Test
//    测试Spring容器创建druid数据源
    public void test06() throws SQLException {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        DruidDataSource dataSource = app.getBean(DruidDataSource.class);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
}
