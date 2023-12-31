package com.example.contact_message.mapper;

import com.example.contact_message.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT id, username, password, role FROM User WHERE username = #{username} AND password = #{password}")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Select("SELECT * FROM User WHERE username = #{username}")
    User findByUsername(@Param("username") String username);

    @Select("SELECT id FROM User WHERE username = #{username}")
    Long findIdByUsername(@Param("username") String username);
}