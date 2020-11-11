package com.mybatis_anno.test;

import com.mybatis_anno.domain.Order;
import com.mybatis_anno.domain.User;
import com.mybatis_anno.mapper.OrderMapper;
import com.mybatis_anno.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MyBatisTest2 {

    private OrderMapper orderMapper;

    @Before
    public void before() throws IOException {
//        加载核心配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
//        获取SqlSessionFactory对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
//        获取SqlSession对象
        SqlSession sqlSession = build.openSession(true);
//        获取userMapper对象
        orderMapper = sqlSession.getMapper(OrderMapper.class);
    }

    @Test
    public void testSave(){
        List<Order> all = orderMapper.findAll();
        for (Order order : all) {
            System.out.println(order);
        }
    }

    @Test
    public void testFindByUid(){
        List<Order> list = orderMapper.findByUid(1);
        for (Order order : list) {
            System.out.println(order);
        }
    }


}
