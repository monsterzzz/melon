package com.monster.melon.service;

import com.monster.melon.mapper.NewsMapper;
import com.monster.melon.pojo.News;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class NewsService {

    private NewsMapper newsMapper;

    @Resource
    private StringRedisTemplate redisTemplate;

    public NewsService(NewsMapper newsMapper) {
        this.newsMapper = newsMapper;
    }

    @Value("${news_page}")
    private Integer pageNum;

    public List<News> getEventAllNews(Integer eventId) {
        return newsMapper.getEventAllNews(eventId);
    }

    public List<News> getUserNews(Integer userId) {
        return newsMapper.getUserNews(userId);
    }

    public List<News> getOnePageNews(Integer eventId, Integer page) {
        page = Math.max(page - 1, 0);
        List<String> newsList = redisTemplate.opsForList().range(String.format("news:%s:%s",eventId,page), page * pageNum, page * pageNum + pageNum);
        if (newsList == null || newsList.size() == 0){
            List<News> newsList1 = newsMapper.getOnePageNews(eventId,page * pageNum,pageNum);
            Map<String,News> map = new HashMap<>();
            for(News news : newsList1){
                map.put(news.getId().toString(),news);
                redisTemplate.opsForList().rightPush(String.format("news:%s:%s",eventId,page),news.getId().toString());
            }
            redisTemplate.expire(String.format("news:%s:%s",eventId,page),360, TimeUnit.SECONDS);
            redisTemplate.opsForHash().putAll("news",map);
            redisTemplate.expire("news",360, TimeUnit.SECONDS);
            return newsList1;
        }
        List<News> newsList1 = new ArrayList<>();
        for(String s : newsList){
            newsList1.add((News) redisTemplate.opsForHash().get("news",s));
        }
        return newsList1;
    }

    public void insertNews(News news) {
        newsMapper.insertNews(news);
    }

    public void deleteNews(Integer id) {
        newsMapper.deleteNews(id);
    }


    public News getNewsById(Integer id) {
        return newsMapper.getNewsById(id);
    }
}
