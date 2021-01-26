package com.example.controller;

import com.example.domain.Order;
import com.example.service.IOrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private IOrderService orderService;

    @RequestMapping(path = {"/", "/index"})
    public String index(){
        return "index";
    }

    @RequestMapping( "/login")
    public String login(){
        return "login-1";
    }

    @RequestMapping("/getOrder")
    @ResponseBody
    public Map<String, Object> getOrder(int page, int limit){
        Map<String, Object> map = new HashMap<>();
        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<Order> orderList = orderService.findAllOrder();
        map.put("code", 0);
        map.put("count", startPage.getTotal());
        map.put("data", orderList);
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public Map<String, Object> save(Order order){
        order.setTime(new Date());
        Map<String, Object> map = new HashMap<>();
        try {
            orderService.save(order);
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
            orderService.delete(ids);
            map.put("code", 0);
        }catch (Exception e){
            map.put("code", 1);
            map.put("msg", "保存出现异常，请重试");
        }
        return map;
    }

    @RequestMapping("/payBack")
    @ResponseBody
    public Map<String, Object> payBack(@RequestParam(value = "id")int id, @RequestParam(value = "payBackPrice")int payBackPrice){
        return orderService.payBack(id, payBackPrice);
    }
}
