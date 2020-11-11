package com.spring.service.impl;

import com.spring.dao.RoleDao;
import com.spring.dao.UserDao;
import com.spring.domain.Role;
import com.spring.domain.User;
import com.spring.service.UserService;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public class UserServiceImp implements UserService {
    private UserDao userDao;
    private RoleDao roleDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<User> list() {
        List<User> list = userDao.findAll();
        for (User user : list) {
            long id = user.getId();
            List<Role> roles = roleDao.findRoleByUserId(id);
            user.setRoles(roles);
        }
        return list;
    }

    @Override
    public void save(User user, long[] roleIds) {
//        保存用户信息
        long userId = userDao.saveUser(user);
//        保存用户信息和角色对应关系
        userDao.saveUserRoleRel(userId, roleIds);
    }

    @Override
    public void del(long userId) {
//        删除用户和角色关系
        userDao.delUserRoleRel(userId);
//        删除用户
        userDao.delUser(userId);
    }

    @Override
    public Boolean login(User user) {
            User user1 = userDao.login(user);
            if (user1 != null){
                return true;
            }else {
                return false;
            }
    }

}
