package com.monster.melon.cache;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.monster.melon.pojo.Comment;
import com.monster.melon.pojo.Event;
import com.monster.melon.pojo.News;
import com.monster.melon.pojo.User;
import com.monster.melon.service.CommentService;
import com.monster.melon.service.EventService;
import com.monster.melon.service.NewsService;
import com.monster.melon.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class RedisInit implements CommandLineRunner {

    private final UserService userService;
    private final CommentService commentService;
    private final NewsService newsService;
    private final EventService eventService;

    @Resource
    private StringRedisTemplate template;

    public RedisInit(UserService userService, CommentService commentService, NewsService newsService, EventService eventService) {
        this.userService = userService;
        this.commentService = commentService;
        this.newsService = newsService;
        this.eventService = eventService;
    }


    @Override
    public void run(String... args) throws Exception {
        initUser();
        initComment();
        initNews();
        initEvent();
    }


    public static JSONObject object2Map(Object o){
        String jsonString = JSON.toJSONString(o);
        return JSON.parseObject(jsonString);
    }

    private void initUser(){
        List<User> users = userService.getAllUser();
        for(User u : users){
            template.opsForZSet().add("users",u.getId().toString(),u.getCreateTime().getTime());
            template.opsForHash().putAll(String.format("user:%d",u.getId()), object2Map(u));
        }
    }

    private void initComment(){
        List<Comment> comments = commentService.getAllComment();
        log.info(Arrays.toString(new List[]{comments}));
        for (Comment c : comments){

            template.opsForZSet().add("comments",c.getId().toString(),c.getCreateTime().getTime());
            template.opsForHash().putAll(String.format("comment:%d",c.getId()),object2Map(c));
            template.opsForZSet().add(String.format("comments:user:%d",c.getUserId()),c.getId().toString(),c.getCreateTime().getTime());
            template.opsForZSet().add(String.format("comments:news:%d",c.getNewsId()),c.getId().toString(),c.getCreateTime().getTime());
        }
    }

    private void initNews(){
        List<News> news = newsService.getAllNews();
        for(News n : news){
            template.opsForZSet().add("news",n.getId().toString(),n.getCreateTime().getTime());
            template.opsForHash().putAll(String.format("news:%d",n.getId()),object2Map(n));
            template.opsForZSet().add(String.format("news:user:%d",n.getUserId()),n.getId().toString(),n.getCreateTime().getTime());
            template.opsForZSet().add(String.format("news:event:%d",n.getEventId()),n.getId().toString(),n.getCreateTime().getTime());
            if(n.getAdminOpt() == 1){
                template.opsForZSet().add(String.format("news:%d:admin",n.getId()),n.getId().toString(),n.getCreateTime().getTime());
            }
        }
    }


    private void initEvent(){
        List<Event> events = eventService.getAllEvents();
        for(Event e : events){
            template.opsForZSet().add("events",e.getId().toString(),e.getCreateTime().getTime());
            template.opsForZSet().add("eventsViewRank",e.getId().toString(),e.getViewNum());
            template.opsForZSet().add("eventsNewsRank",e.getId().toString(),e.getNewsNum());
            template.opsForHash().putAll(String.format("event:%d",e.getId()),object2Map(e));
            template.opsForZSet().add(String.format("events:user:%d",e.getUser().getId()),e.getId().toString(),e.getCreateTime().getTime());
        }
    }






}
