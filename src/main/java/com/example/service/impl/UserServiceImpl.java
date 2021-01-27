package com.example.service.impl;

import com.example.domain.User;
import com.example.mapper.UserMapper;
import com.example.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(User user) {
        User loginUser = null;
        if (user != null){
            loginUser = userMapper.login(user);
            if (loginUser != null){
                logger.info(loginUser.getUsername() + "登录成功！");
            }
        }
        return loginUser;
    }
}
