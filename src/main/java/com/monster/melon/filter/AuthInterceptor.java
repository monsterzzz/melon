package com.monster.melon.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.monster.melon.serializer.Response;
import com.monster.melon.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Component
public class AuthInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private HashSet<String> whiteList = new HashSet<>();

    AuthInterceptor(){
        whiteList.add("/login");
        whiteList.add("/signin");
        whiteList.add("/error");
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("hello world");

        System.out.println(request.getRequestURI()+ "   " + whiteList.contains(request.getRequestURI()));

        if(whiteList.contains(request.getRequestURI())){
            return true;
        }

        String token = request.getHeader("token");
        if(token != null && JWTUtil.verify(token,JWTUtil.getUserName(token))){
            return true;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Response resp = new Response();
        resp.setCode(50003);
        resp.setMsg("please login");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        objectMapper.writeValue(printWriter,resp);
        printWriter.close();
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        logger.info("hello world1");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        logger.info("hello world2");

    }

}


