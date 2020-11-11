package com.mybatis_anno.mapper;

import com.mybatis_anno.domain.Order;
import com.mybatis_anno.domain.User;
import com.mybatis_anno.handler.DateHandler;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {

    @Select("select *, o.id oid from user u, orders o where u.id=o.uid")
    /*@Results({
            @Result(column = "oid", property = "id"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "total", property = "total"),
            @Result(column = "uid", property = "user.id"),
            @Result(column = "username", property = "user.username"),
            @Result(column = "password", property = "user.password"),
            @Result(column = "birthday", property = "user.birthday", typeHandler = DateHandler.class),
    })*/
    @Results({
            @Result(column = "oid", property = "id"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "total", property = "total"),
            @Result(
                    property = "user",//要封装的属性名称
                    column = "uid",//根据那个字段查询
                    javaType = User.class,
                    one = @One(select = "com.mybatis_anno.mapper.UserMapper.findById")
            )
    })
    List<Order> findAll();

    @Select("select * from orders where uid=#{uid}")
    List<Order> findByUid(int uid);
}
