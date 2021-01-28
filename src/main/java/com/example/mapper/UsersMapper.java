package com.example.mapper;

import com.example.domain.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UsersMapper {

    @Select("select * from users where username = #{username} and password = #{password}")
    Users login(Users users);

    @Select("select * from user where username = #{username}")
    Users findUserByUsername(String username);
}
