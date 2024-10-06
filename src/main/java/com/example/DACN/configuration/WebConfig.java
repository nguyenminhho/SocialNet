package com.example.DACN.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Cho phép tất cả các đường dẫn
                .allowedOrigins("http://localhost:3000") // Chỉ cho phép từ origin này
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Các phương thức được cho phép
                .allowCredentials(true); // Cho phép cookie
    }
}