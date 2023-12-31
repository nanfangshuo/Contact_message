package com.example.contact_message.entity;

import lombok.Data;

@Data
public class Message {
    private Long id;
    private String content;
    private Long fromUserId;
    private Long toUserId;
    private String toUserName;
    private String fromUserName;

}