package com.spring.service;

import com.spring.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> list();

    void save(String roleName, String roleDesc);

}
