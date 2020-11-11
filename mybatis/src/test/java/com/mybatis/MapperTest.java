package com.mybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybatis.dao.UserMapper;
import com.mybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MapperTest {
    @Test
    public void test01() throws IOException {
//        加载核心配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
//        获取SqlSessionFactory对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
//        获取SqlSession对象
        SqlSession sqlSession = build.openSession();
//        获取userMapper对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        模拟user
        User user = new User();
//        user.setId(1);
//        user.setUsername("zhangsan");
//        user.setPassword("123");
        List<User> list = userMapper.findByCondition(user);
        System.out.println(list);
//        模拟Ids
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        List<User> ids = userMapper.findByIds(integerList);
        System.out.println(ids);
//        根据id查询
        User userById = userMapper.findUserById(3);
        System.out.println(userById);
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
//        模拟user数据
        User user = new User();
        user.setId(3);
        user.setUsername("测试");
        user.setPassword("123");
        user.setBirthday(new Date());
//        执行SQL
        userMapper.addUser(user);

//        提交事务
        sqlSession.commit();
//        关闭资源
        sqlSession.close();
    }

    @Test
//    测试PageHelper 分页助手插件
    public void test03() throws IOException {
//        加载核心配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
//        获取SqlSessionFactory对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
//        获取SqlSession对象
        SqlSession sqlSession = build.openSession();
//        获取userMapper对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        设置分页相关参数
        PageHelper.startPage(1,2);
//        查询
        List<User> all = userMapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }
        System.out.println(all);
//        获取分页相关参数
        PageInfo<User> pageInfo = new PageInfo<>(all);
        System.out.println("当前页:" + pageInfo.getPageNum());
        System.out.println("条数:" + pageInfo.getPageSize());
        System.out.println("总条数:" + pageInfo.getTotal());
        System.out.println("总页数:" + pageInfo.getPages());
        System.out.println("上一页:" + pageInfo.getPrePage());
        System.out.println("下一页:" + pageInfo.getNextPage());
        System.out.println("是否为第一页:" + pageInfo.isIsFirstPage());
        System.out.println("是否为最后一页:" + pageInfo.isIsLastPage());

//        关闭资源
        sqlSession.close();
    }
}
