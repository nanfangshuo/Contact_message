package com.example.contact_message.controller;

import com.example.contact_message.entity.User;
import com.example.contact_message.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // LoginController.java
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody User user, HttpSession session) {
        logger.info("Received login request for user: {}", user.getUsername());
        User foundUser = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (foundUser != null) {
            logger.info("Login successful for user: {}", user.getUsername());
            session.setAttribute("user", foundUser); // 将用户信息存储到Session中
            String redirectUrl = "/welcome";
            if ("admin".equals(foundUser.getRole())) {
                redirectUrl = "/admin";
            }
            return ResponseEntity.ok().body("{\"success\":true, \"redirectUrl\":\"" + redirectUrl + "\"}");
        } else {
            logger.info("Login failed for user: {}", user.getUsername());
            return new ResponseEntity<>("{\"success\":false}", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        logger.info("Received registration request for user: {}", user.getUsername());
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null) {
            logger.info("Username already taken: {}", user.getUsername());
            return ResponseEntity.badRequest().body("{\"success\":false, \"message\":\"用户名已被占用\"}");
        } else {
            userService.save(user);
            logger.info("Registration successful for user: {}", user.getUsername());
            return ResponseEntity.ok().body("{\"success\":true}");
        }
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


}