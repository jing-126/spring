package com.mybatis.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DateHandler extends BaseTypeHandler<Date> {
//    将java类型 转换成 数据库类型
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        long time = date.getTime();
        preparedStatement.setLong(i,time);
    }

//    将数据库中的类型 转换成 java类型
//    resultSet查询出的结果集
//    String 要转换的字段名称
    @Override
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
        long time = resultSet.getLong(s);
        Date date = new Date(time);
        return date;
    }

//    将数据库中的类型 转换成 java类型
    @Override
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        long time = resultSet.getLong(i);
        Date date = new Date(time);
        return date;
    }

//    将数据库中的类型 转换成 java类型
    @Override
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        long time = callableStatement.getLong(i);
        Date date = new Date(time);
        return date;
    }
}
