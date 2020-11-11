package com.mybatis.dao.impl;

import com.mybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserMapperImp /*implements UserMapper*/ {
//    @Override
    public List<User> findAll() throws IOException {
//        加载配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
//        获取SqlSessionFactory对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
//        获取sqlSession对象
        SqlSession sqlSession = build.openSession();
//        执行SQL语句
        List<User> list = sqlSession.selectList("userMapper.findAll");
        return list;
    }
}
