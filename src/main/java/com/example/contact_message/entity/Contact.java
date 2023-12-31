package com.example.contact_message.entity;

import lombok.Data;

@Data
public class Contact {
    private Long id;  // 添加 id 属性
    private String avatar;
    private String name;
    private String phoneNumber;
    private String userId;
}