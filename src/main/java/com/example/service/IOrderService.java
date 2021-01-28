package com.example.service;

import com.example.domain.Order;

import java.util.List;
import java.util.Map;

public interface IOrderService {

    List<Order> findAllOrder(int userId);

    void save(Order order, int userId);

    int delete(String ids, int userId);

    Order findOrderById(int id, int userId);

    Map<String, Object> payBack(int id, int payBackPrice, int userId);



}
