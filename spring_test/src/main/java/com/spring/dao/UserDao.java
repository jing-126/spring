package com.spring.dao;

import com.spring.domain.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();

    long saveUser(User user);

    void saveUserRoleRel(long userId, long[] roleIds);

    void delUserRoleRel(long userId);

    void delUser(long userId);

    User login(User user);
}
