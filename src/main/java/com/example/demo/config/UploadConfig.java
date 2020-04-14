package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UploadConfig implements WebMvcConfigurer {
    //映射图片保存地址
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/avatars/**").addResourceLocations("file:E:\\Project\\HLJDormitoryManagement\\src\\main\\resources\\static\\avatars\\");
        registry.addResourceHandler("/repair/**").addResourceLocations("file:E:\\Project\\HLJDormitoryManagement\\src\\main\\resources\\static\\repair\\");
    }

}