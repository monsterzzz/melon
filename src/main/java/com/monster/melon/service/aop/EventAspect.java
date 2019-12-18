package com.monster.melon.service.aop;


import com.alibaba.fastjson.JSON;
import com.monster.melon.cache.RedisInit;
import com.monster.melon.mapper.EventMapper;
import com.monster.melon.mapper.UserMapper;
import com.monster.melon.pojo.Event;
import com.monster.melon.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@Aspect
@Slf4j
public class EventAspect {

    private static final String fHeader = "execution(* %s)";

    @Resource
    private StringRedisTemplate redisTemplate;

    private final EventMapper eventMapper;
    private final UserMapper userMapper;

    public EventAspect(EventMapper mapper, UserMapper userMapper) {
        this.eventMapper = mapper;
        this.userMapper = userMapper;
    }

    @Pointcut("execution(* com.monster.melon.service.EventService.getOnePageEvent(..))")
    public void executionPageService(){

    }

    @Pointcut("execution(* com.monster.melon.service.EventService.insertEvent(..))")
    public void executionInsertService(){

    }

    @Pointcut("execution(* com.monster.melon.service.EventService.deleteEvent(..))")
    public void executionDeleteService(){

    }

    @Around("executionPageService()")
    public Object cacheService(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer page = (Integer) joinPoint.getArgs()[0];
        page = Math.max(page-1,0);
        int start = page * 7;
        Set<String> ids = redisTemplate.opsForZSet().range("events",start,start+6);
        if(ids == null || ids.size() == 0){
            return new ArrayList<>();
        }
        List<Event> events = new ArrayList<>();
        for(String id : ids){
            Map<Object,Object> o = redisTemplate.opsForHash().entries(String.format("event:%s",id));
            if(o == null || o.isEmpty()){
                log.info("event" + " " + id + " is expired!");
                Event tmp = eventMapper.getEventById(Integer.valueOf(id));
                redisTemplate.opsForHash().putAll(String.format("event:%s",id), RedisInit.object2Map(tmp));
                events.add(tmp);
            }else {
                String jsonStr = JSON.toJSONString(o);
                Event event = JSON.parseObject(jsonStr,Event.class);
                events.add(event);
            }
        }
        return events;
    }

    @AfterReturning(value = "executionInsertService()",returning = "rvt")
    public Object cacheInsert(JoinPoint joinPoint,Object rvt){
        Object[] args = joinPoint.getArgs();
        Integer userId = (Integer) args[1];
        Event event = (Event) rvt;
        redisTemplate.opsForZSet().add(String.format("event:%d",userId),event.getId().toString(),event.getCreateTime().getTime());
        return event;
    }


    //todo 事务支持
    //todo 一致性
    @After(value = "executionDeleteService()")
    public void cacheDelete(JoinPoint joinPoint){
        Integer eventId = (Integer) joinPoint.getArgs()[0];
        Event event = eventMapper.getEventById(eventId);
        redisTemplate.opsForZSet().remove("events",eventId);
        redisTemplate.opsForZSet().remove(String.format("events:user:%d",event.getUser().getId()),eventId);
        redisTemplate.opsForHash().delete("event",eventId.toString());
    }

}
