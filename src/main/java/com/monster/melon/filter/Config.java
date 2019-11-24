package com.monster.melon.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class Config {
    @Bean
    public AuthInterceptor auth(){
        return new AuthInterceptor();

    }

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(auth()).addPathPatterns("/*");
    }
}
