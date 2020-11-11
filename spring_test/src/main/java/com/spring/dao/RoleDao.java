package com.spring.dao;

import com.spring.domain.Role;

import java.util.List;

public interface RoleDao {
    List<Role> findAll();

    void save(String roleName, String roleDesc);

    List<Role> findRoleByUserId(long id);
}
