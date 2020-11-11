package com.spring.dao.impl;

import com.spring.dao.UserDao;
import com.spring.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImp implements UserDao {

    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }


    @Override
    public List<User> findAll() {
        String sql = "select * from sys_user";
        List<User> list = template.query(sql, new BeanPropertyRowMapper<>(User.class));
        return list;
    }

    @Override
    public long saveUser(User user) {
        String sql = "insert into sys_user values (?,?,?,?,?)";
//        创建PrepareStatementCreator
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                使用原始jdbc完成preparedStatement的组建
                PreparedStatement preparedStatement = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1,null);
                preparedStatement.setString(2,user.getUsername());
                preparedStatement.setString(3,user.getEmail());
                preparedStatement.setString(4,user.getPassword());
                preparedStatement.setString(5,user.getPhoneNum());

                return preparedStatement;
            }
        };
//        创建keyHolder
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
//        保存用户信息
        template.update(psc, keyHolder);
//        获取自动生成的主键id
        long userId = keyHolder.getKey().longValue();
        return userId;
    }

    @Override
    public void saveUserRoleRel(long userId, long[] roleIds) {
        String sql = "insert into sys_user_role values (?,?)";
        for (Long roleId : roleIds) {
            template.update(sql,userId,roleId);
        }
    }

    @Override
    public void delUserRoleRel(long userId) {
        String sql = "delete from sys_user_role where userId = ?";
        template.update(sql,userId);
    }

    @Override
    public void delUser(long userId) {
        String sql = "delete from sys_user where id = ?";
        template.update(sql,userId);
    }

    @Override
    public User login(User user) {
        String sql = "select * from sys_user where username = ? and password = ?";
        User user1 = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user.getUsername(), user.getPassword());
        return user1;
    }
}
