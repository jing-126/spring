package com.mybatis_multi.mapper;

import com.mybatis_multi.domain.User;

import java.util.List;

public interface UserMapper {
    List<User> findAll();
    List<User> findUserAndRoleAll();
}
