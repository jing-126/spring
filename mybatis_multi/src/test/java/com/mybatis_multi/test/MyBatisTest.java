package com.mybatis_multi.test;

import com.mybatis_multi.domain.Order;
import com.mybatis_multi.domain.User;
import com.mybatis_multi.mapper.OrderMapper;
import com.mybatis_multi.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    @Test
    public void test01() throws IOException {
//        加载核心配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
//        获取SqlSessionFactory对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
//        获取sqlSession对象
        SqlSession sqlSession = build.openSession();
//        获取orderMapper对象
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<Order> orderList = orderMapper.findAll();
        for (Order order : orderList) {
            System.out.println(order);
        }
//        关闭资源
        sqlSession.close();
    }

    @Test
    public void test02() throws IOException {
//        加载核心配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
//        获取SqlSessionFactory对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
//        获取sqlSession对象
        SqlSession sqlSession = build.openSession();
//        获取userMapper对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
//        关闭资源
        sqlSession.close();
    }

    @Test
    public void test03() throws IOException {
//        加载核心配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
//        获取SqlSessionFactory对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
//        获取sqlSession对象
        SqlSession sqlSession = build.openSession();
//        获取userMapper对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findUserAndRoleAll();
        for (User user : userList) {
            System.out.println(user);
        }
//        关闭资源
        sqlSession.close();
    }
}
