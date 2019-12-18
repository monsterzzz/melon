package com.monster.melon.service.aop;


import com.monster.melon.mapper.CommentMapper;
import com.monster.melon.pojo.Comment;
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

@Aspect
@Component
@Slf4j
public class CommentAspect {

    private final CommentMapper mapper;

    @Resource
    private StringRedisTemplate template;

    public CommentAspect(CommentMapper mapper) {
        this.mapper = mapper;
    }

    @Pointcut("execution(* com.monster.melon.service.CommentService.getNewsPageComments(..))")
    public void executionPageCommentService(){

    }

    @Pointcut("execution(* com.monster.melon.service.CommentService.insertComment(..))")
    public void executionInsertCommentService(){

    }

    @Pointcut("execution(* com.monster.melon.service.CommentService.deleteComment(..))")
    public void executionDeleteCommentService(){

    }

    @Around("executionPageCommentService()")
    private Object pageCache(ProceedingJoinPoint pjp){
        Integer nId = (Integer) pjp.getArgs()[0];
        Integer page = (Integer) pjp.getArgs()[1];
        return RedisUtil.getPageCache(String.format("comments:news:%d",nId),page, Comment.class);
    }


    @AfterReturning(value = "executionInsertCommentService()",returning = "rvt")
    private void insertCache(Object rvt){
        News news = (News) rvt;
        template.opsForHash().putAll(String.format("comment:%d",news.getId()),RedisUtil.object2Map(news));
        template.opsForZSet().add(String.format("comments:news:%d",news.getId()),news.getId().toString(),news.getCreateTime().getTime());
        template.opsForZSet().add(String.format("comments:user:%d",news.getId()),news.getId().toString(),news.getCreateTime().getTime());
    }

    @Before("executionDeleteCommentService()")
    private void deleteCache(JoinPoint joinPoint){
        Integer id = (Integer) joinPoint.getArgs()[0];
        Comment comment = mapper.getCommentById(id);
        template.opsForHash().delete(String.format("comment:%d",id));
        template.opsForZSet().remove(String.format("comments:news:%d",comment.getNewsId()));
        template.opsForZSet().remove(String.format("comments:user:%d",comment.getUserId()));
    }

}
