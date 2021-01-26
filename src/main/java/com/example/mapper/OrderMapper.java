package com.example.mapper;

import com.example.domain.Order;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderMapper {

    @Insert("insert into order_records (goods_name, goods_price, goods_number, goods_source, time) values (#{goodsName},#{goodsPrice},#{goodsNumber},#{goodsSource}, #{time})")
    void save(Order order);

    @Select("select * from order_records order by time desc")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "goodsName", column = "goods_name"),
            @Result(property = "goodsPrice", column = "goods_price"),
            @Result(property = "goodsSource", column = "goods_source"),
            @Result(property = "goodsNumber", column = "goods_number"),
            @Result(property = "payBackPrice", column = "pay_back_price"),
            @Result(property = "isPayBack", column = "is_pay_back"),
            @Result(property = "time", column = "time")
    })
    List<Order> findAllOrder();

    @Delete("delete from order_records where id in (#{ids})")
    void delete(String ids);
}
