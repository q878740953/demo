package com.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        if (exception instanceof BadCredentialsException){
            response.getWriter().write(new ObjectMapper().writeValueAsString("账号或者密码错误"));
        }else if (exception instanceof LockedException){
            response.getWriter().write(new ObjectMapper().writeValueAsString("账号被锁定"));
        }
    }
}
