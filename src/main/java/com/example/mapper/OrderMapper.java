package com.example.mapper;

import com.example.domain.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface OrderMapper {

    @Insert("insert into t_order (goods_name, goods_price, goods_number, goods_source, time) values (#{goodsName},#{goodsPrice},#{goodsNumber},#{goodsSource}, #{time})")
    void save(Order order);
}
