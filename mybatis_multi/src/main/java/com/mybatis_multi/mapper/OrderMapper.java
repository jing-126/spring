package com.mybatis_multi.mapper;

import com.mybatis_multi.domain.Order;

import java.util.List;

public interface OrderMapper {
    List<Order> findAll();
}
