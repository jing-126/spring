package com.mybatis.dao;

import com.mybatis.domain.User;

import java.util.List;

public interface UserMapper {
    List<User> findAll() ;

    User findUserById(int id);

    List<User> findByCondition(User user);

    List<User> findByIds(List<Integer> list);

    void addUser(User user);
}
