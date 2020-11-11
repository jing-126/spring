package com.mybatis.service.impl;

import com.mybatis.dao.UserMapper;
import com.mybatis.domain.User;
import com.mybatis.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserServiceImp implements UserService {
    public static void main(String[] args) throws IOException {
//        加载配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
//        获取SqlSessionFactory对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
//        获取SQLSession对象
        SqlSession sqlSession = build.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.findAll();
        System.out.println(list);
        User user = userMapper.findUserById(1);
        System.out.println(user);
    }
}
