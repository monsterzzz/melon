package com.monster.melon.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class Config extends WebMvcConfigurationSupport {
    @Bean
    public AuthInterceptor auth(){
        return new AuthInterceptor();

    }

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(auth()).addPathPatterns("/*");
    }
}
