package com.example.contact_message.controller;

import com.example.contact_message.entity.Contact;
import com.example.contact_message.entity.User;
import com.example.contact_message.mapper.ContactMapper;
import com.example.contact_message.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactController {

    private final UserMapper userMapper;
    private final ContactMapper contactMapper;

    @Autowired
    public ContactController(UserMapper userMapper, ContactMapper contactMapper) {
        this.userMapper = userMapper;
        this.contactMapper = contactMapper;
    }

    @GetMapping("/currentUser")
    public User getCurrentUser(HttpSession session) {
        // 从Session中获取当前登录的用户
        return (User) session.getAttribute("user");
    }

    @GetMapping("/contacts")
    public List<Contact> getContacts(HttpSession session) {
        // 从Session中获取当前登录的用户
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Long currentUserId = userMapper.findIdByUsername(user.getUsername());
            // 获取当前用户的所有联系人
            return contactMapper.selectByUserId(currentUserId);
        }
        return null;
    }

    @PostMapping("/editContact")
    public ResponseEntity<?> editContact(@RequestBody Contact contact) {
        // 编辑联系人的信息，并返回操作结果
        int result = contactMapper.updateContact(contact);
        if (result > 0) {
            return ResponseEntity.ok().body("{\"success\":true}");
        } else {
            return ResponseEntity.badRequest().body("{\"success\":false, \"message\":\"编辑联系人失败\"}");
        }
    }

    @PostMapping("/deleteContact")
    public ResponseEntity<?> deleteContact(@RequestBody Contact contact) {
        // 删除联系人，并返回操作结果
        int result = contactMapper.deleteContact(contact.getId());
        if (result > 0) {
            return ResponseEntity.ok().body("{\"success\":true}");
        } else {
            return ResponseEntity.badRequest().body("{\"success\":false, \"message\":\"删除联系人失败\"}");
        }
    }

    @PostMapping("/addContact")
    public ResponseEntity<?> addContact(@RequestBody Contact contact, HttpSession session) {
        // 从Session中获取当前登录的用户
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Long currentUserId = userMapper.findIdByUsername(user.getUsername());
            // 设置新联系人的userId为当前用户的id
            contact.setUserId(String.valueOf(currentUserId));            // 添加新的联系人，并返回操作结果
            int result = contactMapper.insertContact(contact);
            if (result > 0) {
                return ResponseEntity.ok().body("{\"success\":true}");
            } else {
                return ResponseEntity.badRequest().body("{\"success\":false, \"message\":\"添加联系人失败\"}");
            }
        }
        return ResponseEntity.badRequest().body("{\"success\":false, \"message\":\"用户未登录\"}");
    }

    @GetMapping("/searchContact")
    public List<Contact> searchContact(@RequestParam String keyword, HttpSession session) {
        // 从Session中获取当前登录的用户
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Long currentUserId = userMapper.findIdByUsername(user.getUsername());
            // 根据关键词和用户ID搜索联系人
            return contactMapper.searchByKeywordAndUserId(keyword, currentUserId);
        }
        return null;
    }
}