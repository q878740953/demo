package com.example.service;

import com.example.domain.Order;

import java.util.List;
import java.util.Map;

public interface IOrderService {

    List<Order> findAllOrder();

    void save(Order order);

    int delete(String ids);

    Order findOrderById(int id);

    Map<String, Object> payBack(int id, int payBackPrice);



}
