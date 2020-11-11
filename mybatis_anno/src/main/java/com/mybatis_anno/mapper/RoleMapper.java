package com.mybatis_anno.mapper;

import com.mybatis_anno.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {

    @Select("select * from user_role ur, role r where ur.roleId = r.id and ur.userId=#{id}")
    List<Role> findRoleId(int id);
}
