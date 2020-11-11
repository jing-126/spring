package com.mybatis_anno.test;

import com.mybatis_anno.domain.Role;
import com.mybatis_anno.domain.User;
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

public class MyBatisTest {

    private UserMapper userMapper;

    @Before
    public void before() throws IOException {
//        加载核心配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
//        获取SqlSessionFactory对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
//        获取SqlSession对象
        SqlSession sqlSession = build.openSession(true);
//        获取userMapper对象
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("测试");
        user.setPassword("123456");
        user.setBirthday(new Date());

        userMapper.save(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(8);
        user.setUsername("张三");
        user.setPassword("123456");
        user.setBirthday(new Date());

        userMapper.update(user);
    }

    @Test
    public void testDelete(){
        userMapper.delete(4);
    }

    @Test
    public void testFindById(){
        User user = userMapper.findById(1);
        System.out.println(user);
    }

    @Test
    public void testFindAll(){
        List<User> list = userMapper.findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void testAnno(){
//        List<User> list = userMapper.findUserAndOrderAll();
        List<User> list = userMapper.findUserAndRoleAll();
        for (User user : list) {
            System.out.println(user);
        }
    }
}
