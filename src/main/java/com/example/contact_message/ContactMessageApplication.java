package com.example.contact_message;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@MapperScan("com.example.contact_message.mapper")
@SpringBootApplication
public class ContactMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactMessageApplication.class, args);
    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/pictures/**")
                    .addResourceLocations("classpath:/pictures/");
        }
    }
}
