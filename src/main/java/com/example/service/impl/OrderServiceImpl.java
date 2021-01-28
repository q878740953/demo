package com.example.service.impl;

import com.example.domain.Order;
import com.example.mapper.OrderMapper;
import com.example.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> findAllOrder(int userId) {
        List<Order> orderList = orderMapper.findAllOrder(userId);
        return orderList;
    }

    @Override
    public void save(Order order, int userId) {
        order.setTime(new Date());
        order.setUserId(userId);
        orderMapper.save(order);
    }

    @Override
    public int delete(String ids, int userId) {
        return orderMapper.delete(ids, userId);
    }

    @Override
    public Order findOrderById(int id, int userId) {
        return orderMapper.findOrderById(id, userId);
    }

    @Override
    public Map<String, Object> payBack(int id, int payBackPrice, int userId) {
        Map<String, Object> map = new HashMap<>();
        Order order = orderMapper.findOrderById(id, userId);
        if (order != null){
            order.setPayBackPrice(payBackPrice);
            if (payBackPrice != 0){
                order.setIsPayBack(1);
                order.setCommission(order.getPayBackPrice() - order.getGoodsPrice() * order.getGoodsNumber());
            }else {
                order.setIsPayBack(0);
                order.setCommission(0);
            }
            int i = orderMapper.update(order);
            if (i == 1){
                map.put("code", 0);
                map.put("msg", "返款成功");
            }else {
                map.put("code", 1);
                map.put("msg", "返款出错，请联系管理员！");
            }
        }else {
            map.put("code", 1);
            map.put("msg", "订单不存在");
        }
        return map;
    }
}
