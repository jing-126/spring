package com.spring.dao.impl;

import com.spring.dao.RoleDao;
import com.spring.domain.Role;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RoleDaoImp implements RoleDao {
//    jdbcTemplate对象
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Role> findAll() {
        String sql = "select * from sys_role";
        List<Role> list = template.query(sql, new BeanPropertyRowMapper<>(Role.class));
        return list;
    }

    @Override
    public void save(String roleName, String roleDesc) {
        String sql = "insert into sys_role values (null , ?, ?)";
        template.update(sql,roleName,roleDesc);
    }

//    多表查询
    @Override
    public List<Role> findRoleByUserId(long id) {
        String sql = "select * from sys_user_role ur, sys_role role where ur.roleId = role.id and ur.userId = ?";
        List<Role> list = template.query(sql, new BeanPropertyRowMapper<>(Role.class), id);
        return list;
    }
}
