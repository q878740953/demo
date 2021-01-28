package com.example.service.impl;

import com.example.domain.Users;
import com.example.mapper.UsersMapper;
import com.example.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public Users login(Users users) {
        Users loginUsers = null;
        if (users != null){
            loginUsers = usersMapper.login(users);
            if (loginUsers != null){
                logger.info(loginUsers.getUsername() + "登录成功！");
            }
        }
        return loginUsers;
    }
}
