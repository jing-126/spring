package com.spring.service;

import com.spring.domain.User;

import java.util.List;

public interface UserService {
    List<User> list();

    void save(User user, long[] roleIds);


    void del(long userId);

    Boolean login(User user);
}
