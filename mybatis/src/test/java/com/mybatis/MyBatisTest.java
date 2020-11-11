package com.mybatis;

import com.mybatis.domain.User;
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
//        获取核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
//        获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        获取SQLSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        执行操作 参数：namespace + id
        List<User> list = sqlSession.selectList("userMapper.findAll");
//        打印数据
        System.out.println(list);
//        释放资源
        sqlSession.close();
    }

    @Test
    public void test02() throws IOException {
//        User对象
        User user = new User();
        user.setUsername("张三");
        user.setPassword("123456");
//        获取核心配置文件
        InputStream ras = Resources.getResourceAsStream("SqlMapConfig.xml");
//        获取SQLSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(ras);
//        获取SQLSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        执行SQL语句
        sqlSession.insert("userMapper.addUser",user);
//        提交事务
        sqlSession.commit();
//        释放资源
        sqlSession.close();
    }

    @Test
    public void test03() throws IOException {
//        User对象
        User user = new User();
        user.setId(2);
        user.setUsername("李四");
        user.setPassword("123456");
//        获取核心配置文件
        InputStream ras = Resources.getResourceAsStream("SqlMapConfig.xml");
//        获取SQLSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(ras);
//        获取SQLSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        执行SQL语句
        sqlSession.update("userMapper.userUpdate",user);

//        提交事务
        sqlSession.commit();
//        释放资源
        sqlSession.close();
    }

    @Test
    public void test04() throws IOException {
//        获取核心配置文件
        InputStream ras = Resources.getResourceAsStream("SqlMapConfig.xml");
//        获取SQLSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(ras);
//        获取SQLSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        执行SQL语句
        sqlSession.delete("userMapper.delete",6);
//        提交事务
        sqlSession.commit();
//        释放资源
        sqlSession.close();
    }
}
