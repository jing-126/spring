package com.spring.service.impl;

import com.spring.dao.RoleDao;
import com.spring.domain.Role;
import com.spring.service.RoleService;

import java.util.List;

public class RoleServiceImp implements RoleService {

    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> list() {
        List<Role> list = roleDao.findAll();
        return list;
    }

    @Override
    public void save(String roleName, String roleDesc) {
        roleDao.save(roleName,roleDesc);
    }


}
