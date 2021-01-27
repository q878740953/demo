package com.example.controller;

import com.example.domain.Order;
import com.example.domain.User;
import com.example.service.IOrderService;
import com.example.service.IUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private IUserService userService;

    @RequestMapping(path = {"/", "/index", "/index.html"})
    public String index(){
        return "index";
    }

    @GetMapping( "/login")
    public String login(){
        return "login-1";
    }
    @GetMapping( "/welcome")
    public String welcome(){
        return "welcome-1";
    }
    @GetMapping( "/table")
    public String table(){
        return "table";
    }
    @GetMapping("/addOrder")
    public String addOrder(){
        return "table/add";
    }

    @GetMapping("/submitPayBack")
    public String submitPayBack(){
        return "table/edit";
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, String> loginCheck(User user, HttpSession httpSession, HttpServletRequest httpServletRequest){
        Map<String, String> map = new HashMap<>();
        User loginUser = userService.login(user);
        if (loginUser != null){
            String remoteAddr = httpServletRequest.getRemoteAddr();
            System.out.println(remoteAddr);
            httpSession.setAttribute("loginUser", loginUser);
            map.put("code", "0");
        }else {
            map.put("code", "1");
            map.put("msg", "登录失败");
        }
        return map;
    }

    @RequestMapping("/getOrder")
    @ResponseBody
    public Map<String, Object> getOrder(int page, int limit, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("loginUser");
        Map<String, Object> map = new HashMap<>();
        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<Order> orderList = orderService.findAllOrder(user.getId());
        map.put("code", 0);
        map.put("count", startPage.getTotal());
        map.put("data", orderList);
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public Map<String, Object> save(Order order, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("loginUser");
        Map<String, Object> map = new HashMap<>();
        try {
            orderService.save(order, user.getId());
            map.put("code", 0);
        }catch (Exception e){
            map.put("code", 1);
            map.put("msg", "保存出现异常，请重试");
        }
        return map;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(String ids, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("loginUser");
        Map<String, Object> map = new HashMap<>();
        try {
            orderService.delete(ids, user.getId());
            map.put("code", 0);
        }catch (Exception e){
            map.put("code", 1);
            map.put("msg", "保存出现异常，请重试");
        }
        return map;
    }

    @RequestMapping("/payBack")
    @ResponseBody
    public Map<String, Object> payBack(@RequestParam(value = "id")int id,
                                       @RequestParam(value = "payBackPrice")int payBackPrice,
                                       HttpSession httpSession){
        User user = (User) httpSession.getAttribute("loginUser");
        return orderService.payBack(id, payBackPrice, user.getId());
    }
}
