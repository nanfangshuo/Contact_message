package com.example.contact_message.service;

import com.example.contact_message.entity.User;
import com.example.contact_message.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    public User findByUsernameAndPassword(String username, String password) {
        logger.info("Trying to find user with username: {} and password: {}", username, password);
        User foundUser = userMapper.findByUsernameAndPassword(username, password);
        if (foundUser != null) {
            logger.info("Found user: {}", username);
        } else {
            logger.info("User not found: {}", username);
        }
        return foundUser;
    }

    public User findByUsername(String username) {
        logger.info("Trying to find user with username: {}", username);
        User foundUser = userMapper.findByUsername(username);
        if (foundUser != null) {
            logger.info("Found user: {}", username);
        } else {
            logger.info("User not found: {}", username);
        }
        return foundUser;
    }

    public void save(User user) {
        logger.info("Saving new user: {}", user.getUsername());
        userMapper.insert(user);
    }
}