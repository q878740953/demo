//package com.example.config;
//
//import com.example.domain.Users;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class LoginHandlerInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Users loginUser = (Users)request.getSession().getAttribute("loginUser");
//        if (loginUser != null){
//            return true;
//        }else {
//            System.out.println("用户未登录");
//            response.sendRedirect("/login");
//            return false;
//        }
//    }
//}
