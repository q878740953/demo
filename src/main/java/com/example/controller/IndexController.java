package com.example.controller;

import com.example.domain.Order;
import com.example.mapper.OrderMapper;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping(path = {"/", "/index"})
    public String index(){
        return "index";
    }

    @RequestMapping("/getOrder")
    @ResponseBody
    public Map<String, Object> getOrder(){
        Map<String, Object> map = new HashMap<>();
        List<Order> orderList = orderMapper.findAllOrder();
        map.put("code", 0);
        map.put("data", orderList);
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public Map<String, Object> save(Order order){
        order.setTime(new Date());
        Map<String, Object> map = new HashMap<>();
        try {
            orderMapper.save(order);
            map.put("code", 0);
        }catch (Exception e){
            map.put("code", 1);
            map.put("msg", "保存出现异常，请重试");
        }
        return map;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(String ids){
        Map<String, Object> map = new HashMap<>();
        try {
            System.out.println(ids);
            orderMapper.delete(ids);
            map.put("code", 0);
        }catch (Exception e){
            map.put("code", 1);
            map.put("msg", "保存出现异常，请重试");
        }
        return map;
    }
}
