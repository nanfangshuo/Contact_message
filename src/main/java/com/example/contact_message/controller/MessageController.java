package com.example.contact_message.controller;

import com.example.contact_message.entity.Message;
import com.example.contact_message.entity.User;
import com.example.contact_message.mapper.MessageMapper;
import com.example.contact_message.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final UserMapper userMapper;
    private final MessageMapper messageMapper;

    @Autowired
    public MessageController(UserMapper userMapper, MessageMapper messageMapper) {
        this.userMapper = userMapper;
        this.messageMapper = messageMapper;
    }

    @GetMapping("/messages")
    public List<Message> getMessages(HttpSession session) {
        // 从Session中获取当前登录的用户
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            // 如果用户未登录，就返回一个空的列表
            return new ArrayList<>();
        }
        //获取当前用户的ID
        Long currentUserId = userMapper.findIdByUsername(currentUser.getUsername());
        // 根据当前用户的ID获取所有发送给他的消息
        return messageMapper.selectByToUserId(currentUserId);
    }
    @PostMapping("/sendMessage")
    public ResponseEntity<?> sendMessage(@RequestBody Message message, HttpSession session) {
        // 从Session中获取当前登录的用户
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            // 如果用户未登录，就返回一个错误信息
            return ResponseEntity.badRequest().body("{\"success\":false, \"message\":\"用户未登录\"}");
        }
        //获取当前用户的ID
        Long currentUserId = userMapper.findIdByUsername(currentUser.getUsername());
        // 根据接收者的用户名查找对应的用户ID
        Long toUserId = userMapper.findIdByUsername(message.getToUserName());
        if (toUserId == null) {
            // 如果找不到这个用户，就返回一个错误信息
            return ResponseEntity.badRequest().body("{\"success\":false, \"message\":\"接收者用户名不存在\"}");
        } else {
            // 如果找到了这个用户，就将用户ID设置为Message对象的toUserId，并将这个Message对象保存到数据库中
            message.setToUserId(toUserId);
            message.setFromUserId(currentUserId);
            message.setContent(message.getContent());
            message.setFromUserName(currentUser.getUsername());  // 设置发送者用户名
            message.setToUserName(message.getToUserName());  // 设置接收者用户名
            messageMapper.insert(message);  // 使用 insert 方法
            return ResponseEntity.ok().body("{\"success\":true}");
        }
    }
    @GetMapping("/allMessages")
    public List<Message> getAllMessages() {
        List<Message> messages = messageMapper.selectAll();
        // 添加日志以检查返回的消息
        System.out.println("Returned messages: " + messages);
        return messages;
    }
    @PostMapping("/editMessage")
    public ResponseEntity<?> editMessage(@RequestBody Message message) {
        int result = messageMapper.updateMessage(message);
        if (result > 0) {
            return ResponseEntity.ok().body("{\"success\":true}");
        } else {
            return ResponseEntity.badRequest().body("{\"success\":false, \"message\":\"编辑消息失败\"}");
        }
    }

    @PostMapping("/deleteMessage")
    public ResponseEntity<?> deleteMessage(@RequestBody Message message) {
        int result = messageMapper.deleteMessage(message.getId());
        if (result > 0) {
            return ResponseEntity.ok().body("{\"success\":true}");
        } else {
            return ResponseEntity.badRequest().body("{\"success\":false, \"message\":\"删除消息失败\"}");
        }
    }
}