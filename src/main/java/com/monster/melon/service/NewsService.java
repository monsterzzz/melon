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
        return newsMapper.getOnePageNews(eventId,page * pageNum,pageNum);
    }

    public News insertNews(News news) {
        return newsMapper.insertNews(news);
    }

    public void deleteNews(Integer id) {
        newsMapper.deleteNews(id);
    }

    public News getNewsById(Integer id) {
        return newsMapper.getNewsById(id);
    }


    public List<News> getAllNews(){
        return newsMapper.getAllNews();
    }
}
