package com.example.service.impl;

import com.example.domain.Order;
import com.example.mapper.OrderMapper;
import com.example.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> findAllOrder() {
        List<Order> orderList = orderMapper.findAllOrder();
        for (Order order : orderList) {
            if (order.getPayBackPrice() != 0){
                order.setCommission(order.getPayBackPrice() - order.getGoodsPrice());
            }
        }
        return orderList;
    }

    @Override
    public void save(Order order) {
        orderMapper.save(order);
    }

    @Override
    public int delete(String ids) {
        return orderMapper.delete(ids);
    }

    @Override
    public Order findOrderById(int id) {
        return orderMapper.findOrderById(id);
    }

    @Override
    public Map<String, Object> payBack(int id, int payBackPrice) {
        Map<String, Object> map = new HashMap<>();
        Order order = orderMapper.findOrderById(id);
        if (order != null){
            order.setPayBackPrice(payBackPrice);
            if (payBackPrice != 0){
                order.setIsPayBack(1);
            }else {
                order.setIsPayBack(0);
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
