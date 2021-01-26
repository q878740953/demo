package com.example.controller;

import com.example.domain.Order;
import com.example.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class IndexController {
    @Autowired
    private OrderMapper orderMapper;
    @RequestMapping(path = {"/", "/index"})
    public String index(){
        System.out.println("=============");
        return "index";
    }
    @RequestMapping("/save")
    public String save(Order order){
        order.setTime(new Date());
        orderMapper.save(order);
        return "redirect:/index";
    }
}
