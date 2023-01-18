package com.example.drama_kill_system.config;

import com.example.drama_kill_system.interceptors.opInterceptor;
import com.example.drama_kill_system.utils.RedisUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Resource
    private RedisUtil redisUtil ;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截除了User外目录
        registry.addInterceptor(new opInterceptor(redisUtil))
                .addPathPatterns("/**")
                .excludePathPatterns("/user/**");
    }
}
