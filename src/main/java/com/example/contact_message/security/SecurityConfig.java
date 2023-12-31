package com.example.contact_message.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/register").permitAll() // 允许所有人访问登录和注册页面
                .anyRequest().authenticated() // 其他所有请求都需要认证
                .and()
                .formLogin()
                .loginPage("/login") // 指定登录页面的URL
                .successForwardUrl("/welcome") // 登录成功后默认跳转到的URL
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}