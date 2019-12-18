package com.monster.melon.service.aop;

import com.monster.melon.mapper.UserMapper;
import com.monster.melon.pojo.User;
import com.monster.melon.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class UserAspect {

    private final UserMapper mapper;

    @Resource
    private StringRedisTemplate template;

    public UserAspect(UserMapper mapper) {
        this.mapper = mapper;
    }


    @Pointcut("execution(* com.monster.melon.service.UserService.getUserById(..))")
    public void executionUserQueryService(){

    }

    @Around("executionUserQueryService()")
    private Object queryId(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer userId = (Integer) joinPoint.getArgs()[0];
        return RedisUtil.Map2Object(template.opsForHash().entries(String.format("user:%d",userId)),User.class);
    }

}
