package com.example.demo.config;

import com.example.demo.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

//@EnableWebMvc  //全部接管springmvc,不建议使用
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("/login");
                registry.addViewController("index.html").setViewName("/login");
                registry.addViewController("main1.html").setViewName("/index/systemadmin_index");
                registry.addViewController("main2.html").setViewName("/index/admin_index");
                registry.addViewController("main3.html").setViewName("/index/student_index");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").
                        excludePathPatterns("/","/index.html","/user/login","assets/**","/webjars/**");
            }
        };
        return webMvcConfigurer;
    }
}
