package com.example.demo.com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login", "/user/register", "/user/uploadImage");
    }

    @Bean
    public MyInterceptor authenticationInterceptor() {
        return new MyInterceptor();
    }
}
