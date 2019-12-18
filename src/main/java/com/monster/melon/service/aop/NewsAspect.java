package com.monster.melon.service.aop;

import com.monster.melon.mapper.NewsMapper;
import com.monster.melon.pojo.News;
import com.monster.melon.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
@Aspect
public class NewsAspect {

    private final NewsMapper mapper;

    public NewsAspect(NewsMapper mapper) {
        this.mapper = mapper;
    }
    @Resource
    private StringRedisTemplate template;

    @Pointcut("execution(* com.monster.melon.service.NewsService.getOnePageNews(..))")
    public void executionPageService(){

    }

    @Pointcut("execution(* com.monster.melon.service.NewsService.insertNews(..))")
    public void executionInsertService(){

    }

    @Pointcut("execution(* com.monster.melon.service.NewsService.deleteNews(..))")
    public void executionDeleteService(){

    }

    @Around("executionPageService()")
    public Object cachePage(ProceedingJoinPoint pjp){
        Integer eId = (Integer) pjp.getArgs()[0];
        Integer page = (Integer) pjp.getArgs()[1];
        return RedisUtil.getPageCache(String.format("news:event:%s",eId.toString()),page,News.class);
    }


    // todo 一致性
    // todo 队列
    @AfterReturning(value = "executionInsertService()",returning = "rvt")
    public void insertCache(Object rvt){
        News param = (News) rvt;
        template.opsForHash().putAll(String.format("news:%d",param.getId()),RedisUtil.object2Map(param));
        template.opsForZSet().add("news",param.getId().toString(),param.getCreateTime().getTime());
        template.opsForZSet().add(String.format("news:user:%d",param.getUserId()),param.getId().toString(),param.getCreateTime().getTime());
        template.opsForZSet().add(String.format("news:event:%d",param.getUserId()),param.getId().toString(),param.getCreateTime().getTime());
    }

    // TODO: 2019/12/17  一致性
    @Before(value = "executionDeleteService()")
    public void deleteCache(JoinPoint joinPoint){
        Integer id = (Integer) joinPoint.getArgs()[0];
        News news = mapper.getNewsById(id);
        template.opsForZSet().remove(String.format("news:%d",news.getId()));
        template.opsForZSet().remove(String.format("news:event:%d",news.getEventId()));
        template.opsForZSet().remove(String.format("news:user:%d",news.getUserId()));
        template.opsForHash().delete(String.format("news:%d",news.getId()));
    }


}
