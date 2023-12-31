package com.example.contact_message.entity;

import lombok.Data;


@Data
public class User {
    private String username;
    private String password;
    private String role;
    private Long id;
}
