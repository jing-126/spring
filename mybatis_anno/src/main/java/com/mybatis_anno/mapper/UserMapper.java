package com.mybatis_anno.mapper;

import com.mybatis_anno.domain.Role;
import com.mybatis_anno.domain.User;
import com.mybatis_anno.handler.DateHandler;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
//@Mapper
public interface UserMapper {

    @Insert("insert into user values (#{id},#{username},#{password},#{birthday})")
    void save(User user);

    @Update("update user set username=#{username},password=#{password} where id = #{id}")
    void update(User user);

    @Delete("delete from user where id=#{id}")
    void delete(int id);

    @Select("select * from user where id=#{id}")
    User findById(int id);

    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "birthday", property = "birthday", typeHandler = DateHandler.class),
            @Result(
                    property = "orderList",
                    column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.mybatis_anno.mapper.OrderMapper.findByUid")
            )
    })
    List<User> findUserAndOrderAll();

    @Select("select * from user")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "birthday", property = "birthday", typeHandler = DateHandler.class),
            @Result(
                    property = "roleList",
                    column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.mybatis_anno.mapper.RoleMapper.findRoleId")
            )
    })
    List<User> findUserAndRoleAll();
}
